package com.fchj.czglgz.chengzhangguanli_high.zhuanjiajieda;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.fchj.czglgz.chengzhangguanli_high.R;
import com.fchj.czglgz.chengzhangguanli_high.model.ZhuanJiaInfoModel;
import com.fchj.czglgz.chengzhangguanli_high.util.IntentUtil;
import com.fchj.czglgz.chengzhangguanli_high.util.Url;
import com.fchj.czglgz.chengzhangguanli_high.view.GlideRoundTransform;
import com.fchj.czglgz.chengzhangguanli_high.wodetiwen.QuestionAskActivity;
import com.fchj.czglgz.chengzhangguanli_high.wodetiwen.QuestionDetailActivity;
import com.fchj.czglgz.chengzhangguanli_high.wodetiwen.YiReplyModel;
import com.fchj.czglgz.chengzhangguanli_high.xuebalaile.XueBaDetailActivity;
import com.fchj.czglgz.chengzhangguanli_high.zixun.ZiXunDetailModel;
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
 * Created by Administrator on 2018/4/12 0012.
 */

public class ZhuanJiaDetailActivity extends Activity {
    private ListView mListView;
    private ImageView askImg;
    private SharedPreferences usernameSp;
    private int zjId;
    private ZhuanJiaInfoModel mZhuanJiaInfoModel;
    private YiReplyModel mYiReplyModel;
    private List<YiReplyModel.DataBean> mData;
    private List<YiReplyModel.DataBean> mPageData;
    private ZhuanjiaDetailAdapter mZhuanjiaDetailAdapter;
    private Handler mHandler;
    private TextView nameTv, includeTv, schoolTv, contentTv, zhuanyeTv;
    private ImageView returnImg;
    private int questionId;
    private int askId;
    private int itemId = 1;
    private ImageView headImg;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zhuangjia_detail);
        mHandler = new Handler();
