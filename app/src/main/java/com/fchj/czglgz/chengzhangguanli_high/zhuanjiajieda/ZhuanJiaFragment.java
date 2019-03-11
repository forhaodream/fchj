package com.fchj.czglgz.chengzhangguanli_high.zhuanjiajieda;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.fchj.czglgz.chengzhangguanli_high.R;
import com.fchj.czglgz.chengzhangguanli_high.util.Url;
import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import com.lh.ch.pulltorefresh.PullToRefreshLayout;
import com.lh.ch.pulltorefresh.PullableListView;
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
 * Created by Administrator on 2018/4/9 0009.
 */

public class ZhuanJiaFragment extends Fragment {
    private View mView;
    private Handler mHandler;
    private PullableListView mPullableListView;
    private ListView mListView;
    private ZJAdapter mZJAdapter;
    private List<ZJFragModel> mData;
    private List<ZJFragModel> pageData;
    private int itemId = 1;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_zhuanjie_detail, null);
        mHandler = new Handler();
        mListView = (ListView) mView.findViewById(R.id.zj_detail_list);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent toDetail = new Intent(getActivity(), ZhuanJiaDetailActivity.class);
                startActivity(toDetail);
            }
        });
        PullToRefreshLayout ptr = (PullToRefreshLayout) mView.findViewById(R.id.zj_detail_pullrefresh);
        ptr.setOnPullListener(new PullToRefreshLayout.OnPullListener() {
                                  @Override
                                  public void onRefresh(PullToRefreshLayout pullToRefreshLayout) {
//                                      addYXL(Url.yxlUrl);
                                      itemId = 1;
                                      pullToRefreshLayout.refreshFinish(0);
                                  }

                                  @Override
                                  public void onLoadMore(PullToRefreshLayout pullToRefreshLayout) {
                                      itemId += 1;
//


                                      Toast.makeText(getActivity(), "加载完成,上拉查看", Toast.LENGTH_SHORT).show();
                                      pullToRefreshLayout.loadmoreFinish(0);
                                  }
                              }

        );
        return mView;
    }

    private void addList() {

        FormEncodingBuilder builder = new FormEncodingBuilder();
        builder.add("infotype", "1");
        builder.add("title", "");
        builder.add("pageNum", "1");
        builder.add("pageSize", "10");
        Request request = new Request.Builder().url(Url.pageZXUrl).post(builder.build()).build();
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
                if (!TextUtils.isEmpty(body)) {
                    Gson gson = new Gson();
                    JsonReader reader = new JsonReader(new StringReader(body));
//                    mZXFragModel = gson.fromJson(reader, ZXFragModel.class);
//                    handler.post(new Runnable() {
//                        @Override
//                        public void run() {
//
//                            mData = mZXFragModel.getData();
//                            mZXFragAdapter = new ZXFragAdapter(mData);
//                            mListView.setAdapter(mZXFragAdapter);
//
//                        }
//                    });
                } else {
                    Log.d("没有", "数据");
                }






                    } else {
                        Looper.prepare();
                        Toast.makeText(getActivity(), "服务器异常", Toast.LENGTH_SHORT).show(); //请求失败
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
        builder.add("infotype", "1");
        builder.add("title", "");
        Request request = new Request.Builder().url(Url.pageZXUrl).post(builder.build()).build();
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
                try {
                    if (!TextUtils.isEmpty(body)) {
                        Gson gson = new Gson();
                        JsonReader reader = new JsonReader(new StringReader(body));
//                        mZXFragModel = gson.fromJson(reader, ZXFragModel.class);
//                        pageData = mZXFragModel.getData();
//                        handler.post(pageRunn);
                    } else {

                        Log.d("没有", "数据");

                    }
                } catch (IllegalStateException e) {
                }
            }
        });


    }

    Runnable pageRunn = new Runnable() {
        @Override
        public void run() {
            if (pageData.size() > 0) {
                mData.addAll(pageData);
//                mZXFragAdapter.notifyDataSetChanged();
            } else {
                mData.clear();
//                mZXFragAdapter.notifyDataSetChanged();
                Log.d("没有", "数据");
            }
        }
    };
}
