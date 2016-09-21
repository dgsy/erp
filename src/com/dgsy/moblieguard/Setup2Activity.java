package com.dgsy.moblieguard;

import com.dgsy.moblieguard.utils.MyConstants;
import com.dgsy.moblieguard.utils.SpTools;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

/**
 * @author Administrator 第一个设置向导界面
 */
public class Setup2Activity extends BaseSetupActivity {
	private Button bt_bind;
	private ImageView iv_isBind;

	public void initView() {
		// TODO Auto-generated method stub
		setContentView(R.layout.activity_setup2);
		// 获取bind sim卡按钮
		bt_bind = (Button) findViewById(R.id.bt_setup2_bindsim);
		// 是否绑定sim卡的图标
		iv_isBind = (ImageView) findViewById(R.id.iv_setup2_isbind);
		if (TextUtils.isEmpty(SpTools.getString(getApplicationContext(),
				MyConstants.SIM, ""))) {
			// 未绑定
			{
				// 切换是否绑定sim卡的图标
				iv_isBind.setImageResource(R.drawable.unlock);// 设置未加锁的图片
			}
		} else {
			{
				// 切换是否绑定sim卡的图标
				iv_isBind.setImageResource(R.drawable.lock);// 设置加锁的图片
			}
		}

	}

	@Override
	public void initEvent() {
		// TODO Auto-generated method stub
		bt_bind.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// 绑定和解绑
				if (TextUtils.isEmpty(SpTools.getString(
						getApplicationContext(), MyConstants.SIM, ""))) {
					// 没有绑定sim卡
					{

						// /绑定sim的业务
						// 绑定sim卡,存储sim卡信息
						// 获取sim卡信息
						TelephonyManager tm = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
						// sim卡信息
						String simSerialNumber = tm.getSimSerialNumber();
						// 保存sim卡信息,保存到sp中
						SpTools.putString(getApplicationContext(),
								MyConstants.SIM, simSerialNumber);
					}

					{
						// 切换是否绑定sim卡的图标
						iv_isBind.setImageResource(R.drawable.lock);// 设置加锁的图片
					}
				} else {
					// 已经绑定sim卡,解绑sim卡，就是保存空值
					SpTools.putString(getApplicationContext(), MyConstants.SIM,
							"");
					{
						// 切换是否绑定sim卡的图标
						iv_isBind.setImageResource(R.drawable.unlock);// 设置未加锁的图片
					}
				}

			}

		});
		super.initEvent();
	}
	@Override
	public void next(View v) {
		if (TextUtils.isEmpty(SpTools.getString(getApplicationContext(), MyConstants.SIM, ""))){
			//没有绑定sim
			Toast.makeText(getApplicationContext(), "请先绑定sim卡", 1).show();
			return;
		}
		super.next(v);//调用父类的功能
	}
	@Override
	public void prevActivity() {
		// TODO Auto-generated method stub
		startActivity(Setup1Activity.class);
	}

	@Override
	public void nextActivity() {
		// TODO Auto-generated method stub
		startActivity(Setup3Activity.class);
	}
}
