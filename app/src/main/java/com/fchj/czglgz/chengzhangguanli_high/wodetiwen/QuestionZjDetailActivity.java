package com.fchj.czglgz.chengzhangguanli_high.wodetiwen;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
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

/**
 * Created by Administrator on 2018/4/26 0026.
 */

public class QuestionZjDetailActivity extends Activity {
    private TextView questionWen, questionXiang;
    private EditText questionDa;
    private QuestionDetailModel mQuestionDetailModel;
    private ZJReplyModel mZJReplyModel;
    private int newsId;
    private Handler mHandler;
    private ImageView returnImg;
    private SharedPreferences usernameSp;
    private int userId;
    private TextView tijiaoTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question_zj_detail);
        mHandler = new Handler();
        returnImg = (ImageView) findViewById(R.id.title_fanhui);
        returnImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        tijiaoTv = (TextView) findViewById(R.id.reply_finish);
        tijiaoTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setReply();
            }
        });
        usernameSp = getSharedPreferences("user_npt", MODE_PRIVATE);
        userId = usernameSp.getInt("userId", 0);
        questionWen = (TextView) findViewById(R.id.question_wen_title);
        questionXiang = (TextView) findViewById(R.id.question_xiang_title);
        questionDa = (EditText) findViewById(R.id.question_da_title);
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
                                        questionDa.setText(mQuestionDetailModel.getData().getExpaws() + "");

                                    }
                                }
                            });
                        } else if (mQuestionDetailModel.getCode() == 2) {
                            Looper.prepare();
                            Toast.makeText(QuestionZjDetailActivity.this, mQuestionDetailModel.getMsg() + "", Toast.LENGTH_SHORT).show();
                            Looper.loop();
                        } else {
                            Looper.prepare();
                            Toast.makeText(QuestionZjDetailActivity.this, mQuestionDetailModel.getMsg() + "", Toast.LENGTH_SHORT).show();
                            Looper.loop();
                        }


                    } else {
                        Looper.prepare();
                        Toast.makeText(QuestionZjDetailActivity.this, "服务器异常", Toast.LENGTH_SHORT).show(); //请求失败
                        Looper.loop();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        });
    }

    private void setReply() {
        FormEncodingBuilder builder = new FormEncodingBuilder();
        builder.add("questionid", String.valueOf(newsId));
        builder.add("answercontent", String.valueOf(questionDa.getText()));
        builder.add("answerer", String.valueOf(userId));
        Request request = new Request.Builder().url(Url.zjReplyUrl).post(builder.build()).build();
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
                    mZJReplyModel = gson.fromJson(reader, ZJReplyModel.class);
                    mHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            if (mZJReplyModel.getCode() == 1) {
                                Toast.makeText(QuestionZjDetailActivity.this, "回答提问成功", Toast.LENGTH_SHORT).show();
                                finish();
                            } else if (mZJReplyModel.getCode() == 2) {
                                Toast.makeText(QuestionZjDetailActivity.this, "回答提问失败", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(QuestionZjDetailActivity.this, "回答对应问题不存在", Toast.LENGTH_SHORT).show();

                            }
                        }
                    });
                } else {
                    Log.d("没有", "数据");
                }


            }
        });
    }
}
