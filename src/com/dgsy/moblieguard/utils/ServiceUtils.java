package com.dgsy.moblieguard.utils;

import java.util.List;

import android.app.ActivityManager;
import android.app.ActivityManager.RunningServiceInfo;
import android.content.Context;

/**
 * @author Administrator 判断服务的状态
 */
public class ServiceUtils {
	/**
	 * @param context
	 * @param serviceName
	 * 完整的名字,包名加类名
	 * @return
	 * 该service是否在运行
	 */
	public static boolean isServiceRunning(Context context, String serviceName){
		boolean isRunning=false;
		//判断运行中的服务状态,activityManager
		ActivityManager am=(ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
		List<RunningServiceInfo> runningServices	 = am.getRunningServices(50);
		for (RunningServiceInfo runningServiceInfo:runningServices) {
		//System.out.println(runningServiceInfo.service.getClassName());	
		//判断服务的名称是否包含指定的服务名
			if (runningServiceInfo.service.getClassName().equals(serviceName)) {
				isRunning=true;
				//已经找到服务,退出循环
				break;
			}
		}
		return isRunning;
	}
}
