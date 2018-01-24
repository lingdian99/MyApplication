package com.example.zhihuribao.main.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import java.util.ArrayList;

/**
 * Created by banker_test on 2017/6/15.
 */

public class PickerView extends View{
    private ArrayList<String> dataList=new ArrayList<>();
    private Paint textPaint;
    //文字最大宽度
    private float maxTextWidth=40;
    //文字颜色
    private int textColor= Color.BLUE;
    private Paint.FontMetrics fm;
    private int textSize=50;
    //当前选中项
    private int curIndex=0;
    //中心的x，y
    private int cx,cy;
    //内容的宽高
    private int contentWidth,contentHeight;
    //最多显示几个字符
    private int maxShowNum=3;
    //字符高度
    private int textHeight;
    //字符间隔
    private int textPadding=20;
    private float textMaxScale=2.0f;
    private float textMinAlpha=0.4f;

    public PickerView(Context context) {
        this(context,null);
    }

    public PickerView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public PickerView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        textPaint=new Paint();
        textPaint.setAntiAlias(true);
        textPaint.setColor(textColor);
        textPaint.setTextSize(textSize);
        fm=textPaint.getFontMetrics();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int width=MeasureSpec.getSize(widthMeasureSpec);
        int height=MeasureSpec.getSize(heightMeasureSpec);
        cx=width/2;
        cy=height/2;
        contentWidth= (int) (maxTextWidth*textMaxScale+getPaddingLeft()+getPaddingRight());
        textHeight= (int) (fm.bottom-fm.top);
        contentHeight=(textHeight+textPadding)*maxShowNum;
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (null!=dataList&&dataList.size()>0){
            canvas.clipRect(cx-contentWidth,cy-contentHeight,cx+contentWidth,cy+contentHeight);
        }
        int size=dataList.size();
        int centerPadding=textHeight+textPadding;
        int half=maxShowNum/2+1;
        for (int i=-half;i<=half;i++){
            int index=curIndex+i;
            if (index>=0&&index<size){
                //计算每个字中间的y坐标
                int tempY=cy+i*centerPadding;
                //根据每个字到cy的距离计算scale
                float scale=1.0f - (1.0f * Math.abs(tempY - cy) / centerPadding);
                // 根据textMaxScale，计算出tempScale值，即实际text应该放大的倍数，范围 1~textMaxScale
                float tempScale = scale * (textMaxScale - 1.0f) + 1.0f;
                tempScale = tempScale < 1.0f ? 1.0f : tempScale;

                // 计算文字alpha值
                float textAlpha = textMinAlpha;
                if (textMaxScale != 1) {
                    float tempAlpha = (tempScale - 1) / (textMaxScale - 1);
                    textAlpha = (1 - textMinAlpha) * tempAlpha + textMinAlpha;
                }

                textPaint.setTextSize(textSize * tempScale);
                textPaint.setAlpha((int) (255 * textAlpha));

                // 绘制
                Paint.FontMetrics tempFm = textPaint.getFontMetrics();
                String text = dataList.get(index);
                float textWidth = textPaint.measureText(text);
                canvas.drawText(text, cx - textWidth / 2, tempY - (tempFm.ascent + tempFm.descent) / 2, textPaint);
            }
        }
    }

    public void setDataList(ArrayList<String>  dataList){
        this.dataList.clear();
        this.dataList.addAll(dataList);
        int size=dataList.size();
        for (int i=0;i<size;i++){
            float tempWidth=textPaint.measureText(dataList.get(i));
            if (tempWidth>maxTextWidth){
                maxTextWidth=tempWidth;
            }
        }
        curIndex=0;
        requestLayout();
        invalidate();

    }
}
