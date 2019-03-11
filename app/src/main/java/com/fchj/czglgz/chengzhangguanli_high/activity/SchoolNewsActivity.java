package com.fchj.czglgz.chengzhangguanli_high.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.fchj.czglgz.chengzhangguanli_high.R;
import com.fchj.czglgz.chengzhangguanli_high.adapter.SchoolNewsAdapter;
import com.fchj.czglgz.chengzhangguanli_high.model.SchoolNewsModel;
import com.fchj.czglgz.chengzhangguanli_high.util.IntentUtil;
import com.fchj.czglgz.chengzhangguanli_high.util.Url;
import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import com.lh.ch.pulltorefresh.PullToRefreshLayout;
import com.squareup.okhttp.Call;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;
import java.io.StringReader;
import java.util.List;

/**
 * Created by Administrator on 2018/4/23 0023.
 */

public class SchoolNewsActivity extends Activity {
    private ImageView returnImg;
    private Handler handler;
    private SchoolNewsModel mSchoolNewsModel;
    private List<SchoolNewsModel.DataBean> mData;
    private List<SchoolNewsModel.DataBean> pageData;
    private ListView mListView;
    private int itemId = 1;
    private SchoolNewsAdapter mSchoolNewsAdapter;
    private ImageView kaImg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_school_news);
        returnImg = (ImageView) findViewById(R.id.title_fanhui);
        returnImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        handler = new Handler();
        mListView = (ListView) findViewById(R.id.school_newslist_view);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent toDetail = new Intent(SchoolNewsActivity.this, SchoolDetailActivity.class);
                toDetail.putExtra("newsid", mData.get(position).getId());
                startActivity(toDetail);

            }
        });
        kaImg = (ImageView) findViewById(R.id.home_to_ka);
        kaImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                IntentUtil.showIntent(SchoolNewsActivity.this, KaActivity.class);
            }
        });
        addList();
        PullToRefreshLayout ptr = (PullToRefreshLayout) findViewById(R.id.school_news_pullrefresh);
        mListView = (ListView) ptr.getPullableView();
        ptr.setOnPullListener(new PullToRefreshLayout.OnPullListener() {
            @Override
            public void onRefresh(PullToRefreshLayout pullToRefreshLayout) {
                addList();
                pullToRefreshLayout.refreshFinish(0);
            }

            @Override
            public void onLoadMore(PullToRefreshLayout pullToRefreshLayout) {
                itemId += 1;
                addPageCheck();
                pullToRefreshLayout.loadmoreFinish(0);
            }
        });
    }

    private void addList() {

        FormEncodingBuilder builder = new FormEncodingBuilder();
        builder.add("newstype", "1");
        builder.add("pageNum", "1");
        builder.add("pageSize", "10");
        Request request = new Request.Builder().url(Url.schoolNewsUrl).post(builder.build()).build();
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
                        Log.d("sdadsadas", body);
                        Gson gson = new Gson();
                        JsonReader reader = new JsonReader(new StringReader(body));
                        mSchoolNewsModel = gson.fromJson(reader, SchoolNewsModel.class);
                        if (mSchoolNewsModel.getCode() == 1) {

                            handler.post(new Runnable() {
                                @Override
                                public void run() {

                                    mData = mSchoolNewsModel.getData();
                                    mSchoolNewsAdapter = new SchoolNewsAdapter(mData);
                                    mListView.setAdapter(mSchoolNewsAdapter);

                                }
                            });
                        } else if (mSchoolNewsModel.getCode() == 2) {
                            Looper.prepare();
                            Toast.makeText(SchoolNewsActivity.this, mSchoolNewsModel.getMsg() + "", Toast.LENGTH_SHORT).show();
                            Looper.loop();
                        } else {
                            Looper.prepare();
                            Toast.makeText(SchoolNewsActivity.this, mSchoolNewsModel.getMsg() + "", Toast.LENGTH_SHORT).show();
                            Looper.loop();
                        }


                    } else {
                        Looper.prepare();
                        Toast.makeText(SchoolNewsActivity.this, "服务器异常", Toast.LENGTH_SHORT).show(); //请求失败
                        Looper.loop();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        });
    }

    private void addPageCheck() {
        FormEncodingBuilder builder = new FormEncodingBuilder();
        builder.add("pageNum", String.valueOf(itemId));
        builder.add("pageSize", "10");
        builder.add("newstype", "1");
        Request request = new Request.Builder().url(Url.schoolNewsUrl).post(builder.build()).build();
        OkHttpClient okHttpClient = new OkHttpClient();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {

            }

            @Override
            public void onResponse(Response response) throws IOException {
                String body = response.body().string();
                Log.d("body", body);
                Gson gson = new Gson();
                JsonReader reader = new JsonReader(new StringReader(body));
                mSchoolNewsModel = gson.fromJson(reader, SchoolNewsModel.class);
                pageData = mSchoolNewsModel.getData();
                if (mSchoolNewsModel.getCode() == 1) {
                    handler.post(pageRunn);
                } else if (mSchoolNewsModel.getCode() == 2) {
                    Looper.prepare();
                    Toast.makeText(SchoolNewsActivity.this, mSchoolNewsModel.getMsg() + "", Toast.LENGTH_SHORT).show();
                    Looper.loop();
                } else {
                    Looper.prepare();
                    Toast.makeText(SchoolNewsActivity.this, mSchoolNewsModel.getMsg() + "", Toast.LENGTH_SHORT).show();
                    Looper.loop();
                }

            }
        });


    }

    Runnable pageRunn = new Runnable() {
        @Override
        public void run() {
            if (pageData.size() > 0) {
                mData.addAll(pageData);
                mSchoolNewsAdapter.notifyDataSetChanged();
            } else {
                mData.clear();
                mSchoolNewsAdapter.notifyDataSetChanged();
                Log.d("没有", "数据");
            }
        }
    };

    @Override
    public void onStop() {
        super.onStop();
        itemId = 1;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        itemId = 1;
    }
}
