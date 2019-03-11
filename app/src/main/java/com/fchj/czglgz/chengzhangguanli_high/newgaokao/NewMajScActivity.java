package com.fchj.czglgz.chengzhangguanli_high.newgaokao;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.fchj.czglgz.chengzhangguanli_high.R;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NewMajScActivity extends Activity implements View.OnClickListener {
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
    private NewMyMajScAdapter mNewMyMajScAdapter;
    private TextView chooseAllTv;
    private ImageView chooseDeleteTv;
    private Map<Integer, Boolean> map;
    public Map<Integer, Integer> allMajMap = new HashMap<>();
    public Map<Integer, Integer> saveMap;
    private String str1;
    private String str2;
    private String str3;
    private String majId;
    private MyMajDeleteModel mMyMajDeleteModel;
    private List<Integer> allMajId = new ArrayList<>();
    private List<Integer> saveMajId;
    private int count = 0;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_maj_sc_gk);
        mHandler = new Handler();
        usernameSp = getSharedPreferences("user_npt", MODE_PRIVATE);
        userId = usernameSp.getInt("userId", 0);
        saveMap = new HashMap<>();
        saveMajId = new ArrayList<>();
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
        chooseAllTv = (TextView) findViewById(R.id.choose_all_tv);
        chooseAllTv.setOnClickListener(this);
        chooseDeleteTv = (ImageView) findViewById(R.id.choose_delete_img);
        chooseDeleteTv.setOnClickListener(this);
        mMyMajListview = (ListView) findViewById(R.id.my_maj_listview);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.my_maj_choose_btn:
