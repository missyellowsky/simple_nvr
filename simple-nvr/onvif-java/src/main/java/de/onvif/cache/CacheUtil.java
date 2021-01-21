package de.onvif.cache;


import de.onvif.beans.CameraPojo;
import de.onvif.push.CameraPush;
import de.onvif.push.CameraPushWithWatermark;
import de.onvif.soap.OnvifDevice;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Title CacheUtil.java
 * @description 推流缓存信息
 * @time 2019年12月17日 下午3:12:45
 * @author wuguodong
 **/
public final class CacheUtil {
	/*
	 * 保存已经开始推的流
	 */
	public static Map<String, CameraPojo> STREATMAP = new ConcurrentHashMap<String, CameraPojo>();

	/*
	 * 保存push
	 */
	public static Map<String, CameraPushWithWatermark> PUSHMAP = new ConcurrentHashMap<>();

	/*
	 * 保存push
	 */
	public static Map<String, CameraPush> PUSHMAPANOTHER = new ConcurrentHashMap<>();

	public static Map<String, Object> IMAGEJOB = new ConcurrentHashMap<>();

	/**
	 * 保存摄像机数据
	 */
	public static Map<String, OnvifDevice> CAMERAMAP = new ConcurrentHashMap<>();
	/*
	 * 保存服务启动时间
	 */
	public static long STARTTIME;

}
