package com.dgsy.moblieguard.unittest;

import com.dgsy.moblieguard.engine.ReadContantsEngine;
import com.dgsy.moblieguard.utils.ServiceUtils;

import android.test.AndroidTestCase;

public class MyTest extends AndroidTestCase {
	public void testReadContants() {
		ReadContantsEngine.readContants(getContext());//��ȡ�����������
	}
	public void testRunningService(){
		ServiceUtils.isServiceRunning(getContext(), "");
		
	}
}
