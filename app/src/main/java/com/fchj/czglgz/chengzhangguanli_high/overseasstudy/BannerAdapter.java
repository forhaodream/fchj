package com.fchj.czglgz.chengzhangguanli_high.overseasstudy;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.fchj.czglgz.chengzhangguanli_high.R;
import com.fchj.czglgz.chengzhangguanli_high.activity.KaActivity;
import com.fchj.czglgz.chengzhangguanli_high.model.BannerModel;
import com.fchj.czglgz.chengzhangguanli_high.util.Url;
import com.lh.ch.loopswitch.AutoLoopSwitchBaseAdapter;

import java.util.List;

public class BannerAdapter extends AutoLoopSwitchBaseAdapter {

    private Context mContext;

    private List<OsStudyDetailModel.DataBean.LausyBean> mDatas;


    public BannerAdapter(Context mContext, List<OsStudyDetailModel.DataBean.LausyBean> mDatas) {
        this.mContext = mContext;
        this.mDatas = mDatas;
    }

    @Override
    public int getDataCount() {
        return mDatas == null ? 0 : mDatas.size();
    }

    @Override
    public View getView(final int position) {
        ImageView imageView = new ImageView(mContext);
        imageView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT));

        OsStudyDetailModel.DataBean.LausyBean model = (OsStudyDetailModel.DataBean.LausyBean) getItem(position);
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);

        Glide.with(mContext).load(model.getAusimg()).placeholder(R.mipmap.zhongguo).error(R.mipmap.zhongguo).into(imageView);
//        imageView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if (position == 0) {
//                    Intent toKA = new Intent(mContext, KaActivity.class);
//                    mContext.startActivity(toKA);
//
//                } else {
//                    Intent toKA = new Intent(mContext, KaActivity.class);
//                    mContext.startActivity(toKA);
//
//                }
//            }
//        });
        // 引号里写 model.getPic()
        //   Glide.with(context).load(item.getImg()).into(hwImg);

        return imageView;
    }

    @Override
    public Object getItem(int position) {
        if (position >= 0 && position < getDataCount()) {

            return mDatas.get(position);
        }
        return null;
    }


    @Override
    public View getEmptyView() {
        return null;
    }

    @Override
    public void updateView(View view, int position) {

    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        super.destroyItem(container, position, object);
    }
}
