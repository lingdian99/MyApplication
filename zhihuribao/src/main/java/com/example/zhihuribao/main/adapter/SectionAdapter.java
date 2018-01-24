package com.example.zhihuribao.main.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by banker_test on 2017/6/1.
 */

public abstract class SectionAdapter<T> extends MultiItemCommonAdapter<T> {
    private List<T> mDatas;
    private SectionSupport mSectionSupport;
    private LinkedHashMap<String,Integer> mSections;
    private int mLayoutId;
    private Context mContext;
    private MultiItemTypeSupport multiItemTypeSupport;
    private MultiItemTypeSupport<T> headerItemTypeSupport=new MultiItemTypeSupport<T>() {
        @Override
        public int getLayoutId(int itemType) {
            if (itemType==0){
                return mSectionSupport.sectionHeaderLayoutId();
            }else{
                return multiItemTypeSupport.getLayoutId(itemType);
            }
        }

        @Override
        public int getItemViewType(int position, T t) {
            return mSections.values().contains(position)?3:multiItemTypeSupport.getItemViewType(position,t);
        }
    };

    RecyclerView.AdapterDataObserver observer=new RecyclerView.AdapterDataObserver() {
        @Override
        public void onChanged() {
            super.onChanged();
            findSections();
        }
    };

    public void findSections(){
        int n=mDatas.size();
        int nSections=0;
        mSections.clear();
        for (int i=0;i<n;i++){
            String sectionName=mSectionSupport.getTitle(mDatas.get(i));
            if (!mSections.values().contains(sectionName)){
                mSections.put(sectionName,i+nSections);
                nSections++;
            }

        }
    }

    public int getIndexForPosition(int position){
        int nSections=0;
        Set<Map.Entry<String,Integer>> entrySet=mSections.entrySet();
        for (Map.Entry<String,Integer> entry:entrySet){
            if (entry.getValue()<position){
                nSections++;
            }
        }
        return position-nSections;

    }

    public SectionAdapter(Context context, int layoutId,List<T> datas, SectionSupport sectionSupport,MultiItemTypeSupport multiItemTypeSupport) {
        super(context, datas, multiItemTypeSupport);
        mSections=new LinkedHashMap<>();
        mSectionSupport=sectionSupport;
        this.multiItemTypeSupport=multiItemTypeSupport;
        mDatas=datas;
        mContext=context;
        mLayoutId=layoutId;
        findSections();
        registerAdapterDataObserver(observer);
    }

    @Override
    public void onDetachedFromRecyclerView(RecyclerView recyclerView)
    {
        super.onDetachedFromRecyclerView(recyclerView);
        unregisterAdapterDataObserver(observer);
    }

    @Override
    public int getItemCount() {
        return super.getItemCount()+mSections.size();
    }

    @Override
    public int getItemViewType(int position) {
        return headerItemTypeSupport.getItemViewType(position,null);
    }

    @Override
    public abstract void convert(ViewHolder viewHolder, Object o);

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        position=getIndexForPosition(position);
        if (holder.getItemViewType()==3){
//            holder.setText(mSectionSupport.sectionTitleTextViewId(), mSectionSupport.getTitle(mDatas.get(position)));
            return;
        }
        super.onBindViewHolder(holder,position);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType==3){
            int layoutId=mSectionSupport.sectionHeaderLayoutId();
            ViewHolder holder=ViewHolder.get(mContext,parent,layoutId);
            return holder;
        }
        return super.onCreateViewHolder(parent, viewType);
    }
}
