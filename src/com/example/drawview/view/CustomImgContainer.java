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
		 * ��ô�ViewGroup�ϼ�����Ϊ���Ƽ��Ŀ�͸ߣ��Լ�����ģʽ
		 */
		int specWidthMode = MeasureSpec.getMode(widthMeasureSpec);
		int specWidthSize = MeasureSpec.getSize(widthMeasureSpec);
		int specHeightMode = MeasureSpec.getMode(heightMeasureSpec);
		int specHeightSize = MeasureSpec.getSize(heightMeasureSpec);

		// ��������е�childView�Ŀ�͸�
		measureChildren(widthMeasureSpec, heightMeasureSpec);
		/**
		 * ��¼�����wrap_content�����õĿ�͸�
		 */
		int width = 0;
		int height = 0;

		int childCount = getChildCount();
		int childWidth = 0;
		int childHeight = 0;

		MarginLayoutParams childParams = null;

		// ���ڼ����������childView�ĸ߶�
		int lHeight = 0;
		// ���ڼ����ұ�����childView�ĸ߶ȣ����ո߶�ȡ����֮���ֵ
		int rHeight = 0;

		// ���ڼ����ϱ�����childView�Ŀ��
		int tWidth = 0;
		// ���ڼ�����������childiew�Ŀ�ȣ����տ��ȡ����֮���ֵ
		int bWidth = 0;

		/**
		 * ����childView����ĳ��Ŀ�͸ߣ��Լ����õ�margin���������Ŀ�͸ߣ���Ҫ����������warp_contentʱ
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
		 * �����wrap_content����Ϊ���Ǽ����ֵ ����ֱ������Ϊ�����������ֵ
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
		 * ��������childView�������͸ߣ��Լ�margin���в���
		 */
		for (int i = 0; i < childCount; i++) {
			
			View childView = getChildAt(i);
			//��ȡ�ӿؼ��Ŀ�͸�
			childWidth = childView.getMeasuredWidth();
			childHeight = childView.getMeasuredHeight();
			childParams = (MarginLayoutParams) childView.getLayoutParams();
			int cl = 0, ct = 0, cr = 0, cb = 0;

			Log.e(TAG, " childWidth = " + childWidth + " , childHeight = "
					+ childHeight + " , getWidth() = " + getWidth()
					+ " , getHeight() = " + getHeight());//getWidth/getHeight �ǻ�ȡ���ؼ��Ŀ�͸�

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
