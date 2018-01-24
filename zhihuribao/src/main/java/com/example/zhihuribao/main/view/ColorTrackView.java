package com.example.zhihuribao.main.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;

import com.example.zhihuribao.R;

/**
 * Created by banker_test on 2017/4/20.
 */

public class ColorTrackView extends View {
    public enum Direction {
        LEFT, RIGHT;
    }

    private String mText = "开始绘制";
    private int mTextSize = sp2dp(30);
    private int mTextOriginColor = 0xff0000;
    private int mTextChangeColor = 0xffff00;
    private float mProgress = 0;
    private static final int DIRECTION_LEFT = 0;
    private static final int DIRECTION_RIGHT = 1;
    private int mDirection = DIRECTION_LEFT;
    private Paint mPaint;
    private Rect mTextBound = new Rect();
    private int mTextWidth;
    private int mTextStartX;
    private int realWidth=0;

    public void setDirection(int direction) {
        this.mDirection = direction;
    }

    public ColorTrackView(Context context) {
        this(context, null);
    }

    public ColorTrackView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ColorTrackView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.ColorTrackView);
        mText = ta.getString(R.styleable.ColorTrackView_text);
        mTextSize = ta.getDimensionPixelSize(R.styleable.ColorTrackView_textSize, mTextSize);
        mDirection = ta.getInt(R.styleable.ColorTrackView_direction, mDirection);
        mProgress = ta.getFloat(R.styleable.ColorTrackView_progress, mProgress);
        mTextOriginColor = ta.getColor(R.styleable.ColorTrackView_textOriginColor, mTextOriginColor);
        mTextChangeColor = ta.getColor(R.styleable.ColorTrackView_textChangeColor, mTextChangeColor);
        ta.recycle();
        mPaint.setTextSize(mTextSize);
        measureText();
    }

    private void measureText() {
        mTextWidth = (int) mPaint.measureText(mText);
        mPaint.getTextBounds(mText, 0, mText.length(), mTextBound);
        Log.e("mTextWidth", ":" + mTextWidth);
        Log.e("mTextBoundWidth", ":" + mTextBound.width());
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        realWidth=getMeasuredWidth()-getPaddingLeft()-getPaddingRight();
        mTextStartX=realWidth/2-mTextWidth/2;
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int width = 0;
        int height = 0;
        if (widthMode == MeasureSpec.EXACTLY) {
            width = widthSize;
        } else {
            width = Math.min(widthSize, mTextWidth);
        }

        if (heightMode == MeasureSpec.EXACTLY) {
            height = heightSize;
        } else {
            height = Math.min(mTextBound.height(), heightSize);
        }
        setMeasuredDimension(width + getPaddingLeft() + getPaddingRight(), height + getPaddingTop() + getPaddingBottom());
        Log.e("widthSize","+"+widthSize);
        Log.e("heightSize","+"+heightSize);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (mDirection==DIRECTION_LEFT){
            drawChangeLeft(canvas);
            drawOriginLeft(canvas);
        }else {
            drawChangeRight(canvas);
            drawOriginRight(canvas);
        }
    }

    //设置进度
    public void setProgress(float progress){
        this.mProgress=progress;
        invalidate();
    }

    public float getProgress(){
        return mProgress;
    }

    //sp转dp
    private int sp2dp(float spValue) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, spValue, getResources().getDisplayMetrics());
    }

    //dp转sp
    private int dp2sp(float dpValue) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dpValue, getResources().getDisplayMetrics());
    }

    //绘制文字区域
    private void drawText(Canvas canvas, int color, int startX, int endX) {
        mPaint.setColor(color);
        canvas.save(Canvas.CLIP_SAVE_FLAG);
        canvas.clipRect(startX, 0, endX, getMeasuredHeight());
        canvas.drawText(mText,mTextStartX,getMeasuredHeight()/2+mTextBound.height()/2,mPaint);
        canvas.restore();
    }

    //绘制剪切从右边开始
    private void drawChangeRight(Canvas canvas){
        drawText(canvas,mTextChangeColor, (int) (mTextStartX+(1-mProgress)*mTextWidth),mTextStartX+mTextWidth);
    }

    //绘制原始字符从右边开始
    private void drawOriginRight(Canvas canvas){
        drawText(canvas,mTextOriginColor,mTextStartX, (int) (mTextStartX+(1-mProgress)*mTextWidth));
    }

    //绘制剪切从左边开始
    private void drawChangeLeft(Canvas canvas){
        drawText(canvas,mTextChangeColor,mTextStartX, (int) (mTextStartX+mProgress*mTextWidth));
    }

    //绘制原始字符从左边开始
    private void drawOriginLeft(Canvas canvas){
        drawText(canvas,mTextOriginColor, (int) (mTextStartX+mProgress*mTextWidth),mTextStartX+mTextWidth);
    }
}
