package com.fchj.czglgz.chengzhangguanli_high.wodetiwen;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
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
import com.fchj.czglgz.chengzhangguanli_high.activity.MyAskActivity;
import com.fchj.czglgz.chengzhangguanli_high.util.IntentUtil;
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

public class QuestionAskActivity extends Activity {
    private ImageView returnImg;
    private TextView finishTv;
    private EditText titleEd, contentEd;
    private AskerModel mAskerModel;
    private Handler mHandler;
    private SharedPreferences usernameSp;
    private int userId, zjId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question_ask);
        mHandler = new Handler();
        usernameSp = getSharedPreferences("user_npt", MODE_PRIVATE);
        userId = usernameSp.getInt("userId", 0);
        Intent intent = getIntent();
        zjId = intent.getIntExtra("zjId", 0);
        titleEd = (EditText) findViewById(R.id.question_ask_title);
        contentEd = (EditText) findViewById(R.id.question_ask_content);
        returnImg = (ImageView) findViewById(R.id.title_fanhui);
        returnImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        finishTv = (TextView) findViewById(R.id.ask_finish);
        finishTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showNormalDialog();
            }
        });
    }

    private void askFinish() {
        FormEncodingBuilder builder = new FormEncodingBuilder();
        builder.add("questiontitle", String.valueOf(titleEd.getText()));
        builder.add("questioncontent", String.valueOf(contentEd.getText()));
        builder.add("userId", String.valueOf(userId));
        builder.add("answerer", String.valueOf(zjId));
        Request request = new Request.Builder().url(Url.askerUrl).post(builder.build()).build();
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
                        mAskerModel = gson.fromJson(reader, AskerModel.class);
                        if (mAskerModel.getCode() == 1) {

                            mHandler.post(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(QuestionAskActivity.this, "提交成功", Toast.LENGTH_SHORT).show();
                                }
                            });
                        } else {
                            Looper.prepare();
                            Toast.makeText(QuestionAskActivity.this, "提交失败", Toast.LENGTH_SHORT).show();
                            Looper.loop();

                        }


                    } else {
                        Looper.prepare();
                        Toast.makeText(QuestionAskActivity.this, "服务器异常", Toast.LENGTH_SHORT).show(); //请求失败
                        Looper.loop();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        });

    }

    private void showNormalDialog() {
        /* @setIcon 设置对话框图标
         * @setTitle 设置对话框标题
         * @setMessage 设置对话框消息提示
         * setXXX方法返回Dialog对象，因此可以链式设置属性
         */
        final AlertDialog.Builder normalDialog =
                new AlertDialog.Builder(QuestionAskActivity.this);
        normalDialog.setTitle("问题提交成功");
        normalDialog.setMessage("问题已保存到「我的提问」是否前往查看?");
        normalDialog.setPositiveButton("确定前往",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        askFinish();
                        Intent toMyAsk = new Intent(QuestionAskActivity.this, MyAskActivity.class);
                        toMyAsk.putExtra("ask", 123);
                        startActivity(toMyAsk);
                    }
                });
        normalDialog.setNegativeButton("取消",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {


                    }
                });
        // 显示
        normalDialog.show();
    }
}
