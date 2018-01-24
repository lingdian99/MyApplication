package com.example.zhihuribao.main;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.zhihuribao.R;
import com.example.zhihuribao.main.activity.ImageShowActivity;
import com.example.zhihuribao.main.constant.ZhihuRibaoUrl;
import com.example.zhihuribao.main.http.AppClient;
import com.example.zhihuribao.main.utils.ContentNewsUntil;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by banker_test on 2017/5/9.
 */

@SuppressLint("SetJavaScriptEnabled")
public class ScrollingActivity extends AppCompatActivity{
    private String id;
    private WebView webView;
    private String linkCss;
    private Toolbar toolbar;
    private ImageView contentImg;
    private TextView toolTitle;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_layout);
        initUi();
        webView= (WebView) findViewById(R.id.content_web);
        // 启用javascript
        webView.getSettings().setJavaScriptEnabled(true);
        getDatas();
        getHttpContent();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.search,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    //加载控件
    private void initUi(){
        toolbar= (Toolbar) findViewById(R.id.toolbar_content);
        contentImg= (ImageView) findViewById(R.id.collapsing_img);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    //从上一个activity获取数据
    private void getDatas() {
        Intent intent=getIntent();
        Bundle bundle=intent.getExtras();
        id=bundle.getString("id");
        Log.e("id",id);
    }

    //从网络获取数据
    private void getHttpContent(){
        String url= ZhihuRibaoUrl.NEWS_CONTENT_URL;
        AppClient.ApiContent apiContent=AppClient.retrofit(url).create(AppClient.ApiContent.class);
        Call<ContentNewsUntil> call=apiContent.getNewsContent(id);
        call.enqueue(new Callback<ContentNewsUntil>() {
            @Override
            public void onResponse(Call<ContentNewsUntil> call, Response<ContentNewsUntil> response) {
                String html=response.body().getBody();
                String title=response.body().getTitle();
                String imgUrl=response.body().getImage();
                Glide.with(ScrollingActivity.this).load(imgUrl).into(contentImg);
                initUI(html);
                Log.e("html",html);
                Log.e("title",title);
            }

            @Override
            public void onFailure(Call<ContentNewsUntil> call, Throwable t) {

            }
        });
    }

    //加载ui布局
    private void initUI(String html){
        linkCss="<link rel=\"stylesheet\"href=\"file:///android_asset/zhihu.css\" type=\"text/css\">";
        html = "<html><header>" + linkCss + "</header>" + html + "</body></html>";
        html = html.replace("<div class=\"img-place-holder\">", "");
        webView.loadDataWithBaseURL(null,html,"text/html","utf-8",null);
//        webView.loadUrl("http://a.mp.uc.cn/article.html?uc_param_str=frdnsnpfvecpntnwprdssskt&client=ucweb&wm_aid=c51bcf6c1553481885da371a16e33dbe&wm_id=482efebe15ed4922a1f24dc42ab654e6&pagetype=share&btifl=100");
        // 添加js交互接口类，并起别名 imagelistner
        webView.addJavascriptInterface(new JavascriptInterface(this), "imagelistner");
        webView.setWebViewClient(new MyWebViewClient());
    }

    private class MyWebViewClient extends  WebViewClient{
        @Override
        public void onPageFinished(WebView view, String url) {
            view.getSettings().setJavaScriptEnabled(true);
            super.onPageFinished(view, url);
            // html加载完成之后，添加监听图片的点击js函数
            addImageClickListner();
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
            Bundle bundle=new Bundle();
            bundle.putString("imgUrl",img);
            Intent imgIntent=new Intent(ScrollingActivity.this,ImageShowActivity.class);
            imgIntent.putExtras(bundle);
            Log.e("img",img);
            startActivity(imgIntent);
        }
    }

}
