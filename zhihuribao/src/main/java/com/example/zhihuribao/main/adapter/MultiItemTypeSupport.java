package com.example.zhihuribao.main.adapter;

/**
 * Created by banker_test on 2017/6/1.
 */

public interface MultiItemTypeSupport<T> {
    public int getLayoutId(int itemType);
    public int getItemViewType(int postion,T t);
}
