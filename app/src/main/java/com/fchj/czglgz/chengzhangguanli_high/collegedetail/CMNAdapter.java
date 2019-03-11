package com.fchj.czglgz.chengzhangguanli_high.collegedetail;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.fchj.czglgz.chengzhangguanli_high.R;
import com.fchj.czglgz.chengzhangguanli_high.volunteer.CollegeListDetailModel;
import com.fchj.czglgz.chengzhangguanli_high.volunteeradapter.CollegeListDetailAdapter;

import java.util.List;

public class CMNAdapter extends BaseAdapter {
    private List<CMNModel.DataBean> mData;
    // private ImageView mImageView;

    public CMNAdapter(List<CMNModel.DataBean> data) {
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
        ViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_college_list_detail, null);
            holder = new ViewHolder();
            holder.mYuanxiao = (TextView) convertView.findViewById(R.id.yuanxiao);
            holder.mZhuanye = (TextView) convertView.findViewById(R.id.zhuanye);
            holder.mPici = (TextView) convertView.findViewById(R.id.pici);
            holder.mPingjunfen = (TextView) convertView.findViewById(R.id.pingjunfen);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.mYuanxiao.setText(mData.get(position).getMajor() + "");
        holder.mZhuanye.setText(mData.get(position).getSubject() + "");
        holder.mPici.setText(mData.get(position).getAverage2016() + "");
        holder.mPingjunfen.setText(mData.get(position).getAverage2017() + "");

        return convertView;
    }

    public static class ViewHolder {
        TextView mYuanxiao;
        TextView mZhuanye;
        TextView mPici;
        TextView mPingjunfen;
    }


}

