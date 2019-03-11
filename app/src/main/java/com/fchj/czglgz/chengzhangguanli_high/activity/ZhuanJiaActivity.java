package com.fchj.czglgz.chengzhangguanli_high.activity;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.fchj.czglgz.chengzhangguanli_high.R;
import com.fchj.czglgz.chengzhangguanli_high.util.IntentUtil;
import com.fchj.czglgz.chengzhangguanli_high.zhuanjiajieda.DXLS;
import com.fchj.czglgz.chengzhangguanli_high.zhuanjiajieda.SXSJ;
import com.fchj.czglgz.chengzhangguanli_high.zhuanjiajieda.WJJZ;
import com.fchj.czglgz.chengzhangguanli_high.zhuanjiajieda.ZYTB;
import com.fchj.czglgz.chengzhangguanli_high.zhuanjiajieda.ZhuanJiaDetailActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/4/10 0010.
 */

public class ZhuanJiaActivity extends AppCompatActivity {
    private ImageView returnImg, suosouImg;
    private TextView titleTv, djfgOne, djfgTwo, djfgThree, djfgFour;
    private String title;
    private String url;
    private WebView mWebView;
    //  private WebViewModel mAboutModel;
    private int userId;
    private RelativeLayout zcfgRl, gszdRl, qfjnRl;
    private TabLayout tab;
    private ViewPager pager;
    private List<Fragment> mFragmentList;
    private Handler mHandler;
    private String[] titles = {"志愿填报", "新高考", "学业生涯", "职业规划"};
    private MyAdapter adapter;
    private ImageView kaImg;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zhuanjia);
        mHandler = new Handler();
        pager = (ViewPager) findViewById(R.id.viewPager);
        tab = (TabLayout) findViewById(R.id.tabLayout);
        returnImg = (ImageView) findViewById(R.id.title_fanhui);
        returnImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        kaImg = (ImageView) findViewById(R.id.home_to_ka);
        kaImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                IntentUtil.showIntent(ZhuanJiaActivity.this, KaActivity.class);
            }
        });
//        suosouImg = (ImageView) findViewById(R.id.zj_detail_sousuo);
//        suosouImg.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                IntentUtil.showIntent(ZhuanJiaActivity.this, ZhuanJiaDetailActivity.class);
//            }
//        });
        //页面，数据源
        mFragmentList = new ArrayList<>();

        mFragmentList.add(new ZYTB());
        mFragmentList.add(new WJJZ());
        mFragmentList.add(new SXSJ());
        mFragmentList.add(new DXLS());
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
