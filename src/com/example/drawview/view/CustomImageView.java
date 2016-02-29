package com.example.drawview.view;

import com.example.drawview.R;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Rect;
import android.text.TextPaint;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.widget.ImageView;

public class CustomImageView extends View {

	private final static String TAG = "CustomImageView";
	private final static int IMAGE_SCALE_FITXY = 0;
	private final static int IMAGE_SCALE_CENTER = 1;

	private Bitmap mImage;
	private String mTitle;
	private int mTextSize;
	private int mTextColor;
	private int imageScaleType;

	private Rect rect;
	private Paint mPaint;
	private Rect mTextBound;
	private Canvas canvas;
	
	//TextPaint textPaint ;

	private int mWidth;
	private int mHeight;

	public CustomImageView(Context context) {
		this(context, null);
	}

	public CustomImageView(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	/**
	 * 初始化所以自定义类型
	 * 
	 * @param context
	 * @param attrs
	 * @param defStyleAttr
	 */
	public CustomImageView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		// TODO Auto-generated constructor stub
		TypedArray a = context.getTheme().obtainStyledAttributes(attrs,
				R.styleable.CustomImageView, defStyleAttr, 0);
		int n = a.getIndexCount();
		for (int i = 0; i < n; i++) {
			int attr = a.getIndex(i);
			switch (attr) {
			case R.styleable.CustomImageView_image:

				mImage = BitmapFactory.decodeResource(getResources(),
						a.getResourceId(attr, 0));
				break;
			case R.styleable.CustomImageView_imageScaleType:

				imageScaleType = a.getInt(attr, 0);
				break;
			case R.styleable.CustomImageView_titleText:

				mTitle = a.getString(attr);
				break;
			case R.styleable.CustomImageView_titleTextColor:

				mTextColor = a.getColor(attr, Color.BLACK);
				break;
			case R.styleable.CustomImageView_titleTextSize:

				mTextSize = a.getDimensionPixelSize(attr, (int) TypedValue
						.applyDimension(TypedValue.COMPLEX_UNIT_SP, 16,
								getResources().getDisplayMetrics()));
				break;

			default:
				break;
			}
		}

		a.recycle();

		rect = new Rect();
		mPaint = new Paint();
		mTextBound = new Rect();
		
		//textPaint = new TextPaint();

		mPaint.setTextSize(mTextSize);
		// 计算控件上文本的宽和高
		mPaint.getTextBounds(mTitle, 0, mTitle.length(), mTextBound);
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		// TODO Auto-generated method stub
		//super.onMeasure(widthMeasureSpec, heightMeasureSpec);

		int specMode = MeasureSpec.getMode(widthMeasureSpec);
		int specSize = MeasureSpec.getSize(widthMeasureSpec);

		if (specMode == MeasureSpec.EXACTLY) {// match_parent / accurate
			mWidth = specSize;
			Log.d(TAG, " , ..EXACTLY .width. specSize = " + specSize);
		} else {
			// 由图片决定的宽度
			int desireByImg = getPaddingLeft() + mImage.getWidth()
					+ getPaddingRight();
			// 由文字决定的宽度
			int desireByText = getPaddingLeft() + mTextBound.width()
					+ getPaddingRight();
			if (specMode == MeasureSpec.AT_MOST) {// wrap_content
				int desire = Math.max(desireByImg, desireByText);
				// 这种MeasureSpec.AT_MOST模式下，宽度不能超过父控件给定的值specSize
				mWidth = Math.min(specSize, desire);
				Log.e(TAG, " , AT_MOST , xxxx mWidth = " + mWidth + " , mImage.getWidth() = "+mImage.getWidth());
			}
		}

		specMode = MeasureSpec.getMode(heightMeasureSpec);
		specSize = MeasureSpec.getSize(heightMeasureSpec);

		if (specMode == MeasureSpec.EXACTLY) {
			mHeight = specSize;
			Log.e(TAG, " , ..EXACTLY .height. specSize = " + specSize);
		} else {
			int desire = getPaddingBottom() + mImage.getHeight()
					+ mTextBound.height() + getPaddingTop();
			if (specMode == MeasureSpec.AT_MOST) {//wrap content
				mHeight = Math.min(desire, specSize);
				Log.e(TAG, " , AT_MOST , xxxx mHeight = " + mHeight + "　,　mImage.getHeight()　＝　"+mImage.getHeight());
			}else{//一般是 scrollview中用得到 
				mHeight = Math.max(desire, specSize);
				Log.e(TAG, " , AT_MOST , xxxx mHeight = " + mHeight );
			}
		}

		setMeasuredDimension(mWidth, mHeight);
	}

