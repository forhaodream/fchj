package com.fchj.czglgz.chengzhangguanli_high.major;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.fchj.czglgz.chengzhangguanli_high.R;
import com.fchj.czglgz.chengzhangguanli_high.util.Url;
import com.fchj.czglgz.chengzhangguanli_high.wodetiwen.AskDetailActivity;
import com.fchj.czglgz.chengzhangguanli_high.wodetiwen.AskDetailModel;
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

public class ThirdListActivity extends Activity {
    private TextView titleTv;
    private String title, search_str;
    private Handler mHandler;
    private ThirdListModel mThirdListModel;
    private List<String> zyNameList;
    private ThirdListAdapter mThirdListAdapter;
    private ListView mListView;
    private ImageView returnImg;
    private String typeStr;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third_list);
        mHandler = new Handler();
        titleTv = (TextView) findViewById(R.id.title_text);
        returnImg = (ImageView) findViewById(R.id.title_fanhui);
        returnImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        mListView = (ListView) findViewById(R.id.third_list_lv);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent toThreeList = new Intent(ThirdListActivity.this, MajorDetailActivity.class);
                toThreeList.putExtra("zy_name", zyNameList.get(position));
                toThreeList.putExtra("zy_type", typeStr);
                startActivity(toThreeList);
                // Toast.makeText(ThirdListActivity.this, "", Toast.LENGTH_SHORT).show();
            }
        });
        Intent intent = getIntent();
        title = intent.getStringExtra("zy_title");
        search_str = intent.getStringExtra("zy_name");
        typeStr = intent.getStringExtra("zy_type");
        Log.d("zy_name", search_str);
        titleTv.setText(title);
        getThirdList();
    }

    private void getThirdList() {
        FormEncodingBuilder builder = new FormEncodingBuilder();
        builder.add("cajorgenre", search_str);
        Request request = new Request.Builder().url(Url.getThirdList).post(builder.build()).build();
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
                        mThirdListModel = gson.fromJson(reader, ThirdListModel.class);
                        if (!TextUtils.isEmpty(body)) {

                            mHandler.post(new Runnable() {
                                @Override
                                public void run() {
                                    zyNameList = mThirdListModel.getData();
                                    Log.d("zyname", zyNameList.get(0).toString());
                                    mThirdListAdapter = new ThirdListAdapter(zyNameList);
                                    mListView.setAdapter(mThirdListAdapter);
                                }
                            });
                        } else {
                            Log.d("没有", "数据");
                        }
                    } else {
                        Looper.prepare();
                        Toast.makeText(ThirdListActivity.this, "服务器异常", Toast.LENGTH_SHORT).show(); //请求失败
                        Looper.loop();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
