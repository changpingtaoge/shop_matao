package com.jy.firsttest.shop.net;

import com.jy.firsttest.shop.bean.TopicBean;

import io.reactivex.Flowable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiServer {
    String url = "https://cdwan.cn/api/";

    @GET("topic/list?")
    Flowable<TopicBean> getTopic(@Query("page") int page, @Query("size") int size);
}
