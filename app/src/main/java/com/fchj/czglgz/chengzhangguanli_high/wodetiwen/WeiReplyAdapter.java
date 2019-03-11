package com.fchj.czglgz.chengzhangguanli_high.wodetiwen;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.fchj.czglgz.chengzhangguanli_high.R;

import java.util.List;

/**
 * Created by Administrator on 2018/4/24 0024.
 */

public class WeiReplyAdapter extends BaseAdapter {
    private List< MyAskReplyModel.DataBean> mData;

    public WeiReplyAdapter(List< MyAskReplyModel.DataBean> data) {
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
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_wei_tiwen, null);
            holder = new  ViewHolder();
//            holder.contentTv = (TextView) convertView.findViewById(R.id.item_tiwen_content);
            holder.titleTv = (TextView) convertView.findViewById(R.id.item_tiwen_title);
            convertView.setTag(holder);
        } else {
            holder = ( ViewHolder) convertView.getTag();
        }

        holder.titleTv.setText(mData.get(position).getQuestiontitle());
        // 未回复不显示答案
//        holder.contentTv.setText(mData.get(position).getQuestioncontent());
        return convertView;
    }

    class ViewHolder {
        TextView titleTv, timeTv, contentTv;
    }
}
