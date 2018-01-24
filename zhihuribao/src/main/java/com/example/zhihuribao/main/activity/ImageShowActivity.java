package com.example.zhihuribao.main.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.zhihuribao.R;
import com.example.zhihuribao.main.view.MyImageView;

/**
 * Created by banker_test on 2017/5/17.
 */

public class ImageShowActivity extends AppCompatActivity{
    private MyImageView imageView;
    private String imgUrl;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.img_show_layout);
        imageView= (MyImageView) findViewById(R.id.img_content);
        getImgUrl();
    }

    //得到从另外一个activity得到的数据
    private void getImgUrl(){
        Bundle bundle=getIntent().getExtras();
        imgUrl=bundle.getString("imgUrl");
        Log.e("imgUrl",imgUrl);
        Glide.with(this).load(imgUrl).diskCacheStrategy(DiskCacheStrategy.NONE).into(imageView);
    }
}
