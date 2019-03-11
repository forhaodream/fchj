package com.fchj.czglgz.chengzhangguanli_high.overseasstudy;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.Nullable;
import android.support.v7.widget.CardView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.fchj.czglgz.chengzhangguanli_high.R;
import com.fchj.czglgz.chengzhangguanli_high.util.Url;
import com.fchj.czglgz.chengzhangguanli_high.view.CircleView;
import com.fchj.czglgz.chengzhangguanli_high.view.FoldTextView;
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

import static com.fchj.czglgz.chengzhangguanli_high.activity.NewZhuCeActivity.isMobileNO;

public class FreeAskActivity extends Activity implements View.OnClickListener {
    private ImageView mTitleFanhui;
    private TextView mTitleText;
    private CircleView mZjDetailHead;
    private TextView mZjDetailName;
    private TextView mZjDetailXuexiao;
    private TextView mZjDetailZhuanye;
    private FoldTextView mZjDetailContent;
    private TextView mTextView2;
    private EditText mFreeAskRealName;
    private EditText mFreeAskPhone;
    private EditText mFreeAskOldStudySchool;
    private EditText mFreeAskWantAddress;
    private EditText mFreeAskSchool;
    private CardView mCardView;
    private Button mStartAsk;
    private FreeAskModel mFreeAskModel;
    private FreeAskModel.DataBean mData;
    private Handler mHandler;
    private String countryStr = "";
    private FreeAskZjModel mFreeAskZjModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_free_ask);
        mHandler = new Handler();
        initView();
        Intent intent = getIntent();
        countryStr = intent.getStringExtra("countryStr");
        getZjInfo();
    }

    private void initView() {
        mTitleFanhui = (ImageView) findViewById(R.id.title_fanhui);
        mTitleFanhui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        mTitleText = (TextView) findViewById(R.id.title_text);
        mZjDetailHead = (CircleView) findViewById(R.id.zj_detail_head);
        mZjDetailName = (TextView) findViewById(R.id.zj_detail_name);
        mZjDetailXuexiao = (TextView) findViewById(R.id.zj_detail_xuexiao);
        mZjDetailZhuanye = (TextView) findViewById(R.id.zj_detail_zhuanye);
        mZjDetailContent = (FoldTextView) findViewById(R.id.zj_detail_content);
        mTextView2 = (TextView) findViewById(R.id.textView2);
        mFreeAskRealName = (EditText) findViewById(R.id.free_ask_real_name);
        mFreeAskPhone = (EditText) findViewById(R.id.free_ask_phone);
        mFreeAskPhone.setInputType(EditorInfo.TYPE_CLASS_PHONE);
        mFreeAskOldStudySchool = (EditText) findViewById(R.id.free_ask_old_study_school);
        mFreeAskWantAddress = (EditText) findViewById(R.id.free_ask_want_address);
        mFreeAskSchool = (EditText) findViewById(R.id.free_ask_school);
        mCardView = (CardView) findViewById(R.id.cardView);

        mStartAsk = (Button) findViewById(R.id.start_ask);
        mStartAsk.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.start_ask:
                if (isMobileNO(mFreeAskPhone.getText().toString()) && !TextUtils.isEmpty(mFreeAskRealName.getText().toString())) {
                    applyStudy();
                } else {
                    Toast.makeText(FreeAskActivity.this, "请输入正确的手机号", Toast.LENGTH_SHORT).show();

                }
                break;
        }
    }

    private void applyStudy() {

        FormEncodingBuilder builder = new FormEncodingBuilder();
        builder.add("applicant", mFreeAskRealName.getText().toString().trim());
        builder.add("applyTarget", mFreeAskWantAddress.getText().toString().trim());
        builder.add("applySchool", mFreeAskSchool.getText().toString().trim());
        builder.add("contactPhone", mFreeAskPhone.getText().toString().trim());
        builder.add("attendSchool", mFreeAskOldStudySchool.getText().toString().trim());
        builder.add("osetId", String.valueOf(mFreeAskZjModel.getData().get(0).getOsetId()));
        Request request = new Request.Builder().url(Url.applyForStudyUrl).post(builder.build()).build();
        OkHttpClient okHttpClient = new OkHttpClient();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {
                Looper.prepare();
                Toast.makeText(FreeAskActivity.this, "服务器异常", Toast.LENGTH_LONG).show();
                Looper.loop();

            }

            @Override
            public void onResponse(Response response) {
                try {
                    if (response.isSuccessful()) {
                        String body = response.body().string();
                        Log.d("home_zx", body);
                        Gson gson = new Gson();
                        JsonReader reader = new JsonReader(new StringReader(body));
                        mFreeAskModel = gson.fromJson(reader, FreeAskModel.class);
                        if (mFreeAskModel.getCode() == 1) {
                            finish();
                            Looper.prepare();
                            Toast.makeText(FreeAskActivity.this, mFreeAskModel.getMsg() + "", Toast.LENGTH_SHORT).show();
                            Looper.loop();
                        } else if (mFreeAskModel.getCode() == 2) {
                            Looper.prepare();
                            Toast.makeText(FreeAskActivity.this, mFreeAskModel.getMsg() + "", Toast.LENGTH_SHORT).show();
                            Looper.loop();
                        } else {
                            Looper.prepare();
                            Toast.makeText(FreeAskActivity.this, mFreeAskModel.getMsg() + "", Toast.LENGTH_SHORT).show();
                            Looper.loop();
                        }
                    } else {
                        Looper.prepare();
                        Toast.makeText(FreeAskActivity.this, "服务器异常", Toast.LENGTH_SHORT).show(); //请求失败
                        Looper.loop();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        });
    }

    private void getZjInfo() {
        FormEncodingBuilder builder = new FormEncodingBuilder();
        builder.add("country", countryStr);
        Request request = new Request.Builder().url(Url.getOsZjUrl).post(builder.build()).build();
        OkHttpClient okHttpClient = new OkHttpClient();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {
                Looper.prepare();
                Toast.makeText(FreeAskActivity.this, "服务器异常", Toast.LENGTH_LONG).show();
                Looper.loop();

            }

            @Override
            public void onResponse(Response response) {
                try {
                    if (response.isSuccessful()) {
                        String body = response.body().string();
                        Log.d("mFreeAskZjModel", body);
                        Gson gson = new Gson();
                        JsonReader reader = new JsonReader(new StringReader(body));
                        mFreeAskZjModel = gson.fromJson(reader, FreeAskZjModel.class);
                        if (mFreeAskZjModel.getCode() == 1) {
                            mHandler.post(new Runnable() {
                                @Override
                                public void run() {
                                    mZjDetailName.setText(mFreeAskZjModel.getData().get(0).getOs().getOsename());
                                    //mZjDetailContent.setText(mFreeAskZjModel.getData().get(0).getOs().getSummary());
                                 //   mZjDetailContent.setDesc(mFreeAskZjModel.getData().get(0).getOs().getSummary(), TextView.BufferType.NORMAL);
                                    mZjDetailContent.setText(mFreeAskZjModel.getData().get(0).getOs().getSummary());
                                    Glide.with(FreeAskActivity.this).load(mFreeAskZjModel.getData().get(0).getOs().getHeadimgurl())
                                            .placeholder(R.mipmap.teacher).error(R.mipmap.teacher).into(mZjDetailHead);

                                }
                            });
                        } else if (mFreeAskZjModel.getCode() == 2) {
                            Looper.prepare();
                            Toast.makeText(FreeAskActivity.this, mFreeAskZjModel.getMsg() + "", Toast.LENGTH_SHORT).show();
                            Looper.loop();
                        } else {
                            Looper.prepare();
                            Toast.makeText(FreeAskActivity.this, mFreeAskZjModel.getMsg() + "", Toast.LENGTH_SHORT).show();
                            Looper.loop();
                        }
                    } else {
                        Looper.prepare();
                        Toast.makeText(FreeAskActivity.this, "服务器异常", Toast.LENGTH_SHORT).show(); //请求失败
                        Looper.loop();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        });
    }
}