//                Toast.makeText(this, "allMajId.size():" + allMajId.size(), Toast.LENGTH_SHORT).show();
                if (saveMajId.size() > 0 && saveMajId.size() < 21) {
                    Intent toPieDetail = new Intent(this, MajChooseBestActivity.class);
                    toPieDetail.putExtra("majId", majId);
                    startActivity(toPieDetail);
                } else {
                    Toast.makeText(this, "请选择1～20个专业", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.choose_all_tv:
                if (chooseAllTv.getText().toString().trim().equals("全选")) {
                    for (int i = 0; i < mData.size(); i++) {
                        //allMajId.add(mData.get(i).getMaiid());
                        mData.get(i).setCheck(true);
                        saveMajId.add(mData.get(i).getMaiid());
                        for (int j = 0; j < saveMajId.size(); j++) {
                            if (saveMajId.get(j) == mData.get(j).getMaiid()) {
                                saveMajId.remove(j);
                            }
                        }
                    }

                    mNewMyMajScAdapter.notifyDataSetChanged();
                    mHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            mMyMajTvNum.setText(saveMajId.size() + "");
                        }
                    });
                    chooseAllTv.setText("全不选");


                } else {
                    for (int i = 0; i < mData.size(); i++) {
                        //allMajId.add(mData.get(i).getMaiid());
                        mData.get(i).setCheck(false);

                    }
                    saveMajId.clear();
                    mNewMyMajScAdapter.notifyDataSetChanged();
                    mHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            mMyMajTvNum.setText(saveMajId.size() + "");
                        }
                    });
                    chooseAllTv.setText("全选");


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
                Toast.makeText(NewMajScActivity.this, "服务器异常", Toast.LENGTH_LONG).show();
                Looper.loop();

            }

            @Override
            public void onResponse(Response response) {
                try {
                    if (response.isSuccessful()) {
                        final String body = response.body().string();
                        Log.d("home_zx", body);
                        Gson gson = new Gson();
                        JsonReader reader = new JsonReader(new StringReader(body));
                        mMyMajScListModel = gson.fromJson(reader, MyMajScListModel.class);
                        if (mMyMajScListModel.getCode() == 1) {

                            mHandler.post(new Runnable() {
                                @Override
                                public void run() {
                                    mData = mMyMajScListModel.getData();
                                    mNewMyMajScAdapter = new NewMyMajScAdapter(mData);
                                    mMyMajTvAllSize.setText(" / " + mData.size());
                                    mMyMajListview.setAdapter(mNewMyMajScAdapter);


                                }
                            });
                        } else if (mMyMajScListModel.getCode() == 2) {
                            Looper.prepare();
                            Toast.makeText(NewMajScActivity.this, mMyMajScListModel.getMsg() + "", Toast.LENGTH_SHORT).show();
                            Looper.loop();
                        } else {
                            Looper.prepare();
                            Toast.makeText(NewMajScActivity.this, mMyMajScListModel.getMsg() + "", Toast.LENGTH_SHORT).show();
                            Looper.loop();
                        }
                    } else {
                        Looper.prepare();
                        Toast.makeText(NewMajScActivity.this, "服务器异常", Toast.LENGTH_SHORT).show(); //请求失败
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
                Toast.makeText(NewMajScActivity.this, "服务器异常", Toast.LENGTH_LONG).show();
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
                                    Toast.makeText(NewMajScActivity.this, mMyMajDeleteModel.getMsg() + "", Toast.LENGTH_SHORT).show();

                                }
                            });

                        } else if (mMyMajDeleteModel.getCode() == 2) {
                            Looper.prepare();
                            Toast.makeText(NewMajScActivity.this, mMyMajDeleteModel.getMsg() + "", Toast.LENGTH_SHORT).show();
                            Looper.loop();
                        } else {
                            Looper.prepare();
                            Toast.makeText(NewMajScActivity.this, mMyMajDeleteModel.getMsg() + "", Toast.LENGTH_SHORT).show();
                            Looper.loop();
                        }
                    } else {
                        Looper.prepare();
                        Toast.makeText(NewMajScActivity.this, "服务器异常", Toast.LENGTH_SHORT).show(); //请求失败
                        Looper.loop();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        });
    }

    public class NewMyMajScAdapter extends BaseAdapter {
        private List<MyMajScListModel.DataBean> mData;
        public List<Integer> seleteds;
        int count = 1;

        public NewMyMajScAdapter(List<MyMajScListModel.DataBean> data) {
            this.mData = data;
            seleteds = new ArrayList<>();
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
            String str8 = str.replace(" ", "");//去掉所用空格
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
                    Log.d("position", "save->>" + saveMajId);
                    if (mData.get(position).isCheck) {
                        mData.get(position).setCheck(false);
                        Toast.makeText(NewMajScActivity.this, "选择1", Toast.LENGTH_SHORT).show();
//                        for (Integer integer : saveMajId) {
//                            saveMajId.remove(integer);
//                        }
                        for (int i = 0; i < saveMajId.size(); i++) {
                            if (saveMajId.get(i) == mData.get(position).getMaiid()) {
                                saveMajId.remove(i);
                            }
                        }
                        mHandler.post(new Runnable() {
                            @Override
                            public void run() {
                                mMyMajTvNum.setText(saveMajId.size() + "");
                            }
                        });
                        str1 = saveMajId.toString();
                        str2 = str1.replace("[", "");
                        str3 = str2.replace("]", "");
                        majId = str3.replace(" ", "");
                        Log.d("position", "save-delete>>" + majId);
                        Log.d("position", "save-delete>>" + saveMajId);
                    } else {
                        mData.get(position).setCheck(true);
                        saveMajId.add(mData.get(position).getMaiid());
                        mHandler.post(new Runnable() {
                            @Override
                            public void run() {
                                mMyMajTvNum.setText(saveMajId.size() + "");
                            }
                        });
                        str1 = saveMajId.toString();
                        str2 = str1.replace("[", "");
                        str3 = str2.replace("]", "");
                        majId = str3.replace(" ", "");
                        Log.d("position", "save-delete>>" + majId);
                        Log.d("position", "save-add>>" + saveMajId);
                        Toast.makeText(NewMajScActivity.this, "选择", Toast.LENGTH_SHORT).show();
                    }
                    notifyDataSetChanged();
                }
            });

            if (mData.get(position).isCheck) {
                holder.mImg.setImageResource(R.mipmap.xuanze);
            } else {
                holder.mImg.setImageResource(R.mipmap.weixuan);

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

    @Override
    protected void onResume() {
        super.onResume();

        saveMajId.clear();
    }
}
