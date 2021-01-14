package de.onvif.beans;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import java.io.Serializable;
import java.util.List;

public class CameraPojo implements Serializable {
	private static final long serialVersionUID = 8183688502930584159L;
	private String username;// 摄像头账号
	private String password;// 摄像头密码
	private String ip;// 摄像头ip
	private String channel;// 摄像头通道
	private String stream;// 摄像头码流
	private String rtsp;// rtsp地址
	private String rtmp;// rtmp地址
	private String url;// 播放地址
	private String starttime;// 回放开始时间
	private String endtime;// 回放结束时间
	private String opentime;// 打开时间
	private int count = 0;// 使用人数
	private String token;
	private String extraHost;
	private List<String> host;
	private String playHost;

	public String getPlayHost() {
		return playHost;
	}

	public void setPlayHost(String playHost) {
		this.playHost = playHost;
	}

	public List<String> getHost() {
		return host;
	}

	public void setHost(List<String> host) {
		this.host = host;
	}

	public void setEndtime(String endtime) {
		this.endtime = endtime;
	}

	public String getExtraHost() {
		return extraHost;
	}

	public void setExtraHost(String extraHost) {
		this.extraHost = extraHost;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getChannel() {
		return channel;
	}

	public void setChannel(String channel) {
		this.channel = channel;
	}

	public String getStream() {
		return stream;
	}

	public void setStream(String stream) {
		this.stream = stream;
	}

	public String getRtsp() {
		return rtsp;
	}

	public void setRtsp(String rtsp) {
		this.rtsp = rtsp;
	}

	public String getRtmp() {
		return rtmp;
	}

	public void setRtmp(String rtmp) {
		this.rtmp = rtmp;
	}

	public String getStarttime() {
		return starttime;
	}

	public void setStarttime(String starttime) {
		this.starttime = starttime;
	}

	public String getEndtime() {
		return endtime;
	}

	public void setEndTime(String endtime) {
		this.endtime = endtime;
	}

	public String getOpentime() {
		return opentime;
	}

	public void setOpentime(String opentime) {
		this.opentime = opentime;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@Override
	public boolean equals(Object obj){
		if(obj == null) return false;
		CameraPojo cameraPojo = JSONObject.parseObject(JSON.toJSONString(obj),CameraPojo.class);
		if(this.getIp().equals(cameraPojo.getIp())){
			return true;
		}
		return false;
	}

}
