package com.iuoon.app.reader.net;

import com.iuoon.app.reader.model.XUser;

import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import rx.Observable;

/**
 * @GET 表明方法是 get请求
 * "/api" 请求的接口
 * @Query 表示这是一个参数
 * @Description :通信请求api接口
 * Created by iuoon on 2016/8/17.
 * @version  v1.0.0
 */
public interface HttpApi {
    // 登陆
    @POST("app/doLogin")
    Observable<UserResp> login(@Body XUser user);

    @POST("app/doRegister")
    Observable<UserResp> register(@Body XUser user);

    @GET("getNotice")
    Observable<UserResp> getNotice();
}
