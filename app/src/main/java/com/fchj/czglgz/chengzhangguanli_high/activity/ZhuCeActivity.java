package com.fchj.czglgz.chengzhangguanli_high.activity;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.fchj.czglgz.chengzhangguanli_high.R;
import com.fchj.czglgz.chengzhangguanli_high.model.ZcYzmModel;
import com.fchj.czglgz.chengzhangguanli_high.model.ZhuCeModel;
import com.fchj.czglgz.chengzhangguanli_high.util.IntentUtil;
import com.fchj.czglgz.chengzhangguanli_high.util.Url;
import com.fchj.czglgz.chengzhangguanli_high.view.MyEditView;
import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import com.squareup.okhttp.Call;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import com.zhy.autolayout.AutoRelativeLayout;

import java.io.IOException;
import java.io.StringReader;

/**
 * Created by Administrator on 2018/4/16 0016.
 */

public class ZhuCeActivity extends Activity implements View.OnClickListener {
    private AutoRelativeLayout mZhuCe;
    private Handler mHandler;
    private ZhuCeModel mZhuCeModel;
    private MyEditView phoneEd, psdFirEd, psdSecEd, yzmEd;
    private String pwd;
    private String again;
    private ImageView yzmImg;
    private TextView mYZmTv;
    private TimeCount time;
    private AutoRelativeLayout mYZMRl;
    private Button mYZMBtn;
    private ZcYzmModel mZcYzmModel;
    private ImageView returnImg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zhuce);
        mHandler = new Handler();
        time = new TimeCount(60000, 1000);
        initView();
//        initLogin();
    }

    private void initView() {
        returnImg = (ImageView) findViewById(R.id.title_fanhui);
        returnImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        mZhuCe = (AutoRelativeLayout) findViewById(R.id.rl_tozhuce);
        mZhuCe.setOnClickListener(this);
        phoneEd = (MyEditView) findViewById(R.id.ed_phone);
        phoneEd.setInputType(EditorInfo.TYPE_CLASS_PHONE);
        psdFirEd = (MyEditView) findViewById(R.id.ed_psdfirst);
        psdSecEd = (MyEditView) findViewById(R.id.ed_psdsecond);
        pwd = psdFirEd.getText().toString();
        again = psdSecEd.getText().toString();
        yzmEd = (MyEditView) findViewById(R.id.ed_yanzheng);
        yzmEd.setInputType(EditorInfo.TYPE_CLASS_PHONE);
//        mYZMRl = (AutoRelativeLayout) findViewById(R.id.zhuce_yanzhengma);
//        mYZMRl.setOnClickListener(this);
        mYZMBtn = (Button) findViewById(R.id.zhuce_yzm_text);
        mYZMBtn.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rl_tozhuce:
                initZhuCe();
                break;
            case R.id.zhuce_yzm_text:
                sendYZM();
                time.start();
                break;

        }
    }

    private void sendYZM() {
        FormEncodingBuilder builder = new FormEncodingBuilder();
        builder.add("signname", phoneEd.getText().toString());
        Request request = new Request.Builder().url(Url.zcYAMUrl).post(builder.build()).build();
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
                        Log.d("sdadsadas", body);
                        Gson gson = new Gson();
                        JsonReader reader = new JsonReader(new StringReader(body));
                        mZcYzmModel = gson.fromJson(reader, ZcYzmModel.class);
                        if (mZcYzmModel.getCode() == 1) {

                            mHandler.post(new Runnable() {
                                @Override
                                public void run() {

                                    Toast.makeText(ZhuCeActivity.this, "发送成功", Toast.LENGTH_SHORT).show();

                                }
                            });
                        } else if (mZcYzmModel.getCode() == 2) {
                            Looper.prepare();
                            Toast.makeText(ZhuCeActivity.this, "用户已经存在", Toast.LENGTH_SHORT).show();
                            Looper.loop();
                        } else {
                            Looper.prepare();
                            Toast.makeText(ZhuCeActivity.this, "发送失败", Toast.LENGTH_SHORT).show();
                            Looper.loop();
                        }


                    } else {
                        Looper.prepare();
                        Toast.makeText(ZhuCeActivity.this, "服务器异常", Toast.LENGTH_SHORT).show(); //请求失败
                        Looper.loop();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        });
    }

    private void initZhuCe() {

        FormEncodingBuilder builder = new FormEncodingBuilder();
        builder.add("signname", phoneEd.getText().toString());
        builder.add("signpassword", psdSecEd.getText().toString());

        Request request = new Request.Builder().url(Url.zhuCeUrl).post(builder.build()).build();
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
                        Log.d("sdadsadas", body);
                        Gson gson = new Gson();
                        JsonReader reader = new JsonReader(new StringReader(body));
                        mZhuCeModel = gson.fromJson(reader, ZhuCeModel.class);
                        if (mZhuCeModel.getCode() == 1) {

                            mHandler.post(new Runnable() {
                                @Override
                                public void run() {
                                    if (mZhuCeModel.getMsg().equals("注册成功")) {
                                        Toast.makeText(ZhuCeActivity.this, "注册成功", Toast.LENGTH_SHORT).show();
                                        IntentUtil.showIntent(ZhuCeActivity.this, LoginActivity.class);
                                    }
                                }
                            });
                        } else if (mZhuCeModel.getCode() == 2) {
                            Looper.prepare();
                            Toast.makeText(ZhuCeActivity.this, mZhuCeModel.getMsg() + "", Toast.LENGTH_SHORT).show();
                            Looper.loop();
                        } else {
                            Looper.prepare();
                            Toast.makeText(ZhuCeActivity.this, mZhuCeModel.getMsg() + "", Toast.LENGTH_SHORT).show();
                            Looper.loop();
                        }
                    } else {
                        Looper.prepare();
                        Toast.makeText(ZhuCeActivity.this, "服务器异常", Toast.LENGTH_SHORT).show(); //请求失败
                        Looper.loop();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        });


    }

    class TimeCount extends CountDownTimer {

        public TimeCount(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onTick(long millisUntilFinished) {
            //  mYZMBtn.setBackgroundColor(Color.parseColor("#60a0fa"));
            mYZMBtn.setClickable(false);
            mYZMBtn.setText(millisUntilFinished / 1000 + "秒后重发");
        }

        @Override
        public void onFinish() {
            mYZMBtn.setText("重新获取验证码");
            mYZMBtn.setClickable(true);
            // mYZMBtn.setBackgroundColor(Color.parseColor("#4EB84A"));

        }
    }

}

