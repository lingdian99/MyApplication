package view;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;

/**
 * Created by wys10 on 17/11/30.
 */

public class BottomView extends FrameLayout{
    //每个view占的宽
    private int childWidth;
    public BottomView(@NonNull Context context) {
        this(context,null);
    }

    public BottomView(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public BottomView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        int count=getChildCount();
        childWidth=getWidth()/count;
        for (int i = 0; i < count; i++) {
            final View childView=getChildAt(i);
            childView.layout(childWidth/2-childView.getWidth()/2+childWidth*i,getHeight()/2-childView.getHeight()/2
            ,childWidth/2+childView.getWidth()/2+childWidth*i,getHeight()/2+childView.getHeight()/2);
            Log.e("onLayout", "childWidth: "+childWidth*(i+1)+"---"+"getWidth:"+getWidth()+"---"+"childView:"+childView.getWidth());
        }
    }
}
