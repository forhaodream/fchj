package com.fchj.czglgz.chengzhangguanli_high.activity;

import android.app.Activity;
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

/**
 * Created by Administrator on 2018/4/16 0016.
 */

public class LoginActivity extends Activity implements View.OnClickListener {
    private TextView toZhuCe, toForget;
    private TextView forgetTv, zhuceTv;
    private ImageView toLoginImg, userCha, passCha;
    private EditText userNameEd, passWordEd;
    private LoginModel mLoginModel;
    private Handler mHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mHandler = new Handler();
        initView();
    }

    private void initView() {
        forgetTv = (TextView) findViewById(R.id.login_forget);
        forgetTv.setOnClickListener(this);
        zhuceTv = (TextView) findViewById(R.id.login_zhuce);
        zhuceTv.setOnClickListener(this);
        toLoginImg = (ImageView) findViewById(R.id.to_login_img);
        toLoginImg.setOnClickListener(this);
        userCha = (ImageView) findViewById(R.id.username_cha);
        userCha.setOnClickListener(this);
        passCha = (ImageView) findViewById(R.id.password_cha);
        passCha.setOnClickListener(this);
        userNameEd = (EditText) findViewById(R.id.login_username);
        passWordEd = (EditText) findViewById(R.id.login_password);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.login_zhuce:
                IntentUtil.showIntent(LoginActivity.this, ZhuCeActivity.class);
                break;
            case R.id.login_forget:
                IntentUtil.showIntent(LoginActivity.this, ForgetActivity.class);
                break;
            case R.id.username_cha:
                userNameEd.setText("");
                break;
            case R.id.password_cha:
                passWordEd.setText("");
                break;
            case R.id.to_login_img:
                if (!TextUtils.isEmpty(userNameEd.getText().toString()) && !TextUtils.isEmpty(passWordEd.getText().toString())) {
                    initLogin();
                } else {
                    Toast.makeText(LoginActivity.this, "请重新输入信息", Toast.LENGTH_SHORT).show();
                }


                break;
        }
    }

    private void initLogin() {

        FormEncodingBuilder builder = new FormEncodingBuilder();
        builder.add("signname", userNameEd.getText().toString());//账号
        builder.add("signpassword", passWordEd.getText().toString());//密码
        Request request = new Request.Builder().url(Url.loginUrl).post(builder.build()).build();
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
                        if (!TextUtils.isEmpty(body)) {
                            Gson gson = new Gson();
                            JsonReader reader = new JsonReader(new StringReader(body));
                            mLoginModel = gson.fromJson(reader, LoginModel.class);
                            mHandler.post(new Runnable() {
                                @Override
                                public void run() {
                                    if (mLoginModel.getCode() == 1) {
                                        SharedPreferences.Editor userInfo = getSharedPreferences("user_npt", MODE_PRIVATE).edit();
                                        userInfo.putString("userName", mLoginModel.getData().getSignname());
                                        userInfo.putInt("userId", mLoginModel.getData().getId());
                                        userInfo.putInt("userType", mLoginModel.getData().getUsertype());
                                        userInfo.commit();
                                        Toast.makeText(LoginActivity.this, "登录成功", Toast.LENGTH_SHORT).show();
                                        IntentUtil.showIntent(LoginActivity.this, HomeActivity.class);
                                    } else if (mLoginModel.getCode() == 2) {
                                        Toast.makeText(LoginActivity.this, "账号不存在", Toast.LENGTH_SHORT).show();
                                    } else if (mLoginModel.getCode() == 3) {
                                        Toast.makeText(LoginActivity.this, "密码错误", Toast.LENGTH_SHORT).show();
                                    } else {
                                        Toast.makeText(LoginActivity.this, "登录失败", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                        } else {
                            Log.d("没有", "数据");
                        }


                    } else {
                        Looper.prepare();
                        Toast.makeText(LoginActivity.this, "服务器异常", Toast.LENGTH_SHORT).show(); //请求失败
                        Looper.loop();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }


            }
        });


    }
}

