package com.fchj.czglgz.chengzhangguanli_high.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.fchj.czglgz.chengzhangguanli_high.R;
import com.fchj.czglgz.chengzhangguanli_high.util.IntentUtil;

/**
 * Created by Administrator on 2018/4/25 0025.
 */

public class AboutOurActivity extends Activity {
    private ImageView returnImg;
    private TextView webTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        webTv = (TextView) findViewById(R.id.about_web);
        webTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent toWeb = new Intent(AboutOurActivity.this, AboutWebActivity.class);
                toWeb.putExtra("urls", "http://www.fchj.net/");
                toWeb.putExtra("title", "公司官网");
                startActivity(toWeb);
            }
        });
        returnImg = (ImageView) findViewById(R.id.title_fanhui);
        returnImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
