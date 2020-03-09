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
import com.jy.firsttest.shop.bean.HomeBean;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;

public class HomeAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final LayoutInflater inflater;
    private Context context;
    private List<HomeBean.DataBean.BannerBean> banners;
    private List<HomeBean.DataBean.NewGoodsListBean> list;
    private int VIEW_TYPE_ONE = 1;
    private int VIEW_TYPE_TWO = 2;

    public HomeAdapter(Context context, List<HomeBean.DataBean.BannerBean> banners, List<HomeBean.DataBean.NewGoodsListBean> list) {
        this.context = context;
        this.banners = banners;
        this.list = list;
        inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == VIEW_TYPE_ONE) {
            View inflate = LayoutInflater.from(context).inflate(R.layout.item_banner, null);
            return new ViewHolderOne(inflate);
        } else {
            View inflate = LayoutInflater.from(context).inflate(R.layout.item_list, null);
            return new ViewHolderTwo(inflate);
        }

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        int itemViewType = holder.getItemViewType();

        if (itemViewType == VIEW_TYPE_ONE) {
            ArrayList<String> images = new ArrayList<>();
            ArrayList<String> titles = new ArrayList<>();
            for (int i = 0; i < banners.size(); i++) {
                images.add(banners.get(i).getImage_url());
                titles.add(banners.get(i).getName());
            }
            ViewHolderOne viewHolderOne = (ViewHolderOne) holder;
            viewHolderOne.banner.setBannerStyle(BannerConfig.NUM_INDICATOR_TITLE).setImages(images).setImageLoader(new ImageLoader() {
                @Override
                public void displayImage(Context context, Object path, ImageView imageView) {
                    Glide.with(context).load(path).into(imageView);
                }
            }).setBannerTitles(titles).start();
        } else {
            HomeBean.DataBean.NewGoodsListBean newGoodsListBean = list.get(position - 1);
            ViewHolderTwo viewHolderTwo = (ViewHolderTwo) holder;
            viewHolderTwo.tv_home_name.setText(newGoodsListBean.getName());
            Glide.with(context).load(newGoodsListBean.getList_pic_url()).into(viewHolderTwo.iv_home_item);
        }
    }

    @Override
    public int getItemCount() {
        return list.size() + 1;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return VIEW_TYPE_ONE;
        } else {
            return VIEW_TYPE_TWO;
        }

    }

    public static
    class ViewHolderOne extends RecyclerView.ViewHolder {
        public Banner banner;

        public ViewHolderOne(View rootView) {
            super(rootView);
            this.banner = (Banner) rootView.findViewById(R.id.banner);
        }
    }


    public static
    class ViewHolderTwo extends RecyclerView.ViewHolder {
        public ImageView iv_home_item;
        public TextView tv_home_name;

        public ViewHolderTwo(View rootView) {
            super(rootView);
            this.iv_home_item = (ImageView) rootView.findViewById(R.id.iv_home_item);
            this.tv_home_name = (TextView) rootView.findViewById(R.id.tv_home_name);
        }

    }
}
