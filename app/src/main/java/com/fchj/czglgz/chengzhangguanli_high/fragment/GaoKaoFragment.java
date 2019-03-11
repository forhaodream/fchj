package com.fchj.czglgz.chengzhangguanli_high.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.fchj.czglgz.chengzhangguanli_high.R;

/**
 * Created by Administrator on 2018/4/11 0011.
 */

public class GaoKaoFragment extends Fragment {
    private View mView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_mine, null);
        return mView;
    }
}
