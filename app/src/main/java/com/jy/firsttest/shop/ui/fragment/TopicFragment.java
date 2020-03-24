package com.jy.firsttest.shop.ui.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.jy.firsttest.shop.R;
import com.jy.firsttest.shop.adapter.RlvTopicAdapter;
import com.jy.firsttest.shop.bean.TopicBean;
import com.jy.firsttest.shop.net.ApiServer;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;

import java.util.ArrayList;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subscribers.ResourceSubscriber;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;


public class TopicFragment extends Fragment {

    private static final String TAG = "TAG";
    private RecyclerView mRlv;
    private SmartRefreshLayout mSrl;
    private int page = 1;
    private int size = 10;
    private RlvTopicAdapter mAdapter;
    private int mTotalPages;

    public static TopicFragment newInstance() {
        TopicFragment fragment = new TopicFragment();
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.fragment_topic, container, false);
        return inflate;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        initView(view);
        initData();
        super.onViewCreated(view, savedInstanceState);
    }
    @SuppressLint("CheckResult")
    private void initData() {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(ApiServer.url)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ApiServer apiServer = retrofit.create(ApiServer.class);
        apiServer.getTopic(page, size)
                .subscribeOn(Schedulers.io())                   //被观察者在子线程执行，这里可以newThread(),但是经常用io()，这里有了线程池，效率更高,数据访问在子线程【执行一次】
                .observeOn(AndroidSchedulers.mainThread())      //数据处理在主线程【线程切换】   【观察者在哪里响应可以写多个】
                .subscribeWith(new ResourceSubscriber<TopicBean>() {        //订阅
                    @Override
                    public void onNext(TopicBean topicBean) {
                        Log.d(TAG, "onNext: "+topicBean.getData().toString());
                        //将网络数据异步回来后塞入到界面

                        //网络数据异步回来后塞到界面中展示
                        if (topicBean != null && topicBean.getErrno() == 0
                                && topicBean.getData() != null && topicBean.getData().getData() != null
                                && topicBean.getData().getData().size() > 0) {
                            mTotalPages = topicBean.getData().getTotalPages();
                            mAdapter.addData(topicBean.getData().getData());
                        }
                        hideHeader();
                    }

                    @Override
                    public void onError(Throwable t) {
                        Log.d(TAG, "onError: "+t.toString());
                        hideHeader();
                    }

                    @Override
                    public void onComplete() {

                    }
                });


    }
    private void hideHeader() {
        mSrl.finishRefresh();
        mSrl.finishLoadMore();
    }
    private void initView(View view) {
        mRlv = view.findViewById(R.id.rlv);
        mSrl = view.findViewById(R.id.srl);
        //布局管理器，适配器adapter
        mRlv.setLayoutManager(new LinearLayoutManager(getContext()));
        ArrayList<TopicBean.DataBeanX.DataBean> list = new ArrayList<>();
        mAdapter = new RlvTopicAdapter(getContext(), list);
        mRlv.setAdapter(mAdapter);

        mAdapter.setOnItemClickListener(new RlvTopicAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {
                Toast.makeText(getContext(), "item click:"+position, Toast.LENGTH_SHORT).show();
            }
        });

        mSrl.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                //上拉加载更多
                if (page < mTotalPages){
                    page++;
                    initData();
                }else {
                    Toast.makeText(getContext(), "没有更多数据了", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                //下拉刷新
                mAdapter.mList.clear();
                page = 1;
                initData();
            }
        });
    }
}
