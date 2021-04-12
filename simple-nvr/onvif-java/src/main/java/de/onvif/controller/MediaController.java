package de.onvif.controller;

import com.alibaba.fastjson.JSONObject;
import de.onvif.beans.*;
import de.onvif.cache.CacheUtil;
import de.onvif.service.MediaService;
import de.onvif.utils.Utils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
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

    @RequestMapping(value = "media/addTranscodeTask", method = RequestMethod.POST)
    public Result addTranscodeTask(@RequestBody TranscodeParam transcodeParam){
        if(StringUtils.isEmpty(transcodeParam.getInputUrl())){
            return Result.fail("no stream!");
        }
        String stream = mediaService.addTranscodeTask(transcodeParam);
        if(StringUtils.isEmpty(stream)){
            return Result.fail();
        }
        return Result.success(stream);
    }

    @RequestMapping(value = "media/stopTranscodeTask", method = RequestMethod.POST)
    public Result stopTranscodeTask(@RequestBody TranscodeParam transcodeParam){
        Boolean result = mediaService.stopTranscodeTask(transcodeParam.getTaskId());
        if(result){
            return Result.success();
        }
        return Result.fail();
    }

    @RequestMapping(value = "media/dropStream", method = RequestMethod.POST)
    public Result dropStream(@RequestParam(name = "name", required = true) String name, HttpServletResponse response){
        System.out.println(name);
        Boolean result = mediaService.stopTranscodeTask(name);
        System.out.println(result);
        if(!result){
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);//设置状态码
        }
        return Result.success();
    }

    @RequestMapping(value = "media/queryTranscodeTask", method = RequestMethod.GET)
    public Result queryTranscodeTask(){
        return Result.success(CacheUtil.PUSHMAP);
    }

}
