package com.fchj.czglgz.chengzhangguanli_high.jiazhangketang;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.fchj.czglgz.chengzhangguanli_high.R;
import com.fchj.czglgz.chengzhangguanli_high.activity.ForgetActivity;
import com.fchj.czglgz.chengzhangguanli_high.activity.HomeActivity;
import com.fchj.czglgz.chengzhangguanli_high.activity.ZhuCeActivity;
import com.fchj.czglgz.chengzhangguanli_high.model.LoginModel;
import com.fchj.czglgz.chengzhangguanli_high.util.IntentUtil;
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

public class JiaZhangDetail extends Activity {

    private Handler mHandler;
    private ImageView returnImg;
    private TextView videoName, videoContent;
    private JCVideoPlayerStandard mJCVideoPlayerStandard;
    private JZDetailModel mJZDetailModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_detail);
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
        mJCVideoPlayerStandard = (JCVideoPlayerStandard) findViewById(R.id.video_jcplayer);
        videoName = (TextView) findViewById(R.id.video_name);
        videoContent = (TextView) findViewById(R.id.video_content);
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
            public void onResponse(Response response) throws IOException {
                String body = response.body().string();
                Log.d("asdasdasdasd", body);
                if (!TextUtils.isEmpty(body)) {
                    Gson gson = new Gson();
                    JsonReader reader = new JsonReader(new StringReader(body));

//                    mLoginModel = gson.fromJson(reader, LoginModel.class);
                    mHandler.post(new Runnable() {
                        @Override
                        public void run() {

                        }
                    });
                } else {
                    Log.d("没有", "数据");
                }


            }
        });


    }
}

