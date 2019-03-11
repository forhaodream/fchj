package com.fchj.czglgz.chengzhangguanli_high.newgaokao;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.ActionMode;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.fchj.czglgz.chengzhangguanli_high.R;
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
import com.zhy.autolayout.AutoRelativeLayout;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class MyMajScByGkActivity extends Activity implements View.OnClickListener {
    private ImageView mTitleFanhui;
    private TextView mTitleText;
    private ListView mMyMajListview;
    private TextView mMyMajTvNum;
    private TextView mMyMajTvAllSize;
    private Button mMyMajChooseBtn;
    private MyMajScListModel mMyMajScListModel;
    private List<MyMajScListModel.DataBean> mData;
    private Handler mHandler;
    private SharedPreferences usernameSp;
    private int userId;
    private MyMajScByGkAdapter mMyMajScByGkAdapter;
    private TextView chooseAllTv;
    private ImageView chooseDeleteTv;
    private Map<Integer, Boolean> map;
    public Map<Integer, Boolean> isCheckMap = new HashMap<>();
    public Map<Integer, Integer> saveMap;
    private String str1;
    private String str2;
    private String str3;
    private String majId;
    private MyMajDeleteModel mMyMajDeleteModel;
    private List<Integer> allMajId = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_maj_sc_gk);
        mHandler = new Handler();
        usernameSp = getSharedPreferences("user_npt", MODE_PRIVATE);
        userId = usernameSp.getInt("userId", 0);
        saveMap = new HashMap<>();
        initView();
        addData();
        Log.d("mdatasize", "saveMap.size---->" + saveMap.size());
    }

    private void initView() {
        mTitleFanhui = (ImageView) findViewById(R.id.title_fanhui);
        mTitleFanhui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        mTitleText = (TextView) findViewById(R.id.title_text);
        mMyMajTvNum = (TextView) findViewById(R.id.my_maj_tv_num);
        mMyMajTvAllSize = (TextView) findViewById(R.id.my_maj_tv_all_size);
        mMyMajChooseBtn = (Button) findViewById(R.id.my_maj_choose_btn);
        mMyMajChooseBtn.setOnClickListener(this);
        mMyMajListview = (ListView) findViewById(R.id.my_maj_listview);
        chooseAllTv = (TextView) findViewById(R.id.choose_all_tv);
        chooseAllTv.setOnClickListener(this);
        chooseDeleteTv = (ImageView) findViewById(R.id.choose_delete_img);
        chooseDeleteTv.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.my_maj_choose_btn:
                if (saveMap.size() > 0 && saveMap.size() < 21) {
                    Intent toPieDetail = new Intent(MyMajScByGkActivity.this, MajChooseBestActivity.class);
                    toPieDetail.putExtra("majId", majId);
                    startActivity(toPieDetail);
                } else {
                    Toast.makeText(this, "请选择1～20个专业", Toast.LENGTH_SHORT).show();
                }

                // Intent toDetail = new Intent(MyMajScByGkActivity.this, MajChooseBestActivity.class);


//                IntentUtil.showIntent(MyMajScByGkActivity.this, MajChooseBestActivity.class);
                // Toast.makeText(this, "map--->" + map.size(), Toast.LENGTH_SHORT).show();

                break;
            case R.id.choose_all_tv:
                if (chooseAllTv.getText().toString().trim().equals("全选")) {
                Toast.makeText(this, "map_..>" + saveMap.size(), Toast.LENGTH_SHORT).show();
                for (int i = 0; i < mData.size(); i++) {
                    allMajId.add(mData.get(i).getMaiid());
                }
                // 所有项目全部选中
                mMyMajScByGkAdapter.configCheckMap(true);

                mMyMajScByGkAdapter.notifyDataSetChanged();

                chooseAllTv.setText("全不选");
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        mMyMajTvNum.setText(saveMap.size() + "");
                        Log.d("mdatasize", "saveMap.size---->" + saveMap.size());
                        Log.d("mdatasize", "data.size" + mData.size());
                        Log.d("mdatasize", "savemap.size" + saveMap.size());
                    }
                });

            } else {

                // 所有项目全部不选中
                mMyMajScByGkAdapter.configCheckMap(false);

                mMyMajScByGkAdapter.notifyDataSetChanged();
                saveMap.clear();

                chooseAllTv.setText("全选");
                saveIsCheckMap.clear();
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        mMyMajTvNum.setText(saveMap.size() + "");
                    }
                });
            }
                break;
            case R.id.choose_delete_img:
                if (saveMap.size() > 0) {
                    map = mMyMajScByGkAdapter.getCheckMap();

                    int count = mMyMajScByGkAdapter.getCount();
                    for (int i = 0; i < count; i++) {
                        int postition = i - (count - mMyMajScByGkAdapter.getCount());
                        if (map.get(i) != null && map.get(i)) {
                            MyMajScListModel.DataBean mDataBean = (MyMajScListModel.DataBean) mMyMajScByGkAdapter.getItem(postition);

                            if (mDataBean.isCanRemove()) {
                                mMyMajScByGkAdapter.getCheckMap().remove(i);
                                mMyMajScByGkAdapter.remove(postition);
                                deleteMaj();
                            } else {
                                map.put(postition, false);
                            }
                        }

                    }
                    mMyMajScByGkAdapter.notifyDataSetChanged();

                } else {
                    Toast.makeText(this, "请至少选择一个专业", Toast.LENGTH_SHORT).show();
                }

                break;
        }
    }

    private void addData() {
        FormEncodingBuilder builder = new FormEncodingBuilder();
        builder.add("userId", String.valueOf(userId)); //String.valueOf(userId)
        Request request = new Request.Builder().url(Url.myMajScListUrl).post(builder.build()).build();
        OkHttpClient okHttpClient = new OkHttpClient();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {
                Looper.prepare();
                Toast.makeText(MyMajScByGkActivity.this, "服务器异常", Toast.LENGTH_LONG).show();
                Looper.loop();

            }

            @Override
            public void onResponse(Response response) {
                try {
                    if (response.isSuccessful()) {
                        String body = response.body().string();
                        Log.d("home_zx", body);
                        Gson gson = new Gson();
                        JsonReader reader = new JsonReader(new StringReader(body));
                        mMyMajScListModel = gson.fromJson(reader, MyMajScListModel.class);
                        if (mMyMajScListModel.getCode() == 1) {

                            mHandler.post(new Runnable() {
                                @Override
                                public void run() {
                                    mData = mMyMajScListModel.getData();
                                    mMyMajScByGkAdapter = new MyMajScByGkAdapter(mData);
                                    mMyMajListview.setAdapter(mMyMajScByGkAdapter);
                                    mMyMajTvAllSize.setText(" / " + mData.size());

                                }
                            });
                        } else if (mMyMajScListModel.getCode() == 2) {
                            Looper.prepare();
                            Toast.makeText(MyMajScByGkActivity.this, mMyMajScListModel.getMsg() + "", Toast.LENGTH_SHORT).show();
                            Looper.loop();
                        } else {
                            Looper.prepare();
                            Toast.makeText(MyMajScByGkActivity.this, mMyMajScListModel.getMsg() + "", Toast.LENGTH_SHORT).show();
                            Looper.loop();
                        }
                    } else {
                        Looper.prepare();
                        Toast.makeText(MyMajScByGkActivity.this, "服务器异常", Toast.LENGTH_SHORT).show(); //请求失败
                        Looper.loop();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        });
    }

    private void deleteMaj() {
        FormEncodingBuilder builder = new FormEncodingBuilder();
        builder.add("maisid", majId);
        Request request = new Request.Builder().url(Url.deleteMajListUrl).post(builder.build()).build();
        OkHttpClient okHttpClient = new OkHttpClient();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {
                Looper.prepare();
                Toast.makeText(MyMajScByGkActivity.this, "服务器异常", Toast.LENGTH_LONG).show();
                Looper.loop();

            }

            @Override
            public void onResponse(Response response) {
                try {
                    if (response.isSuccessful()) {
                        String body = response.body().string();
                        Log.d("home_zx", body);
                        Gson gson = new Gson();
                        JsonReader reader = new JsonReader(new StringReader(body));
                        mMyMajDeleteModel = gson.fromJson(reader, MyMajDeleteModel.class);
                        if (mMyMajDeleteModel.getCode() == 1) {
                            mHandler.post(new Runnable() {
                                @Override
                                public void run() {
                                    mMyMajTvNum.setText("0");
                                    mMyMajTvAllSize.setText(" / " + mData.size());
                                    Toast.makeText(MyMajScByGkActivity.this, mMyMajDeleteModel.getMsg() + "", Toast.LENGTH_SHORT).show();

                                }
                            });

                        } else if (mMyMajDeleteModel.getCode() == 2) {
                            Looper.prepare();
                            Toast.makeText(MyMajScByGkActivity.this, mMyMajDeleteModel.getMsg() + "", Toast.LENGTH_SHORT).show();
                            Looper.loop();
                        } else {
                            Looper.prepare();
                            Toast.makeText(MyMajScByGkActivity.this, mMyMajDeleteModel.getMsg() + "", Toast.LENGTH_SHORT).show();
                            Looper.loop();
                        }
                    } else {
                        Looper.prepare();
                        Toast.makeText(MyMajScByGkActivity.this, "服务器异常", Toast.LENGTH_SHORT).show(); //请求失败
                        Looper.loop();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        });
    }

    private Map<Integer, Boolean> saveIsCheckMap = new HashMap<>();// 存放已被选中的CheckBox

    class MyMajScByGkAdapter extends BaseAdapter {
        private List<MyMajScListModel.DataBean> mData;
        private com.fchj.czglgz.chengzhangguanli_high.newgaokao.MyMajScByGkAdapter.onClickMyTextView onClickMyTextView;

        public MyMajScByGkAdapter(List<MyMajScListModel.DataBean> data) {
            this.mData = data;
            configCheckMap(false);
        }

        public void configCheckMap(boolean bool) {
            for (int i = 0; i < mData.size(); i++) {
                isCheckMap.put(i, bool);


            }
        }

        public void getAllMap(int majidaaa) {
            for (int i = 0; i < mData.size(); i++) {
                saveMap.put(majidaaa, i);
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
            final String str = mData.get(position).getAbtsubject();//根据逗号分隔到List数组中
            String str9 = str.replace(" ", "");//去掉所用空格
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

            final MyMajScListModel.DataBean mDataBean = mData.get(position);
            boolean canRemove = mDataBean.isCanRemove();
            holder.mCheckBox.setOnCheckedChangeListener(null);
            holder.mCheckBox.setChecked(mDataBean.isCheck);
            final ViewHolder finalHolder = holder;
            holder.mCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (finalHolder.mCheckBox.isChecked()) {
                        saveIsCheckMap.put(position, true);
                        saveMap.put(position, mData.get(position).getMaiid());
                        Log.d("save_map", mData.get(position).getMaiid() + "");
                        Log.d("save_map", saveMap.size() + "");
                        mHandler.post(new Runnable() {
                            @Override
                            public void run() {
                                mMyMajTvNum.setText(saveMap.size() + "");
                            }
                        });
                        List<Integer> qqqq = new ArrayList<>();
                        for (Integer s2 : saveMap.values()) {//遍历map的值
                            Log.d("值value ：", "" + s2);
                            qqqq.add(s2);
                            Log.d("s2", String.valueOf(qqqq));// 去掉[]
                        }
                        str1 = qqqq.toString();
                        str2 = str1.replace("[", "");
                        str3 = str2.replace("]", "");
                        majId = str3.replace(" ", "");

                        Log.d("pppppp009", majId);
//                    saveData.add(mData.get(position).getMaiid());

                    } else {
                        saveIsCheckMap.remove(position);
                        saveMap.remove(position);
                        mHandler.post(new Runnable() {
                            @Override
                            public void run() {
                                mMyMajTvNum.setText(saveMap.size() + "");
                            }
                        });
                        List<Integer> qqqq = new ArrayList<>();
                        for (Integer s2 : saveMap.values()) {//遍历map的值
                            Log.d("值value ：", "" + s2);
                            qqqq.add(s2);
                            Log.d("s2", String.valueOf(qqqq));// 去掉[]
                        }
                        str1 = qqqq.toString();
                        str2 = str1.replace("[", "");
                        str3 = str2.replace("]", "");
                        majId = str3.replace(" ", "");

                        Log.d("pppppp009", majId);
                    }


                    isCheckMap.put(position, isChecked);
                }
            });

            if (!canRemove) {
                holder.mCheckBox.setVisibility(View.GONE);
                holder.mCheckBox.setChecked(false);

            } else {
                holder.mCheckBox.setVisibility(View.VISIBLE);
                if (isCheckMap.get(position) == null) {
                    isCheckMap.put(position, false);
                }
                holder.mCheckBox.setChecked(isCheckMap.get(position));
            }
            if (saveIsCheckMap != null && saveIsCheckMap.containsKey(position)) {
                holder.mCheckBox.setChecked(true);
            } else {
                holder.mCheckBox.setChecked(false);
            }

            return convertView;
        }

//        //接口回调
//        public interface onClickMyTextView {
//            public void myTextViewClick(Map<Integer, Integer> map);// Map<Integer, Boolean> map
//        }

        public void setOnClickMyTextView
                (com.fchj.czglgz.chengzhangguanli_high.newgaokao.MyMajScByGkAdapter.onClickMyTextView
                         onClickMyTextView) {
            this.onClickMyTextView = onClickMyTextView;
        }

        public class ViewHolder {
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
            return isCheckMap;
        }

        public List<MyMajScListModel.DataBean> getData() {
            return mData;
        }

        public void checkNum(int num) {

        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        saveMap.clear();
        isCheckMap.clear();

    }
}
