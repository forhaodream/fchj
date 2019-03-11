package com.fchj.czglgz.chengzhangguanli_high.newgaokao;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

//import com.don.pieviewlibrary.PercentPieView;
import com.fchj.czglgz.chengzhangguanli_high.R;
import com.fchj.czglgz.chengzhangguanli_high.adapter.PopupShengAdapter;
import com.fchj.czglgz.chengzhangguanli_high.util.DensityUtils;
import com.fchj.czglgz.chengzhangguanli_high.util.IntentUtil;
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
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class MajorBySubActivity extends Activity implements MyPieChart.OnItemClickListener, View.OnClickListener {

    private int sum = 0;
    private TextView sumTv;
    private MyPieChart mMyPieChart;
    private List<MyPieChart.PieEntry> pieEntries;
    private TextView mSubTitleTwo;
    private TextView mSubTitleOne;
    private TextView mSubTitleThree;
    public WindowManager.LayoutParams lp;
    private Handler mHandler;
    private SubListModel mSubListModel;
    private List<SubListModel.DataBean> mSubData;
    private ListView mListView;
    private SubListAdapter mSubListAdapter;
    private List<Integer> colors;
    private String oneS, twoS, threeS;
    private String[] a = new String[]{"物理", "化学", "生物", "历史", "政治", "地理", "技术"};
    private List<String> arrayList;
    private GKPopupAdapter mGKPopupAdapter;
    private ImageView returnImg;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_major_by_sub);
        mHandler = new Handler();
        Intent intent = getIntent();
        oneS = intent.getStringExtra("subname1");
        twoS = intent.getStringExtra("subname2");
        threeS = intent.getStringExtra("subname3");
        // 布局
        initView();
        // 数组
        addSubList();
        // 添加颜色
        addColors();
        getMajBySub();
    }


    private void addSubList() {
        List<String> list = Arrays.asList(a);//将数组转换为list集合
        if (list.contains(oneS) || list.contains(twoS) || list.contains(threeS)) {//加入集合中包含这个元素
            arrayList = new ArrayList<String>(list);//转换为ArrayLsit调用相关的remove方法
            arrayList.remove(oneS);
            arrayList.remove(twoS);
            arrayList.remove(threeS);
        }
    }

    private void initView() {
        returnImg = (ImageView) findViewById(R.id.title_fanhui);
        returnImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        sumTv = (TextView) findViewById(R.id.pie_sum);
        mSubTitleOne = (TextView) findViewById(R.id.sub_title_one);
        mSubTitleOne.setText(oneS);
        mSubTitleOne.setOnClickListener(this);
        mSubTitleTwo = (TextView) findViewById(R.id.sub_title_two);
        mSubTitleTwo.setText(twoS);
        mSubTitleTwo.setOnClickListener(this);
        mSubTitleThree = (TextView) findViewById(R.id.sub_title_three);
        mSubTitleThree.setText(threeS);
        mSubTitleThree.setOnClickListener(this);
        mListView = (ListView) findViewById(R.id.maj_by_sub_lv);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent toMBSDetail = new Intent(MajorBySubActivity.this, MBSDetailActivity.class);
                toMBSDetail.putExtra("maj_name", mSubData.get(position).getKindname());
                toMBSDetail.putExtra("one_S", oneS);
                toMBSDetail.putExtra("two_S", twoS);
                toMBSDetail.putExtra("three_S", threeS);
                startActivity(toMBSDetail);
            }
        });
        mMyPieChart = (MyPieChart) findViewById(R.id.pieView);
        mMyPieChart.setRadius(DensityUtils.dp2px(this, 80));
        mMyPieChart.setOnItemClickListener(this);
        pieEntries = new ArrayList<>();
    }


    private void addPieChart() {
        for (int i = 0; i < mSubData.size(); i++) {
            if (Float.valueOf(mSubData.get(i).getKindpercent()) > 1) {
                pieEntries.add(new MyPieChart.PieEntry(mSubData.get(i).getKindsnum(), colors.get(i), true));
            }
        }
        mMyPieChart.requestLayout();
        mMyPieChart.invalidate();
        mMyPieChart.setPieEntries(pieEntries);
    }

    @Override
    public void onItemClick(int position) {
        Toast.makeText(this, "共有" + position + "个专业匹配", Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.sub_title_one:
                onePopup(mSubTitleOne);
                break;
            case R.id.sub_title_two:
                twoPopup(mSubTitleTwo);
                break;
            case R.id.sub_title_three:
                threePopup(mSubTitleThree);
                break;
        }
    }

    PopupShengAdapter mPopupShengAdapter;


    private void onePopup(TextView textView) {

        final View popView = View.inflate(this, R.layout.popupwindow_sub, null);
        ListView subLv = (ListView) popView.findViewById(R.id.popup_sub_lv);
        mGKPopupAdapter = new GKPopupAdapter(arrayList);
        subLv.setAdapter(mGKPopupAdapter);

        //获取屏幕宽高
        int weight = getResources().getDisplayMetrics().widthPixels * 20 / 100;
        int height = getResources().getDisplayMetrics().heightPixels * 1 / 5;
        final PopupWindow popupWindow = new PopupWindow(popView, weight, height);//RelativeLayout.LayoutParams.WRAP_CONTENT
        //   popupWindow.setAnimationStyle(R.style.anim_popup_dir);
        popupWindow.setFocusable(true);
        //点击外部popueWindow消失
        popupWindow.setOutsideTouchable(true);
        subLv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                final String oneStr = arrayList.get(position).toString();
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        mSubTitleOne.setText(oneStr);
                        oneS = oneStr;
                        addSubList();
                        mMyPieChart.invalidate();
                        pieEntries.clear();
                        getMajBySub();
                    }
                });
                popupWindow.dismiss();
            }
        });
        lp = getWindow().getAttributes();
        lp.alpha = 0.9f;
        getWindow().setAttributes(lp);
        popupWindow.showAsDropDown(textView, -15, 30);//, -15, 0
        popupWindow.setOnDismissListener(new poponDismissListener());

    }


    private void twoPopup(TextView textView) {

        final View popView = View.inflate(this, R.layout.popupwindow_sub, null);
        ListView subLv = (ListView) popView.findViewById(R.id.popup_sub_lv);
        mGKPopupAdapter = new GKPopupAdapter(arrayList);
        subLv.setAdapter(mGKPopupAdapter);

        //获取屏幕宽高
        int weight = getResources().getDisplayMetrics().widthPixels * 20 / 100;
        int height = getResources().getDisplayMetrics().heightPixels * 1 / 5;
        final PopupWindow popupWindow = new PopupWindow(popView, weight, height);//RelativeLayout.LayoutParams.WRAP_CONTENT
        //   popupWindow.setAnimationStyle(R.style.anim_popup_dir);
        popupWindow.setFocusable(true);
        //点击外部popueWindow消失
        popupWindow.setOutsideTouchable(true);
        subLv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                final String twoStr = arrayList.get(position).toString();
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        mSubTitleTwo.setText(twoStr);
                        twoS = twoStr;
                        addSubList();
                        mMyPieChart.invalidate();
                        pieEntries.clear();
                        getMajBySub();
                    }
                });
                popupWindow.dismiss();
            }
        });
        lp = getWindow().getAttributes();
        lp.alpha = 0.9f;
        getWindow().setAttributes(lp);
        popupWindow.showAsDropDown(textView, -15, 30);//, -15, 0
        popupWindow.showAsDropDown(textView);
        popupWindow.setOnDismissListener(new poponDismissListener());

    }

    private void threePopup(TextView textView) {

        final View popView = View.inflate(this, R.layout.popupwindow_sub, null);
        ListView subLv = (ListView) popView.findViewById(R.id.popup_sub_lv);
        mGKPopupAdapter = new GKPopupAdapter(arrayList);
        subLv.setAdapter(mGKPopupAdapter);

        //获取屏幕宽高
        int weight = getResources().getDisplayMetrics().widthPixels * 20 / 100;
        int height = getResources().getDisplayMetrics().heightPixels * 1 / 5;
        final PopupWindow popupWindow = new PopupWindow(popView, weight, height);//RelativeLayout.LayoutParams.WRAP_CONTENT
        //   popupWindow.setAnimationStyle(R.style.anim_popup_dir);
        popupWindow.setFocusable(true);
        //点击外部popueWindow消失
        popupWindow.setOutsideTouchable(true);
        subLv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                final String threeStr = arrayList.get(position).toString();
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        mSubTitleThree.setText(threeStr);
                        threeS = threeStr;
                        addSubList();
                        mMyPieChart.invalidate();
                        pieEntries.clear();
                        getMajBySub();
                    }
                });
                popupWindow.dismiss();
            }
        });
        lp = getWindow().getAttributes();
        lp.alpha = 0.9f;
        getWindow().setAttributes(lp);
        popupWindow.showAsDropDown(textView, -15, 30);//, -15, 0
        popupWindow.showAsDropDown(textView);
        popupWindow.setOnDismissListener(new poponDismissListener());

    }

    class poponDismissListener implements PopupWindow.OnDismissListener {

        @Override
        public void onDismiss() {
            backgroundAlpha(1f);
        }

    }

    public void backgroundAlpha(float bgAlpha) {
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.alpha = bgAlpha; //0.0-1.0
        getWindow().setAttributes(lp);
    }

    private void getMajBySub() {
        FormEncodingBuilder builder = new FormEncodingBuilder();
        builder.add("schoolStatus", "浙江");
        builder.add("parYear", "2017");
        builder.add("optF", oneS);
        builder.add("optS", twoS);
        builder.add("optT", threeS);
        Request request = new Request.Builder().url(Url.majBySubUrl).post(builder.build()).build();
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
                        mSubListModel = gson.fromJson(reader, SubListModel.class);
                        if (mSubListModel.getCode() == 1) {
                            mHandler.post(new Runnable() {
                                @Override
                                public void run() {
                                    mSubData = mSubListModel.getData();
                                    mSubListAdapter = new SubListAdapter(mSubData, colors);
                                    mListView.setAdapter(mSubListAdapter);
                                    sumTv.setText(mSubListModel.getValue() + "");
                                    for (int i = 0; i < mSubData.size(); i++) {
                                        if (Float.valueOf(mSubData.get(i).getKindpercent()) > 1) {
                                            pieEntries.add(new MyPieChart.PieEntry(mSubData.get(i).getKindsnum(), colors.get(i), true));
                                        }
                                    }
                                    mMyPieChart.requestLayout();
                                    mMyPieChart.invalidate();

                                    mMyPieChart.setPieEntries(pieEntries);
//                                    pieEntries.clear();
//                                    addPieChart();


                                }
                            });
                        } else if (mSubListModel.getCode() == 2) {
                            Looper.prepare();
                            Toast.makeText(MajorBySubActivity.this, mSubListModel.getMsg() + "", Toast.LENGTH_SHORT).show();
                            Looper.loop();
                        } else {
                            Looper.prepare();
                            Toast.makeText(MajorBySubActivity.this, mSubListModel.getMsg() + "", Toast.LENGTH_SHORT).show();
                            Looper.loop();
                        }
                    } else {
                        Looper.prepare();
                        Toast.makeText(MajorBySubActivity.this, "服务器异常", Toast.LENGTH_SHORT).show(); //请求失败
                        Looper.loop();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void addColors() {
        colors = new ArrayList<>();
        colors.add(R.color.color_1);
        colors.add(R.color.color_2);
        colors.add(R.color.color_3);
        colors.add(R.color.color_4);
        colors.add(R.color.color_5);
        colors.add(R.color.color_6);
        colors.add(R.color.color_7);
        colors.add(R.color.color_8);
        colors.add(R.color.color_9);
        colors.add(R.color.color_10);
        colors.add(R.color.color_11);
        colors.add(R.color.color_12);
        colors.add(R.color.color_13);
        colors.add(R.color.color_14);
        colors.add(R.color.color_15);
        colors.add(R.color.color_16);
        colors.add(R.color.color_17);
        colors.add(R.color.color_18);
        colors.add(R.color.color_19);
        colors.add(R.color.color_20);
        colors.add(R.color.color_21);
        colors.add(R.color.color_22);
        colors.add(R.color.color_23);
        colors.add(R.color.color_24);
        colors.add(R.color.color_25);
        colors.add(R.color.color_26);
        colors.add(R.color.color_27);
        colors.add(R.color.color_28);
        colors.add(R.color.color_29);
        colors.add(R.color.color_30);
        colors.add(R.color.color_31);

    }
}
