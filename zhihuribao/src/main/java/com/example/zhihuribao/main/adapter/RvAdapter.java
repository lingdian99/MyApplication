package com.example.zhihuribao.main.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by banker_test on 2017/8/8.
 */

//public abstract class RvAdapter<T> extends RecyclerView.Adapter<RvHolder> {
//    private List<T> mList;
//
//    @Override
//    public RvHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//        return getViewHolder(parent,viewType);
//    }
//
//    @Override
//    public void onBindViewHolder(RvHolder holder, int position) {
//        holder.onBindViewHolder(mList.get(position),position);
//    }
//
//    @Override
//    public int getItemCount() {
//        return mList.size();
//    }
//
//    public abstract RvHolder getViewHolder(ViewGroup parent,int viewType);
//}
public abstract class RvAdapter<T> extends RecyclerView.Adapter<RvHolder>{
    private Context context;
    private List<T> dataList;
    private RvListener rvListener;

    public RvAdapter(Context context,List<T> dataList,RvListener rvListener){
        this.context=context;
        this.dataList=dataList;
        this.rvListener=rvListener;
    }

    @Override
    public RvHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return getViewHolder(parent,viewType);
    }

    @Override
    public void onBindViewHolder(RvHolder holder, int position) {
        holder.bindHolder(dataList.get(position),position);
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public abstract RvHolder getViewHolder(ViewGroup parent,int viewType);
}