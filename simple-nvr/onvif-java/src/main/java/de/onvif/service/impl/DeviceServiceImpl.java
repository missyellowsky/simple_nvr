package de.onvif.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import de.onvif.beans.*;
import de.onvif.repository.DeviceRepository;
import de.onvif.service.DeviceService;
import de.onvif.utils.HttpClient;
import de.onvif.utils.RSAEncrypt;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static de.onvif.beans.constant.Constant.FLV;
import static de.onvif.beans.constant.Constant.HLS;

@Service
@EnableTransactionManagement
public class DeviceServiceImpl implements DeviceService {

    @Autowired
    public Config config;// 配置文件bean

    @Autowired
    DeviceRepository deviceRepository;


    @Override
    public void addCameraServer(CameraPojo cameraPojo) {
        deviceRepository.addDeviceHost(cameraPojo);
    }

    @Override
    public List<CameraPojo> getCameraHost() {
        //暂时无用
        return null;
    }

    @Override
    public List<DeviceInfo> queryCameras() {
        List<DeviceInfo> deviceInfos = deviceRepository.queryCameras();
        return deviceInfos;
    }

    @Override
    public void refreshCameras(List<DeviceInfo> onvifDevices) {
        deviceRepository.deleteCameras();
        deviceRepository.addDevices(onvifDevices);
    }

    @Override
    public void setHostMapping(HostRelations hostRelationss) {
        deviceRepository.setHostMapping(hostRelationss);
    }

    @Override
    public List<HostRelations> getHostMapping() {
        List<HostRelations> hostRelations = deviceRepository.getHostMapping();
        return hostRelations;
    }

    @Override
    public void editHostMapping(HostRelations hostRelationss) {
        deviceRepository.deleteHostMapping(hostRelationss);
        deviceRepository.setHostMapping(hostRelationss);
    }

    @Override
    public void deleteHostMapping(HostRelations hostRelationss) {
        deviceRepository.deleteHostMapping(hostRelationss);
    }

    @Override
    public CameraPojo getCameraByIp(String ip) {
        CameraPojo cameraPojo = deviceRepository.getCameraByIp(ip);
        return cameraPojo;
    }

    @Override
    public void editCameraServer(CameraPojo cameraPojo) {
        deviceRepository.deleteDeviceHost(cameraPojo);
        deviceRepository.addDeviceHost(cameraPojo);
    }

    @Override
    public Result queryRecordList(String date, String ip) {
        CameraPojo cameraPojoFromRedis = getCameraByIp(ip);
        if (cameraPojoFromRedis == null) {
            return Result.fail("no camera info");
        }
        Map<String, Object> map = new HashMap<>();
        for (int i = 0 ; i < cameraPojoFromRedis.getHost().size(); i++){
            String host = cameraPojoFromRedis.getHost().get(i);
            String url = "http://" + host + ":" + config.getRecorder_port() + "/record/queryRecordList?date=" + date + "&ip=" + ip;
            HttpClient httpClient = HttpClient.getInstance();
            JSONObject jsonObject = new JSONObject();
            String resp = httpClient.getMethodReuqest(url, JSONObject.toJSONString(jsonObject));
            if (StringUtils.isEmpty(resp)) {
               continue;
            }
            Result result = JSON.parseObject(resp, Result.class);
            map.put(host,result.getObj());
        }
        return Result.success(map);
    }
}
