package com.recorder.recorder.service;

import com.recorder.recorder.beans.RecordParam;
import com.recorder.recorder.beans.Result;

public interface FFmpegService {
    Result playRecord(RecordParam recordParam);
}
