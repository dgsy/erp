package com.dgsy.moblieguard;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.dgsy.moblieguard.domain.UrlBean;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;

public class SplashActivity extends Activity {
	protected static final int LOADMAIN = 1;// ���� ������
	protected static final int SHOWUPDATEDIALOG = 2;// ��ʾ�Ƿ���µĶԻ���
	private RelativeLayout rl_root;
	private int versionCode;
	private String versionName;
	private TextView tv_versionName;
	private UrlBean parsejson;// URL����Ϣ��װ
	private long startTimeMillis;
	protected Context context = SplashActivity.this;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		initView();
		initdata();
		initAnimation();

		checkVersion();
	}

	private void initdata() {
		// TODO Auto-generated method stub
		PackageManager pm = getPackageManager();
		try {
			PackageInfo packageInfo = pm.getPackageInfo(getPackageName(), 0);
			versionCode = packageInfo.versionCode;
			versionName = packageInfo.versionName;
			tv_versionName.setText(versionName);
		} catch (NameNotFoundException e) {
			// can not reach
		}
	}

	private void loadMain() {
		Intent intent = new Intent(SplashActivity.this, HomeActivity.class);
		startActivity(intent);
		finish();//�ر��Լ�	
	}

	private void checkVersion() {
		// TODO Auto-generated method stub
		// ?
		new Thread() {

			public void run() {
				try {
					startTimeMillis = System.currentTimeMillis();
					URL url = new URL(
							"http://192.168.0.100:8080/guardversion.json");
					HttpURLConnection conn = (HttpURLConnection) url
							.openConnection();
					conn.setReadTimeout(5000);
					conn.setConnectTimeout(5000);
					int responseCode = conn.getResponseCode();
					if (responseCode == 200) {
						InputStream is = conn.getInputStream();
						BufferedReader reader = new BufferedReader(
								new InputStreamReader(is));
						String line = reader.readLine();
						StringBuffer json = new StringBuffer();
						while (line != null) {
							json.append(line);
							line = reader.readLine();
						}
						parsejson = parsejson(json);
						isNewVersion(parsejson);
						System.out.println(parsejson.getVersionCode() + "�汾��");
						reader.close();
						conn.disconnect();
						parsejson(json);
					}
				} catch (MalformedURLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}

			private Handler handler = new Handler() {
				public void handleMessage(android.os.Message msg) {
					switch (msg.what) {
					case LOADMAIN:
						loadMain();
						break;
					case SHOWUPDATEDIALOG:// ��ʾ���°汾�ĶԻ���
						showUpdateDialog();
					default:
						break;
					}

				}

			};

			private void isNewVersion(UrlBean parsejson) {
				// TODO Auto-generated method stub
				int serverCode = parsejson.getVersionCode();
				long endTimeMillis = System.currentTimeMillis();// ִ�н�����ʱ��
				if (endTimeMillis - startTimeMillis < 3000) {
					// �������ߵ�ʱ��,��֤��������3��
					SystemClock.sleep(3000 - (endTimeMillis - startTimeMillis));
				}

				if (serverCode == versionCode) {

					Message msg = Message.obtain();
					msg.what = LOADMAIN;
					handler.sendMessage(msg);
				} else {
					Message msg = Message.obtain();
					msg.what = SHOWUPDATEDIALOG;
					handler.sendMessage(msg);
				}
			}

			/**
			 * @param jsonstring
			 *            �ӷ�������ȡ��JSON����
			 * @return URL�ķ�װ����
			 */
			private UrlBean parsejson(StringBuffer jsonstring) {
				UrlBean bean = new UrlBean();
				try {
					JSONObject joObject = new JSONObject(jsonstring + "");
					int version = joObject.getInt("version");
					String apkPath = joObject.getString("url");
					String desc = joObject.getString("desc");
					bean.setDesc(desc);
					bean.setUrl(apkPath);
					bean.setVersionCode(version);

				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return bean;

				// TODO Auto-generated method stub

			}

		}.start();
	}

	private void initView() {

		setContentView(R.layout.activity_splash);
		rl_root = (RelativeLayout) findViewById(R.id.rl_splash_root);
		tv_versionName = (TextView) findViewById(R.id.tv_splash_version_name);
	}

	private void initAnimation() {
		// ����Alpha����
		AlphaAnimation aa = new AlphaAnimation(0.0f, 1.0f);
		aa.setDuration(3000);
		aa.setFillAfter(true);
		RotateAnimation ra = new RotateAnimation(0, 360,
				Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF,
				0.5f);
		ra.setDuration(3000);
		ra.setFillAfter(true);
		ScaleAnimation sa = new ScaleAnimation(0.0f, 1.0f, 0.0f, 1.0f,
				Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF,
				0.5f);
		sa.setDuration(3000);
		sa.setFillAfter(true);
		AnimationSet as = new AnimationSet(true);
		as.addAnimation(sa);
		as.addAnimation(ra);
		as.addAnimation(aa);
		rl_root.startAnimation(as);
	}

	/**
	 * ��ʾ�Ƿ�����°汾�ĶԻ���
	 */
	private void showUpdateDialog() {
		// TODO Auto-generated method stub
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		// ���û�����ȡ������
		// builder.setCancelable(false);
		builder.setOnCancelListener(new OnCancelListener() {

			@Override
			public void onCancel(DialogInterface dialog) {
				// ȡ�����¼�����
				//����������
				loadMain();

			}
		}).setTitle("����").setMessage("�Ƿ�����°汾���°汾��������" + parsejson.getDesc())
				.setPositiveButton("����", new OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub
						// ����APK
						System.out.println("����APK");
						downloadNewAPK();// �����°汾
						// ��װAPK
						
						installApk();
					}
				}).setNegativeButton("ȡ��", new OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub
						// ����������
						loadMain();
					}
				});
		builder.show();
	}

	protected void installApk() {
		// TODO Auto-generated method stub
		SystemClock.sleep(1000);
		Intent intent = new Intent("android.intent.action.VIEW");
		intent.addCategory("android.intent.category.DEFAULT");
		String type = "application/vnd.android.package-archive";
		Uri data = Uri.fromFile(new File(Environment
				.getExternalStorageDirectory().getPath() + "/xx.apk"));
		intent.setDataAndType(data, type);
		startActivityForResult(intent, 0);
	}

	/**
	 * �°汾�����ذ�װ
	 */
	protected void downloadNewAPK() {
		// TODO Auto-generated method stub
		HttpUtils utils = new HttpUtils();
		// ���ص�URL
		// target ���ص�·��
		//��ɾ����XX.APK
		File file=new File(Environment
				.getExternalStorageDirectory().getPath() + "/xx.apk");
		file.delete();
		utils.download(parsejson.getUrl(), Environment
				.getExternalStorageDirectory().getPath() + "/xx.apk",
				new RequestCallBack<File>() {

					@Override
					public void onSuccess(ResponseInfo<File> arg0) {
						// TODO Auto-generated method stub
						// ���سɹ�
						// �����߳�ִ��
						Toast.makeText(context, "�����°汾�ɹ�", Toast.LENGTH_SHORT)
								.show();
					}

					@Override
					public void onFailure(HttpException arg0, String arg1) {
						// TODO Auto-generated method stub
						// ����ʧ��
						Toast.makeText(context, "�����°汾ʧ��", Toast.LENGTH_SHORT)
								.show();
					}
				});
	};

}
