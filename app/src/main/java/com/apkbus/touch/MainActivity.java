package com.apkbus.touch;

import android.os.Bundle;
import android.view.GestureDetector;
import android.view.GestureDetector.OnGestureListener;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.Button;
import android.widget.Toast;
import android.app.Activity;

public class MainActivity extends Activity  {
	private Button touchButton;
	
	//利用手势处理实时更新控件位置
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		touchButton = (Button) findViewById(R.id.touch_button);
		touchButton.setOnTouchListener(new MyGesture());
	}

	

	class MyGesture implements OnTouchListener, OnGestureListener {

		GestureDetector myGesture = new GestureDetector(MainActivity.this,this);
		View view = null;
		int[] temp = new int[] { 0, 0 };
		
		@Override
		public boolean onTouch(View v, MotionEvent event) {

			if(view == null)
				view = v;
			myGesture.onTouchEvent(event);
			return false;
		}
		
		//在按下时调用 
		@Override
		public boolean onDown(MotionEvent e) {
			
			temp[0] = (int) e.getX();
			temp[1] = ((int) e.getRawY()) - view.getTop();
			return false;
		}
		
		//手指在触摸屏上迅速移动，并松开的动作。
		@Override
		public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
				float velocityY) {
			
			return false;
		}
		
		//长按的时候调用
		@Override
		public void onLongPress(MotionEvent e) {
			
			
		}
		
		//按住然后滑动时调用
		@Override
		public boolean onScroll(MotionEvent e1, MotionEvent e2,
				float distanceX, float distanceY) {
			int x = (int) e2.getRawX();
			int y = (int) e2.getRawY();
			view.layout(x - temp[0], y - temp[1], x + view.getWidth() - temp[0], y - temp[1] + view.getHeight());
			return false;
		}
		
		// 用户轻触触摸屏，尚未松开或拖动，由一个1个MotionEvent ACTION_DOWN触发 
		// 注意和onDown()的区别，强调的是没有松开或者拖动的状态
		@Override
		public void onShowPress(MotionEvent e) {
			
			
		}
		
		// 用户（轻触触摸屏后）松开，由一个1个MotionEvent ACTION_UP触发
		@Override
		public boolean onSingleTapUp(MotionEvent e) {
			Toast.makeText(MainActivity.this, "你点击了按钮", Toast.LENGTH_LONG).show();
			return false;
		}
		
	}

	

}
