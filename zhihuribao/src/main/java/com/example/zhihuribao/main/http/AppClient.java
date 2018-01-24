package com.example.zhihuribao.main.http;


import com.example.zhihuribao.main.MyApplication;
import com.example.zhihuribao.main.bean.Comment;
import com.example.zhihuribao.main.utils.BeforeNewsList;
import com.example.zhihuribao.main.utils.ContentNewsUntil;
import com.example.zhihuribao.main.utils.NetworkUtil;
import com.example.zhihuribao.main.utils.ThemeNewsUntil;
import com.example.zhihuribao.main.utils.ThemeUntil;
import com.example.zhihuribao.main.utils.TodayNewsList;
import com.example.zhihuribao.main.utils.SplashUntil;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Url;

/**
 * Created by banker_test on 2017/4/12.
 */

public class AppClient {

    public static Retrofit retrofit(String url){
        File cacheFile=new File(MyApplication.getContextObject().getCacheDir(),"HttpCache");
        int cacheSize=20*1024*1024;
        Cache cache=new Cache(cacheFile,cacheSize);
        OkHttpClient httpClient=new OkHttpClient.Builder()
                .connectTimeout(10, TimeUnit.SECONDS)//连接设置超时
                .readTimeout(10,TimeUnit.SECONDS)//设置读取超时
                .writeTimeout(10,TimeUnit.SECONDS)//写入超时
                .addNetworkInterceptor(interceptor)
                .cache(cache)
                .build();

        Retrofit mRetrofit=new Retrofit.Builder().baseUrl(url)
                .client(httpClient)//把OkhttpClient添加进来
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return mRetrofit;
    }

    //设置拦截器
    static Interceptor interceptor=new Interceptor() {

        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request=chain.request();
            //网上很多示例代码都对在request请求前对其进行无网的判断，其实无需判断，无网自动访问缓存
            if (!NetworkUtil.isNetworkAvailable(MyApplication.getContextObject())){
                request=request.newBuilder()
                        .cacheControl(CacheControl.FORCE_CACHE)
                        .build();
            }

            Response response=chain.proceed(request);

            if (NetworkUtil.isNetworkAvailable(MyApplication.getContextObject())){
                int maxAge=60;//缓存失效时间，单位是秒
                return response.newBuilder()
                        .removeHeader("Pragma")
                        .header("Cache-Control","public ,max-age=" + maxAge)
                        .build();
            }
            return response;
        }
    };

    //获取首页图片
    public interface ApiStores{
        @GET()
        Call<SplashUntil> getImg(@Url String url);
    }

    //获取今日数据
    public interface ApiNewsList{
        @GET("{dates}")
        Call<TodayNewsList> getNews(@Path("dates") String dates);
    }

    //获取往日数据
    public interface ApiBeforeNews{
        @GET("{dates}")
        Call<BeforeNewsList> getNews(@Path("dates") String dates);
    }

    //获取内容数据
    public interface ApiContent{
        @GET("{id}")
        Call<ContentNewsUntil> getNewsContent(@Path("id") String id);
    }

    //获取主题日报
    public interface ApiThemeList{
        @GET()
        Call<ThemeUntil> getThemeList(@Url String url);
    }

    //获取主题日报列表
    public interface ApiThemeNewsList{
        @GET("{id}")
        Call<ThemeNewsUntil> getThemeNewsList(@Path("id") String id);
    }

    //获取短评论
    public interface ApiShortComment{
        @GET("{id}/short-comments")
        Call<Comment> getShortComment(@Path("id") String id);
    }

    //获取长评论
    public interface ApiLongComment{
        @GET("{id}/long-comments")
        Call<Comment> getLongComment(@Path("id") String id);
    }

}
