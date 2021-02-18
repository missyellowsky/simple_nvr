package de.onvif.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import de.onvif.beans.*;
import de.onvif.beans.constant.Constant;
import de.onvif.cache.CacheUtil;
import de.onvif.service.DeviceService;
import de.onvif.service.MediaService;
import de.onvif.soap.OnvifDevice;
import de.onvif.thread.CameraThread;
import de.onvif.utils.HttpClient;
import de.onvif.utils.RSAEncrypt;
import de.onvif.utils.RedisUtils;
import de.onvif.utils.Utils;
import de.onvif.utils.componentUtils.RedisDataUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.xml.soap.SOAPException;
import java.io.IOException;
import java.net.ConnectException;
import java.net.InetSocketAddress;
import java.net.MalformedURLException;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.*;

import static de.onvif.beans.constant.Constant.*;

@Service
@Slf4j
public class MediaServiceImpl implements MediaService {

    @Value("${rsa-member}")
    private String member;

    @Value("${rsa.publicKey}")
    private String publicKey;

    @Value("${camera.username}")
    private String username;

    @Value("${camera.password}")
    private String password;

    @Autowired
    public Config config;// 配置文件bean

    @Autowired
    private RedisDataUtil redisDataUtil;

    @Autowired
    private DeviceService deviceService;


