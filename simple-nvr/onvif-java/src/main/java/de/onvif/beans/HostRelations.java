package de.onvif.beans;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class HostRelations implements Serializable {
    private String playHost;
    private List<String> host;

    @Override
    public boolean equals(Object obj){
        if (obj == null){
            return false;
        }
        HostRelations hostRelations = JSONObject.parseObject(JSON.toJSONString(obj),HostRelations.class);
        if(playHost.equals(hostRelations.playHost)) return true;
        return false;
    }
}
