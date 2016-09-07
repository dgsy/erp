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
import android.preference.PreferenceManager.OnActivityResultListener;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.widget.ProgressBar;
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
	protected static final int ERROR = 3;// ����ͳһ����
	private RelativeLayout rl_root;
	private int versionCode;
	private String versionName;
	private TextView tv_versionName;
	private UrlBean parsejson;// URL����Ϣ��װ
	private long startTimeMillis;
	protected Context context = SplashActivity.this;
	private ProgressBar pb_download;//�������°汾APK�Ľ�����

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
		finish();// �ر��Լ�
	}

	private void checkVersion() {
		// TODO Auto-generated method stub
		// ?
		new Thread() {

			public void run() {
				BufferedReader reader = null;
				HttpURLConnection conn = null;
				int errorCode = -1;// ����,û�д���
				try {
					startTimeMillis = System.currentTimeMillis();
					URL url = new URL(
							"http://192.168.0.100:8080/guardversion.json");
					conn = (HttpURLConnection) url.openConnection();
					conn.setReadTimeout(5000);
					conn.setConnectTimeout(5000);
					int responseCode = conn.getResponseCode();
					if (responseCode == 200) {
						InputStream is = conn.getInputStream();
						reader = new BufferedReader(new InputStreamReader(is));
						String line = reader.readLine();
						StringBuffer json = new StringBuffer();
						while (line != null) {
							json.append(line);
							line = reader.readLine();
						}
						parsejson = parsejson(json);

						// System.out.println(parsejson.getVersionCode() +
						// "�汾��");

					} else {
						errorCode = 404;
					}
				} catch (MalformedURLException e) {
					// TODO Auto-generated catch block
					errorCode = 4002;
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					errorCode = 4001;
					System.out.println("��������");
					e.printStackTrace();
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					errorCode = 4003;
					System.out.println("JSON�ļ�����");
					e.printStackTrace();
				} finally {
//					if (errorCode == -1) {
//						
//					} else {
//						Message msg = Message.obtain();
//						
//						handler.sendMessage(msg);
//
//					}
					Message msg = Message.obtain();
					if (errorCode==-1) {
					msg.what=isNewVersion(parsejson);//���汾�Ƿ����µ�
					}else {
						msg.what = ERROR;
						msg.arg1 = errorCode;
					}
					long endTimeMillis = System.currentTimeMillis();// ִ�н�����ʱ��
					if (endTimeMillis - startTimeMillis < 3000) {
						// �������ߵ�ʱ��,��֤��������3��
						SystemClock.sleep(3000 - (endTimeMillis - startTimeMillis));
					}
					handler.sendMessage(msg);//������Ϣ
					try {
						if (reader == null || conn == null) {
							return;
						}
						reader.close();
						conn.disconnect();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

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
						break;
					case ERROR:// ���쳣
						switch (msg.arg1) {
						case 404:// ��Դ�Ҳ���
							Toast.makeText(context, "404��Դ�Ҳ���", 0).show();
							break;
						case 4001:// �Ҳ�������
							Toast.makeText(context, "4001�Ҳ�������", 0).show();
							break;
						case 4003:// JSON��ʽ����
							Toast.makeText(context, "4003 JSON��ʽ����", 0).show();
							break;
						default:
							break;
						}
						loadMain();
						break;
					default:
						break;
					}

				}

			};

			private int isNewVersion(UrlBean parsejson) {
				// TODO Auto-generated method stub
				int serverCode = parsejson.getVersionCode();
				

				if (serverCode == versionCode) {
					return LOADMAIN;
//					Message msg = Message.obtain();
//					msg.what = LOADMAIN;
//					handler.sendMessage(msg);
				} else {
					return SHOWUPDATEDIALOG;
//					Message msg = Message.obtain();
//					msg.what = SHOWUPDATEDIALOG;
//					handler.sendMessage(msg);
				}
			}

			/**
			 * @param jsonstring
			 *            �ӷ�������ȡ��JSON����
			 * @return URL�ķ�װ����
			 * @throws JSONException
			 */
			private UrlBean parsejson(StringBuffer jsonstring)
					throws JSONException {
				UrlBean bean = new UrlBean();

				JSONObject joObject = new JSONObject(jsonstring + "");
				int version = joObject.getInt("version");
				String apkPath = joObject.getString("url");
				String desc = joObject.getString("desc");
				bean.setDesc(desc);
				bean.setUrl(apkPath);
				bean.setVersionCode(version);

				return bean;

				// TODO Auto-generated method stub

			}

		}.start();
	}

	private void initView() {

		setContentView(R.layout.activity_splash);
		rl_root = (RelativeLayout) findViewById(R.id.rl_splash_root);
		tv_versionName = (TextView) findViewById(R.id.tv_splash_version_name);
		pb_download = (ProgressBar) findViewById(R.id.pb_splash_download_progress);
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
				// ����������
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
//		SystemClock.sleep(1000);
		Intent intent = new Intent("android.intent.action.VIEW");
		intent.addCategory("android.intent.category.DEFAULT");
		String type = "application/vnd.android.package-archive";
		Uri data = Uri.fromFile(new File(Environment
				.getExternalStorageDirectory().getPath() + "/xx.apk"));
		intent.setDataAndType(data, type);
		startActivityForResult(intent, 0);
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		// ����û�ȡ������APK��ֱ�ӽ���������
		loadMain();
		super.onActivityResult(requestCode, resultCode, data);
	}

	/**
	 * �°汾�����ذ�װ
	 */
	protected void downloadNewAPK() {
		// TODO Auto-generated method stub
		HttpUtils utils = new HttpUtils();
		// ���ص�URL
		// target ���ص�·��
		// ��ɾ����XX.APK
		File file = new File(Environment.getExternalStorageDirectory()
				.getPath() + "/xx.apk");
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
						installApk();
						pb_download.setVisibility(View.GONE);
					} 

					@Override
					public void onLoading(long total, long current,
							boolean isUploading) {
						// TODO Auto-generated method stub
						pb_download.setMax((int) total);//���ý����������ֵ
						pb_download.setProgress((int) current);
						pb_download.setVisibility(View.VISIBLE);
						super.onLoading(total, current, isUploading);
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
