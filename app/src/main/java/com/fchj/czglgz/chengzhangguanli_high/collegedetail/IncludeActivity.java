package com.fchj.czglgz.chengzhangguanli_high.collegedetail;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.fchj.czglgz.chengzhangguanli_high.R;

public class IncludeActivity extends Activity {
    private ImageView returnImg;
    private TextView includeTv;
    private String mString = "";
    private String headUrl = "";
    private String nameStr = "";
    private String xzStr = "";
    private ImageView mCollegeDetailHead;
    private TextView mCollegeDetailName;
    private TextView mCollegeDetailXz;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_include);
        returnImg = (ImageView) findViewById(R.id.title_fanhui);
        returnImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        includeTv = (TextView) findViewById(R.id.include_tv);
        Intent intent = getIntent();
        mString = intent.getStringExtra("include");
        headUrl = intent.getStringExtra("head_url");
        nameStr = intent.getStringExtra("cname");
        xzStr = intent.getStringExtra("cXZ");
        includeTv.setText(mString);
        Log.d("12123", headUrl);
        Log.d("12123", nameStr);
        mCollegeDetailHead = (ImageView) findViewById(R.id.college_detail_head);
        mCollegeDetailName = (TextView) findViewById(R.id.college_detail_name);
        mCollegeDetailXz = (TextView) findViewById(R.id.college_detail_xz);
        Glide.with(getApplication()).load(headUrl).placeholder(R.mipmap.fchj_e).error(R.mipmap.fchj_e).into(mCollegeDetailHead);
        mCollegeDetailName.setText(nameStr + "");
        mCollegeDetailXz.setText("学校性质: " + xzStr);
    }


}
