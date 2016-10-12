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
 * @author Administrator ��ʾ���к�����Ϣ�Ľ���
 */
public class FriendsActivity extends ListActivity {
	protected static final int LOADING = 1;
	protected static final int FINISH = 2;
	// ��ȡ��ϵ�˵�����
	private List<ContantBean> datas = new ArrayList<ContantBean>();

	private ListView lv_datas;
	private ProgressDialog pd;
	private MyAdapter adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		lv_datas = getListView();
		// ����LISTVIEW��������
		adapter = new MyAdapter();
		lv_datas.setAdapter(adapter);
		initData();
		//��ʼ���¼�
		initEvent();
	}

	/**
	 * ��ʼ��LISTVIEW����Ŀ����¼�
	 */
	private void initEvent() {
		// TODO Auto-generated method stub
		lv_datas.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				//������Ŀ����¼�
				//��ȡ��ǰ��Ŀ������
				ContantBean contantBean = datas.get(position);
				//��ȡ����
				String phone = contantBean.getPhone();
				Intent datas=new Intent();
				datas.putExtra(MyConstants.SAFENUMBER, phone);//���氲ȫ����
				//��������
				setResult(1,datas);
				//�ر��Լ�
				finish();
				
			}
		});
	}

	private Handler handler = new Handler() {

		public void handleMessage(Message msg) {
			// ���½���
			switch (msg.what) {
			case LOADING:// ���ڼ�������
				// ��ʾ�Ի���
				pd = new ProgressDialog(FriendsActivity.this);
				pd.setTitle("ע��");
				pd.setMessage("����������������...");
				pd.show();// ��ʾ�Ի���
				break;
			case FINISH:// ���ݼ������
				if (pd != null) {
					pd.dismiss();// �رնԻ���
					pd = null;// ���������ͷ��ڴ�
				}

				// ������ʾ��LISTVIEW��,ͨ����������֪ͨlistview
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
			return datas.size();// ���ݵĸ���
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			// TODO Auto-generated method stub
			// ÿ����Ŀ��ʾ������
			View view = View.inflate(getApplicationContext(),
					R.layout.item_friend_listview, null);
			TextView tv_name = (TextView) view
					.findViewById(R.id.tv_friends_item_name);
			TextView tv_phone = (TextView) view
					.findViewById(R.id.tv_friends_item_phone);
			ContantBean bean = datas.get(position);
			tv_name.setText(bean.getName());// ����
			tv_phone.setText(bean.getPhone());// �绰
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
		// ��ȡ����2�б������ݺ���������
		// ���̷߳�������
		new Thread() {

			public void run() {
				// ��ʾ��ȡ���ݵĽ���
				Message msg = Message.obtain();
				msg.what = LOADING;
				handler.sendMessage(msg);
				SystemClock.sleep(2000);// Ϊ��չʾ������,����2��
				datas = ReadContantsEngine
						.readContants(getApplicationContext());
				// ���ݻ�ȡ���,���ͼ�����ɵ���Ϣ

				msg = Message.obtain();
				msg.what = FINISH;
				handler.sendMessage(msg);
			};

			// ��ȡ����

		}.start();
	}
}
