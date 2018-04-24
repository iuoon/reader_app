package com.iuoon.app.reader.net;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.jackson.JacksonConverterFactory;

/**
 * Created by iuoon on 2018/3/19.
 */

public class RRetrofit {
    public static String BASE_URL = "http://www.iuoon.com:9002/YNServer/";
    private static final int DEFAULT_TIMEOUT = 3;


    public static <T> T create(final Class<T> cls) {
        Retrofit.Builder builder = new Retrofit.Builder();
        OkHttpClient.Builder httpBuilder = new OkHttpClient.Builder();
        //设置超时时间
        OkHttpClient client=httpBuilder.readTimeout(DEFAULT_TIMEOUT, TimeUnit.MINUTES)
                .connectTimeout(DEFAULT_TIMEOUT, TimeUnit.MINUTES).writeTimeout(DEFAULT_TIMEOUT, TimeUnit.MINUTES) //设置超时
                .build();
        Retrofit retrofit = builder
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(JacksonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        return retrofit.create(cls);
    }
}
