package de.onvif.service;

import de.onvif.beans.StreamParam;
import de.onvif.beans.TranscodeParam;
import de.onvif.thread.CameraThread;

import java.util.HashMap;
import java.util.Map;

public interface MediaService {
    // 存放任务 线程

    String live(StreamParam streamParam);

    Map<String, Object> openStream(String ip, String username, String password, String channel, String stream,
                                   String starttime, String endtime, String opentime);

    String record(StreamParam streamParam);

    String addTranscodeTask(TranscodeParam transcodeParam);

    Boolean stopTranscodeTask(String name);
}
