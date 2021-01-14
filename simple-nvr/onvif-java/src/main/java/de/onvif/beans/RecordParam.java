package de.onvif.beans;

import lombok.Data;

@Data
public class RecordParam {
    private String startTime;
    private String endTime;
    private String cameraIp;
    private String streamType;

    public RecordParam() {
    }

    public RecordParam(StreamParam streamParam) {
        this.startTime = streamParam.getStartTime();
        this.endTime = streamParam.getEndTime();
        this.cameraIp = streamParam.getIp();
        this.streamType = streamParam.getStreamType();
    }
}
