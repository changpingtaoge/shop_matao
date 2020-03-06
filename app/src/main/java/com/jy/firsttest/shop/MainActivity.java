package com.jy.firsttest.shop;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.jy.firsttest.shop.adapter.VpFragmentAdapter;
import com.jy.firsttest.shop.adapter.VpImageAdapter;
import com.jy.firsttest.shop.ui.fragment.CartFragment;
import com.jy.firsttest.shop.ui.fragment.MainPageFragment;
import com.jy.firsttest.shop.ui.fragment.MeFragment;
import com.jy.firsttest.shop.ui.fragment.SortFragment;
import com.jy.firsttest.shop.ui.fragment.TopicFragment;

import java.util.ArrayList;

import io.reactivex.annotations.NonNull;

/**
 * 作者：matao
 * 时间：2020-3-5 23:42:30
 * 描述：MainActivity
 */
public class MainActivity extends AppCompatActivity {


    private Toolbar tooBar;
    private ViewPager vp;
    private TabLayout mtabLayout;
    private ArrayList<Fragment> mFragments;
    private ArrayList<String> mTitles;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }


    private void initView() {
        initFragment();
        initTitles();
        tooBar = (Toolbar) findViewById(R.id.tooBar);
        vp = (ViewPager) findViewById(R.id.vp);
        mtabLayout = (TabLayout) findViewById(R.id.tabLayout);
        toolbar();
        vp();
        //关联viewPager和TabLayout
        mtabLayout.setupWithViewPager(vp);
        for (int i = 0;i<mFragments.size();i++){
            TabLayout.Tab tab = mtabLayout.getTabAt(i);
            tab.setCustomView(tabView(i));
        }
    }

    private void vp() {
        VpFragmentAdapter vpFragmentAdapter = new VpFragmentAdapter(getSupportFragmentManager(), mFragments, mTitles);
        vp.setAdapter(vpFragmentAdapter);
    }

    //1.设置任务栏（最上部）
    private void toolbar() {
        tooBar.setTitle(R.string.title);
        //默认的样式里面有ActionBar，需要使用tooBar去代替
        //在style.xml中的AppTheme更改为“Theme.AppCompat.Light.NoActionBar"
        setSupportActionBar(tooBar);
    }


    //2.动态给每个tab赋值
    public View tabView(int position){
        View inflate = LayoutInflater.from(this).inflate(R.layout.tab, null);
        ImageView iv = inflate.findViewById(R.id.iv);
        TextView tv = inflate.findViewById(R.id.tv);
        switch (position){
            case 0:
                //选择器,根据不同的状态选用不同的资源
                iv.setImageResource(R.drawable.se_main_page);
                break;
            case 1:
                iv.setImageResource(R.drawable.se_section);
                break;
            case 2:
                iv.setImageResource(R.drawable.se_category);
                break;
            case 3:
                iv.setImageResource(R.drawable.se_cart);
                break;
            case 4:
                iv.setImageResource(R.drawable.se_me);
                break;
        }
        tv.setText(mTitles.get(position));
        return inflate;
    }

    private void initTitles() {
        mTitles = new ArrayList<>();
        mTitles.add(getResources().getString(R.string.main_page));
        mTitles.add(getResources().getString(R.string.section));
        mTitles.add(getResources().getString(R.string.category));
        mTitles.add(getResources().getString(R.string.cart));
        mTitles.add(getResources().getString(R.string.me));

    }

    private void initFragment(){
        mFragments = new ArrayList<Fragment>();
        mFragments.add(MainPageFragment.newInstance());
        mFragments.add(TopicFragment.newInstance());
        mFragments.add(SortFragment.newInstance());
        mFragments.add(CartFragment.newInstance());
        mFragments.add(MeFragment.newInstance());
    }


    //optionMenu使用2步
    //1.创建选项菜单
    //2.菜单的点击事件

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //添加菜单2种方式
        //groupid,菜单组的id
        //itemid,菜单的id
        //order,排序,数字越小排位靠上
        //title,菜单标题
        //添加方式1
        /*menu.add(0,0,1,"删除");
        menu.add(0,1,0,"添加");*/
        //添加方式2
        getMenuInflater().inflate(R.menu.menu,menu);
        return super.onCreateOptionsMenu(menu);
    }
    //2.菜单的点击事件
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.add:
                showToast(item.getTitle());
                break;
            case R.id.delete:
                showToast(item.getTitle());
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    public void showToast(CharSequence msg){
        Toast.makeText(this,msg, Toast.LENGTH_SHORT).show();
    }
}
