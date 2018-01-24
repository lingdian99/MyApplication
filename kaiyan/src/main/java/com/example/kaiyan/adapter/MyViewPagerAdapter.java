package com.example.kaiyan.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by wys10 on 17/12/11.
 */

public class MyViewPagerAdapter extends FragmentPagerAdapter{
    private List<Fragment> mFragmentList;

    public MyViewPagerAdapter(FragmentManager fm,List<Fragment> fragmentList) {
        super(fm);
        mFragmentList=fragmentList;
    }

    @Override
    public Fragment getItem(int position) {
        return mFragmentList.get(position);
    }

    @Override
    public int getCount() {
        return mFragmentList.size();
    }
}
