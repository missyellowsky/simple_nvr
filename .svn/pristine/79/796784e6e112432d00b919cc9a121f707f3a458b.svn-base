package de.onvif.utils.componentUtils;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import de.onvif.beans.CameraPojo;
import de.onvif.beans.HostRelations;
import de.onvif.beans.constant.Constant;
import de.onvif.utils.RedisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.List;

@Component
public class RedisDataUtil {

    @Autowired
    private RedisUtils redisUtils;

    public List<HostRelations> getHostRelationsListFromRedis() {
        List<HostRelations> hostRelationss;
        Object obj = redisUtils.get(Constant.HOST_MAPPING);
        if (obj == null) {
            obj = new LinkedList<HostRelations>();
        }
        hostRelationss = JSONArray.parseArray(JSONObject.toJSONString(obj), HostRelations.class);
        return hostRelationss;
    }

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
