package com.fchj.czglgz.chengzhangguanli_high.newgaokao;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;

import com.fchj.czglgz.chengzhangguanli_high.R;
import com.fchj.czglgz.chengzhangguanli_high.view.AnimationPieView;

public class PieTeatActivity extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pie_test);
        int[] data = new int[]{721, 701, 586, 537, 421, 334, 187, 137, 74, 53, 22, 5};
        String[] name = new String[]{"", "", "", "", "", "", "", "", "", "", "", ""};


//        PercentPieView pieView = (PercentPieView) findViewById(R.id.pieView);
//        AnimationPercentPieView

//设置指定颜色
        int sum = 0;
//        pieView.setData(data, name, color);
//        for (int i = 0; i < data.length; ++i) {
//            sum += data[i];
//        }
//        Log.d("aaadaaada", String.valueOf(sum));
    }
}
