package com.dgsy.moblieguard;

import android.app.Activity;
import android.os.Bundle;

/**
 * @author Administrator
 *第一个设置向导界面
 */
public class Setup2Activity extends BaseSetupActivity {
public void initView() {
	// TODO Auto-generated method stub
	setContentView(R.layout.activity_setup2);
}

@Override
public void prevActivity() {
	// TODO Auto-generated method stub
	startActivity(Setup1Activity.class);
}

@Override
public void nextAnimation() {
	// TODO Auto-generated method stub
	
}

@Override
public void nextActivity() {
	// TODO Auto-generated method stub
	startActivity(Setup3Activity.class);
}
}
