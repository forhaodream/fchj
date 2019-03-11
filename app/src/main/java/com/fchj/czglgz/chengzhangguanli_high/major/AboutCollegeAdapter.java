package com.fchj.czglgz.chengzhangguanli_high.major;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.fchj.czglgz.chengzhangguanli_high.R;

import java.util.List;

public class AboutCollegeAdapter extends RecyclerView.Adapter<AboutCollegeAdapter.ViewHolder> {

    public List<AboutCollegeModel.DataBean> mData;

    OnItemClickListener onItemClickListener;

    public AboutCollegeAdapter setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
        return this;
    }

    public interface OnItemClickListener {
        void onItemClick(ViewHolder holder, int position, String vcid);

        void onToDetail(View view, int position);
    }


    public AboutCollegeAdapter(List<AboutCollegeModel.DataBean> data) {
        this.mData = data;
        Log.d("aaaaa", data.size() + "");
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_about_college, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {

        holder.nameTv.setText(mData.get(position).getUniversityname() + "");
        holder.includeTv.setText(mData.get(position).getMajor() + "");
        holder.batchTv.setText(mData.get(position).getBatch() + "");


    }

    @Override
    public int getItemCount() {
        return mData == null ? 0 : mData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView nameTv, includeTv, batchTv;

        Button sctitle;

        public ViewHolder(final View itemView) { //, MyItemClickListener myItemClickListener
            super(itemView);
            //将全局的监听赋值给接口
//                this.mListener = myItemClickListener;
            itemView.setOnClickListener(this);
            nameTv = (TextView) itemView.findViewById(R.id.item_vocation_name);
            includeTv = (TextView) itemView.findViewById(R.id.item_vocation_type);
            batchTv = (TextView) itemView.findViewById(R.id.about_college_batch);

        }

        @Override
        public void onClick(View v) {
            if (onItemClickListener != null) {
                onItemClickListener.onToDetail(v, getPosition());
            }
        }


    }


    // 删除数据
    public void removeData(int position) {
        mData.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(0, mData.size());
    }

    public void removeItem(int adapterPosition) {
        if (adapterPosition == RecyclerView.NO_POSITION)
            return;
        if (adapterPosition >= 0 && adapterPosition < mData.size()) {  //mDatas为数据集合
            mData.remove(adapterPosition);
            notifyItemRemoved(adapterPosition);
        }
    }
}