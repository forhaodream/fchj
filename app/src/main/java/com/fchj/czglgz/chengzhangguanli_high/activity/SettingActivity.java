package com.fchj.czglgz.chengzhangguanli_high.activity;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.net.NetworkRequest;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.fchj.czglgz.chengzhangguanli_high.R;
import com.fchj.czglgz.chengzhangguanli_high.model.VersionModel;
import com.fchj.czglgz.chengzhangguanli_high.util.IntentUtil;
import com.fchj.czglgz.chengzhangguanli_high.util.Url;
import com.fchj.czglgz.chengzhangguanli_high.util.VersionTools;
import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import com.lh.ch.updateversion.UpdateHelper;
import com.lh.ch.updateversion.bean.UpdateEntity;
import com.lh.ch.updateversion.listener.UpdateListener;
import com.squareup.okhttp.Call;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;
import java.io.StringReader;

/**
 * Created by Administrator on 2018/4/18 0018.
 */

public class SettingActivity extends Activity implements View.OnClickListener {
    private RelativeLayout exitImg;
    private SharedPreferences readInfo;
    private SharedPreferences.Editor editor;
    private ImageView returnImg;
    private RelativeLayout aboutRl, updateRl, xiugaiRl;
    private String virCode;
    private VersionModel mVersionModel;
    private Handler mHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        mHandler = new Handler();
        returnImg = (ImageView) findViewById(R.id.title_fanhui);
        returnImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        readInfo = getSharedPreferences("user_npt", Context.MODE_PRIVATE);
        editor = readInfo.edit();
        exitImg = (RelativeLayout) findViewById(R.id.setting_exit);
        exitImg.setOnClickListener(this);
        aboutRl = (RelativeLayout) findViewById(R.id.personal_about_rl);
        aboutRl.setOnClickListener(this);
        updateRl = (RelativeLayout) findViewById(R.id.setting_update_rl);
        updateRl.setOnClickListener(this);
        xiugaiRl = (RelativeLayout) findViewById(R.id.personal_xiugai_rl);
        xiugaiRl.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.setting_exit:
                editor.clear();
                editor.apply();
                IntentUtil.showIntent(SettingActivity.this, NewLoginActivity.class);
                break;
            case R.id.personal_about_rl:
                IntentUtil.showIntent(SettingActivity.this, AboutOurActivity.class);
                break;
            case R.id.personal_xiugai_rl:
                IntentUtil.showIntent(SettingActivity.this, XiuGaiPsdActivity.class);
                break;
            case R.id.setting_update_rl:
                update();
//                Toast.makeText(this, "当前为最新版本", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    private void update() {
        OkHttpClient okHttpClient = new OkHttpClient();
        Request request = new Request.Builder().url(Url.updateUrl).build();
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
                        Log.d("update_body", body);
                        Gson gson = new Gson();
                        JsonReader reader = new JsonReader(new StringReader(body));
                        mVersionModel = gson.fromJson(reader, VersionModel.class);
                        if (mVersionModel.getCode() == 1) {
                            mHandler.post(virRunn);

                        } else if (mVersionModel.getCode() == 2) {
                            Looper.prepare();
                            Toast.makeText(SettingActivity.this, mVersionModel.getMsg() + "", Toast.LENGTH_SHORT).show();
                            Looper.loop();
                        } else {
                            Looper.prepare();
                            Toast.makeText(SettingActivity.this, mVersionModel.getMsg() + "", Toast.LENGTH_SHORT).show();
                            Looper.loop();
                        }


                    } else {
                        Looper.prepare();
                        Toast.makeText(SettingActivity.this, "服务器异常", Toast.LENGTH_SHORT).show(); //请求失败
                        Looper.loop();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

    }

    Runnable virRunn = new Runnable() {
        @Override
        public void run() {
            virCode = mVersionModel.getData().getAndroidversion();
            Log.d("111jk", virCode);
            Log.d("111xt", VersionTools.getVersion(SettingActivity.this));
            if (!VersionTools.getVersion(SettingActivity.this).equals(virCode)) {
                UpdateHelper
                        .getInstance()
                        .setCheckType(UpdateHelper.CheckType.check_with_Dialog)
                        .setUpdateListener(false, new UpdateListener() {
                            @Override
                            public void Update(boolean update, UpdateEntity updateEntity) {
                                if (update) {
                                    Toast.makeText(SettingActivity.this, "发现新版本", Toast.LENGTH_SHORT).show();
                                } else {
                                    Toast.makeText(SettingActivity.this, "未发现新版本", Toast.LENGTH_SHORT).show();
                                }
                            }
                        })
                        .check(SettingActivity.this);
            } else {
                Toast.makeText(SettingActivity.this, "当前最新版本", Toast.LENGTH_SHORT).show();
            }
        }
    };
}
