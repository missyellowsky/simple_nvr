package com.recorder.recorder.controller;

import com.recorder.recorder.beans.RecordParam;
import com.recorder.recorder.beans.Result;
import com.recorder.recorder.service.FFmpegService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FFmpegController {

    @Autowired
    FFmpegService fFmpegService;

    @RequestMapping(value = "record/playRecord", method = RequestMethod.POST)
    public Result playRecord(@RequestBody RecordParam recordParam){
        return fFmpegService.playRecord(recordParam);
    }
}
