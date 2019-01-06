package com.example.menglk.myapplication.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;


public class CustomView extends View {

    private int circleXY; //圆点 x坐标 y坐标
    private float mRadius;
    private RectF rectF;
    private Paint mPaint1;
    private Paint mPaint2;
    private Paint mPaint3;
    private int width;
    private int height;
    private float paintWith = 20f;
    private String mShowText = "Android skill";
    private int mTextSize = 14;

    public CustomView(Context context) {
        super(context);
    }

    public CustomView(Context context,AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomView(Context context,AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
//        setMeasuredDimension(getMeasuredWidth(widthMeasureSpec),getMeasuredHeight(heightMeasureSpec));
        initView();
    }

    private int getMeasuredHeight(int heightMeasureSpec) {
        int height;

        int mode = MeasureSpec.getMode(heightMeasureSpec);
        int size = MeasureSpec.getSize(heightMeasureSpec);
        if(mode == MeasureSpec.EXACTLY){

            height = size;
        }else{
            height = 300;
            if(mode == MeasureSpec.AT_MOST){
              height = Math.min(height,size);
            }
        }

        return height;
    }

    private int getMeasuredWidth(int widthMeasureSpec) {
        int width;
        int mode = MeasureSpec.getMode(widthMeasureSpec);
        int size = MeasureSpec.getSize(widthMeasureSpec);
        if(mode == MeasureSpec.EXACTLY){
           width = size;
        }else{
            width = 300;
            if(mode == MeasureSpec.AT_MOST){
                width = Math.min(width,size);
            }
        }
        return width;
    }

    private void initView() {
        circleXY = getMeasuredWidth() /2;
        mRadius = getMeasuredWidth() * 0.5f/2;
        rectF = new RectF(getMeasuredWidth() * 0.1f, getMeasuredWidth() * 0.1f,getMeasuredWidth() * 0.9f,getMeasuredWidth() * 0.9f);
        rectF = new RectF(paintWith/2, paintWith/2,getMeasuredWidth() - paintWith/2,getMeasuredHeight() - paintWith/2);
        mPaint1 = new Paint();
        mPaint1.setColor(Color.RED);
        mPaint1.setStrokeWidth(2);
//        mPaint1.setStyle(Paint.Style.STROKE);

        mPaint2 = new Paint();
        mPaint2.setColor(Color.BLUE);

        mPaint2.setStrokeWidth(paintWith);
//        mPaint2.setStyle(Paint.Style.FILL);//设置画笔类型为填充
        mPaint2.setStyle(Paint.Style.STROKE);//设置画笔不填充


         mPaint3 = new Paint();
         mPaint3.setColor(Color.BLACK);
         mPaint3.setTextSize(mTextSize);
         mPaint3.setTextAlign(Paint.Align.CENTER);//设置文字居中显示

//         mPaint3.setStyle(Paint.Style.FILL);//设置画笔类型为填充
         mPaint3.setStyle(Paint.Style.STROKE);//设置画笔不填充
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //绘制圆
        //参数1圆心的x坐标  参数2圆心的y坐标  参数3半径 参数4画笔对象                                  c
        canvas.drawCircle(circleXY,circleXY,mRadius,mPaint1);


        //绘制弧线
        //参数1需要绘制椭圆的外接矩形  参数2开始角度  参数3扫过到的角度  参数4圆弧是否连接圆心  参数5画笔对象
        canvas.drawArc(rectF,90,270,false,mPaint2);


        //绘制文字
//        canvas.drawText(mShowText,0,mShowText.length(),circleXY,circleXY + (mTextSize / 4),mPaint3);
//        canvas.drawText(mShowText,0,mShowText.length(),circleXY,circleXY,mPaint3);

        //绘制点
        canvas.drawPoint(circleXY,circleXY,mPaint2);



        //计算baseline
        Paint.FontMetrics fontMetrics= mPaint3.getFontMetrics();
        float distance=(fontMetrics.bottom - fontMetrics.top)/2 - fontMetrics.bottom;
        float baseline=rectF.centerY()+distance;
        //根据基准线绘制文字  并居中
        canvas.drawText(mShowText, rectF.centerX(), baseline, mPaint3);
    }
}
