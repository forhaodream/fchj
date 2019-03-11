package com.fchj.czglgz.chengzhangguanli_high.major;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.fchj.czglgz.chengzhangguanli_high.R;

import java.util.List;

@SuppressLint("ValidFragment")
public class DetailFragment extends Fragment {
    private TextView titleTv, contentTv;

    private String contentStr;
    private String titleStr;
    public DetailFragment(String title, String include) {
        this.titleStr = title;
        this.contentStr = include;

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_detail, null);
        titleTv = (TextView) view.findViewById(R.id.fragment_voca_card_title);
        titleTv.setText(titleStr);
        contentTv = (TextView) view.findViewById(R.id.fragment_voca_card_include);
        contentTv.setText(contentStr);
        return view;
    }


}


