package com.recorder.recorder.service;

import com.recorder.recorder.beans.RecordParam;
import com.recorder.recorder.beans.Result;

public interface FFmpegService {

    Result playRecord(RecordParam recordParam);

    Result compensateFirstFlv(String path, long current);

    Result queryRecordList(String date, String ip);

    Boolean dropStream(String name);
}
