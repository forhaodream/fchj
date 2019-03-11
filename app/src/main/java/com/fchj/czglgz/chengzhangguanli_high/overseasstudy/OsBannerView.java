package com.fchj.czglgz.chengzhangguanli_high.overseasstudy;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

import com.fchj.czglgz.chengzhangguanli_high.model.BannerModel;
import com.lh.ch.loopswitch.AutoLoopSwitchBaseAdapter;
import com.lh.ch.loopswitch.AutoLoopSwitchBaseView;

public class OsBannerView extends AutoLoopSwitchBaseView {

    public OsBannerView(Context context) {
        super(context);
    }

    public OsBannerView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public OsBannerView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public OsBannerView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }


    @Override
    protected void onSwitch(int index, Object o) {
//        LoopModel model = (LoopModel) o;
        OsStudyDetailModel.DataBean.LausyBean model = (OsStudyDetailModel.DataBean.LausyBean) o;
        if (model != null) {
        }
    }

    @Override
    protected View getFailtView() {
        return null;
    }

    @Override
    protected long getDurtion() {
        return 3000;
    }

    @Override
    public void setAdapter(AutoLoopSwitchBaseAdapter adapter) {
        super.setAdapter(adapter);
        mHandler.sendEmptyMessage(LoopHandler.MSG_REGAIN);
    }
}

