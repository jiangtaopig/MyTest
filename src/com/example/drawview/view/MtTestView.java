package com.example.drawview.view;

import com.example.drawview.R;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;

public class MtTestView extends View {

	private final static String TAG = "MtTestView";

	/**
	 * 设置view的字体大小、颜色以及内容
	 */
	private String mTitle;
	private int mTitleSize;
	private int mTitleColor;

	private Rect textBound;
	private Paint mPaint;
	private Rect tmpBound;
	private Canvas mCanvas;
	Bitmap mBitmap = BitmapFactory.decodeResource(getResources(),
			R.drawable.ic_menu_emoticons);
	Bitmap bitmap2;
	private float mAlpha = 1.0f;

	Paint mPaint2;

	private Bitmap bitm1, bitm2;
	Bitmap bm;
	Rect rect;
	
	private Bitmap srcBitmap,desBitmap;

	public MtTestView(Context context) {
		this(context, null);
	}

	public MtTestView(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public MtTestView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		// TODO Auto-generated constructor stub

		TypedArray ta = context.obtainStyledAttributes(attrs,
				R.styleable.CustomTitleView, defStyle, 0);

		int n = ta.getIndexCount();

		for (int i = 0; i < n; i++) {

			int attr = ta.getIndex(i);
			switch (attr) {
			case R.styleable.CustomTitleView_titleText:
				mTitle = ta.getString(attr);
				break;
			case R.styleable.CustomTitleView_titleTextColor:
				mTitleColor = ta.getColor(attr, Color.BLACK);
				break;
			case R.styleable.CustomTitleView_titleTextSize:
				mTitleSize = ta.getDimensionPixelSize(attr, (int) TypedValue
						.applyDimension(TypedValue.COMPLEX_UNIT_SP, 16,
								getResources().getDisplayMetrics()));
				break;

			default:
				break;
			}

		}

		ta.recycle();
		mPaint = new Paint();
		mPaint.setTextSize(mTitleSize);
		textBound = new Rect();
		tmpBound = new Rect();

		// 获取绘制字体内容的长、宽
		mPaint.getTextBounds(mTitle, 0, mTitle.length(), textBound);
		
		bitm1 = BitmapFactory.decodeResource(getResources(), R.drawable.a1);
		bitm2 = BitmapFactory.decodeResource(getResources(), R.drawable.a2);
		bm = bitm1.copy(Config.ARGB_8888, true);
		
		rect = new Rect();
		rect.left = 0;
		rect.right = 80;
		rect.top = 0;
		rect.bottom = 80;
		
		srcBitmap = drawRect();
		desBitmap = drawCircle();

	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		// TODO Auto-generated method stub

		int widthMode = MeasureSpec.getMode(widthMeasureSpec);
		int widthSize = MeasureSpec.getSize(widthMeasureSpec);

		int heightMode = MeasureSpec.getMode(heightMeasureSpec);
		int heightSize = MeasureSpec.getSize(heightMeasureSpec);

		int width = 0, heigh = 0;

		mPaint.setTextSize(mTitleSize);
		mPaint.getTextBounds(mTitle, 0, mTitle.length(), textBound);

		Log.d(TAG,
				"widthSize = " + widthSize + " , bound width = "
						+ textBound.width());
		Log.d(TAG, "heightSize = " + widthSize + " , bound height = "
				+ textBound.height());

		if (widthMode == MeasureSpec.EXACTLY) {
			width = widthSize;
		} else {
			mPaint.setTextSize(mTitleSize);
			mPaint.getTextBounds(mTitle, 0, mTitle.length(), tmpBound);
			float boundWid = tmpBound.width();
			int wid = (int) (boundWid + getPaddingRight() + getPaddingLeft());
			// width = Math.min(wid, widthSize);
			width = wid;
		}

		if (heightMode == MeasureSpec.EXACTLY) {
			heigh = heightSize;
		} else {

			mPaint.setTextSize(mTitleSize);
			mPaint.getTextBounds(mTitle, 0, mTitle.length(), tmpBound);
			float boundHei = tmpBound.height();

			int height = (int) (boundHei + getPaddingBottom() + getPaddingTop());
			// heigh = Math.min(height, heightSize);
			heigh = height;
		}

		setMeasuredDimension(width, heigh);

	}

