package com.example.zhihuribao.main.adapter;

/**
 * Created by banker_test on 2017/6/1.
 */

public interface SectionSupport<T> {
    public int sectionHeaderLayoutId();
    public int sectionTitleTextViewId();
    public String getTitle(T t);
}
