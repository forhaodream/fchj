package com.fchj.czglgz.chengzhangguanli_high.mysc;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.fchj.czglgz.chengzhangguanli_high.R;

import java.util.List;

public class MajorScAdapter extends RecyclerView.Adapter<MajorScAdapter.ViewHolder> {
    private List<ScMajorModel.DataBean> mData;
    OnItemClickListener onItemClickListener;

    public MajorScAdapter setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
        return this;
    }

    public interface OnItemClickListener {
        void onItemClick(ViewHolder holder, int position, String vcid);

        void onToDetail(View view, int position);
    }

    public MajorScAdapter(List<ScMajorModel.DataBean> data) {
        this.mData = data;
        Log.d("aaaaa", data.size() + "");
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_voca_sc_detail, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.nameTv.setText(mData.get(position).getMajors().getMajorname().toString() + "");
        holder.includeTv.setText(mData.get(position).getMajors().getMajorgenre().toString() + "");
        final ViewHolder finalHolder = holder;
        holder.sctitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onItemClickListener != null) {
                    onItemClickListener.onItemClick(holder, position, mData.get(position).getMajid());
                }

            }
        });
    }

    @Override
    public int getItemCount() {
        return mData == null ? 0 : mData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView nameTv, includeTv;
        ImageView headImg;
        Button sctitle;
        //  private MyItemClickListener mListener;

        public ViewHolder(final View itemView) { //, MyItemClickListener myItemClickListener
            super(itemView);
            //将全局的监听赋值给接口
//                this.mListener = myItemClickListener;
            itemView.setOnClickListener(this);
            nameTv = (TextView) itemView.findViewById(R.id.item_vocation_name);
            includeTv = (TextView) itemView.findViewById(R.id.item_vocation_type);
            sctitle = (Button) itemView.findViewById(R.id.sc_title);

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
        notifyDataSetChanged();
    }

}
