package com.dgsy.moblieguard;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.os.Bundle;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.widget.RelativeLayout;

import com.dgsy.moblieguard.domain.UrlBean;

public class SplashActivity extends Activity {
	private RelativeLayout rl_root;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		initView();
		initAnimation();
		checkVersion();
	}

	private void checkVersion() {
		// TODO Auto-generated method stub
		new Thread() {

			public void run() {
				try {
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
						StringBuffer jsonstring = new StringBuffer();
						while (line != null) {
							jsonstring.append(line);
							line = reader.readLine();
						}
						UrlBean parsejson = parsejson(jsonstring);
						System.out.println(parsejson.getVersionCode());
						reader.close();
						conn.disconnect();
					}
				} catch (MalformedURLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}

			/**
			 * @param jsonstring
			 *            从服务器获取的JSON数据
			 * @return URL的封装对象
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
	}

	private void initAnimation() {
		// 创建Alpha动画
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

}
