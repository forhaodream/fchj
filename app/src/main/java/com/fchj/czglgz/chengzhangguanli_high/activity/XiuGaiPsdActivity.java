package com.fchj.czglgz.chengzhangguanli_high.activity;

import android.app.Activity;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
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
import android.widget.Toast;

import com.fchj.czglgz.chengzhangguanli_high.R;
import com.fchj.czglgz.chengzhangguanli_high.model.XiuGaiPsdModel;
import com.fchj.czglgz.chengzhangguanli_high.model.ZcYzmModel;
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
import com.zhy.autolayout.AutoRelativeLayout;

import java.io.IOException;
import java.io.StringReader;

import static com.fchj.czglgz.chengzhangguanli_high.activity.NewZhuCeActivity.isMobileNO;

/**
 * Created by Administrator on 2018/5/21 0021.
 */

public class XiuGaiPsdActivity extends Activity implements View.OnClickListener {
    private Handler mHandler;
    private XiuGaiPsdModel mXiuGaiPsdModel;
    private EditText phoneEd, psdFirEd, psdSecEd;
    private String pwd;
    private String again;
    private TimeCount time;
    private Button mYZMBtn;
    private SharedPreferences username;
    private int userId;
    private ImageView returnImg;
    private AutoRelativeLayout toForget;
    private ZcYzmModel mZcYzmModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= 21) {
            View decorView = getWindow().getDecorView();
            decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }
        setContentView(R.layout.activity_xiugai_psd);
        mHandler = new Handler();
        time = new TimeCount(60000, 1000);
        username = getSharedPreferences("user_npt", MODE_PRIVATE);
        userId = username.getInt("userId", 0);
        initView();
    }

    private void initView() {
        returnImg = (ImageView) findViewById(R.id.title_fanhui);
        returnImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        phoneEd = (EditText) findViewById(R.id.ed_phone);
        phoneEd.setInputType(EditorInfo.TYPE_CLASS_PHONE);
        psdFirEd = (EditText) findViewById(R.id.ed_psdfirst);
        psdSecEd = (EditText) findViewById(R.id.ed_psdsecond);
        pwd = psdFirEd.getText().toString();
        again = psdSecEd.getText().toString();
        toForget = (AutoRelativeLayout) findViewById(R.id.rl_to_forget);
        toForget.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rl_to_forget:
                if (isMobileNO(phoneEd.getText().toString())
                        && !TextUtils.isEmpty(psdFirEd.getText().toString())
                        && !TextUtils.isEmpty(psdSecEd.getText().toString())) {
                    initZhuCe();
                } else {
                    Toast.makeText(XiuGaiPsdActivity.this, "请输入正确的手机号", Toast.LENGTH_SHORT).show();

                }

                break;

        }
    }

//    private void sendYZM() {
//        FormEncodingBuilder builder = new FormEncodingBuilder();
//        builder.add("signname", phoneEd.getText().toString());
//        Request request = new Request.Builder().url(Url.zcYAMUrl).post(builder.build()).build();
//        OkHttpClient okHttpClient = new OkHttpClient();
//        Call call = okHttpClient.newCall(request);
//        call.enqueue(new Callback() {
//            @Override
//            public void onFailure(Request request, IOException e) {
//
//            }
//
//            @Override
//            public void onResponse(Response response) throws IOException {
//                String body = response.body().string();
//                Log.d("sdadsadas", body);
//                if (!TextUtils.isEmpty(body)) {
//                    Gson gson = new Gson();
//                    JsonReader reader = new JsonReader(new StringReader(body));
//                    mZcYzmModel = gson.fromJson(reader, ZcYzmModel.class);
//                    mHandler.post(new Runnable() {
//                        @Override
//                        public void run() {
//                            if (mZcYzmModel.getMsg().equals("发送成功")) {
//                                Toast.makeText(XiuGaiPsdActivity.this, "发送成功", Toast.LENGTH_SHORT).show();
//                            } else if (mZcYzmModel.getCode() == 2) {
//                                Toast.makeText(XiuGaiPsdActivity.this, "用户已经存在", Toast.LENGTH_SHORT).show();
//
//                            } else {
//
//                                Toast.makeText(XiuGaiPsdActivity.this, "发送失败", Toast.LENGTH_SHORT).show();
//                            }
//                        }
//                    });
//                } else {
//                    Log.d("没有", "数据");
//                }
//
//
//            }
//        });
//    }

    private void initZhuCe() {

        FormEncodingBuilder builder = new FormEncodingBuilder();
        builder.add("userId", String.valueOf(userId));
        builder.add("signpassword", psdFirEd.getText().toString());
        builder.add("newpassword", psdSecEd.getText().toString());
        Request request = new Request.Builder().url(Url.xiugaiPsdUrl).post(builder.build()).build();
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
                        mXiuGaiPsdModel = gson.fromJson(reader, XiuGaiPsdModel.class);
                        if (mXiuGaiPsdModel.getCode() == 1) {

                            mHandler.post(new Runnable() {
                                @Override
                                public void run() {
                                    if (mXiuGaiPsdModel.getMsg().equals("修改成功")) {
                                        Toast.makeText(XiuGaiPsdActivity.this, mXiuGaiPsdModel.getMsg()+"", Toast.LENGTH_SHORT).show();
                                        IntentUtil.showIntent(XiuGaiPsdActivity.this, NewLoginActivity.class);
                                    } else if (mXiuGaiPsdModel.getCode() == 2) {
                                        Toast.makeText(XiuGaiPsdActivity.this, mXiuGaiPsdModel.getMsg()+"", Toast.LENGTH_SHORT).show();
                                    } else if (mXiuGaiPsdModel.getCode() == 0) {
                                        Toast.makeText(XiuGaiPsdActivity.this, mXiuGaiPsdModel.getMsg()+"", Toast.LENGTH_SHORT).show();
                                    } else if (mXiuGaiPsdModel.getCode() == 3) {
                                        Toast.makeText(XiuGaiPsdActivity.this, mXiuGaiPsdModel.getMsg()+"", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                        } else if (mXiuGaiPsdModel.getCode() == 2) {
                            Looper.prepare();
                            Toast.makeText(XiuGaiPsdActivity.this, mXiuGaiPsdModel.getMsg() + "", Toast.LENGTH_SHORT).show();
                            Looper.loop();
                        } else {
                            Looper.prepare();
                            Toast.makeText(XiuGaiPsdActivity.this, mXiuGaiPsdModel.getMsg() + "", Toast.LENGTH_SHORT).show();
                            Looper.loop();
                        }


                    } else {
                        Looper.prepare();
                        Toast.makeText(XiuGaiPsdActivity.this, "服务器异常", Toast.LENGTH_SHORT).show(); //请求失败
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
