package com.example.zhihuribao.main.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.zhihuribao.R;
import com.example.zhihuribao.main.adapter.HomeAdapter;
import com.example.zhihuribao.main.constant.ZhihuRibaoUrl;
import com.example.zhihuribao.main.fragment.MainFragment;
import com.example.zhihuribao.main.fragment.ThemeNewsFragment;
import com.example.zhihuribao.main.http.AppClient;
import com.example.zhihuribao.main.utils.BeforeNewsList;
import com.example.zhihuribao.main.utils.ThemeUntil;
import com.example.zhihuribao.main.utils.TodayNewsList;
import com.example.zhihuribao.main.view.BannerView;
import com.example.zhihuribao.main.view.SuperRefreshView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by banker_test on 2017/4/14.
 */

public class MainActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private List<String> idList;
    private HomeAdapter adapter;
    private List<TodayNewsList.TopStoriesBean> topStoriesBeen = new ArrayList<>();
    private List<TodayNewsList.StoriesBean> storiesBeen = new ArrayList<>();
    private List<BeforeNewsList.StoriesBean> beforeBeen = new ArrayList<>();
    private List<BeforeNewsList.StoriesBean> totalBeen = new ArrayList<>();
    private String today;
    private Toolbar toolbar;
    private DrawerLayout drawerLayout;
    private SuperRefreshView swipeRefreshLayout1;
    private SuperRefreshView swipeRefreshLayout;
    private ActionBarDrawerToggle drawerToggle;
    private int totalItemCount;
    private int lastVisibleItemPosition;
    private boolean isLoading = false;
    private List<String> dateList = new ArrayList<>();
    private String id;
    private LinearLayout llTheme;
    private List<ThemeUntil.OthersBean> othersBeen = new ArrayList<>();
    private int themeId;
    private SwipeRefreshLayout mLayout;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        toolbar = (Toolbar) findViewById(R.id.main_toolBar);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_left);
        swipeRefreshLayout= (SuperRefreshView) findViewById(R.id.swipe_refresh_layout);
        setSupportActionBar(toolbar);
        toolbar.setLogo(R.mipmap.ic_launcher);
        toolbar.setTitle("知乎日报");
        toolbar.setSubtitle("我的知乎日报");
        toolbar.setTitleTextColor(Color.parseColor("#667788"));
        getSupportActionBar().setElevation(0);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        drawerToggle = new ActionBarDrawerToggle(MainActivity.this, drawerLayout, toolbar, R.string.drawer_open, R.string.drawer_close);
        drawerToggle.syncState();
        MainFragment mainFragment = new MainFragment();
        initFragment(mainFragment);
