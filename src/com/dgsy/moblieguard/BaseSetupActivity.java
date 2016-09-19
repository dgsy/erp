package com.dgsy.moblieguard;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public abstract class BaseSetupActivity extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		initView();
		// initGesture();//初始化手势识别器

		initData();// 初始数据
		initEvent();// 初始化组件的事件
	}

	public void initData() {
		// TODO Auto-generated method stub

	}

	public void initEvent() {
		// TODO Auto-generated method stub

	}

	// public boolean onTouchEvent(MotionEvent event) {
	// // TODO Auto-generated method stub
	// gd.onTouchEvent(event);//绑定onTouch事件
	// return super.onTouchEvent(event);
	// }
	public abstract void initView();

	/**
	 * 下一个页面的事件处理
	 * 
	 * @param v
	 */
	public void next(View v) {
		// 1,完成界面的切换
		nextActivity();

		// 2,动画的播放
		nextAnimation();// 界面之间企划的动画
		/*
		 * Intent next = new Intent(this,Setup2Activity.class);
		 * startActivity(next);
		 */
	}

	public void startActivity(Class type) {
		Intent next = new Intent(this, type);
		startActivity(next);
		finish();// 关闭自己
	}

	public void prev(View v) {
		// 1,完成界面的切换
		prevActivity();
		// 2,动画的播放
		prevAnimation();// 界面之间企划的动画
	}

	private void prevAnimation() {
		// TODO Auto-generated method stub
		
	}

	public abstract void prevActivity();

	/**
	 * 下一个界面显示的动画
	 */
	private void nextAnimation() {
		//第一个参数进来的动画，第二个参数是出去的动画
		overridePendingTransition(R.anim.next_in, R.anim.next_out);
	}

	public abstract void nextActivity();

}
