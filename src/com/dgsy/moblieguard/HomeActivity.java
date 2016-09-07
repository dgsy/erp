package com.dgsy.moblieguard;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

public class HomeActivity extends Activity {
	private GridView gv_menus;
	private int icons[] = { R.drawable.safe, R.drawable.callmsgsafe,
			R.drawable.item_gv_selector_app, R.drawable.taskmanager,
			R.drawable.netmanager, R.drawable.trojan, R.drawable.sysoptimize,
			R.drawable.atools, R.drawable.settings };
	private String names[] = { "�ֻ�����", "ͨѶ��ʿ", "����ܼ�", "���̹���", "����ͳ��", "������ɱ",
			"��������", "�߼�����", "��������" };
	private MyAdapter adapter;

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
