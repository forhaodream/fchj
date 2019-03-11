package com.fchj.czglgz.chengzhangguanli_high.vocation;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.fchj.czglgz.chengzhangguanli_high.R;
import com.fchj.czglgz.chengzhangguanli_high.util.Url;
import com.fchj.czglgz.chengzhangguanli_high.viewpagercard.CardFragment;
import com.fchj.czglgz.chengzhangguanli_high.viewpagercard.CardFragmentPagerAdapter;
import com.fchj.czglgz.chengzhangguanli_high.viewpagercard.CardPagerAdapter;
import com.fchj.czglgz.chengzhangguanli_high.viewpagercard.ShadowTransformer;
import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import com.squareup.okhttp.Call;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;
import java.io.StringReader;
import java.util.Arrays;
import java.util.List;


public class VocationDetailActivity extends AppCompatActivity implements View.OnClickListener, CompoundButton.OnCheckedChangeListener {
    private String vocaName = "";
    private String vocaId = "";
    private Button mButton;
    private ViewPager mViewPager;
    private ImageView returnImg;
    private CardPagerAdapter mCardAdapter;
    private ShadowTransformer mCardShadowTransformer;
    private CardFragmentPagerAdapter mFragmentCardAdapter;
    private ShadowTransformer mFragmentCardShadowTransformer;
    private TextView titleTv;
    private boolean mShowingFragments = false;

    private TabLayout tab;
    private List<Fragment> mFragmentList;
    private String[] titles = {"职业概述", "职业前景", "教育背景", "职业关联的大学专业", "工作经验", "工作内容"};
    private Handler mHandler;
    // 数据
    private VocationDetailModel mVocationDetailModel;
    private VocationDetailModel.DataBean mData;
    private int typeNum;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {//因为不是所有的系统都可以设置颜色的，在4.4以下就不可以。。有的说4.1，所以在设置的时候要检查一下系统版本是否是4.1以上
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(getResources().getColor(R.color.dark_blue));
        }
        setContentView(R.layout.activity_vocation_detail);
        mHandler = new Handler();
        Intent intent = getIntent();
        vocaName = intent.getStringExtra("vocaname");
        Log.d("vocaname", vocaName + "");
        vocaId = intent.getStringExtra("vocaid");
        typeNum = intent.getIntExtra("type", 0);
        titleTv = (TextView) findViewById(R.id.title_text);

        returnImg = (ImageView) findViewById(R.id.title_fanhui);
        returnImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();

            }
        });
        mViewPager = (ViewPager) findViewById(R.id.viewPager);
        // 绑定布局
        tab = (TabLayout) findViewById(R.id.tabLayout);
        getVocaDetail(vocaId);

    }

    private void addFragment() {
        mFragmentCardAdapter = new CardFragmentPagerAdapter(getSupportFragmentManager(), dpToPixels(2, this));
        mFragmentCardAdapter.addCardFragment(new CardFragment(titles[0], mData.getVocasummary().toString(), R.mipmap.votwo, 1));
        mFragmentCardAdapter.addCardFragment(new CardFragment(titles[1], mData.getVocavista().toString(), R.mipmap.vothree, 1));
        mFragmentCardAdapter.addCardFragment(new CardFragment(titles[2], mData.getEdubg().toString(), R.mipmap.vofour, 1));
        mFragmentCardAdapter.addCardFragment(new CardFragment(titles[3], mData.getAbtmajor().toString(), R.mipmap.voone, 5));
        mFragmentCardAdapter.addCardFragment(new CardFragment(titles[4], mData.getHandson().toString(), R.mipmap.vosix, 1));
        mFragmentCardAdapter.addCardFragment(new CardFragment(titles[5], mData.getJobcontent().toString(), R.mipmap.voseven, 1));

        mFragmentCardShadowTransformer = new ShadowTransformer(mViewPager, mFragmentCardAdapter);
        mFragmentCardShadowTransformer.enableScaling(true);
        mViewPager.setAdapter(mFragmentCardAdapter);
        //  mViewPager.setPageTransformer(false, mFragmentCardShadowTransformer);
        mViewPager.setOffscreenPageLimit(3);
        tab.setupWithViewPager(mViewPager);
        tab.getTabAt(0).setText(titles[0]);
        tab.getTabAt(1).setText(titles[1]);
        tab.getTabAt(2).setText(titles[2]);
        tab.getTabAt(3).setText(titles[3]);
        tab.getTabAt(4).setText(titles[4]);
        tab.getTabAt(5).setText(titles[5]);
    }


    @Override
    public void onClick(View v) {
        if (!mShowingFragments) {
            mButton.setText("Views");
            mViewPager.setAdapter(mFragmentCardAdapter);
            mViewPager.setPageTransformer(false, mFragmentCardShadowTransformer);
        } else {
            mButton.setText("Fragments");
            mViewPager.setAdapter(mCardAdapter);
            mViewPager.setPageTransformer(false, mCardShadowTransformer);
        }

        mShowingFragments = !mShowingFragments;
    }

    public static float dpToPixels(int dp, Context context) {
        return dp * (context.getResources().getDisplayMetrics().density);
    }

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
        mCardShadowTransformer.enableScaling(b);
        mFragmentCardShadowTransformer.enableScaling(b);
    }

    private void getVocaDetail(String string) {
        FormEncodingBuilder builder = new FormEncodingBuilder();
        builder.add("vctid", string);
        Log.d("vocTypestring", string);
        Request request = new Request.Builder().url(Url.getVocationDetailUrl).post(builder.build()).build();
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
                        mVocationDetailModel = gson.fromJson(reader, VocationDetailModel.class);
                        if (mVocationDetailModel.getCode() == 1) {
//                            closeDialog(loadingDialog);
                            mHandler.post(new Runnable() {
                                @Override
                                public void run() {
                                    mData = mVocationDetailModel.getData();
                                    titleTv.setText(mData.getVocaname());
                                    addFragment();
                                }
                            });
                        } else if (mVocationDetailModel.getCode() == 2) {
//                            closeDialog(loadingDialog);
                            Looper.prepare();
                            Toast.makeText(VocationDetailActivity.this, mVocationDetailModel.getMsg() + "", Toast.LENGTH_SHORT).show();
                            Looper.loop();
                        } else {
//                            closeDialog(loadingDialog);
                            Looper.prepare();
                            Toast.makeText(VocationDetailActivity.this, mVocationDetailModel.getMsg() + "", Toast.LENGTH_SHORT).show();
                            Looper.loop();
                        }
                    } else {
                        Looper.prepare();
                        Toast.makeText(VocationDetailActivity.this, "服务器异常", Toast.LENGTH_SHORT).show(); //请求失败
                        Looper.loop();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        });
    }
}
