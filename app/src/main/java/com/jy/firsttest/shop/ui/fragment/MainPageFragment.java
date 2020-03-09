package com.jy.firsttest.shop.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.jy.firsttest.shop.R;
import com.jy.firsttest.shop.adapter.HomeAdapter;
import com.jy.firsttest.shop.bean.HomeBean;
import com.jy.firsttest.shop.net.ApiServer;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subscribers.ResourceSubscriber;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainPageFragment extends Fragment {


    private RecyclerView rv;

    private HomeAdapter adapter;
    private ArrayList<HomeBean.DataBean.BannerBean> banners;
    private ArrayList<HomeBean.DataBean.NewGoodsListBean> list;


    public static MainPageFragment newInstance() {
        MainPageFragment fragment = new MainPageFragment();
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.fragment_main_page, container, false);
        return inflate;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
        initData();
    }

    private void initData() {
        //获取retrofit对象
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiServer.url)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        //获取接口对象
        ApiServer apiService = retrofit.create(ApiServer.class);

        //调用接口方法
        Flowable<HomeBean> homeData = apiService.getHomeData();

        //执行请求
        homeData.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new ResourceSubscriber<HomeBean>() {
                    @Override
                    public void onNext(HomeBean homeBean) {
                        List<HomeBean.DataBean.BannerBean> banner = homeBean.getData().getBanner();
                        List<HomeBean.DataBean.NewGoodsListBean> newGoodsList = homeBean.getData().getNewGoodsList();

                        banners.addAll(banner);
                        list.addAll(newGoodsList);
                        adapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onError(Throwable t) {
                        Toast.makeText(getActivity(), "网络错误：" + t.getMessage(), Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    private void initView(View view) {
        rv = view.findViewById(R.id.rv);
        rv.setLayoutManager(new LinearLayoutManager(getActivity()));
        rv.addItemDecoration(new DividerItemDecoration(getActivity(), RecyclerView.VERTICAL));
        banners = new ArrayList<>();
        list = new ArrayList<>();

        adapter = new HomeAdapter(getActivity(), banners, list);
        rv.setAdapter(adapter);
    }


}