//        usernameSp = getSharedPreferences("user_npt", MODE_PRIVATE);
//        zjId = usernameSp.getInt("zjId", 0);
        Intent intent = getIntent();
        zjId = intent.getIntExtra("zjId", 0);

        initView();
        getZjInfo();
        getQuestionList();

    }

    private void initView() {
        mListView = (ListView) findViewById(R.id.zj_detail_list_view);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Intent toDetail = new Intent(ZhuanJiaDetailActivity.this, QuestionDetailActivity.class);
                toDetail.putExtra("newsid", mData.get(i).getId());
                startActivity(toDetail);
            }
        });
        nameTv = (TextView) findViewById(R.id.zj_detail_name);
        includeTv = (TextView) findViewById(R.id.zj_detail_xuexiao);
        askImg = (ImageView) findViewById(R.id.title_xiezuo);
        askImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent toAsk = new Intent(ZhuanJiaDetailActivity.this, QuestionAskActivity.class);
                toAsk.putExtra("zjId", zjId);
                startActivity(toAsk);
            }
        });
        schoolTv = (TextView) findViewById(R.id.zj_detail_xuexiao);
        contentTv = (TextView) findViewById(R.id.zj_detail_content);
        returnImg = (ImageView) findViewById(R.id.title_fanhui);
        returnImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        headImg = (ImageView) findViewById(R.id.zj_detail_head);
        zhuanyeTv = (TextView) findViewById(R.id.zj_detail_zhuanye);
        PullToRefreshLayout ptr = (PullToRefreshLayout) findViewById(R.id.zj_detail_pullrefresh);
        mListView = (ListView) ptr.getPullableView();
        ptr.setOnPullListener(new PullToRefreshLayout.OnPullListener() {
            @Override
            public void onRefresh(PullToRefreshLayout pullToRefreshLayout) {
                getQuestionList();
                pullToRefreshLayout.refreshFinish(0);
            }

            @Override
            public void onLoadMore(PullToRefreshLayout pullToRefreshLayout) {
                itemId += 1;
                getZjPage();
                pullToRefreshLayout.loadmoreFinish(0);
            }
        });
    }

    private void getZjInfo() {
        FormEncodingBuilder builder = new FormEncodingBuilder();
        Log.d("asdasdasdasd", String.valueOf(zjId));
        builder.add("userId", String.valueOf(zjId));
        Request request = new Request.Builder().url(Url.showInfoUrl).post(builder.build()).build();
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
                        Log.d("asdasdasdasd", body);
                        Gson gson = new Gson();
                        JsonReader reader = new JsonReader(new StringReader(body));
                        mZhuanJiaInfoModel = gson.fromJson(reader, ZhuanJiaInfoModel.class);
                        if (mZhuanJiaInfoModel.getCode() == 1) {

                            mHandler.post(new Runnable() {
                                @Override
                                public void run() {
                                    nameTv.setText(mZhuanJiaInfoModel.getData().getUsername());
                                    schoolTv.setText("毕业于" + mZhuanJiaInfoModel.getData().getSchl().getSchoolname());
                                    zhuanyeTv.setText(mZhuanJiaInfoModel.getData().getGrade());
                                    contentTv.setText(mZhuanJiaInfoModel.getData().getSummary());
                                    Glide.with(ZhuanJiaDetailActivity.this)
                                            .load(Url.fileUrl + mZhuanJiaInfoModel.getData().getHeadimgurl())
//                                    .transform(new GlideRoundTransform(ZhuanJiaDetailActivity.this, 12))
                                            .placeholder(R.mipmap.teacher)
                                            .into(headImg);
                                }
                            });
                        } else if (mZhuanJiaInfoModel.getCode() == 2) {
                            Looper.prepare();
                            Toast.makeText(ZhuanJiaDetailActivity.this, mZhuanJiaInfoModel.getMsg() + "", Toast.LENGTH_SHORT).show();
                            Looper.loop();
                        } else {
                            Looper.prepare();
                            Toast.makeText(ZhuanJiaDetailActivity.this, mZhuanJiaInfoModel.getMsg() + "", Toast.LENGTH_SHORT).show();
                            Looper.loop();
                        }


                    } else {
                        Looper.prepare();
                        Toast.makeText(ZhuanJiaDetailActivity.this, "服务器异常", Toast.LENGTH_SHORT).show(); //请求失败
                        Looper.loop();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        });
    }

    private void getQuestionList() {
        Log.d("xiamian", zjId + "");
        FormEncodingBuilder builder = new FormEncodingBuilder();
        builder.add("isanswered", "1");
        builder.add("questiontitle", "");
        builder.add("answerer", String.valueOf(zjId));
        builder.add("pageSize", "10");
        builder.add("pageNum", "1");
        Request request = new Request.Builder().url(Url.yiReplyUrl).post(builder.build()).build();
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
                        Gson gson = new Gson();
                        JsonReader reader = new JsonReader(new StringReader(body));
                        mYiReplyModel = gson.fromJson(reader, YiReplyModel.class);
                        Log.d("下面问题", body);
                        if (mYiReplyModel.getCode() == 1) {

                            mHandler.post(new Runnable() {
                                @Override
                                public void run() {

                                    mData = mYiReplyModel.getData();
                                    mZhuanjiaDetailAdapter = new ZhuanjiaDetailAdapter(mData);
                                    mListView.setAdapter(mZhuanjiaDetailAdapter);

                                }
                            });
                        } else {
                            Log.d("没有", "数据");
                        }

                    } else {
                        Looper.prepare();
                        Toast.makeText(ZhuanJiaDetailActivity.this, "服务器异常", Toast.LENGTH_SHORT).show(); //请求失败
                        Looper.loop();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void getZjPage() {
        Log.d("xiamian", zjId + "");
        FormEncodingBuilder builder = new FormEncodingBuilder();
        builder.add("isanswered", "1");
        builder.add("questiontitle", "");
        builder.add("answerer", String.valueOf(zjId));
        builder.add("pageSize", "10");
        builder.add("pageNum", String.valueOf(itemId));
        Request request = new Request.Builder().url(Url.yiReplyUrl).post(builder.build()).build();
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
                        Gson gson = new Gson();
                        JsonReader reader = new JsonReader(new StringReader(body));
                        mYiReplyModel = gson.fromJson(reader, YiReplyModel.class);
                        Log.d("下面问题", body);
                        if (mYiReplyModel.getCode() == 1) {

                            mHandler.post(new Runnable() {
                                @Override
                                public void run() {

                                    mPageData = mYiReplyModel.getData();
                                    if (mPageData.size() > 0) {
                                        mData.addAll(mPageData);
                                        mZhuanjiaDetailAdapter.notifyDataSetChanged();
                                    } else {
                                        mData.clear();
                                        mZhuanjiaDetailAdapter.notifyDataSetChanged();
                                        Log.d("没有", "数据");
                                    }

                                }
                            });
                        } else if (mYiReplyModel.getCode() == 2) {
                            Looper.prepare();
                            Toast.makeText(ZhuanJiaDetailActivity.this, mYiReplyModel.getMsg() + "", Toast.LENGTH_SHORT).show();
                            Looper.loop();
                        } else {
                            Looper.prepare();
                            Toast.makeText(ZhuanJiaDetailActivity.this, mYiReplyModel.getMsg() + "", Toast.LENGTH_SHORT).show();
                            Looper.loop();
                        }

                    } else {
                        Looper.prepare();
                        Toast.makeText(ZhuanJiaDetailActivity.this, "服务器异常", Toast.LENGTH_SHORT).show(); //请求失败
                        Looper.loop();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        getQuestionList();
    }

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
