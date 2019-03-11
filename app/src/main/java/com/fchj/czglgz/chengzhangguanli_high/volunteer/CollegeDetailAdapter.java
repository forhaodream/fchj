package com.fchj.czglgz.chengzhangguanli_high.volunteer;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.fchj.czglgz.chengzhangguanli_high.R;

import java.util.List;

public class CollegeDetailAdapter extends BaseAdapter {
    private List<CollegeListModel.DataBean> mData;

    public CollegeDetailAdapter(List<CollegeListModel.DataBean> data) {
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
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_college_detail, null);
            holder = new ViewHolder();
            holder.mItemCollegeYx = (TextView) convertView.findViewById(R.id.item_college_yx);
            holder.mItemCollegeLqpc = (TextView) convertView.findViewById(R.id.item_college_lqpc);
            holder.mItemCollegeZymc = (TextView) convertView.findViewById(R.id.item_college_zymc);
            holder.mItemCollegePjf = (TextView) convertView.findViewById(R.id.item_college_pjf);
            holder.mItemCollegeZgf = (TextView) convertView.findViewById(R.id.item_college_zgf);
            holder.mItemCollegeZdf = (TextView) convertView.findViewById(R.id.item_college_zdf);
            convertView.setTag(holder);
        } else {
            /**
             * "adid": 731331,
             "schoolstatus": "山东",
             "paryear": "2017",
             "subject": "理科",
             "universityname": "大连理工大学",
             "major": "工程力学（钱令希力学创新实验班）",
             "average": 646,
             "hstscore": null,
             "mimscore": "646",
             "adtbatch": "本科批"
             */
            holder = (ViewHolder) convertView.getTag();
        }
        holder.mItemCollegeYx.setText(mData.get(position).getUniversityname() + "");
        holder.mItemCollegeLqpc.setText(mData.get(position).getAdtbatch() + "");
        holder.mItemCollegeZymc.setText(mData.get(position).getMajor() + "");
        holder.mItemCollegePjf.setText(mData.get(position).getAverage() + "");
        if (mData.get(position).getHstscore() != null) {

            holder.mItemCollegeZgf.setText(mData.get(position).getHstscore() + "");
        } else {
            holder.mItemCollegeZgf.setText("--");
        }
        holder.mItemCollegeZdf.setText(mData.get(position).getMimscore() + "");


        return convertView;
    }


    class ViewHolder {
        TextView mItemCollegeYx;
        TextView mItemCollegeLqpc;
        TextView mItemCollegeZymc;
        TextView mItemCollegePjf;
        TextView mItemCollegeZgf;
        TextView mItemCollegeZdf;


    }
}
