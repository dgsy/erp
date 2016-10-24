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
 * @author Administrator 第一个设置向导界面
 */
public class Setup4Activity extends BaseSetupActivity {
	private CheckBox cb_isprotected;

	public void initView() {
		// TODO Auto-generated method stub
		setContentView(R.layout.activity_setup4);
		cb_isprotected = (CheckBox) findViewById(R.id.cb_setup4_isprotected);
	}

	/*
	 * (non-Javadoc) 初始化复选框的值看服务是否开启
	 * 
	 * @see com.dgsy.moblieguard.BaseSetupActivity#initData()
	 */
	@Override
	public void initData() {
		// 如果服务开启,打勾,否则不打勾
		if (ServiceUtils.isServiceRunning(getApplicationContext(),
				"com.dgsy.moblieguard.service.LostFindService")) {
			// 服务在运行
			cb_isprotected.setChecked(true);
		} else {
			cb_isprotected.setChecked(false);
		}
		super.initData();
	}

	/*
	 * (non-Javadoc) 初始化复选框的事件
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
						// 如果选择打勾,那就开启防盗保护,防盗保护是个服务
						if (isChecked) {
							// true,开启防盗保护
							Intent service = new Intent(Setup4Activity.this,
									LostFindService.class);
							// 启动防盗防护的服务
							startService(service);
						} else {
							// 关闭防盗保护

							Intent service = new Intent(Setup4Activity.this,
									LostFindService.class);
							// 关闭防盗防护的服务
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
		// 保存设置完成的状态
		SpTools.putBoolean(getApplicationContext(), MyConstants.ISSETUP, true);
		startActivity(LostFindActivity.class);
	}
}
