package com.dgsy.moblieguard;

import com.dgsy.moblieguard.utils.MyConstants;
import com.dgsy.moblieguard.utils.SpTools;

import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

/**
 * @author Administrator 第一个设置向导界面
 */
public class Setup3Activity extends BaseSetupActivity {
	private EditText et_safeNumber;// 安全号码的编辑框

	public void initData() {

		et_safeNumber.setText(SpTools.getString(getApplicationContext(),
				MyConstants.SAFENUMBER, "110"));
		super.initData();
	}

	public void initView() {
		// TODO Auto-generated method stub
		setContentView(R.layout.activity_setup3);
		et_safeNumber = (EditText) findViewById(R.id.et_setup3_safenumber);
	}
	/**
	 * 从手机联系人里获取安全号码
	 * @param v
	 */
	public void selectSafeNumber(View v){
		//弹出新的Activity来显示所有好友信息
		Intent friends = new Intent(this,FriendsActivity.class);
		startActivityForResult(friends,1);//启动显示好友界面
		//
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		if (data!=null) {
			String phone = data.getStringExtra(MyConstants.SAFENUMBER);
			//显示安全号码
			et_safeNumber.setText(phone);
		}
		super.onActivityResult(requestCode, resultCode, data);
	}
	/* (non-Javadoc)
	 * 覆盖了父类的方法
	 * @see com.dgsy.moblieguard.BaseSetupActivity#next(android.view.View)
	 */
	@Override
	public void next(View v) {
		//保存安全号码
		
				//获取安全号码
				String safeNumber = et_safeNumber.getText().toString().trim();
				
				//如果安全号码，下一步不进行页面的跳转
				if (TextUtils.isEmpty(safeNumber)){
					//为空
					Toast.makeText(getApplicationContext(), "安全号码不能为空", 1).show();
					//不调用父类的功能来进行页面的切换
					return;
				} else {
					//保存安全号码
					SpTools.putString(getApplicationContext(), MyConstants.SAFENUMBER, safeNumber);
				}
				
				//调用父类功能完成页面的切换
				super.next(v);
	}

	@Override
	public void prevActivity() {
		// TODO Auto-generated method stub
		startActivity(Setup2Activity.class);
	}

	@Override
	public void nextActivity() {
		// TODO Auto-generated method stub
		startActivity(Setup4Activity.class);
	}
}
