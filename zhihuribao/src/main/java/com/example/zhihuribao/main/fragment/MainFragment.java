package com.example.zhihuribao.main.fragment;

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
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.zhihuribao.R;
import com.example.zhihuribao.main.activity.ContentActivity;
import com.example.zhihuribao.main.adapter.HomeAdapter;
import com.example.zhihuribao.main.constant.Constants;
import com.example.zhihuribao.main.constant.ZhihuRibaoUrl;
import com.example.zhihuribao.main.http.AppClient;
import com.example.zhihuribao.main.utils.ACache;
import com.example.zhihuribao.main.utils.BeforeNewsList;
import com.example.zhihuribao.main.utils.ThemeUntil;
import com.example.zhihuribao.main.utils.TodayNewsList;
import com.example.zhihuribao.main.view.BannerView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by banker_test on 2017/5/26.
 */

public class MainFragment extends Fragment {
    private RecyclerView mRecyclerView;
    private List<String> newsIds=new ArrayList<>();
    private HomeAdapter adapter;
    private List<TodayNewsList.TopStoriesBean> topStoriesBeen = new ArrayList<>();
    private List<TodayNewsList.StoriesBean> storiesBeen = new ArrayList<>();
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
    //缓存
    private ACache mACache;
    private TodayNewsList todayNewsList;
    private List<Integer> datePosition = new ArrayList<>();
    private boolean isLoadTodayNews = true;
    private int nextPosition = 0;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mACache = ACache.get(getContext());
        view = inflater.inflate(R.layout.main_fragment, container, false);
        getTodayNews(ZhihuRibaoUrl.NEWS_LIST_BASE_URL, ZhihuRibaoUrl.NEWS_LIST_END_URL);
        Log.e("view", "onCreateView: " );
        return view;
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
                for (int i=0;i<storiesBeen.size();i++){
                    newsIds.add(String.valueOf(storiesBeen.get(i).getId()));
                }
                Log.e("storiesBeen.size", "onResponse: " + storiesBeen.size());
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
                today = response.body().getDate();
                beforeBeen = response.body().getStories();
                dateList.add(today);
                for (int i = 0; i < beforeBeen.size(); i++) {
                    totalBeen.add(beforeBeen.get(i));
                    newsIds.add(String.valueOf(beforeBeen.get(i).getId()));
                }
                Log.e("beforeBeen.size", "onResponse: " + beforeBeen.size());
                //添加日期头
                if (isLoadTodayNews) {
                    addDates(storiesBeen.size());
                    isLoadTodayNews = false;
                    nextPosition = beforeBeen.size();
                } else {
                    addDates(nextPosition);
                    nextPosition = beforeBeen.size();
                }
                adapter.setDateList(dateList, datePosition);
                adapter.setBeforeBeen(beforeBeen);


                isLoading = false;
                adapter.footerHolder.setData(0);

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
                    adapter.footerHolder.setData(1);
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
        BannerView headerView = new BannerView(getActivity());
        headerView.setFocusable(true);
        headerView.setBeen(topStoriesBeen);
        headerView.setOnBannerClick(new BannerView.OnBannerClick() {
            @Override
            public void setOnClick(int position) {
                Toast.makeText(getActivity().getApplicationContext(), "第几个：" + position, Toast.LENGTH_SHORT).show();
                id = String.valueOf(topStoriesBeen.get(position - 1).getId());
                gotoContent(id);
            }
        });
        mRecyclerView = (RecyclerView) view.findViewById(R.id.news_recyclerView);
        adapter = new HomeAdapter(getActivity(), storiesBeen);
        adapter.setHeaderView(headerView);
        datePosition.add(1);
        adapter.setDateList(dateList, datePosition);
        RecyclerView.LayoutManager manager = new GridLayoutManager(getActivity(), 1);
        mRecyclerView.setLayoutManager(manager);
        mRecyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(new HomeAdapter.OnItemClickListener() {
            @Override
            public void setOnClick(int position) {
                Toast.makeText(getActivity(), "第几个：" + position, Toast.LENGTH_SHORT).show();
                int realPosition=position-2;
                if (realPosition < storiesBeen.size()) {
                    id = String.valueOf(storiesBeen.get(realPosition).getId());
                } else {
                    for (int i = datePosition.size() - 1; i >= 0; i--) {
                        if (position > datePosition.get(i)) {
                            id = String.valueOf(totalBeen.get(position-storiesBeen.size()-2-i).getId());
                            Log.e("MainPosition", "setOnClick: " + position);
                            gotoContent(id);
                            return;
                        }
                    }
                }

                gotoContent(id);
            }
        });
        loadMore();
    }

    //进入内容主界面
    private void gotoContent(String id) {
        Intent intent = new Intent(getActivity(), ContentActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString("id", id);
        bundle.putStringArrayList(Constants.NEWS_ID_LIST, (ArrayList<String>) newsIds);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    private void addDates(int prePosition) {
        int position = datePosition.get(datePosition.size() - 1) + prePosition + 1;
        datePosition.add(position);
        Log.e("datePosition.size", "addDates: " + datePosition.get(datePosition.size() - 1) + "----prePosition:" + prePosition);
    }


}
