package com.zjt.test.scroller;

import com.example.drawview.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;

public class TestScrollGroupActivity extends Activity {

	private final static String TAG = "TestScrollGroupActivity";
	private CustomScrollView mCustomScrollView;
	private int [] mImgs = new int[]{R.drawable.p7,R.drawable.p8,R.drawable.p11};
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.custom_scrollview_layout);
		
		mCustomScrollView = (CustomScrollView) findViewById(R.id.custom_scrollview);
		
		for (int i = 0; i < mImgs.length; i++) {
			ImageView img = new ImageView(TestScrollGroupActivity.this);
			img.setBackgroundResource(mImgs[i]);
			img.setScaleType(ScaleType.FIT_XY);
			img.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.MATCH_PARENT));
			mCustomScrollView.addView(img);
			
		}
		
	}
	
}
