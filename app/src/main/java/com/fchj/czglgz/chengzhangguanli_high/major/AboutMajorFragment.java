package com.fchj.czglgz.chengzhangguanli_high.major;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.fchj.czglgz.chengzhangguanli_high.R;
import com.fchj.czglgz.chengzhangguanli_high.vocation.VocationDetailActivity;
import com.nex3z.flowlayout.FlowLayout;
import com.zhy.autolayout.AutoLinearLayout;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SuppressLint("ValidFragment")
public class AboutMajorFragment extends Fragment {
    private String titleStr = "";
    private String includeStr = "";
    private int mPicid;
    private int typeNum;
    private View view;
    private AutoLinearLayout mLinearLayout;

    public AboutMajorFragment(String title, String include) {
        this.titleStr = title;
        this.includeStr = include;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_major_about, null);
//        initBtn();
        initChip();
        return view;
    }

    String str;
    String str2;
    List<String> list;


    private void initChip() {
        if (includeStr.length() > 0) {
            FlowLayout flowLayout = (FlowLayout) view.findViewById(R.id.flow);
            str = includeStr;//根据逗号分隔到List数组中
            str2 = str.replace(" ", "");//去掉所用空格
            list = Arrays.asList(str2.split(","));
            Log.d("llist", list.get(0));
            for (int i = 0; i < list.size(); i++) {
                TextView textView = buildLabel(list.get(i));
                flowLayout.addView(textView);
            }
        }
    }

    private TextView buildLabel(String text) {
        TextView textView = new TextView(getActivity());
        textView.setText(text);
        textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16);
        textView.setPadding((int) dpToPx(16), (int) dpToPx(8), (int) dpToPx(16), (int) dpToPx(8));
        textView.setBackgroundResource(R.drawable.label_bg);
        return textView;
    }

    private float dpToPx(float dp) {
        return TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP, dp, getResources().getDisplayMetrics());
    }
}
