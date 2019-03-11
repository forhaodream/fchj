package com.fchj.czglgz.chengzhangguanli_high.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.fchj.czglgz.chengzhangguanli_high.R;
import com.fchj.czglgz.chengzhangguanli_high.model.TestModel;
import com.fchj.czglgz.chengzhangguanli_high.util.Url;
import com.zhy.autolayout.utils.AutoUtils;

import java.util.List;

/**
 * Created by Administrator on 2018/5/21 0021.
 */

public class HomeRecyclerAdapter extends RecyclerView.Adapter<HomeRecyclerAdapter.ViewHolder> {
    private List<TestModel.DataBean> mData;
    private MyItemClickListener mItemClickListener;
    XXListener mXXListener;

    public interface XXListener {
        public void onXXClick();
    }

    public void setOnXXClickListener(XXListener XXListener) {
        this.mXXListener = XXListener;
    }

    public HomeRecyclerAdapter(List<TestModel.DataBean> data) {
        this.mData = data;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_home_new_reyview, parent, false);
        ViewHolder viewHolder = new ViewHolder(view, mItemClickListener);
        AutoUtils.autoSize(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.nameTv.setText(mData.get(position).getUsername() + "");
        holder.includeTv.setText(mData.get(position).getSchl().getSchoolname() + "");
        Glide.with(holder.headImg.getContext())
                .load(Url.fileUrl + mData.get(position).getHeadimgurl())
                .placeholder(R.mipmap.teacher).into(holder.headImg);


    }

    @Override
    public int getItemCount() {
        return mData == null ? 0 : mData.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView nameTv, includeTv;
        ImageView headImg;
        private MyItemClickListener mListener;

        public ViewHolder(View itemView, MyItemClickListener myItemClickListener) {
            super(itemView);
            //将全局的监听赋值给接口
            this.mListener = myItemClickListener;
            itemView.setOnClickListener(this);
            nameTv = (TextView) itemView.findViewById(R.id.item_home_new_name);
            includeTv = (TextView) itemView.findViewById(R.id.item_home_new_include);
            headImg = (ImageView) itemView.findViewById(R.id.item_home_new_head);

        }

//        public ViewHolder(View itemView) {
//            super(itemView);
//
//        }

        @Override
        public void onClick(View v) {
            if (mListener != null) {
                mListener.onItemClick(v, getPosition());
            }
        }
    }

    /**
     * 创建一个回调接口
     */
    public interface MyItemClickListener {
        void onItemClick(View view, int position);
    }

    //在activity里面adapter就是调用的这个方法,将点击事件监听传递过来,并赋值给全局的监听

    public void setItemClickListener(MyItemClickListener myItemClickListener) {
        this.mItemClickListener = myItemClickListener;
    }


}
