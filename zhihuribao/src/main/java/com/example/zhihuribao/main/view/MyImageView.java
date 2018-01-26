package com.example.zhihuribao.main.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Matrix;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.ScaleGestureDetector.OnScaleGestureListener;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewTreeObserver;
import android.widget.ImageView;

/**
 * @author zhy
 *         博客地址：http://blog.csdn.net/lmj623565791/article/details/39474553
 */
@SuppressLint("AppCompatCustomView")
public class MyImageView extends ImageView implements OnScaleGestureListener, OnTouchListener, ViewTreeObserver.OnGlobalLayoutListener {
    private static final String TAG="MyImageView";
    //整个界面的宽高
    private int mWidth, mHeight;
    //图片的宽高
    private int mDrawableWidth, mDrawableHeight;
    private Drawable drawable;
    private Matrix matrix;
    //缩放值
    private float scale = 1.0f;
    private float mMaxScale, mMinScale;
    private ScaleGestureDetector scaleGestureDetector;
    //当前手指按下时的x，y
    private int curX,curY;
    private int startX,startY;

    private MODE mode=MODE.NONE;
    private enum MODE{
        NONE,DRAG,ZOOM
    }


    public MyImageView(Context context) {
        this(context, null);
    }

    public MyImageView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setOnTouchListener(this);
        scaleGestureDetector = new ScaleGestureDetector(context, this);
        setScaleType(ScaleType.MATRIX);
    }

    //获取当前的缩放值
    private float getScale() {
        float[] floats = new float[9];
        matrix.getValues(floats);
        return floats[Matrix.MSCALE_X];
    }

    //获取图片的rectF
    private RectF getRectF() {
        RectF rectF = new RectF();
        rectF.set(0, 0, mDrawableWidth, mDrawableHeight);
        matrix.mapRect(rectF);
        return rectF;
    }

    //处理手势的缩放
    @Override
    public boolean onScale(ScaleGestureDetector detector) {
        if (drawable == null) {
            return true;
        }
        //获取手势滑动的缩放值
        float scaleFactor = detector.getScaleFactor();
        float scale = getScale();
        Log.e("scaleFactor",":"+scaleFactor);
        float scaleResult = scale * scaleFactor;
        if (scaleResult >= mMaxScale && scaleFactor > 1.0f) {
            scaleFactor = mMaxScale / scale;
        }
        if (scaleResult <= mMinScale && scaleFactor < 1.0f) {
            scaleFactor = mMinScale / scale;
        }
        matrix.postScale(scaleFactor, scaleFactor, mWidth / 2, mHeight / 2);
        RectF rectF = getRectF();
        float dx = 0.0f;
        float dy = 0.0f;
        //图片高度大于界面的高度
        if (rectF.height() >= mHeight) {
            //图片顶部存在空白
            if (rectF.top > 0) {
                dy = -rectF.top;
            }
            //图片底部存在空白
            if (rectF.bottom < mHeight) {
                dy = mHeight - rectF.bottom;
            }
        }
        //图片的宽度大于界面的宽度
        if (rectF.width() > mWidth) {
            //图片左边存在空白
            if (rectF.left > 0) {
                dx = rectF.left;
            }
            //图片的右边存在空白
            if (rectF.right < mWidth) {
                dx = mWidth - rectF.right;
            }
        }
        matrix.postTranslate(dx, dy);
        setImageMatrix(matrix);

        Log.e("scale", "scale");
        return true;
    }

    @Override
    public boolean onScaleBegin(ScaleGestureDetector detector) {
        //手势滑动开启，返回值为true
        return true;
    }

    @Override
    public void onScaleEnd(ScaleGestureDetector detector) {

    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
//        return scaleGestureDetector.onTouchEvent(event);
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                onTouchDown(event);
                break;
            case MotionEvent.ACTION_MOVE:
                onTouchMove(event);
                break;
        }
        return true;
    }

    private void onTouchDown(MotionEvent event){
        curX= (int) event.getRawX();
        curY= (int) event.getRawX();
        startX=curX;
        startY=curY;
    }

    private void onTouchMove(MotionEvent event){
        curX= (int) event.getRawX();
        curY= (int) event.getRawY();
        matrix.postTranslate(curX-startX,curY-startY);
        setImageMatrix(matrix);
        Log.e(TAG, "curX: "+curX+"-----curY:"+curY);
    }


    @Override
    public void onGlobalLayout() {
        mWidth = getWidth();
        mHeight = getHeight();
        drawable = getDrawable();
        if (drawable == null) {
            return;
        }
        mDrawableWidth = drawable.getIntrinsicWidth();
        mDrawableHeight = drawable.getIntrinsicHeight();
        //图片的宽度大于界面宽度，高度小于界面高度
        if (mDrawableWidth > mWidth && mDrawableHeight <= mHeight) {
            scale = mWidth * 1.0f / mDrawableWidth;
        }
        //图片的宽度小于界面宽度，高度大于界面高度
        else if (mDrawableWidth <= mWidth && mDrawableHeight > mHeight) {
            scale = mHeight * 1.0f / mDrawableHeight;
        }
        //图片宽度高度都大于或者宽度和高度都小于
        else {
            scale = Math.min(mWidth * 1.0f / mDrawableWidth, mHeight * 1.0f / mDrawableHeight);
        }
        mMaxScale = 8.0f * scale;
        mMinScale = 0.5f * scale;
        //初始化放置在界面中间
        matrix = new Matrix();
        matrix.postTranslate(mWidth / 2 - mDrawableWidth / 2, mHeight / 2 - mDrawableHeight / 2);
        matrix.postScale(scale, scale, mWidth / 2, mHeight / 2);
        setImageMatrix(matrix);
        Log.e("MyImageView", "mWidth:" + mWidth + " mHeight:" + mHeight + " mDrawableWidth:" + mDrawableWidth + " mDrawableHeight:" + mDrawableHeight);

    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        getViewTreeObserver().addOnGlobalLayoutListener(this);
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        getViewTreeObserver().removeOnGlobalLayoutListener(this);
    }
}
