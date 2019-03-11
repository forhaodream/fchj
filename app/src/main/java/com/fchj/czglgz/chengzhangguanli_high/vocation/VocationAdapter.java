package com.fchj.czglgz.chengzhangguanli_high.vocation;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.fchj.czglgz.chengzhangguanli_high.R;
import com.fchj.czglgz.chengzhangguanli_high.util.Url;
import com.fchj.czglgz.chengzhangguanli_high.view.CornersTransform;
import com.fchj.czglgz.chengzhangguanli_high.volunteer.CollegeDetailAdapter;
import com.fchj.czglgz.chengzhangguanli_high.volunteer.CollegeListModel;
import com.zhy.autolayout.AutoRelativeLayout;

import java.util.List;

public class VocationAdapter extends BaseAdapter {
    private List<PicModel> mData;
    private Context mContext;
    private int phoneType;

    public VocationAdapter(List<PicModel> data, Context context, int a) {
        this.mData = data;
        this.mContext = context;
        this.phoneType = a;
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
//            if (phoneType == 2) {
                convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_vocation, null);

//            } else if (phoneType == 1) {
//                convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_vocation_oppo, null);
//            }
            holder = new ViewHolder();
            holder.vocationImg = (ImageView) convertView.findViewById(R.id.item_vocation_img);
            holder.vocationName = (TextView) convertView.findViewById(R.id.item_vocation_name);
            holder.mAutoRelativeLayout = (AutoRelativeLayout) convertView.findViewById(R.id.vocation_rl);


            convertView.setTag(holder);
        } else {

            holder = (ViewHolder) convertView.getTag();
        }
        holder.vocationName.setText(mData.get(position).getVocationName().toString() + "");
        holder.mAutoRelativeLayout.setBackgroundResource(mData.get(position).getPhotoId());
        //  Glide.with(holder.vocationImg.getContext())
        //     .load(mData.get(position).getPhotoId()).transform(new CornersTransform(mContext, 10)).into(holder.vocationImg);

//

        return convertView;
    }


    class ViewHolder {
        TextView vocationName;
        ImageView vocationImg;
        AutoRelativeLayout mAutoRelativeLayout;


    }
}