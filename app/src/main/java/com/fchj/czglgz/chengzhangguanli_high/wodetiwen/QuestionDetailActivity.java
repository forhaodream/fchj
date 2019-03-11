package com.fchj.czglgz.chengzhangguanli_high.wodetiwen;

import android.app.Activity;
import android.content.Intent;
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
import com.fchj.czglgz.chengzhangguanli_high.zixun.ZiXunDetailModel;
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

/**
 * Created by Administrator on 2018/4/24 0024.
 */

public class QuestionDetailActivity extends Activity {
    private TextView questionWen, questionXiang, questionDa;
    private QuestionDetailModel mQuestionDetailModel;
    private int newsId;
    private Handler mHandler;
    private ImageView returnImg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wenti_detail);
        mHandler = new Handler();
        returnImg = (ImageView) findViewById(R.id.title_fanhui);
        returnImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        questionWen = (TextView) findViewById(R.id.question_wen_title);
        questionXiang = (TextView) findViewById(R.id.question_xiang_title);
        questionDa = (TextView) findViewById(R.id.question_da_title);
        Intent intent = getIntent();
        newsId = intent.getIntExtra("newsid", 0);
        getQuestion();

    }

    private void getQuestion() {
        FormEncodingBuilder builder = new FormEncodingBuilder();
        builder.add("qId", String.valueOf(newsId));
        Request request = new Request.Builder().url(Url.questionDetailUrl).post(builder.build()).build();
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
                        mQuestionDetailModel = gson.fromJson(reader, QuestionDetailModel.class);
                        if (mQuestionDetailModel.getCode() == 1) {

                            mHandler.post(new Runnable() {
                                @Override
                                public void run() {
                                    questionWen.setText(mQuestionDetailModel.getData().getQuestiontitle());
                                    questionXiang.setText(mQuestionDetailModel.getData().getQuestioncontent());
                                    if (mQuestionDetailModel.getData().getExpaws() == null) {

                                        questionDa.setText("");

                                    } else {
                                        questionDa.setText(mQuestionDetailModel.getData().getExpaws().getAnswercontent() + "");

                                    }
                                }
                            });
                        } else if (mQuestionDetailModel.getCode() == 2) {
                            Looper.prepare();
                            Toast.makeText(QuestionDetailActivity.this, mQuestionDetailModel.getMsg() + "", Toast.LENGTH_SHORT).show();
                            Looper.loop();
                        } else {
                            Looper.prepare();
                            Toast.makeText(QuestionDetailActivity.this, mQuestionDetailModel.getMsg() + "", Toast.LENGTH_SHORT).show();
                            Looper.loop();
                        }


                    } else {
                        Looper.prepare();
                        Toast.makeText(QuestionDetailActivity.this, "服务器异常", Toast.LENGTH_SHORT).show(); //请求失败
                        Looper.loop();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        });
    }

}
