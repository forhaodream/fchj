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
import java.util.List;

public class MajChooseBestActivity extends Activity implements View.OnClickListener, MyPieChart.OnItemClickListener {
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
    private BestListAdapter mBestListAdapter;
    private List<Integer> colors;
    private String oneS, twoS, threeS;
    private String[] a = new String[]{"物理", "化学", "生物", "历史", "政治", "地理", "技术"};
    private List<String> arrayList;
    private GKPopupAdapter mGKPopupAdapter;
    private ImageView returnImg;
//    private PercentPieView mPercentPieView;
    private int[] coloraaa;
    private String majId;
    private BestMajModel mBestMajModel;
    private List<BestMajModel.DataBean> mBestData;
    List<String> aaaaa = new ArrayList<>();
    List<Integer> dddd = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_best_maj_choose);
        mHandler = new Handler();
        Intent intent = getIntent();
        majId = intent.getStringExtra("majId");
        oneS = "物理";
        twoS = "化学";
        threeS = "生物";
        // 布局
        //mPercentPieView = (PercentPieView) findViewById(R.id.pieView2);
        addColorsOld();
        initView();
        // 数组
        addSubList();
        // 添加颜色
//        addColors();
        dddd.add(10);
        dddd.add(10);
        dddd.add(10);
        dddd.add(40);
        aaaaa.add("兄弟");
        aaaaa.add("兄弟");
        aaaaa.add("兄弟");
        aaaaa.add("兄弟");
        getMajBySub();
        coloraaa = new int[]{
                getResources().getColor(R.color.color_1)
                , getResources().getColor(R.color.color_2)
                , getResources().getColor(R.color.color_3)
//                , getResources().getColor(R.color.color_4)
//                , getResources().getColor(R.color.color_5)
//                , getResources().getColor(R.color.color_6)
//                , getResources().getColor(R.color.color_7)
//                , getResources().getColor(R.color.color_8)
//                , getResources().getColor(R.color.color_9)
//                , getResources().getColor(R.color.color_10)
//                , getResources().getColor(R.color.color_11)
//                , getResources().getColor(R.color.color_12)
//                , getResources().getColor(R.color.color_13)
//                , getResources().getColor(R.color.color_14)
//                , getResources().getColor(R.color.color_15)
//                , getResources().getColor(R.color.color_16)
//                , getResources().getColor(R.color.color_17)
//                , getResources().getColor(R.color.color_18)
//                , getResources().getColor(R.color.color_19)
//                , getResources().getColor(R.color.color_20)
//                , getResources().getColor(R.color.color_21)
//                , getResources().getColor(R.color.color_22)
//                , getResources().getColor(R.color.color_23)
//                , getResources().getColor(R.color.color_24)
//                , getResources().getColor(R.color.color_25)
//                , getResources().getColor(R.color.color_26)
//                , getResources().getColor(R.color.color_27)
//                , getResources().getColor(R.color.color_28)
//                , getResources().getColor(R.color.color_29)
//                , getResources().getColor(R.color.color_30)
                , getResources().getColor(R.color.color_31)};
        initView();


        String[] name = new String[]{"兄弟", "姐妹", "情侣", "基友"};
        List<Integer> color = new ArrayList<>();

        color.add(getResources().getColor(R.color.color_11));
        color.add(getResources().getColor(R.color.color_11));
        color.add(getResources().getColor(R.color.color_11));
        color.add(getResources().getColor(R.color.color_11));


        ;


//        List<String> aaaa = new ArrayList<>();
//        for (int i = 0; i < mBestData.size(); i++) {
//            aaaa.add(mBestData.get(i).getMajors().getMajorname());
//        }
//        List<Integer> bbbb = new ArrayList<>();
//        List<Float> cccc = new ArrayList<>();
//        for (int i = 0; i < mBestData.size(); i++) {
//            Float ffff = Float.valueOf(mBestData.get(i).getMajpercent());
//            double aaaaa = Math.ceil(Double.parseDouble(mBestData.get(i).getMajpercent()));
//            bbbb.add((int) aaaaa);
//        }
//        for (int i = 0; i < mBestData.size(); i++) {
//            //mPercentPieView.setData(bbbb, aaaa, coloraaa);
//            mPercentPieView.setData(dddd, aaaa, coloraaa);
//
//        }
        // mPercentPieView.setData(dddd, aaaa, coloraaa);

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
                Intent toMBSDetail = new Intent(MajChooseBestActivity.this, NGKMajDetailActivity.class);
                toMBSDetail.putExtra("maj_name", mBestData.get(position).getMajors().getMajorname());
                toMBSDetail.putExtra("one_S", oneS);
                toMBSDetail.putExtra("two_S", twoS);
                toMBSDetail.putExtra("three_S", threeS);
                toMBSDetail.putExtra("title", mBestData.get(position).getInterestmajor());
                toMBSDetail.putExtra("tv_1", mBestData.get(position).getMajors().getMajorgenre());
                toMBSDetail.putExtra("tv_2", mBestData.get(position).getMajors().getCoursekind());
                toMBSDetail.putExtra("tv_3", mBestData.get(position).getMajors().getCoursetype());

                startActivity(toMBSDetail);
            }
        });
        mMyPieChart = (MyPieChart) findViewById(R.id.pieView);
        mMyPieChart.setRadius(DensityUtils.dp2px(this, 80));
        mMyPieChart.setOnItemClickListener(this);
        pieEntries = new ArrayList<>();

        int[] data = new int[]{10, 10, 10, 40};


    }

