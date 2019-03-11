package com.fchj.czglgz.chengzhangguanli_high.volunteer;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.util.Util;
import com.fchj.czglgz.chengzhangguanli_high.R;
import com.fchj.czglgz.chengzhangguanli_high.activity.AboutWebActivity;
import com.fchj.czglgz.chengzhangguanli_high.collegedetail.IncludeActivity;
import com.fchj.czglgz.chengzhangguanli_high.util.Url;
import com.fchj.czglgz.chengzhangguanli_high.volunteeradapter.CollegeListDetailAdapter;
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
import java.util.List;

public class CollegeListDetailActivity extends Activity implements View.OnClickListener {
    public static final String TAG = "CollegeListDetailActivity";
    private ImageView mTitleFanhui;
    private TextView mTitleText;
    private ImageView mCollegeDetailHead;
    private TextView mCollegeDetailName;
    private TextView mCollegeDetailEngName;
    private TextView mCollegeDetailYears;
    private TextView mCollegeDetailStudentNumber;
    private TextView mCollegeIncludeDetail;
    private TextView mCollegeXingzhi;
    private TextView mCollegeInclude;
    private TextView mCollegeSceneDetail;
    private ImageView mImgOne;
    private ImageView mImgTwo;
    private ImageView mImgThree;
    private TextView mCollegeTeachersDetail, imgOneTv, imgTwoTv, imgThreeTv;
    private ImageView mCollegeShuo;
    private TextView mCollegeShuoNumber;
    private ImageView mCollegeBo;
    private TextView mCollegeBoNumber;
    private TextView mCollegeMajorDetail;
    private TextView mYuanxiao;
    private TextView mZhuanye;
    private TextView mPici;
    private TextView mPingjunfen;
    private TextView mAddress;
    private ListView mCollegeListDetailLv;
    private ImageView mPersonalUpdateImgNo;
    private TextView mCollegeWebsite;
    private TextView mCollegeDetailZsWeb;
    private TextView mCollegeDetailPhone;
    private RelativeLayout mPersonalMsgRl;
    private ScrollView mHomeScroll;
    private String guid = "";
    private CollegeListDetailModel mDetailModel;
    private Handler mHandler;
    private CollegeListDetailAdapter mListDetailAdapter;
    private List<CollegeListDetailModel.DataBean.LuadtiBean> mLuadtiBeans;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_college_list_detail);
        mHandler = new Handler();
        createLoadingDialog(CollegeListDetailActivity.this, "加载中....");
        Intent intent = getIntent();
        guid = intent.getStringExtra("guid");
        Log.d("uname", guid + "");
        initView();
        setCollegeInfo();
        addNumList();
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
        mCollegeDetailHead = (ImageView) findViewById(R.id.college_detail_head);
        mCollegeDetailName = (TextView) findViewById(R.id.college_detail_name);
        mCollegeDetailEngName = (TextView) findViewById(R.id.college_detail_eng_name);
        mCollegeDetailYears = (TextView) findViewById(R.id.college_detail_years);
        mCollegeDetailStudentNumber = (TextView) findViewById(R.id.college_detail_student_number);
        mCollegeIncludeDetail = (TextView) findViewById(R.id.college_include_detail);
        mCollegeIncludeDetail.setOnClickListener(this);
        mCollegeXingzhi = (TextView) findViewById(R.id.college_xingzhi);
        mCollegeInclude = (TextView) findViewById(R.id.college_include);
        mCollegeSceneDetail = (TextView) findViewById(R.id.college_scene_detail);
        mCollegeSceneDetail.setOnClickListener(this);
        mImgOne = (ImageView) findViewById(R.id.img_one);
        mImgTwo = (ImageView) findViewById(R.id.img_two);
        mImgThree = (ImageView) findViewById(R.id.img_three);
        //  mCollegeTeachersDetail = (TextView) findViewById(R.id.college_teachers_detail);
        mCollegeShuo = (ImageView) findViewById(R.id.college_shuo);
        mCollegeShuoNumber = (TextView) findViewById(R.id.college_shuo_number);
        mCollegeBo = (ImageView) findViewById(R.id.college_bo);
        mCollegeBoNumber = (TextView) findViewById(R.id.college_bo_number);
        mCollegeMajorDetail = (TextView) findViewById(R.id.college_major_detail);
        mCollegeMajorDetail.setOnClickListener(this);
        mYuanxiao = (TextView) findViewById(R.id.yuanxiao);
        mZhuanye = (TextView) findViewById(R.id.zhuanye);
        mPici = (TextView) findViewById(R.id.pici);
        mPingjunfen = (TextView) findViewById(R.id.pingjunfen);
        mCollegeListDetailLv = (ListView) findViewById(R.id.college_list_detail_lv);
        mCollegeListDetailLv.setFocusable(false);
        mPersonalUpdateImgNo = (ImageView) findViewById(R.id.personal_update_img_no);
        mCollegeWebsite = (TextView) findViewById(R.id.college_website);
        mCollegeWebsite.setOnClickListener(this);
        mPersonalMsgRl = (RelativeLayout) findViewById(R.id.personal_msg_rl);
        mCollegeDetailZsWeb = (TextView) findViewById(R.id.college_detail_zs_web);
        mCollegeDetailZsWeb.setOnClickListener(this);
        mCollegeDetailPhone = (TextView) findViewById(R.id.college_detail_phone);
        mCollegeDetailPhone.setOnClickListener(this);
        mHomeScroll = (ScrollView) findViewById(R.id.home_scroll);
        imgOneTv = (TextView) findViewById(R.id.img_one_text);
        imgTwoTv = (TextView) findViewById(R.id.img_two_text);
        imgThreeTv = (TextView) findViewById(R.id.img_three_text);
        mAddress = (TextView) findViewById(R.id.college_detail_address);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.college_include_detail:
                if (mDetailModel.getData().getUniversitysummary() != null) {
                    Intent toInclude = new Intent(CollegeListDetailActivity.this, IncludeActivity.class);
                    toInclude.putExtra("include", mDetailModel.getData().getUniversitysummary() + "");
                    toInclude.putExtra("head_url", mDetailModel.getData().getLogofilename() + "");
                    toInclude.putExtra("cname", mDetailModel.getData().getUniversityname() + "");
                    toInclude.putExtra("cXZ", mDetailModel.getData().getUniversitytype() + "");
                    startActivity(toInclude);
                } else {
                    Log.d("没有", "数据");
                }

                break;
            case R.id.college_scene_detail:
                Intent toDetail = new Intent(CollegeListDetailActivity.this, CollegePicDetailActivty.class);
                toDetail.putExtra("guid", mDetailModel.getData().getGuid() + "");
                startActivity(toDetail);
                break;
            case R.id.college_major_detail:
                Intent toList = new Intent(CollegeListDetailActivity.this, CollegeMajorDetailActivity.class);
                toList.putExtra("guid", mDetailModel.getData().getUniversityname() + "");
                startActivity(toList);
                break;

            // 官网
            case R.id.college_website:
                Intent toWeb = new Intent(CollegeListDetailActivity.this, AboutWebActivity.class);
                toWeb.putExtra("title", "学校官网");
                toWeb.putExtra("urls", mDetailModel.getData().getUniversityofficial() + "");
                startActivity(toWeb);
                break;
            //招生
            case R.id.college_detail_zs_web:
                Intent tozsWeb = new Intent(CollegeListDetailActivity.this, AboutWebActivity.class);
                tozsWeb.putExtra("title", "招生网址");
                tozsWeb.putExtra("urls", mDetailModel.getData().getEnrolmentwebsite() + "");
                startActivity(tozsWeb);
                break;
            //打电话
            case R.id.college_detail_phone:
                showNormalDialog();
                break;
        }
    }

    private void setCollegeInfo() {
        FormEncodingBuilder builder = new FormEncodingBuilder();
        builder.add("universityname", guid);
        Log.d("guid1", guid);
        Request request = new Request.Builder().url(Url.allCollegeByNameUrl).post(builder.build()).build();
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
                        Log.d("校园动态展示", body);
                        Gson gson = new Gson();
                        JsonReader reader = new JsonReader(new StringReader(body));
                        mDetailModel = gson.fromJson(reader, CollegeListDetailModel.class);
                        if (mDetailModel.getCode() == 1) {
                            mHandler.post(new Runnable() {
                                @Override
                                public void run() {
                                    closeDialog(loadingDialog);
                                    mCollegeIncludeDetail.setVisibility(View.VISIBLE);
                                    mCollegeSceneDetail.setVisibility(View.VISIBLE);
                                    mCollegeMajorDetail.setVisibility(View.VISIBLE);
                                    mCollegeDetailName.setText(mDetailModel.getData().getUniversityname() + "");
                                    mCollegeDetailEngName.setText(mDetailModel.getData().getUniversityenname() + "");
                                    mCollegeDetailYears.setText("创建于 " + mDetailModel.getData().getUniversitycreatime());
                                    if (mDetailModel.getData().getUniversitystudentsnum().equals("暂无数据")) {
                                        mCollegeDetailStudentNumber.setText("在校人数: 暂无数据");

                                    } else {
                                        mCollegeDetailStudentNumber.setText("在校人数: " + mDetailModel.getData().getUniversitystudentsnum() + "");
                                    }
                                    mCollegeXingzhi.setText("学校性质: " + mDetailModel.getData().getUniversitytype());
                                    mCollegeInclude.setText("    " + mDetailModel.getData().getUniversitysummary());
                                    mCollegeShuoNumber.setText(mDetailModel.getData().getUniversitymasternum() + "");
                                    mCollegeBoNumber.setText(mDetailModel.getData().getUniversitydoctoralnum() + "");
                                    mCollegeWebsite.setText(mDetailModel.getData().getEnrolmentwebsite() + "");
                                    mCollegeDetailZsWeb.setText(mDetailModel.getData().getUniversityofficial() + "");
                                    mCollegeDetailPhone.setText(mDetailModel.getData().getUniversityphone() + "");
                                    mAddress.setText(mDetailModel.getData().getUniversityaddress() + "");
                                    imgOneTv.setText(mDetailModel.getData().getLusy().get(0).getSceneryplace());
                                    imgTwoTv.setText(mDetailModel.getData().getLusy().get(1).getSceneryplace());
                                    imgThreeTv.setText(mDetailModel.getData().getLusy().get(2).getSceneryplace());
                                    Glide.with(getApplication()).load(mDetailModel.getData().getLogofilename()).placeholder(R.mipmap.fchj_e).error(R.mipmap.fchj_e).into(mCollegeDetailHead);
                                    Glide.with(getApplication()).load(mDetailModel.getData().getLusy().get(0).getBreviaryimg()).placeholder(R.mipmap.fchj_e).error(R.mipmap.fchj_e).into(mImgOne);
                                    Glide.with(getApplication()).load(mDetailModel.getData().getLusy().get(1).getBreviaryimg()).placeholder(R.mipmap.fchj_e).error(R.mipmap.fchj_e).into(mImgTwo);
                                    Glide.with(getApplication()).load(mDetailModel.getData().getLusy().get(2).getBreviaryimg()).placeholder(R.mipmap.fchj_e).error(R.mipmap.fchj_e).into(mImgThree);


                                }
                            });
                        } else if (mDetailModel.getCode() == 2) {
                            Looper.prepare();
                            Toast.makeText(CollegeListDetailActivity.this, mDetailModel.getMsg() + "", Toast.LENGTH_SHORT).show();
                            Looper.loop();
                        } else {
                            Looper.prepare();
                            Toast.makeText(CollegeListDetailActivity.this, mDetailModel.getMsg() + "", Toast.LENGTH_SHORT).show();
                            Looper.loop();
                        }


                    } else {
                        Looper.prepare();
                        Toast.makeText(CollegeListDetailActivity.this, "服务器异常", Toast.LENGTH_SHORT).show(); //请求失败
                        Looper.loop();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        });

    }


    private Context mContext = this;

    //    @Override
