package com.fchj.czglgz.chengzhangguanli_high.wodetiwen;

import android.content.Intent;
import android.content.SharedPreferences;
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
import com.squareup.okhttp.Call;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;
import java.io.StringReader;
import java.util.List;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by Administrator on 2018/4/26 0026.
 */

public class ZjWeiReplyFragment extends Fragment {
    private Handler handler;
    private YiReplyModel mYiReplyModel;
    private List<YiReplyModel.DataBean> mData;
    private List<YiReplyModel.DataBean> pageData;
    private ListView mListView;
    private int itemId = 1;
    private ZjWeiAdapter mZjWeiAdapter;
    private SharedPreferences usernameSp;
    private int userId, zjId, userType;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_yireply, null);
        handler = new Handler();
        usernameSp = getActivity().getSharedPreferences("user_npt", MODE_PRIVATE);
        userId = usernameSp.getInt("userId", 0);
        userType = usernameSp.getInt("userType", 0);
        mListView = (ListView) view.findViewById(R.id.yi_reply_frag_list_view);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent toDetail = new Intent(getActivity(), QuestionZjDetailActivity.class);
                toDetail.putExtra("newsid", mData.get(position).getId());
                startActivity(toDetail);

            }
        });
        addList();
        PullToRefreshLayout ptr = (PullToRefreshLayout) view.findViewById(R.id.yi_reply_frag_pullrefresh);
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
//                addPageCheck();
                pullToRefreshLayout.loadmoreFinish(0);
            }
        });

        return view;
    }


    private void addList() {

        FormEncodingBuilder builder = new FormEncodingBuilder();
        builder.add("isanswered", "0");
        builder.add("questiontitle", "");
        builder.add("answerer", String.valueOf(userId));
        builder.add("pageSize", "10");
        builder.add("pageNum", "1");
        Request request = new Request.Builder().url(Url.weiReplyUrl).post(builder.build()).build();
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
                        Log.d("sdadsadas", body);
                        Log.d("sdadsadas--code", mYiReplyModel.getCode() + "");
                        if (mYiReplyModel.getCode() == 1) {
                            handler.post(new Runnable() {
                                @Override
                                public void run() {
                                    mData = mYiReplyModel.getData();
                                    mZjWeiAdapter = new ZjWeiAdapter(mData);
                                    mListView.setAdapter(mZjWeiAdapter);

                                }
                            });
                        } else if (mYiReplyModel.getCode() == 2) {
                            Looper.prepare();
                            Toast.makeText(getActivity(), mYiReplyModel.getMsg() + "", Toast.LENGTH_SHORT).show();
                            Looper.loop();
                        } else {
                            Looper.prepare();
                            Toast.makeText(getActivity(), mYiReplyModel.getMsg() + "", Toast.LENGTH_SHORT).show();
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
        builder.add("isanswered", "1");
        builder.add("userId", String.valueOf(userId));
        Request request = new Request.Builder().url(Url.myAskYiUrl).post(builder.build()).build();
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
                mYiReplyModel = gson.fromJson(reader, YiReplyModel.class);
                if (mYiReplyModel.getCode() == 1) {
                    pageData = mYiReplyModel.getData();
                    handler.post(pageRunn);
                } else if (mYiReplyModel.getCode() == 2) {
                    Looper.prepare();
                    Toast.makeText(getActivity(), mYiReplyModel.getMsg() + "", Toast.LENGTH_SHORT).show();
                    Looper.loop();
                } else {
                    Looper.prepare();
                    Toast.makeText(getActivity(), mYiReplyModel.getMsg() + "", Toast.LENGTH_SHORT).show();
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
                mZjWeiAdapter.notifyDataSetChanged();
            } else {
                mData.clear();
                mZjWeiAdapter.notifyDataSetChanged();
                Log.d("没有", "数据");
            }
        }
    };

    @Override
    public void onResume() {
        super.onResume();
        addList();
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