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
 *���������Ĺ㲥
 */
public class BootReceiver extends BroadcastReceiver{

	@Override
	public void onReceive(Context context, Intent intent) {
		// �ֻ�������ɣ����SIM���Ƿ�仯
		//ȡ��ԭ�������SIM����Ϣ
		String oldsim = SpTools.getString(context, MyConstants.SIM, null);
		//��ȡ��ǰ�ֻ���SIM����Ϣ
		TelephonyManager tm=(TelephonyManager) context.getSystemService(context.TELEPHONY_SERVICE);
		String simSerialNumber = tm.getSimSerialNumber();
		//�ж��Ƿ�仯
		if (!oldsim.equals(simSerialNumber)) {
			//sim���仯,���ͱ������� 
			//ȡ����ȫ����
			String safeNumber=SpTools.getString(context,MyConstants.SAFENUMBER, null);
			//���Ͷ��Ű�ȫ����
			SmsManager sm=SmsManager.getDefault();
			sm.sendTextMessage(safeNumber, "", "����С͵", null, null);
		}
	}

}
