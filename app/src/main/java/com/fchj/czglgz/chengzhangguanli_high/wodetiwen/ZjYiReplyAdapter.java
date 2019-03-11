package com.fchj.czglgz.chengzhangguanli_high.wodetiwen;

/**
 * Created by Administrator on 2018/4/26 0026.
 */

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.fchj.czglgz.chengzhangguanli_high.R;

import java.util.List;


public class ZjYiReplyAdapter extends BaseAdapter {
    private List<YiReplyModel.DataBean> mData;

    public ZjYiReplyAdapter(List<YiReplyModel.DataBean> data) {
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
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_tiwen, null);
            holder = new ViewHolder();
            holder.titleTv = (TextView) convertView.findViewById(R.id.item_tiwen_title);
            holder.contentTv = (TextView) convertView.findViewById(R.id.item_tiwen_content);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.titleTv.setText(mData.get(position).getQuestiontitle());
        holder.contentTv.setText(mData.get(position).getExpaws().getAnswercontent());
        return convertView;
    }

    class ViewHolder {
        TextView titleTv, timeTv, contentTv;
    }
}
