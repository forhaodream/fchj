package com.fchj.czglgz.chengzhangguanli_high.major;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.fchj.czglgz.chengzhangguanli_high.R;
import com.fchj.czglgz.chengzhangguanli_high.adapter.PopupShengAdapter;
import com.fchj.czglgz.chengzhangguanli_high.volunteer.CollegeDetailAdapter;
import com.fchj.czglgz.chengzhangguanli_high.volunteer.CollegeListModel;

import java.util.List;

public class ThirdListAdapter extends BaseAdapter {
    private List<String> mData;

    public ThirdListAdapter(List<String> data) {
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
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_third_list, null);
            holder = new ViewHolder();
            holder.titleTv = (TextView) convertView.findViewById(R.id.item_third_list_title);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.titleTv.setText(mData.get(position) + "");
        return convertView;
    }

    class ViewHolder {
        TextView titleTv;
    }
}