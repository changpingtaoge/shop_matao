package com.jy.firsttest.shop.adapter;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import java.util.ArrayList;

public class VpImageAdapter extends PagerAdapter {
    private ArrayList<View> mViews;

    public VpImageAdapter(ArrayList<View> views) {
        mViews = views;
    }

    @Override
    public int getCount() {
        return mViews.size();
    }

    /**
     * 该函数用来判断instantiateItem(ViewGroup, int)函数所返回来的Key与
     * 一个页面视图是否是代表的同一个视图(即它俩是否是对应的，对应的表示同一个View)
     * 返回值：如果对应的是同一个View，返回True，否则返回False。
     * @param view
     * @param object
     * @return
     */
    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    /**
     * 初始化视图页面的
     * @param container
     * @param position
     * @return
     */
    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        //返回什么对象,isViewFromObject(@NonNull View view, @NonNull Object object)object就是啥
        //destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object)object也是啥
        View view = mViews.get(position);
        container.addView(view);
        return view;
    }
    /**
     * 销毁视图页面的
     * @param container
     * @param position
     * @param object
     */
    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        //必须注释掉,要不然滑动到最后一页,会崩溃
        //super.destroyItem(container, position, object);
        container.removeView((View) object);
    }
}