	@Override
	protected void onDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		super.onDraw(canvas);

		int width = getWidth();
		int height = getHeight();

		int measureWid = getMeasuredWidth();
		int measureHeight = getMeasuredHeight();

		Log.d(TAG, "width = " + width + " , height = " + height
				+ " , measureWid = " + measureWid + " , measureHeight = "
				+ measureHeight);
		/**
		 * tset 1
		 * 
		canvas.drawBitmap(mBitmap, null, rect, null);
		setupTargetBitmap(mAlpha);
		canvas.drawBitmap(bitmap2, 0, 0, null);
		*/
		
		/**
		 * test 2
		 * 
		 canvas.drawBitmap(bitm2, 0, 0, null);
		 init();
		 canvas.drawBitmap(bm, 0, 0, null);
		*/
		
//		testMode();
//		canvas.drawBitmap(bitmap2, 0, 0, null);
		
		canvas.drawBitmap(srcBitmap, 0, 0, null);
		
		canvas.drawBitmap(desBitmap, 0, 0, null);
		
	}

	
	public void testMode(){
		bitmap2 = Bitmap.createBitmap(getMeasuredWidth(), getMeasuredHeight(),
				Config.ARGB_8888);
		mCanvas = new Canvas(bitmap2);
		mPaint = new Paint();
		mPaint.setColor(Color.BLUE);
		//mPaint.setStrokeWidth(4);  
	    //mPaint.setStyle(Paint.Style.STROKE);
	    
	    mCanvas.drawRect(0, 0, 200, 100, mPaint);
	    mPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
	    
	    mPaint.setColor(Color.RED);
	    mCanvas.drawCircle(100, 100, 50, mPaint);
		
		
	}

	private void setupTargetBitmap(float alpha) {
		bitmap2 = Bitmap.createBitmap(getMeasuredWidth(), getMeasuredHeight(),
				Config.ARGB_8888);
		mCanvas = new Canvas(bitmap2);
		mPaint = new Paint();
		mPaint.setColor(Color.GREEN);
		// mPaint.setAlpha(0);
		mPaint.setAntiAlias(true);
		mPaint.setDither(true);
		mCanvas.drawRect(0, 0, 80, 80, mPaint);
		mPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
		mPaint.setAlpha(255);
		mCanvas.drawBitmap(mBitmap, null, rect, mPaint);
	}
	
	public void init() {
		
		 mCanvas = new Canvas();
		 mCanvas.setBitmap(bm);
		 mPaint2 = new Paint();
		 //mPaint2.setAlpha(255);
		 mCanvas.drawBitmap(bitm2, 0, 0, mPaint2);
		 mPaint2.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_IN));
		 //mPaint2.setAlpha(180);
		 mCanvas.drawBitmap(bitm1, 0, 0, mPaint2);
	}
	
	
	public Bitmap drawCircle(){
		Bitmap bm = Bitmap.createBitmap(getMeasuredWidth(), getMeasuredHeight(),
				Config.ARGB_8888);
		Canvas canvas = new Canvas(bm);
		Paint paint = new Paint();
		paint.setColor(Color.RED);
		canvas.drawCircle(100, 100, 50, paint);
		return bm;
	}
	
	public Bitmap drawRect(){
		Bitmap bm = Bitmap.createBitmap(getMeasuredWidth(), getMeasuredHeight(),
				Config.ARGB_8888);
		Canvas canvas = new Canvas(bm);
		Paint paint = new Paint();
		paint.setColor(Color.BLUE);
		canvas.drawRect(0, 0, 200, 100, paint);
		return bm;
	}
	
	
	
	
	

}
