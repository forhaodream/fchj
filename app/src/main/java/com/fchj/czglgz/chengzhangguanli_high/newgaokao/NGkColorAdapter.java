package com.fchj.czglgz.chengzhangguanli_high.newgaokao;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.fchj.czglgz.chengzhangguanli_high.R;

import java.util.List;

public class NGkColorAdapter extends BaseAdapter {
    private List<NGkMajDetailModel.DataBean> mData;
    private List<Integer> colors;

    public NGkColorAdapter(List<NGkMajDetailModel.DataBean> data, List<Integer> colors) {
        this.mData = data;
        this.colors = colors;
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
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_color_list, null);
            holder = new ViewHolder();

            holder.mItemColorTitle = (TextView) convertView.findViewById(R.id.item_color_title);
            holder.mItemColorTitlePercent = (TextView) convertView.findViewById(R.id.item_color_title_percent);
            holder.mItemColorTitleMaj = (TextView) convertView.findViewById(R.id.item_color_title_maj);
//            holder.mItemColorTitleStudent = (TextView) convertView.findViewById(R.id.item_color_title_student);
//            holder.upRlc = (RelativeLayout) convertView.findViewById(R.id.item_color_list_up_rlc);
//            holder.downRlc = (RelativeLayout) convertView.findViewById(R.id.item_color_list_down_rlc);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.mItemColorTitle.setText(mData.get(position).getSubjectname());
        holder.mItemColorTitlePercent.setText(mData.get(position).getSubjpercent() + "%");
        holder.mItemColorTitleMaj.setText(mData.get(position).getSubjnum() + "个专业");



        return convertView;
    }


    public static class ViewHolder {
        TextView mItemColorTitle;
        TextView mItemColorTitlePercent;
        TextView mItemColorTitleMaj;
        TextView mItemColorTitleStudent;
        RelativeLayout upRlc, downRlc;
    }


}