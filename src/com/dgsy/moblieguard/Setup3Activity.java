package com.dgsy.moblieguard;

import com.dgsy.moblieguard.utils.MyConstants;
import com.dgsy.moblieguard.utils.SpTools;

import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

/**
 * @author Administrator ��һ�������򵼽���
 */
public class Setup3Activity extends BaseSetupActivity {
	private EditText et_safeNumber;// ��ȫ����ı༭��

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
	 * ���ֻ���ϵ�����ȡ��ȫ����
	 * @param v
	 */
	public void selectSafeNumber(View v){
		//�����µ�Activity����ʾ���к�����Ϣ
		Intent friends = new Intent(this,FriendsActivity.class);
		startActivityForResult(friends,1);//������ʾ���ѽ���
		//
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		if (data!=null) {
			String phone = data.getStringExtra(MyConstants.SAFENUMBER);
			//��ʾ��ȫ����
			et_safeNumber.setText(phone);
		}
		super.onActivityResult(requestCode, resultCode, data);
	}
	/* (non-Javadoc)
	 * �����˸���ķ���
	 * @see com.dgsy.moblieguard.BaseSetupActivity#next(android.view.View)
	 */
	@Override
	public void next(View v) {
		//���氲ȫ����
		
				//��ȡ��ȫ����
				String safeNumber = et_safeNumber.getText().toString().trim();
				
				//�����ȫ���룬��һ��������ҳ�����ת
				if (TextUtils.isEmpty(safeNumber)){
					//Ϊ��
					Toast.makeText(getApplicationContext(), "��ȫ���벻��Ϊ��", 1).show();
					//�����ø���Ĺ���������ҳ����л�
					return;
				} else {
					//���氲ȫ����
					SpTools.putString(getApplicationContext(), MyConstants.SAFENUMBER, safeNumber);
				}
				
				//���ø��๦�����ҳ����л�
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
