package com.fchj.czglgz.chengzhangguanli_high.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

import com.fchj.czglgz.chengzhangguanli_high.model.BannerModel;
import com.lh.ch.loopswitch.AutoLoopSwitchBaseAdapter;
import com.lh.ch.loopswitch.AutoLoopSwitchBaseView;


/**
 * Created by Administrator on 2018/4/9 0009.
 */

public class AutoSwitchView extends AutoLoopSwitchBaseView {

    public AutoSwitchView(Context context) {
        super(context);
    }

    public AutoSwitchView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public AutoSwitchView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public AutoSwitchView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }


    @Override
    protected void onSwitch(int index, Object o) {
//        LoopModel model = (LoopModel) o;
        BannerModel.DataBean model = (BannerModel.DataBean) o;
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
