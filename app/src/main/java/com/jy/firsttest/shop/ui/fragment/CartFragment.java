package com.jy.firsttest.shop.ui.fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.jy.firsttest.shop.R;
import com.jy.firsttest.shop.SuerBuyActivity;
import com.jy.firsttest.shop.adapter.CarRlvAdapter;
import com.jy.firsttest.shop.bean.CarInfo;
import com.jy.firsttest.shop.bean.DelCarInfo;
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


public class CartFragment extends Fragment implements View.OnClickListener {

    private TextView car_tv_count_price;
    private CarRlvAdapter carRlvAdapter;
    private List<CarInfo.DataBean.CartListBean> list = new ArrayList<>();
    private Button car_btn_car_press;
    private CheckBox car_cb_all;
    private TextView car_tv_edit_state;


    public static CartFragment newInstance(){
        CartFragment fragment = new CartFragment();
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        View inflate = inflater.inflate(R.layout.fragment_cart, container, false);
        return inflate;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initData();
        initView(getView());
    }

    @SuppressLint("CheckResult")
    private void initData() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiServer.url)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

        ApiServer apiService = retrofit.create(ApiServer.class);
        Flowable<CarInfo> flowable = apiService.getCarDtate();
        flowable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new ResourceSubscriber<CarInfo>() {
                    @Override
                    public void onNext(CarInfo carInfo) {
                        ArrayList<CarInfo.DataBean.CartListBean> cartList =
                                (ArrayList<CarInfo.DataBean.CartListBean>) carInfo.getData().getCartList();
                        list.addAll(cartList);
                        carRlvAdapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onError(Throwable t) {
                        Log.i("tag", "===> " + t.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }

    private void initView(View view) {

        RecyclerView car_recycler = view.findViewById(R.id.car_recycler);
        car_recycler.setLayoutManager(new LinearLayoutManager(getContext()));

        carRlvAdapter = new CarRlvAdapter(list, getContext());
        car_recycler.setAdapter(carRlvAdapter);
        carRlvAdapter.setOnCbClick(new CarRlvAdapter.OnCbClick() {
            @Override
            public void cbClick() {
                setAllPrice();
            }
        });
        car_btn_car_press = view.findViewById(R.id.car_btn_car_press);
        car_cb_all = view.findViewById(R.id.car_cb_all);
        car_tv_edit_state = view.findViewById(R.id.car_tv_edit_state);
        car_tv_count_price = view.findViewById(R.id.car_tv_count_price);

        car_btn_car_press.setOnClickListener(this);
        car_cb_all.setOnClickListener(this);
        car_tv_edit_state.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.car_btn_car_press:
                ArrayList<CarInfo.DataBean.CartListBean> getAll = carRlvAdapter.getGetAll();
                String s = car_btn_car_press.getText().toString();
                if ("下单".equals(s)) {

                    // 跳转到一个确认下单页面
                    // 把getAll 集合数据传递到  确定页面显示
                    Intent intent = new Intent(getContext(), SuerBuyActivity.class);
                    intent.putExtra("getAll", getAll);
                    startActivity(intent);

                } else {// 删除   删除选中的条目

                    ArrayList<CarInfo.DataBean.CartListBean> getList = carRlvAdapter.getGetAll();
                    //先删除后台数据
                    for (int i = 0; i < getAll.size(); i++) {
                        //删除的网络请求
                        Retrofit retrofit = new Retrofit.Builder()
                                .baseUrl(ApiServer.url)
                                .addConverterFactory(GsonConverterFactory.create())
                                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                                .build();

                        ApiServer apiService = retrofit.create(ApiServer.class);
                        Flowable<DelCarInfo> flowable = apiService.delCarInfo(getAll.get(i).getProduct_id());
                        int finalI = i;
                        flowable.subscribeOn(Schedulers.io())
                                .observeOn(AndroidSchedulers.mainThread())
                                .subscribeWith(new ResourceSubscriber<DelCarInfo>() {
                                    @Override
                                    public void onNext(DelCarInfo delCarInfo) {
                                        if (delCarInfo.getErrno() == 0) {
                                            Log.i("tag", "删除成功");
                                            getList.remove(getAll.get(finalI));
                                            carRlvAdapter.notifyDataSetChanged();
                                        }
                                    }

                                    @Override
                                    public void onError(Throwable t) {
                                        Log.i("tag", "===> " + t.getMessage());
                                    }

                                    @Override
                                    public void onComplete() {

                                    }
                                });
                    }
                    /*// 再删除界面效果

                    getList.removeAll(getAll);*/
                }
                break;

            case R.id.car_cb_all:
                boolean checked = car_cb_all.isChecked();
                carRlvAdapter.getAll(checked);
                setAllPrice();
                break;

            case R.id.car_tv_edit_state:
                String state = car_tv_edit_state.getText().toString();
                if ("编辑".equals(state)) {// 编辑状态    显示的name   隐藏 添加数量

                    car_btn_car_press.setText("删除所有");
                    car_tv_edit_state.setText("完成");
                } else {// 完成    显示的 添加商品的数量

                    car_btn_car_press.setText("下单");
                    car_tv_edit_state.setText("编辑");
                }
                carRlvAdapter.setItemVisibility(car_tv_edit_state.getText().toString());
                break;
        }
    }


    private void setAllPrice() {
        //计算一下总价   选中item存储的集合
        List<CarInfo.DataBean.CartListBean> getAll = carRlvAdapter.getGetAll();
        int sum = 0;
        int count = 0;
        for (int i = 0; i < getAll.size(); i++) {
            sum += getAll.get(i).getRetail_price() * getAll.get(i).getNumber();
            count += getAll.get(i).getNumber();
        }
        car_cb_all.setText("全部(" + count + ")");
        car_tv_count_price.setText("￥" + sum);
    }
}