    /**
     * 开始直播推流
     * @param streamParam
     * @return
     */
    @Override
    public String live(StreamParam streamParam) {
        String stream = null;
        try {
            if (Constant.RTSP.equals(streamParam.getStreamType())) {
                stream = getRTSPStream(streamParam);
            } else {
                stream = getOtheerStream(streamParam);
            }
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return stream;
    }

    /**
     * 通过设备获取rtsp流
     * @param streamParam
     * @return
     * @throws SOAPException
     * @throws MalformedURLException
     * @throws ConnectException
     */
    private String getRTSPStream(StreamParam streamParam) throws SOAPException, MalformedURLException, ConnectException {
        OnvifDevice device = CacheUtil.CAMERAMAP.get(streamParam.getIp());
        if(device == null){
            device = new OnvifDevice(streamParam.getIp(), username, password);
            CacheUtil.CAMERAMAP.put(streamParam.getIp(), device);
        }
        String rtsp = device.getStreamUri();
        if (!StringUtils.isEmpty(rtsp)) {
            rtsp = rtsp.replace("//", "//" + username + ":" + password + "@");
        }
        return rtsp;
    }

    /**
     * 推流并返回非rtsp流
     * @param streamParam
     * @return
     * @throws ConnectException
     * @throws SOAPException
     * @throws MalformedURLException
     */
    private String getOtheerStream(StreamParam streamParam) throws ConnectException, SOAPException, MalformedURLException {
        return checkExist(streamParam);
    }

    /**
     * @param ip
     * @param username
     * @param password
     * @param channel
     * @param stream
     * @param starttime
     * @param endtime
     * @param opentime
     * @return CameraPojo
     * @throws IOException
     * @Title: openStream
     * @Description: 推流器
     **/
    @Override
    public Map<String, Object> openStream(String ip, String username, String password, String channel, String stream,
                                          String starttime, String endtime, String opentime) {
        Map<String, Object> map = new HashMap<>();
        CameraPojo cameraPojo = new CameraPojo();
        // 生成token
        String token = UUID.randomUUID().toString();
        String rtsp = "";
        String rtmp = "";
        String IP = Utils.IpConvert(ip);
        String url = "";
        boolean sign = false;// 该nvr是否再回放，true：在回放；false： 没在回放
        // 历史流
        if (null != starttime && !"".equals(starttime)) {
            if (null != endtime && !"".equals(endtime)) {
                rtsp = "rtsp://" + username + ":" + password + "@" + IP + ":554/Streaming/tracks/"
                        + (Integer.valueOf(channel) - 32) + "01?starttime=" + Utils.getTime(starttime).substring(0, 8)
                        + "t" + Utils.getTime(starttime).substring(8) + "z'&'endtime="
                        + Utils.getTime(endtime).substring(0, 8) + "t" + Utils.getTime(endtime).substring(8) + "z";
                cameraPojo.setStarttime(Utils.getTime(starttime));
                cameraPojo.setEndTime(Utils.getTime(endtime));
            } else {
                String startTime = Utils.getStarttime(starttime);
                String endTime = Utils.getEndtime(starttime);
                rtsp = "rtsp://" + username + ":" + password + "@" + IP + ":554/Streaming/tracks/"
                        + (Integer.valueOf(channel) - 32) + "01?starttime=" + startTime.substring(0, 8) + "t"
                        + startTime.substring(8) + "z'&'endtime=" + endTime.substring(0, 8) + "t" + endTime.substring(8)
                        + "z";
                cameraPojo.setStarttime(Utils.getStarttime(starttime));
                cameraPojo.setEndTime(Utils.getEndtime(starttime));
            }
//			rtmp = "rtmp://" + Utils.IpConvert(config.getPush_host()) + ":" + config.getPush_port() + "/history/"
//					+ token;
            rtmp = "rtmp://" + Utils.IpConvert(config.getPush_host()) + ":" + config.getPush_port() + "/history/test";
            if (config.getHost_extra().equals("127.0.0.1")) {
                url = rtmp;
            } else {
                url = "rtmp://" + Utils.IpConvert(config.getHost_extra()) + ":" + config.getPush_port() + "/history/"
                        + token;
            }
        } else {// 直播流
            rtsp = "rtsp://" + username + ":" + password + "@" + IP + ":554/h264/ch" + channel + "/" + stream
                    + "/av_stream";
            rtmp = "rtmp://" + Utils.IpConvert(config.getPush_host()) + ":" + config.getPush_port() + "/live/" + token;
            if (config.getHost_extra().equals("127.0.0.1")) {
                url = rtmp;
            } else {
                url = "rtmp://" + Utils.IpConvert(config.getHost_extra()) + ":" + config.getPush_port() + "/live/"
                        + token;
            }
        }

        cameraPojo.setUsername(username);
        cameraPojo.setPassword(password);
        cameraPojo.setIp(IP);
        cameraPojo.setChannel(channel);
        cameraPojo.setStream(stream);
        cameraPojo.setRtsp(rtsp);
        cameraPojo.setRtmp(rtmp);
        cameraPojo.setUrl(url);
        cameraPojo.setOpentime(opentime);
        cameraPojo.setCount(1);
        cameraPojo.setToken(token);

        // 解决ip输入错误时，grabber.start();出现阻塞无法释放grabber而导致后续推流无法进行；
        Socket rtspSocket = new Socket();
        Socket rtmpSocket = new Socket();

        // 建立TCP Scoket连接，超时时间1s，如果成功继续执行，否则return
        try {
            rtspSocket.connect(new InetSocketAddress(cameraPojo.getIp(), 554), 1000);
        } catch (IOException e) {
            log.error("与拉流IP：   " + cameraPojo.getIp() + "   端口：   554    建立TCP连接失败！");
            map.put("pojo", cameraPojo);
            map.put("errorcode", 6);
            map.put("message", "与拉流IP：   " + cameraPojo.getIp() + "   端口：   554    建立TCP连接失败！");
            return map;
        }
        try {
            rtmpSocket.connect(new InetSocketAddress(Utils.IpConvert(config.getPush_host()),
                    Integer.parseInt(config.getPush_port())), 1000);
        } catch (IOException e) {
            log.error("与推流IP：   " + config.getPush_host() + "   端口：   " + config.getPush_port() + " 建立TCP连接失败！");
            map.put("pojo", cameraPojo);
            map.put("errorcode", 7);
            map.put("message",
                    "与推流IP:" + config.getPush_host() + " 端口: " + config.getPush_port() + " 建立连接失败,请检查nginx服务");
            return map;
        }
        // 执行任务
        CameraThread.MyRunnable job = new CameraThread.MyRunnable(cameraPojo);
        CameraThread.MyRunnable.es.execute(job);
        JOBMAP.put(token, job);
        map.put("pojo", cameraPojo);
        map.put("errorcode", 0);
        map.put("message", "打开视频流成功");
        return map;
    }

    /**
     * 获取会看流地址
     * @param streamParam
     * @return
     */
    @Override
    public String record(StreamParam streamParam) {
        CameraPojo cameraPojoFromRedis = getCameraByIp(streamParam.getIp());
        if (cameraPojoFromRedis == null) {
            return null;
        }
        RecordParam recordParam = new RecordParam(streamParam);
        String host = cameraPojoFromRedis.getPlayHost();
        String url = "http://" + host + ":" + config.getRecorder_port() + "/record/playRecord";
        HttpClient httpClient = HttpClient.getInstance();
        String resp = httpClient.postMethodReuqest(url, JSONObject.toJSONString(recordParam));
        if(StringUtils.isEmpty(resp)){
            return null;
        }
        Result<String> result = JSON.parseObject(resp, Result.class);
        String rsa = null;
        try {
            rsa = java.net.URLEncoder.encode(java.net.URLEncoder.encode(RSAEncrypt.encrypt(member + "-" + System.currentTimeMillis(), publicKey), "UTF-8"), "UTF-8");
        } catch (Exception e) {
            rsa = "";
        }
        String newUrl = result.getObj().toString();
        switch (streamParam.getStreamType()) {
            case HLS:
                newUrl = newUrl;
                break;
            case FLV:
                newUrl = newUrl + "&pass=" + rsa;
                break;
            default:newUrl = newUrl + "?pass=" + rsa;
        }
        return newUrl;
    }


    /**
     * 校验流是否已推送并获取播放地址
     * @param streamParam
     * @return
     * @throws SOAPException
     * @throws MalformedURLException
     * @throws ConnectException
     */
    private String checkExist(StreamParam streamParam) throws SOAPException, MalformedURLException, ConnectException {
        Set<String> keys = CacheUtil.STREATMAP.keySet();
        CameraPojo cameraPojoFromRedis = getCameraByIp(streamParam.getIp());
        if (cameraPojoFromRedis == null) {
            return null;
        }
        List<String> ips = cameraPojoFromRedis.getHost();
        String playUrl = cameraPojoFromRedis.getPlayHost();
        String url = null;
        String opentime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date().getTime());
        if (0 == keys.size()) {
            for (String host :
                    ips) {
                url = openStream(streamParam, host, playUrl);
            }
            return url;
        } else {
            boolean sign = false;
            CameraPojo cameraPojo = new CameraPojo();
            for (String key : keys) {
                if (streamParam.getIp().equals(CacheUtil.STREATMAP.get(key).getIp())
                        && null == CacheUtil.STREATMAP.get(key).getStarttime()) {// 存在直播流

                    cameraPojo = CacheUtil.STREATMAP.get(key);
                    cameraPojo.setCount(cameraPojo.getCount() + 1);
                    cameraPojo.setOpentime(opentime);
                    String newUrl = chooseStreamType(streamParam, playUrl);
                    cameraPojo.setUrl(newUrl);
                    sign = true;
                    break;
                }
            }
            if (sign) {
                return cameraPojo.getUrl();
            } else {
                for (String host :
                        ips) {
                    url = openStream(streamParam, host, playUrl);
                }
                return url;
            }
        }
    }

