package com.example.test.view.touch;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.LinearLayout;

public class MyLinearLayout extends LinearLayout {

	private final static String TAG = "MyLinearLayout :";

	public MyLinearLayout(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean dispatchTouchEvent(MotionEvent ev) {
		// TODO Auto-generated method stub
		int action = ev.getAction();

		switch (action) {
		case MotionEvent.ACTION_DOWN:
			Log.e(TAG, "dispatchTouchEvent , ACTION_DOWN");
			break;

		case MotionEvent.ACTION_MOVE:
			Log.e(TAG, "dispatchTouchEvent , ACTION_MOVE");
			break;
		case MotionEvent.ACTION_UP:
			Log.e(TAG, "dispatchTouchEvent , ACTION_UP");
			break;
		default:
			break;
		}

		return super.dispatchTouchEvent(ev);
	}

	@Override
	public boolean onInterceptTouchEvent(MotionEvent ev) {
		// TODO Auto-generated method stub
		int action = ev.getAction();

		switch (action) {
		case MotionEvent.ACTION_DOWN:
			Log.e(TAG, "onInterceptTouchEvent , ACTION_DOWN");
	        return true;
			//break;

		case MotionEvent.ACTION_MOVE:
			Log.e(TAG, "onInterceptTouchEvent , ACTION_MOVE");
			//return true;
			break;
		case MotionEvent.ACTION_UP:
			Log.e(TAG, "onInterceptTouchEvent , ACTION_UP");
			break;
		default:
			break;
		}
		return super.onInterceptTouchEvent(ev);
		//return true;
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		// TODO Auto-generated method stub
		int action = event.getAction();

		switch (action) {
		case MotionEvent.ACTION_DOWN:
			Log.e(TAG, "onTouchEvent , ACTION_DOWN");
			return true;
			//break;

		case MotionEvent.ACTION_MOVE:
			Log.e(TAG, "onTouchEvent , ACTION_MOVE");
			break;
		case MotionEvent.ACTION_UP:
			Log.e(TAG, "onTouchEvent , ACTION_UP");
			break;
		default:
			break;
		}
		return super.onTouchEvent(event);
		//return true;
	}
	
	@Override
	public void requestDisallowInterceptTouchEvent(boolean disallowIntercept) {
		// TODO Auto-generated method stub
		Log.e(TAG, "enter requestDisallowInterceptTouchEvent");
		super.requestDisallowInterceptTouchEvent(disallowIntercept);
	}

}
