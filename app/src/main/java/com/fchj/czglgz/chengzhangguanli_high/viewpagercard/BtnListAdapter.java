package com.fchj.czglgz.chengzhangguanli_high.viewpagercard;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.fchj.czglgz.chengzhangguanli_high.R;
import com.fchj.czglgz.chengzhangguanli_high.adapter.PopupShengAdapter;

import java.util.List;

public class BtnListAdapter extends BaseAdapter {
    private List<String> mData;

    public BtnListAdapter(List<String> data) {
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
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_btn_list, null);
            holder = new ViewHolder();
            holder.mButton = (Button) convertView.findViewById(R.id.item_btn_list_button);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.mButton.setText(mData.get(position) + "");
        return convertView;
    }

    class ViewHolder {
        Button mButton;
//        TextView titleTv;
    }
}
