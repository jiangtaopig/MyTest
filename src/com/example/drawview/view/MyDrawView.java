package com.example.drawview.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Bitmap.Config;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

public class MyDrawView extends View {

	
	private PorterDuffXfermode pdXfermode;   //定义PorterDuffXfermode变量
    //定义MODE常量，等下直接改这里即可进行测试
    private static PorterDuff.Mode PD_MODE = PorterDuff.Mode.DST_IN;
    private int screenW, screenH; //屏幕宽高
    private int width = 200;      //绘制的图片宽高
    private int height = 200;
    private Bitmap srcBitmap, dstBitmap;     //上层SRC的Bitmap和下层Dst的Bitmap

    public MyDrawView(Context context) {
        this(context, null);
    }

    public MyDrawView(Context context, AttributeSet attrs) {
        super(context, attrs);
        screenW = ScreenUtil.getScreenW(context);
        screenH = ScreenUtil.getScreenH(context);
        //创建一个PorterDuffXfermode对象
        pdXfermode = new PorterDuffXfermode(PD_MODE);
        //实例化两个Bitmap
        srcBitmap = makeSrc(width, height);
        dstBitmap = makeDst(width, height);
    }

    public MyDrawView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    //定义一个绘制圆形Bitmap的方法
    private Bitmap makeDst(int w, int h) {
        Bitmap bm = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        Canvas c = new Canvas(bm);
        Paint p = new Paint(Paint.ANTI_ALIAS_FLAG);
        p.setColor(0xFF26AAD1);
        c.drawOval(new RectF(0, 0, w * 3 / 4, h * 3 / 4), p);
        return bm;
    }

    //定义一个绘制矩形的Bitmap的方法
    private Bitmap makeSrc(int w, int h) {
        Bitmap bm = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        Canvas c = new Canvas(bm);
        Paint p = new Paint(Paint.ANTI_ALIAS_FLAG);
        p.setColor(0xFFFFCE43);
        c.drawRect(w / 3, h / 3, w * 19 / 20, h * 19 / 20, p);
        return bm;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        Paint paint = new Paint();
        paint.setFilterBitmap(false);
        paint.setStyle(Paint.Style.FILL);
        canvas.drawBitmap(srcBitmap, (screenW / 3 - width) / 2, (screenH / 2 - height) / 2, paint);
        canvas.drawBitmap(dstBitmap, (screenW / 3 - width) / 2 + screenW / 3, (screenH / 2 - height) / 2, paint);

//        //创建一个图层，在图层上演示图形混合后的效果
//        int sc = canvas.saveLayer(0, 0, screenW, screenH, null, Canvas.MATRIX_SAVE_FLAG |
//                Canvas.CLIP_SAVE_FLAG |
//                Canvas.HAS_ALPHA_LAYER_SAVE_FLAG |
//                Canvas.FULL_COLOR_LAYER_SAVE_FLAG |
//                Canvas.CLIP_TO_LAYER_SAVE_FLAG);
//
        //paint.setAlpha(0);
        canvas.drawBitmap(dstBitmap, (screenW / 3 - width) / 2 + screenW / 3 * 2,
                (screenH / 2 - height) / 2, paint);     //绘制i
        //设置Paint的Xfermode
        paint.setXfermode(pdXfermode);
        
        canvas.drawBitmap(srcBitmap, (screenW / 3 - width) / 2 + screenW / 3 * 2,
                (screenH / 2 - height) / 2, paint);
        paint.setXfermode(null);
        // 还原画布
       // canvas.restoreToCount(sc);
    }
	
//	private Bitmap srcBitmap, desBitmap;
//
//	private Paint paint;
//	public MyDrawView(Context context) {
//		this(context, null);
//	}
//
//	public MyDrawView(Context context, AttributeSet attrs) {
//		this(context, attrs, 0);
//	}
//
//	public MyDrawView(Context context, AttributeSet attrs, int defStyle) {
//		super(context, attrs, defStyle);
//		// TODO Auto-generated constructor stub
//
//		paint = new Paint();
//	}
//
//	public Bitmap drawCircle() {
//		Bitmap bm = Bitmap.createBitmap(getMeasuredWidth(),
//				getMeasuredHeight(), Config.ARGB_8888);
//		Canvas canvas = new Canvas(bm);
//		Paint paint = new Paint();
//		paint.setColor(Color.RED);
//		canvas.drawCircle(100, 100, 50, paint);
//		return bm;
//	}
//
//	public Bitmap drawRect() {
//		Bitmap bm = Bitmap.createBitmap(getMeasuredWidth(),
//				getMeasuredHeight(), Config.ARGB_8888);
//		Canvas canvas = new Canvas(bm);
//		Paint paint = new Paint();
//		paint.setColor(Color.BLUE);
//		canvas.drawRect(0, 0, 200, 100, paint);
//		return bm;
//	}
//
//	@Override
//	protected void onDraw(Canvas canvas) {
//		// TODO Auto-generated method stub
//		super.onDraw(canvas);
//
//		srcBitmap = drawRect();
//		desBitmap = drawCircle();
//
//		paint.setFilterBitmap(false);
//		paint.setStyle(Paint.Style.FILL);
//
//		canvas.drawBitmap(srcBitmap, 0, 0, paint);
//		paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_IN));
//		canvas.drawBitmap(desBitmap, 0, 0, paint);
//	}

}
