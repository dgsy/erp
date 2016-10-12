package com.dgsy.moblieguard;

import java.util.ArrayList;
import java.util.List;

import com.dgsy.moblieguard.domain.ContantBean;
import com.dgsy.moblieguard.engine.ReadContantsEngine;
import com.dgsy.moblieguard.utils.MyConstants;

import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

/**
 * @author Administrator 显示所有好友信息的界面
 */
public class FriendsActivity extends ListActivity {
	protected static final int LOADING = 1;
	protected static final int FINISH = 2;
	// 获取联系人的数据
	private List<ContantBean> datas = new ArrayList<ContantBean>();

	private ListView lv_datas;
	private ProgressDialog pd;
	private MyAdapter adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		lv_datas = getListView();
		// 创建LISTVIEW的适配器
		adapter = new MyAdapter();
		lv_datas.setAdapter(adapter);
		initData();
		//初始化事件
		initEvent();
	}

	/**
	 * 初始化LISTVIEW的条目点击事件
	 */
	private void initEvent() {
		// TODO Auto-generated method stub
		lv_datas.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				//处理条目点击事件
				//获取当前条目的数据
				ContantBean contantBean = datas.get(position);
				//获取号码
				String phone = contantBean.getPhone();
				Intent datas=new Intent();
				datas.putExtra(MyConstants.SAFENUMBER, phone);//保存安全号码
				//设置数据
				setResult(1,datas);
				//关闭自己
				finish();
				
			}
		});
	}

	private Handler handler = new Handler() {

		public void handleMessage(Message msg) {
			// 更新界面
			switch (msg.what) {
			case LOADING:// 正在加载数据
				// 显示对话框
				pd = new ProgressDialog(FriendsActivity.this);
				pd.setTitle("注意");
				pd.setMessage("正在玩命加载数据...");
				pd.show();// 显示对话框
				break;
			case FINISH:// 数据加载完成
				if (pd != null) {
					pd.dismiss();// 关闭对话框
					pd = null;// 垃圾回收释放内存
				}

				// 数据显示在LISTVIEW中,通过适配器来通知listview
				adapter.notifyDataSetChanged();
				break;
			default:
				break;
			}
		};
	};

	private class MyAdapter extends BaseAdapter {

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return datas.size();// 数据的个数
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			// TODO Auto-generated method stub
			// 每个条目显示的数据
			View view = View.inflate(getApplicationContext(),
					R.layout.item_friend_listview, null);
			TextView tv_name = (TextView) view
					.findViewById(R.id.tv_friends_item_name);
			TextView tv_phone = (TextView) view
					.findViewById(R.id.tv_friends_item_phone);
			ContantBean bean = datas.get(position);
			tv_name.setText(bean.getName());// 名字
			tv_phone.setText(bean.getPhone());// 电话
			return view;
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

	}

	private void initData() {
		// TODO Auto-generated method stub
		// 获取数据2中本地数据和网络数据
		// 子线程访问数据
		new Thread() {

			public void run() {
				// 显示获取数据的进度
				Message msg = Message.obtain();
				msg.what = LOADING;
				handler.sendMessage(msg);
				SystemClock.sleep(2000);// 为了展示进度条,休眠2秒
				datas = ReadContantsEngine
						.readContants(getApplicationContext());
				// 数据获取完成,发送加载完成的消息

				msg = Message.obtain();
				msg.what = FINISH;
				handler.sendMessage(msg);
			};

			// 获取操作

		}.start();
	}
}
