package com.fchj.czglgz.chengzhangguanli_high.newgaokao;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.Nullable;
import android.support.annotation.PluralsRes;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.CheckBox;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.fchj.czglgz.chengzhangguanli_high.R;
import com.fchj.czglgz.chengzhangguanli_high.expandlistview.ChildBean;
import com.fchj.czglgz.chengzhangguanli_high.expandlistview.ParentBean;
import com.fchj.czglgz.chengzhangguanli_high.util.IntentUtil;
import com.fchj.czglgz.chengzhangguanli_high.util.Url;
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

public class MBSDetailActivity extends Activity implements View.OnClickListener {
    private List<ParentBean> mParentBeans;
    private List<ChildBean> mChildBeans;
    private ExpandableListView mExpandableListView;
    private Map<String, List<ChildBean>> datasets = new HashMap<>();
    private String[] PList;
    private MyExpandAdapter mMyExpandAdapter;
    private TextView parentText;
    private CheckBox parentCb, childCb;

    private int lastClick = -1;//上一次点击的group的position
    private Handler mHandler;
    private String oneS, twoS, threeS, ckStr;
    /**
     * 记录正在选中的子listview的item条目 用hashset是为了去除重复值
     */
//    private HashSet<String> hashSet;
    private List<String> stringSet;
    private SharedPreferences usernameSp;
    private int userId;
    private TextView openTv, closeTv, allTv, trueTv;
    final List<String> majList = new ArrayList<>();
    int childCount = 1;
    private ImageView parentImg, childImg;
    private MBSDetailPutModel mMBSDetailPutModel;