//    private void initPieView() {
//
////设置指定颜色
//        pieView.setData(data, name, color);
//
//        PercentPieView pieView2 = (PercentPieView) findViewById(R.id.pieView2);
////使用随机颜色
//        pieView2.setData(data, name);
//    }

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
        popupWindow.setOnDismissListener(new MajChooseBestActivity.poponDismissListener());

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
        popupWindow.setOnDismissListener(new MajChooseBestActivity.poponDismissListener());

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
        /**
         * String[]	  maiids    选科关注id
         String	  optF    第一科目
         String	  optS    第二科目
         String	  optT    第三科目
         */
        FormEncodingBuilder builder = new FormEncodingBuilder();
        builder.add("maisid", majId);
        builder.add("optF", oneS);
        builder.add("optS", twoS);
        builder.add("optT", threeS);
        Request request = new Request.Builder().url(Url.getBestMajUrl).post(builder.build()).build();
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
                        mBestMajModel = gson.fromJson(reader, BestMajModel.class);

                        if (mBestMajModel.getCode() == 1) {
                            mHandler.post(new Runnable() {

                                @Override
                                public void run() {
//                                    mPercentPieView.setData(dddd, aaaa, coloraaa);
                                    mBestData = mBestMajModel.getData();
                                    mBestListAdapter = new BestListAdapter(mBestData, colors);
                                    mListView.setAdapter(mBestListAdapter);
                                    sumTv.setText(mBestMajModel.getValue().getAllpeople() + "");

                                    for (int i = 0; i < mBestData.size(); i++) {
                                        if (Float.valueOf(mBestData.get(i).getMajpercent()) > 1) {
                                            pieEntries.add(new MyPieChart.PieEntry(Double.valueOf(mBestData.get(i).getMajpercent()), colors.get(i), true));
                                        }
                                    }
                                    mMyPieChart.requestLayout();
                                    mMyPieChart.invalidate();

                                    mMyPieChart.setPieEntries(pieEntries);
//                                    pieEntries.clear();
//                                    addPieChart();


                                }
                            });
                        } else if (mBestMajModel.getCode() == 2) {
                            Looper.prepare();
                            Toast.makeText(MajChooseBestActivity.this, mBestMajModel.getMsg() + "", Toast.LENGTH_SHORT).show();
                            Looper.loop();
                        } else {
                            Looper.prepare();
                            Toast.makeText(MajChooseBestActivity.this, mBestMajModel.getMsg() + "", Toast.LENGTH_SHORT).show();
                            Looper.loop();
                        }
                    } else {
                        Looper.prepare();
                        Toast.makeText(MajChooseBestActivity.this, "Network server connection failure...", Toast.LENGTH_SHORT).show(); //请求失败
                        Looper.loop();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void addColors() {

    }

    //
    int colorss[];

    private void addColorsOld() {
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


    private void intColor() {
        colorss = new int[]{
                R.color.color_1
                , R.color.color_2
                , R.color.color_3
                , R.color.color_4
                , R.color.color_5
                , R.color.color_6
                , R.color.color_7
                , R.color.color_8
                , R.color.color_9
                , R.color.color_10
                , R.color.color_11
                , R.color.color_12
                , R.color.color_13
                , R.color.color_14
                , R.color.color_15
                , R.color.color_16
                , R.color.color_17
                , R.color.color_18
                , R.color.color_19
                , R.color.color_20
                , R.color.color_21
                , R.color.color_22
                , R.color.color_23
                , R.color.color_24
                , R.color.color_25
                , R.color.color_26
                , R.color.color_27
                , R.color.color_28
                , R.color.color_29
                , R.color.color_30
                , R.color.color_31};
    }
}
