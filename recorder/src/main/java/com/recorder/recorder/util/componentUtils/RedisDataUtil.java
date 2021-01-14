package com.recorder.recorder.util.componentUtils;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import de.onvif.beans.CameraPojo;
import com.recorder.recorder.beans.constant.Constant;
import com.recorder.recorder.util.RedisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.List;

@Component
public class RedisDataUtil {

    @Autowired
    private RedisUtils redisUtils;

    public List<CameraPojo> getCameraHostFromRedis() {
        List<CameraPojo> cameraPojoList;
        Object obj = redisUtils.get(Constant.CAMERA_HOST);
        if(obj == null) {
            obj = new LinkedList<CameraPojo>();
        }
        cameraPojoList = JSONArray.parseArray(JSONObject.toJSONString(obj), CameraPojo.class);
        return cameraPojoList;
    }
}
