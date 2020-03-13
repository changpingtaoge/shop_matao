package com.jy.firsttest.shop.net;

import android.widget.EditText;

import com.jy.firsttest.shop.bean.HomeBean;
import com.jy.firsttest.shop.bean.TopicBean;
import com.jy.firsttest.shop.bean.UserBean;
import com.jy.firsttest.shop.bean.UserRegisterBean;

import io.reactivex.Flowable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiServer {
    String url = "https://cdwan.cn/api/";

    @GET("topic/list?")
    Flowable<TopicBean> getTopic(@Query("page") int page, @Query("size") int size);

    @GET("index")
    Flowable<HomeBean> getHomeData();

    @POST("auth/login")
    @FormUrlEncoded
    Flowable<UserBean> doLogin(@Field("nickname") String nickname, @Field("password") String password);

    @POST("auth/register")
    @FormUrlEncoded
    Flowable<UserRegisterBean> doRegister(@Field("nickname") String nickname, @Field("password") String password);


}
