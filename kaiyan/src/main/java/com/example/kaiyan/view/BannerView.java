package com.example.kaiyan.view;

import android.content.Context;
import android.os.Handler;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.kaiyan.R;
import com.example.kaiyan.adapter.MyBannerAdapter;
import com.example.kaiyan.bean.BannerVideoBeen;
import com.example.kaiyan.bean.HomepageBeen;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by banker_test on 2017/4/18.
 */

public class BannerView extends FrameLayout {
    private Context context;
    private List<View> views;
    private List<ImageView> imgDots;
    private ViewPager viewPager;
    private LinearLayout llDot;
    private int[] images = new int[5];
    private boolean isAutoPlay=true;
    private int currentItem=1;
    private Handler mHandler=new Handler();
    private OnBannerClick onBannerClick;
//    private List<TodayNewsList.TopStoriesBean> itemListBeans=new ArrayList<>();
    private List<BannerVideoBeen.ItemListBean.DataBeanX.ItemListBannerBean> itemListBeans;

    public BannerView(Context context) {
        this(context, null);
    }

    public BannerView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BannerView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        views=new ArrayList<>();
        imgDots=new ArrayList<>();
        itemListBeans=new ArrayList<>();
    }

    public void setBeen(List<BannerVideoBeen.ItemListBean.DataBeanX.ItemListBannerBean> itemListBeans){
        this.itemListBeans=itemListBeans;
        views.clear();
        initUI();
        startPlay();
    }

    public void setOnBannerClick(OnBannerClick onBannerClick){
        this.onBannerClick=onBannerClick;
    }

    //加载ui
    private void initUI() {
        View view = LayoutInflater.from(context).inflate(R.layout.banner_layout, this,true);
        viewPager = (ViewPager) view.findViewById(R.id.banner_vp);
        llDot = (LinearLayout) view.findViewById(R.id.ll_dot);
        llDot.removeAllViews();
        Log.e("initView","initView");
        //加载小圆点指示器
        for (int i = 0; i < images.length; i++) {
            ImageView dotImg = new ImageView(context);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT
                    , ViewGroup.LayoutParams.WRAP_CONTENT);
            params.leftMargin = 10;
            params.rightMargin = 10;
            llDot.addView(dotImg, params);
            imgDots.add(dotImg);
            if (i==0){
                imgDots.get(i).setImageResource(R.drawable.focus_dot);
            }else {
                imgDots.get(i).setImageResource(R.drawable.normal_dot);
            }
        }
        //广告图片集合
        int length = images.length;
        for (int i = 0; i <= images.length + 1; i++) {
            View contentView = LayoutInflater.from(context).inflate(R.layout.banner_content, null);
            ImageView bannerImg = (ImageView) contentView.findViewById(R.id.banner_iv);
            TextView bannerText = (TextView) contentView.findViewById(R.id.banner_tv);
            if (i == 0) {
//                Glide.with(context).load(itemListBeans.get(length-1).getImage()).into(bannerImg);
//                bannerText.setText(itemListBeans.get(length-1).getTitle());
            } else if (i == length + 1) {
//                Glide.with(context).load(itemListBeans.get(0).getImage()).into(bannerImg);
//                bannerText.setText(itemListBeans.get(0).getTitle());
            } else {
//                Glide.with(context).load(itemListBeans.get(i-1).getImage()).into(bannerImg);
//                bannerText.setText(itemListBeans.get(i-1).getTitle());
            }
            views.add(contentView);
            contentView.setTag(i);
            contentView.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    onBannerClick.setOnClick((Integer) v.getTag());
                }
            });
        }
        MyBannerAdapter adapter=new MyBannerAdapter(views);
        MyPagerOnChangeListener listener=new MyPagerOnChangeListener();
        viewPager.setAdapter(adapter);
        viewPager.setFocusable(true);
        viewPager.setCurrentItem(1);
        viewPager.addOnPageChangeListener(listener);
    }

    //默认开始定时滑动
    private void startPlay(){
        isAutoPlay=true;
        mHandler.postDelayed(task,3000);
    }

    //定时滑动
    private final Runnable task=new Runnable() {
        @Override
        public void run() {
            if (isAutoPlay){
                currentItem=currentItem%(images.length+1)+1;
                if (currentItem==1){
                    viewPager.setCurrentItem(currentItem);
                    mHandler.post(task);
                }else {
                    viewPager.setCurrentItem(currentItem);
                    mHandler.postDelayed(task,5000);
                }
            }else {
                mHandler.postDelayed(task,3000);
            }
        }
    };

    class MyPagerOnChangeListener implements ViewPager.OnPageChangeListener {

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            Log.e("viewPosition",":"+position);
            for (int i=0;i<images.length;i++){
                if (position-1==i){
                    imgDots.get(i).setImageResource(R.drawable.focus_dot);
                }else {
                    imgDots.get(i).setImageResource(R.drawable.normal_dot);
                }
            }
        }

        @Override
        public void onPageScrollStateChanged(int state) {
            switch (state) {
                case 0:
                    if (viewPager.getCurrentItem() == 0) {
                        viewPager.setCurrentItem(images.length, false);
                    } else if (viewPager.getCurrentItem() == images.length + 1) {
                        viewPager.setCurrentItem(1, false);
                    }
                    isAutoPlay=true;
                    break;
                case 1:
                    isAutoPlay=false;
                    break;
                case 2:
                    isAutoPlay=false;
                    currentItem=viewPager.getCurrentItem();
            }
        }
    }

    public interface OnBannerClick{
        public void setOnClick(int position);
    }


}
