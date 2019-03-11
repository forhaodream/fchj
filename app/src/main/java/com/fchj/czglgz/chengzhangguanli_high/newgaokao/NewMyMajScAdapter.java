package com.fchj.czglgz.chengzhangguanli_high.newgaokao;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.fchj.czglgz.chengzhangguanli_high.R;
import com.fchj.czglgz.chengzhangguanli_high.overseasstudy.OsStudyAdapter;
import com.zhy.autolayout.AutoRelativeLayout;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NewMyMajScAdapter extends BaseAdapter {
    private List<MyMajScListModel.DataBean> mData;
    public List<Integer> seleteds;
    int count = 1;

    public NewMyMajScAdapter(List<MyMajScListModel.DataBean> data) {
        this.mData = data;
        seleteds = new ArrayList<>();
    }

    OnItemClickListener onItemClickListener;


    public NewMyMajScAdapter setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
        return this;
    }

    public interface OnItemClickListener {
        void onChangeImg(boolean bool, int postition);

        void onItemClick(ViewHolder holder, int position);

        void onToDetail(View view, int position);
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

    public void addSelected(int position) {
        seleteds.add(position);
    }

    public void removeSelected(Integer position) {
        if (seleteds.contains(position)) {
            seleteds.remove(position);
        }
    }

    /**
     * 清空选中集合
     */
    public void removeselected() {
        seleteds.clear();
    }

    /*
     * 判读是否饱含当前的条目
     */
    public boolean isItemSelected(int position) {
        return seleteds.contains(position) ? true : false;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_my_maj_sc_list_new, null);
            holder = new ViewHolder();
            holder.mScListTv = (TextView) convertView.findViewById(R.id.item_my_maj_sc_list_tv);
            holder.mTvOne = (TextView) convertView.findViewById(R.id.item_my_maj_tv_one);
            holder.mColorOne = (AutoRelativeLayout) convertView.findViewById(R.id.item_my_maj_color_one);
            holder.mTvTwo = (TextView) convertView.findViewById(R.id.item_my_maj_tv_two);
            holder.mColorTwo = (AutoRelativeLayout) convertView.findViewById(R.id.item_my_maj_color_two);
            holder.mTvThree = (TextView) convertView.findViewById(R.id.item_my_maj_tv_three);
            holder.mColorThree = (AutoRelativeLayout) convertView.findViewById(R.id.item_my_maj_color_three);
            holder.mTv1 = (TextView) convertView.findViewById(R.id.item_my_maj_tv_1);
            holder.mTv2 = (TextView) convertView.findViewById(R.id.item_my_maj_tv_2);
            holder.mTv3 = (TextView) convertView.findViewById(R.id.item_my_maj_tv_3);
            holder.mImg = (ImageView) convertView.findViewById(R.id.item_my_maj_sc_list_img);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.mScListTv.setText(mData.get(position).getMajio().getMajorname());//mData.get(position).getMajio().getMajorname()
        holder.mTv1.setText(mData.get(0).getMajio().getMajorgenre());
        holder.mTv2.setText(mData.get(0).getMajio().getCoursekind());
        holder.mTv3.setText(mData.get(0).getMajio().getCoursetype());
        String str = mData.get(position).getAbtsubject();//根据逗号分隔到List数组中
        String str2 = str.replace(" ", "");//去掉所用空格
        List<String> list = Arrays.asList(str.split(","));
        holder.mTvOne.setText(list.get(0));
        holder.mTvTwo.setText(list.get(1));
        holder.mTvThree.setText(list.get(2));
        // color
        if (list.get(0).equals("物理")) {
            holder.mColorOne.setBackgroundResource(R.color.wuli);
        } else if (list.get(0).equals("化学")) {
            holder.mColorOne.setBackgroundResource(R.color.huaxue);
        } else if (list.get(0).equals("生物")) {
            holder.mColorOne.setBackgroundResource(R.color.shengwu);
        } else if (list.get(0).equals("地理")) {
            holder.mColorOne.setBackgroundResource(R.color.dili);
        } else if (list.get(0).equals("政治")) {
            holder.mColorOne.setBackgroundResource(R.color.zhengzhi);
        } else if (list.get(0).equals("历史")) {
            holder.mColorOne.setBackgroundResource(R.color.lishi);
        }
        if (list.get(1).equals("物理")) {
            holder.mColorTwo.setBackgroundResource(R.color.wuli);
        } else if (list.get(1).equals("化学")) {
            holder.mColorTwo.setBackgroundResource(R.color.huaxue);
        } else if (list.get(1).equals("生物")) {
            holder.mColorTwo.setBackgroundResource(R.color.shengwu);
        } else if (list.get(1).equals("地理")) {
            holder.mColorTwo.setBackgroundResource(R.color.dili);
        } else if (list.get(1).equals("政治")) {
            holder.mColorTwo.setBackgroundResource(R.color.zhengzhi);
        } else if (list.get(1).equals("历史")) {
            holder.mColorTwo.setBackgroundResource(R.color.lishi);
        }
        if (list.get(2).equals("物理")) {
            holder.mColorThree.setBackgroundResource(R.color.wuli);
        } else if (list.get(2).equals("化学")) {
            holder.mColorThree.setBackgroundResource(R.color.huaxue);
        } else if (list.get(2).equals("生物")) {
            holder.mColorThree.setBackgroundResource(R.color.shengwu);
        } else if (list.get(2).equals("地理")) {
            holder.mColorThree.setBackgroundResource(R.color.dili);
        } else if (list.get(2).equals("政治")) {
            holder.mColorThree.setBackgroundResource(R.color.zhengzhi);
        } else if (list.get(2).equals("历史")) {
            holder.mColorThree.setBackgroundResource(R.color.lishi);
        }
        holder.mImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mData.get(position).isCheck) {
                    mData.get(position).setCheck(false);
                } else {
                    mData.get(position).setCheck(true);
                }
                notifyDataSetChanged();
            }
        });

        if (mData.get(position).isCheck) {
            holder.mImg.setImageResource(R.mipmap.xuanze);
            seleteds.add(mData.get(position).getMaiid());
        } else {
            holder.mImg.setImageResource(R.mipmap.weixuan);
            if (seleteds.size() > 0) {
                seleteds.remove(mData.get(position).getMaiid());

            }
        }


        return convertView;
    }


    class ViewHolder {
        TextView mScListTv;
        TextView mTvOne;
        TextView mTvTwo;
        TextView mTvThree;
        TextView mTv1, mTv2, mTv3;
        AutoRelativeLayout mColorOne;
        AutoRelativeLayout mColorTwo;
        AutoRelativeLayout mColorThree;
        CheckBox mCheckBox;
        ImageView mImg;
    }


}