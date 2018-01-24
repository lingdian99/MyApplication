package com.example.kaiyan.http;

import com.example.kaiyan.bean.HomepageBeen;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Url;

/**
 * Created by wys10 on 17/12/14.
 */

public class AppClient {
    public static Retrofit retrofit(String url){
        Retrofit retrofit=new Retrofit.Builder().baseUrl(url)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit;
    }

    public interface ApiHomepage{
        @GET()
        Call<HomepageBeen> getHomepage(@Url String url);
    }
}
