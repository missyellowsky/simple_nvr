package de.onvif.utils.init;

import de.onvif.beans.DeviceInfo;
import de.onvif.beans.StreamParam;
import de.onvif.cache.CacheUtil;
import de.onvif.discovery.OnvifDiscovery;
import de.onvif.service.DeviceService;
import de.onvif.service.MediaService;
import de.onvif.soap.OnvifDevice;
import de.onvif.utils.RedisUtils;
import lombok.extern.slf4j.Slf4j;
import org.onvif.ver10.schema.CapabilityCategory;
import org.onvif.ver10.schema.NetworkHostType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import javax.xml.soap.SOAPException;
import java.net.ConnectException;
import java.net.MalformedURLException;
import java.net.URL;
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
            initCamera();
        } catch (SOAPException e) {
            log.error("SOAPException: " + e.getMessage());
        } catch (ConnectException e) {
            log.error("ConnectException: " + e.getMessage());
        }
    }

    public List<DeviceInfo> initCamera() throws SOAPException, ConnectException {
        Collection<URL> urls = OnvifDiscovery.discoverOnvifURLs();
        List<DeviceInfo> onvifDevices = new ArrayList<>();
        for (URL u : urls) {
            String ip = getSftpInfo(u);
            OnvifDevice device = new OnvifDevice(u, userName, password);
            DeviceInfo deviceInfo = device.getDeviceInfo();
            deviceInfo.setIp(ip);
            deviceInfo.setId(UUID.randomUUID().toString());
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
        return onvifDevices;
    }
}
