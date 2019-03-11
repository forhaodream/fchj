package com.fchj.czglgz.chengzhangguanli_high.overseasstudy;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.Nullable;
import android.support.v7.widget.CardView;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.fchj.czglgz.chengzhangguanli_high.R;
import com.fchj.czglgz.chengzhangguanli_high.activity.AboutWebActivity;
import com.fchj.czglgz.chengzhangguanli_high.util.Url;
import com.fchj.czglgz.chengzhangguanli_high.view.AutoSwitchView;
import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import com.jaeger.library.StatusBarUtil;
import com.nex3z.flowlayout.FlowLayout;
import com.squareup.okhttp.Call;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import com.zhy.autolayout.AutoRelativeLayout;

import java.io.IOException;
import java.io.StringReader;
import java.util.Arrays;
import java.util.List;

public class OsStudydDetailActivity extends Activity implements View.OnClickListener {
    private AutoSwitchView mAutoSwitchView;
    private String osId = "";
    private OsStudyDetailModel mOsStudyDetailModel;
    private List<OsStudyDetailModel.DataBean.LausyBean> mOsDetailData;
    private OsStudyDetailModel.DataBean mData;
    private Handler mHandler;
    private BannerAdapter mAdapter;
    private OsBannerView mOsBannerView;
    private TextView includeTv, termTv;
    String str;
    String str2;
    List<String> list;
    private FlowLayout flowLayout;
    private ImageView mOsStudyDRlcHead, returnImg;
    private TextView mOsStudyDRlcName;
    private TextView mOsStudyDRlcEngName;
    private TextView mOsStudyDRlcAddress;
    private RelativeLayout mPhoneToAsk;
    private RelativeLayout mToAsk;
    private TextView mCollegeWebsite;
    private TextView mCollegeDetailZsWeb;
    private TextView mCollegeDetailAddress;
    private TextView mCollegeDetailPhone;
    private TextView mOsTvQ;
    private TextView mOsTvW;
    private TextView mOsTvE;
    private TextView mOsTvR;
    private TextView mOsTvT;
    private TextView mOsTvY;
    private TextView mOsTvU;
    private TextView mOsTvI;
    private TextView mOsTvO;
    private TextView mOsTvP;
    private TextView mOsTvA;
    private TextView mOsTvS;
    private TextView mOsTvD;
    private TextView mOsTvF;
    private SpannableString qS, wS, eS, rS, tS, yS, uS, iS, oS, pS, aS, sS, dS, fS;
    private ScrollView mScrollView;
    private AutoRelativeLayout mBannerRl, cardBgRl;
    private CardView mCardView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= 21) {
            View decorView = getWindow().getDecorView();
            decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }
        setContentView(R.layout.activity_os_study_detail);
        mHandler = new Handler();
        initView();
        Intent intent = getIntent();
        osId = intent.getStringExtra("os_id");
        Log.d("os_id", osId);
        addBanner();
        getUniInfo();


    }

    @SuppressLint("NewApi")
    private void initView() {
        mScrollView = (ScrollView) findViewById(R.id.os_detail_scroll);
        mScrollView.setOnScrollChangeListener(new View.OnScrollChangeListener() {
            @Override
            public void onScrollChange(View v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                if (scrollY > 100) {
                    returnImg.setVisibility(View.GONE);
                    getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN); //隐藏状态栏

                } else {
                    returnImg.setVisibility(View.VISIBLE);
                    getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN); //显示状态栏
                }
            }
        });
        mBannerRl = (AutoRelativeLayout) findViewById(R.id.os_study_detail_relative);
//        cardBgRl = (AutoRelativeLayout) findViewById(R.id.card_bg_rl);
        //获取屏幕宽高
