package com.fchj.czglgz.chengzhangguanli_high.volunteer;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.fchj.czglgz.chengzhangguanli_high.R;
import com.fchj.czglgz.chengzhangguanli_high.adapter.PopupShengAdapter;
import com.zhy.autolayout.AutoRelativeLayout;

import java.util.ArrayList;
import java.util.List;

public class SearchNumberActivity extends Activity implements View.OnClickListener {
    private ImageView mTitleFanhui;
    private TextView mTitleText;
    private RelativeLayout mTitle;
    private TextView mOneText;
    private AutoRelativeLayout mSearchNumberYear;
    private TextView mTwoText;
    private AutoRelativeLayout mSearchNumberAddress;
    private TextView mThreeText;
    private EditText mCollegeLowEd;
    private EditText mCollegeHighEd;
    private TextView mFiveText;
    private AutoRelativeLayout mSearchNumberSubject;
    private AutoRelativeLayout mSearchNumberFinish;
    private Handler mHandler;
    private TextView yearsTv, wenkeTv, shengTv;
    private int lownum, highnum;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_number);
        mHandler = new Handler();
        initView();
        getData();
        getSubjectData();
        getyearData();
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
        mTitle = (RelativeLayout) findViewById(R.id.title);
        mOneText = (TextView) findViewById(R.id.one_text);
        mSearchNumberYear = (AutoRelativeLayout) findViewById(R.id.search_number_year);
        mSearchNumberYear.setOnClickListener(this);
        mTwoText = (TextView) findViewById(R.id.two_text);
        mSearchNumberAddress = (AutoRelativeLayout) findViewById(R.id.search_number_address);
        mSearchNumberAddress.setOnClickListener(this);
        mThreeText = (TextView) findViewById(R.id.three_text);
        mCollegeLowEd = (EditText) findViewById(R.id.college_low_ed);
        mCollegeLowEd.setInputType(EditorInfo.TYPE_CLASS_PHONE);
        mCollegeHighEd = (EditText) findViewById(R.id.college_high_ed);
        mCollegeHighEd.setInputType(EditorInfo.TYPE_CLASS_PHONE);
        mFiveText = (TextView) findViewById(R.id.five_text);
        mSearchNumberSubject = (AutoRelativeLayout) findViewById(R.id.search_number_subject);
        mSearchNumberSubject.setOnClickListener(this);
        mSearchNumberFinish = (AutoRelativeLayout) findViewById(R.id.search_number_finish);
        mSearchNumberFinish.setOnClickListener(this);
        yearsTv = (TextView) findViewById(R.id.college_years_tv);
        wenkeTv = (TextView) findViewById(R.id.college_wenke);
        shengTv = (TextView) findViewById(R.id.college_sheng_tv);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.search_number_finish:
                if (!TextUtils.isEmpty(mCollegeLowEd.getText().toString()) && !TextUtils.isEmpty(mCollegeHighEd.getText().toString()) && Integer.valueOf(mCollegeLowEd.getText().toString()) > Integer.valueOf(mCollegeHighEd.getText().toString())) {
                    Toast.makeText(this, "最低分不能高于最高分", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (!TextUtils.isEmpty(years) && !TextUtils.isEmpty(sheng) && !TextUtils.isEmpty(subject)) {
                    Intent toDetail = new Intent(SearchNumberActivity.this, NumberListActivity.class);
                    toDetail.putExtra("years", years);
                    toDetail.putExtra("sheng", sheng);
                    toDetail.putExtra("subject", subject);
                    toDetail.putExtra("lownum", mCollegeLowEd.getText().toString());
                    toDetail.putExtra("highnum", mCollegeHighEd.getText().toString());
                    Log.d("college", mCollegeLowEd.getText().toString());
                    Log.d("college", mCollegeHighEd.getText().toString());
                    startActivity(toDetail);
                } else {
                    Toast.makeText(this, "请选择年份/生源地/科类", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.search_number_year:
                yearPopup();
                break;
            case R.id.search_number_address:
                shengPopup();
                break;
            case R.id.search_number_subject:
                subjectPopup();
                break;
        }
    }

    public WindowManager.LayoutParams lp;
    private String years = "";
    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            yearsTv.setText("2016年");
        }
    };
    Runnable runnable1 = new Runnable() {
        @Override
        public void run() {
            yearsTv.setText("2017年");
        }
    };
    private List<String> yearList;

    private void getyearData() {
        yearList = new ArrayList<>();
        yearList.add("2017年");
        yearList.add("2016年");
    }

    private void yearPopup() {


        final View popView = View.inflate(this, R.layout.popwindow_years, null);
        ListView yearLv = (ListView) popView.findViewById(R.id.popup_year_lv);
        mPopupShengAdapter = new PopupShengAdapter(yearList);
        yearLv.setAdapter(mPopupShengAdapter);

        //获取屏幕宽高
        int weight = getResources().getDisplayMetrics().widthPixels;
        int height = getResources().getDisplayMetrics().heightPixels * 1 / 4;

        final PopupWindow popupWindow = new PopupWindow(popView, weight, height);
        //   popupWindow.setAnimationStyle(R.style.anim_popup_dir);
        popupWindow.setFocusable(true);
        //点击外部popueWindow消失
        popupWindow.setOutsideTouchable(true);
        yearLv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String yearsStr = yearList.get(position).toString();
                years = yearsStr.substring(0, yearsStr.length() - 1);
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        yearsTv.setText(years);
                    }
                });
                Log.d("popo", years);
                popupWindow.dismiss();
            }
        });
        //popupWindow出现屏幕变为半透明
