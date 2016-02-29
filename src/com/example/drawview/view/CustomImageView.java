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
	 * ��ʼ�������Զ�������
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
		// ����ؼ����ı��Ŀ�͸�
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
			// ��ͼƬ�����Ŀ��
			int desireByImg = getPaddingLeft() + mImage.getWidth()
					+ getPaddingRight();
			// �����־����Ŀ��
			int desireByText = getPaddingLeft() + mTextBound.width()
					+ getPaddingRight();
			if (specMode == MeasureSpec.AT_MOST) {// wrap_content
				int desire = Math.max(desireByImg, desireByText);
				// ����MeasureSpec.AT_MOSTģʽ�£���Ȳ��ܳ������ؼ�������ֵspecSize
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
				Log.e(TAG, " , AT_MOST , xxxx mHeight = " + mHeight + "��,��mImage.getHeight()������"+mImage.getHeight());
			}else{//һ���� scrollview���õõ� 
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
		 * ���Ʊ߿�
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
		 * ��ǰ���õĿ��С��������Ҫ�Ŀ�ȣ��������Ϊxxx...
		 */
		
		//���ַ���ͼƬ����
		
		/*
		if (mTextBound.width() > mWidth) {
			TextPaint textPaint = new TextPaint(mPaint);
			String msg = TextUtils.ellipsize(mTitle, textPaint,
					(float) mWidth - getPaddingLeft() - getPaddingRight(),
					TextUtils.TruncateAt.END).toString();
			canvas.drawText(msg, getPaddingLeft(), mHeight
					- getPaddingBottom(), mPaint);
		} else {// ��������£��������
			canvas.drawText(mTitle, mWidth / 2 - mTextBound.width() / 2,
					mHeight - getPaddingBottom(), mPaint);
		}

		// ȡ��ʹ�õ��Ŀ飨����ȥ������ռ�Ŀ�ȣ�
		rect.bottom -= mTextBound.height();

		if (imageScaleType == IMAGE_SCALE_FITXY) {
			canvas.drawBitmap(mImage, null, rect, mPaint);
		} else {
			// ������еľ��η�Χ
			rect.left = mWidth / 2 - mImage.getWidth() / 2;
			rect.right = mWidth / 2 + mImage.getWidth() / 2;
			rect.top = (mHeight - mTextBound.height()) / 2 - mImage.getHeight()
					/ 2;
//			rect.bottom = (mHeight - mTextBound.height()) / 2
//					+ mImage.getHeight() / 2;
			
			rect.bottom = rect.top + mImage.getHeight();//����rect.top + ͼƬ�ĸ߶�

			canvas.drawBitmap(mImage, null, rect, mPaint);
		}*/
		
		//���ַ���ͼƬ�Ϸ�
		
		if (mTextBound.width() > mWidth) {
			TextPaint textPaint = new TextPaint(mPaint);
			String msg = TextUtils.ellipsize(mTitle, textPaint,
					(float) mWidth - getPaddingLeft() - getPaddingRight(),
					TextUtils.TruncateAt.END).toString();
			canvas.drawText(msg, getPaddingLeft(), getPaddingTop()+mTextBound.height()
					, mPaint);
		} else {// ��������£��������
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
