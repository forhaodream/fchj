package com.fchj.czglgz.chengzhangguanli_high.travel;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.fchj.czglgz.chengzhangguanli_high.R;
import com.fchj.czglgz.chengzhangguanli_high.activity.AboutWebActivity;
import com.fchj.czglgz.chengzhangguanli_high.util.Url;
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
import java.util.List;

public class TravelStudyActivity extends Activity implements SwipeRefreshLayout.OnRefreshListener {
    private ImageView mTitleFanhui;
    private TextView mTitleText;
    private RecyclerView mActivityTravelStudyRlc;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private TravelStudyAdapter mStudyAdapter;
    private TravelModel mTravelModel;
    private List<TravelModel.DataBean> mData;
    private Handler mHandler;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_travel_study);
        mHandler = new Handler();
        initView();
        getTravel();
    }

    private void initView() {
        mTitleFanhui = (ImageView) findViewById(R.id.title_fanhui);
        mTitleFanhui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        mTitleText = (TextView) findViewById(R.id.title_text);
        mActivityTravelStudyRlc = (RecyclerView) findViewById(R.id.activity_travel_study_rlc);
        mSwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.activity_travel_study_swiperefresh);
    }


    private void getTravel() {

        FormEncodingBuilder builder = new FormEncodingBuilder();
        builder.add("pageNum", "1");
        builder.add("pageSize", "10");
        Request request = new Request.Builder().url(Url.travelstudyUrl).post(builder.build()).build();
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
                        Log.d("body", body);
                        Gson gson = new Gson();
                        JsonReader reader = new JsonReader(new StringReader(body));
                        mTravelModel = gson.fromJson(reader, TravelModel.class);
                        mData = mTravelModel.getData();
                        if (mTravelModel.getCode() == 1) {
                            mHandler.post(new Runnable() {
                                @Override
                                public void run() {
                                    mSwipeRefreshLayout.setRefreshing(false);
                                    mStudyAdapter = new TravelStudyAdapter(mData);
                                    mActivityTravelStudyRlc.setLayoutManager(new LinearLayoutManager(TravelStudyActivity.this));
                                    mActivityTravelStudyRlc.setAdapter(mStudyAdapter);
                                    mStudyAdapter.setOnItemClickListener(new TravelStudyAdapter.OnItemClickListener() {
                                        @Override
                                        public void onItemClick(TravelStudyAdapter.ViewHolder holder, int position) {

                                        }

                                        @Override
                                        public void onToDetail(View view, int position) {
                                            Intent toDetail = new Intent(TravelStudyActivity.this, AboutWebActivity.class);
                                            toDetail.putExtra("urls", mData.get(position).getTsconwayurl());
                                            toDetail.putExtra("title", "游学详情");
                                            startActivity(toDetail);
                                        }
                                    });
                                }
                            });
                        } else if (mTravelModel.getCode() == 2) {
                            Looper.prepare();
                            Toast.makeText(TravelStudyActivity.this, mTravelModel.getMsg() + "", Toast.LENGTH_SHORT).show();
                            Looper.loop();
                        } else {
                            Looper.prepare();
                            Toast.makeText(TravelStudyActivity.this, mTravelModel.getMsg() + "", Toast.LENGTH_SHORT).show();
                            Looper.loop();
                        }
                    } else {
                        Looper.prepare();
                        Toast.makeText(TravelStudyActivity.this, "服务器异常", Toast.LENGTH_SHORT).show(); //请求失败
                        Looper.loop();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }


    @Override
    public void onRefresh() {
        getTravel();
    }
}
