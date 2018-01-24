package com.example.zhihuribao.main.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by banker_test on 2017/8/8.
 */
//
//public abstract class RvHolder<T> extends RecyclerView.ViewHolder {
//    private RvListener rvListener;
//
//    public RvHolder(View itemView,RvListener rvListener) {
//        super(itemView);
//        this.rvListener=rvListener;
//    }
//
//    public abstract void onBindViewHolder(T t,int position);
//}
public abstract class RvHolder<T> extends RecyclerView.ViewHolder{
    private RvListener rvListener;

    public RvHolder(View itemView,int viewType,RvListener rvListener) {
        super(itemView);
        this.rvListener=rvListener;
    }

    public abstract void bindHolder(T t,int position);
}
