package com.example.zhihuribao.main.activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.zhihuribao.R;
import com.example.zhihuribao.main.utils.ContentDataLoadTask;

/**
 * Created by banker_test on 2017/9/12.
 */

public class SelectImgActivity extends AppCompatActivity{
    private Button btnSelect;
    private ImageView imgSelected;
    private MyOnClickListener listener;
    private ContentDataLoadTask task;
    private static final int MY_PERMISSION_CODE=1;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_img);
        requestPermission();
        initUi();
    }

    //申请权限
    private void requestPermission(){
        if (ContextCompat.checkSelfPermission(SelectImgActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(SelectImgActivity.this,new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}
                    ,MY_PERMISSION_CODE);
        }
    }

    //权限回调


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode){
            case MY_PERMISSION_CODE:
                if (grantResults.length>0&&grantResults[0]==PackageManager.PERMISSION_GRANTED){
                    initUi();
                }else {
                    finish();
                }
        }
    }

    private void initUi(){
        btnSelect= (Button) findViewById(R.id.btn_select);
        imgSelected= (ImageView) findViewById(R.id.img_selected);
        btnSelect.setOnClickListener(new MyOnClickListener());
    }

    private class MyOnClickListener implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.btn_select:
//                    Intent intent = new Intent();
//                /* 开启Pictures画面Type设定为image */
//                    intent.setType("image/*");
//                /* 使用Intent.ACTION_GET_CONTENT这个Action */
//                    intent.setAction(Intent.ACTION_GET_CONTENT);
//                /* 取得相片后返回本画面 */
//                    startActivityForResult(intent, 1);

                    task=new ContentDataLoadTask(SelectImgActivity.this);
                    task.setOnContentDataListener(new ContentDataLoadTask.OnContentDataListener() {
                        @Override
                        public void startLoad() {

                        }

                        @Override
                        public void finishLoad() {
                            Intent intent=new Intent(SelectImgActivity.this,LocalImgActivity.class);
                            startActivity(intent);
                        }
                    });
                    task.execute();
                    break;
            }
        }
    }


}
