package com.fchj.czglgz.chengzhangguanli_high.overseasstudy;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.fchj.czglgz.chengzhangguanli_high.R;

import java.util.List;

public class SearchByNameAdapter extends BaseAdapter {
    private List<SearchByNameModel.DataBean> mData;

    public SearchByNameAdapter(List<SearchByNameModel.DataBean> data) {
        this.mData = data;
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public Object getItem(int position) {
        return mData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_popup_sheng, null);
            holder = new ViewHolder();
            holder.titleTv = (TextView) convertView.findViewById(R.id.popup_sheng_tv);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.titleTv.setText(mData.get(position).getSchoolName() + "");
        return convertView;
    }

    class ViewHolder {
        TextView titleTv;
    }
}