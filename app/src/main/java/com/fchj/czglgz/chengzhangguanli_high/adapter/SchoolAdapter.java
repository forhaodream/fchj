package com.fchj.czglgz.chengzhangguanli_high.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.fchj.czglgz.chengzhangguanli_high.R;
import com.fchj.czglgz.chengzhangguanli_high.model.SchoolModel;
import com.fchj.czglgz.chengzhangguanli_high.model.ShiModel;

import java.util.List;

/**
 * Created by Administrator on 2018/4/20 0020.
 */

public class SchoolAdapter extends BaseAdapter {
    private List<SchoolModel.DataBean> mData;

    public SchoolAdapter(List<SchoolModel.DataBean> data) {
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
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_spinner, null);
            holder = new ViewHolder();
            holder.titleTv = (TextView) convertView.findViewById(R.id.item_spinner_text);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.titleTv.setText(mData.get(position).getSchoolname());
        return convertView;
    }

    class ViewHolder {
        TextView titleTv;
    }
}
