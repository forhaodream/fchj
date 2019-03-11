package com.fchj.czglgz.chengzhangguanli_high.xuebalaile;

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

import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayerStandard;

/**
 * Created by Administrator on 2018/4/23 0023.
 */

public class XueBaAdapter extends BaseAdapter {
    private List<XueBaModel.DataBean> mData;

    public XueBaAdapter(List<XueBaModel.DataBean> data) {
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
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_video_list, null);
            holder = new ViewHolder();
            holder.mJCVideoPlayerStandard = (JCVideoPlayerStandard) convertView.findViewById(R.id.item_video_jiecao);
            holder.titleTv = (TextView) convertView.findViewById(R.id.item_video_title);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.titleTv.setText(mData.get(position).getVoidname());
        holder.titleTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                holder.mJCVideoPlayerStandard.
            }
        });
        holder.mJCVideoPlayerStandard.setUp(mData.get(position).getContentvoidurl(), JCVideoPlayer.SCREEN_LAYOUT_NORMAL, "");
//        holder.mJCVideoPlayerStandard.startButton.performClick();
        Glide.with(holder.mJCVideoPlayerStandard.thumbImageView.getContext()).load(Url.fileUrl + mData.get(position).getPreviewimageurl()).into(holder.mJCVideoPlayerStandard.thumbImageView);

        return convertView;
    }

    class ViewHolder {
        TextView titleTv;
        JCVideoPlayerStandard mJCVideoPlayerStandard;

    }
}