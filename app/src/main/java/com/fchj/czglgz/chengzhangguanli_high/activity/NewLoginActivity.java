package com.fchj.czglgz.chengzhangguanli_high.activity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
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
import com.tencent.mm.opensdk.modelmsg.SendAuth;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.zhy.autolayout.AutoRelativeLayout;

import java.io.IOException;
import java.io.StringReader;

/**
 * Created by Administrator on 2018/5/15 0015.
 */

public class NewLoginActivity extends Activity implements View.OnClickListener {
    // 微信登录
    private static final String APP_ID = "wx9fefde76187f8505";
    private IWXAPI api;
    private TextView forgetTv, regTv;
    private AutoRelativeLayout toLoginRl;
    private ImageView toQQ, toWechat;
    private EditText userNameEd, passWordEd;
    private LoginModel mLoginModel;
    private boolean isExit;
    private Handler mHandler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            isExit = false;
        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= 21) {
            View decorView = getWindow().getDecorView();
            decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }
        setContentView(R.layout.activity_new_login);
        api = WXAPIFactory.createWXAPI(this, APP_ID, true);
        api.registerApp(APP_ID);
        mHandler = new Handler();
        initView();
    }

    private void initView() {
        // 输入框
        userNameEd = (EditText) findViewById(R.id.login_name);
        passWordEd = (EditText) findViewById(R.id.login_psd);
        //  第三方登录
        toQQ = (ImageView) findViewById(R.id.login_to_qq);
        toQQ.setOnClickListener(this);
        toWechat = (ImageView) findViewById(R.id.login_to_wechat);
        toWechat.setOnClickListener(this);
        // 按钮
        toLoginRl = (AutoRelativeLayout) findViewById(R.id.login_log_button);
        toLoginRl.setOnClickListener(this);
        forgetTv = (TextView) findViewById(R.id.login_to_forget);
        forgetTv.setOnClickListener(this);
        regTv = (TextView) findViewById(R.id.login_to_reg);
        regTv.setOnClickListener(this);


    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.login_to_reg:
                IntentUtil.showIntent(NewLoginActivity.this, NewZhuCeActivity.class);
                break;
            case R.id.login_to_forget:
                IntentUtil.showIntent(NewLoginActivity.this, ForgetActivity.class);
                break;
            case R.id.login_to_qq:
                Toast.makeText(this, "暂未开放, 请选择其他方式登录", Toast.LENGTH_SHORT).show();
                break;
            case R.id.login_to_wechat:
                if (!api.isWXAppInstalled()) {
                    Toast.makeText(this, "请安装微信", Toast.LENGTH_SHORT).show();
                } else {
                    final SendAuth.Req req = new SendAuth.Req();
                    req.scope = "snsapi_userinfo";
                    req.state = "wechat_sdk_demo_test";
                    api.sendReq(req);
                }

                break;
            case R.id.login_log_button:
                ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
                NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
                if (networkInfo == null || !networkInfo.isAvailable()) {
                    Toast.makeText(this, "无法连接到网络", Toast.LENGTH_SHORT).show();
                } else {
                    //当前有可用网络
                    if (!TextUtils.isEmpty(userNameEd.getText().toString()) && !TextUtils.isEmpty(passWordEd.getText().toString())) {
                        initLogin();
                    } else {
                        Toast.makeText(NewLoginActivity.this, "请重新输入信息", Toast.LENGTH_SHORT).show();
                    }

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
                                        Toast.makeText(NewLoginActivity.this, mLoginModel.getMsg() + "", Toast.LENGTH_SHORT).show();
                                        IntentUtil.showIntent(NewLoginActivity.this, HomeActivity.class);
                                    } else if (mLoginModel.getCode() == 2) {
                                        Toast.makeText(NewLoginActivity.this, mLoginModel.getMsg() + "", Toast.LENGTH_SHORT).show();
                                    } else if (mLoginModel.getCode() == 3) {
                                        Toast.makeText(NewLoginActivity.this, mLoginModel.getMsg() + "", Toast.LENGTH_SHORT).show();
                                    } else {
                                        Toast.makeText(NewLoginActivity.this, mLoginModel.getMsg() + "", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                        } else {
                            Log.d("没有", "数据");
                        }


                    } else {
                        Looper.prepare();
                        Toast.makeText(NewLoginActivity.this, "服务器异常", Toast.LENGTH_SHORT).show(); //请求失败
                        Looper.loop();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        });


    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            exit();
            System.gc();

            return false;
        } else {
            return super.onKeyDown(keyCode, event);
        }
    }

    public void exit() {
        if (!isExit) {
            isExit = true;
            Toast.makeText(getApplicationContext(), "再按一次退出程序", Toast.LENGTH_SHORT).show();
            mHandler.sendEmptyMessageDelayed(0, 2000);
        } else {
            Intent intent = new Intent(Intent.ACTION_MAIN);
            intent.addCategory(Intent.CATEGORY_HOME);
            startActivity(intent);
            System.exit(0);
        }
    }
}
