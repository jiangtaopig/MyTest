package com.example.test.view.touch;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.Button;

public class MyButton extends Button {

	
	private final static String TAG = "MyButton::zjt";
	
	public MyButton(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}

	

	public MyButton(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		// TODO Auto-generated method stub

		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:

			Log.e(TAG, "onTouchEvent ACTION_DOWN");
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
		//return false;
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

}
