package com.example.kaiyan.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.kaiyan.R;
import com.example.kaiyan.adapter.MyRvAdapter;
import com.example.kaiyan.bean.HomepageBean;
import com.example.kaiyan.constants.HttpApiConstants;
import com.example.kaiyan.http.AppClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by wys10 on 17/12/11.
 */

public class MainFragment extends Fragment{
    private final String TAG=this.getClass().getName();
    private List<HomepageBean.ItemListBean> mItemList;
    private List<HomepageBean.ItemListBean> mVideoItemList;
    private RecyclerView mRecyclerview;
    private View view;
    private MyRvAdapter myRvAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mItemList=new ArrayList<>();
        mVideoItemList=new ArrayList<>();
        view=inflater.inflate(R.layout.main_fragment_layout,container,false);
        getHttpHomepage(HttpApiConstants.HOMEPAGE_URL);
        return view;
    }

    //加载ui
    private void initUi(){
        myRvAdapter=new MyRvAdapter(getContext(),mVideoItemList);
        RecyclerView.LayoutManager manager=new LinearLayoutManager(getContext());
        mRecyclerview= (RecyclerView) view.findViewById(R.id.rv_main_fragment);
        mRecyclerview.setLayoutManager(manager);
        mRecyclerview.setAdapter(myRvAdapter);
        mRecyclerview.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                Log.e(TAG, "onScrolled: "+dy) ;
            }
        });
        myRvAdapter.setMyOnClickListener(new MyRvAdapter.MyOnClickListener() {
            @Override
            public void onItemClick(int position) {
                Toast.makeText(getContext(), ""+position, Toast.LENGTH_SHORT).show();
            }
        });
    }

    //加载网络数据
    private void getHttpHomepage(String url){
        AppClient.ApiHomepage apiHomepage=AppClient.retrofit("http://www.baidu.com").create(AppClient.ApiHomepage.class);
        Call<HomepageBean> call=apiHomepage.getHomepage(url);
        call.enqueue(new Callback<HomepageBean>() {
            @Override
            public void onResponse(Call<HomepageBean> call, Response<HomepageBean> response) {
                mItemList=response.body().getItemList();
                for (int i = 0; i < mItemList.size(); i++) {
                    if (mItemList.get(i).getType().equals("video")){
                        mVideoItemList.add(mItemList.get(i));
                    }else if (mItemList.get(i).getType().equals("videoCollectionWithCover")){
                        Log.e(TAG, "MyInnerBanner: "+mItemList.get(i).getData().getItemList().get(0).getData().getTitle());
                    }
                }
                initUi();
                Log.e(TAG, "onResponse: "+response.body().getItemList().get(0).getData().getTitle());
            }

            @Override
            public void onFailure(Call<HomepageBean> call, Throwable t) {

            }
        });
    }

}