//        int width = getResources().getDisplayMetrics().widthPixels;
//        int height = getResources().getDisplayMetrics().heightPixels * 1 / 3;
//        mBannerRl.setLayoutParams(new RelativeLayout.LayoutParams(width, height));
//        mCardView = (CardView) findViewById(R.id.cardView);
//        ViewGroup.LayoutParams lp = (ViewGroup.LayoutParams) mCardView.getLayoutParams();
//        lp.setMargins(30, 50, 22, getResources().getDisplayMetrics().heightPixels / 20);
//        mCardView.setLayoutParams(lp);
        returnImg = (ImageView) findViewById(R.id.return_img);
        returnImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        mOsBannerView = (OsBannerView) findViewById(R.id.os_study_detail_banner);
        includeTv = (TextView) findViewById(R.id.os_study_detail_include);
        termTv = (TextView) findViewById(R.id.os_study_detail_term);
        flowLayout = (FlowLayout) findViewById(R.id.flow);
        mOsStudyDRlcHead = (ImageView) findViewById(R.id.os_study_d_rlc_head);
        mOsStudyDRlcName = (TextView) findViewById(R.id.os_study_d_rlc_name);
        mOsStudyDRlcEngName = (TextView) findViewById(R.id.os_study_d_rlc_eng_name);
        mOsStudyDRlcAddress = (TextView) findViewById(R.id.os_study_d_rlc_address);
        mPhoneToAsk = (RelativeLayout) findViewById(R.id.phone_to_ask);
        mPhoneToAsk.setOnClickListener(this);
        mToAsk = (RelativeLayout) findViewById(R.id.to_ask);
        mToAsk.setOnClickListener(this);
        mCollegeWebsite = (TextView) findViewById(R.id.college_website);
        mCollegeWebsite.setOnClickListener(this);
        mCollegeDetailZsWeb = (TextView) findViewById(R.id.college_detail_zs_web);
        mCollegeDetailAddress = (TextView) findViewById(R.id.college_detail_address);
        mCollegeDetailAddress.setOnClickListener(this);
        mCollegeDetailPhone = (TextView) findViewById(R.id.college_detail_phone);
        mCollegeDetailPhone.setOnClickListener(this);
        mOsTvQ = (TextView) findViewById(R.id.os_tv_q);
        mOsTvQ.setOnClickListener(this);
        mOsTvW = (TextView) findViewById(R.id.os_tv_w);
        mOsTvW.setOnClickListener(this);
        mOsTvE = (TextView) findViewById(R.id.os_tv_e);
        mOsTvE.setOnClickListener(this);
        mOsTvR = (TextView) findViewById(R.id.os_tv_r);
        mOsTvR.setOnClickListener(this);
        mOsTvT = (TextView) findViewById(R.id.os_tv_t);
        mOsTvT.setOnClickListener(this);
        mOsTvY = (TextView) findViewById(R.id.os_tv_y);
        mOsTvY.setOnClickListener(this);
        mOsTvU = (TextView) findViewById(R.id.os_tv_u);
        mOsTvU.setOnClickListener(this);
        mOsTvI = (TextView) findViewById(R.id.os_tv_i);
        mOsTvI.setOnClickListener(this);
        mOsTvO = (TextView) findViewById(R.id.os_tv_o);
        mOsTvO.setOnClickListener(this);
        mOsTvP = (TextView) findViewById(R.id.os_tv_p);
        mOsTvP.setOnClickListener(this);
        mOsTvA = (TextView) findViewById(R.id.os_tv_a);
        mOsTvA.setOnClickListener(this);
        mOsTvS = (TextView) findViewById(R.id.os_tv_s);
        mOsTvS.setOnClickListener(this);
        mOsTvD = (TextView) findViewById(R.id.os_tv_d);
        mOsTvD.setOnClickListener(this);
        mOsTvF = (TextView) findViewById(R.id.os_tv_f);
        mOsTvF.setOnClickListener(this);
    }

    private void addBanner() {
        FormEncodingBuilder builder = new FormEncodingBuilder();
        builder.add("intalshId", osId);
        Request request = new Request.Builder().url(Url.getUniInfoByIdUrl).post(builder.build()).build();
        OkHttpClient okHttpClient = new OkHttpClient();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {
                Looper.prepare();
                Toast.makeText(OsStudydDetailActivity.this, "服务器异常", Toast.LENGTH_LONG).show();
                Looper.loop();
            }

            @Override
            public void onResponse(Response response) throws IOException {
                String body = response.body().string();
                Log.d("0s_aaaaa", body);
                Gson gson = new Gson();
                JsonReader reader = new JsonReader(new StringReader(body));
                mOsStudyDetailModel = gson.fromJson(reader, OsStudyDetailModel.class);
                if (mOsStudyDetailModel.getCode() == 1) {
                    mOsDetailData = mOsStudyDetailModel.getData().getLausy();
                    mHandler.post(mBannerRunn);
                } else {
                    Log.d("无", "数据");
                }
            }
        });
    }

    Runnable mBannerRunn = new Runnable() {
        @Override
        public void run() {
            mAdapter = new BannerAdapter(OsStudydDetailActivity.this, mOsDetailData);
            mOsBannerView.setAdapter(mAdapter);

        }
    };

    private void getUniInfo() {
        FormEncodingBuilder builder = new FormEncodingBuilder();
        builder.add("intalshId", osId);
        Request request = new Request.Builder().url(Url.getUniInfoByIdUrl).post(builder.build()).build();
        OkHttpClient okHttpClient = new OkHttpClient();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {
                Looper.prepare();
                Toast.makeText(OsStudydDetailActivity.this, "服务器异常", Toast.LENGTH_LONG).show();
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
                        mOsStudyDetailModel = gson.fromJson(reader, OsStudyDetailModel.class);
                        if (mOsStudyDetailModel.getCode() == 1) {
                            mData = mOsStudyDetailModel.getData();
                            closeDialog(loadingDialog);
                            mHandler.post(new Runnable() {
                                @Override
                                public void run() {
                                    includeTv.setText(mData.getSchoolSummary());
                                    termTv.setText(mData.getApplyClaim());
                                    Glide.with(OsStudydDetailActivity.this)
                                            .load(mData.getSchoollogo())
//                                    .transform(new GlideRoundTransform(ZhuanJiaDetailActivity.this, 12))
                                            .placeholder(R.mipmap.teacher)
                                            .into(mOsStudyDRlcHead);

                                    mOsStudyDRlcName.setText(mData.getSchoolName());
                                    mOsStudyDRlcEngName.setText(mData.getSchoolEnname());
                                    mOsStudyDRlcAddress.setText(mData.getOwnedCountry() + mData.getOwnedCity());
                                    // 热门专业
                                    if (mData.getDominantMajor() != null) {
                                        str = mData.getDominantMajor();
                                        initChip();
                                    }
                                    //官方网站
                                    mCollegeWebsite.setText(mData.getSchoolNetwork());
                                    //邮箱
                                    mCollegeDetailZsWeb.setText(mData.getEmail().trim());
                                    Log.d("email", mData.getEmail().trim());
                                    mCollegeDetailAddress.setText(mData.getAddress());
                                    mCollegeDetailPhone.setText(mData.getTelephone());
                                    spannableString();


                                }
                            });
                        } else if (mOsStudyDetailModel.getCode() == 2) {
                            closeDialog(loadingDialog);
                            mHandler.post(new Runnable() {
                                @Override
                                public void run() {
                                }
                            });
                            Looper.prepare();
                            Toast.makeText(OsStudydDetailActivity.this, mOsStudyDetailModel.getMsg() + "", Toast.LENGTH_SHORT).show();
                            Looper.loop();
                        } else {
                            closeDialog(loadingDialog);
                            Looper.prepare();
                            Toast.makeText(OsStudydDetailActivity.this, mOsStudyDetailModel.getMsg() + "", Toast.LENGTH_SHORT).show();
                            Looper.loop();
                        }
                    } else {
                        Looper.prepare();
                        Toast.makeText(OsStudydDetailActivity.this, "服务器异常", Toast.LENGTH_SHORT).show(); //请求失败
                        Looper.loop();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        });
    }

    private Dialog loadingDialog;

    public Dialog createLoadingDialog(Context context, String msg) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View v = inflater.inflate(R.layout.dialog_loading_white, null);// 得到加载view
        LinearLayout layout = (LinearLayout) v
                .findViewById(R.id.dialog_loading_view);// 加载布局
        TextView tipTextView = (TextView) v.findViewById(R.id.tipTextView);// 提示文字
        tipTextView.setText(msg);// 设置加载信息

        loadingDialog = new Dialog(context, R.style.MyDialogStyle);// 创建自定义样式dialog
        loadingDialog.setCancelable(true); // 是否可以按“返回键”消失
        loadingDialog.setCanceledOnTouchOutside(false); // 点击加载框以外的区域
        loadingDialog.setContentView(layout, new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT));// 设置布局
        /**
         *将显示Dialog的方法封装在这里面
         */
        Window window = loadingDialog.getWindow();
        WindowManager.LayoutParams lp = window.getAttributes();
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.MATCH_PARENT;
        window.setGravity(Gravity.CENTER);
        window.setAttributes(lp);
        window.setWindowAnimations(R.style.PopWindowAnimStyle);
        loadingDialog.show();

        return loadingDialog;
    }

    /**
     * 关闭dialog
     *
     * @param mDialogUtils
     */
    public static void closeDialog(Dialog mDialogUtils) {
        if (mDialogUtils != null && mDialogUtils.isShowing()) {
            mDialogUtils.dismiss();
        }
    }

    @Override
    public Resources getResources() {
        Resources res = super.getResources();
        Configuration config = new Configuration();
        config.setToDefaults();
        res.updateConfiguration(config, res.getDisplayMetrics());
        return res;
    }

    private void initChip() {
        if (str.length() > 0) {
            //  str2 = str.replace(" ", "");//去掉所用空格
            list = Arrays.asList(str.split(","));
            Log.d("llist", list.get(0));
            for (int i = 0; i < list.size(); i++) {
                TextView textView = buildLabel(list.get(i));
                flowLayout.addView(textView);

            }
        }

    }

    private TextView buildLabel(String text) {
        TextView textView = new TextView(this);
        textView.setText(text);
        textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16);
        textView.setPadding((int) dpToPx(16), (int) dpToPx(8), (int) dpToPx(16), (int) dpToPx(8));
        textView.setBackgroundResource(R.drawable.label_bg);

        return textView;
    }

    private float dpToPx(float dp) {
        return TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP, dp, getResources().getDisplayMetrics());
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.to_ask:
                Intent toAsk = new Intent(OsStudydDetailActivity.this, FreeAskActivity.class);
                toAsk.putExtra("countryStr", mData.getOwnedCountry());
                startActivity(toAsk);
                break;
            case R.id.phone_to_ask:
                break;
            case R.id.college_website:
                Intent toWeb = new Intent(OsStudydDetailActivity.this, AboutWebActivity.class);
                toWeb.putExtra("title", "学校官网");
                toWeb.putExtra("urls", mData.getSchoolNetwork() + "");
                startActivity(toWeb);
                break;
        }
    }

    private void spannableString() {
        ForegroundColorSpan colorSpan = new ForegroundColorSpan(Color.parseColor("#111111"));
        qS = new SpannableString("建校时间:  " + mData.getBuildTime());
        qS.setSpan(colorSpan, 5, qS.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        mOsTvQ.setText(qS);
        wS = new SpannableString("所在国家:  " + mData.getOwnedCountry());
        wS.setSpan(colorSpan, 5, wS.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        mOsTvW.setText(wS);
        eS = new SpannableString("院校性质:  " + mData.getSchoolNature());
        eS.setSpan(colorSpan, 5, eS.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        mOsTvE.setText(eS);
        rS = new SpannableString("所在城市:  " + mData.getOwnedCity());
        rS.setSpan(colorSpan, 5, rS.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        mOsTvR.setText(rS);
        tS = new SpannableString("学校类型:  " + mData.getSchoolType());
        tS.setSpan(colorSpan, 5, tS.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        mOsTvT.setText(tS);
        yS = new SpannableString("授课语言:  " + mData.getLectureLanguage());
        yS.setSpan(colorSpan, 5, yS.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        mOsTvY.setText(yS);
        uS = new SpannableString("雅思要求:  " + mData.getScoreIELTS());
        uS.setSpan(colorSpan, 5, uS.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        mOsTvU.setText(uS);
        iS = new SpannableString("托福要求:  " + mData.getScoreTOEFL());
        iS.setSpan(colorSpan, 5, iS.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        mOsTvI.setText(iS);
        oS = new SpannableString("教师数量:  " + mData.getTeacherNum());
        oS.setSpan(colorSpan, 5, oS.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        mOsTvO.setText(oS);
        pS = new SpannableString("学生数量:  " + mData.getClassNum());
        pS.setSpan(colorSpan, 5, pS.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        mOsTvP.setText(pS);
        aS = new SpannableString("国际学生:  " + mData.getIntalStudentNum());
        aS.setSpan(colorSpan, 5, aS.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        mOsTvA.setText(aS);
        sS = new SpannableString("师生比例:  " + mData.getTsProportion());
        sS.setSpan(colorSpan, 5, sS.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        mOsTvS.setText(sS);
        dS = new SpannableString("学费:  " + mData.getTuition());
        dS.setSpan(colorSpan, 3, dS.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        mOsTvD.setText(dS);
        fS = new SpannableString("住宿费:  " + mData.getStayExpense());
        fS.setSpan(colorSpan, 5, fS.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        mOsTvF.setText(fS);


    }


}
