package com.fchj.czglgz.chengzhangguanli_high.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.fchj.czglgz.chengzhangguanli_high.R;
import com.fchj.czglgz.chengzhangguanli_high.activity.KaActivity;
import com.fchj.czglgz.chengzhangguanli_high.util.IntentUtil;
import com.fchj.czglgz.chengzhangguanli_high.volunteer.SearchCollegeActivity;
import com.fchj.czglgz.chengzhangguanli_high.volunteer.SearchMajorActivity;
import com.fchj.czglgz.chengzhangguanli_high.volunteer.SearchNumberActivity;

public class VolunteerFragment extends Fragment implements View.OnClickListener {
    private View view;
    private TextView titleText;
    private ImageView homeToKa;
    private RelativeLayout fuwuDetailTitle;
    private CardView cdVolunteerCollege;
    private CardView cdVolunteerNumber;
    private CardView cdVolunteerMajor;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_volunteer, null);

        initView(view);
        return view;
    }

    private void initView(View view) {
        titleText = (TextView) view.findViewById(R.id.title_text);
        homeToKa = (ImageView) view.findViewById(R.id.home_to_ka);
        homeToKa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                IntentUtil.showIntent(getActivity(), KaActivity.class);
            }
        });
        fuwuDetailTitle = (RelativeLayout) view.findViewById(R.id.fuwu_detail_title);
        cdVolunteerCollege = (CardView) view.findViewById(R.id.cd_volunteer_college);
        cdVolunteerNumber = (CardView) view.findViewById(R.id.cd_volunteer_number);
        cdVolunteerMajor = (CardView) view.findViewById(R.id.cd_volunteer_major);
        cdVolunteerNumber.setOnClickListener(this);
        cdVolunteerCollege.setOnClickListener(this);
        cdVolunteerMajor.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.cd_volunteer_college:
                IntentUtil.showIntent(getActivity(), SearchCollegeActivity.class);
                break;
            case R.id.cd_volunteer_number:
                IntentUtil.showIntent(getActivity(), SearchNumberActivity.class);
                break;
            case R.id.cd_volunteer_major:
                IntentUtil.showIntent(getActivity(), SearchMajorActivity.class);
                break;
        }
    }
}
