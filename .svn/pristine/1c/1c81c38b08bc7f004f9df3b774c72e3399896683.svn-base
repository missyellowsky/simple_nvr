package de.onvif.controller;

import com.alibaba.fastjson.JSONObject;
import de.onvif.beans.CameraPojo;
import de.onvif.beans.Config;
import de.onvif.beans.Result;
import de.onvif.beans.StreamParam;
import de.onvif.cache.CacheUtil;
import de.onvif.service.MediaService;
import de.onvif.utils.Utils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
@Slf4j
public class MediaController {

    @Autowired
    private MediaService mediaService;

    @Autowired
    public Config config;// 配置文件bean

    @RequestMapping(value = "media/live", method = RequestMethod.POST)
    public Result live(@RequestBody StreamParam streamParam){
        if(StringUtils.isEmpty(streamParam.getIp()) || StringUtils.isEmpty(streamParam.getStreamType())){
            return Result.fail();
        }
        String stream = mediaService.live(streamParam);
        if(StringUtils.isEmpty(stream)){
            return Result.fail();
        }
        return Result.success(stream);
    }

    @RequestMapping(value = "media/record", method = RequestMethod.POST)
    public Result record(@RequestBody StreamParam streamParam){
        if(StringUtils.isEmpty(streamParam.getIp()) || StringUtils.isEmpty(streamParam.getStreamType()) || StringUtils.isEmpty(streamParam.getStartTime()) || StringUtils.isEmpty(streamParam.getEndTime())){
            return Result.fail();
        }
        String stream = mediaService.record(streamParam);
        if(StringUtils.isEmpty(stream)){
            return Result.fail();
        }
        return Result.success(stream);
    }