	//
	@Override
	protected void onDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		// super.onDraw(canvas);
		/**
		 * 绘制边框
		 */

		mPaint.setStrokeWidth(4);
		mPaint.setStyle(Paint.Style.STROKE);
		mPaint.setColor(Color.CYAN);
		canvas.drawRect(0, 0, getMeasuredWidth(), getMeasuredHeight(), mPaint);

		rect.left = getPaddingLeft();
		rect.right = mWidth - getPaddingRight();
		rect.top = getPaddingTop();
		rect.bottom = mHeight - getPaddingBottom();

		mPaint.setColor(mTextColor);
		mPaint.setStyle(Style.FILL);

		Log.d(TAG, " , mTextBound wid = " + mTextBound.width()
				+ " , mTextBound height = " + mTextBound.height()
				+ " , left = " + mTextBound.left + " , right = "
				+ mTextBound.right + " , top = " + mTextBound.top
				+ " , bottom = " + mTextBound.bottom);

		/**
		 * 当前设置的宽度小于字体需要的宽度，将字体改为xxx...
		 */
		
		//文字放于图片底下
		
		/*
		if (mTextBound.width() > mWidth) {
			TextPaint textPaint = new TextPaint(mPaint);
			String msg = TextUtils.ellipsize(mTitle, textPaint,
					(float) mWidth - getPaddingLeft() - getPaddingRight(),
					TextUtils.TruncateAt.END).toString();
			canvas.drawText(msg, getPaddingLeft(), mHeight
					- getPaddingBottom(), mPaint);
		} else {// 正常情况下，字体居中
			canvas.drawText(mTitle, mWidth / 2 - mTextBound.width() / 2,
					mHeight - getPaddingBottom(), mPaint);
		}

		// 取消使用掉的块（即减去文字所占的宽度）
		rect.bottom -= mTextBound.height();

		if (imageScaleType == IMAGE_SCALE_FITXY) {
			canvas.drawBitmap(mImage, null, rect, mPaint);
		} else {
			// 计算居中的矩形范围
			rect.left = mWidth / 2 - mImage.getWidth() / 2;
			rect.right = mWidth / 2 + mImage.getWidth() / 2;
			rect.top = (mHeight - mTextBound.height()) / 2 - mImage.getHeight()
					/ 2;
//			rect.bottom = (mHeight - mTextBound.height()) / 2
//					+ mImage.getHeight() / 2;
			
			rect.bottom = rect.top + mImage.getHeight();//等于rect.top + 图片的高度

			canvas.drawBitmap(mImage, null, rect, mPaint);
		}*/
		
		//文字放于图片上方
		
		if (mTextBound.width() > mWidth) {
			TextPaint textPaint = new TextPaint(mPaint);
			String msg = TextUtils.ellipsize(mTitle, textPaint,
					(float) mWidth - getPaddingLeft() - getPaddingRight(),
					TextUtils.TruncateAt.END).toString();
			canvas.drawText(msg, getPaddingLeft(), getPaddingTop()+mTextBound.height()
					, mPaint);
		} else {// 正常情况下，字体居中
			canvas.drawText(mTitle, mWidth / 2 - mTextBound.width() / 2,
					getPaddingTop()+mTextBound.height(), mPaint);
		}
		
		rect.top += mTextBound.height();
		
		if(imageScaleType == IMAGE_SCALE_FITXY){
			canvas.drawBitmap(mImage, null, rect, mPaint);
		}else{
			rect.left = mWidth/2 - mImage.getWidth()/2;
			rect.right = mWidth/2 + mImage.getWidth()/2;
			rect.top = mHeight/2 + mTextBound.height()/2 - mImage.getWidth()/2;
			rect.bottom = mHeight/2 + mTextBound.height()/2 + mImage.getWidth()/2;
			canvas.drawBitmap(mImage, null, rect, mPaint);
		}

	}

}