    /**
     * 根据入参组装播放地址
     * @param streamParam
     * @param host
     * @return
     */
    private String chooseStreamType(StreamParam streamParam, String host){
        String rsa = null;
        try {
            rsa = java.net.URLEncoder.encode(java.net.URLEncoder.encode(RSAEncrypt.encrypt(member + "-" + System.currentTimeMillis(), publicKey), "UTF-8"), "UTF-8");
        } catch (Exception e) {
            rsa = "";
        }
        String newUrl;
        switch (streamParam.getStreamType()) {
            case HLS:
                newUrl = "http://" + host + ":" + config.getPlay_port() + "/hls/" + streamParam.getIp() + "/index.m3u8";
                break;
            case FLV:
                newUrl = "http://" + host + ":" + config.getPlay_port() + "/live?port=" + config.getPush_port() + "&app=live&stream=" + streamParam.getIp() + "&pass=" + rsa;
                break;
            default:newUrl = "rtmp://" + host + ":" + config.getPush_port() + "/live/" + streamParam.getIp() + "?pass=" + rsa;
        }
        return newUrl;
    }

    /**
     * 通过ip从redis获取设备
     * @param ip
     * @return
     */
    private CameraPojo getCameraFromRedisByIp(String ip) {
        List<CameraPojo> cameraPojoList = redisDataUtil.getCameraHostFromRedis();
        CameraPojo cameraPojo = new CameraPojo();
        cameraPojo.setIp(ip);
        CameraPojo cameraPojoRedis = cameraPojoList.get(cameraPojoList.indexOf(cameraPojo));
        return cameraPojoRedis;
    }

