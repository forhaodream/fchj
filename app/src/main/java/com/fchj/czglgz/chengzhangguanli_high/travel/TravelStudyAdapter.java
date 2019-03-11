package com.fchj.czglgz.chengzhangguanli_high.travel;

import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.fchj.czglgz.chengzhangguanli_high.R;
import com.fchj.czglgz.chengzhangguanli_high.util.Url;
import com.fchj.czglgz.chengzhangguanli_high.view.GlideRoundTransform;
import com.zhy.autolayout.utils.AutoUtils;

import java.util.List;

public class TravelStudyAdapter extends RecyclerView.Adapter<TravelStudyAdapter.ViewHolder> {

    public List<TravelModel.DataBean> mData;

    OnItemClickListener onItemClickListener;
    private ImageView mItemTravelPic;
    private TextView mItemTravelTitle;
    private TextView mItemTravelContent;


    public TravelStudyAdapter setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
        return this;
    }

    public interface OnItemClickListener {
        void onItemClick(ViewHolder holder, int position);

        void onToDetail(View view, int position);
    }


    public TravelStudyAdapter(List<TravelModel.DataBean> data) {
        this.mData = data;
        Log.d("aaaaa", data.size() + "");
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_travel, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        AutoUtils.autoSize(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.mTitle.setText(mData.get(position).getTitle());
        holder.mContent.setText(mData.get(position).getSummary());
        holder.mPrice.setText("¥" + mData.get(position).getPayfor() + "起/份");
        Glide.with(holder.mPic.getContext())
                .load(Url.fileUrl + mData.get(position).getTsimageurl())//
                .into(holder.mPic);


    }

    @Override
    public int getItemCount() {
        return mData == null ? 0 : mData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView mTitle;
        private TextView mContent;
        private ImageView mPic;
        private Button mPrice;

        public ViewHolder(final View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            mTitle = (TextView) itemView.findViewById(R.id.item_travel_title);
            mContent = (TextView) itemView.findViewById(R.id.item_travel_content);
            mPic = (ImageView) itemView.findViewById(R.id.item_travel_pic);
            mPrice = (Button) itemView.findViewById(R.id.item_travel_price);


        }

        @Override
        public void onClick(View v) {
            if (onItemClickListener != null) {
                onItemClickListener.onToDetail(v, getPosition());
            }
        }


    }

}