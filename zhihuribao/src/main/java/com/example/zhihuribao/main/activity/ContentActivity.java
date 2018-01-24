package com.example.zhihuribao.main.activity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.zhihuribao.R;
import com.example.zhihuribao.main.bean.Comment;
import com.example.zhihuribao.main.constant.Constants;
import com.example.zhihuribao.main.constant.ZhihuRibaoUrl;
import com.example.zhihuribao.main.fragment.ContentFragment;
import com.example.zhihuribao.main.http.AppClient;
import com.example.zhihuribao.main.utils.ContentNewsUntil;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by banker_test on 2017/5/9.
 */

@SuppressLint("SetJavaScriptEnabled")
public class ContentActivity extends AppCompatActivity implements ContentFragment.CallBackValues {
    private String id;
    private WebView webView;
    private String linkCss;
    private Toolbar toolbar;
    private ImageView contentImg;
    private TextView toolTitle;
    private CollapsingToolbarLayout toolbarLayout;
    private LinearLayout commentLayout;
    private List<Comment.CommentsBean> commentList;
    private ViewPager viewPager;
    private List<String> newsIdList;
    private String html;
    private SectionPagerAdapter sectionPagerAdapter;
    private boolean isFirst=true;
    private int curIndex=0;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_layout);
        commentList = new ArrayList<>();
        newsIdList = new ArrayList<>();
        getActivityData();
        initView();
        initViewPager();