    /**
     * 通过ip从redis获取设备
     * @param ip
     * @return
     */
    private CameraPojo getCameraByIp(String ip) {
        CameraPojo cameraPojo = deviceService.getCameraByIp(ip);
        return cameraPojo;
    }

    /**
     * 推流
     * @param streamParam
     * @param host
     * @param playHostStr
     * @return
     * @throws ConnectException
     * @throws SOAPException
     * @throws MalformedURLException
     */
    private String openStream(StreamParam streamParam, String host, String playHostStr) throws ConnectException, SOAPException, MalformedURLException {
        String rsa = null;
        try {
            rsa = java.net.URLEncoder.encode(java.net.URLEncoder.encode(RSAEncrypt.encrypt(member + "-" + System.currentTimeMillis(), publicKey), "UTF-8"), "UTF-8");
        } catch (Exception e) {
            rsa = "";
        }
        String rtsp = getRTSPStream(streamParam);
        if (StringUtils.isEmpty(rtsp)) {
            return null;
        }
        String token = UUID.randomUUID().toString() + "_" + streamParam.getStreamType();
        String rtmp = "rtmp://" + host + ":" + config.getPush_port() + "/live/" + streamParam.getIp() + "?pass=" + rsa;
        String url;
        CameraPojo cameraPojo = new CameraPojo();
        url = chooseStreamType(streamParam, playHostStr);

        String opentime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date().getTime());
        cameraPojo.setUsername(username);
        cameraPojo.setPassword(password);
        cameraPojo.setIp(streamParam.getIp());
        cameraPojo.setStream("main");
        cameraPojo.setRtsp(rtsp);
        cameraPojo.setRtmp(rtmp);
        cameraPojo.setUrl(url);
        cameraPojo.setCount(1);
        cameraPojo.setToken(token);
        cameraPojo.setOpentime(opentime);
        // 解决ip输入错误时，grabber.start();出现阻塞无法释放grabber而导致后续推流无法进行；
        Socket rtspSocket = new Socket();
        Socket rtmpSocket = new Socket();

        /*try {
            rtmpSocket.connect(new InetSocketAddress(Utils.IpConvert(config.getPush_host()),
                    Integer.parseInt(config.getPush_port())), 1000);
        } catch (IOException e) {
            log.error("与推流IP：   " + config.getPush_host() + "   端口：   " + config.getPush_port() + " 建立TCP连接失败！");
            return null;
        }*/
        // 执行任务
        CameraThread.MyRunnable job = new CameraThread.MyRunnable(cameraPojo);
        CameraThread.MyRunnable.es.execute(job);
        JOBMAP.put(token, job);
        return url;
    }
}
