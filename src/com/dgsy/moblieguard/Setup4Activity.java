package com.dgsy.moblieguard;


/**
 * @author Administrator
 *��һ�������򵼽���
 */
public class Setup4Activity extends BaseSetupActivity {
	public void initView() {
		// TODO Auto-generated method stub
		setContentView(R.layout.activity_setup4);
	}

	@Override
	public void prevActivity() {
		// TODO Auto-generated method stub
		startActivity(Setup3Activity.class);
	}

	

	@Override
	public void nextActivity() {
		// TODO Auto-generated method stub
		startActivity(LostFindActivity.class);
	}
}
