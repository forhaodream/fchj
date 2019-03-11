package com.fchj.czglgz.chengzhangguanli_high.fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.fchj.czglgz.chengzhangguanli_high.R;
import com.fchj.czglgz.chengzhangguanli_high.activity.FeedbackActivity;
import com.fchj.czglgz.chengzhangguanli_high.activity.HomeActivity;
import com.fchj.czglgz.chengzhangguanli_high.activity.KaActivity;
import com.fchj.czglgz.chengzhangguanli_high.activity.MsgActivity;
import com.fchj.czglgz.chengzhangguanli_high.activity.MyAskActivity;
import com.fchj.czglgz.chengzhangguanli_high.activity.SettingActivity;
import com.fchj.czglgz.chengzhangguanli_high.activity.XiuGaiActivity;
import com.fchj.czglgz.chengzhangguanli_high.activity.XiuGaiHeadPicActivity;
import com.fchj.czglgz.chengzhangguanli_high.model.ShowInfoModel;
import com.fchj.czglgz.chengzhangguanli_high.mysc.MyScActivity;
import com.fchj.czglgz.chengzhangguanli_high.util.IntentUtil;
import com.fchj.czglgz.chengzhangguanli_high.util.Url;
import com.fchj.czglgz.chengzhangguanli_high.view.CircleView;
import com.fchj.czglgz.chengzhangguanli_high.wodetiwen.QuestionAskActivity;
import com.fchj.czglgz.chengzhangguanli_high.wodetiwen.ZJAskActivity;
import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import com.squareup.okhttp.Call;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.MultipartBuilder;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;
import java.io.StringReader;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by Administrator on 2018/4/9 0009.
 */

public class MineFragment extends Fragment implements View.OnClickListener {
    private RelativeLayout msgRl, tiwenRl, callRl, feedBackRl, scRl;
    private View mView;
    private CircleView headImg;
    private RelativeLayout settingRl;
    private SharedPreferences usernameSp;
    private int userType, userId;
    private String userName;
    private TextView nameTv;
    private ShowInfoModel mShowInfoModel;
    private Handler mHandler;
    private TextView typeTv;
    private ImageView toKaImg;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_mine, null);
        mHandler = new Handler();
        tiwenRl = (RelativeLayout) mView.findViewById(R.id.personal_myask_rl);
        tiwenRl.setOnClickListener(this);
        typeTv = (TextView) mView.findViewById(R.id.zj_detail_type);
        msgRl = (RelativeLayout) mView.findViewById(R.id.personal_msg_rl);
        msgRl.setOnClickListener(this);
        headImg = (CircleView) mView.findViewById(R.id.zj_detail_head);
        headImg.setOnClickListener(this);
        settingRl = (RelativeLayout) mView.findViewById(R.id.personal_setting);
        settingRl.setOnClickListener(this);
        callRl = (RelativeLayout) mView.findViewById(R.id.personal_call_me_rl);
        callRl.setOnClickListener(this);
        scRl = (RelativeLayout) mView.findViewById(R.id.personal_sc_rl);
        scRl.setOnClickListener(this);
//        feedBackRl = (RelativeLayout) mView.findViewById(R.id.personal_feedback_rl);
//        feedBackRl.setOnClickListener(this);
        toKaImg = (ImageView) mView.findViewById(R.id.home_to_ka);
        toKaImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                IntentUtil.showIntent(getActivity(), KaActivity.class);
            }
        });
        usernameSp = getActivity().getSharedPreferences("user_npt", MODE_PRIVATE);
        userId = usernameSp.getInt("userId", 0);
        userType = usernameSp.getInt("userType", 0);
        userName = usernameSp.getString("userName", "");
        if (userType == 2) {
            typeTv.setText("专家");
        } else {
            typeTv.setText("普通用户");
        }
        showInfo();
        nameTv = (TextView) mView.findViewById(R.id.zj_detail_name);
        return mView;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.personal_myask_rl:
                if (userType == 1) {
                    Intent toUser = new Intent(getActivity(), MyAskActivity.class);
                    startActivity(toUser);
                } else if (userType == 2) {
                    Intent toZj = new Intent(getActivity(), ZJAskActivity.class);
                    startActivity(toZj);
                } else {
                    Log.d("我的提问", "点不开");
                }

                break;
            case R.id.personal_msg_rl:
                if (userType != 2) {
                    Intent toGaiMsg = new Intent(getActivity(), XiuGaiActivity.class);
                    startActivity(toGaiMsg);
                } else {
                    Toast.makeText(getActivity(), "专家不能修改信息", Toast.LENGTH_SHORT).show();
                }

                break;
            case R.id.personal_setting:
                IntentUtil.showIntent(getActivity(), SettingActivity.class);
                break;
            case R.id.personal_sc_rl:
                IntentUtil.showIntent(getActivity(), MyScActivity.class);
                break;
            case R.id.personal_call_me_rl:
                showNormalDialog();

                break;

        }
    }

    private void showInfo() {
        FormEncodingBuilder builder = new FormEncodingBuilder();
        builder.add("userId", String.valueOf(userId));
        Request request = new Request.Builder().url(Url.showInfoUrl).post(builder.build()).build();
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
                        Log.d("showinfo_mine", body);
                        Gson gson = new Gson();
                        JsonReader reader = new JsonReader(new StringReader(body));
                        mShowInfoModel = gson.fromJson(reader, ShowInfoModel.class);
                        if (mShowInfoModel.getCode() == 1) {

                            mHandler.post(new Runnable() {
                                @Override
                                public void run() {
                                    nameTv.setText(mShowInfoModel.getData().getUsername() + "");
                                    if (mShowInfoModel.getData().getHeadimgurl() != null) { //
                                        Glide.with(getActivity()).load(mShowInfoModel.getData().getHeadimgurl()).placeholder(R.mipmap.boy).error(R.mipmap.boy).into(headImg);
                                    } else {
                                        Log.d("无", "头像");
                                    }
                                }
                            });
                        } else if (mShowInfoModel.getCode() == 2) {
                            Looper.prepare();
                            Toast.makeText(getActivity(), mShowInfoModel.getMsg() + "", Toast.LENGTH_SHORT).show();
                            Looper.loop();
                        } else {
                            Looper.prepare();
                            Toast.makeText(getActivity(), mShowInfoModel.getMsg() + "", Toast.LENGTH_SHORT).show();
                            Looper.loop();
                        }


                    } else {
                        Looper.prepare();
                        Toast.makeText(getActivity(), "服务器异常", Toast.LENGTH_SHORT).show(); //请求失败
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
        AlertDialog.Builder normalDialog = new AlertDialog.Builder(getActivity());
        normalDialog.setTitle("确认拨打电话?");
        normalDialog.setPositiveButton("确定",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent();
                        intent.setAction(Intent.ACTION_CALL);
                        intent.setData(Uri.parse("tel:4008358488"));
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
}
