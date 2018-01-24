package com.example.zhihuribao.main.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by banker_test on 2017/9/11.
 */

public class NetworkUtil {
    public static boolean isNetworkAvailable(Context context){
        ConnectivityManager manager= (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (manager!=null){
            NetworkInfo info=manager.getActiveNetworkInfo();
            if (info!=null&&info.isConnected()){
                //当前网络连接
                if (info.getState()==NetworkInfo.State.CONNECTED){
                    //当前网络连接可用
                    return true;
                }
            }
        }
        return false;
    }
}
