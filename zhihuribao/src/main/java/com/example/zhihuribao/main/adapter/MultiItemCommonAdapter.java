package com.example.zhihuribao.main.adapter;

import android.content.Context;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by banker_test on 2017/6/1.
 */

public abstract class MultiItemCommonAdapter<T> extends CommonAdapter{
    private MultiItemTypeSupport<T> mMultiItemTypeSupport;
    private List<T> mDatas;
    private Context mContext;

    public MultiItemCommonAdapter(Context context, List<T> datas,MultiItemTypeSupport<T> multiItemTypeSupport) {
        super(context, -1, datas);
        mMultiItemTypeSupport=multiItemTypeSupport;
        mDatas=datas;
        mContext=context;
    }

    @Override
    public int getItemViewType(int position) {
        return mMultiItemTypeSupport.getItemViewType(position,mDatas.get(position));
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        int layoutId=mMultiItemTypeSupport.getLayoutId(viewType);
        ViewHolder holder=ViewHolder.get(mContext,parent,layoutId);
        return holder;
    }
}
