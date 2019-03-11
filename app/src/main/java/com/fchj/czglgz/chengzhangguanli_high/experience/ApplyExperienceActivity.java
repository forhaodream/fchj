package com.fchj.czglgz.chengzhangguanli_high.experience;

import android.app.Activity;
import android.os.Bundle;
import android.os.Looper;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;

import com.fchj.czglgz.chengzhangguanli_high.R;
import com.fchj.czglgz.chengzhangguanli_high.overseasstudy.FreeAskActivity;
import com.fchj.czglgz.chengzhangguanli_high.overseasstudy.FreeAskModel;
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

public class ApplyExperienceActivity extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_study_experience);
    }
//    private void applyStudy() {
//
//        FormEncodingBuilder builder = new FormEncodingBuilder();
//        builder.add("applicant", mFreeAskRealName.getText().toString().trim());
//        builder.add("applyTarget", mFreeAskWantAddress.getText().toString().trim());
//        builder.add("applySchool", mFreeAskSchool.getText().toString().trim());
//        builder.add("contactPhone", mFreeAskPhone.getText().toString().trim());
//        builder.add("attendSchool", mFreeAskOldStudySchool.getText().toString().trim());
//        builder.add("osetId", "专家id");
//        Request request = new Request.Builder().url(Url.applyForStudyUrl).post(builder.build()).build();
//        OkHttpClient okHttpClient = new OkHttpClient();
//        Call call = okHttpClient.newCall(request);
//        call.enqueue(new Callback() {
//            @Override
//            public void onFailure(Request request, IOException e) {
//                Looper.prepare();
//                Toast.makeText(ApplyExperienceActivity.this, "服务器异常", Toast.LENGTH_LONG).show();
//                Looper.loop();
//
//            }
//
//            @Override
//            public void onResponse(Response response) {
//                try {
//                    if (response.isSuccessful()) {
//                        String body = response.body().string();
//                        Log.d("home_zx", body);
//                        Gson gson = new Gson();
//                        JsonReader reader = new JsonReader(new StringReader(body));
//                        mFreeAskModel = gson.fromJson(reader, FreeAskModel.class);
//                        if (mFreeAskModel.getCode() == 1) {
//                            Looper.prepare();
//                            Toast.makeText(ApplyExperienceActivity.this, mFreeAskModel.getMsg() + "", Toast.LENGTH_SHORT).show();
//                            Looper.loop();
//                        } else if (mFreeAskModel.getCode() == 2) {
//                            Looper.prepare();
//                            Toast.makeText(ApplyExperienceActivity.this, mFreeAskModel.getMsg() + "", Toast.LENGTH_SHORT).show();
//                            Looper.loop();
//                        } else {
//                            Looper.prepare();
//                            Toast.makeText(ApplyExperienceActivity.this, mFreeAskModel.getMsg() + "", Toast.LENGTH_SHORT).show();
//                            Looper.loop();
//                        }
//                    } else {
//                        Looper.prepare();
//                        Toast.makeText(ApplyExperienceActivity.this, "服务器异常", Toast.LENGTH_SHORT).show(); //请求失败
//                        Looper.loop();
//                    }
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//
//            }
//        });
//    }
}
