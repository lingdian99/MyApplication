package com.example.zhihuribao.main.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.ImageView;

/**
 * Created by banker_test on 2017/6/8.
 */

public class RoundImageView extends ImageView{
    private static final String TAG = "RoundImageView";
    private Paint mPaint;

    public RoundImageView(Context context) {
        this(context,null);
    }

    public RoundImageView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public RoundImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mPaint=new Paint();
    }

    @Override
    protected void onDraw(Canvas canvas) {

        Drawable drawable=getDrawable();
            Bitmap bitmap= Bitmap.createBitmap(drawable.getIntrinsicWidth(),drawable.getIntrinsicHeight(), Bitmap.Config.ARGB_4444);
            Bitmap circleBitmap=getCircleBitmap(bitmap);
            Rect srcRect=new Rect(0,0,500,500);
            Rect desRect=new Rect(0,0,500,500);
            mPaint.reset();
            canvas.drawBitmap(bitmap,srcRect,desRect,mPaint);

//            super.onDraw(canvas);

    }

    private Bitmap getCircleBitmap(Bitmap bitmap){
        Bitmap rBitmap=Bitmap.createBitmap(bitmap.getWidth(),bitmap.getHeight(), Bitmap.Config.ARGB_4444);
        Canvas canvas=new Canvas(rBitmap);
        Rect rect=new Rect(0,0,bitmap.getWidth(),bitmap.getHeight());
        mPaint.setColor(0x55000000);
        mPaint.setAntiAlias(true);
        int x=bitmap.getWidth();
//        canvas.drawCircle(x/2,x/2,x/2,mPaint);
        mPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(rBitmap,rect,rect,mPaint);
        Log.e(TAG, "getCircleBitmap: "+x);
        return rBitmap;
    }
}
