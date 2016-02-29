package com.example.drawview.view;

import com.example.drawview.R;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;

public class CustomTitleView extends View {

	private String mTitle;
	private int mTitleSize;
	private int mTitleColor;

	/**
	 * ����ʱ�����ı����Ƶķ�Χ
	 */
	private Rect mBound;
	private Rect mBounds;
	private Paint mPaint;

	public CustomTitleView(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public CustomTitleView(Context context) {
		this(context, null);
	}

	public CustomTitleView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		// TODO Auto-generated constructor stub

		/**
		 * ���������������Զ�����ʽ����
		 */
		TypedArray a = context.getTheme().obtainStyledAttributes(attrs,
				R.styleable.CustomTitleView, defStyle, 0);
		int n = a.getIndexCount();
		for (int i = 0; i < n; i++) {
			int attr = a.getIndex(i);
			switch (attr) {
			case R.styleable.CustomTitleView_titleText:
				mTitle = a.getString(attr);
				break;
			case R.styleable.CustomTitleView_titleTextColor:
				// Ĭ����ɫ����Ϊ��ɫ
				mTitleColor = a.getColor(attr, Color.BLACK);
				break;
			case R.styleable.CustomTitleView_titleTextSize:
				// Ĭ������Ϊ16sp��TypeValueҲ���԰�spת��Ϊpx
				mTitleSize = a.getDimensionPixelSize(attr, (int) TypedValue
						.applyDimension(TypedValue.COMPLEX_UNIT_SP, 16,
								getResources().getDisplayMetrics()));
				break;

			}

		}
		a.recycle();

		/**
		 * ��û����ı��Ŀ�͸�
		 */
		mPaint = new Paint();
		mPaint.setTextSize(mTitleSize);
		// mPaint.setColor(mTitleTextColor);
		mBound = new Rect();
		mBounds = new Rect();
		//������������Ŀ�Ⱥ͸߶�
		mPaint.getTextBounds(mTitle, 0, mTitle.length(), mBound);

	}

	@Override
	protected void onDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		super.onDraw(canvas);
		mPaint.setColor(Color.YELLOW);
		//��ȡ�ؼ��Ŀ�� android:layout_width="200dp",���android:layout_width="wrap_content" , getMeasuredWidth() = ������ռ�Ŀ�ȼ��� paddingֵ
		int wid = getMeasuredWidth();// ��ȡ�ؼ��Ŀ�� android:layout_width="200dp"
		int hei = getMeasuredHeight(); // ��ȡ�ؼ��ĸ߶� layout_height="100dp"

		int w1 = getWidth() / 2;
		int h1 = getHeight() / 2;

		int w2 = mBound.width() / 2;// mBound.width() �����ֵĿ��
		int h2 = mBound.height() / 2;// mBound.height() �����ֵĸ߶�

		Log.d("...", "wid = " + wid + " , hei = " + hei);
		Log.d("...//...", "w1 = " + w1 + " , h1 = " + h1);
		Log.d("...//...", "w2 = " + w2 + " , h2 = " + h2);
		
		canvas.drawRect(0, 0, getMeasuredWidth(), getMeasuredHeight(), mPaint);

		Log.d("ccc", " mBound.top = " + mBound.top + " , mBound.bottom = "
				+ mBound.bottom + " , mBound.left = " + mBound.left
				+ " , mBound.right = " + mBound.right);

		mPaint.setColor(mTitleColor);
		//mPaint.setTextAlign(Paint.Align.CENTER);
		
		canvas.drawText(mTitle, getWidth() / 2 - mBound.width() / 2,
				getHeight() / 2 + mBound.height() / 2, mPaint);
		// canvas.drawText(mTitle, 40, 50, mPaint);
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		// TODO Auto-generated method stub
		int widthMode = MeasureSpec.getMode(widthMeasureSpec);
		int widthSize = MeasureSpec.getSize(widthMeasureSpec);
		int heightMode = MeasureSpec.getMode(heightMeasureSpec);
		int heightSize = MeasureSpec.getSize(heightMeasureSpec);

		int width, height;
		
		Log.d("zjt ", " , widthSize = "+widthSize+" , heightSize = "+heightSize);
		Log.d("zjt ", " , getPaddingLeft = "+getPaddingLeft()+" , getPaddingRight = "+getPaddingRight());

		if (widthMode == MeasureSpec.EXACTLY) {
			width = widthSize;
		} else {
			mPaint.setTextSize(mTitleSize);
			mPaint.getTextBounds(mTitle, 0, mTitle.length(), mBounds);
			float textWidth = mBounds.width();
			Log.d("zjt ", " , textWidth = "+textWidth);
			int desired = (int) (getPaddingLeft() + textWidth + getPaddingRight());
			width = desired;
		}

		if (heightMode == MeasureSpec.EXACTLY) {
			height = heightSize;
		} else {
			mPaint.setTextSize(mTitleSize);
			mPaint.getTextBounds(mTitle, 0, mTitle.length(), mBounds);
			float textHeight = mBounds.height();
			Log.d("zjt ", " , textHeight = "+textHeight);
			int desired = (int) (getPaddingTop() + textHeight + getPaddingBottom());
			height = desired;
		}

		setMeasuredDimension(width, height);

	}

}
