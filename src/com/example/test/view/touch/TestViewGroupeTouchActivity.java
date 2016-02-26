package com.example.test.view.touch;

import com.example.drawview.R;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Telephony.Mms;
import android.support.v4.util.LruCache;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class TestViewGroupeTouchActivity extends Activity {

	private final static String TAG = "TestViewGroupeTouchActivity : ";
	
	private Button mButton ;
	private TextView mTextView;
	private LinearLayout mLinearLayout ;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.my_linear_layout);
		
		mButton = (Button) findViewById(R.id.btn_click);
		mTextView = (TextView) findViewById(R.id.my_textview_click);
		mLinearLayout = (LinearLayout) findViewById(R.id.id_my_linearlayout);
		
		
//		mLinearLayout.setOnClickListener(new OnClickListener() {
//			
//			@Override
//			public void onClick(View v) {
//				// TODO Auto-generated method stub
//				Log.e(TAG, "mLinearLayout ,  onClick");
//			}
//		});
		
		mLinearLayout.setOnTouchListener(new OnTouchListener() {
			
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				// TODO Auto-generated method stub
				int action = event.getAction();

				switch (action) {
				case MotionEvent.ACTION_DOWN:
					Log.e(TAG, "mLinearLayout , onTouch ACTION_DOWN");
					break;
				case MotionEvent.ACTION_MOVE:
					Log.e(TAG, "mLinearLayout , onTouch ACTION_MOVE");
					break;
				case MotionEvent.ACTION_UP:
					Log.e(TAG, "mLinearLayout ,onTouch ACTION_UP");
					break;
				default:
					break;
				}
				return false;
			}
		});
		
		mButton.setOnTouchListener(new OnTouchListener() {
			
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				// TODO Auto-generated method stub
				int action = event.getAction();

				switch (action) {
				case MotionEvent.ACTION_DOWN:
					Log.e(TAG, "mButton onTouch ACTION_DOWN");
					break;
				case MotionEvent.ACTION_MOVE:
					Log.e(TAG, "mButton onTouch ACTION_MOVE");
					break;
				case MotionEvent.ACTION_UP:
					Log.e(TAG, "mButton onTouch ACTION_UP");
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
				switch (event.getAction()) {
				case MotionEvent.ACTION_DOWN:
					Log.e(TAG, "mTextView , onTouch ACTION_DOWN");
					break;
				case MotionEvent.ACTION_MOVE:
					Log.e(TAG, "mTextView , onTouch ACTION_MOVE");
					break;
				case MotionEvent.ACTION_UP:
					Log.e(TAG, "mTextView , onTouch ACTION_UP");
					break;
				default:
					break;
				}
				return false;
			}
		});
		
	}
	
	
	
}
