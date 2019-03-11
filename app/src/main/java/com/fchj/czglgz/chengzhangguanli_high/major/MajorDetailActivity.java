package com.fchj.czglgz.chengzhangguanli_high.major;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.fchj.czglgz.chengzhangguanli_high.R;
import com.fchj.czglgz.chengzhangguanli_high.util.Url;
import com.fchj.czglgz.chengzhangguanli_high.vocation.VocaScModel;
import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import com.jaeger.library.StatusBarUtil;
import com.squareup.okhttp.Call;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

public class MajorDetailActivity extends AppCompatActivity {
    private TabLayout tab;
    private ViewPager pager;
    private MyAdapter adapter;
    private Handler mHandler;
    private List<Fragment> mFragmentList;
    private String[] titles = {"培养目标", "开设课程", "开设院校", "技能要求", "对口职业", "教学实践"};//"培养目标", "教学实践", "技能要求",
    private CollapsingToolbarLayout collapsingToolbarLayout;
    private String search_str = "";
    private MajorDetailModel mMajorDetailModel;
    private SharedPreferences usernameSp;
    private String username;
    private int userId;
    private TextView detailTv, codeTv, yearTv;
    private ImageView returnImg;
    private TextView followTv;
    private String typeStr = "";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= 21) {
            View decorView = getWindow().getDecorView();
            decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }
        setContentView(R.layout.activity_major_detail_home);
        mHandler = new Handler();
        pager = (ViewPager) findViewById(R.id.viewPager);
        tab = (TabLayout) findViewById(R.id.tabLayout);
        detailTv = (TextView) findViewById(R.id.activity_major_detail_name);
        codeTv = (TextView) findViewById(R.id.activity_major_code);
        yearTv = (TextView) findViewById(R.id.activity_major_year);
        followTv = (TextView) findViewById(R.id.activity_major_follow);
        followTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addSc(mMajorDetailModel.getData().getMajid() + "");
                Log.d("majorID", mMajorDetailModel.getData().getMajid() + "");
            }
        });
        returnImg = (ImageView) findViewById(R.id.return_img);
        returnImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        Intent intent = getIntent();
        search_str = intent.getStringExtra("zy_name");
        typeStr = intent.getStringExtra("zy_type");
        usernameSp = getSharedPreferences("user_npt", MODE_PRIVATE);
        userId = usernameSp.getInt("userId", 0);
        getMajorDetail();
    }

    private void initView() {
        mFragmentList = new ArrayList<>();
        //"培养目标", "教学实践", "技能要求", "对口职业", "专业相关", "开设院校"
        mFragmentList.add(new DetailFragment("培养目标", mMajorDetailModel.getData().getTrainingtarget()));
        // 开设课程
        mFragmentList.add(new DetailFragment("开设课程", mMajorDetailModel.getData().getTeachclass()));
//        mFragmentList.add(new ZyAboutFragment("开设课程", mMajorDetailModel.getData().getTeachclass() + ""));
        mFragmentList.add(new AboutCollegeFragment(mMajorDetailModel.getData().getMajorname(), mMajorDetailModel.getData().getCoursetype()));
        mFragmentList.add(new DetailFragment("技能要求", mMajorDetailModel.getData().getSkillsclaim()));
        mFragmentList.add(new AboutMajorFragment("对口职业", mMajorDetailModel.getData().getRelativevct() + ""));
        mFragmentList.add(new DetailFragment("教学实践", mMajorDetailModel.getData().getTeachingpractice()));
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

    private void getMajorDetail() {

        FormEncodingBuilder builder = new FormEncodingBuilder();
        builder.add("majorname", search_str);
        builder.add("userId", String.valueOf(userId));
        Request request = new Request.Builder().url(Url.getZyDetailUrl).post(builder.build()).build();//Url.getZyDetailNewUrl
        OkHttpClient okHttpClient = new OkHttpClient();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {


            @Override
            public void onFailure(Request request, IOException e) {

            }

            @Override
            public void onResponse(Response response) {
                try {
                    if (response.isSuccessful()) {
                        String body = response.body().string();
                        Log.d("major_detail", body);
                        Gson gson = new Gson();
                        JsonReader reader = new JsonReader(new StringReader(body));
                        mMajorDetailModel = gson.fromJson(reader, MajorDetailModel.class);
                        if (mMajorDetailModel.getCode() == 1) {
                            mHandler.post(new Runnable() {
                                @Override
                                public void run() {
                                    initView();
                                    detailTv.setText(mMajorDetailModel.getData().getMajorname());
                                    codeTv.setText("专业代码:" + mMajorDetailModel.getData().getMajorcode());
                                    yearTv.setText("学习年限:" + mMajorDetailModel.getData().getStudyyears());
                                    if (mMajorDetailModel.getData().getIsfollow() == 1) {

                                        followTv.setText("已关注");
                                    } else {
                                        followTv.setText("关注");
                                    }

                                }
                            });
                        } else {
                            Log.d("没有", "数据");
                        }
                    } else {
                        Looper.prepare();
                        Toast.makeText(MajorDetailActivity.this, "服务器异常", Toast.LENGTH_SHORT).show(); //请求失败
                        Looper.loop();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private VocaScModel mVocaScModel;

    private void addSc(final String pos) {
        FormEncodingBuilder builder = new FormEncodingBuilder();
        builder.add("majid", pos);
        builder.add("userId", String.valueOf(userId));
        Request request = new Request.Builder().url(Url.addMajScUrl).post(builder.build()).build();
        OkHttpClient okHttpClient = new OkHttpClient();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {

            }

            @Override
            public void onResponse(Response response) {
                try {
                    if (response.isSuccessful()) {
                        String body = response.body().string();
                        Log.d("vocation", body);
                        Gson gson = new Gson();
                        JsonReader reader = new JsonReader(new StringReader(body));
                        mVocaScModel = gson.fromJson(reader, VocaScModel.class);
                        if (mVocaScModel.getCode() == 1) {
                            mHandler.post(new Runnable() {
                                @Override
                                public void run() {
                                    followTv.setText("已关注");
                                }
                            });
                            Log.d("关注", "成功");
                        } else if (mVocaScModel.getCode() == 2) {
                            mHandler.post(new Runnable() {
                                @Override
                                public void run() {
                                    followTv.setText("关注");

                                }
                            });

                        } else {
                            Looper.prepare();
                            Toast.makeText(MajorDetailActivity.this, mVocaScModel.getMsg() + "", Toast.LENGTH_SHORT).show();
                            Looper.loop();
                        }
                    } else {
                        Looper.prepare();
                        Toast.makeText(MajorDetailActivity.this, "服务器异常", Toast.LENGTH_SHORT).show(); //请求失败
                        Looper.loop();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        });
    }
}
