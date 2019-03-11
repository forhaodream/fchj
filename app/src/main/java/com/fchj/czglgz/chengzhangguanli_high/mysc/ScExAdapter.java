package com.fchj.czglgz.chengzhangguanli_high.mysc;

import android.content.ClipData;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.fchj.czglgz.chengzhangguanli_high.R;

import java.util.List;

public class ScExAdapter extends BaseExpandableListAdapter {
    List<MyScModel.DataBean> mGroup;
    List<List> majorList;
    Context mContext;

    public ScExAdapter(List<MyScModel.DataBean> group, List<List> majorList, Context context) {
        this.mGroup = group;
        this.majorList = majorList;
        this.mContext = context;

    }

    @Override
    public int getGroupCount() {
        return mGroup.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        List<MyScModel.DataBean> item = (List<MyScModel.DataBean>) majorList.get(groupPosition);
        //  List<ItemBean> itemList = majorList.get(groupPosition);
        return item.size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return mGroup.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        List<MyScModel.DataBean> item = (List<MyScModel.DataBean>) majorList.get(groupPosition);
        //  List<ItemBean> itemList = majorList.get(groupPosition);
        return item.get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return 0;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return 0;
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

//        GroupBean groupBean = (GroupBean) getGroup(groupPosition);
        MyScModel.DataBean mDataBean = (MyScModel.DataBean) getGroup(groupPosition);
        TextView textView = (TextView) view.findViewById(R.id.view_title);
        textView.setText(mDataBean.getVoc().getVocatype());
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
        //   ItemBean itemBean = (ItemBean) getChild(groupPosition, childPosition);
        MyScModel.DataBean aaa = (MyScModel.DataBean) getChild(groupPosition, childPosition);
        TextView textView = (TextView) view.findViewById(R.id.view_content);
        textView.setText(aaa.getVoc().getVocaname());
        return view;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

    @Override
    public boolean areAllItemsEnabled() {
        return false;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }


}
