package com.jy.firsttest.shop.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.jy.firsttest.shop.R;
import com.jy.firsttest.shop.bean.GoodsDetailBean;

import java.util.List;

public class RlvGoodsDetailAttributeAdapter extends RecyclerView.Adapter<RlvGoodsDetailAttributeAdapter.MyHolder> {

    private Context context;
    private List<GoodsDetailBean.DataBeanX.AttributeBean> list;


    public RlvGoodsDetailAttributeAdapter(Context context, List<GoodsDetailBean.DataBeanX.AttributeBean> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.rec_item_goodsdetail_attribute, null);
        return new MyHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, int position) {
        holder.txt_name.setText(list.get(position).getName());
        holder.txt_value.setText(list.get(position).getValue());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyHolder extends RecyclerView.ViewHolder {
        private TextView txt_name;
        private TextView txt_value;

        public MyHolder(@NonNull View itemView) {
            super(itemView);
            txt_name = itemView.findViewById(R.id.txt_name);
            txt_value = itemView.findViewById(R.id.txt_value);
        }
    }
}
