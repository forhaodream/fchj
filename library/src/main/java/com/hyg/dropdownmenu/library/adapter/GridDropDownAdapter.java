package com.hyg.dropdownmenu.library.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.hyg.dropdownmenu.library.R;

import java.util.List;

/**
 * Created by HuangYuGuang on 2016/4/14.
 */
public class GridDropDownAdapter extends BaseAdapter {

    private Context context;
    private List<String> list;
    private int checkItemPosition = 0;

    public void setCheckItem(int position) {
        checkItemPosition = position;
        notifyDataSetChanged();
    }

    public GridDropDownAdapter(Context context, List<String> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView != null) {
            viewHolder = (ViewHolder) convertView.getTag();
        } else {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_grid_drop_down, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        }
        fillValue(position, viewHolder);
        return convertView;
    }

    private void fillValue(int position, ViewHolder viewHolder) {
        viewHolder.mText.setText(list.get(position));
        if (checkItemPosition != -1) {
            if (checkItemPosition == position) {
                viewHolder.mText.setTextColor(context.getResources().getColor(R.color.drop_down_selected));
                viewHolder.mText.setBackgroundResource(R.drawable.shape_drop_down_check_bg);
            } else {
                viewHolder.mText.setTextColor(context.getResources().getColor(R.color.drop_down_unselected));
                viewHolder.mText.setBackgroundResource(R.drawable.shape_drop_down_uncheck_bg);
            }
        }
    }

    static class ViewHolder {
        TextView mText;

        ViewHolder(View view) {
            mText = (TextView) view.findViewById(R.id.text);
        }
    }
}
