package com.fchj.czglgz.chengzhangguanli_high.zhuanjiajieda;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.fchj.czglgz.chengzhangguanli_high.R;
import com.fchj.czglgz.chengzhangguanli_high.model.TestModel;
import com.fchj.czglgz.chengzhangguanli_high.util.Url;
import com.fchj.czglgz.chengzhangguanli_high.view.GlideRoundTransform;
import com.fchj.czglgz.chengzhangguanli_high.zixun.ZXFragAdapter;

import java.util.List;

/**
 * Created by Administrator on 2018/4/23 0023.
 */

public class ZJAdapter extends BaseAdapter {
    private List<TestModel.DataBean> mData;
//    private List<TestModel.DataBean.SchlBean> mSchlBeen;

    public ZJAdapter(List<TestModel.DataBean> data) {
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
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_zj_detail, null);
            holder = new ViewHolder();
            holder.schoolTv = (TextView) convertView.findViewById(R.id.zj_detail_school);
            holder.contentTv = (TextView) convertView.findViewById(R.id.zj_detail_jieshao);
            holder.titleTv = (TextView) convertView.findViewById(R.id.zj_detail_name);
            holder.img = (ImageView) convertView.findViewById(R.id.zj_detail_touxiang);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.titleTv.setText(mData.get(position).getUsername());
        holder.schoolTv.setText(mData.get(position).getSchl().getSchoolname() + "");
//        holder.schoolTv.setText(mData.get(position).getSchool() + "");
        holder.contentTv.setText(mData.get(position).getSummary() + "");
        Glide.with(holder.img.getContext())
                .load(Url.fileUrl + mData.get(position).getHeadimgurl())
                .placeholder(R.mipmap.teacher).into(holder.img);
//        Glide.with(holder.img.getContext()).load(Url.url + mData.get(position).getPreviewimageurl()).into(holder.img);
        return convertView;
    }

    class ViewHolder {
        TextView titleTv, schoolTv, contentTv;
        ImageView img;
    }
}
