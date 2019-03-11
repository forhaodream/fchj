package com.fchj.czglgz.chengzhangguanli_high.activity;

import android.app.Activity;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
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
import android.widget.TextView;
import android.widget.Toast;

import com.fchj.czglgz.chengzhangguanli_high.R;
import com.fchj.czglgz.chengzhangguanli_high.model.ZcYzmModel;
import com.fchj.czglgz.chengzhangguanli_high.model.ZhuCeModel;
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

/**
 * Created by Administrator on 2018/5/16 0016.
 */

public class NewZhuCeActivity extends Activity implements View.OnClickListener {
    private AutoRelativeLayout mZhuCe;
    private Handler mHandler;
    private ZhuCeModel mZhuCeModel;
    private EditText phoneEd, psdFirEd, psdSecEd, yzmEd;
    private String pwd;
    private String again;
    private ImageView yzmImg;
    private TextView mYZmTv;
    private TimeCount time;
    private AutoRelativeLayout nowReg;
    private Button mYZMBtn;
    private ZcYzmModel mZcYzmModel;
    private ImageView returnImg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= 21) {
            View decorView = getWindow().getDecorView();
            decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }
        setContentView(R.layout.activity_new_zhuce);
        mHandler = new Handler();
        time = new TimeCount(60000, 1000);
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
        yzmEd = (EditText) findViewById(R.id.ed_yanzheng);
        yzmEd.setInputType(EditorInfo.TYPE_CLASS_PHONE);
        nowReg = (AutoRelativeLayout) findViewById(R.id.login_reg_button);
        nowReg.setOnClickListener(this);
        mYZMBtn = (Button) findViewById(R.id.zhuce_yzm_text);
        mYZMBtn.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.login_reg_button:
                if (!TextUtils.isEmpty(phoneEd.getText().toString())
                        && !TextUtils.isEmpty(psdFirEd.getText().toString())
                        && !TextUtils.isEmpty(psdSecEd.getText().toString())) {
                    initZhuCe();

                } else {
                    Toast.makeText(NewZhuCeActivity.this, "请认真输入信息", Toast.LENGTH_SHORT).show();
                }

                break;
            case R.id.zhuce_yzm_text:
                ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
                NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
                if (networkInfo == null || !networkInfo.isAvailable()) {
                    Toast.makeText(this, "无法连接到网络", Toast.LENGTH_SHORT).show();
                } else {
                    //当前有可用网络
                    if (isMobileNO(phoneEd.getText().toString())) {
                        sendYZM();
                        time.start();
                    } else {
                        Toast.makeText(NewZhuCeActivity.this, "请输入正确的手机号", Toast.LENGTH_SHORT).show();

                    }

                }

                break;

        }
    }

    /**
     * 判断字符串是否符合手机号码格式
     * 移动号段: 134,135,136,137,138,139,147,150,151,152,157,158,159,170,178,182,183,184,187,188
     * 联通号段: 130,131,132,145,155,156,170,171,175,176,185,186
     * 电信号段: 133,149,153,170,173,177,180,181,189
     *
     * @return 待检测的字符串
     */
    public static boolean isMobileNO(String mobileNums) {
        /**
         * 判断字符串是否符合手机号码格式
         * 移动号段: 134,135,136,137,138,139,147,150,151,152,157,158,159,170,178,182,183,184,187,188
         * 联通号段: 130,131,132,145,155,156,170,171,175,176,185,186
         * 电信号段: 133,149,153,170,173,177,180,181,189
         * @param str
         * @return 待检测的字符串
         */
        String telRegex = "^((13[0-9])|(14[5,7,9])|(15[^4])|(18[0-9])|(17[0,1,3,5,6,7,8]))\\d{8}$";// "[1]"代表第1位为数字1，"[358]"代表第二位可以为3、5、8中的一个，"\\d{9}"代表后面是可以是0～9的数字，有9位。
        if (TextUtils.isEmpty(mobileNums))
            return false;
        else
            return mobileNums.matches(telRegex);
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

                                    Toast.makeText(NewZhuCeActivity.this, "发送成功", Toast.LENGTH_SHORT).show();

                                }
                            });
                        } else if (mZcYzmModel.getCode() == 2) {
                            Looper.prepare();
                            Toast.makeText(NewZhuCeActivity.this, "用户已经存在", Toast.LENGTH_SHORT).show();
                            Looper.loop();
                        } else {
                            Looper.prepare();
                            Toast.makeText(NewZhuCeActivity.this, "发送失败", Toast.LENGTH_SHORT).show();
                            Looper.loop();
                        }
                    } else {
                        Looper.prepare();
                        Toast.makeText(NewZhuCeActivity.this, "服务器异常", Toast.LENGTH_SHORT).show(); //请求失败
                        Looper.loop();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        });
    }

    private void initZhuCe() {
//        if (!pwd.equals(again)) {
//            Toast.makeText(NewZhuCeActivity.this, "密码不一致", Toast.LENGTH_SHORT).show();
//        }
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
                        if (!TextUtils.isEmpty(body)) {
                            Gson gson = new Gson();
                            JsonReader reader = new JsonReader(new StringReader(body));
                            mZhuCeModel = gson.fromJson(reader, ZhuCeModel.class);
                            mHandler.post(new Runnable() {
                                @Override
                                public void run() {
                                    if (mZhuCeModel.getMsg().equals("注册成功")) {
                                        Toast.makeText(NewZhuCeActivity.this, "注册成功", Toast.LENGTH_SHORT).show();
                                        IntentUtil.showIntent(NewZhuCeActivity.this, NewLoginActivity.class);
                                    } else if (mZhuCeModel.getCode() == 2) {
                                        Toast.makeText(NewZhuCeActivity.this, "账号已存在", Toast.LENGTH_SHORT).show();

                                    } else {

                                        Toast.makeText(NewZhuCeActivity.this, "注册失败", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                        } else {
                            Log.d("没有", "数据");
                        }

                    } else {
                        Looper.prepare();
                        Toast.makeText(NewZhuCeActivity.this, "服务器异常", Toast.LENGTH_SHORT).show(); //请求失败
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
