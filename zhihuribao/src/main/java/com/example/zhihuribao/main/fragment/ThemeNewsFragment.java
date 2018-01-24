package com.example.zhihuribao.main.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.zhihuribao.R;
import com.example.zhihuribao.main.activity.ContentActivity;
import com.example.zhihuribao.main.adapter.ThemeNewsAdapter;
import com.example.zhihuribao.main.constant.ZhihuRibaoUrl;
import com.example.zhihuribao.main.http.AppClient;
import com.example.zhihuribao.main.utils.BeforeNewsList;
import com.example.zhihuribao.main.utils.ThemeNewsUntil;
import com.example.zhihuribao.main.utils.ThemeUntil;
import com.example.zhihuribao.main.utils.TodayNewsList;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by banker_test on 2017/5/26.
 */

public class ThemeNewsFragment extends Fragment {
    private RecyclerView mRecyclerView;
    private List<String> datas;
    private ThemeNewsAdapter adapter;
    private List<TodayNewsList.TopStoriesBean> topStoriesBeen = new ArrayList<>();
    private List<ThemeNewsUntil.StoriesBean> storiesBeen = new ArrayList<>();
    private List<BeforeNewsList.StoriesBean> beforeBeen = new ArrayList<>();
    private List<BeforeNewsList.StoriesBean> totalBeen = new ArrayList<>();
    private String today;
    private Toolbar toolbar;
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle drawerToggle;
    private int totalItemCount;
    private int lastVisibleItemPosition;
    private boolean isLoading = false;
    private List<String> dateList = new ArrayList<>();
    private String id;
    private LinearLayout llTheme;
    private List<ThemeUntil.OthersBean> othersBeen = new ArrayList<>();
    private View view;
    private String imgUrl;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.main_fragment, container, false);
        Bundle bundle = getArguments();
        int themeId = bundle.getInt("themeId");
//        getTodayNews(ZhihuRibaoUrl.NEWS_LIST_BASE_URL, ZhihuRibaoUrl.NEWS_LIST_END_URL);
        getThemeNewsList(ZhihuRibaoUrl.NEWS_THEME_NEWS_LIST, String.valueOf(themeId));
        return view;
    }

    //加载今日网络数据
    private void getThemeNewsList(String baseUrl, String id) {
        AppClient.ApiThemeNewsList apiThemeNewsList = AppClient.retrofit(baseUrl).create(AppClient.ApiThemeNewsList.class);
        Call<ThemeNewsUntil> call = apiThemeNewsList.getThemeNewsList(id);
        call.enqueue(new Callback<ThemeNewsUntil>() {
            @Override
            public void onResponse(Call<ThemeNewsUntil> call, Response<ThemeNewsUntil> response) {
                Log.e("themeNews", response.body().getStories().get(0).getTitle().toString());
                storiesBeen = response.body().getStories();
                imgUrl=response.body().getImage();
                initUi();
            }

            @Override
            public void onFailure(Call<ThemeNewsUntil> call, Throwable t) {

            }
        });
    }

    //加载往日数据列表
    private void getBeforeNews(String baseUrl, final String date) {
        isLoading = true;
        AppClient.ApiBeforeNews apiBeforeNews = AppClient.retrofit(baseUrl).create(AppClient.ApiBeforeNews.class);
        Call<BeforeNewsList> call = apiBeforeNews.getNews(date);
        call.enqueue(new Callback<BeforeNewsList>() {
            @Override
            public void onResponse(Call<BeforeNewsList> call, Response<BeforeNewsList> response) {
                Log.e("BeforeNews", ":" + response.body().getStories().get(1).getTitle());
                today = response.body().getDate();
                beforeBeen = response.body().getStories();
                dateList.add(today);
                for (int i = 0; i < beforeBeen.size(); i++) {
                    totalBeen.add(beforeBeen.get(i));
                }
                adapter.setBeforeBeen(beforeBeen, dateList);
                isLoading = false;

            }

            @Override
            public void onFailure(Call<BeforeNewsList> call, Throwable t) {

            }
        });
    }

    //上拉加载更多
    private void loadMore() {
        final View footerView = LayoutInflater.from(getActivity()).inflate(R.layout.news_list_footer, mRecyclerView, false);
        final LinearLayoutManager linearLayoutManager = (LinearLayoutManager) mRecyclerView.getLayoutManager();
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                totalItemCount = adapter.getItemCount();
                lastVisibleItemPosition = linearLayoutManager.findLastVisibleItemPosition();
                if (totalItemCount == lastVisibleItemPosition + 1 && !isLoading && newState == RecyclerView.SCROLL_STATE_IDLE) {
                    getBeforeNews(ZhihuRibaoUrl.NEWS_LIST_BASE_URL + "before/", today);
                    Log.e("itemCount", "totalCount:" + totalItemCount + " lastItem:" + lastVisibleItemPosition);
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                totalItemCount = adapter.getItemCount();
                lastVisibleItemPosition = linearLayoutManager.findLastVisibleItemPosition();

            }
        });
    }

    private void initUi() {
        ImageView imageView = new ImageView(getActivity());
        imageView.setImageResource(R.drawable.test01);
        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        imageView.setLayoutParams(params);
        Glide.with(getActivity()).load(imgUrl).into(imageView);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.news_recyclerView);
        adapter = new ThemeNewsAdapter(getActivity(), storiesBeen);
        adapter.setHeaderView(imageView);
        RecyclerView.LayoutManager manager = new GridLayoutManager(getActivity(), 1);
        mRecyclerView.setLayoutManager(manager);
        mRecyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(new ThemeNewsAdapter.OnItemClickListener() {
            @Override
            public void setOnClick(int position) {
                Toast.makeText(getActivity(), "第几个：" + position, Toast.LENGTH_SHORT).show();
                if (position < storiesBeen.size() + 1) {
                    id = String.valueOf(storiesBeen.get(position - 1).getId());

                } else {
                    id = String.valueOf(totalBeen.get(position - storiesBeen.size() - 1).getId());
                }
                gotoContent(id);
            }
        });
//        loadMore();
    }

    //进入内容主界面
    private void gotoContent(String id) {
        Intent intent = new Intent(getActivity(), ContentActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString("id", id);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    //px转dp
    private int px2dp(int px) {
        float scale = getActivity().getApplicationContext().getResources().getDisplayMetrics().density;
        return (int) (px / scale);
    }



    public static int sp2px(Context context, float spVal)

    {

        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP,

                spVal, context.getResources().getDisplayMetrics());

    }
}