    //Expandlistview 相关
    List<String> listParent;
    List<String> listChild;
    List<Map<String, Boolean>> groupCheckBox;
    List<List<Map<String, Boolean>>> childCheckBox;
    private List<Map<String, String>> parentList = new ArrayList<Map<String, String>>();
    private List<List<Map<String, String>>> childData = new ArrayList<List<Map<String, String>>>();
    private int isResume = -1;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ex_test);
        mHandler = new Handler();
        usernameSp = getSharedPreferences("user_npt", MODE_PRIVATE);
        userId = usernameSp.getInt("userId", 0);
        mExpandableListView = (ExpandableListView) findViewById(R.id.expand);
        openTv = (TextView) findViewById(R.id.ex_open);
        trueTv = (TextView) findViewById(R.id.choose_true);
        openTv.setOnClickListener(this);
        trueTv.setOnClickListener(this);
        mChildBeans = new ArrayList<>();
        mParentBeans = new ArrayList<>();
        Intent intent = getIntent();
        oneS = intent.getStringExtra("one_S");
        twoS = intent.getStringExtra("two_S");
        threeS = intent.getStringExtra("three_S");
        ckStr = intent.getStringExtra("maj_name");
        hashSet = new HashSet<String>();
        mMyExpandAdapter = new MyExpandAdapter();
        addData();
        setListener();
    }


    private void addPList() {
        PList = new String[mBean.getData().size()];
        for (int i = 0; i < PList.length; i++) {
            PList[i] = mBean.getData().get(i).getGenresname();
            Map<String, String> groupMap = new HashMap<String, String>();
            List<Map<String, String>> list = new ArrayList<Map<String, String>>();
            groupMap.put("isGroupCheckd", "No");
            parentList.add(groupMap);
            for (int j = 0; j < mBean.getData().get(i).getLsmjrs().size(); j++) {
                datasets.put(PList[i], mBean.getData().get(i).getLsmjrs());
                Map<String, String> map = new HashMap<String, String>();
                map.put("isChecked", "No");
                list.add(map);
            }
            childData.add(list);
        }
    }

    private List<ParentBean.DataBean> mParentBean;
    private ParentBean mBean;

    private void addData() {
        FormEncodingBuilder builder = new FormEncodingBuilder();
        builder.add("schoolStatus", "浙江");
        builder.add("parYear", "2017");
        builder.add("optF", oneS);
        builder.add("optS", twoS);
        builder.add("optT", threeS);
        builder.add("coursekind", ckStr);
        Request request = new Request.Builder().url(Url.mbsDetailUrl).post(builder.build()).build();
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
                        mBean = gson.fromJson(reader, ParentBean.class);
                        if (mBean.getCode() == 1) {
                            mHandler.post(new Runnable() {
                                @Override
                                public void run() {
                                    mParentBean = mBean.getData();
                                    addPList();

                                    mExpandableListView.setAdapter(mMyExpandAdapter);


                                }
                            });
                        } else if (mBean.getCode() == 2) {
                            Looper.prepare();
                            Toast.makeText(MBSDetailActivity.this, mBean.getMsg() + "", Toast.LENGTH_SHORT).show();
                            Looper.loop();
                        } else {
                            Looper.prepare();
                            Toast.makeText(MBSDetailActivity.this, mBean.getMsg() + "", Toast.LENGTH_SHORT).show();
                            Looper.loop();
                        }
                    } else {
                        Looper.prepare();
                        Toast.makeText(MBSDetailActivity.this, "服务器异常", Toast.LENGTH_SHORT).show(); //请求失败
                        Looper.loop();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private HashSet<String> hashSet;

    private void setListener() {
        mExpandableListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {

            @Override
            public void onGroupExpand(int groupPosition) {
                //存取已选定的集合
                hashSet = new HashSet<String>();
            }
        });
        // ExpandableListView的Group的点击事件
        mExpandableListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {

            @Override
            public boolean onGroupClick(ExpandableListView parent, View v,
                                        int groupPosition, long id) {
                // 可以写点击后实现的功能

                return false;
            }
        });
        // ExpandableListView的child的点击事件

        mExpandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {

            @Override
            public boolean onChildClick(ExpandableListView parent, View v,
                                        int groupPosition, int childPosition, long id) {
                Map<String, String> map = childData.get(groupPosition).get(
                        childPosition);
                String childChecked = map.get("isChecked");
                if ("No".equals(childChecked)) {
                    map.put("isChecked", "Yes");
                    //hashSet.add("选定" + childPosition);

                    hashSet.add(datasets.get(PList[groupPosition]).get(childPosition).getMajname());
                } else {
                    map.put("isChecked", "No");
                    if (hashSet.contains("选定" + childPosition)) {
                        hashSet.remove("选定" + childPosition);
                    }
                }
                System.out.println("选定的长度==1" + hashSet.size());
                System.out.println("选定的长度==2" + childData.get(groupPosition).size());
                if (hashSet.size() == childData.get(groupPosition).size()) {
                    parentList.get(groupPosition).put("isGroupCheckd", "Yes");
                } else {
                    parentList.get(groupPosition).put("isGroupCheckd", "No");
                }
                mMyExpandAdapter.notifyDataSetChanged();
                return false;
            }
        });
    }

    private void openEx() {
        for (int i = 0; i < mMyExpandAdapter.getGroupCount(); i++) {
            mExpandableListView.expandGroup(i);
        }

    }

    private void closeEx() {
        for (int i = 0; i < mMyExpandAdapter.getGroupCount(); i++) {
            mExpandableListView.collapseGroup(i);
        }

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ex_open:
//                openEx();
                Log.d("pppppp-1", hashSet.toString());
                Log.d("pppppp-2", parentList.toString());
                Log.d("pppppp-3", listaaaa.toString());
                String interests = hashSet.toString();
                String removeStr = "[";
                String interStr = interests.replace(removeStr, "");
                String str2 = "]";
                String str3 = interStr.replace(str2, "");
                Log.d("pppppp-4", str3);
                break;

            case R.id.choose_true:
                // 实际在接口里跳转
//                IntentUtil.showIntent(MBSDetailActivity.this, MyMajScByGkActivity.class);
                if (hashSet.size() != 0) {

                    putMaj();
                } else {
                    Toast.makeText(this, "请选择专业", Toast.LENGTH_SHORT).show();
                }
                break;
        }

    }

    CheckBox groupBox;
    List<Map<String, String>> listaaaa;

    public class MyExpandAdapter extends BaseExpandableListAdapter {


        @Override
        public int getGroupCount() {
            if (datasets == null) {
                return 0;
            }
            return datasets.size();
        }

        @Override
        public int getChildrenCount(int groupPosition) {
            if (datasets.get(PList[groupPosition]) == null) {
                return 0;
            }
            return datasets.get(PList[groupPosition]).size();
        }

        @Override
        public Object getGroup(int groupPosition) {
            return datasets.get(PList[groupPosition]);
        }

        @Override
        public Object getChild(int groupPosition, int childPosition) {
            return datasets.get(PList[groupPosition]).get(childPosition);
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
        public View getGroupView(final int groupPosition, final boolean isExpanded, View view, ViewGroup parent) {
            if (view == null) {
                LayoutInflater inflater = (LayoutInflater) MBSDetailActivity
                        .this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                view = inflater.inflate(R.layout.item_parent_bean, null);
            }
            view.setTag(R.layout.item_parent_bean, groupPosition);
            view.setTag(R.layout.item_child_bean, -1);
            parentText = (TextView) view.findViewById(R.id.item_parent_tv);
            parentCb = (CheckBox) view.findViewById(R.id.item_parent_cb);


            final String isGroupCheckd = parentList.get(groupPosition).get("isGroupCheckd");

            if ("No".equals(isGroupCheckd)) {
                parentCb.setChecked(false);
            } else {
                parentCb.setChecked(true);
            }

            parentCb.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    groupBox = (CheckBox) v.findViewById(R.id.item_parent_cb);
                    if (!isExpanded) {
                        //展开某个group view
                        mExpandableListView.expandGroup(groupPosition);
                    } else {
                        //关闭某个group view
                        mExpandableListView.collapseGroup(groupPosition);
                    }

                    if ("No".equals(isGroupCheckd)) {
                        mExpandableListView.expandGroup(groupPosition);
                        groupBox.setChecked(true);
                        parentList.get(groupPosition).put("isGroupCheckd", "Yes");
                        for (int i = 0; i < childData.get(groupPosition).size(); i++) {

                            hashSet.add(datasets.get(PList[groupPosition]).get(i).getMajname());
                        }
                        listaaaa = childData.get(groupPosition);

                        for (Map<String, String> map : listaaaa) {
                            map.put("isChecked", "Yes");
                        }
                    } else {
                        for (int i = 0; i < childData.get(groupPosition).size(); i++) {

                            hashSet.remove(datasets.get(PList[groupPosition]).get(i).getMajname());
                        }
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


            // 设置展开和收缩的文字颜色
            if (isExpanded) {
                parentText.setTextColor(Color.parseColor("#2FD0B5"));
            } else {
                parentText.setTextColor(Color.BLACK);
            }
            parentText.setText(PList[groupPosition]);
            return view;
        }

        @Override
        public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View view, ViewGroup parent) {
            if (view == null) {
                LayoutInflater inflater = (LayoutInflater) MBSDetailActivity
                        .this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                view = inflater.inflate(R.layout.item_child_bean, null);
            }
            view.setTag(R.layout.item_parent_bean, groupPosition);
            view.setTag(R.layout.item_child_bean, childPosition);
            TextView text = (TextView) view.findViewById(R.id.item_child_tv);
            text.setText(datasets.get(PList[groupPosition]).get(childPosition).getMajname());
            childCb = (CheckBox) view.findViewById(R.id.item_child_cb);
            String isChecked = childData.get(groupPosition).get(childPosition)
                    .get("isChecked");
            if ("No".equals(isChecked)) {
                childCb.setChecked(false);
            } else {
                childCb.setChecked(true);
            }


            return view;
        }

        @Override
        public boolean isChildSelectable(int groupPosition, int childPosition) {
            return true;
        }
    }

    private void putMaj() {
        String hashSetSre = hashSet.toString();
        String interStr = hashSetSre.replace("[", "");
        String interests = interStr.replace("]", "");
        Log.d("pppppp-4", hashSet.toString());
        Log.d("pppppp-4", interests);
        String str = interests.replace(" ", "");
        Log.d("pppppp-4", str);

        FormEncodingBuilder builder = new FormEncodingBuilder();
        builder.add("schoolStatus", "浙江");
        builder.add("parYear", "2017");
        builder.add("optF", oneS);
        builder.add("optS", twoS);
        builder.add("optT", threeS);
        builder.add("interests", str);//majList.toString()
        builder.add("userId", String.valueOf(userId));
        Request request = new Request.Builder().url(Url.addMajorBySubScUrl).post(builder.build()).build();
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
                        mMBSDetailPutModel = gson.fromJson(reader, MBSDetailPutModel.class);
                        if (mMBSDetailPutModel.getCode() == 1) {
                            mHandler.post(new Runnable() {
                                @Override
                                public void run() {
                                    IntentUtil.showIntent(MBSDetailActivity.this, NewMajScActivity.class);

                                }
                            });
                        } else if (mBean.getCode() == 2) {
                            Looper.prepare();
                            Toast.makeText(MBSDetailActivity.this, mMBSDetailPutModel.getMsg() + "", Toast.LENGTH_SHORT).show();
                            Looper.loop();
                        } else {
                            Looper.prepare();
                            Toast.makeText(MBSDetailActivity.this, mMBSDetailPutModel.getMsg() + "", Toast.LENGTH_SHORT).show();
                            Looper.loop();
                        }
                    } else {
                        Looper.prepare();
                        Toast.makeText(MBSDetailActivity.this, "服务器异常", Toast.LENGTH_SHORT).show(); //请求失败
                        Looper.loop();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        hashSet.clear();


    }
}
