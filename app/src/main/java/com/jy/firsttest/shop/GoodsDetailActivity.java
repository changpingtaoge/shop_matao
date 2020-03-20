package com.jy.firsttest.shop;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.jy.firsttest.shop.bean.GoodsDetailBean;
import com.jy.firsttest.shop.net.ApiServer;
import com.youth.banner.Banner;
import com.youth.banner.loader.ImageLoader;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subscribers.ResourceSubscriber;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class GoodsDetailActivity extends AppCompatActivity {

    private Banner banner_goodsdetail;
    private int id;
    private String TAG = "GoodsActivity";
    private TextView txt_name;
    private TextView txt_brief;
    private TextView txt_price;
    private TextView txt_count;
    private TextView txt_right;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goods_detail);
        id = getIntent().getIntExtra("id", 0);
        Log.d("goods", "onCreate: " + id);
        initData();
        initView();

    }

    private void initView() {
        banner_goodsdetail = (Banner) findViewById(R.id.banner_goodsdetail);
        txt_name = (TextView) findViewById(R.id.txt_name);
        txt_brief = (TextView) findViewById(R.id.txt_brief);
        txt_price = (TextView) findViewById(R.id.txt_price);
        txt_count = (TextView) findViewById(R.id.txt_count);
        txt_right = (TextView) findViewById(R.id.txt_right);
    }


    @SuppressLint("CheckResult")
    private void initData() {
        //1.创建retrofit对象
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiServer.url)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        //2.创建接口对象
        ApiServer apiServer = retrofit.create(ApiServer.class);

        //3.调用方法
        Flowable<GoodsDetailBean> goodsDetail = apiServer.getGoodsDetail(id);

        //4.执行操作
        goodsDetail
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new ResourceSubscriber<GoodsDetailBean>() {
                    @Override
                    public void onNext(GoodsDetailBean goodsDetailBean) {
                        GoodsDetailBean.DataBeanX data = goodsDetailBean.getData();
                        Log.d(TAG, "onNext: " + data.toString());
                        fillData(goodsDetailBean);
                    }

                    @Override
                    public void onError(Throwable t) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });


    }

    //填充数据
    private void fillData(GoodsDetailBean goodsDetailBean) {
        //设置banner轮播图
        List<GoodsDetailBean.DataBeanX.GalleryBean> banners = goodsDetailBean.getData().getGallery();
        Log.d(TAG, "fillData: ");
        initBanner(banners);
        //填充名字，brief，价钱
        txt_name.setText(goodsDetailBean.getData().getInfo().getName());
        txt_brief.setText(goodsDetailBean.getData().getInfo().getGoods_brief());
        txt_price.setText("￥" + goodsDetailBean.getData().getInfo().getRetail_price());
        //配置评价数据
        txt_count.setText("评价(" + goodsDetailBean.getData().getComment().getCount() + ")");

    }

    private void initBanner(List<GoodsDetailBean.DataBeanX.GalleryBean> banners) {
        banner_goodsdetail.setImages(banners).setImageLoader(new ImageLoader() {
            @Override
            public void displayImage(Context context, Object path, ImageView imageView) {
                GoodsDetailBean.DataBeanX.GalleryBean banner = (GoodsDetailBean.DataBeanX.GalleryBean) path;
                String img_url = banner.getImg_url();
                Log.d("img", "displayImage: " + img_url);
                Glide.with(context).load(img_url).into(imageView);
            }
        }).start();
    }


}
