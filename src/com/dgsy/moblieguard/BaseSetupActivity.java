package com.dgsy.moblieguard;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.GestureDetector.OnGestureListener;
import android.view.MotionEvent;
import android.view.View;

public abstract class BaseSetupActivity extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		initView();
		 initGesture();//��ʼ������ʶ����

		initData();// ��ʼ����
		initEvent();// ��ʼ��������¼�
	}

	private void initGesture() {
		// TODO Auto-generated method stub
		GestureDetector gd=new GestureDetector(new OnGestureListener() {
			
			@Override
			public boolean onSingleTapUp(MotionEvent e) {
				// TODO Auto-generated method stub
				return false;
			}
			
			@Override
			public void onShowPress(MotionEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX,
					float distanceY) {
				// TODO Auto-generated method stub
				return false;
			}
			
			@Override
			public void onLongPress(MotionEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
					float velocityY) {
				// TODO Auto-generated method stub
				//x�᷽����ٶ��Ƿ�������򻬶������� pix/s
				if (velocityX > 200) { //�ٶȴ���400����ÿ��
					//������ɻ���
					float dx = e2.getX() - e1.getX();//x�᷽�򻬶��ļ��
					if (Math.abs(dx) < 100) {
						return true;//�����಻����ֱ����Ч
					}
					if (dx < 0 ){//�������󻬶�
						next(null);//����������¼�����
					}  else {//�������һ���
						prev(null);
					}
				}
				return true;
			}
			
			@Override
			public boolean onDown(MotionEvent e) {
				// TODO Auto-generated method stub
				return false;
			}
		});
	}

	public void initData() {
		// TODO Auto-generated method stub

	}

	public void initEvent() {
		// TODO Auto-generated method stub

	}

	// public boolean onTouchEvent(MotionEvent event) {
	// // TODO Auto-generated method stub
	// gd.onTouchEvent(event);//��onTouch�¼�
	// return super.onTouchEvent(event);
	// }
	public abstract void initView();

	/**
	 * ��һ��ҳ����¼�����
	 * 
	 * @param v
	 */
	public void next(View v) {
		// 1,��ɽ�����л�
		nextActivity();

		// 2,�����Ĳ���
		nextAnimation();// ����֮���󻮵Ķ���
		/*
		 * Intent next = new Intent(this,Setup2Activity.class);
		 * startActivity(next);
		 */
	}

	public void startActivity(Class type) {
		Intent next = new Intent(this, type);
		startActivity(next);
		finish();// �ر��Լ�
	}

	public void prev(View v) {
		// 1,��ɽ�����л�
		prevActivity();
		// 2,�����Ĳ���
		prevAnimation();// ����֮���󻮵Ķ���
	}

	private void prevAnimation() {
		// TODO Auto-generated method stub
		
	}

	public abstract void prevActivity();
	
	/**
	 * ��һ��������ʾ�Ķ���
	 */
	private void nextAnimation() {
		//��һ�����������Ķ������ڶ��������ǳ�ȥ�Ķ���
		overridePendingTransition(R.anim.next_in, R.anim.next_out);
	}

	public abstract void nextActivity();

}
