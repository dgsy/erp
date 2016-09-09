package com.dgsy.moblieguard;

import com.dgsy.moblieguard.utils.MyConstants;
import com.dgsy.moblieguard.utils.SpTools;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class LostFindActivity extends Activity {
@Override
protected void onCreate(Bundle savedInstanceState) {
	// TODO Auto-generated method stub
	super.onCreate(savedInstanceState);
	if (SpTools.getBoolean(getApplicationContext(), MyConstants.ISSETUP, false)) {
		//����������򵼽���
		initView();//�ֻ���������
	}else {
		//Ҫ����������򵼽���,ֱ����ʾ������
		Intent intent=new Intent(this,Setup1Activity.class);
		startActivity(intent);
		finish();//�ر��Լ�
	}
	
}

private void initView() {
	// TODO Auto-generated method stub
	setContentView(R.layout.activity_lostfind);
}
}
