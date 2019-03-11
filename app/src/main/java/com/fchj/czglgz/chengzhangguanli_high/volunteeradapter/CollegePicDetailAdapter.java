package com.fchj.czglgz.chengzhangguanli_high.volunteeradapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bm.library.PhotoView;
import com.bumptech.glide.Glide;
import com.fchj.czglgz.chengzhangguanli_high.R;
import com.fchj.czglgz.chengzhangguanli_high.volunteermodel.CollegePicDetailModel;

import java.util.ArrayList;
import java.util.List;

public class CollegePicDetailAdapter extends BaseAdapter {
    private List<CollegePicDetailModel.DataBean> mData;

    public CollegePicDetailAdapter(List<CollegePicDetailModel.DataBean> data) {
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
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_college_pic, null);
            holder = new ViewHolder();
            holder.mPhotoView = (ImageView) convertView.findViewById(R.id.item_college_pic);
            holder.titleTv = (TextView) convertView.findViewById(R.id.img_three_text);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.titleTv.setText(mData.get(position).getSceneryplace() + "");
        Glide.with(holder.mPhotoView.getContext()).load(mData.get(position).getBreviaryimg()).into(holder.mPhotoView);

        return convertView;
    }

    class ViewHolder {
        ImageView mPhotoView;
        TextView titleTv;
    }
}
