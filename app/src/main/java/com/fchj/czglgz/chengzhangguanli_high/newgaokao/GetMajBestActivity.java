package com.fchj.czglgz.chengzhangguanli_high.newgaokao;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.fchj.czglgz.chengzhangguanli_high.R;

import java.util.ArrayList;
import java.util.List;

public class GetMajBestActivity extends Activity {
//    PercentPieView pieView2;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_maj_best);
        List<Integer> dddd = new ArrayList<>();
        dddd.add(10);
        dddd.add(10);
        dddd.add(10);
        dddd.add(40);
        List<String> aaaa = new ArrayList<>();
        aaaa.add("兄弟");
        aaaa.add("兄弟");
        aaaa.add("兄弟");
        aaaa.add("兄弟");

        int[] color = new int[]{
                getResources().getColor(R.color.color_11),
                getResources().getColor(R.color.red),
                getResources().getColor(R.color.color_1),
                getResources().getColor(R.color.color_2)};
//        pieView2 = (PercentPieView) findViewById(R.id.pieView2);
//使用随机颜色
//        pieView2.setData(dddd, aaaa, color);
    }
}
