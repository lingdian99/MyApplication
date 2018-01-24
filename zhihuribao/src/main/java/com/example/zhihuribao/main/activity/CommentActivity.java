package com.example.zhihuribao.main.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.SparseArray;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.zhihuribao.R;
import com.example.zhihuribao.main.adapter.CommentAdapter;
import com.example.zhihuribao.main.adapter.RvListener;
import com.example.zhihuribao.main.bean.Comment;
import com.example.zhihuribao.main.constant.ZhihuRibaoUrl;
import com.example.zhihuribao.main.http.AppClient;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by banker_test on 2017/8/17.
 */

public class CommentActivity extends AppCompatActivity {
    private RecyclerView rvComment;
    private CommentAdapter commentAdapter;
    private Context context;
    private List<Comment.CommentsBean> longDataList;
    private List<Comment.CommentsBean> shortDataList;
    private String id;
    private List<Integer> headPositions;
    private Map<Integer, String> sections;
    private SparseArray<String> sectionArray;
    //返回按钮
    private Button btnBack;
    //菜单按钮
    private Button btnSetting;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment);
        longDataList = new ArrayList<>();
        shortDataList = new ArrayList<>();
        headPositions = new ArrayList<>();
        sections = new HashMap<>();
        sectionArray = new SparseArray<>();
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        id = bundle.getString("listId");
        context = CommentActivity.this;
        initUi();
        getLongComment();
    }

    private void initUi(){
        btnBack= (Button) findViewById(R.id.btn_back);
        btnSetting= (Button) findViewById(R.id.btn_setting);
        btnBack.setOnClickListener(new MyOnClickListener());
        btnSetting.setOnClickListener(new MyOnClickListener());
    }

    private void initView() {
        rvComment = (RecyclerView) findViewById(R.id.rv_comment);
        commentAdapter = new CommentAdapter(context, longDataList, headPositions, new RvListener() {
            @Override
            public void onClick(int position) {
                Toast.makeText(CommentActivity.this, ":" + position, Toast.LENGTH_SHORT).show();
            }
        });
        rvComment.setAdapter(commentAdapter);
        rvComment.setLayoutManager(new LinearLayoutManager(context));
    }

    //加载网络数据
    private void getLongComment() {
        AppClient.ApiLongComment apiLongComment = AppClient.retrofit(ZhihuRibaoUrl.SHORT_COMMENT_HEAD).create(AppClient.ApiLongComment.class);
        Call<Comment> call = apiLongComment.getLongComment(id);
        call.enqueue(new Callback<Comment>() {
            @Override
            public void onResponse(Call<Comment> call, Response<Comment> response) {
                headPositions.add(0);
                sectionArray.put(0, "长评论");
                sections.put(0, "长评论");
//                longDataList.add(0,null);
                for (int i=0;i<response.body().getComments().size();i++){
                    longDataList.add(response.body().getComments().get(i));
                }
                headPositions.add(longDataList.size() + 1);
//                longDataList.addAll(response.body().getComments());

                sectionArray.put(longDataList.size() + 1, "短评论");
                sections.put(longDataList.size() + 1, "短评论");
//                longDataList.add(longDataList.size()-1,null);
                initView();
                getShortComment();
                Log.e("dataSize", "onResponse: " + longDataList.size());
            }

            @Override
            public void onFailure(Call<Comment> call, Throwable t) {

            }
        });
    }

    //加载短评
    private void getShortComment() {
        final AppClient.ApiShortComment shortComment = AppClient.retrofit(ZhihuRibaoUrl.SHORT_COMMENT_HEAD).create(AppClient.ApiShortComment.class);
        Call<Comment> call = shortComment.getShortComment(id);
        call.enqueue(new Callback<Comment>() {
            @Override
            public void onResponse(Call<Comment> call, Response<Comment> response) {
                shortDataList = response.body().getComments();
                longDataList.addAll(shortDataList);
                commentAdapter.notifyDataSetChanged();
                Log.e("shortSize", "onResponse: " + shortDataList.size());
            }

            @Override
            public void onFailure(Call<Comment> call, Throwable t) {

            }
        });
    }

    //点击事件
    private class MyOnClickListener implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.btn_back:
                    finish();
                    break;
                case R.id.btn_setting:
                    Intent intent=new Intent(CommentActivity.this,SelectImgActivity.class);
                    startActivity(intent);
                    break;
            }
        }
    }
}
