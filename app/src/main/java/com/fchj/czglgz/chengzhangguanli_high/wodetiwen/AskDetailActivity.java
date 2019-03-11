package com.fchj.czglgz.chengzhangguanli_high.wodetiwen;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.fchj.czglgz.chengzhangguanli_high.R;
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

import fm.jiecao.jcvideoplayer_lib.JCVideoPlayerStandard;

/**
 * Created by Administrator on 2018/4/23 0023.
 */

public class AskDetailActivity extends Activity {
    private Handler mHandler;
    private ImageView returnImg;
    private TextView askTv, answerTv;
    private AskDetailModel mAskDetailModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ask_detail);
        mHandler = new Handler();
        initView();
        setVideo();
    }

    private void initView() {
        returnImg = (ImageView) findViewById(R.id.title_fanhui);
        returnImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        askTv = (TextView) findViewById(R.id.video_name);
        answerTv = (TextView) findViewById(R.id.video_content);
    }

    private void setVideo() {

        FormEncodingBuilder builder = new FormEncodingBuilder();
        builder.add("sId", "1");
        Request request = new Request.Builder().url(Url.JZDetailUrl).post(builder.build()).build();
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
                        mAskDetailModel = gson.fromJson(reader, AskDetailModel.class);
                        if (!TextUtils.isEmpty(body)) {

                            mHandler.post(new Runnable() {
                                @Override
                                public void run() {
//                            askTv.setText();
//                            answerTv.setText();
                                }
                            });
                        } else {
                            Log.d("没有", "数据");
                        }


                    } else {
                        Looper.prepare();
                        Toast.makeText(AskDetailActivity.this, "服务器异常", Toast.LENGTH_SHORT).show(); //请求失败
                        Looper.loop();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });


    }
}
