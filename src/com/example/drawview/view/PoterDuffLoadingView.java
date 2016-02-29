
package com.example.drawview.view;

import com.example.drawview.R;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.Paint.Cap;
import android.graphics.Paint.Join;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.graphics.Path.Direction;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.util.Log;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;


public class PoterDuffLoadingView extends View {

    private Resources mResources;
    private Paint mBitPaint;
    private Bitmap mBitmap;

    private int mTotalWidth, mTotalHeight;
    private int mBitWidth, mBitHeight;
    private Rect mSrcRect, mDestRect;
    private PorterDuffXfermode mXfermode;

    private Rect mDynamicRect;
    private int mCurrentTop;
    private int mStart, mEnd;
    
    private Bitmap girlBitmap;
    private int girlBitWidth , girlBitHeight;
    private Rect girlSrcRect , girlDesRect;
    
    private Paint mPathPaint;
    private Canvas mCanvas;
    private Bitmap mBitmap2;
    private Path mPath;
    
    private int mLastX , mLastY;

    public PoterDuffLoadingView(Context context) {
        super(context);
        mResources = getResources();
        initBitmap();
        initPaint();
        initXfermode();
    }

    private void initXfermode() {
        // 叠加处绘制源图
        mXfermode = new PorterDuffXfermode(Mode.SRC_IN);
    }