//        webView = (WebView) findViewById(R.id.content_web);
//        // 启用javascript
//        webView.getSettings().setJavaScriptEnabled(true);
//        getHttpContent(id);
//        getHttpComment();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.search, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    //加载控件
    private void initView() {
        toolbar = (Toolbar) findViewById(R.id.toolbar_content);
        contentImg = (ImageView) findViewById(R.id.collapsing_img);
        toolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.toolbar_layout);
        sectionPagerAdapter = new SectionPagerAdapter(getSupportFragmentManager());
        viewPager = (ViewPager) findViewById(R.id.vp_content);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void initViewPager(){
        viewPager.setAdapter(sectionPagerAdapter);
        int position = newsIdList.indexOf(id);
        curIndex=position;
        viewPager.setCurrentItem(position);
        viewPager.addOnPageChangeListener(new MyOnPagerChangeListener());
    }

    //从上一个activity获取数据
    private void getActivityData() {
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        id = bundle.getString("id");
        newsIdList = bundle.getStringArrayList(Constants.NEWS_ID_LIST);
    }

    @Override
    public void sendMsg() {
        Glide.with(ContentActivity.this).load(Constants.IMGURLS.get(curIndex)).into(contentImg);
    }

    public class SectionPagerAdapter extends FragmentPagerAdapter {

        public SectionPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return ContentFragment.newInstance(newsIdList.get(position),newsIdList);
        }


        @Override
        public int getCount() {
            return newsIdList.size();
        }
    }

    //viewpager页面滑动监听事件
    class MyOnPagerChangeListener implements ViewPager.OnPageChangeListener{

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            curIndex=position;
            if (position==0||position==newsIdList.size()-1){
                sendMsg();
            }
            Log.e("onPageSelected", "onPageSelected: "+position);
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    }


    //从网络获取数据
    private void getHttpContent(String id) {
        String newsContentUrl = ZhihuRibaoUrl.NEWS_CONTENT_URL;

        AppClient.ApiContent apiContent = AppClient.retrofit(newsContentUrl).create(AppClient.ApiContent.class);
        Call<ContentNewsUntil> call = apiContent.getNewsContent(id);
        call.enqueue(new Callback<ContentNewsUntil>() {
            @Override
            public void onResponse(Call<ContentNewsUntil> call, Response<ContentNewsUntil> response) {
                html = response.body().getBody();
                String title = response.body().getTitle();
                String imgUrl = response.body().getImage();
                toolbarLayout.setTitle(title);
                toolbarLayout.setTitleEnabled(true);
                if (imgUrl == null || imgUrl.isEmpty()) {
                    contentImg.setVisibility(View.GONE);
                } else {
                    Glide.with(ContentActivity.this).load(imgUrl).into(contentImg);
                }
//                initUI(html);
//                getHttpComment();
            }

            @Override
            public void onFailure(Call<ContentNewsUntil> call, Throwable t) {

            }
        });

    }

    private void getHttpComment() {
        String commentUrl = ZhihuRibaoUrl.SHORT_COMMENT_HEAD;
        AppClient.ApiShortComment apiShortComment = AppClient.retrofit(commentUrl).create(AppClient.ApiShortComment.class);
        Call<Comment> shortCommentCall = apiShortComment.getShortComment(id);
        shortCommentCall.enqueue(new Callback<Comment>() {
            @Override
            public void onResponse(Call<Comment> call, Response<Comment> response) {
                commentList = response.body().getComments();
                /*
                int total = (commentList.size() < 5 ? commentList.size() : 5);
                for (int i = 0; i < total; i++) {
                    View commentView = LayoutInflater.from(ContentActivity.this).inflate(R.layout.comment_layout, null);
                    TextView commentText = (TextView) commentView.findViewById(R.id.tv_comment);
                    commentText.setMaxLines(2);
                    commentText.setText("\t\t" + commentList.get(i).getContent());
                    final ImageView userImg = (ImageView) commentView.findViewById(R.id.img_user);
//                    Glide.with(ContentActivity.this).load(commentList.get(i).getAvatar()).into(userImg);
                    //设置圆形图片
                    Glide.with(ContentActivity.this).load(commentList.get(i).getAvatar()).asBitmap().centerCrop().into(
                            new BitmapImageViewTarget(userImg) {
                                @Override
                                protected void setResource(Bitmap resource) {
                                    RoundedBitmapDrawable drawable = RoundedBitmapDrawableFactory.create(ContentActivity.this.getResources(), resource);
                                    drawable.setCircular(true);
                                    userImg.setImageDrawable(drawable);
                                }
                            }
                    );

                    TextView tvUser = (TextView) commentView.findViewById(R.id.tv_user);
                    tvUser.setText(commentList.get(i).getAuthor());
                    TextView tvTime = (TextView) commentView.findViewById(R.id.tv_time);
                    tvTime.setText(DateUtil.times(commentList.get(i).getTime()));
                    Log.e("time", "onResponse: " + commentList.get(i).getTime());
                    commentLayout.addView(commentView);
                }
                TextView tvMoreComment = new TextView(ContentActivity.this);
                tvMoreComment.setText("更多评论");
                tvMoreComment.setPadding(5, 15, 5, 15);
                tvMoreComment.setTextSize(20);
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT
                        , ViewGroup.LayoutParams.WRAP_CONTENT);
                tvMoreComment.setLayoutParams(params);
                tvMoreComment.setGravity(Gravity.CENTER);
                tvMoreComment.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        gotoActivity();
                    }
                });
                commentLayout.addView(tvMoreComment);
                */
                if (isFirst){
                    initViewPager();
                    isFirst=false;
                }else {
                    sectionPagerAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<Comment> call, Throwable t) {

            }
        });
    }



    private void gotoActivity() {
        Intent intent = new Intent(ContentActivity.this, CommentActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString("listId", id);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    //加载ui布局
    private void initUI(String html) {
        linkCss = "<link rel=\"stylesheet\"href=\"file:///android_asset/zhihu.css\" type=\"text/css\">";
        html = "<html><header>" + linkCss + "</header>" + html + "</body></html>";
        html = html.replace("<div class=\"img-place-holder\">", "");
        webView.loadDataWithBaseURL(null, html, "text/html", "utf-8", null);
        // 添加js交互接口类，并起别名 imagelistner
        webView.addJavascriptInterface(new JavascriptInterface(this), "imagelistner");
        webView.setWebViewClient(new MyWebViewClient());

    }

    private class MyWebViewClient extends WebViewClient {
        @Override
        public void onPageFinished(WebView view, String url) {
            view.getSettings().setJavaScriptEnabled(true);
            super.onPageFinished(view, url);
            // html加载完成之后，添加监听图片的点击js函数
            addImageClickListner();
//            getHttpComment();
        }

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
            return false;
        }
    }

    // 注入js函数监听
    private void addImageClickListner() {
        // 这段js函数的功能就是，遍历所有的img几点，并添加onclick函数，函数的功能是在图片点击的时候调用本地java接口并传递url过去
        webView.loadUrl("javascript:(function(){" +
                "var objs = document.getElementsByTagName(\"img\"); " +
                "for(var i=0;i<objs.length;i++)  " +
                "{"
                + "    objs[i].onclick=function()  " +
                "    {  "
                + "        window.imagelistner.openImage(this.src);  " +
                "    }  " +
                "}" +
                "})()");
    }

    // js通信接口
    public class JavascriptInterface {

        private Context context;

        public JavascriptInterface(Context context) {
            this.context = context;
        }

        @android.webkit.JavascriptInterface
        public void openImage(String img) {
            Bundle bundle = new Bundle();
            bundle.putString("imgUrl", img);
            Intent imgIntent = new Intent(ContentActivity.this, ImageShowActivity.class);
            imgIntent.putExtras(bundle);
            Log.e("img", img);
            startActivity(imgIntent);
        }
    }

}
