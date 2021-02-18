package com.recorder.recorder.controller;

import com.alibaba.fastjson.JSONObject;
import com.recorder.recorder.beans.RecordParam;
import com.recorder.recorder.beans.Result;
import com.recorder.recorder.service.FFmpegService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
public class FFmpegController {

    @Autowired
    FFmpegService fFmpegService;

    /**
     * 获取回看地址
     *
     * @param recordParam
     * @return
     */
    @RequestMapping(value = "record/playRecord", method = RequestMethod.POST)
    public Result playRecord(@RequestBody RecordParam recordParam) {
        return fFmpegService.playRecord(recordParam);
    }

    /**
     * 保存flv文件后的回调
     *
     * @param name
     * @param path
     * @return
     */
    @RequestMapping(value = "record/compensateFirstFlv", method = RequestMethod.POST)
    public Result compensateFirstFlv(@RequestParam String name, @RequestParam String path) {
        return fFmpegService.compensateFirstFlv(path, System.currentTimeMillis());
    }

    /**
     * 查询某天的所有回看文件
     *
     * @param date
     * @param ip
     * @return
     */
    @RequestMapping(value = "record/queryRecordList", method = RequestMethod.GET)
    public Result queryRecordList(@RequestParam String date, @RequestParam String ip) {
        return fFmpegService.queryRecordList(date, ip);
    }


    @RequestMapping(value = "record/dropStream", method = RequestMethod.POST)
    public Result dropStream(@RequestParam(name = "name", required = true) String name, HttpServletResponse response){
        System.out.println(name);
        Boolean result = fFmpegService.dropStream(name);
        System.out.println(result);
        if(!result){
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);//设置状态码
        }
        return Result.success();
    }

}
