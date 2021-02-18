package de.onvif.service;

import de.onvif.beans.CameraPojo;
import de.onvif.beans.DeviceInfo;
import de.onvif.beans.HostRelations;
import de.onvif.beans.Result;

import java.util.List;

public interface DeviceService {
    void addCameraServer(CameraPojo cameraPojo);

    List<CameraPojo> getCameraHost();

    List<DeviceInfo> queryCameras();

    void refreshCameras(List<DeviceInfo> onvifDevices);

    void setHostMapping(HostRelations hostRelationss);

    List<HostRelations> getHostMapping();

    void editHostMapping(HostRelations hostRelationss);

    void deleteHostMapping(HostRelations hostRelationss);

    CameraPojo getCameraByIp(String ip);

    void editCameraServer(CameraPojo cameraPojo);

    Result queryRecordList(String date, String ip);
}
