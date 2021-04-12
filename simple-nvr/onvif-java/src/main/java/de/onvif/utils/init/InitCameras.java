package de.onvif.utils.init;

import de.onvif.beans.DeviceInfo;
import de.onvif.beans.StreamParam;
import de.onvif.cache.CacheUtil;
import de.onvif.discovery.DeviceDiscovery;
import de.onvif.discovery.OnvifDiscovery;
import de.onvif.service.DeviceService;
import de.onvif.service.MediaService;
import de.onvif.soap.OnvifDevice;
import de.onvif.utils.CommenUtils;
import de.onvif.utils.RedisUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.onvif.ver10.schema.CapabilityCategory;
import org.onvif.ver10.schema.NetworkHostType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import javax.xml.soap.SOAPException;
import java.net.*;
import java.util.*;

import static de.onvif.beans.constant.Constant.DEVICE_CACHE;
import static de.onvif.beans.constant.Constant.TRUE;
import static de.onvif.utils.CommenUtils.getSftpInfo;

@Component
@Slf4j
public class InitCameras implements ApplicationRunner {

    @Value("${camera.username}")
    private String userName;

    @Value("${camera.password}")
    private String password;

    @Value("${camera.autopush}")
    private String autoPush;

    @Autowired
    private MediaService mediaService;

    @Autowired
    DeviceService deviceService;

    @Autowired
    private RedisUtils redisUtils;

    @Override
    public void run(ApplicationArguments args) {
        try {
            initCamera(null);
        } catch (SOAPException e) {
            log.error("SOAPException: " + e.getMessage());
        } catch (ConnectException e) {
            log.error("ConnectException: " + e.getMessage());
        } catch (Exception e){
            log.error("InitCameras: " + e.getMessage());
        }
    }

    public List<DeviceInfo> initCamera(String netParam) throws SOAPException, ConnectException {
        List<String> nets = null;
        if(StringUtils.isEmpty(netParam)){
            nets = queryLocalNet();
        }else{
            nets = new ArrayList<>();
            nets.add(netParam);
        }
        List<DeviceInfo> onvifDevices = new ArrayList<>();
        for (String net:
                nets) {
            Map<String, Collection<URL>> netUrls = OnvifDiscovery.discoverOnvifURLs(net);
            for (String key:
                 netUrls.keySet()) {
                Collection<URL> urls = netUrls.get(key);
                for (URL u : urls) {
                    String ip = getSftpInfo(u);
                    OnvifDevice device = new OnvifDevice(u, userName, password);
                    DeviceInfo deviceInfo = device.getDeviceInfo();
                    deviceInfo.setIp(ip);
                    deviceInfo.setId(CommenUtils.generateCameraId(ip, key));
                    deviceInfo.setNet(key);
                    onvifDevices.add(deviceInfo);
                    CacheUtil.CAMERAMAP.put(ip, device);
                    if (TRUE.equals(autoPush)) {
                        StreamParam streamParam = new StreamParam();
                        streamParam.setIp(ip);
                        streamParam.setStreamType("rtmp");
                        mediaService.live(streamParam);
                    }
                }
                //redisUtils.set(DEVICE_CACHE,onvifDevices);
                if(onvifDevices != null && onvifDevices.size() > 0){
                    deviceService.refreshCameras(onvifDevices);
                }
            }
        }
        return onvifDevices;
    }

    private List<String> queryLocalNet(){
        List<String> inetAddresses = new ArrayList<>();
        try {
            final Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces();
            if (interfaces != null) {
                while (interfaces.hasMoreElements()) {
                    NetworkInterface anInterface = interfaces.nextElement();
                    if (!anInterface.isLoopback()) {
                        final List<InterfaceAddress> interfaceAddresses = anInterface.getInterfaceAddresses();
                        for (InterfaceAddress address : interfaceAddresses) {
                            Class clz = address.getAddress().getClass();
                            if (!DeviceDiscovery.enableIPv6 && address.getAddress() instanceof Inet6Address) continue;
                            inetAddresses.add(address.getAddress().getHostAddress());
                        }
                    }
                }
            }
        } catch (SocketException e) {
            e.printStackTrace();
        }
        return inetAddresses;
    }
}
