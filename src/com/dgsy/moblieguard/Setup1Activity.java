package com.dgsy.moblieguard;


/**
 * @author Administrator
 *��һ�������򵼽���
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
	//��ת���ڶ��������򵼽���
	startActivity(Setup2Activity.class);
}
}
