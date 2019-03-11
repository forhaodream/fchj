package com.fchj.czglgz.chengzhangguanli_high.jiazhangketang;

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
import com.fchj.czglgz.chengzhangguanli_high.zixun.ZXFragAdapter;
import com.fchj.czglgz.chengzhangguanli_high.zixun.ZXFragModel;

import java.util.List;

/**
 * Created by Administrator on 2018/4/20 0020.
 */

public class JZAdapter extends BaseAdapter {
    private List<JZFragModel.DataBean> mData;

    public JZAdapter(List<JZFragModel.DataBean> data) {
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
            if (mData.get(position).getIsinc() == 0) {
                convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_jz_no_video, null);
                holder = new ViewHolder();
                holder.img = (ImageView) convertView.findViewById(R.id.item_jz_no_video_pic);
                holder.titleTv = (TextView) convertView.findViewById(R.id.item_jz_no_video_title);
                holder.contentTv = (TextView) convertView.findViewById(R.id.item_jz_no_video_content);
                convertView.setTag(holder);
            } else if (mData.get(position).getIsinc() == 1) {
                convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_jz_video, null);
                holder = new ViewHolder();
                holder.img = (ImageView) convertView.findViewById(R.id.item_jz_video_pic);
                holder.titleTv = (TextView) convertView.findViewById(R.id.item_jz_video_title);
                holder.contentTv = (TextView) convertView.findViewById(R.id.item_jz_video_content);
                convertView.setTag(holder);
            }

        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.titleTv.setText(mData.get(position).getClassname());
        holder.contentTv.setText(mData.get(position).getClasscontenttext());
        Glide.with(holder.img.getContext()).load(Url.fileUrl + mData.get(position).getClasspreviewimageurl())
                .transform(new GlideRoundTransform(holder.img.getContext(), 1))
                .placeholder(R.mipmap.zhongguo).error(R.mipmap.zhongguo).into(holder.img);
//        Glide.with(holder.img.getContext()).load(Url.url + mData.get(position).getPreviewimageurl()).into(holder.img);
        return convertView;
    }

    class ViewHolder {
        TextView titleTv, timeTv, contentTv;
        ImageView img;
    }
}
