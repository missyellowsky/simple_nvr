package de.onvif.utils.init;

import de.onvif.beans.StreamParam;
import de.onvif.cache.CacheUtil;
import de.onvif.discovery.OnvifDiscovery;
import de.onvif.service.MediaService;
import de.onvif.soap.OnvifDevice;
import de.onvif.utils.RedisUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Collection;

import static de.onvif.beans.constant.Constant.TRUE;

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
    private RedisUtils redisUtils;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        Collection<URL> urls = OnvifDiscovery.discoverOnvifURLs();
        for (URL u : urls) {
            String ip = getSftpInfo(u);
            OnvifDevice device = new OnvifDevice(u, userName, password);
            CacheUtil.CAMERAMAP.put(ip, device);
            if (TRUE.equals(autoPush)) {
                StreamParam streamParam = new StreamParam();
                streamParam.setIp(ip);
                streamParam.setStreamType("rtmp");
                mediaService.live(streamParam);
            }
        }
    }


    private String getSftpInfo(URL url) {
        String userInfo = url.getUserInfo();
        System.err.println("用户信息：" + userInfo);
        if (userInfo != null) {
            int index = userInfo.indexOf(":");
            String userName = userInfo.substring(0, index);
            System.err.println("用户名：" + userName);
            String password = userInfo.substring(index + 1);
            System.err.println("密码：" + password);
        }
        int port = url.getPort();
        System.err.println("端口号：" + port);
        String host = url.getHost();
        System.err.println("host：" + host);
        String path = url.getPath();
        System.err.println("远程路径：" + path);
        return host;
    }


}
