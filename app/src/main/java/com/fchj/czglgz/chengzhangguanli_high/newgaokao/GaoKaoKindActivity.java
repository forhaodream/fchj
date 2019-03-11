package com.fchj.czglgz.chengzhangguanli_high.newgaokao;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;

import com.fchj.czglgz.chengzhangguanli_high.R;
import com.zhy.autolayout.AutoRelativeLayout;

public class GaoKaoKindActivity extends Activity implements View.OnClickListener {
    private ImageView returnImg;
    private AutoRelativeLayout sGMRl;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gaokao_kind);
        returnImg = (ImageView) findViewById(R.id.title_fanhui);
        returnImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        sGMRl = (AutoRelativeLayout) findViewById(R.id.subject_get_major);
        sGMRl.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.subject_get_major:
                Intent intent = new Intent(GaoKaoKindActivity.this, ChooseSubjectActivity.class);

                startActivity(intent);
                break;
        }
    }
}
