package com.fchj.czglgz.chengzhangguanli_high.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.fchj.czglgz.chengzhangguanli_high.R;
import com.fchj.czglgz.chengzhangguanli_high.model.SchoolNewsModel;
import com.fchj.czglgz.chengzhangguanli_high.util.Url;
import com.fchj.czglgz.chengzhangguanli_high.view.GlideRoundTransform;
import com.fchj.czglgz.chengzhangguanli_high.zixun.ZXFragAdapter;
import com.fchj.czglgz.chengzhangguanli_high.zixun.ZXFragModel;

import java.util.List;

/**
 * Created by Administrator on 2018/4/23 0023.
 */

public class SchoolNewsAdapter extends BaseAdapter {
    private List<SchoolNewsModel.DataBean> mData;

    public SchoolNewsAdapter(List<SchoolNewsModel.DataBean> data) {
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
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_zx_big, null);
            holder = new ViewHolder();
            holder.img = (ImageView) convertView.findViewById(R.id.item_zixun_big_pic);
            holder.titleTv = (TextView) convertView.findViewById(R.id.item_zx_title);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.titleTv.setText(mData.get(position).getNewsname());
//        holder.contentTv.setText(mData.get(position).getContenttext());
        Glide.with(holder.img.getContext())
                .load(Url.fileUrl + mData.get(position).getNewspreviewimageurl())
                .transform(new GlideRoundTransform(holder.img.getContext(), 8))
                .placeholder(R.mipmap.zhongguo).error(R.mipmap.zhongguo)
                .into(holder.img);
//        Glide.with(holder.img.getContext()).load(Url.url + mData.get(position).getPreviewimageurl()).into(holder.img);
        return convertView;
    }

    class ViewHolder {
        TextView titleTv, timeTv, contentTv;
        ImageView img;
    }
}