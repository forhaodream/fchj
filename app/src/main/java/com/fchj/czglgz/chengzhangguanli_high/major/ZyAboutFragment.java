package com.fchj.czglgz.chengzhangguanli_high.major;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.fchj.czglgz.chengzhangguanli_high.R;
import com.fchj.czglgz.chengzhangguanli_high.vocation.VocationDetailActivity;
import com.nex3z.flowlayout.FlowLayout;
import com.zhy.autolayout.AutoLinearLayout;

import java.util.Arrays;
import java.util.List;


@SuppressLint("ValidFragment")
public class ZyAboutFragment extends Fragment {
    private TextView highTv, classesTv;
    private String contentStr;
    private String titleStr;
    private TextView mAboutHighSchoolTitle;
    private TextView mAboutClassesTitle;
    View view;
    private AutoLinearLayout mLinearLayout;

    public ZyAboutFragment(String highStr, String classesStr) {
        this.titleStr = highStr;
        this.contentStr = classesStr;

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_zy_about, null);
        initBtn();
        return view;
    }

    String str;
    String str2;
    List<String> list;

    private void initBtn() {
        if (contentStr.length() > 0) {
            FlowLayout flowLayout = (FlowLayout) view.findViewById(R.id.flow);
            str = contentStr;//根据逗号分隔到List数组中
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
