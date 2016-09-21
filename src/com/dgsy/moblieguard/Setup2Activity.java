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
 * @author Administrator ��һ�������򵼽���
 */
public class Setup2Activity extends BaseSetupActivity {
	private Button bt_bind;
	private ImageView iv_isBind;

	public void initView() {
		// TODO Auto-generated method stub
		setContentView(R.layout.activity_setup2);
		// ��ȡbind sim����ť
		bt_bind = (Button) findViewById(R.id.bt_setup2_bindsim);
		// �Ƿ��sim����ͼ��
		iv_isBind = (ImageView) findViewById(R.id.iv_setup2_isbind);
		if (TextUtils.isEmpty(SpTools.getString(getApplicationContext(),
				MyConstants.SIM, ""))) {
			// δ��
			{
				// �л��Ƿ��sim����ͼ��
				iv_isBind.setImageResource(R.drawable.unlock);// ����δ������ͼƬ
			}
		} else {
			{
				// �л��Ƿ��sim����ͼ��
				iv_isBind.setImageResource(R.drawable.lock);// ���ü�����ͼƬ
			}
		}

	}

	@Override
	public void initEvent() {
		// TODO Auto-generated method stub
		bt_bind.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// �󶨺ͽ��
				if (TextUtils.isEmpty(SpTools.getString(
						getApplicationContext(), MyConstants.SIM, ""))) {
					// û�а�sim��
					{

						// /��sim��ҵ��
						// ��sim��,�洢sim����Ϣ
						// ��ȡsim����Ϣ
						TelephonyManager tm = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
						// sim����Ϣ
						String simSerialNumber = tm.getSimSerialNumber();
						// ����sim����Ϣ,���浽sp��
						SpTools.putString(getApplicationContext(),
								MyConstants.SIM, simSerialNumber);
					}

					{
						// �л��Ƿ��sim����ͼ��
						iv_isBind.setImageResource(R.drawable.lock);// ���ü�����ͼƬ
					}
				} else {
					// �Ѿ���sim��,���sim�������Ǳ����ֵ
					SpTools.putString(getApplicationContext(), MyConstants.SIM,
							"");
					{
						// �л��Ƿ��sim����ͼ��
						iv_isBind.setImageResource(R.drawable.unlock);// ����δ������ͼƬ
					}
				}

			}

		});
		super.initEvent();
	}
	@Override
	public void next(View v) {
		if (TextUtils.isEmpty(SpTools.getString(getApplicationContext(), MyConstants.SIM, ""))){
			//û�а�sim
			Toast.makeText(getApplicationContext(), "���Ȱ�sim��", 1).show();
			return;
		}
		super.next(v);//���ø���Ĺ���
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
