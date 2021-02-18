package de.onvif.repository;

import de.onvif.beans.CameraPojo;
import de.onvif.beans.DeviceInfo;
import de.onvif.beans.HostRelations;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
public interface DeviceRepository {

    void addDeviceHost(CameraPojo cameraPojo);

    List<DeviceInfo> queryCameras();

    void deleteCameras();

    void addDevices(List<DeviceInfo> onvifDevices);

    void setHostMapping(HostRelations hostRelationss);

    List<HostRelations> getHostMapping();

    void editHostMapping(HostRelations hostRelationss);

    void deleteHostMapping(HostRelations hostRelationss);

   CameraPojo getCameraByIp(String ip);

    void deleteDeviceHost(CameraPojo cameraPojo);
}
