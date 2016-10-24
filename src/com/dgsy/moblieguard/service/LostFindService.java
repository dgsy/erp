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
	 *���ŵĹ㲥������
	 */
	public class SmsReceiver extends BroadcastReceiver{

		@Override
		public void onReceive(Context context, Intent intent) {
			// TODO Auto-generated method stub
			//ʵ�ֶ������صĹ���
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
		//���Ź㲥������
		receiver = new SmsReceiver();
		IntentFilter filter=new IntentFilter("android.provider.Telephony.SMS_RECEIVED");
		filter.setPriority(Integer.MAX_VALUE);//����һ��,�嵥�ļ�,˭��ע��˭��ִ��,����һ�����뼶���
		
		//ע����ż���
		registerReceiver(receiver, filter);
		super.onCreate();
	}
	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		//ȡ��ע����ŵļ����㲥
		unregisterReceiver(receiver);
		super.onDestroy();
	}

}
