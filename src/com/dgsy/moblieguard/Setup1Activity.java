package com.dgsy.moblieguard;

import android.app.Activity;
import android.os.Bundle;

/**
 * @author Administrator
 *第一个设置向导界面
 */
public class Setup1Activity extends Activity {
@Override
protected void onCreate(Bundle savedInstanceState) {
	// TODO Auto-generated method stub
	super.onCreate(savedInstanceState);
	initView();
	
}

private void initView() {
	// TODO Auto-generated method stub
	setContentView(R.layout.activity_setup1);
}

}
