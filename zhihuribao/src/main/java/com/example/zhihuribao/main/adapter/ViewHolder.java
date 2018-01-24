package com.example.zhihuribao.main.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * Created by banker_test on 2017/5/31.
 */

public class ViewHolder extends RecyclerView.ViewHolder{
    private SparseArray<View> mViews;
    private Context mContext;
    private View mConvertView;

    public ViewHolder(Context context, View itemView, ViewGroup parent) {
        super(itemView);
        mContext=context;
        mConvertView=itemView;
        mViews=new SparseArray<>();
    }

    public static ViewHolder get(Context context,ViewGroup parent,int layoutId){
        View itemView= LayoutInflater.from(context).inflate(layoutId,parent,false);
        ViewHolder holder=new ViewHolder(context,itemView,parent);
        return holder;
    }

    //获取控件
    public <T extends View> T getView(int viewId){
        View view=mViews.get(viewId);
        if (view==null){
            view=mConvertView.findViewById(viewId);
            mViews.put(viewId,view);
            Log.e("getView","getView");
        }
        return (T) view;
    }
}