    private void initPaint() {
        // 初始化paint
        mBitPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
       // mBitPaint.setFilterBitmap(true);
       // mBitPaint.setDither(true);
        mBitPaint.setColor(Color.RED);
        
        mPathPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPathPaint.setColor(Color.BLUE);
        mPathPaint.setStyle(Style.STROKE);
        mPathPaint.setStrokeJoin(Join.ROUND);
        mPathPaint.setStrokeCap(Cap.ROUND);
        mPathPaint.setStrokeWidth(20);
        
    }

    
    private void  drawPath(){
    	mPathPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_OUT));
    	mCanvas.drawPath(mPath, mPathPaint);
    }
    
    private void initBitmap() {
        // 初始化bitmap
        mBitmap = ((BitmapDrawable) mResources.getDrawable(R.drawable.ga_studio))
                .getBitmap();
        mBitWidth = mBitmap.getWidth();
        mBitHeight = mBitmap.getHeight();
        //美女图片的宽和高
        girlBitmap = ((BitmapDrawable)mResources.getDrawable(R.drawable.a1)).getBitmap();
        girlBitWidth = girlBitmap.getWidth();
        girlBitHeight = girlBitmap.getHeight();
        
        mPath = new Path();
        
    }

    
   
    
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Log.d("yyyyyyy", "enter onDraw , mCurrentTop = "+mCurrentTop);
        // 存为新图层
       // changeColor(canvas);
       // drawBitmap(canvas);
        //mPath.addCircle(100, 100, 50, Direction.CW);
        //mPath.addRect(new RectF(75, 0, 125, 50), Direction.CW);
       // drawPath(canvas);
        
      //  canvas.drawBitmap(girlBitmap, 0, 0, null);
       // canvas.drawRect(girlDesRect, null);
        
       // testGuaGuaKa(canvas);
        
        test();
        canvas.drawBitmap(mBitmap2, 0, 0, null);
       
    }

    /**
     * 测试刮刮卡
     * @param canvas
     */
	private void testGuaGuaKa(Canvas canvas) {
		canvas.drawBitmap(girlBitmap, 0, 0, null);
        drawPath();
        canvas.drawBitmap(mBitmap2, 0, 0, null);
	}
    
    /**
     * 测试各种mode， 如： DST_IN, DST_OUT
     */
    public void testXfermode(){
    	
    }
    
    
    public void test(){
    	
    	Rect rc = new Rect();
    	rc.top = 0;
    	rc.left = 0;
    	rc.right = 400;
    	rc.bottom = 400;
    	
    	Paint pa = new Paint();
    	pa.setColor(Color.RED);
    	//pa.setStyle(Style.STROKE);
    	//pa.setStrokeWidth(5);
    	
    	mCanvas.drawRect(rc, pa);
    	pa.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_OUT));
    	pa.setColor(Color.BLUE);
    	//mCanvas.drawBitmap(girlBitmap, 0, 0, pa);
    	mCanvas.drawCircle(200, 200, 100, pa);
    	
    }

    /**
     * path 练习
     * @param canvas
     */
	private void drawPath(Canvas canvas) {
		Paint paint = new Paint();
        paint.setStyle(Style.STROKE);  
        paint.setStrokeWidth(2);  
        paint.setColor(Color.RED);
        
        RectF rect = new RectF();
        rect.left = 0;
        rect.right = 400;
        rect.top = 200;
        rect.bottom = 400;
        
//        mPath.lineTo(100, 100);
//        mPath.rMoveTo(50, 50);
//        mPath.lineTo(200, 200);
       // canvas.drawPath(mPath, paint);
        
        /**
         * 参数1：绘制曲线所在的矩形区域
         * 参数2：绘制曲线的起始角度
         * 参数3：指的是旋转的度数，如果该参数是整数就表示顺时针旋转，否则逆时针旋转
         */
//        mPath.arcTo(rect,270, 90);
//        mPath.close();
        
        mPath.moveTo(200, 90);
       // mPath.quadTo(100, 50, 200, 200);
       // mPath.quadTo(300, 50, 200, 90);
        mPath.quadTo(300, 50, 200, 200);
        mPath.quadTo(100, 50, 200, 90);
        
        canvas.drawPath(mPath, paint);
       // canvas.drawRect(rect, paint);
        
        
	}
    
	private void changeColor(Canvas canvas) {
		int saveLayerCount = canvas.saveLayer(0, 0, mTotalWidth, mTotalHeight, mBitPaint,
                Canvas.ALL_SAVE_FLAG);
        // 绘制目标图
        canvas.drawBitmap(mBitmap, mSrcRect, mDestRect, mBitPaint);
        // 设置混合模式
        mBitPaint.setXfermode(mXfermode);
        // 绘制源图形
        canvas.drawRect(mDynamicRect, mBitPaint);
        // 清除混合模式
        mBitPaint.setXfermode(null);
        // 恢复保存的图层；
        canvas.restoreToCount(saveLayerCount);

        // 改变Rect区域，真实情况下时提供接口传入进度，计算高度
        mCurrentTop -= 8;
        if (mCurrentTop <= mEnd) {
            mCurrentTop = mStart;
            
        }else if (mCurrentTop >= 48){
        	mDynamicRect.top = mCurrentTop;
            postInvalidate();
        }
	}
	
	
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		// TODO Auto-generated method stub
		Log.d("............", "enter onTouchEvent");
		int action = event.getAction();
		int x = (int) event.getX();
		int y = (int) event.getY();
		
		switch (action) {
		case MotionEvent.ACTION_DOWN:
			mLastX = x;
			mLastY = y;
			mPath.moveTo(mLastX, mLastY);
			break;
		case MotionEvent.ACTION_MOVE:
			Log.d("MotionEvent.ACTION_MOVE", " , mLastX = "+mLastX+" , mLastY = "+mLastY+" , x = "+x+" , y = "+y);
			int diffX = Math.abs(x - mLastX);
			int diffY = Math.abs(y - mLastY);
			
			if(diffX > 3 || diffY > 3){
				mPath.lineTo(x, y);
			}
			
			mLastX = x;
			mLastY = y;
			
			break;

		default:
			break;
		}
		
		invalidate();
		return true;
		
	}

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        
        mTotalWidth = getMeasuredWidth();
        mTotalHeight = getMeasuredHeight();
        
        mBitmap2 = Bitmap.createBitmap(mTotalWidth, mTotalHeight, Config.ARGB_8888);
    	mCanvas = new Canvas(mBitmap2);
        
        //mCanvas.drawColor(Color.parseColor("#c0c0c0"));
        
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
    	Log.d("xxxxxx", "onSizeChanged , w = "+w+" , h = "+h+" , mBitWidth = "+mBitWidth+" , mBitHeight = "+mBitHeight);
        super.onSizeChanged(w, h, oldw, oldh);
        mTotalWidth = w;
        mTotalHeight = h;
        mSrcRect = new Rect(0, 0, mBitWidth, mBitHeight);
        // 让左边和上边有些距离
        int left = (int) TypedValue.complexToDimension(20, mResources.getDisplayMetrics());
        mDestRect = new Rect(left, left, left + mBitWidth, left + mBitHeight);
        mStart = left + mBitHeight;
        mCurrentTop = mStart;
        mEnd = left;
        mDynamicRect = new Rect(left, mStart, left + mBitWidth, left + mBitHeight);
        
        
        girlSrcRect = new Rect(0, 0, girlBitWidth, girlBitHeight);
        girlDesRect = new Rect(20, 50, 20+girlBitWidth, 50+girlBitHeight);
        
    }
}
