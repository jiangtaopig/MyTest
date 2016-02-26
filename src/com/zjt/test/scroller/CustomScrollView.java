package com.zjt.test.scroller;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Scroller;

public class CustomScrollView extends ViewGroup {

	private final static String TAG = "CustomScrollView : zjt ";
	
	private int mLastX;
	private int mCurX;
	private int mOffX;
	
	private Scroller mScroller;

	public CustomScrollView(Context context) {
		this(context, null);
	}

	public CustomScrollView(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public CustomScrollView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		// TODO Auto-generated constructor stub
		mScroller = new Scroller(context);
	}

	@Override
	protected void onLayout(boolean changed, int l, int t, int r, int b) {
		// TODO Auto-generated method stub

		for (int i = 0; i < getChildCount(); i++) {
			View v = getChildAt(i);
			v.layout(0 + i * getWidth(), 0, getWidth() + i * getWidth(), getHeight());
		}
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		// TODO Auto-generated method stub
		 switch (event.getAction()) {  
         case MotionEvent.ACTION_DOWN:  
              // 只考虑水平方向  
              mLastX = (int) event.getX();  
              Log.e(TAG, "ACTION_DOWN , mLastX = "+mLastX);
              return true;  
               
         case MotionEvent.ACTION_MOVE:  
              mCurX = (int) event.getX();  
              mOffX = mCurX - mLastX;  
              Log.e(TAG, "ACTION_MOVE , mLastX = "+mLastX+" , mCurX = "+mCurX+" , scrollx = "+getScrollX());
             // scrollBy(-mOffX, 0);  
              mScroller.startScroll(getScrollX(), 0, -mOffX, 0);
              break;  
               
         case MotionEvent.ACTION_UP:  
        	 Log.e(TAG, "ACTION_UP x = "+getScrollX()+" , y = "+getScrollY()+" , scrollx = "+getScrollX());
            //  scrollTo(0, 0);  
        	 mScroller.startScroll(getScrollX(), 0, -100, 0);
              break;  
         } 
		 
         invalidate();  
         return super.onTouchEvent(event); 
	}

	

	@Override
	public void computeScroll() {
		// TODO Auto-generated method stub
		super.computeScroll();
		
		Log.d(TAG, "enter computeScroll");
		
		if(mScroller.computeScrollOffset()){//判断是否滑动结束 true表示未结束。
		
			int curX = mScroller.getCurrX();
			Log.e("zzzzzzz", "curX = "+curX);
			scrollTo(curX, 0);  
            invalidate();  
		}
		
	}
	
	
}
