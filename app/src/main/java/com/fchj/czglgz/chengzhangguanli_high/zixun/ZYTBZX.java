package com.fchj.czglgz.chengzhangguanli_high.zixun;

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
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.fchj.czglgz.chengzhangguanli_high.R;
import com.fchj.czglgz.chengzhangguanli_high.activity.ZiXunDetailActivity;
import com.fchj.czglgz.chengzhangguanli_high.model.ZcYzmModel;
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
 * Created by Administrator on 2018/4/18 0018.
 */

public class ZYTBZX extends Fragment {
    private ImageView returnImg;
    private Handler handler;
    private ZXFragModel mZXFragModel;
    private List<ZXFragModel.DataBean> mData;
    private List<ZXFragModel.DataBean> pageData;
    private ListView mListView;
    private int itemId = 1;
    private ZXFragAdapter mZXFragAdapter;
    private String f = "66";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_zx_frag, null);
        handler = new Handler();
        mListView = (ListView) view.findViewById(R.id.zx_frag_list_view);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent toDetail = new Intent(getActivity(), ZiXunDetailActivity.class);
                toDetail.putExtra("newsid", mData.get(position).getId());
                startActivity(toDetail);

            }
        });
        addList();
        PullToRefreshLayout ptr = (PullToRefreshLayout) view.findViewById(R.id.zx_frag_pullrefresh);
        mListView = (ListView) ptr.getPullableView();
        ptr.setOnPullListener(new PullToRefreshLayout.OnPullListener() {
            @Override
            public void onRefresh(PullToRefreshLayout pullToRefreshLayout) {
                addList();
                pullToRefreshLayout.refreshFinish(0);
            }

            @Override
            public void onLoadMore(PullToRefreshLayout pullToRefreshLayout) {
                if (mZXFragModel.getCode() == 1) {
                    itemId += 1;
                    addPageCheck();
                    pullToRefreshLayout.loadmoreFinish(0);
                } else {
                    Toast.makeText(getActivity(), "没有更多资讯", Toast.LENGTH_SHORT).show();
                    pullToRefreshLayout.loadmoreFinish(1);
                }
            }
        });

        return view;
    }


    private void addList() {

        FormEncodingBuilder builder = new FormEncodingBuilder();
        builder.add("infotype", "3");
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
                        Gson gson = new Gson();
                        JsonReader reader = new JsonReader(new StringReader(body));
                        mZXFragModel = gson.fromJson(reader, ZXFragModel.class);
                        Log.d("sdadsadas", body);
                        if (mZXFragModel.getCode() == 1) {
                            handler.post(new Runnable() {
                                @Override
                                public void run() {

                                    mData = mZXFragModel.getData();
                                    mZXFragAdapter = new ZXFragAdapter(mData);
                                    mListView.setAdapter(mZXFragAdapter);

                                }
                            });
                        } else if (mZXFragModel.getCode() == 2) {
                            Looper.prepare();
                            Toast.makeText(getActivity(), mZXFragModel.getMsg() + "", Toast.LENGTH_SHORT).show();
                            Looper.loop();
                        } else {
                            Looper.prepare();
                            Toast.makeText(getActivity(), mZXFragModel.getMsg() + "", Toast.LENGTH_SHORT).show();
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
        builder.add("infotype", "3");
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
                        Log.d("body", body);
                        Gson gson = new Gson();
                        JsonReader reader = new JsonReader(new StringReader(body));
                        mZXFragModel = gson.fromJson(reader, ZXFragModel.class);
                        pageData = mZXFragModel.getData();
                        if (mZXFragModel.getCode() == 1) {
                            handler.post(pageRunn);
                        } else if (mZXFragModel.getCode() == 2) {
                            Looper.prepare();
                            Toast.makeText(getActivity(), mZXFragModel.getMsg() + "", Toast.LENGTH_SHORT).show();
                            Looper.loop();

                        } else {
                            Looper.prepare();
                            Toast.makeText(getActivity(), mZXFragModel.getMsg() + "", Toast.LENGTH_SHORT).show();
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
                mZXFragAdapter.notifyDataSetChanged();
            } else {
                mData.clear();
                mZXFragAdapter.notifyDataSetChanged();
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
