package com.jy.firsttest.shop.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.jy.firsttest.shop.R;
import com.jy.firsttest.shop.bean.CarInfo;

import java.util.ArrayList;
import java.util.List;

public class CarRlvAdapter2 extends RecyclerView.Adapter<CarRlvAdapter2.MyHolder> {
    private List<CarInfo.DataBean.CartListBean> list = new ArrayList<CarInfo.DataBean.CartListBean>();
    private Context context;

    public CarRlvAdapter2(List<CarInfo.DataBean.CartListBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.car_item2, null);
        MyHolder myHolder = new MyHolder(view);
        return myHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, int position) {
        CarInfo.DataBean.CartListBean cartListBean = list.get(position);
        Glide.with(context).load(cartListBean.getList_pic_url()).into(holder.car_item_iv2);
        holder.car_tv_name.setText(cartListBean.getGoods_name());
        holder.car_tv_price.setText("￥" + cartListBean.getRetail_price());
        holder.car_item_count2.setText("X" + cartListBean.getNumber());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyHolder extends RecyclerView.ViewHolder {
        public View rootView;
        public ImageView car_item_iv2;
        public TextView car_tv_name;
        public TextView car_tv_price;
        public TextView car_item_count2;

        public MyHolder(View rootView) {
            super(rootView);
            car_item_iv2 = (ImageView) rootView.findViewById(R.id.car_item_iv2);
            car_tv_name = (TextView) rootView.findViewById(R.id.car_tv_name);
            car_tv_price = (TextView) rootView.findViewById(R.id.car_tv_price);
            car_item_count2 = (TextView) rootView.findViewById(R.id.car_item_count2);
        }
    }

}
