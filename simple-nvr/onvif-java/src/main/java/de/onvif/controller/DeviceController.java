package de.onvif.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import de.onvif.beans.CameraPojo;
import de.onvif.beans.DeviceInfo;
import de.onvif.beans.HostRelations;
import de.onvif.beans.Result;
import de.onvif.beans.constant.Constant;
import de.onvif.soap.OnvifDevice;
import de.onvif.utils.RedisUtils;
import de.onvif.utils.componentUtils.RedisDataUtil;
import de.onvif.utils.init.InitCameras;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.xml.soap.SOAPException;
import java.net.ConnectException;
import java.util.LinkedList;
import java.util.List;


@RestController
@Slf4j
public class DeviceController {

    @Autowired
    private RedisUtils redisUtils;

    @Autowired
    private RedisDataUtil redisDataUtil;

    @Value("${camera.username}")
    private String userName;

    @Value("${camera.password}")
    private String password;

    @Value("${camera.autopush}")
    private String autoPush;

    @Autowired
    InitCameras initCameras;

    @RequestMapping(value = "device/refreshCamera", method = RequestMethod.GET)
    public Result refreshCamera() {
        List<DeviceInfo> onvifDevices = null;
        try {
            onvifDevices = initCameras.initCamera();
        } catch (SOAPException e) {
            log.error("SOAPException: " + e.getMessage());
            Result.fail(e.getMessage());
        } catch (ConnectException e) {
            log.error("ConnectException: " + e.getMessage());
            Result.fail(e.getMessage());
        }
        return Result.success(onvifDevices);
    }

    @RequestMapping(value = "device/getCameras", method = RequestMethod.GET)
    public Result queryCameras() {
        Object obj = redisUtils.get(Constant.DEVICE_CACHE);
        if (obj == null) {
            obj = new LinkedList<DeviceInfo>();
        }
        List<DeviceInfo> onvifDevices = JSONArray.parseArray(JSONObject.toJSONString(obj), DeviceInfo.class);
        return Result.success(onvifDevices);
    }

    @RequestMapping(value = "device/setCameraServer", method = RequestMethod.POST)
    public Result setCameraServer(@RequestBody CameraPojo cameraPojo) {
        List<CameraPojo> cameraPojoList = redisDataUtil.getCameraHostFromRedis();
        if (cameraPojo == null || StringUtils.isEmpty(cameraPojo.getIp()) || StringUtils.isEmpty(cameraPojo.getPlayHost()) || CollectionUtils.isEmpty(cameraPojo.getHost())) {
            return Result.fail("Camera and host information are incomplete!");
        }
        if (cameraPojoList.contains(cameraPojo)) {
            cameraPojoList.remove(cameraPojo);
        }
        cameraPojoList.add(cameraPojo);
        redisUtils.set(Constant.CAMERA_HOST, cameraPojoList);
        return Result.success();
    }

    @RequestMapping(value = "device/getCameraServer", method = RequestMethod.GET)
    public Result getCameraServer() {
        return Result.success(redisDataUtil.getCameraHostFromRedis());
    }

    @RequestMapping(value = "device/setHostMapping", method = RequestMethod.POST)
    public Result setHostMapping(@RequestBody HostRelations hostRelations) {
        if (hostRelations != null && !StringUtils.isEmpty(hostRelations.getPlayHost())) {
            List<HostRelations> hostRelationss = redisDataUtil.getHostRelationsListFromRedis();
            if (hostRelationss.contains(hostRelations)) {
                hostRelationss.remove(hostRelations);
            }
            hostRelationss.add(hostRelations);
            redisUtils.set(Constant.HOST_MAPPING, hostRelationss);
        } else {
            return Result.fail("hostRelations is null");
        }
        return Result.success();
    }

    @RequestMapping(value = "device/getHostMapping", method = RequestMethod.GET)
    public Result getHostMapping() {
        List<HostRelations> hostRelationss = redisDataUtil.getHostRelationsListFromRedis();
        return Result.success(hostRelationss);
    }

    @RequestMapping(value = "device/editHostMapping", method = RequestMethod.POST)
    public Result editHostMapping(@RequestBody HostRelations hostRelations) {
        if (hostRelations != null && !StringUtils.isEmpty(hostRelations.getPlayHost())) {
            List<HostRelations> hostRelationss = redisDataUtil.getHostRelationsListFromRedis();
            if (!hostRelationss.contains(hostRelations)) {
                return Result.fail("playHost not exist! please refresh the page and try again!");
            }
            hostRelationss.remove(hostRelations);
            hostRelationss.add(hostRelations);
            redisUtils.set(Constant.HOST_MAPPING, hostRelationss);
        } else {
            return Result.fail("hostRelations is null");
        }
        return Result.success();
    }

    @RequestMapping(value = "device/deleteHostMapping", method = RequestMethod.POST)
    public Result deleteHostMapping(@RequestBody HostRelations hostRelations) {
        if (hostRelations != null && !StringUtils.isEmpty(hostRelations.getPlayHost())) {
            List<HostRelations> hostRelationss = redisDataUtil.getHostRelationsListFromRedis();
            if (!hostRelationss.contains(hostRelations)) {
                return Result.fail("playHost not exist! please refresh the page and try again!");
            }
            hostRelationss.remove(hostRelations);
            redisUtils.set(Constant.HOST_MAPPING, hostRelationss);
        } else {
            return Result.fail("hostRelations is null");
        }
        return Result.success();
    }


}
