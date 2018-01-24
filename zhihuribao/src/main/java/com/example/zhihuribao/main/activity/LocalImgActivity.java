package com.example.zhihuribao.main.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.zhihuribao.R;
import com.example.zhihuribao.main.adapter.ImgGridAdapter;
import com.example.zhihuribao.main.adapter.RvListener;
import com.example.zhihuribao.main.utils.ContentDataControl;

/**
 * Created by banker_test on 2017/9/12.
 */

public class LocalImgActivity extends AppCompatActivity {
    private RecyclerView rvImage;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_local_img);
        initUi();
    }

    private void initUi() {
        rvImage = (RecyclerView) findViewById(R.id.rv_img_grid);
        ImgGridAdapter adapter=new ImgGridAdapter(LocalImgActivity.this, ContentDataControl.getFileImages(), new RvListener() {
            @Override
            public void onClick(int position) {

            }
        });

        GridLayoutManager manager=new GridLayoutManager(LocalImgActivity.this,3);
        rvImage.setLayoutManager(manager);
        rvImage.setAdapter(adapter);

    }
}
