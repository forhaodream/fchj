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
import com.zhy.autolayout.AutoRelativeLayout;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MyMajScByGkAdapter extends BaseAdapter {
    private List<MyMajScListModel.DataBean> mData;
    private Map<Integer, Boolean> isCheckMap = new HashMap<>();
    private onClickMyTextView onClickMyTextView;
    private List<Integer> saveData = new ArrayList<>();
    private Map<Integer, Integer> saveMap = new HashMap<>();

    public MyMajScByGkAdapter(List<MyMajScListModel.DataBean> data) {
        this.mData = data;
        configCheckMap(false);
    }

    public void configCheckMap(boolean bool) {
        for (int i = 0; i < mData.size(); i++) {
            isCheckMap.put(i, bool);
        }
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
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_my_maj_sc_list, null);
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
            holder.mCheckBox = (CheckBox) convertView.findViewById(R.id.item_my_maj_sc_list_cb);
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

        final MyMajScListModel.DataBean mDataBean = mData.get(position);
        boolean canRemove = mDataBean.isCanRemove();
        final List<Integer> intss = new ArrayList<>();
        holder.mCheckBox.setChecked(mDataBean.isCheck);
        holder.mCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {

                    saveMap.put(position, mData.get(position).getMaiid());
//                    saveData.add(mData.get(position).getMaiid());

                } else {
                    saveMap.remove(position);
//                    saveData.remove(mData.get(position).getMaiid());
                }


                isCheckMap.put(position, isChecked);
                onClickMyTextView.myTextViewClick(saveMap);
            }
        });

        if (!canRemove) {
            holder.mCheckBox.setVisibility(View.GONE);
            holder.mCheckBox.setChecked(false);
            //   intss.remove(mData.get(position).getMaiid());

        } else {
            holder.mCheckBox.setVisibility(View.VISIBLE);
            //    intss.add(mData.get(position).getMaiid());
            if (isCheckMap.get(position) == null) {
                isCheckMap.put(position, false);
            }
            holder.mCheckBox.setChecked(isCheckMap.get(position));
        }

        return convertView;
    }

    //接口回调
    public interface onClickMyTextView {
        public void myTextViewClick(Map<Integer, Integer> map);// Map<Integer, Boolean> map
    }

    public void setOnClickMyTextView(onClickMyTextView onClickMyTextView) {
        this.onClickMyTextView = onClickMyTextView;
    }

    public static class ViewHolder {
        TextView mScListTv;
        TextView mTvOne;
        TextView mTvTwo;
        TextView mTvThree;
        TextView mTv1, mTv2, mTv3;
        AutoRelativeLayout mColorOne;
        AutoRelativeLayout mColorTwo;
        AutoRelativeLayout mColorThree;
        CheckBox mCheckBox;
    }

    public void remove(int position) {
        mData.remove(position);
    }

    public Map<Integer, Boolean> getCheckMap() {
        return this.isCheckMap;
    }

    public List<MyMajScListModel.DataBean> getData() {
        return mData;
    }

    public static void checkNum(int num) {

    }

}