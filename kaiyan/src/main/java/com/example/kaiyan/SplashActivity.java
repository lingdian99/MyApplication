package com.example.kaiyan;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by wys10 on 17/11/29.
 */

public class SplashActivity extends AppCompatActivity{
    private static final String TAG="SplashActivity";
    private int time=4;
    final Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 1:
                    time--;
                    if (time>0){
                        Message message=handler.obtainMessage(1);
                        handler.sendMessageDelayed(message,1000);
                    }else {
                        Intent intent=new Intent(SplashActivity.this,MainActivity.class);
                        startActivity(intent);
                        overridePendingTransition(R.anim.in_right,R.anim.out_left);
                    }
            }
        }
    };
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_layout);
        Message msg=handler.obtainMessage(1);
        handler.sendMessageDelayed(msg,1000);
    }


}
