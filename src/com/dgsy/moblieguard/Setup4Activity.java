package com.dgsy.moblieguard;

import com.dgsy.moblieguard.service.LostFindService;
import com.dgsy.moblieguard.utils.MyConstants;
import com.dgsy.moblieguard.utils.ServiceUtils;
import com.dgsy.moblieguard.utils.SpTools;

import android.content.Intent;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;

/**
 * @author Administrator ��һ�������򵼽���
 */
public class Setup4Activity extends BaseSetupActivity {
	private CheckBox cb_isprotected;

	public void initView() {
		// TODO Auto-generated method stub
		setContentView(R.layout.activity_setup4);
		cb_isprotected = (CheckBox) findViewById(R.id.cb_setup4_isprotected);
	}

	/*
	 * (non-Javadoc) ��ʼ����ѡ���ֵ�������Ƿ���
	 * 
	 * @see com.dgsy.moblieguard.BaseSetupActivity#initData()
	 */
	@Override
	public void initData() {
		// ���������,��,���򲻴�
		if (ServiceUtils.isServiceRunning(getApplicationContext(),
				"com.dgsy.moblieguard.service.LostFindService")) {
			// ����������
			cb_isprotected.setChecked(true);
		} else {
			cb_isprotected.setChecked(false);
		}
		super.initData();
	}

	/*
	 * (non-Javadoc) ��ʼ����ѡ����¼�
	 * 
	 * @see com.dgsy.moblieguard.BaseSetupActivity#initEvent()
	 */
	@Override
	public void initEvent() {
		cb_isprotected
				.setOnCheckedChangeListener(new OnCheckedChangeListener() {

					@Override
					public void onCheckedChanged(CompoundButton buttonView,
							boolean isChecked) {
						// TODO Auto-generated method stub
						// ���ѡ���,�ǾͿ�����������,���������Ǹ�����
						if (isChecked) {
							// true,������������
							Intent service = new Intent(Setup4Activity.this,
									LostFindService.class);
							// �������������ķ���
							startService(service);
						} else {
							// �رշ�������

							Intent service = new Intent(Setup4Activity.this,
									LostFindService.class);
							// �رշ��������ķ���
							stopService(service);
						}
					}
				});
		super.initEvent();
	}

	@Override
	public void prevActivity() {
		// TODO Auto-generated method stub
		startActivity(Setup3Activity.class);
	}

	@Override
	public void nextActivity() {
		// TODO Auto-generated method stub
		// ����������ɵ�״̬
		SpTools.putBoolean(getApplicationContext(), MyConstants.ISSETUP, true);
		startActivity(LostFindActivity.class);
	}
}