//        final WindowManager.LayoutParams
        lp = getWindow().getAttributes();
        lp.alpha = 0.9f;
        getWindow().setAttributes(lp);
        popupWindow.showAtLocation(popView, Gravity.BOTTOM, 0, 0);

        popupWindow.setOnDismissListener(new poponDismissListener());

    }

    private String subject = "";
    private List<String> subjectList;

    private void getSubjectData() {
        subjectList = new ArrayList<String>();
        subjectList.add("文科");
        subjectList.add("理科");
        subjectList.add("艺术类");
        subjectList.add("体育类");
        subjectList.add("综合");
        subjectList.add("文理");
    }

    private void subjectPopup() {
        final View popView = View.inflate(this, R.layout.popwindow_subject, null);
        ListView subjectLv = (ListView) popView.findViewById(R.id.popup_subject_lv);
        mPopupShengAdapter = new PopupShengAdapter(subjectList);
        subjectLv.setAdapter(mPopupShengAdapter);
        //获取屏幕宽高
        int weight = getResources().getDisplayMetrics().widthPixels;
        int height = getResources().getDisplayMetrics().heightPixels * 3 / 4;

        final PopupWindow popupWindow = new PopupWindow(popView, weight, height);
        //   popupWindow.setAnimationStyle(R.style.anim_popup_dir);
        popupWindow.setFocusable(true);
        //点击外部popueWindow消失
        popupWindow.setOutsideTouchable(true);
        subjectLv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position == 5) {
                    subject = "";
                }
                subject = subjectList.get(position).toString();
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        wenkeTv.setText(subject);
                    }
                });
                Log.d("popo", subject);
                popupWindow.dismiss();
            }
        });
        //popupWindow出现屏幕变为半透明
//        final WindowManager.LayoutParams
        lp = getWindow().getAttributes();
        lp.alpha = 0.5f;
        getWindow().setAttributes(lp);
        popupWindow.showAtLocation(popView, Gravity.BOTTOM, 0, 0);

        popupWindow.setOnDismissListener(new poponDismissListener());

    }

    private PopupShengAdapter mPopupShengAdapter;
    private List<String> shengList;

    private void getData() {

        shengList = new ArrayList<String>();
        shengList.add("全国");
        shengList.add("北京");
        shengList.add("天津");
        shengList.add("河北");
        shengList.add("山西");
        shengList.add("内蒙古");
        shengList.add("辽宁");
        shengList.add("吉林");
        shengList.add("黑龙江");
        shengList.add("上海");
        shengList.add("江苏");
        shengList.add("浙江");
        shengList.add("安徽");
        shengList.add("福建");
        shengList.add("江西");
        shengList.add("山东");
        shengList.add("河南");
        shengList.add("湖北");
        shengList.add("湖南");
        shengList.add("广东");
        shengList.add("广西");
        shengList.add("海南");
        shengList.add("重庆");
        shengList.add("四川");
        shengList.add("贵州");
        shengList.add("云南");
        shengList.add("西藏");
        shengList.add("陕西");
        shengList.add("甘肃");
        shengList.add("青海");
        shengList.add("宁夏");
        shengList.add("新疆");
        shengList.add("台湾");
        shengList.add("香港");
        shengList.add("澳门");

    }

    private String sheng = "";

    private void shengPopup() {

        final View popView = View.inflate(this, R.layout.popwindow_sheng, null);
        ListView shengLv = (ListView) popView.findViewById(R.id.popup_sheng_lv);
        mPopupShengAdapter = new PopupShengAdapter(shengList);
        shengLv.setAdapter(mPopupShengAdapter);

        //获取屏幕宽高
        int weight = getResources().getDisplayMetrics().widthPixels;
        int height = getResources().getDisplayMetrics().heightPixels * 1 / 2;

        final PopupWindow popupWindow = new PopupWindow(popView, weight, height);
        //   popupWindow.setAnimationStyle(R.style.anim_popup_dir);
        popupWindow.setFocusable(true);
        //点击外部popueWindow消失
        popupWindow.setOutsideTouchable(true);
        shengLv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                sheng = shengList.get(position).toString();
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        shengTv.setText(sheng);
                    }
                });
                Log.d("popo", sheng);
                popupWindow.dismiss();
            }
        });

        //popupWindow出现屏幕变为半透明
//        final WindowManager.LayoutParams
        lp = getWindow().getAttributes();
        lp.alpha = 0.9f;
        getWindow().setAttributes(lp);
        popupWindow.showAtLocation(popView, Gravity.BOTTOM, 0, 0);

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
}
