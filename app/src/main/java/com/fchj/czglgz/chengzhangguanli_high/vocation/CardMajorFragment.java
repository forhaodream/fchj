package com.fchj.czglgz.chengzhangguanli_high.vocation;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.fchj.czglgz.chengzhangguanli_high.R;
import com.fchj.czglgz.chengzhangguanli_high.view.CornersTransform;
import com.fchj.czglgz.chengzhangguanli_high.viewpagercard.CardAdapter;

@SuppressLint("ValidFragment")
public class CardMajorFragment extends Fragment {
    private TextView mFragmentVocaCardTitle;
    private ImageView mFragmentVocaCardPic;
    private TextView mFragmentVocaCardInclude;


    private CardView mCardView;
    private String titleStr = "";
    private String includeStr = "";
    private int mPicid;

    public CardMajorFragment(String title, String include, int picId) {
        this.titleStr = title;
        this.includeStr = include;
        this.mPicid = picId;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_adapter, container, false);
        mCardView = (CardView) view.findViewById(R.id.cardView);
        mCardView.setMaxCardElevation(mCardView.getCardElevation() * CardAdapter.MAX_ELEVATION_FACTOR);
        initView(view);

      //  addButton();
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
                .placeholder(R.mipmap.fchj_e).transform(new CornersTransform(getContext(), 20)).into(mFragmentVocaCardPic);
//        mFragmentVocaCardPic.setImageResource(mPicid);
    }

    private void addButton(int btnNum) {
        //获取屏幕大小，以合理设定 按钮 大小及位置

        DisplayMetrics dm = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(dm);
        int width = dm.widthPixels;
        int height = dm.heightPixels;

        //自定义layout组件
        RelativeLayout layout = new RelativeLayout(getActivity());

        //这里创建16个按钮，每行放置4个按钮

        Button Btn[] = new Button[btnNum];
        int j = -1;
        for (int i = 0; i < btnNum; i++) {
            Btn[i] = new Button(getActivity());
            Btn[i].setId(2000 + i);
            Btn[i].setText("按钮" + i);
            RelativeLayout.LayoutParams btParams = new RelativeLayout.LayoutParams((width - 50) / 4, 40);  //设置按钮的宽度和高度
            if (i % 2 == 0) {
                j++;
            }
            btParams.leftMargin = 10 + ((width - 50) / 2 + 10) * (i % 2);   //横坐标定位
            btParams.topMargin = 20 + 55 * j;   //纵坐标定位
            layout.addView(Btn[i], btParams);   //将按钮放入layout组件
        }
        getActivity().setContentView(layout);
        //批量设置监听

        for (int k = 0; k <= Btn.length - 1; k++) {
            //这里不需要findId，因为创建的时候已经确定哪个按钮对应哪个Id
            Btn[k].setTag(k);                //为按钮设置一个标记，来确认是按下了哪一个按钮

            Btn[k].setOnClickListener(new Button.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int i = (Integer) v.getTag();   //这里的i不能在外部定义，因为内部类的关系，内部类好多繁琐的东西，要好好研究一番
                    Toast.makeText(getActivity(), "" + i, Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent();

//                    intent.setClass(getActivity(), Work_02.class);
//                    Bundle bundle = new Bundle();
//                    bundle.putInt("count", i);
//                    intent.putExtras(bundle);
                    startActivity(intent);

                }
            });
        }
    }

}
