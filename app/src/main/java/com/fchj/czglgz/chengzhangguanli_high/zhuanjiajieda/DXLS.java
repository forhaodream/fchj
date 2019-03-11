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
import com.fchj.czglgz.chengzhangguanli_high.model.TestModel;
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

public class DXLS extends Fragment {
    private Handler handler;
    private TestModel mTestModel;
    private List<TestModel.DataBean> mData;
    private List<TestModel.DataBean> pageData;
    private ListView mListView;
    private int itemId = 1;
    private ZJAdapter mZJAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_zj_frag, null);
        handler = new Handler();
        mListView = (ListView) view.findViewById(R.id.jz_frag_list_view);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent toDetail = new Intent(getActivity(), ZhuanJiaDetailActivity.class);
                toDetail.putExtra("zjId", mData.get(position).getId());
                startActivity(toDetail);

            }
        });
        addList();
        PullToRefreshLayout ptr = (PullToRefreshLayout) view.findViewById(R.id.zj_frag_pullrefresh);
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
                pullToRefreshLayout.loadmoreFinish(1);
            }
        });

        return view;
    }


    private void addList() {

        FormEncodingBuilder builder = new FormEncodingBuilder();
        builder.add("usertype", "2");
        builder.add("asktype", "4");
        Request request = new Request.Builder().url(Url.zjListUrl).post(builder.build()).build();
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
                        mTestModel = gson.fromJson(reader, TestModel.class);
                        mData = mTestModel.getData();
                        if (mTestModel.getCode() == 1) {

                            handler.post(new Runnable() {
                                @Override
                                public void run() {
                                    mZJAdapter = new ZJAdapter(mData);
                                    mListView.setAdapter(mZJAdapter);

                                }
                            });
                        } else if (mTestModel.getCode() == 2) {
                            Looper.prepare();
                            Toast.makeText(getActivity(), mTestModel.getMsg() + "", Toast.LENGTH_SHORT).show();
                            Looper.loop();
                        } else {
                            Looper.prepare();
                            Toast.makeText(getActivity(), mTestModel.getMsg() + "", Toast.LENGTH_SHORT).show();
                            Looper.loop();
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
            public void onResponse(Response response) {
                try {
                    if (response.isSuccessful()) {
                String body = response.body().string();
                Log.d("sdadsadas", body);
                Gson gson = new Gson();
                JsonReader reader = new JsonReader(new StringReader(body));
                mTestModel = gson.fromJson(reader, TestModel.class);
                pageData = mTestModel.getData();
                if (mTestModel.getCode() == 1) {
                    handler.post(pageRunn);
                } else if (mTestModel.getCode() == 2) {
                    Looper.prepare();
                    Toast.makeText(getActivity(), mTestModel.getMsg() + "", Toast.LENGTH_SHORT).show();
                    Looper.loop();
                } else {
                    Looper.prepare();
                    Toast.makeText(getActivity(), mTestModel.getMsg() + "", Toast.LENGTH_SHORT).show();
                    Looper.loop();
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

    Runnable pageRunn = new Runnable() {
        @Override
        public void run() {
            if (pageData.size() > 0) {
                mData.addAll(pageData);
                mZJAdapter.notifyDataSetChanged();
            } else {
                mData.clear();
                mZJAdapter.notifyDataSetChanged();
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
