package com.example.drawview.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

public class CustomImgContainer extends ViewGroup {

	private static final String TAG = "CustomImgContainer";

	public CustomImgContainer(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	public CustomImgContainer(Context context) {
		super(context);
	}

	public CustomImgContainer(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	@Override
	public LayoutParams generateLayoutParams(AttributeSet attrs) {
		// TODO Auto-generated method stub
		return new MarginLayoutParams(getContext(), attrs);
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		// TODO Auto-generated method stub
		// super.onMeasure(widthMeasureSpec, heightMeasureSpec);

		/**
		 * 获得此ViewGroup上级容器为其推荐的宽和高，以及计算模式
		 */
		int specWidthMode = MeasureSpec.getMode(widthMeasureSpec);
		int specWidthSize = MeasureSpec.getSize(widthMeasureSpec);
		int specHeightMode = MeasureSpec.getMode(heightMeasureSpec);
		int specHeightSize = MeasureSpec.getSize(heightMeasureSpec);

		// 计算出所有的childView的宽和高
		measureChildren(widthMeasureSpec, heightMeasureSpec);
		/**
		 * 记录如果是wrap_content是设置的宽和高
		 */
		int width = 0;
		int height = 0;

		int childCount = getChildCount();
		int childWidth = 0;
		int childHeight = 0;

		MarginLayoutParams childParams = null;

		// 用于计算左边两个childView的高度
		int lHeight = 0;
		// 用于计算右边两个childView的高度，最终高度取二者之间大值
		int rHeight = 0;

		// 用于计算上边两个childView的宽度
		int tWidth = 0;
		// 用于计算下面两个childiew的宽度，最终宽度取二者之间大值
		int bWidth = 0;

		/**
		 * 根据childView计算的出的宽和高，以及设置的margin计算容器的宽和高，主要用于容器是warp_content时
		 */

		for (int i = 0; i < childCount; i++) {
			View childView = getChildAt(i);
			childWidth = childView.getMeasuredWidth();
			childHeight = childView.getMeasuredHeight();
			childParams = (MarginLayoutParams) childView.getLayoutParams();

			Log.d(TAG, " , .. childWidth = " + childWidth + " , childHeight = "
					+ childHeight + " , leftMargin = " + childParams.leftMargin);

			if (i == 0 || i == 1) {
				tWidth += childWidth + childParams.leftMargin
						+ childParams.rightMargin;
				Log.d(TAG, " , tWidth = " + tWidth);
			}

			if (i == 2 || i == 3) {
				bWidth += childWidth + childParams.leftMargin
						+ childParams.rightMargin;
				Log.d(TAG, " , bWidth = " + bWidth);
			}

			if (i == 0 || i == 2) {
				lHeight += childHeight + childParams.topMargin
						+ childParams.bottomMargin;
				Log.d(TAG, " , lHeight = " + lHeight);
			}

			if (i == 1 || i == 3) {
				rHeight += childHeight + childParams.topMargin
						+ childParams.bottomMargin;
				Log.d(TAG, " , rHeight = " + rHeight);
			}
		}

		width = Math.max(tWidth, bWidth);
		height = Math.max(lHeight, rHeight);

		/**
		 * 如果是wrap_content设置为我们计算的值 否则：直接设置为父容器计算的值
		 */
		setMeasuredDimension(
				(specWidthMode == MeasureSpec.EXACTLY) ? specWidthSize : width,
				(specHeightMode == MeasureSpec.EXACTLY) ? specHeightSize
						: height);

	}

	@Override
	protected void onLayout(boolean changed, int l, int t, int r, int b) {
		// TODO Auto-generated method stub

		int childCount = getChildCount();
		int childWidth = 0;
		int childHeight = 0;
		MarginLayoutParams childParams = null;

		Log.d(TAG, " ww = "+getMeasuredWidth()+ ", hh = "+getMeasuredHeight());
		
		/**
		 * 遍历所有childView根据其宽和高，以及margin进行布局
		 */
		for (int i = 0; i < childCount; i++) {
			
			View childView = getChildAt(i);
			//获取子控件的宽和高
			childWidth = childView.getMeasuredWidth();
			childHeight = childView.getMeasuredHeight();
			childParams = (MarginLayoutParams) childView.getLayoutParams();
			int cl = 0, ct = 0, cr = 0, cb = 0;

			Log.e(TAG, " childWidth = " + childWidth + " , childHeight = "
					+ childHeight + " , getWidth() = " + getWidth()
					+ " , getHeight() = " + getHeight());//getWidth/getHeight 是获取父控件的宽和高

			switch (i) {
			case 0:
				cl = childParams.leftMargin;
				ct = childParams.topMargin;
				break;
			case 1:
				cl = getWidth() - childWidth - childParams.leftMargin
						- childParams.rightMargin;
				ct = childParams.topMargin;

				break;
			case 2:
				cl = childParams.leftMargin;
				ct = getHeight() - childHeight - childParams.bottomMargin;
				break;
			case 3:
				cl = getWidth() - childWidth - childParams.leftMargin
						- childParams.rightMargin;
				ct = getHeight() - childHeight - childParams.bottomMargin;
				break;

				
				
			}
			
			cr = cl + childWidth;
			cb = childHeight + ct;
			Log.d(TAG, " i = " + i + " , cl = "+cl+" , ct = "+ct+" , cr = "+cr+" , cb = "+cb);
			childView.layout(cl, ct, cr, cb);
		}

	}

}
