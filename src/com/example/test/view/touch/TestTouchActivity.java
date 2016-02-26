package com.example.test.view.touch;

import com.example.drawview.R;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.Button;
import android.widget.TextView;

public class TestTouchActivity extends Activity {

	private final static String TAG = "TestTouchActivity";
	private Button mButton;
	private TextView mTextView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		setContentView(R.layout.my_button_layout);
		mButton = (Button) findViewById(R.id.my_btn);
		mTextView = (TextView) findViewById(R.id.my_textview);
		
//		mTextView.setOnClickListener(new OnClickListener() {
//			
//			@Override
//			public void onClick(View v) {
//				// TODO Auto-generated method stub
//				Log.e(TAG, "mTextView onClick");
//			}
//		});

		mButton.setOnTouchListener(new OnTouchListener() {

			@Override
			public boolean onTouch(View v, MotionEvent event) {
				// TODO Auto-generated method stub
				int action = event.getAction();

				switch (action) {
				case MotionEvent.ACTION_DOWN:
					Log.e(TAG, "onTouch ACTION_DOWN");
					//return true;
					break;
				case MotionEvent.ACTION_MOVE:
					Log.e(TAG, "onTouch ACTION_MOVE");
					break;
				case MotionEvent.ACTION_UP:
					Log.e(TAG, "onTouch ACTION_UP");
					break;
				default:
					break;
				}

				return false;
			}
		});
		
		mTextView.setOnTouchListener(new OnTouchListener() {
			
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				// TODO Auto-generated method stub
				int action = event.getAction();

				switch (action) {
				case MotionEvent.ACTION_DOWN:
					Log.e(TAG, "mTextView onTouch ACTION_DOWN");
					break;
				case MotionEvent.ACTION_MOVE:
					Log.e(TAG, "mTextView onTouch ACTION_MOVE");
					break;
				case MotionEvent.ACTION_UP:
					Log.e(TAG, "mTextView onTouch ACTION_UP");
					break;
				default:
					break;
				}

				return false;
			}
		});
		
	}
	
//	@Override
//	public boolean dispatchTouchEvent(MotionEvent ev) {
//		// TODO Auto-generated method stub
//		int action = ev.getAction();
//
//		switch (action) {
//		case MotionEvent.ACTION_DOWN:
//			Log.e(TAG, "TestTouchActivity dispatchTouchEvent ACTION_DOWN");
//			break;
//		case MotionEvent.ACTION_MOVE:
//			Log.e(TAG, "TestTouchActivity dispatchTouchEvent ACTION_MOVE");
//			break;
//		case MotionEvent.ACTION_UP:
//			Log.e(TAG, "TestTouchActivity dispatchTouchEvent ACTION_UP");
//			break;
//		default:
//			break;
//		}
//		return super.dispatchTouchEvent(ev);
//	}
//	
//	@Override
//	public boolean onTouchEvent(MotionEvent event) {
//		// TODO Auto-generated method stub
//		int action = event.getAction();
//
//		switch (action) {
//		case MotionEvent.ACTION_DOWN:
//			Log.e(TAG, "TestTouchActivity onTouchEvent ACTION_DOWN");
//			break;
//		case MotionEvent.ACTION_MOVE:
//			Log.e(TAG, "TestTouchActivity onTouchEvent ACTION_MOVE");
//			break;
//		case MotionEvent.ACTION_UP:
//			Log.e(TAG, "TestTouchActivity onTouchEvent ACTION_UP");
//			break;
//		default:
//			break;
//		}
//		return super.onTouchEvent(event);
//	}
	
	
	
	
}
