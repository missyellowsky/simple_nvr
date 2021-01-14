package com.recorder.recorder.beans.constant;

import lombok.Data;

@Data
public class Constant {
    //return type
    public static final String SUCCESS = "success";
    public static final String FAIL = "fail";
    public static final String SUCCESS_CODE = "1";
    public static final String FAIL_CODE = "0";

    //stream
    public static final String RTMP = "rtmp";
    public static final String RTSP = "rtsp";
    public static final String HLS = "hls";
    public static final String FLV = "flv-http";


    //host
    public static final String HOST_MAPPING = "HOST_MAPPING";
    public static final String CAMERA_HOST = "CAMERA_HOST";
    public static final String LOCAL_HOST = "127.0.0.1";

    //auth
    public static final String PASS_MEMBER = "PASS_MEMBER";
}
