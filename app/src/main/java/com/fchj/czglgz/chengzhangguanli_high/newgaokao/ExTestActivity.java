package com.fchj.czglgz.chengzhangguanli_high.newgaokao;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.CheckBox;
import android.widget.ExpandableListView;
import android.widget.TextView;
import android.widget.Toast;

import com.fchj.czglgz.chengzhangguanli_high.R;
import com.fchj.czglgz.chengzhangguanli_high.util.Url;
import com.fchj.czglgz.chengzhangguanli_high.view.MyPieChart;
import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import com.squareup.okhttp.Call;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

public class ExTestActivity extends Activity {
    private List<Map<String, String>> parentList = new ArrayList<Map<String, String>>();
    private List<List<Map<String, String>>> childData = new ArrayList<List<Map<String, String>>>();
    private ExpandableListView exListView;
    private Context context = this;
    private MyAdapter adapter;
    private Handler mHandler;
    private List<MBSDModel.DataBean> mGroupData;
    private List<MBSDModel.DataBean.LsmjrsBean> mChildData;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ex_test);
        mHandler = new Handler();
        exListView = (ExpandableListView) findViewById(R.id.expand);
//        addData();
        initData();
        setListener();
    }

    /**
     * 记录正在选中的子listview的item条目 用hashset是为了去除重复值
     */
    private HashSet<String> hashSet;

    private void setListener() {
        exListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {

            @Override
            public void onGroupExpand(int groupPosition) {
                //存取已选定的集合
                hashSet = new HashSet<String>();
            }
        });
        // ExpandableListView的Group的点击事件
        exListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {

            @Override
            public boolean onGroupClick(ExpandableListView parent, View v,
                                        int groupPosition, long id) {
                // 可以写点击后实现的功能

                return false;
            }
        });
        // ExpandableListView的child的点击事件

        exListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {

            @Override
            public boolean onChildClick(ExpandableListView parent, View v,
                                        int groupPosition, int childPosition, long id) {
                Map<String, String> map = childData.get(groupPosition).get(childPosition);
                String childChecked = map.get("isChecked");
                if ("No".equals(childChecked)) {
                    map.put("isChecked", "Yes");
                    hashSet.add("选定" + childPosition);
                } else {
                    map.put("isChecked", "No");
                    if (hashSet.contains("选定" + childPosition)) {
                        hashSet.remove("选定" + childPosition);
                    }
                }
                System.out.println("选定的长度==1" + hashSet.size());
                System.out.println("选定的长度==2"
                        + childData.get(groupPosition).size());
                if (hashSet.size() == childData.get(groupPosition).size()) {
                    parentList.get(groupPosition).put("isGroupCheckd", "Yes");
                } else {
                    parentList.get(groupPosition).put("isGroupCheckd", "No");
                }
                adapter.notifyDataSetChanged();
                return false;
            }
        });
    }

    // 初始化数据
    private void initData() {
        String[] aaa = new String[]{"哲学类", "逻辑学", "哲学", "伦理学", "宗教学"};
        for (int i = 0; i <1; i++) {
            Map<String, String> groupMap = new HashMap<String, String>();
            groupMap.put("groupText", "哲学");
            groupMap.put("isGroupCheckd", "No");
            parentList.add(groupMap);
        }
        for (int i = 0; i < 10; i++) {
            List<Map<String, String>> list = new ArrayList<Map<String, String>>();
            for (int j = 0; j < 4; j++) {
                Map<String, String> map = new HashMap<String, String>();
                map.put("childItem", aaa[j]);
                map.put("isChecked", "No");
                list.add(map);
            }
            childData.add(list);
        }
        adapter = new MyAdapter();
        exListView.setAdapter(adapter);
        exListView.expandGroup(0);
        hashSet = new HashSet<String>();
    }

    private MBSDModel mMBSDModel;

    private void addData() {
        FormEncodingBuilder builder = new FormEncodingBuilder();
        builder.add("schoolStatus", "浙江");
        builder.add("parYear", "2017");
        builder.add("optF", "物理");
        builder.add("optS", "化学");
        builder.add("optT", "生物");
        builder.add("coursekind", "哲学");
        Request request = new Request.Builder()
                .url("http://192.168.0.108:8080/HSGUM/JsonService/UnviersityRecruitAdmitMajo/getMajorCourseListByKind.json")
                .post(builder.build()).build();
        OkHttpClient okHttpClient = new OkHttpClient();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {

            }

            @Override
            public void onResponse(Response response) {
                try {
                    if (response.isSuccessful()) {
                        String body = response.body().string();
                        Log.d("body", body);
                        Gson gson = new Gson();
                        JsonReader reader = new JsonReader(new StringReader(body));
                        mMBSDModel = gson.fromJson(reader, MBSDModel.class);
                        if (mMBSDModel.getCode() == 1) {
                            mHandler.post(new Runnable() {
                                @Override
                                public void run() {
                                    mGroupData = mMBSDModel.getData();
                                    mChildData = mMBSDModel.getData().get(0).getLsmjrs();


                                }
                            });
                        } else if (mMBSDModel.getCode() == 2) {
                            Looper.prepare();
                            Toast.makeText(ExTestActivity.this, mMBSDModel.getMsg() + "", Toast.LENGTH_SHORT).show();
                            Looper.loop();
                        } else {
                            Looper.prepare();
                            Toast.makeText(ExTestActivity.this, mMBSDModel.getMsg() + "", Toast.LENGTH_SHORT).show();
                            Looper.loop();
                        }
                    } else {
                        Looper.prepare();
                        Toast.makeText(ExTestActivity.this, "服务器异常", Toast.LENGTH_SHORT).show(); //请求失败
                        Looper.loop();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private class MyAdapter extends BaseExpandableListAdapter {
        @Override
        public Object getChild(int groupPosition, int childPosition) {
            // TODO Auto-generated method stub
            return mChildData.get(groupPosition).getMajname();
        }

        @Override
        public long getChildId(int groupPosition, int childPosition) {
            // TODO Auto-generated method stub
            return childPosition;
        }

        @Override
        public View getChildView(int groupPosition, int childPosition,
                                 boolean isLastChild, View convertView, ViewGroup parent) {

            ViewHolder holder = null;
            if (convertView == null) {
                holder = new ViewHolder();
                convertView = View.inflate(context, R.layout.listview_item,
                        null);
                holder.childText = (TextView) convertView
                        .findViewById(R.id.id_text);
                holder.childBox = (CheckBox) convertView
                        .findViewById(R.id.id_checkbox);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            holder.childText.setText(childData.get(groupPosition)
                    .get(childPosition).get("childItem"));
            String isChecked = childData.get(groupPosition).get(childPosition)
                    .get("isChecked");
            if ("No".equals(isChecked)) {
                holder.childBox.setChecked(false);
            } else {
                holder.childBox.setChecked(true);
            }
            return convertView;
        }

        @Override
        public int getChildrenCount(int groupPosition) {
            // TODO Auto-generated method stub
            return childData.get(groupPosition).size();
        }

        @Override
        public Object getGroup(int groupPosition) {
            return parentList.get(groupPosition);
        }

        @Override
        public int getGroupCount() {
            // TODO Auto-generated method stub
            return parentList.size();
        }

        @Override
        public long getGroupId(int groupPosition) {
            // TODO Auto-generated method stub
            return groupPosition;
        }

        @Override
        public View getGroupView(final int groupPosition,
                                 final boolean isExpanded, View convertView, ViewGroup parent) {
            ViewHolder holder = null;
            if (convertView == null) {
                holder = new ViewHolder();
                convertView = View.inflate(context, R.layout.group_item, null);
                holder.groupText = (TextView) convertView
                        .findViewById(R.id.id_group_text);
                holder.groupBox = (CheckBox) convertView
                        .findViewById(R.id.id_group_checkbox);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            holder.groupText.setText(parentList.get(groupPosition).get(
                    "groupText"));
            final String isGroupCheckd = parentList.get(groupPosition).get(
                    "isGroupCheckd");

            if ("No".equals(isGroupCheckd)) {
                holder.groupBox.setChecked(false);
            } else {
                holder.groupBox.setChecked(true);
            }

            /*
             * groupListView的点击事件
             */
            holder.groupBox.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    CheckBox groupBox = (CheckBox) v
                            .findViewById(R.id.id_group_checkbox);
                    if (!isExpanded) {
                        //展开某个group view
                        exListView.expandGroup(groupPosition);
                    } else {
                        //关闭某个group view
                        exListView.collapseGroup(groupPosition);
                    }

                    if ("No".equals(isGroupCheckd)) {
                        exListView.expandGroup(groupPosition);
                        groupBox.setChecked(true);
                        parentList.get(groupPosition).put("isGroupCheckd",
                                "Yes");
                        List<Map<String, String>> list = childData
                                .get(groupPosition);
                        for (Map<String, String> map : list) {
                            map.put("isChecked", "Yes");
                        }
                    } else {
                        groupBox.setChecked(false);
                        parentList.get(groupPosition)
                                .put("isGroupCheckd", "No");
                        List<Map<String, String>> list = childData
                                .get(groupPosition);
                        for (Map<String, String> map : list) {
                            map.put("isChecked", "No");
                        }
                    }
                    notifyDataSetChanged();
                }
            });
            return convertView;
        }

        @Override
        public boolean hasStableIds() {
            return true;
        }

        @Override
        public boolean isChildSelectable(int groupPosition, int childPosition) {
            return true;
        }

    }

    private class ViewHolder {
        TextView groupText, childText;
        CheckBox groupBox, childBox;
    }
}
