package com.fchj.czglgz.chengzhangguanli_high.adapter;

import android.util.Log;
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
import com.fchj.czglgz.chengzhangguanli_high.volunteer.AllCollegeModel;

import java.util.List;

public class AllCollegeAdapter extends BaseAdapter {
    private List<AllCollegeModel.DataBean> mData;

    public AllCollegeAdapter(List<AllCollegeModel.DataBean> data) {
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
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_all_college, null);
            holder = new ViewHolder();
            holder.mHead = (ImageView) convertView.findViewById(R.id.item_all_college_head);
            holder.mName = (TextView) convertView.findViewById(R.id.item_all_college_name);
            holder.mEngName = (TextView) convertView.findViewById(R.id.item_all_college_eng_name);
            holder.mAddress = (TextView) convertView.findViewById(R.id.item_all_college_address);
            holder.mNum = (TextView) convertView.findViewById(R.id.item_all_college_num);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
//        if (mData.get(position).getUnty().getUniversityname() != null) {
        holder.mName.setText(mData.get(position).getUnty().getUniversityname() + "");
        holder.mEngName.setText(mData.get(position).getUnty().getUniversityenname() + "");
        holder.mAddress.setText(mData.get(position).getUnty().getUniversityaddress() + "");
        holder.mNum.setText(mData.get(position).getUnty().getUniversitycreatime() + "");
        Glide.with(holder.mHead.getContext())
                .load(mData.get(position).getUnty().getLogofilename())
                .placeholder(R.mipmap.fchj_e).error(R.mipmap.fchj_e).into(holder.mHead);
        Log.d("position", position + "");
//        } else {
//            holder.mName.setText("");
//            holder.mEngName.setText("");
//            holder.mAddress.setText("");
//            holder.mNum.setText("");
//            Glide.with(holder.mHead.getContext())
//                    .load(mData.get(position).getUnty().getLogofilename())
//                    .placeholder(R.mipmap.fchj_e).error(R.mipmap.fchj_e).into(holder.mHead);
//        }

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