//        colorTrackView= (ColorTrackView) findViewById(R.id.my_colorTrackView);
//        startLeftChange(colorTrackView);
//        getTodayNews(ZhihuRibaoUrl.NEWS_LIST_BASE_URL, ZhihuRibaoUrl.NEWS_LIST_END_URL);
        getThemeList("http://www.baidu.com", ZhihuRibaoUrl.NEWS_THEME_LIST);
    }

    //加载fragment
    private void initFragment(Fragment fragment) {
        android.support.v4.app.FragmentManager fragmentManager = getSupportFragmentManager();
        android.support.v4.app.FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, fragment);
        fragmentTransaction.commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

    private void initUi() {
        BannerView headerView = new BannerView(this);
        headerView.setFocusable(true);
        headerView.setBeen(topStoriesBeen);
        headerView.setOnBannerClick(new BannerView.OnBannerClick() {
            @Override
            public void setOnClick(int position) {
                Toast.makeText(getApplicationContext(), "第几个：" + position, Toast.LENGTH_SHORT).show();
                id = String.valueOf(topStoriesBeen.get(position - 1).getId());
                gotoContent(id);
            }
        });
//        mRecyclerView = (RecyclerView) findViewById(R.id.news_recyclerView);
        adapter = new HomeAdapter(MainActivity.this, storiesBeen);
        adapter.setHeaderView(headerView);
        RecyclerView.LayoutManager manager = new GridLayoutManager(this, 1);
        mRecyclerView.setLayoutManager(manager);
        mRecyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(new HomeAdapter.OnItemClickListener() {
            @Override
            public void setOnClick(int position) {
                Toast.makeText(MainActivity.this, "第几个：" + position, Toast.LENGTH_SHORT).show();
                if (position < storiesBeen.size() - 1) {
                    id = String.valueOf(storiesBeen.get(position - 1).getId());
                } else {
                    id = String.valueOf(totalBeen.get(position - storiesBeen.size() - 1).getId());
                }

                gotoContent(id);
            }
        });
        loadMore();
    }

    //加载drawer主题
    private void initThemeUi(final List<ThemeUntil.OthersBean> othersBeen) {
        llTheme = (LinearLayout) findViewById(R.id.ll_theme);
        for (int i = 0; i < othersBeen.size() + 1; i++) {
            View view = LayoutInflater.from(this).inflate(R.layout.theme_layout, llTheme, false);
            TextView textView = (TextView) view.findViewById(R.id.theme_text);
            if (i == 0) {
                textView.setText("主页");
            } else {
                textView.setText(othersBeen.get(i - 1).getName());
            }
            llTheme.addView(view);
            final int finalI = i;
            textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(MainActivity.this, ":" + finalI, Toast.LENGTH_SHORT).show();
                    drawerLayout.closeDrawer(GravityCompat.START);
                    if (finalI == 0) {
                        MainFragment mainFragment = new MainFragment();
                        initFragment(mainFragment);
                    } else {
                        themeId=othersBeen.get(finalI-1).getId();
                        ThemeNewsFragment themeNewsFragment = new ThemeNewsFragment();
                        Bundle fb=new Bundle();
                        fb.putInt("themeId",themeId);
                        themeNewsFragment.setArguments(fb);
                        initFragment(themeNewsFragment);
                    }
                }
            });
        }
    }

    //加载今日网络数据
    private void getTodayNews(String baseUrl, String date) {
        AppClient.ApiNewsList apiNewsList = AppClient.retrofit(baseUrl).create(AppClient.ApiNewsList.class);
        Call<TodayNewsList> call = apiNewsList.getNews(date);
        call.enqueue(new Callback<TodayNewsList>() {
            @Override
            public void onResponse(Call<TodayNewsList> call, Response<TodayNewsList> response) {
                int size = response.body().getTop_stories().size();
                topStoriesBeen = response.body().getTop_stories();
                storiesBeen = response.body().getStories();
                today = response.body().getDate();
                dateList.add(today);
                Log.e("Today", today);
                for (int i = 0; i < size; i++) {
                    String title = topStoriesBeen.get(i).getTitle();
                    Log.e("title", ":" + title);
                }
                initUi();
            }

            @Override
            public void onFailure(Call<TodayNewsList> call, Throwable t) {

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
//                adapter.setBeforeBeen(beforeBeen);
                isLoading = false;

            }

            @Override
            public void onFailure(Call<BeforeNewsList> call, Throwable t) {

            }
        });
    }

    //获取主题
    private void getThemeList(String baseUrl, String realUrl) {
        AppClient.ApiThemeList apiThemeList = AppClient.retrofit(baseUrl).create(AppClient.ApiThemeList.class);
        Call<ThemeUntil> call = apiThemeList.getThemeList(realUrl);
        call.enqueue(new Callback<ThemeUntil>() {
            @Override
            public void onResponse(Call<ThemeUntil> call, Response<ThemeUntil> response) {
                othersBeen = response.body().getOthers();
                String themeName = response.body().getOthers().get(0).getName();
                initThemeUi(othersBeen);
                Log.e("themeName", themeName);
            }

            @Override
            public void onFailure(Call<ThemeUntil> call, Throwable t) {

            }
        });
    }

    //上拉加载更多
    private void loadMore() {
        final View footerView = LayoutInflater.from(this).inflate(R.layout.news_list_footer, mRecyclerView, false);
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

    //进入内容主界面
    private void gotoContent(String id) {
        Intent intent = new Intent(MainActivity.this, ContentActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString("id", id);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    //返回键设置


    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    static class ViewHolder {
        TextView textView;
    }

}
