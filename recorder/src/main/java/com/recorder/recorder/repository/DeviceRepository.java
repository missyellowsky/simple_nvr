package com.recorder.recorder.repository;

import de.onvif.beans.CameraPojo;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface DeviceRepository {
    CameraPojo getCameraByIp(String ip);
}
