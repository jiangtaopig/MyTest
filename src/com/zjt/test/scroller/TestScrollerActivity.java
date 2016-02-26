package com.zjt.test.scroller;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.example.drawview.R;

import android.app.Activity;
import android.os.Bundle;
import android.os.Looper;
import android.os.SystemClock;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;

public class TestScrollerActivity extends Activity implements OnClickListener {

	private final static String TAG = "TestScrollerActivity : ";
	private Button mButton;
	private LinearLayout mLinearLayout;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		setContentView(R.layout.scroller_layout);

		mButton = (Button) findViewById(R.id.scroller_btn);

		mButton.setOnClickListener(this);

		mLinearLayout = (LinearLayout) findViewById(R.id.ll_scroller_layout);

		// testDate();

	}

	private void testDate() {
		long t = SystemClock.elapsedRealtime();

		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		// long d = 1453281415;
		Date date = new Date(t);
		String s = sf.format(date);
		Log.d(TAG, " s = " + s);

		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		t = SystemClock.elapsedRealtime();
		Date date1 = new Date(t);
		String s1 = sf.format(date1);
		Log.d(TAG, " s1 = " + s1);
	}

	int val = -20;
	
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.scroller_btn:
			int x = mButton.getScrollX();
			int y = mButton.getScrollY();
			Log.e(TAG, "x = " + x + " , y = " + y);
			
			 mButton.scrollTo(val, 0);
			val +=10;
			//mLinearLayout.scrollTo(-20, 0);
			
			break;

		default:
			break;
		}
	}

}
