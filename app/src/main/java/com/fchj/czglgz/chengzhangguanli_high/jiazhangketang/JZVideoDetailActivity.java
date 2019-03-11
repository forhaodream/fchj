package com.fchj.czglgz.chengzhangguanli_high.jiazhangketang;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.fchj.czglgz.chengzhangguanli_high.R;
import com.fchj.czglgz.chengzhangguanli_high.util.Url;
import com.fchj.czglgz.chengzhangguanli_high.xuebalaile.XuebaDetialModel;
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

import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayerStandard;

/**
 * Created by Administrator on 2018/5/7 0007.
 */

public class JZVideoDetailActivity extends Activity {
    private Handler mHandler;
    private ImageView returnImg;
    private JCVideoPlayerStandard mJCVideoPlayerStandard;
    private int sid;
    private XuebaDetialModel mXuebaDetialModel;
    private TextView videoName, videoContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jz_video_detail);
        mHandler = new Handler();
        Intent intent = getIntent();
        sid = intent.getIntExtra("sid", 0);
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
//        videoContent = (TextView) findViewById(R.id.video_content);
    }


    private void setVideo() {

        FormEncodingBuilder builder = new FormEncodingBuilder();
        builder.add("pId", String.valueOf(sid));
        Request request = new Request.Builder().url(Url.jzVideoUrl).post(builder.build()).build();
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
                        mXuebaDetialModel = gson.fromJson(reader, XuebaDetialModel.class);
                        if (mXuebaDetialModel.getCode() == 1) {

//                    mLoginModel = gson.fromJson(reader, LoginModel.class);
                            mHandler.post(new Runnable() {
                                @Override
                                public void run() {
                                    mJCVideoPlayerStandard.setUp((Url.url + mXuebaDetialModel.getData().getContentvoidurl()), JCVideoPlayer.SCREEN_LAYOUT_NORMAL, "");
                                    videoName.setText(mXuebaDetialModel.getData().getVoidname());
//                            videoContent.setText(mXuebaDetialModel.getData().getVoidname());
                                }
                            });
                        } else if (mXuebaDetialModel.getCode() == 2) {
                            Looper.prepare();
                            Toast.makeText(JZVideoDetailActivity.this, mXuebaDetialModel.getMsg() + "", Toast.LENGTH_SHORT).show();
                            Looper.loop();
                        } else {
                            Looper.prepare();
                            Toast.makeText(JZVideoDetailActivity.this, mXuebaDetialModel.getMsg() + "", Toast.LENGTH_SHORT).show();
                            Looper.loop();
                        }


                    } else {
                        Looper.prepare();
                        Toast.makeText(JZVideoDetailActivity.this, "服务器异常", Toast.LENGTH_SHORT).show(); //请求失败
                        Looper.loop();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        });


    }
}
