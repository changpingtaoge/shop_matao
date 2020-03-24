package com.jy.firsttest.shop.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.jy.firsttest.shop.R;
import com.jy.firsttest.shop.bean.CarInfo;

import java.util.ArrayList;
import java.util.List;

public class CarRlvAdapter extends RecyclerView.Adapter<CarRlvAdapter.MyHolder> {
    private List<CarInfo.DataBean.CartListBean> list = new ArrayList<CarInfo.DataBean.CartListBean>();
    private Context context;
    private String state = "编辑";
    // 删除  集合   下单  集合
    private ArrayList<CarInfo.DataBean.CartListBean> getAll = new ArrayList<>();// 记录选中的条目
    private OnCbClick onCbClick;

    public CarRlvAdapter(List<CarInfo.DataBean.CartListBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    public ArrayList<CarInfo.DataBean.CartListBean> getGetAll() {
        return getAll;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.car_item, null);
        MyHolder myHolder = new MyHolder(view);
        return myHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, int position) {
        CarInfo.DataBean.CartListBean cartListBean = list.get(position);
        Glide.with(context).load(cartListBean.getList_pic_url()).into(holder.car_item_iv);
        holder.car_tv_name.setText(cartListBean.getGoods_name());
        holder.car_tv_price.setText("￥" + cartListBean.getRetail_price());
        holder.car_item_count.setText("X" + cartListBean.getNumber());
        //根据  状态  设置条目的 控件的显示和隐藏
        if (state.equals("编辑")) {
            holder.ll_control_add.setVisibility(View.INVISIBLE);
            holder.car_item_chose.setVisibility(View.INVISIBLE);
            holder.car_tv_name.setVisibility(View.VISIBLE);
            holder.car_item_count.setVisibility(View.VISIBLE);
        } else {
            holder.ll_control_add.setVisibility(View.VISIBLE);
            holder.car_item_chose.setVisibility(View.VISIBLE);
            holder.car_tv_name.setVisibility(View.INVISIBLE);
            holder.car_item_count.setVisibility(View.INVISIBLE);
        }

        // getAll 选中的数据集合  list 包含 getAll 的数据
        if (getAll.contains(list.get(position))) {
            holder.car_item_cb.setChecked(true);
        } else {
            holder.car_item_cb.setChecked(false);
        }

        // 条目添加商品数量
        holder.car_item_add_count.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s = holder.car_item_show_count.getText().toString();
                int i = Integer.parseInt(s);
                i++;
                holder.car_item_show_count.setText(i + "");
                list.get(position).setNumber(i);
            }
        });
        // 条目减少商品数量
        holder.car_item_reduce_count.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s = holder.car_item_show_count.getText().toString();
                int i = Integer.parseInt(s);
                i--;
                if (i >= 1) {
                    holder.car_item_show_count.setText(i + "");
                    list.get(position).setNumber(i);
                }
            }
        });

        holder.car_item_cb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (holder.car_item_cb.isChecked()) {
                    getAll.add(list.get(position));
                } else {
                    getAll.remove(list.get(position));
                }
                if (onCbClick != null) {
                    onCbClick.cbClick();
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    //改变条目控件  状态值    编辑  完成
    public void setItemVisibility(String state) {
        this.state = state;
        notifyDataSetChanged();
    }

    //全选  全不选
    public void getAll(boolean checked) {
        getAll.clear();
        if (checked) {
            getAll.addAll(list);
        }
        notifyDataSetChanged();
    }

    public void setOnCbClick(OnCbClick onCbClick) {
        this.onCbClick = onCbClick;
    }

    public interface OnCbClick {
        void cbClick();
    }

    public class MyHolder extends RecyclerView.ViewHolder {
        private CheckBox car_item_cb;
        private ImageView car_item_iv;
        private TextView car_tv_name;
        private TextView car_tv_price;
        private TextView car_item_count;
        private TextView car_item_chose;
        private TextView car_item_reduce_count;
        private TextView car_item_show_count;
        private TextView car_item_add_count;
        private LinearLayout ll_control_add;

        public MyHolder(@NonNull View itemView) {
            super(itemView);
            car_item_cb = itemView.findViewById(R.id.car_item_cb);
            car_item_iv = itemView.findViewById(R.id.car_item_iv);
            car_tv_name = itemView.findViewById(R.id.car_tv_name);
            car_tv_price = itemView.findViewById(R.id.car_tv_price);
            car_item_count = itemView.findViewById(R.id.car_item_count);
            car_item_chose = itemView.findViewById(R.id.car_item_chose);
            car_item_reduce_count = itemView.findViewById(R.id.car_item_reduce_count);

            car_item_show_count = itemView.findViewById(R.id.car_item_show_count);
            car_item_add_count = itemView.findViewById(R.id.car_item_add_count);
            ll_control_add = itemView.findViewById(R.id.ll_control_add);

        }
    }


}
