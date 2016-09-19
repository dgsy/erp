package com.dgsy.moblieguard;


/**
 * @author Administrator
 *第一个设置向导界面
 */
public class Setup1Activity extends BaseSetupActivity{
@Override
public void initView() {
	// TODO Auto-generated method stub
	setContentView(R.layout.activity_setup1);
}

@Override
public void prevActivity() {
	// TODO Auto-generated method stub
	
}



@Override
public void nextActivity() {
	// TODO Auto-generated method stub
	//跳转到第二个设置向导界面
	startActivity(Setup2Activity.class);
}
}
