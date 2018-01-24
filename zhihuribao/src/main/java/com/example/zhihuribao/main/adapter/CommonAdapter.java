package com.example.zhihuribao.main.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by banker_test on 2017/5/31.
 */

public abstract class CommonAdapter<T> extends RecyclerView.Adapter<ViewHolder> {
    private Context mContext;
    private int mLayoutId;
    private List<T> mDatas;
    private LayoutInflater mInflater;

    public CommonAdapter(Context context,int layoutId,List<T> datas){
        this.mContext=context;
        mInflater=LayoutInflater.from(context);
        mLayoutId=layoutId;
        mDatas=datas;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ViewHolder holder=ViewHolder.get(mContext,parent,mLayoutId);
        Log.e("createHolder","createHolder");
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        convert(holder, mDatas.get(position));
    }

    public abstract void convert(ViewHolder viewHolder,T t);

    @Override
    public int getItemCount() {
        return mDatas.size();
    }
}
