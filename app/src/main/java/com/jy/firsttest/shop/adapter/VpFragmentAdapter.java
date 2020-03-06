package com.jy.firsttest.shop.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.ArrayList;

public class VpFragmentAdapter extends FragmentPagerAdapter {
    private final ArrayList<Fragment> mFragments;
    private final ArrayList<String> mTitles;

    public VpFragmentAdapter(@NonNull FragmentManager fm, ArrayList<Fragment> fragments, ArrayList<String> titles) {
        super(fm);
        mFragments = fragments;
        mTitles = titles;
    }


    @NonNull
    @Override
    public Fragment getItem(int position) {
        return mFragments.get(position);
    }


    @Override
    public int getCount() {
        return mFragments.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return mTitles.get(position);
    }
}