//    public void onDestroy() {
//        super.onDestroy();
//        if (Util.isOnMainThread()) {
//            Glide.with(mContext).pauseRequests();
//        }
//    }
    private void addNumList() {
        FormEncodingBuilder builder = new FormEncodingBuilder();
        builder.add("universityname", guid);
        Log.d("guid1", guid);
        Request request = new Request.Builder().url(Url.allCollegeByNameUrl).post(builder.build()).build();
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
                        Log.d("校园动态展示", body);
                        Gson gson = new Gson();
                        JsonReader reader = new JsonReader(new StringReader(body));
                        mDetailModel = gson.fromJson(reader, CollegeListDetailModel.class);
                        if (mDetailModel.getCode() == 1) {
                            mHandler.post(new Runnable() {
                                @Override
                                public void run() {
                                    // 专业分数list
                                    mLuadtiBeans = mDetailModel.getData().getLuadti();
                                    mListDetailAdapter = new CollegeListDetailAdapter(mLuadtiBeans);
                                    mCollegeListDetailLv.setAdapter(mListDetailAdapter);
                                }
                            });
                        } else if (mDetailModel.getCode() == 2) {
                            Looper.prepare();
                            Toast.makeText(CollegeListDetailActivity.this, mDetailModel.getMsg() + "", Toast.LENGTH_SHORT).show();
                            Looper.loop();
                        } else {
                            Looper.prepare();
                            Toast.makeText(CollegeListDetailActivity.this, mDetailModel.getMsg() + "", Toast.LENGTH_SHORT).show();
                            Looper.loop();
                        }


                    } else {
                        Looper.prepare();
                        Toast.makeText(CollegeListDetailActivity.this, "服务器异常", Toast.LENGTH_SHORT).show(); //请求失败
                        Looper.loop();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        });
    }

    private void showNormalDialog() {
        /* @setIcon 设置对话框图标
         * @setTitle 设置对话框标题
         * @setMessage 设置对话框消息提示
         * setXXX方法返回Dialog对象，因此可以链式设置属性
         */
        String name = mDetailModel.getData().getUniversityphone() + "";
        final String phonenum = name.substring(name.length() - 8);
        AlertDialog.Builder normalDialog = new AlertDialog.Builder(CollegeListDetailActivity.this);
        normalDialog.setTitle("确认拨打电话?");
        normalDialog.setPositiveButton("确定",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent();
                        intent.setAction(Intent.ACTION_CALL);
                        intent.setData(Uri.parse(phonenum));
                        startActivity(intent);
                    }
                });
        normalDialog.setNegativeButton("取消",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();

                    }
                });
        // 显示
        normalDialog.show();
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
}
