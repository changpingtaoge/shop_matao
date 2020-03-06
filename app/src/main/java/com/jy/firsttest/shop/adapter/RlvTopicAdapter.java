package com.jy.firsttest.shop.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.jy.firsttest.shop.R;
import com.jy.firsttest.shop.bean.TopicBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RlvTopicAdapter extends RecyclerView.Adapter {
    private final Context mContext;
    public final ArrayList<TopicBean.DataBeanX.DataBean> mList;
    private OnItemClickListener mItemClickListener;

    public RlvTopicAdapter(Context context, ArrayList<TopicBean.DataBeanX.DataBean> list) {

        mContext = context;
        mList = list;
    }

    /**
     * 创建viewholder方法,view的持有者
     * @param parent
     * @param viewType
     * @return
     */
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(mContext).inflate(R.layout.item_topic, parent, false);
        return new VH(inflate);
    }

    /**
     * 绑定viewholder,设置页码数据
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        TopicBean.DataBeanX.DataBean bean = mList.get(position);
        VH vh = (VH) holder;
        vh.mTvTitle.setText(bean.getTitle());
        vh.mTvSubTitle.setText(bean.getSubtitle());
        vh.mTvPrice.setText(bean.getPrice_info()+
                mContext.getResources().getString(R.string.yuan));

        Glide.with(mContext).load(bean.getScene_pic_url()).into(vh.mIv);

        vh.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //子条目的点击事件
                if (mItemClickListener != null){
                    mItemClickListener.onItemClick(v,position);
                }
            }
        });
    }

    //子条目的数量
    @Override
    public int getItemCount() {
        return mList.size();
    }

    public void addData(List<TopicBean.DataBeanX.DataBean> data) {
        mList.addAll(data);
        //刷新适配器
        notifyDataSetChanged();
    }

    class VH extends RecyclerView.ViewHolder{
        @BindView(R.id.tv_title)
        TextView mTvTitle;
        @BindView(R.id.tv_subtitle)
        TextView mTvSubTitle;
        @BindView(R.id.tv_price)
        TextView mTvPrice;
        @BindView(R.id.iv)
        ImageView mIv;
        public VH(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public interface OnItemClickListener{
        void onItemClick(View v,int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener){
        mItemClickListener = listener;
    }
}
