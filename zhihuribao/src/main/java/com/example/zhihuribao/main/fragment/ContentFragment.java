package com.example.zhihuribao.main.fragment;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.Fragment;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.example.zhihuribao.R;
import com.example.zhihuribao.main.activity.CommentActivity;
import com.example.zhihuribao.main.activity.ImageShowActivity;
import com.example.zhihuribao.main.bean.Comment;
import com.example.zhihuribao.main.constant.Constants;
import com.example.zhihuribao.main.constant.ZhihuRibaoUrl;
import com.example.zhihuribao.main.http.AppClient;
import com.example.zhihuribao.main.utils.ContentNewsUntil;
import com.example.zhihuribao.main.utils.DateUtil;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by banker_test on 2017/9/14.
 */

public class ContentFragment extends Fragment {
    private String id;
    private WebView webView;
    private String linkCss;
    private Toolbar toolbar;
    private ImageView contentImg;
    private TextView toolTitle;
    private CollapsingToolbarLayout toolbarLayout;
    private LinearLayout commentLayout;
    private List<Comment.CommentsBean> commentList;
    private View view;
    private static final String BUNDLE_ID = "id";
    private static final String BUNDLE_NEWS_ID_LIST="newIdList";
    private CallBackValues callBackValues;
    private String html;
    private List<String> newIdList;

    public static Fragment newInstance(String id,List<String> newsIdList) {
        ContentFragment fragment = new ContentFragment();
        Bundle args = new Bundle();
        args.putString(BUNDLE_ID, id);
        args.putStringArrayList(BUNDLE_NEWS_ID_LIST, (ArrayList<String>) newsIdList);
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.comment_fragment, container, false);
        commentList = new ArrayList<>();
        initView();
        webView = (WebView) view.findViewById(R.id.content_web);
        // 启用javascript
        webView.getSettings().setJavaScriptEnabled(true);
        getDatas();
        getHttpContent();
        return view;
    }

    private void getDatas() {
        id = getArguments().getString(BUNDLE_ID);
        html=getArguments().getString(Constants.NEW_CONTENT_HTML);
//        Log.e("contentHtml", "onResponse: "+html);
        newIdList=getArguments().getStringArrayList(BUNDLE_NEWS_ID_LIST);
        initUI(html);
    }

    //加载控件
    private void initView() {
        commentLayout= (LinearLayout) view.findViewById(R.id.ll_content);
    }
    //从网络获取数据
    private void getHttpContent() {
        String newsContentUrl = ZhihuRibaoUrl.NEWS_CONTENT_URL;
        AppClient.ApiContent apiContent = AppClient.retrofit(newsContentUrl).create(AppClient.ApiContent.class);
        Call<ContentNewsUntil> call = apiContent.getNewsContent(id);
        call.enqueue(new Callback<ContentNewsUntil>() {
            @Override
            public void onResponse(Call<ContentNewsUntil> call, Response<ContentNewsUntil> response) {
                String html = response.body().getBody();
                String title = response.body().getTitle();
                String imgUrl = response.body().getImage();
                int index=newIdList.indexOf(id);
                Constants.IMGURLS.put(index,imgUrl);
                callBackValues.sendMsg();
                initUI(html);

            }

            @Override
            public void onFailure(Call<ContentNewsUntil> call, Throwable t) {

            }
        });

    }

    //加载ui布局
    private void initUI(String html) {
        linkCss = "<link rel=\"stylesheet\"href=\"file:///android_asset/zhihu.css\" type=\"text/css\">";
        html = "<html><header>" + linkCss + "</header>" + html + "</body></html>";
        html = html.replace("<div class=\"img-place-holder\">", "");
        webView.loadDataWithBaseURL(null, html, "text/html", "utf-8", null);
        // 添加js交互接口类，并起别名 imagelistner
        webView.addJavascriptInterface(new JavascriptInterface(getContext()), "imagelistner");
        webView.setWebViewClient(new MyWebViewClient());
    }

    private class MyWebViewClient extends WebViewClient {
        @Override
        public void onPageFinished(WebView view, String url) {
            view.getSettings().setJavaScriptEnabled(true);
            super.onPageFinished(view, url);
            // html加载完成之后，添加监听图片的点击js函数
            addImageClickListner();
            getHttpComment();
        }

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
            return false;
        }
    }

    private void getHttpComment() {
        String commentUrl = ZhihuRibaoUrl.SHORT_COMMENT_HEAD;
        AppClient.ApiShortComment apiShortComment = AppClient.retrofit(commentUrl).create(AppClient.ApiShortComment.class);
        Call<Comment> shortCommentCall = apiShortComment.getShortComment(id);
        shortCommentCall.enqueue(new Callback<Comment>() {
            @Override
            public void onResponse(Call<Comment> call, Response<Comment> response) {
                commentList = response.body().getComments();
                int total = (commentList.size() < 5 ? commentList.size() : 5);
                for (int i = 0; i < total; i++) {
                    View commentView = LayoutInflater.from(getActivity()).inflate(R.layout.comment_layout, null);
                    TextView commentText = (TextView) commentView.findViewById(R.id.tv_comment);
                    commentText.setMaxLines(2);
                    commentText.setText("\t\t" + commentList.get(i).getContent());
                    final ImageView userImg = (ImageView) commentView.findViewById(R.id.img_user);
//                    Glide.with(ContentActivity.this).load(commentList.get(i).getAvatar()).into(userImg);
                    //设置圆形图片
                    Glide.with(getContext()).load(commentList.get(i).getAvatar()).asBitmap().centerCrop().into(
                            new BitmapImageViewTarget(userImg) {
                                @Override
                                protected void setResource(Bitmap resource) {
                                    RoundedBitmapDrawable drawable = RoundedBitmapDrawableFactory.create(getActivity().getResources(), resource);
                                    drawable.setCircular(true);
                                    userImg.setImageDrawable(drawable);
                                }
                            }
                    );

                    TextView tvUser = (TextView) commentView.findViewById(R.id.tv_user);
                    tvUser.setText(commentList.get(i).getAuthor());
                    TextView tvTime = (TextView) commentView.findViewById(R.id.tv_time);
                    tvTime.setText(DateUtil.times(commentList.get(i).getTime()));
                    commentLayout.addView(commentView);
                }
                TextView tvMoreComment = new TextView(getContext());
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

            }

            @Override
            public void onFailure(Call<Comment> call, Throwable t) {

            }
        });
    }

    private void gotoActivity() {
        Intent intent = new Intent(getActivity(), CommentActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString("listId", id);
        intent.putExtras(bundle);
        startActivity(intent);
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
            Intent imgIntent = new Intent(getActivity(), ImageShowActivity.class);
            imgIntent.putExtras(bundle);
            startActivity(imgIntent);
        }
    }

    public interface CallBackValues{
        public void sendMsg();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        callBackValues= (CallBackValues) getActivity();
    }

}
