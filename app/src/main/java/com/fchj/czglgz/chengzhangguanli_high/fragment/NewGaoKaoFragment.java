package com.fchj.czglgz.chengzhangguanli_high.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.fchj.czglgz.chengzhangguanli_high.R;
import com.fchj.czglgz.chengzhangguanli_high.newgaokao.GaoKaoKindActivity;
import com.zhy.autolayout.AutoRelativeLayout;

public class NewGaoKaoFragment extends Fragment implements View.OnClickListener {
    private View mView;
    private AutoRelativeLayout textRl;
    private Button zhejiangBtn;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_new_gaokao, null);
        zhejiangBtn = (Button) mView.findViewById(R.id.btn_zhejiang);
        zhejiangBtn.setOnClickListener(this);
        return mView;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_zhejiang:
                Intent toZj = new Intent(getActivity(), GaoKaoKindActivity.class);
                toZj.putExtra("shengstr", "浙江");
                startActivity(toZj);
                break;
        }
    }
}
