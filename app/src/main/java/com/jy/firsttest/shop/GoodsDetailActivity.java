package com.jy.firsttest.shop;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.webkit.WebView;
import android.widget.AbsListView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.jy.firsttest.shop.adapter.RlvGoodsDetailAttributeAdapter;
import com.jy.firsttest.shop.bean.AddCarBean;
import com.jy.firsttest.shop.bean.GoodsDetailBean;
import com.jy.firsttest.shop.net.ApiServer;
import com.youth.banner.Banner;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subscribers.ResourceSubscriber;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class GoodsDetailActivity extends AppCompatActivity implements View.OnClickListener {

    private Banner banner_goodsdetail;
    private int id;
    private String TAG = "GoodsActivity";
    private TextView txt_name;
    private TextView txt_brief;
    private TextView txt_price;
    private TextView txt_count;
    private TextView txt_right;
    private ImageView img_avatar;
    private TextView txt_nickname;
    private TextView txt_addtime;
    private TextView txt_content;
    private ImageView img_pic_url;
    private List<GoodsDetailBean.DataBeanX.AttributeBean> list = new ArrayList<>();
    private RlvGoodsDetailAttributeAdapter mAttributeAdapter;
    private RecyclerView rec_attribute;
    private WebView web_desc;
    private RelativeLayout relat_choose_count;
    private LinearLayout ll_bottom_lead;
    private TextView pup_tv_show_count;
    private PopupWindow popupWindow;
    private GoodsDetailBean alldata;
    private TextView txt_addtocart;
    private GoodsDetailBean.DataBeanX.InfoBean info;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goods_detail);
        id = getIntent().getIntExtra("id", 0);
        Log.d("goods", "onCreate: " + id);
        initData();
        initView();
    }

    //初始化控件
    private void initView() {
        banner_goodsdetail = (Banner) findViewById(R.id.banner_goodsdetail);
        txt_name = (TextView) findViewById(R.id.txt_name);
        txt_brief = (TextView) findViewById(R.id.txt_brief);
        txt_price = (TextView) findViewById(R.id.txt_price);
        txt_count = (TextView) findViewById(R.id.txt_count);
        txt_right = (TextView) findViewById(R.id.txt_right);
        img_avatar = (ImageView) findViewById(R.id.img_avatar);
        txt_nickname = (TextView) findViewById(R.id.txt_nickname);
        txt_addtime = (TextView) findViewById(R.id.txt_addtime);
        txt_content = (TextView) findViewById(R.id.txt_content);
        img_pic_url = (ImageView) findViewById(R.id.img_pic_url);
        rec_attribute = (RecyclerView) findViewById(R.id.rec_attribute);
        web_desc = (WebView) findViewById(R.id.web_desc);
        relat_choose_count = (RelativeLayout) findViewById(R.id.relat_choose_count);
        relat_choose_count.setOnClickListener(this);
        ll_bottom_lead = (LinearLayout) findViewById(R.id.ll_bottom_lead);
        txt_addtocart = (TextView) findViewById(R.id.txt_addtocart);
        txt_addtocart.setOnClickListener(this);
    }

    //显示弹框的方法
    private void showPup() {
        //【一】把弹框整出来
        //1.创建弹窗视图
        View view = LayoutInflater.from(this).inflate(R.layout.detail_pup, null);
        //2.讲弹窗视图放入 弹窗里面（三要素：1.布局  2.宽  3.高）
        popupWindow = new PopupWindow(view, GridView.LayoutParams.MATCH_PARENT, GridView.LayoutParams.WRAP_CONTENT);
        //3.设置弹窗 显示的位置
        popupWindow.showAtLocation(ll_bottom_lead, Gravity.BOTTOM, 0, 0);

        //【二】获取弹框里的控件
        ImageView pup_iv = view.findViewById(R.id.pup_iv);
        TextView pup_tv_add_count = view.findViewById(R.id.pup_tv_add_count);
        pup_tv_show_count = view.findViewById(R.id.pup_tv_show_count);
        TextView pup_tv_reduce_count = view.findViewById(R.id.pup_tv_reduce_count);
        TextView pup_tv_price = view.findViewById(R.id.pup_tv_price);
        TextView pup_tv_close = view.findViewById(R.id.pup_tv_close);
        pup_tv_close.setOnClickListener(this);
        pup_tv_reduce_count.setOnClickListener(this);
        pup_tv_add_count.setOnClickListener(this);

        //【三】获取网络数据，并且将数据加载到对应的控件上，在
        // 设置数据
        if (alldata != null) {
            Glide.with(this).load(alldata.getData().getInfo().getPrimary_pic_url()).into(pup_iv);
            pup_tv_price.setText(alldata.getData().getInfo().getRetail_price() + "");
        }


    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.relat_choose_count:
                //显示弹框
                showPup();
                break;
            case R.id.pup_tv_close:
                //弹框 中 关闭按钮
                if (popupWindow != null) {
                    popupWindow.dismiss();
                }
                break;
            case R.id.pup_tv_reduce_count:
                //弹框中减少个数
                String s = pup_tv_show_count.getText().toString();
                int num = Integer.parseInt(s);
                if (num > 1) {
                    num--;
                    pup_tv_show_count.setText(num + "");
                }
                break;
            case R.id.pup_tv_add_count:
                //弹框中增加个数
                String s2 = pup_tv_show_count.getText().toString();
                int num2 = Integer.parseInt(s2);
                if (num2 <= info.getGoods_number()) {
                    num2++;
                    pup_tv_show_count.setText(num2 + "");
                }
                break;
            case R.id.txt_addtocart:
                //点击加入购物车，显示弹框，加载数据
                addShoppingCar();
                break;
        }
    }

    //加入购物车的点击事件
    private void addShoppingCar() {
        Log.d("tag", "===> " + "进入addShoppingCar方法");
        if (popupWindow != null) {
            Log.d("tag", "===> " + "popupWindow不为null");
            if (alldata != null) {
                Log.d("tag", "===> " + "alldata不为null");
                List<GoodsDetailBean.DataBeanX.ProductListBean> productList = alldata.getData().getProductList();
                String s = pup_tv_show_count.getText().toString();
                int number = Integer.parseInt(s);
                Log.d("tag", "===> " + "选择的个数number" + number);
                for (int i = 0; i < productList.size(); i++) {
                    //1.创建retrofit
                    Retrofit retrofit = new Retrofit.Builder()
                            .baseUrl(ApiServer.url)
                            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                            .addConverterFactory(GsonConverterFactory.create())
                            .build();
                    //2.创建接口对象
                    ApiServer apiServer = retrofit.create(ApiServer.class);

                    //3.调用方法x
                    Flowable<AddCarBean> addCarBeanFlowable = apiServer.addCarInfo(productList.get(i).getGoods_id(), number, productList.get(i).getId());

                    //4.执行操作
                    addCarBeanFlowable
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribeWith(new ResourceSubscriber<AddCarBean>() {
                                @Override
                                public void onNext(AddCarBean addCarBean) {
                                    if (addCarBean.getErrno() == 0) {
                                        Log.d("tag", "===> " + "响应码为：" + addCarBean.getErrno() + "表示成功！");
                                        Toast.makeText(GoodsDetailActivity.this, "购物车添加成功", Toast.LENGTH_LONG).show();
                                    }
                                }

                                @Override
                                public void onError(Throwable t) {
                                    Log.d("tag", "===> " + t.getMessage());
                                }

                                @Override
                                public void onComplete() {

                                }
                            });


                }


            }
        } else {
            showPup();
        }

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
                        info = goodsDetailBean.getData().getInfo();
                        alldata = goodsDetailBean;
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
        //配置评价头像，姓名，时间，内容,内容图片
        RequestOptions requestOptions = new RequestOptions().circleCrop();//设置圆形图片
        Glide.with(this).load(goodsDetailBean.getData().getComment().getData().getAvatar()).apply(requestOptions).into(img_avatar);
        txt_nickname.setText(goodsDetailBean.getData().getComment().getData().getNickname());
        txt_addtime.setText(goodsDetailBean.getData().getComment().getData().getAdd_time());
        txt_content.setText(goodsDetailBean.getData().getComment().getData().getContent());
        Glide.with(this).load(goodsDetailBean.getData().getComment().getData().getPic_list().get(0).getPic_url()).into(img_pic_url);
        //RecyclerView适配器
        //1.设置全局变量的数据，适配器【最上面已经声明ok】
        //2.给RecyclerView控件设置布局管理器
        rec_attribute.setLayoutManager(new LinearLayoutManager(this));
//        rec_attribute.addItemDecoration(new DividerItemDecoration(this,LinearLayoutManager.VERTICAL));
        mAttributeAdapter = new RlvGoodsDetailAttributeAdapter(this, list);
        rec_attribute.setAdapter(mAttributeAdapter);
        List<GoodsDetailBean.DataBeanX.AttributeBean> attribute = goodsDetailBean.getData().getAttribute();
        list.addAll(attribute);
        mAttributeAdapter.notifyDataSetChanged();
        String desc = goodsDetailBean.getData().getInfo().getGoods_desc();
        desc = "<html><head><style type='text/css'>img{max-width:100%;height:auto}</style></head><body>" + desc + "</body></html>";
        web_desc.loadData(desc, "text/html", "utf-8");
    }


    //详情里的轮播图
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
