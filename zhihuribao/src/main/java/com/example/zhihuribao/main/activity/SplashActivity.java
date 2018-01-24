package com.example.zhihuribao.main.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.FutureTarget;
import com.example.zhihuribao.R;
import com.example.zhihuribao.main.constant.ZhihuRibaoUrl;
import com.example.zhihuribao.main.http.AppClient;
import com.example.zhihuribao.main.utils.SplashUntil;

import java.io.File;
import java.util.concurrent.ExecutionException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SplashActivity extends AppCompatActivity {
    private String imgUrl;
    private ImageView splashImg;
    private Handler handler;
    private File file;
    private boolean timeEnd = false;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_layout);
        context=this;
        splashImg = (ImageView) findViewById(R.id.splash_img);
        handlerMsg();
        getImg(ZhihuRibaoUrl.SPLASH_IMG_URL);
    }

    private void getImg(String url) {
        AppClient.ApiStores apiStores = AppClient.retrofit("http://www.baidu.com").create(AppClient.ApiStores.class);
        Call<SplashUntil> call = apiStores.getImg(url);
        call.enqueue(new Callback<SplashUntil>() {
            @Override
            public void onResponse(Call<SplashUntil> call, Response<SplashUntil> response) {
                if (response.body().getCreatives().size() != 0) {

                    imgUrl = response.body().getCreatives().get(0).getUrl();
                    Glide.with(SplashActivity.this).load(imgUrl).diskCacheStrategy(DiskCacheStrategy.ALL).into(splashImg);
                    File cacheFile=context.getCacheDir();
                    Log.e("splashImg", ":" + response.body().getCreatives().get(0).getUrl());
                    Log.e("cacheFile",":"+cacheFile);
                }
                MyThread myThread = new MyThread();
                myThread.start();
            }

            @Override
            public void onFailure(Call<SplashUntil> call, Throwable t) {
                timeTask();
                Log.e("onFailure", ":onFailure");
            }
        });
    }

    //倒计时
    private void timeTask() {
        for (int i = 3; i > 0; i--) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            timeEnd = true;
        }

        if (timeEnd) {
            Intent intent = new Intent(SplashActivity.this, MainActivity.class);
            startActivity(intent);
            Log.e("timeEnd", ":" + timeEnd);
        }

    }

    //handler处理
    private void handlerMsg() {
        handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                switch (msg.what) {
                    case 1:
                        Glide.with(SplashActivity.this).load(file).diskCacheStrategy(DiskCacheStrategy.ALL).into(splashImg);
                        break;
                }
                super.handleMessage(msg);
            }
        };
    }

    class MyThread extends Thread {
        @Override
        public void run() {
            FutureTarget<File> future = Glide.with(SplashActivity.this)
                    .load(imgUrl)
                    .downloadOnly(500, 500);
            Log.e("run", "run");
            try {
                File cacheFile = future.get();
                String path = cacheFile.getAbsolutePath();
                file = cacheFile;
                Log.e("file", ":" + path);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
            Message message = handler.obtainMessage();
            message.what = 1;
            handler.sendMessage(message);
            timeTask();
        }
    }
}
