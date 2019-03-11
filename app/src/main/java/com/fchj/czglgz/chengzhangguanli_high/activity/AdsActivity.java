package com.fchj.czglgz.chengzhangguanli_high.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;

import com.fchj.czglgz.chengzhangguanli_high.expandlistview.MbsTestActivity;
import com.fchj.czglgz.chengzhangguanli_high.newgaokao.ChooseSubjectActivity;
import com.fchj.czglgz.chengzhangguanli_high.newgaokao.ExTestActivity;
import com.fchj.czglgz.chengzhangguanli_high.newgaokao.GaoKaoKindActivity;
import com.fchj.czglgz.chengzhangguanli_high.newgaokao.MajorBySubActivity;
import com.fchj.czglgz.chengzhangguanli_high.newgaokao.MyMajScByGkActivity;
import com.fchj.czglgz.chengzhangguanli_high.newgaokao.PieGKActivity;
import com.fchj.czglgz.chengzhangguanli_high.test.EListActivity;
import com.fchj.czglgz.chengzhangguanli_high.test.ExPandListActivity;

/**
 * Created by Administrator on 2018/4/17 0017.
 */

public class AdsActivity extends AppCompatActivity {
    private SharedPreferences usernameSp;
    private String username;
    private int userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        usernameSp = getSharedPreferences("user_npt", MODE_PRIVATE);
        username = usernameSp.getString("userName", "");
        if (!TextUtils.isEmpty(username)) {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent intent = new Intent(AdsActivity.this, HomeActivity.class);
                    startActivity(intent);
                    finish();
                }
            }, 2000);
        } else {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent intent = new Intent(AdsActivity.this, HomeActivity.class);
                    startActivity(intent);
                    finish();
                }
            }, 2000);
        }


    }
}
