package com.fchj.czglgz.chengzhangguanli_high.test;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.ExpandableListView;
import android.widget.Toast;

import com.fchj.czglgz.chengzhangguanli_high.R;
import com.fchj.czglgz.chengzhangguanli_high.newgaokao.MBSDModel;
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
import java.util.Collections;
import java.util.List;

public class ExPandListActivity extends Activity {
    private Handler mHandler;
    private ExpandableListView exListView;
    private MBSDModel mMBSDModel;
    List<MBSDModel.DataBean> mGroup;
    List<List<MBSDModel.DataBean.LsmjrsBean>> majorList;
    private ExpandListAdapter mAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ex_test);
        mHandler = new Handler();
        exListView = (ExpandableListView) findViewById(R.id.expand);
        addData();
    }

    private void addData() {
//        FormEncodingBuilder builder = new FormEncodingBuilder();
////        builder.add("schoolStatus", "浙江");
////        builder.add("parYear", "2017");
////        builder.add("optF", "物理");
////        builder.add("optS", "化学");
////        builder.add("optT", "生物");
////        builder.add("coursekind", "管理学");
//        Request request = new Request.Builder()
//                .url("http://192.168.0.108:8080/HSGUM/JsonService/Banner/getBannerList.json")//JsonService/UnviersityRecruitAdmitMajo/getMajorCourseListByKind.json
//                .post(builder.build()).build();
//        OkHttpClient okHttpClient = new OkHttpClient();
//        Call call = okHttpClient.newCall(request);
//        call.enqueue(new Callback() {
//            @Override
//            public void onFailure(Request request, IOException e) {
//
//            }
//
//            @Override
//            public void onResponse(Response response) {
        String url = "http://192.168.0.108:8080/HSGUM/JsonService/UnviersityRecruitAdmitMajo/getMajorCourseListByKind.json";
        FormEncodingBuilder builder = new FormEncodingBuilder();
        builder.add("schoolStatus", "浙江");
        builder.add("parYear", "2017");
        builder.add("optF", "物理");
        builder.add("optS", "化学");
        builder.add("optT", "生物");
        builder.add("coursekind", "管理学");
        Request request = new Request.Builder().url(url).post(builder.build()).build();
        OkHttpClient okHttpClient = new OkHttpClient();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {
                Looper.prepare();
                Toast.makeText(ExPandListActivity.this, "服务器异常1", Toast.LENGTH_LONG).show();
                Looper.loop();

            }

            @Override
            public void onResponse(Response response) {
                try {
                    if (response.isSuccessful()) {
                        String body = response.body().string();
                        Log.d("body", body);
                        Gson gson = new Gson();
                        JsonReader reader = new JsonReader(new StringReader(body));
                        mMBSDModel = gson.fromJson(reader, MBSDModel.class);
                        if (mMBSDModel.getCode() == 1) {
                            mHandler.post(new Runnable() {
                                @Override
                                public void run() {
                                    mGroup = mMBSDModel.getData();
                                    majorList = Collections.singletonList(mMBSDModel.getData().get(0).getLsmjrs());
                                    mAdapter = new ExpandListAdapter(mGroup, majorList, ExPandListActivity.this);
                                    exListView.setAdapter(mAdapter);


                                }
                            });
                        } else if (mMBSDModel.getCode() == 2) {
                            Looper.prepare();
                            Toast.makeText(ExPandListActivity.this, mMBSDModel.getMsg() + "", Toast.LENGTH_SHORT).show();
                            Looper.loop();
                        } else {
                            Looper.prepare();
                            Toast.makeText(ExPandListActivity.this, mMBSDModel.getMsg() + "", Toast.LENGTH_SHORT).show();
                            Looper.loop();
                        }
                    } else {
                        Looper.prepare();
                        Toast.makeText(ExPandListActivity.this, "服务器异常2", Toast.LENGTH_SHORT).show(); //请求失败
                        Looper.loop();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
