package com.jy.firsttest.shop;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.jy.firsttest.shop.adapter.VpImageAdapter;

import java.util.ArrayList;

//马涛
public class MainActivity666  {

//
//    private Toolbar tooBar;
//    private ViewPager vp;
//    private TabLayout mtabLayout;
//
//
//
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//        initView();
//    }
//
//
//    private void initView() {
//        tooBar = (Toolbar) findViewById(R.id.tooBar);
//        vp = (ViewPager) findViewById(R.id.vp);
//        mtabLayout = (TabLayout) findViewById(R.id.tabLayout);
//        toolbar();
//        tab();
//        vp();
//
//        mtabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
//            @Override
//            public void onTabSelected(TabLayout.Tab tab) {
//                int position = tab.getPosition();
//                vp.setCurrentItem(position);
//            }
//
//            @Override
//            public void onTabUnselected(TabLayout.Tab tab) {
//
//            }
//
//            @Override
//            public void onTabReselected(TabLayout.Tab tab) {
//
//            }
//        });
//
//        vp.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
//            @Override
//            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
//
//            }
//
//            @Override
//            public void onPageSelected(int position) {
//                mtabLayout.getTabAt(position).select();
//            }
//
//            @Override
//            public void onPageScrollStateChanged(int state) {
//
//            }
//        });
//    }
//
//    private void vp() {
//        ArrayList<View> views = new ArrayList<>();
//        for (int i = 0;i<5;i++){
//            View inflate = LayoutInflater.from(this).inflate(R.layout.tab, null);
//            TextView textView = inflate.findViewById(R.id.tv);
//            textView.setText("窗口"+(i+1));
//            views.add(inflate);
//        }
//        VpImageAdapter vpImageAdapter = new VpImageAdapter(views);
//        vp.setAdapter(vpImageAdapter);
//    }
//
//    //1.设置任务栏（最上部）
//    private void toolbar() {
//        tooBar.setTitle(R.string.title);
//        //默认的样式里面有ActionBar，需要使用tooBar去代替
//        //在style.xml中的AppTheme更改为“Theme.AppCompat.Light.NoActionBar"
//        setSupportActionBar(tooBar);
//    }
//
//
//    //2.动态给每个tab赋值
//    public View tabView(int position){
//        View inflate = LayoutInflater.from(this).inflate(R.layout.tab, null);
//        ImageView iv = inflate.findViewById(R.id.iv);
//        TextView tv = inflate.findViewById(R.id.tv);
//        switch (position){
//            case 0:
//                //选择器,根据不同的状态选用不同的资源
//                iv.setImageResource(R.drawable.se_main_page);
//                tv.setText(R.string.main_page);
//                break;
//            case 1:
//                iv.setImageResource(R.drawable.se_section);
//                tv.setText(R.string.section);
//                break;
//            case 2:
//                iv.setImageResource(R.drawable.se_category);
//                tv.setText(R.string.category);
//                break;
//            case 3:
//                iv.setImageResource(R.drawable.se_cart);
//                tv.setText(R.string.cart);
//                break;
//            case 4:
//                iv.setImageResource(R.drawable.se_me);
//                tv.setText(R.string.me);
//                break;
//        }
//        return inflate;
//    }
//
//    //3.调用tabView方法，进行获取对应的值
//    public void tab(){
//        mtabLayout.addTab(mtabLayout.newTab().setCustomView(tabView(0)));
//        mtabLayout.addTab(mtabLayout.newTab().setCustomView(tabView(1)));
//        mtabLayout.addTab(mtabLayout.newTab().setCustomView(tabView(2)));
//        mtabLayout.addTab(mtabLayout.newTab().setCustomView(tabView(3)));
//        mtabLayout.addTab(mtabLayout.newTab().setCustomView(tabView(4)));
//    }
//
//    private void  initFragment(){
//
//    }

}
