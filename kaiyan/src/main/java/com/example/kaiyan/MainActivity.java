package com.example.kaiyan;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

import com.example.kaiyan.adapter.MyViewPagerAdapter;
import com.example.kaiyan.fragment.MainFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wys10 on 17/11/27.
 */

public class MainActivity extends AppCompatActivity{
    private TextView tvMain;
    private TextView tvDiscovery;
    private TextView tvSetting;
    private ViewPager mViewPager;
    private MyViewPagerAdapter myViewPagerAdapter;
    private List<Fragment> mFragmentList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_layout);
        tvMain= (TextView) findViewById(R.id.tv_main);
        tvDiscovery= (TextView) findViewById(R.id.tv_dis);
        tvSetting= (TextView) findViewById(R.id.tv_setting);
        tvMain.setOnClickListener(new MyOnClickListener());
        tvDiscovery.setOnClickListener(new MyOnClickListener());
        tvSetting.setOnClickListener(new MyOnClickListener());
        initUi();
    }

    //加载ui
    private void initUi(){
        mFragmentList=new ArrayList<>();
        FragmentManager manager=getSupportFragmentManager();
        MainFragment mainFragment=new MainFragment();
        mFragmentList.add(mainFragment);
        mViewPager= (ViewPager) findViewById(R.id.vp_fragment);
        MyViewPagerAdapter viewPagerAdapter=new MyViewPagerAdapter(manager,mFragmentList);
        mViewPager.setAdapter(viewPagerAdapter);
    }

    private class MyOnClickListener implements OnClickListener{

        @Override
        public void onClick(View v) {
            switch (v.getId()){

            }
        }
    }

    
}
