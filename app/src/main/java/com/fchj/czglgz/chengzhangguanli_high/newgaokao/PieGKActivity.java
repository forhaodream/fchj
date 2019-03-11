package com.fchj.czglgz.chengzhangguanli_high.newgaokao;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.TextView;


import com.fchj.czglgz.chengzhangguanli_high.R;

public class PieGKActivity extends Activity {
//    private PercentPieView pie;

    private int sum = 0;
    private TextView sumTv;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pie_gk);
        addPie();
    }

    private void addPie() {
        int[] data = new int[]{721, 701, 586, 537, 421, 334, 187, 137, 74, 53, 22, 5};
        int[] color = new int[]{
                getResources().getColor(R.color.color_1),
                getResources().getColor(R.color.color_2),
                getResources().getColor(R.color.color_3),
                getResources().getColor(R.color.color_4),
                getResources().getColor(R.color.color_5),
                getResources().getColor(R.color.color_6),
                getResources().getColor(R.color.color_7),
                getResources().getColor(R.color.color_8),
                getResources().getColor(R.color.color_9),
                getResources().getColor(R.color.color_10),
                getResources().getColor(R.color.color_11),
                getResources().getColor(R.color.color_12)};
        String[] name = new String[]{"", "", "", "", "", "", "", "", "", "", "", ""};
//        pie = (PercentPieView) findViewById(R.id.pie);
//        pie.setData(data, name, color);
    }
}

