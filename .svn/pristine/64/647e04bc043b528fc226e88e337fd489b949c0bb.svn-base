package de.onvif.service;

import de.onvif.beans.StreamParam;
import de.onvif.thread.CameraThread;

import java.util.HashMap;
import java.util.Map;

public interface MediaService {
    // 存放任务 线程
    public static Map<String, CameraThread.MyRunnable> JOBMAP = new HashMap<String, CameraThread.MyRunnable>();
    String live(StreamParam streamParam);

    Map<String, Object> openStream(String ip, String username, String password, String channel, String stream,
                                   String starttime, String endtime, String opentime);

    String record(StreamParam streamParam);
}
