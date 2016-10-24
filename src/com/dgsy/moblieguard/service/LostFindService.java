package com.dgsy.moblieguard.service;

import java.util.Iterator;

import android.R.integer;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.IBinder;
import android.telephony.SmsMessage;


public class LostFindService extends Service {

	private SmsReceiver receiver;
	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return null;
	}
	/**
	 * @author Administrator
	 *短信的广播接受者
	 */
	public class SmsReceiver extends BroadcastReceiver{

		@Override
		public void onReceive(Context context, Intent intent) {
			// TODO Auto-generated method stub
			//实现短信拦截的功能
			Bundle extras = intent.getExtras();
		Object datas[]=	(Object[]) extras.get("pdus");
		for (Object data:datas) {
			SmsMessage sm=SmsMessage.createFromPdu((byte[]) data);
			System.out.println(sm.getMessageBody()+":"+ sm.getOriginatingAddress());
		}
		}
		
	}
	@Override
	public void onCreate() {
		//短信广播接收者
		receiver = new SmsReceiver();
		IntentFilter filter=new IntentFilter("android.provider.Telephony.SMS_RECEIVED");
		filter.setPriority(Integer.MAX_VALUE);//级别一样,清单文件,谁先注册谁先执行,级别一样代码级别高
		
		//注册短信监听
		registerReceiver(receiver, filter);
		super.onCreate();
	}
	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		//取消注册短信的监听广播
		unregisterReceiver(receiver);
		super.onDestroy();
	}

}
