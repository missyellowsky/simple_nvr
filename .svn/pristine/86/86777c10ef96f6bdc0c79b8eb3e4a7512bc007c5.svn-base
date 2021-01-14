package de.onvif.controller;

import de.onvif.beans.CameraPojo;
import de.onvif.beans.HostRelations;
import de.onvif.beans.Result;
import de.onvif.beans.constant.Constant;
import de.onvif.discovery.OnvifDiscovery;
import de.onvif.utils.RedisUtils;
import de.onvif.utils.componentUtils.RedisDataUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import java.net.URL;
import java.util.Collection;
import java.util.List;

@RestController
public class DeviceController {

    @Autowired
    private RedisUtils redisUtils;

    @Autowired
    private RedisDataUtil redisDataUtil;

    @RequestMapping(value = "device/queryCameras", method = RequestMethod.GET)
    public Collection<URL> queryCameras() {
        Collection<URL> urls = OnvifDiscovery.discoverOnvifURLs();
        return urls;
    }

    @RequestMapping(value = "device/setCameraServer", method = RequestMethod.POST)
    public Result setCameraServer(@RequestBody CameraPojo cameraPojo) {
        List<CameraPojo> cameraPojoList = redisDataUtil.getCameraHostFromRedis();
        if (cameraPojo == null || StringUtils.isEmpty(cameraPojo.getIp()) || StringUtils.isEmpty(cameraPojo.getPlayHost()) || CollectionUtils.isEmpty(cameraPojo.getHost())) {
            return Result.fail("Camera and host information are incomplete!");
        }
        if(cameraPojoList.contains(cameraPojo)){
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
