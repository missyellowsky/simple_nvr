package de.onvif.utils;

import java.net.URL;
import java.util.concurrent.*;

public class CommenUtils {
	private static int corePoolSize=10;
	private static int maximumPoolSize=10;
	private static long keepAliveTime=60*30L;
	private static TimeUnit unit=TimeUnit.SECONDS;
	private static BlockingQueue<Runnable> workQueue=new ArrayBlockingQueue<Runnable>(1000);
	private static ExecutorService confact=
			new ThreadPoolExecutor(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue);
	public static ExecutorService getConPool() {
		return confact;
	}


	public static String getSftpInfo(URL url) {
		String userInfo = url.getUserInfo();
		System.err.println("用户信息：" + userInfo);
		if (userInfo != null) {
			int index = userInfo.indexOf(":");
			String userName = userInfo.substring(0, index);
			System.err.println("用户名：" + userName);
			String password = userInfo.substring(index + 1);
			System.err.println("密码：" + password);
		}
		int port = url.getPort();
		System.err.println("端口号：" + port);
		String host = url.getHost();
		System.err.println("host：" + host);
		String path = url.getPath();
		System.err.println("远程路径：" + path);
		return host;
	}

	public static String generateCameraId(String cameraIp, String netIp){
		final int secret = 210558;
		byte[] cameraIpB = cameraIp.getBytes();
		byte[] netIpB = netIp.getBytes();
		for(int i=0;i<cameraIpB.length;i++)
		{
			cameraIpB[i]=(byte)(cameraIpB[i]^secret); //通过异或运算进行加密
		}
		for(int i=0;i<netIpB.length;i++)
		{
			netIpB[i]=(byte)(netIpB[i]^secret); //通过异或运算进行加密
		}
		cameraIp = new String(cameraIpB,0,cameraIpB.length);
		netIp = new String(netIpB,0,netIpB.length);
		return cameraIp+"#"+netIp;
	}
}
