package com.example.zhihuribao.main.activity;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.example.zhihuribao.R;
import com.example.zhihuribao.main.adapter.MultiItemTypeSupport;
import com.example.zhihuribao.main.adapter.SectionAdapter;
import com.example.zhihuribao.main.adapter.SectionSupport;
import com.example.zhihuribao.main.adapter.ViewHolder;

import java.util.ArrayList;
import java.util.List;

public class DrawerActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private RecyclerView recyclerView;
    private List<String> mDatas=new ArrayList<>();
    private String TAG= "DrawerActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawer);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        for (int i=0;i<10;i++){
            mDatas.add("who:"+i);
        }

        initRecyclerView();

//        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
//        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.drawer, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void initRecyclerView(){
        recyclerView= (RecyclerView) findViewById(R.id.mReyclerView);
        RecyclerView.LayoutManager manager = new GridLayoutManager(this, 1);
        recyclerView.setLayoutManager(manager);
//        recyclerView.setAdapter(new CommonAdapter<String>(this, R.layout.news_item, mDatas) {
//            @Override
//            public void convert(ViewHolder viewHolder, String s) {
//                TextView textView=viewHolder.getView(R.id.item_text);
//                textView.setText(s);
//                Log.e("convert","convert");
//            }
//        });

        MultiItemTypeSupport multiItemTypeSupport=new MultiItemTypeSupport<String>() {
            @Override
            public int getLayoutId(int itemType) {
                Log.e(TAG, "getLayoutId: " );
                if (itemType==0){
                    return R.layout.news_item_date;
                }else {
                    return R.layout.news_item;
                }

            }

            @Override
            public int getItemViewType(int postion, String datas) {
                if (postion==3){
                    return 3;
                }
                if (postion==1){
                    return 0;
                }else {
                    return 1;
                }
            }

        };
//
//        recyclerView.setAdapter(new MultiItemCommonAdapter<String>(this,mDatas,multiItemTypeSupport) {
//            @Override
//            public void convert(ViewHolder viewHolder, Object o) {
//
//            }
//
//            @Override
//            public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
//
//            }
//        });

        SectionSupport<String> sectionSupport=new SectionSupport<String>() {
            @Override
            public int sectionHeaderLayoutId() {
                return R.layout.news_list_footer;
            }

            @Override
            public int sectionTitleTextViewId() {
                return 0;
            }

            @Override
            public String getTitle(String s) {
                return null;
            }
        };
        recyclerView.setAdapter(new SectionAdapter<String>(this,R.layout.news_item,mDatas,sectionSupport,multiItemTypeSupport) {
            @Override
            public void convert(ViewHolder viewHolder, Object o) {

            }

            @Override
            public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

            }
        });
    }
}
