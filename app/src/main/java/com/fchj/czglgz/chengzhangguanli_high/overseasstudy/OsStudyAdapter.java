package com.fchj.czglgz.chengzhangguanli_high.overseasstudy;

import android.support.v7.widget.CardView;
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

public class OsStudyAdapter extends RecyclerView.Adapter<OsStudyAdapter.ViewHolder> {

    public List<OsStudyModel.DataBean> mData;

    OnItemClickListener onItemClickListener;


    public OsStudyAdapter setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
        return this;
    }

    public interface OnItemClickListener {
        void onItemClick(ViewHolder holder, int position);

        void onToDetail(View view, int position);
    }


    public OsStudyAdapter(List<OsStudyModel.DataBean> data) {
        this.mData = data;
        Log.d("aaaaa", data.size() + "");
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_os_study, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        AutoUtils.autoSize(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {

        holder.mItemOsCountry.setText(mData.get(position).getOwnedCountry() + "");
        if (!TextUtils.isEmpty(mData.get(position).getStayExpense())) {
            holder.mItemOsMoney.setText(mData.get(position).getStayExpense() + "");

        } else {
            holder.mItemOsMoney.setVisibility(View.GONE);
        }
        holder.mItemOsChnName.setText(mData.get(position).getSchoolName() + "");
        holder.mItemOsEngName.setText(mData.get(position).getSchoolEnname() + "");
        Glide.with(holder.mItemOsPic.getContext())//mData.get(position).getLausy().get(0).getAusimg()
                .load(mData.get(position).getLausy().get(0).getAusimg())//Url.fileUrl + "/HighShoolManager/image/banner/201806121006496655.jpg"
                .placeholder(R.mipmap.zhongguo).error(R.mipmap.zhongguo)
                .into(holder.mItemOsPic);
        Glide.with(holder.mLogo.getContext())
                .load(mData.get(position).getSchoollogo())
                .transform(new GlideRoundTransform(holder.mLogo.getContext(), 8))
                .placeholder(R.mipmap.fchj_e).error(R.mipmap.fchj_e)
                .into(holder.mLogo);
        holder.mItemOsAsk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onItemClickListener != null) {
                    onItemClickListener.onItemClick(holder, position);
                }
            }
        });


    }

    @Override
    public int getItemCount() {
        return mData == null ? 0 : mData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView mItemOsCountry;
        private TextView mItemOsMoney;
        private ImageView mItemOsPic, mLogo;
        private TextView mItemOsChnName;
        private TextView mItemOsEngName;
        private Button mItemOsAsk;

        public ViewHolder(final View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            mItemOsCountry = (TextView) itemView.findViewById(R.id.item_os_country);
            mItemOsMoney = (TextView) itemView.findViewById(R.id.item_os_money);
            mItemOsPic = (ImageView) itemView.findViewById(R.id.item_os_pic);
            mItemOsChnName = (TextView) itemView.findViewById(R.id.item_os_chn_name);
            mItemOsEngName = (TextView) itemView.findViewById(R.id.item_os_eng_name);
            mItemOsAsk = (Button) itemView.findViewById(R.id.item_os_ask);
            mLogo = (ImageView) itemView.findViewById(R.id.item_os_logo);

        }

        @Override
        public void onClick(View v) {
            if (onItemClickListener != null) {
                onItemClickListener.onToDetail(v, getPosition());
            }
        }


    }

}
