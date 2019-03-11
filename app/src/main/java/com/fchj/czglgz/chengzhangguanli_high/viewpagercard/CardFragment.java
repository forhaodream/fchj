package com.fchj.czglgz.chengzhangguanli_high.viewpagercard;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import android.support.v7.widget.CardView;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.fchj.czglgz.chengzhangguanli_high.R;
import com.fchj.czglgz.chengzhangguanli_high.major.MajorDetailActivity;
import com.fchj.czglgz.chengzhangguanli_high.view.CornersTransform;
import com.nex3z.flowlayout.FlowLayout;
import com.zhy.autolayout.AutoLinearLayout;

import java.util.Arrays;
import java.util.List;

import static com.baidu.location.g.j.p;

@SuppressLint("ValidFragment")
public class CardFragment extends Fragment {
    private TextView mFragmentVocaCardTitle;
    private ImageView mFragmentVocaCardPic;
    private TextView mFragmentVocaCardInclude;


    private CardView mCardView;
    private String titleStr = "";
    private String includeStr = "";
    private int mPicid;
    private int typeNum;
    private View view;
    private AutoLinearLayout mLinearLayout;
    String str;
    String str2;
    List<String> list;

    public CardFragment(String title, String include, int picId, int type) {
        this.titleStr = title;
        this.includeStr = include;
        this.mPicid = picId;
        this.typeNum = type;
    }

    private int aaa;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // 从上一界面传个参数进来,根据参数判断走哪个布局

        if (typeNum == 1) {
            view = inflater.inflate(R.layout.fragment_adapter, container, false);
            mCardView = (CardView) view.findViewById(R.id.cardView);
            mCardView.setMaxCardElevation(mCardView.getCardElevation() * CardAdapter.MAX_ELEVATION_FACTOR);
            initView(view);
        } else if (typeNum == 5) {
            view = inflater.inflate(R.layout.fragment_card_list_btn, container, false);
            mCardView = (CardView) view.findViewById(R.id.cardView);
            mCardView.setMaxCardElevation(mCardView.getCardElevation() * CardAdapter.MAX_ELEVATION_FACTOR);
            initBtnView(view);
        }

        return view;
    }

    public CardView getCardView() {
        return mCardView;
    }

    private void initView(View view) {
        mFragmentVocaCardTitle = (TextView) view.findViewById(R.id.fragment_voca_card_title);
        mFragmentVocaCardPic = (ImageView) view.findViewById(R.id.fragment_voca_card_pic);
        mFragmentVocaCardInclude = (TextView) view.findViewById(R.id.fragment_voca_card_include);
        mFragmentVocaCardTitle.setText(titleStr);
        mFragmentVocaCardInclude.setText(includeStr);
        Glide.with(getContext())
                .load(mPicid)
                .placeholder(R.mipmap.fchj_e).transform(new CornersTransform(getContext(), 30)).into(mFragmentVocaCardPic);
    }

    @SuppressLint("ResourceAsColor")
    private void initBtnView(View view) {
        mFragmentVocaCardTitle = (TextView) view.findViewById(R.id.fragment_voca_card_title);
        mFragmentVocaCardPic = (ImageView) view.findViewById(R.id.fragment_voca_card_pic);
        mFragmentVocaCardInclude = (TextView) view.findViewById(R.id.fragment_voca_card_include);
        mFragmentVocaCardTitle.setText(titleStr);
        Glide.with(getContext())
                .load(mPicid)
                .placeholder(R.mipmap.fchj_e).transform(new CornersTransform(getContext(), 30)).into(mFragmentVocaCardPic);

        str = includeStr;//根据逗号分隔到List数组中
        str2 = str.replace(" ", "");//去掉所用空格
        list = Arrays.asList(str2.split(","));
        Log.d("llist", list.get(0));
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
        textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14);
        textView.setPadding((int) dpToPx(16), (int) dpToPx(8), (int) dpToPx(16), (int) dpToPx(8));
        textView.setBackgroundResource(R.drawable.label_bg);
        return textView;
    }

    private float dpToPx(float dp) {
        return TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP, dp, getResources().getDisplayMetrics());
    }
}



