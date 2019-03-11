package com.fchj.czglgz.chengzhangguanli_high.test;

import android.app.Activity;
import android.os.Bundle;
import android.os.Looper;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;

import com.fchj.czglgz.chengzhangguanli_high.R;
import com.fchj.czglgz.chengzhangguanli_high.mysc.TestsModel;
import com.fchj.czglgz.chengzhangguanli_high.treelist.Node;
import com.fchj.czglgz.chengzhangguanli_high.treelist.TreeListViewAdapter;
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
import java.util.ArrayList;
import java.util.List;

public class TestTreeActivity extends Activity {
    private List<FileBean> mDatas = new ArrayList<FileBean>();
    private ListView mTree;
    private TreeListViewAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_benke);
        getBenKeData();
//        initDatas();
        mTree = (ListView) findViewById(R.id.frag_benke_lv);
        try {
            mAdapter = new SimpleTreeAdapter<FileBean>(mTree, this, mDatas, 10);

            mAdapter.setOnTreeNodeClickListener(new TreeListViewAdapter.OnTreeNodeClickListener() {
                @Override
                public void onClick(Node node, int position) {
                    if (node.isLeaf()) {
                        Toast.makeText(getApplicationContext(), node.getName(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
            mTree.setAdapter(mAdapter);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    TestsModel mTestsModel;

    private void getBenKeData() {
        FormEncodingBuilder builder = new FormEncodingBuilder();
        builder.add("coursetype", "本科专业");
        Request request = new Request.Builder().url(Url.getZyListUrl).post(builder.build()).build();
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
                        mTestsModel = gson.fromJson(reader, TestsModel.class);
                        Log.d("testsmodel", mTestsModel.toString());
                        Log.d("testsmodel", body);


                    } else {
                        Looper.prepare();
                        Toast.makeText(TestTreeActivity.this, "服务器异常", Toast.LENGTH_SHORT).show(); //请求失败
                        Looper.loop();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        });
    }

    private String[] vocationName = {"农、林、牧、渔业", "采矿业", "制造业", "电力、热力、燃气及水生产和供应业", "建筑业"
            , "批发和零售业", "交通运输、仓储和邮政业", "住宿和餐饮业", "信息传输、软件和信息技术服务业", "金融业"
            , "房地产业", "租赁和商户服务业", "科学研究和技术服务业", "水利、环境和公共设施管理业", "居民服务、修理和其他服务业"
            , "教育", "卫生和社会工作", "文化、体育和娱乐业", "公共管理、社会保障和社会组织", "通用或其他"};

//    private void initDatas() {
//        List<String> subjectList;
//        subjectList = new ArrayList<String>();
//        subjectList.add("文科");
//        subjectList.add("理科");
//        subjectList.add("艺术类");
//        subjectList.add("体育类");
//        subjectList.add("综合");
//        subjectList.add("文理");
//        for (int i = 0; i < vocationName.length; i++) {
//            mDatas.add(new FileBean(i, 0, vocationName[i]));
//        }
//        for (int i = 0; i < vocationName.length; i++) {
//            for (int j = 0; j < subjectList.size(); j++) {
//                mDatas.add(new FileBean(i, j, subjectList.get(j)));
//
//            }
//
//        }
//
//        // id , pid , label , 其他属性
//        mDatas.add(new FileBean(1, 0, "文件管理系统"));
//        mDatas.add(new FileBean(2, 0, "游戏"));
//        mDatas.add(new FileBean(3, 0, "文档"));
//        mDatas.add(new FileBean(4, 0, "程序"));
//        mDatas.add(new FileBean(5, 0, "war3"));
//        mDatas.add(new FileBean(6, 0, "刀塔传奇"));
//
//        mDatas.add(new FileBean(7, 1, "面向对象"));
//        mDatas.add(new FileBean(8, 1, "非面向对象"));
//
//        mDatas.add(new FileBean(9, 2, "C++"));
//        mDatas.add(new FileBean(10, 2, "JAVA"));
//        mDatas.add(new FileBean(11, 2, "Javascript"));
//        mDatas.add(new FileBean(12, 2, "C语言"));
//
//
//    }

}