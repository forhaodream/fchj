package com.fchj.czglgz.chengzhangguanli_high.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.fchj.czglgz.chengzhangguanli_high.R;
import com.fchj.czglgz.chengzhangguanli_high.wodetiwen.WeiReplyFragment;
import com.fchj.czglgz.chengzhangguanli_high.wodetiwen.YiReplyFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/4/13 0013.
 */

public class MyAskActivity extends AppCompatActivity {
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
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myask);
        mHandler = new Handler();
        mFragmentList = new ArrayList<>();

        Intent intent = getIntent();
        askCode = intent.getIntExtra("ask", 0);
        if (askCode == 123) {
            titles = new String[]{"未回复", "已回复"};
            //页面，数据源
            mFragmentList.add(new WeiReplyFragment());
            mFragmentList.add(new YiReplyFragment());
        } else {
            titles = new String[]{"已回复", "未回复"};
            mFragmentList.add(new YiReplyFragment());
            mFragmentList.add(new WeiReplyFragment());
        }
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
