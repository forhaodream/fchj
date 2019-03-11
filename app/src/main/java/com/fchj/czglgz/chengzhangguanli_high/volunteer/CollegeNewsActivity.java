package com.fchj.czglgz.chengzhangguanli_high.volunteer;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.fchj.czglgz.chengzhangguanli_high.R;
import com.lh.ch.pulltorefresh.PullToRefreshLayout;
import com.lh.ch.pulltorefresh.PullableListView;


public class CollegeNewsActivity extends AppCompatActivity {
    private ImageView mTitleFanhui;
    private TextView mTitleText;
    private RelativeLayout mTitle;
    private Spinner mAddressSp;
    private Spinner mKindsSp;
    private Spinner mSubjectSp;
    private PullableListView mFragVideoList;
    private PullToRefreshLayout mYxlPullrefresh;
    final String[] HEROES = new String[]{
            "Iron Man", "Ant Man", "American Captain",
            "Hulk", "Thor", "Black Widow",
            "一个长度特别长的用来测试最大长度的英雄"
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_college_list);
//        initView();
        initSpData();
    }

    private void initView() {
        mTitleFanhui = (ImageView) findViewById(R.id.title_fanhui);
        mTitleText = (TextView) findViewById(R.id.title_text);
        mTitle = (RelativeLayout) findViewById(R.id.title);
//        mAddressSp = (Spinner) findViewById(R.id.college_list_address);
//        mKindsSp = (Spinner) findViewById(R.id.college_list_kinds);
//        mSubjectSp = (Spinner) findViewById(R.id.college_list_subject);
        mFragVideoList = (PullableListView) findViewById(R.id.frag_video_list);
        mYxlPullrefresh = (PullToRefreshLayout) findViewById(R.id.yxl_pullrefresh);
    }

    private void initSpData() {


    }
}
