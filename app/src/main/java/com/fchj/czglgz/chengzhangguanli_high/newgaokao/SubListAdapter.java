package com.fchj.czglgz.chengzhangguanli_high.newgaokao;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.fchj.czglgz.chengzhangguanli_high.R;
import com.fchj.czglgz.chengzhangguanli_high.util.Url;
import com.fchj.czglgz.chengzhangguanli_high.view.GlideRoundTransform;
import com.zhy.autolayout.AutoRelativeLayout;

import java.util.List;

public class SubListAdapter extends BaseAdapter {
    private List<SubListModel.DataBean> mData;
    private List<Integer> colors;

    public SubListAdapter(List<SubListModel.DataBean> data, List<Integer> colors) {
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
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_sub_list, null);
            holder = new ViewHolder();
            holder.mItemSubListNum = (TextView) convertView.findViewById(R.id.item_sub_list_num);
            holder.mItemSubListRlc = (AutoRelativeLayout) convertView.findViewById(R.id.item_sub_list_rlc);
            holder.mItemSubListTitle = (TextView) convertView.findViewById(R.id.item_sub_list_title);
            holder.kindNum = (TextView) convertView.findViewById(R.id.item_sub_list_kindsnum);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.mItemSubListNum.setText(mData.get(position).getMkid() + "");
        holder.mItemSubListTitle.setText(mData.get(position).getKindname());
        holder.mItemSubListRlc.setBackgroundResource(colors.get(position));
        holder.kindNum.setText(mData.get(position).getKindsnum()+" 专业匹配");
        return convertView;
    }

    public static class ViewHolder {
        TextView mItemSubListNum, kindNum;
        AutoRelativeLayout mItemSubListRlc;
        TextView mItemSubListTitle;
    }


}