package com.fchj.czglgz.chengzhangguanli_high.newgaokao;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.fchj.czglgz.chengzhangguanli_high.R;
import com.zhy.autolayout.AutoRelativeLayout;

import java.util.List;

public class BestListAdapter extends BaseAdapter {
    private List<BestMajModel.DataBean> mData;
    private List<Integer> colors;

    public BestListAdapter(List<BestMajModel.DataBean> data, List<Integer> colors) {
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
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_best_list, null);
            holder = new ViewHolder();
            holder.mItemBestListTitle = (TextView) convertView.findViewById(R.id.item_best_list_title);
            holder.mItemBestTv1 = (TextView) convertView.findViewById(R.id.item_best_tv_1);
            holder.mItemBestTv2 = (TextView) convertView.findViewById(R.id.item_best_tv_2);
            holder.mItemBestTv3 = (TextView) convertView.findViewById(R.id.item_best_tv_3);
            holder.mView = convertView.findViewById(R.id.item_best_list_view);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.mItemBestListTitle.setText(mData.get(position).getInterestmajor() + "");
        holder.mItemBestTv1.setText(mData.get(position).getMajors().getMajorgenre());
        holder.mItemBestTv2.setText(mData.get(position).getMajors().getCoursekind());
        holder.mItemBestTv3.setText(mData.get(position).getMajors().getCoursetype());
        holder.mView.setBackgroundResource(colors.get(position));
        return convertView;
    }

    public static class ViewHolder {
        TextView mItemBestListTitle;
        TextView mItemBestTv1;
        TextView mItemBestTv2;
        TextView mItemBestTv3;
        View mView;
    }
}