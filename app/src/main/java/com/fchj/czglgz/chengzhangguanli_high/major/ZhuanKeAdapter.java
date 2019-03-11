package com.fchj.czglgz.chengzhangguanli_high.major;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.fchj.czglgz.chengzhangguanli_high.R;
import com.fchj.czglgz.chengzhangguanli_high.bean.ZhuanKeBig;
import com.fchj.czglgz.chengzhangguanli_high.mysc.Classes;
import com.fchj.czglgz.chengzhangguanli_high.mysc.GroupBean;
import com.fchj.czglgz.chengzhangguanli_high.mysc.ItemBean;

import java.util.List;

public class ZhuanKeAdapter extends BaseExpandableListAdapter {

    private List<ZhuanKeBig> mZhuanKeBigs;
    private List<Classes> mClasses;
    private Context mContext;
    private String[] listinfo;
    private String[] groupname;

    public ZhuanKeAdapter(List<Classes> mClasses, Context context) {
        this.mClasses = mClasses;
        this.mContext = context;
    }

    @Override
    public int getGroupCount() {
        return mClasses.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        // 获取对应一级条目下二级条目的数量，就是各个班学生的数量
        return mClasses.get(groupPosition).student.size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return mClasses.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return mClasses.get(groupPosition).student.get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        View view;
        if (convertView == null) {
            view = LayoutInflater.from(mContext).inflate(R.layout.item_sc_group_exlist, parent, false);
        } else {
            view = convertView;
        }

        //  ZhuanKeBig zhuanKeBig = (ZhuanKeBig) getGroup(groupPosition);
        TextView textView = (TextView) view.findViewById(R.id.view_title);
        textView.setText(mClasses.get(groupPosition).name);
        return view;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        View view;
        if (convertView == null) {
            view = LayoutInflater.from(mContext).inflate(R.layout.item_sc_exlist, parent, false);
        } else {
            view = convertView;
        }
        //  ZhuanKeBig zhuanKeBig = (ZhuanKeBig) getGroup(groupPosition);
        TextView textView = (TextView) view.findViewById(R.id.view_content);
        textView.setText(mClasses.get(groupPosition).student.get(childPosition));
        return view;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}
