package com.jy.firsttest.shop.net;

import com.jy.firsttest.shop.bean.AddCarBean;
import com.jy.firsttest.shop.bean.CarInfo;
import com.jy.firsttest.shop.bean.DelCarInfo;
import com.jy.firsttest.shop.bean.GoodsDetailBean;
import com.jy.firsttest.shop.bean.HomeBean;
import com.jy.firsttest.shop.bean.LoginBean;
import com.jy.firsttest.shop.bean.RegisterBean;
import com.jy.firsttest.shop.bean.TopicBean;

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
    Flowable<LoginBean> doLogin(@Field("nickname") String nickname, @Field("password") String password);

    @POST("auth/register")
    @FormUrlEncoded
    Flowable<RegisterBean> doRegister(@Field("nickname") String nickname, @Field("password") String password);

    @GET("goods/detail?")
    Flowable<GoodsDetailBean> getGoodsDetail(@Query("id") int id);

    // 添加购物车
    // https://cdwan.cn/api/cart/add
    @POST("cart/add")
    @FormUrlEncoded
    Flowable<AddCarBean> addCarInfo(@Field("goodsId") int goodsId,
                                    @Field("number") int number,
                                    @Field("productId") int productId);

    // 获取购物车数据
    //https://cdwan.cn/api/cart/index
    @GET("cart/index")
    Flowable<CarInfo> getCarDtate();


    @POST("cart/delete")
    @FormUrlEncoded
    Flowable<DelCarInfo> delCarInfo(@Field("productIds") int productIds);

}
