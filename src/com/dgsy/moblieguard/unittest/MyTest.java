package com.dgsy.moblieguard.unittest;

import com.dgsy.moblieguard.engine.ReadContantsEngine;
import com.dgsy.moblieguard.utils.ServiceUtils;

import android.test.AndroidTestCase;

public class MyTest extends AndroidTestCase {
	public void testReadContants() {
		ReadContantsEngine.readContants(getContext());//获取虚拟的上下文
	}
	public void testRunningService(){
		ServiceUtils.isServiceRunning(getContext(), "");
		
	}
}
