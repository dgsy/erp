package com.dgsy.moblieguard;

import com.dgsy.moblieguard.utils.MyConstants;
import com.dgsy.moblieguard.utils.SpTools;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telephony.SmsManager;
import android.telephony.TelephonyManager;


/**
 * @author Administrator
 *开机启动的广播
 */
public class BootReceiver extends BroadcastReceiver{

	@Override
	public void onReceive(Context context, Intent intent) {
		// 手机启动完成，检测SIM卡是否变化
		//取出原来保存的SIM卡信息
		String oldsim = SpTools.getString(context, MyConstants.SIM, null);
		//获取当前手机的SIM卡信息
		TelephonyManager tm=(TelephonyManager) context.getSystemService(context.TELEPHONY_SERVICE);
		String simSerialNumber = tm.getSimSerialNumber();
		//判断是否变化
		if (!oldsim.equals(simSerialNumber)) {
			//sim卡变化,发送报警短信 
			//取出安全号码
			String safeNumber=SpTools.getString(context,MyConstants.SAFENUMBER, null);
			//发送短信安全号码
			SmsManager sm=SmsManager.getDefault();
			sm.sendTextMessage(safeNumber, "", "我是小偷", null, null);
		}
	}

}
