package com.dgsy.moblieguard.utils;

import java.util.List;

import android.app.ActivityManager;
import android.app.ActivityManager.RunningServiceInfo;
import android.content.Context;

/**
 * @author Administrator �жϷ����״̬
 */
public class ServiceUtils {
	/**
	 * @param context
	 * @param serviceName
	 * ����������,����������
	 * @return
	 * ��service�Ƿ�������
	 */
	public static boolean isServiceRunning(Context context, String serviceName){
		boolean isRunning=false;
		//�ж������еķ���״̬,activityManager
		ActivityManager am=(ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
		List<RunningServiceInfo> runningServices	 = am.getRunningServices(50);
		for (RunningServiceInfo runningServiceInfo:runningServices) {
		//System.out.println(runningServiceInfo.service.getClassName());	
		//�жϷ���������Ƿ����ָ���ķ�����
			if (runningServiceInfo.service.getClassName().equals(serviceName)) {
				isRunning=true;
				//�Ѿ��ҵ�����,�˳�ѭ��
				break;
			}
		}
		return isRunning;
	}
}
