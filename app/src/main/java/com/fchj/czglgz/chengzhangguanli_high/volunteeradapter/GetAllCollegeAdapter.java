package com.fchj.czglgz.chengzhangguanli_high.volunteeradapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.fchj.czglgz.chengzhangguanli_high.R;
import com.fchj.czglgz.chengzhangguanli_high.adapter.AllCollegeAdapter;
import com.fchj.czglgz.chengzhangguanli_high.volunteer.AllCollegeModel;
import com.fchj.czglgz.chengzhangguanli_high.volunteer.SearchCollegeModel;
import com.zhy.autolayout.utils.AutoUtils;

import java.util.List;

public class GetAllCollegeAdapter extends BaseAdapter {
    private List<SearchCollegeModel.DataBean> mData;

    public GetAllCollegeAdapter(List<SearchCollegeModel.DataBean> data) {
        this.mData = data;
    }

    @Override
    public int getCount() {
        return mData.size();
//        return 20;
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
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_drop_college, null);
            holder = new ViewHolder();
            holder.mHead = (ImageView) convertView.findViewById(R.id.item_all_college_head);
            holder.mName = (TextView) convertView.findViewById(R.id.item_all_college_name);
            holder.mEngName = (TextView) convertView.findViewById(R.id.item_all_college_eng_name);
            holder.mAddress = (TextView) convertView.findViewById(R.id.item_all_college_address);
            holder.mNum = (TextView) convertView.findViewById(R.id.item_all_college_num);
            convertView.setTag(holder);
            AutoUtils.autoSize(convertView);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.mName.setText(mData.get(position).getUniversityname() + "");
        holder.mEngName.setText(mData.get(position).getUniversityenname() + "");
        holder.mAddress.setText(mData.get(position).getUniversityaddress() + "");
        holder.mNum.setText(mData.get(position).getUniversitycreatime() + "");
        Glide.with(holder.mHead.getContext())
                .load(mData.get(position).getLogofilename())
                .placeholder(R.mipmap.fchj_e).error(R.mipmap.fchj_e).into(holder.mHead);
        return convertView;
    }


    class ViewHolder {
        ImageView mHead;
        TextView mName;
        TextView mEngName;
        TextView mAddress;
        TextView mNum;


    }
}