    /**
     * @Title: openCamera
     * @Description: 开启视频流
     * @return Map<String,String>
     **/
    /*@RequestMapping(value = "media/cameras", method = RequestMethod.POST)
    public Map<String, Object> openCamera(@RequestBody CameraPojo pojo) {
        // 返回结果
        Map<String, Object> map = new LinkedHashMap<String, Object>();
        // openStream返回结果
        Map<String, Object> openMap = new HashMap<>();
        JSONObject cameraJson = JSONObject.parseObject(JSONObject.toJSON(pojo).toString());
        // 需要校验非空的参数
        String[] isNullArr = { "ip", "username", "password", "channel", "stream" };
        // 空值校验
        if (!Utils.isNullParameters(cameraJson, isNullArr)) {
            map.put("msg", "输入参数不完整");
            map.put("code", 1);
            return map;
        }
        // ip格式校验
        if (!Utils.isTrueIp(pojo.getIp())) {
            map.put("msg", "ip格式输入错误");
            map.put("code", 2);
            return map;
        }
        if (null != pojo.getStarttime() || "".equals(pojo.getStarttime())) {
            // 开始时间校验
            if (!Utils.isTrueTime(pojo.getStarttime())) {
                map.put("msg", "starttime格式输入错误");
                map.put("code", 3);
                return map;
            }
            if (null != pojo.getEndtime() || "".equals(pojo.getEndtime())) {
                if (!Utils.isTrueTime(pojo.getEndtime())) {
                    map.put("msg", "endtime格式输入错误");
                    map.put("code", 4);
                    return map;
                }
                // 结束时间要大于开始时间
                try {
                    long starttime = new SimpleDateFormat("yyyy-MM-dd HH:ss:mm").parse(pojo.getStarttime()).getTime();
                    long endtime = new SimpleDateFormat("yyyy-MM-dd HH:ss:mm").parse(pojo.getEndtime()).getTime();
                    if (endtime - starttime < 0) {
                        map.put("msg", "endtime需要大于starttime");
                        map.put("code", 5);
                        return map;
                    }
                } catch (ParseException e) {
                    log.error(e.getMessage());
                }
            }
        }

        CameraPojo cameraPojo = new CameraPojo();
        // 获取当前时间
        String opentime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date().getTime());
        Set<String> keys = CacheUtil.STREATMAP.keySet();
        // 缓存是否为空
        if (0 == keys.size()) {
            // 开始推流

            openMap = mediaService.openStream(pojo.getIp(), pojo.getUsername(), pojo.getPassword(), pojo.getChannel(),
                    pojo.getStream(), pojo.getStarttime(), pojo.getEndtime(), opentime);
            if (Integer.parseInt(openMap.get("errorcode").toString()) == 0) {
                map.put("url", ((CameraPojo) openMap.get("pojo")).getUrl());
                map.put("token", ((CameraPojo) openMap.get("pojo")).getToken());
                map.put("msg", "打开视频流成功");
                map.put("code", 0);
            } else {
                map.put("msg", openMap.get("message"));
                map.put("code", openMap.get("errorcode"));
            }
        } else {
            // 是否存在的标志；false：不存在；true：存在
            boolean sign = false;
            if (null == pojo.getStarttime()) {// 直播流
                for (String key : keys) {
                    if (pojo.getIp().equals(CacheUtil.STREATMAP.get(key).getIp())
                            && pojo.getChannel().equals(CacheUtil.STREATMAP.get(key).getChannel())
                            && null == CacheUtil.STREATMAP.get(key).getStarttime()) {// 存在直播流
                        cameraPojo = CacheUtil.STREATMAP.get(key);
                        sign = true;
                        break;
                    }
                }
                if (sign) {// 存在
                    cameraPojo.setCount(cameraPojo.getCount() + 1);
                    cameraPojo.setOpentime(opentime);
                    map.put("url", cameraPojo.getUrl());
                    map.put("token", cameraPojo.getToken());
                    map.put("msg", "打开视频流成功");
                    map.put("code", 0);
                } else {
                    openMap = mediaService.openStream(pojo.getIp(), pojo.getUsername(), pojo.getPassword(), pojo.getChannel(),
                            pojo.getStream(), pojo.getStarttime(), pojo.getEndtime(), opentime);
                    if (Integer.parseInt(openMap.get("errorcode").toString()) == 0) {
                        map.put("url", ((CameraPojo) openMap.get("pojo")).getUrl());
                        map.put("token", ((CameraPojo) openMap.get("pojo")).getToken());
                        map.put("msg", "打开视频流成功");
                        map.put("code", 0);
                    } else {
                        map.put("msg", openMap.get("message"));
                        map.put("code", openMap.get("errorcode"));
                    }
                }

            } else {// 历史流
                for (String key : keys) {
                    if (pojo.getIp().equals(CacheUtil.STREATMAP.get(key).getIp())
                            && CacheUtil.STREATMAP.get(key).getStarttime() != null) {// 存在历史流
                        sign = true;
                        cameraPojo = CacheUtil.STREATMAP.get(key);
                        break;
                    }
                }
                if (sign && cameraPojo.getCount() == 0) {
                    map.put("msg", "设备正在结束回放,请稍后再试");
                    map.put("code", 9);
                } else if (sign && cameraPojo.getCount() != 0) {
                    map.put("msg", "设备正在进行回放,请稍后再试");
                    map.put("code", 8);
                } else {
                    openMap = mediaService.openStream(pojo.getIp(), pojo.getUsername(), pojo.getPassword(), pojo.getChannel(),
                            pojo.getStream(), pojo.getStarttime(), pojo.getEndtime(), opentime);
                    if (Integer.parseInt(openMap.get("errorcode").toString()) == 0) {
                        map.put("url", ((CameraPojo) openMap.get("pojo")).getUrl());
                        map.put("token", ((CameraPojo) openMap.get("pojo")).getToken());
                        map.put("msg", "打开视频流成功");
                        map.put("code", 0);
                    } else {
                        map.put("msg", openMap.get("message"));
                        map.put("code", openMap.get("errorcode"));
                    }
                }
            }
        }
        return map;
    }*/



}
