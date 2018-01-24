package com.example.zhihuribao.main;

import android.app.Application;
import android.content.Context;

/**
 * Created by banker_test on 2017/9/11.
 */

public class MyApplication extends Application{
    private static Context context;

    @Override
    public void onCreate() {
        this.context=getApplicationContext();
    }

    public static Context getContextObject(){
        return context;
    }
}
