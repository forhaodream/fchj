package com.fchj.czglgz.chengzhangguanli_high.newgaokao;

import android.animation.IntEvaluator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.Nullable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.fchj.czglgz.chengzhangguanli_high.R;
import com.fchj.czglgz.chengzhangguanli_high.major.MajorDetailActivity;
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

public class NGKMajDetailActivity extends Activity implements View.OnClickListener {
    private ImageView mTitleFanhui;
    private TextView mTitleText;
    private RelativeLayout mTitleRlc;
    private TextView mNgkMajName;
    private TextView mNgkMajPercent;
    private TextView mNgkMajSchool;
    private TextView mNgkMajStudent;
    private TextView mSubTitle;
    private TextView mSubTitleOne;
    private TextView mSubTitleTwo;
    private TextView mSubTitleThree;
    private List<Integer> colors;
    private String oneS = "", twoS = "", threeS = "", majorname = "";
    private String title = "", tv1 = "", tv2 = "", tv3 = "";
    private NGkMajDetailModel mNGkMajDetailModel;
    private Handler mHandler;
    private List<NGkMajDetailModel.DataBean> mData;
    private NGkMajDetailModel.ValueBean mValueBeans;
    private SpannableString schoolSp, studentSp;
    private TextView schoolTv, studentTv;
    private NGkColorAdapter mNGkColorAdapter;
    private ListView mListView;
    private Button mToMajBtn;
    private String[] a = new String[]{"物理", "化学", "生物", "历史", "政治", "地理", "技术"};
    private List<String> arrayList;
    public WindowManager.LayoutParams lp;
    private int[] pbList = new int[]{R.drawable.progress_bar_1
            , R.drawable.progress_bar_2
            , R.drawable.progress_bar_3
            , R.drawable.progress_bar_4
            , R.drawable.progress_bar_5
            , R.drawable.progress_bar_6
            , R.drawable.progress_bar_7
            , R.drawable.progress_bar_8};
    private GKPopupAdapter mGKPopupAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ngk_maj_detail);
        mHandler = new Handler();
        Intent intent = getIntent();
        oneS = intent.getStringExtra("one_S");
        twoS = intent.getStringExtra("two_S");
        threeS = intent.getStringExtra("three_S");
        majorname = intent.getStringExtra("maj_name");
        title = intent.getStringExtra("title");
        tv1 = intent.getStringExtra("tv_1");
        tv2 = intent.getStringExtra("tv_2");
        tv3 = intent.getStringExtra("tv_3");
        addSubList();
        addColors();
        initView();
        setText();
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
        mTitleFanhui = (ImageView) findViewById(R.id.title_fanhui);
        mTitleFanhui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        mTitleText = (TextView) findViewById(R.id.title_text);
        mTitleRlc = (RelativeLayout) findViewById(R.id.title_rlc);
        mNgkMajName = (TextView) findViewById(R.id.ngk_maj_name);
        mNgkMajPercent = (TextView) findViewById(R.id.ngk_maj_percent);
        mNgkMajSchool = (TextView) findViewById(R.id.ngk_maj_school);
        mNgkMajStudent = (TextView) findViewById(R.id.ngk_maj_student);
        mSubTitle = (TextView) findViewById(R.id.sub_title);
        mSubTitleOne = (TextView) findViewById(R.id.sub_title_one);
        mSubTitleOne.setOnClickListener(this);
        mSubTitleTwo = (TextView) findViewById(R.id.sub_title_two);
        mSubTitleTwo.setOnClickListener(this);
        mSubTitleThree = (TextView) findViewById(R.id.sub_title_three);
        mSubTitleThree.setOnClickListener(this);
        schoolTv = (TextView) findViewById(R.id.ngk_maj_detail_rlc_school);
        studentTv = (TextView) findViewById(R.id.ngk_maj_detail_rlc_student);
        mListView = (ListView) findViewById(R.id.ngk_maj_detail_lv);
        mToMajBtn = (Button) findViewById(R.id.ngk_maj_detail_btn);
        mToMajBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent toMaj = new Intent(NGKMajDetailActivity.this, MajorDetailActivity.class);
                toMaj.putExtra("zy_name", title);
                startActivity(toMaj);

            }
        });

    }

    private void setText() {
        mTitleText.setText(title);
        mNgkMajName.setText(tv3 + " > " + tv2 + " > " + tv1 + " > " + title);
    }

    private void getMajBySub() {

        FormEncodingBuilder builder = new FormEncodingBuilder();
        builder.add("schoolStatus", "浙江");
        builder.add("parYear", "2017");
        builder.add("majorname", majorname);
        builder.add("optF", oneS);
        builder.add("optS", twoS);
        builder.add("optT", threeS);
        Request request = new Request.Builder().url(Url.majFromSubUrl).post(builder.build()).build();
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
                        mNGkMajDetailModel = gson.fromJson(reader, NGkMajDetailModel.class);

                        if (mNGkMajDetailModel.getCode() == 1) {
                            mHandler.post(new Runnable() {

                                @Override
                                public void run() {
                                    mData = mNGkMajDetailModel.getData();
                                    mValueBeans = mNGkMajDetailModel.getValue();
                                    mNGkColorAdapter = new NGkColorAdapter(mData, colors);
                                    mListView.setAdapter(mNGkColorAdapter);
                                    mNgkMajPercent.setText(mValueBeans.getTotalpercent() + "");
                                    mNgkMajSchool.setText(mValueBeans.getSchoolNum() + "所");
                                    mNgkMajStudent.setText(mValueBeans.getRecruitnum() + "人");
                                    ForegroundColorSpan colorSpan = new ForegroundColorSpan(Color.parseColor("#ee0101"));
                                    String school = String.valueOf(mValueBeans.getSchoolNum());
                                    schoolSp = new SpannableString("共 " + mValueBeans.getSchoolNum() + " 所院校招生");
                                    schoolSp.setSpan(colorSpan, 2, 2 + school.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
                                    schoolTv.setText(schoolSp);
                                    String recruitum = String.valueOf(mValueBeans.getRecruitnum());
                                    studentSp = new SpannableString("2017计划招生 " + recruitum + "人");
                                    studentSp.setSpan(colorSpan, 9, 9 + recruitum.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
                                    studentTv.setText(studentSp);


                                }
                            });
                        } else if (mNGkMajDetailModel.getCode() == 2) {
                            Looper.prepare();
                            Toast.makeText(NGKMajDetailActivity.this, mNGkMajDetailModel.getMsg() + "", Toast.LENGTH_SHORT).show();
                            Looper.loop();
                        } else {
                            Looper.prepare();
                            Toast.makeText(NGKMajDetailActivity.this, mNGkMajDetailModel.getMsg() + "", Toast.LENGTH_SHORT).show();
                            Looper.loop();
                        }
                    } else {
                        Looper.prepare();
                        Toast.makeText(NGKMajDetailActivity.this, "Network server connection failure...", Toast.LENGTH_SHORT).show(); //请求失败
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

    //    private List<Integer> pbList = new ArrayList<>();


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

    class NGkColorAdapter extends BaseAdapter {
        private List<NGkMajDetailModel.DataBean> mData;
        private List<Integer> colors;

        public NGkColorAdapter(List<NGkMajDetailModel.DataBean> data, List<Integer> colors) {
            this.mData = data;
            this.colors = colors;
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
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder = null;
            if (convertView == null) {
                convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_color_list, null);
                holder = new ViewHolder();

                holder.mItemColorTitle = (TextView) convertView.findViewById(R.id.item_color_title);
                holder.mItemColorTitlePercent = (TextView) convertView.findViewById(R.id.item_color_title_percent);
                holder.mItemColorTitleMaj = (TextView) convertView.findViewById(R.id.item_color_title_maj);
//                holder.mItemColorTitleStudent = (TextView) convertView.findViewById(R.id.item_color_title_student);
                holder.mProgressBar = (ProgressBar) convertView.findViewById(R.id.item_color_progressBar);
//                holder.upRlc = (RelativeLayout) convertView.findViewById(R.id.item_color_list_up_rlc);
//                holder.downRlc = (RelativeLayout) convertView.findViewById(R.id.item_color_list_down_rlc);


                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            Drawable progressDrawable = getResources().getDrawable(pbList[position]);
            holder.mItemColorTitle.setText(mData.get(position).getSubjectname());
            holder.mItemColorTitlePercent.setText(mData.get(position).getSubjpercent() + "%");
            holder.mItemColorTitleMaj.setText(mData.get(position).getSubjnum() + "个专业");
            holder.mProgressBar.setProgressDrawable(progressDrawable);
            setPropertyAnimation(holder.mProgressBar, (int) mData.get(position).getSubjpercent());
//            int width = getResources().getDisplayMetrics().widthPixels * 1 / 3;
//            int downWidth = (int) (width * mData.get(position).getSubjpercent() / 100);
//            holder.upRlc.setLayoutParams(new RelativeLayout.LayoutParams(width, ViewGroup.LayoutParams.WRAP_CONTENT));
//            holder.downRlc.setLayoutParams(new RelativeLayout.LayoutParams(downWidth, ViewGroup.LayoutParams.WRAP_CONTENT));


            return convertView;
        }

        @SuppressLint("NewApi")
        private void setPropertyAnimation(final ProgressBar progressbar, int progress) {
            if (android.os.Build.VERSION.SDK_INT >= 11) {
                ValueAnimator anim = ObjectAnimator.ofInt(0, progress);
                anim.setDuration(1000l);
                anim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {

                    @SuppressLint("NewApi")
                    @Override
                    public void onAnimationUpdate(ValueAnimator animation) {
                        if (animation != null && animation.getAnimatedValue() != null)
                            progressbar.setProgress((Integer) animation.getAnimatedValue());
                    }
                });
                anim.setEvaluator(new IntEvaluator());
                anim.start();
            } else {
                progressbar.setProgress(progress);
            }
        }

        public class ViewHolder {
            TextView mItemColorTitle;
            TextView mItemColorTitlePercent;
            TextView mItemColorTitleMaj;
            TextView mItemColorTitleStudent;
            RelativeLayout upRlc, downRlc;
            ProgressBar mProgressBar;
        }


    }
}
