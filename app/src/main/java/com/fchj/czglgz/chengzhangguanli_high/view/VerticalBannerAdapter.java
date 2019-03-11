package com.fchj.czglgz.chengzhangguanli_high.view;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.fchj.czglgz.chengzhangguanli_high.R;

import java.util.List;

public class VerticalBannerAdapter extends BaseBannerAdapter<VerticalModel> {
    private List<VerticalModel> mDatas;

    public VerticalBannerAdapter(List<VerticalModel> datas) {
        super(datas);
    }

    @Override
    public View getView(VerticalBannerView parent) {
        return LayoutInflater.from(parent.getContext()).inflate(R.layout.item_vertical, null);
    }

    @Override
    public void setItem(final View view, final VerticalModel data) {
        TextView tv = (TextView) view.findViewById(R.id.title);
        tv.setText(data.title);

        TextView tag = (TextView) view.findViewById(R.id.tag);
        tag.setText(data.url);

    }
}
