package com.fchj.czglgz.chengzhangguanli_high.vocation;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.fchj.czglgz.chengzhangguanli_high.R;
import com.fchj.czglgz.chengzhangguanli_high.adapter.PopupShengAdapter;
import com.zhy.autolayout.AutoRelativeLayout;

import java.util.List;

public class VocationListAdapter extends BaseAdapter {
    private List<VocationModel.DataBean> mData;

    public VocationListAdapter(List<VocationModel.DataBean> data) {
        this.mData = data;
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

    @SuppressLint("ResourceAsColor")
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_popup_voca, null);
            holder = new ViewHolder();
            holder.titleTv = (TextView) convertView.findViewById(R.id.item_vocation_name);
           // holder.scImg = (AutoRelativeLayout) convertView.findViewById(R.id.item_vocation_sc);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.titleTv.setText(mData.get(position).getVocaname() + "");
//        holder.scImg.setBackgroundColor(Color.parseColor("#EE0101"));
        //mRelativeLayout.setBackgroundColor(getRescource().getColor(R.color.red)) ;
        //mRelativeLayout.setBackgroundColor(Color.parseColor(" # FF00FF")) ;
        //
        //作者：世道无情
        //链接：https://www.jianshu.com/p/28d041690a0e
        //來源：简书
        //著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
        return convertView;
    }

    class ViewHolder {
        TextView titleTv;
        AutoRelativeLayout scImg;
    }
}