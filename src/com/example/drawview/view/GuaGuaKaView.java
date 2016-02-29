package com.example.drawview.view;

import com.example.drawview.R;

import android.R.color;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Paint.Cap;
import android.graphics.Paint.Join;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

public class GuaGuaKaView extends View {

	private final static String TAG = "GuaGuaKaView";
	//在内存中绘制path的参数
	private Path mPath;
	private Canvas mCanvas;
	private Bitmap mBitmap;
	private Paint mPaint;

	private int mLastX, mLastY;

	// ------------------------

	/**
	 * 刮奖漏出的图片
	 */
	private Bitmap bitmap;
	
	//----------------------------
	//绘制刮刮卡的文本信息
	Paint mBackPaint;
	//用来获取刮刮卡上面文本信息的宽和高，---比如“谢谢惠顾”的宽和高
	Rect mTextBound;
	String mTitle;//中奖的信息
	int mTextSize ;//中奖信息字体大小
	
	

	public GuaGuaKaView(Context context) {
		this(context, null);
	}

	public GuaGuaKaView(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public GuaGuaKaView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		// TODO Auto-generated constructor stub
		init();
	}

	private void init() {
		// TODO Auto-generated method stub
		mPath = new Path();
		mPaint = new Paint();
		bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.t2);
		
		//-----------------获奖文本信息属性初始化
		mBackPaint = new Paint();
		mTextBound = new Rect();
		mTitle = "谢谢惠顾";
		mTextSize = 30;
		
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		// TODO Auto-generated method stub
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);

		int width = getMeasuredWidth();
		int height = getMeasuredHeight();

		mBitmap = Bitmap.createBitmap(width, height, Config.ARGB_8888);
		mCanvas = new Canvas(mBitmap);
		//mCanvas.drawColor(Color.BLUE);

		// 设置绘制Path画笔的一些属性
		setupOutPaint();
		setupBackPaint();

		// 绘制遮盖的图层---灰色
		//mCanvas.drawColor(Color.parseColor("#c0c0c0"));
		mCanvas.drawColor(Color.RED);

	}

	private void setupBackPaint() {
		// TODO Auto-generated method stub
		mBackPaint.setColor(Color.BLACK);
		mBackPaint.setStyle(Style.FILL);
		mBackPaint.setTextSize(mTextSize);
		
		//获取获奖文字的宽、高
		mBackPaint.getTextBounds(mTitle, 0, mTitle.length(), mTextBound);
		
	}

	private void setupOutPaint() {
		mPaint.setColor(Color.RED);
		mPaint.setAntiAlias(true);// 去锯齿
		mPaint.setDither(true);
		mPaint.setStyle(Paint.Style.STROKE);
		mPaint.setStrokeJoin(Join.ROUND);// 圆角
		mPaint.setStrokeCap(Cap.ROUND);// 圆角
		mPaint.setStrokeWidth(20);// 设置画笔的宽度
	}

	@Override
	protected void onDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		super.onDraw(canvas);
		//绘制刮刮卡的底图
		//canvas.drawBitmap(bitmap, 0, 0, null);
		
		Log.d(TAG, "getWidth = "+getWidth()+",getHeight = "+getHeight()
				+" , mTextBound.width = "+mTextBound.width()+" , mTextBound.height = "+mTextBound.height());
		
		canvas.drawText(mTitle, getWidth()/2-mTextBound.width()/2, getHeight()/2+mTextBound.height()/2, mBackPaint);
		
		drawPath();
		canvas.drawBitmap(mBitmap, 0, 0, null);
		//getPixels(getWidth(), getHeight());
	}

	/**
	 * 绘制线条
	 */
	private void drawPath() {
		mPaint.setXfermode(new PorterDuffXfermode(Mode.DST_OUT));
		mCanvas.drawPath(mPath, mPaint);
	}

	
	public void getPixels(int w, int h){
		int [] mPixels = new int [w*h];
		Bitmap bitmap = mBitmap;
		
		/**
		 * 拿到所有的像素信息
		 */
		bitmap.getPixels(mPixels, 0, w, 0, 0, w, h);
		/**
		 * 遍历统计擦除的区域
		 */
		for (int i = 0; i < w; i++)
		{
			for (int j = 0; j < h; j++)
			{
				int index = i + j * w;
				//if (mPixels[index] != 0)
				//{
					Log.d(TAG, "index = "+index+" val = "+mPixels[index]);
				//}
			}
		}
		
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		// TODO Auto-generated method stub

		int action = event.getAction();
		int x = (int) event.getX();
		int y = (int) event.getY();
		Log.d(TAG, "enter onTouchEvent , x = " + x + " , y = " + y);
		switch (action) {
		case MotionEvent.ACTION_DOWN:

			mLastX = x;
			mLastY = y;

			Log.d(TAG, "MotionEvent.ACTION_DOWN , mLastX = " + mLastX
					+ " , mLastY = " + mLastY);
			mPath.moveTo(mLastX, mLastY);
			break;

		case MotionEvent.ACTION_MOVE:

			Log.d(TAG, "MotionEvent.ACTION_MOVE , mLastX = " + mLastX
					+ " , mLastY = " + mLastY + " , x = " + x + " , y = " + y);
			int diffx = Math.abs(x - mLastX);
			int diffy = Math.abs(y - mLastY);

			if (diffx > 3 || diffy > 3) {
				mPath.lineTo(x, y);
			}

			mLastX = x;
			mLastY = y;

			break;
		case MotionEvent.ACTION_UP:
			getPixels(getWidth(), getHeight());
			break;
		default:
			break;
		}

		invalidate();
		return true;
	}

}
