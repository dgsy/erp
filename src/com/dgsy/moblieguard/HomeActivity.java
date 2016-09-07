package com.dgsy.moblieguard;

import com.dgsy.moblieguard.utils.MyConstants;
import com.dgsy.moblieguard.utils.SpTools;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class HomeActivity extends Activity {
	private GridView gv_menus;
	private int icons[] = { R.drawable.safe, R.drawable.callmsgsafe,
			R.drawable.item_gv_selector_app, R.drawable.taskmanager,
			R.drawable.netmanager, R.drawable.trojan, R.drawable.sysoptimize,
			R.drawable.atools, R.drawable.settings };
	private String names[] = { "�ֻ�����", "ͨѶ��ʿ", "����ܼ�", "���̹���", "����ͳ��", "������ɱ",
			"��������", "�߼�����", "��������" };
	private MyAdapter adapter;
	private AlertDialog dialog;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		initView();// ��ʼ������
		initData();// ��Gridview��������
		initevent();// ��ʼ���¼�
	}

	/**
	 * ��ʼ��������¼�
	 */
	private void initevent() {
		// TODO Auto-generated method stub
		gv_menus.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				switch (position) {
				case 0:// �ֻ�����
					showSettingPassDialog();
					break;
				case 1:// �ֻ�����

					break;
				case 2:// �ֻ�����

					break;
				case 3:// �ֻ�����

					break;
				case 4:// �ֻ�����

					break;
				case 5:// �ֻ�����

					break;
				case 6:// �ֻ�����

					break;
				case 7:// �ֻ�����

					break;
				case 8:// �ֻ�����

					break;

				default:
					break;
				}
			}

		});
	}

	private void showSettingPassDialog() {
		// TODO Auto-generated method stub
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		View view = View.inflate(getApplicationContext(),
				R.layout.dialog_setting_password, null);
		final EditText et_passone = (EditText) view
				.findViewById(R.id.et_dialog_setting_password_passone);
		final EditText et_passtwo = (EditText) view
				.findViewById(R.id.et_dialog_setting_password_passtwo);
		Button bt_setpass = (Button) view
				.findViewById(R.id.bt_dialog_setting_password_setpass);
		Button bt_cancel = (Button) view
				.findViewById(R.id.bt_dialog_setting_password_cancel);

		builder.setView(view);
		bt_setpass.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// ��������
				String passone=et_passone.getText().toString().trim();
				String passtwo=et_passtwo.getText().toString().trim();
				if (TextUtils.isEmpty(passone)||TextUtils.isEmpty(passtwo)) {
					Toast.makeText(getApplicationContext(), "���벻��Ϊ��", Toast.LENGTH_LONG).show();
				return;
				}else if(!passone.equals(passtwo)) {
					Toast.makeText(getApplicationContext(), "���벻һ��", Toast.LENGTH_LONG).show();
				return;
				}else {
					//�������뵽SP��
					System.out.println("��������");
					SpTools.putString(getApplicationContext(), MyConstants.PASSWORD, passone);
					dialog.dismiss();
				}

			}
		});
		bt_cancel.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				dialog.dismiss();//�رնԻ���
			}
		});
		dialog = builder.create();
		dialog.show();
	}

	/**
	 * ��ʼ�����������
	 */
	private void initData() {
		adapter = new MyAdapter();// gridview��������
		gv_menus.setAdapter(adapter);// ����gridview����������
	}

	private class MyAdapter extends BaseAdapter {

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return icons.length;
		}

		@Override
		public Object getItem(int position) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			// TODO Auto-generated method stub
			View view = View.inflate(getApplicationContext(),
					R.layout.item_home_gridview, null);
			ImageView iv_icon = (ImageView) view
					.findViewById(R.id.iv_item_home_gv_icon);
			TextView tv_name = (TextView) view
					.findViewById(R.id.tv_item_home_gv_name);
			// ��������
			// ͼƬ
			iv_icon.setImageResource(icons[position]);
			// ����
			tv_name.setText(names[position]);
			return view;
		}

	}

	private void initView() {
		// TODO Auto-generated method stub
		setContentView(R.layout.activity_home);
		gv_menus = (GridView) findViewById(R.id.gv_home_menus);
	}
}
