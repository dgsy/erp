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
	private String names[] = { "手机防盗", "通讯卫士", "软件管家", "进程管理", "流量统计", "病毒查杀",
			"缓存清理", "高级工具", "设置中心" };
	private MyAdapter adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		initView();// 初始化数据
		initData();// 给Gridview设置数据
		initevent();// 初始化事件
	}

	/**
	 * 初始化组件的事件
	 */
	private void initevent() {
		// TODO Auto-generated method stub
		gv_menus.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				switch (position) {
				case 0:// 手机防盗

					break;
				case 1:// 手机防盗

					break;
				case 2:// 手机防盗

					break;
				case 3:// 手机防盗

					break;
				case 4:// 手机防盗

					break;
				case 5:// 手机防盗

					break;
				case 6:// 手机防盗

					break;
				case 7:// 手机防盗

					break;
				case 8:// 手机防盗

					break;

				default:
					break;
				}
			}
		});
	}

	/**
	 * 初始化组件的数据
	 */
	private void initData() {
		adapter = new MyAdapter();// gridview的适配器
		gv_menus.setAdapter(adapter);// 设置gridview适配器数据
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
			// 设置数据
			// 图片
			iv_icon.setImageResource(icons[position]);
			// 文字
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
