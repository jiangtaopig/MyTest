package com.example.test.view.touch;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.TextView;

public class MyTextView extends TextView {

	private final static String TAG = "MyTextView : ";

	public MyTextView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean dispatchTouchEvent(MotionEvent event) {
		// TODO Auto-generated method stub
		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:

			Log.e(TAG, "dispatchTouchEvent ACTION_DOWN");
			break;
		case MotionEvent.ACTION_MOVE:

			Log.e(TAG, "dispatchTouchEvent ACTION_MOVE");
			break;
		case MotionEvent.ACTION_UP:

			Log.e(TAG, "dispatchTouchEvent ACTION_UP");
			break;

		default:
			break;
		}
		return super.dispatchTouchEvent(event);
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		// TODO Auto-generated method stub
		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:

			Log.e(TAG, "onTouchEvent ACTION_DOWN");
			// //return true 后面的ACTION_MOVE、和ACTION_UP能够得以执行，如果不做任何操作，即
			// break，由于textview默认是不可点击和长点击的，所以return false,
			// 那么 dispatcTouchEvent 会 return false，导致后面的ACTION_MOVE 和
			// ACTION_UP不能执行
			// return true;
			break;
		case MotionEvent.ACTION_MOVE:
			Log.e(TAG, "onTouchEvent ACTION_MOVE");
			break;
		case MotionEvent.ACTION_UP:

			Log.e(TAG, "onTouchEvent ACTION_UP");
			break;

		default:
			break;
		}
		return super.onTouchEvent(event);
	}

}
