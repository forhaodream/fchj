package com.fchj.czglgz.chengzhangguanli_high.jiazhangketang;

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
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.fchj.czglgz.chengzhangguanli_high.R;
import com.fchj.czglgz.chengzhangguanli_high.activity.HttpUrl;
import com.fchj.czglgz.chengzhangguanli_high.activity.ZiXunDetailActivity;
import com.fchj.czglgz.chengzhangguanli_high.util.Url;
import com.fchj.czglgz.chengzhangguanli_high.xuebalaile.XueBaDetailActivity;
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
 * Created by Administrator on 2018/4/19 0019.
 */

public class XLZX extends Fragment {
    private ImageView returnImg;
    private Handler handler;
    private JZFragModel mJZFragModel;
    private List<JZFragModel.DataBean> mData;
    private List<JZFragModel.DataBean> pageData;
    private ListView mListView;
    private int itemId = 1;
    private JZAdapter mJZAdapter;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_jz_frag, null);
        handler = new Handler();
        mListView = (ListView) view.findViewById(R.id.jz_frag_list_view);
        addList();
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (mData.get(position).getIsinc() == 0) {
                    Intent toNoVideo = new Intent(getActivity(), JZNoVideoActivity.class);
                    toNoVideo.putExtra("newsid", mData.get(position).getId());
                    startActivity(toNoVideo);
                } else if (mData.get(position).getIsinc() == 1) {
                    Intent toVideo = new Intent(getActivity(), XueBaDetailActivity.class);
                    toVideo.putExtra("newsid", mData.get(position).getId());
                    startActivity(toVideo);
                }


            }
        });
        PullToRefreshLayout ptr = (PullToRefreshLayout) view.findViewById(R.id.jz_frag_pullrefresh);
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

        return view;
    }


    private void addList() {

        FormEncodingBuilder builder = new FormEncodingBuilder();
        builder.add("classtype", "1");
        builder.add("classname", "");
        builder.add("pageNum", "1");
        builder.add("pageSize", "10");
        Request request = new Request.Builder().url(Url.JZUrl).post(builder.build()).build();//
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
                        mJZFragModel = gson.fromJson(reader, JZFragModel.class);
                        if (mJZFragModel.getCode() == 1) {

                            handler.post(new Runnable() {
                                @Override
                                public void run() {
                                    mData = mJZFragModel.getData();
                                    mJZAdapter = new JZAdapter(mData);
                                    mListView.setAdapter(mJZAdapter);

                                }
                            });
                        } else if (mJZFragModel.getCode() == 2) {
                            Looper.prepare();
                            Toast.makeText(getActivity(), mJZFragModel.getMsg() + "", Toast.LENGTH_SHORT).show();
                            Looper.loop();
                        } else {
                            Looper.prepare();
                            Toast.makeText(getActivity(), mJZFragModel.getMsg() + "", Toast.LENGTH_SHORT).show();
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
        builder.add("classtype", "1");
        builder.add("classname", "");
        builder.add("pageNum", String.valueOf(itemId));
        builder.add("pageSize", "10");
        Request request = new Request.Builder().url(Url.JZUrl).post(builder.build()).build();
        OkHttpClient okHttpClient = new OkHttpClient();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {

            }

            @Override
            public void onResponse(Response response) throws IOException {
                String body = response.body().string();
                Log.d("sdadsadas", body);
                Gson gson = new Gson();
                JsonReader reader = new JsonReader(new StringReader(body));
                mJZFragModel = gson.fromJson(reader, JZFragModel.class);
                pageData = mJZFragModel.getData();
                if (mJZFragModel.getCode() == 1) {

                    handler.post(pageRunn);
                } else if (mJZFragModel.getCode() == 2) {
                    Looper.prepare();
                    Toast.makeText(getActivity(), mJZFragModel.getMsg() + "", Toast.LENGTH_SHORT).show();
                    Looper.loop();
                } else {
                    Looper.prepare();
                    Toast.makeText(getActivity(), mJZFragModel.getMsg() + "", Toast.LENGTH_SHORT).show();
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
                mJZAdapter.notifyDataSetChanged();
            } else {
                pageData.clear();
                mJZAdapter.notifyDataSetChanged();
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
