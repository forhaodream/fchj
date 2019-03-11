package com.fchj.czglgz.chengzhangguanli_high.mysc;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.fchj.czglgz.chengzhangguanli_high.R;
import com.fchj.czglgz.chengzhangguanli_high.activity.MyAskActivity;
import com.fchj.czglgz.chengzhangguanli_high.wodetiwen.WeiReplyFragment;
import com.fchj.czglgz.chengzhangguanli_high.wodetiwen.YiReplyFragment;

import java.util.ArrayList;
import java.util.List;

public class MyScActivity extends AppCompatActivity {
    private ImageView returnImg;
    private TabLayout tab;
    private ViewPager pager;
    private List<Fragment> mFragmentList;
    private Handler mHandler;
    private String[] titles;
    private MyAdapter adapter;
    private SharedPreferences usernameSp;
    private int userId;
    private int askCode = -1;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_sc);
        mHandler = new Handler();
        mFragmentList = new ArrayList<>();


        titles = new String[]{"职业", "专业"};
        //页面，数据源
        mFragmentList.add(new NewVocaScFragment());
        mFragmentList.add(new MajorScFragment());
        Intent intent = new Intent("android.intent.action.CART_BROADCAST");
        intent.putExtra("data", "refresh");
        LocalBroadcastManager.getInstance(MyScActivity.this).sendBroadcast(intent);
        sendBroadcast(intent);
        usernameSp = getSharedPreferences("user_npt", MODE_PRIVATE);
        userId = usernameSp.getInt("userId", 0);
        pager = (ViewPager) findViewById(R.id.viewPager);
        tab = (TabLayout) findViewById(R.id.tabLayout);
        returnImg = (ImageView) findViewById(R.id.title_fanhui);
        returnImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        //ViewPager的适配器
        adapter = new MyAdapter(getSupportFragmentManager());
        pager.setAdapter(adapter);
        //绑定
        tab.setupWithViewPager(pager);
    }

    class MyAdapter extends FragmentPagerAdapter {

        public MyAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        //重写这个方法，将设置每个Tab的标题
        @Override
        public CharSequence getPageTitle(int position) {
            return titles[position];
        }
    }

}
