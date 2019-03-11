package com.fchj.czglgz.chengzhangguanli_high.volunteer;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.fchj.czglgz.chengzhangguanli_high.R;
import com.fchj.czglgz.chengzhangguanli_high.util.Url;
import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import com.lh.ch.pulltorefresh.PullToRefreshLayout;
import com.lh.ch.pulltorefresh.PullableListView;
import com.squareup.okhttp.Call;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;
import java.io.StringReader;
import java.util.List;

public class CollegeListActivity extends Activity {
    private ImageView returnImg;
    private ListView mListView;
    private ImageView mTitleFanhui;
    private TextView mTitleText;
    private RelativeLayout mVideoDetailTitle;
    private TextView mYuanxiao;
    private TextView mZhuanye;
    private TextView mPici;
    private TextView mPingjunfen;
    private TextView mZuigaofen;
    private TextView mZuidifen;
    private PullableListView mCollegeList;
    private PullToRefreshLayout mCollegePullrefresh;
    private String years = "";
    private String sheng = "";
    private String subject = "";
    private String college = "";
    private CollegeListModel mCollegeListModel;
    private List<CollegeListModel.DataBean> mData;
    private List<CollegeListModel.DataBean> pageData;
    private Handler mHandler;
    private CollegeDetailAdapter mCollegeDetailAdapter;
    private int itemId = 1;
    private HorizontalScrollView mScrollView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_college_detail);
        createLoadingDialog(CollegeListActivity.this, "加载中....");
        mHandler = new Handler();
        initView();
        returnImg = (ImageView) findViewById(R.id.title_fanhui);
        returnImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        mListView = (ListView) findViewById(R.id.college_list);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent toDetail = new Intent(CollegeListActivity.this, CollegeListDetailActivity.class);
                toDetail.putExtra("guid", mData.get(position).getUniversityname());
                Log.d("guid", mData.get(position).getUniversityname());
                startActivity(toDetail);
            }
        });
        Intent intent = getIntent();
        years = intent.getStringExtra("years");
        sheng = intent.getStringExtra("sheng");
        subject = intent.getStringExtra("subject");
        college = intent.getStringExtra("college");
        Log.d("collegeyears", years);
        Log.d("collegesheng", sheng);
        Log.d("collegesubject", subject);
        Log.d("collegecollege", college);
        addList();
        //   mScrollView = (HorizontalScrollView) findViewById(R.id.college_list_hs);
        PullToRefreshLayout ptr = (PullToRefreshLayout) findViewById(R.id.college_pullrefresh);
        mListView = (ListView) ptr.getPullableView();
        ptr.setOnPullListener(new PullToRefreshLayout.OnPullListener() {
            @Override
            public void onRefresh(PullToRefreshLayout pullToRefreshLayout) {
                addList();
                pullToRefreshLayout.refreshFinish(0);
            }

            @Override
            public void onLoadMore(PullToRefreshLayout pullToRefreshLayout) {
//                mScrollView.setFocusable(false);
//                mScrollView.requestDisallowInterceptTouchEvent(false);
                itemId += 1;
                addPageCheck();
                pullToRefreshLayout.loadmoreFinish(0);
            }
        });
    }

    private void initView() {
        mTitleFanhui = (ImageView) findViewById(R.id.title_fanhui);
        mTitleText = (TextView) findViewById(R.id.title_text);
        mVideoDetailTitle = (RelativeLayout) findViewById(R.id.video_detail_title);
        mYuanxiao = (TextView) findViewById(R.id.yuanxiao);
        mZhuanye = (TextView) findViewById(R.id.zhuanye);
        mPici = (TextView) findViewById(R.id.pici);
        mPingjunfen = (TextView) findViewById(R.id.pingjunfen);
        mZuigaofen = (TextView) findViewById(R.id.zuigaofen);
        mZuidifen = (TextView) findViewById(R.id.zuidifen);
        mCollegeList = (PullableListView) findViewById(R.id.college_list);
        mCollegePullrefresh = (PullToRefreshLayout) findViewById(R.id.college_pullrefresh);
    }

    private void addList() {

        FormEncodingBuilder builder = new FormEncodingBuilder();
        Log.d("college", years);
        Log.d("college", sheng);
        Log.d("college", subject);
        Log.d("college", college);
        builder.add("paryear", years);
        builder.add("schoolstatus", sheng);
        builder.add("subject", subject);
        builder.add("universityname", college);
        builder.add("pageNum", "1");
        builder.add("pageSize", "25");
        Request request = new Request.Builder().url(Url.collegeDetailUrl).post(builder.build()).build();
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
                        Gson gson = new Gson();
                        JsonReader reader = new JsonReader(new StringReader(body));
                        mCollegeListModel = gson.fromJson(reader, CollegeListModel.class);
                        Log.d("sdadsadas", body);
                        if (mCollegeListModel.getCode() == 1) {
                            closeDialog(loadingDialog);
                            mHandler.post(new Runnable() {
                                @Override
                                public void run() {

                                    mData = mCollegeListModel.getData();
                                    mCollegeDetailAdapter = new CollegeDetailAdapter(mData);
                                    mListView.setAdapter(mCollegeDetailAdapter);

                                }
                            });
                        } else if (mCollegeListModel.getCode() == 2) {
                            closeDialog(loadingDialog);
                            Looper.prepare();
                            Toast.makeText(CollegeListActivity.this, mCollegeListModel.getMsg() + "", Toast.LENGTH_SHORT).show();
                            Looper.loop();
                        } else {
                            closeDialog(loadingDialog);
                            Looper.prepare();
                            Toast.makeText(CollegeListActivity.this, mCollegeListModel.getMsg() + "", Toast.LENGTH_SHORT).show();
                            Looper.loop();
                        }
                    } else {
                        Looper.prepare();
                        Toast.makeText(CollegeListActivity.this, "服务器异常", Toast.LENGTH_SHORT).show(); //请求失败
                        Looper.loop();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        });
    }

    private void addPageCheck() {
        FormEncodingBuilder builder = new FormEncodingBuilder();
        Log.d("college_page", years);
        Log.d("college_page", sheng);
        Log.d("college_page", subject);
        Log.d("college_page", college);
        builder.add("paryear", years);
        builder.add("schoolstatus", sheng);
        builder.add("subject", subject);
        builder.add("universityname", college);
        builder.add("pageNum", String.valueOf(itemId));
        builder.add("pageSize", "25");
        Request request = new Request.Builder().url(Url.collegeDetailUrl).post(builder.build()).build();
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
                        mCollegeListModel = gson.fromJson(reader, CollegeListModel.class);
                        pageData = mCollegeListModel.getData();
                        if (mCollegeListModel.getCode() == 1) {
                            mHandler.post(new Runnable() {
                                @Override
                                public void run() {
                                    if (pageData.size() > 0) {
                                        mData.addAll(pageData);
                                        mCollegeDetailAdapter.notifyDataSetChanged();
                                    } else {
                                        mData.clear();
                                        mCollegeDetailAdapter.notifyDataSetChanged();
                                        Log.d("没有", "数据");
                                    }
                                }
                            });
                        } else if (mCollegeListModel.getCode() == 2) {
                            Looper.prepare();
                            Toast.makeText(CollegeListActivity.this, mCollegeListModel.getMsg() + "", Toast.LENGTH_SHORT).show();
                            Looper.loop();
                        } else {
                            Looper.prepare();
                            Toast.makeText(CollegeListActivity.this, mCollegeListModel.getMsg() + "", Toast.LENGTH_SHORT).show();
                            Looper.loop();
                        }
                    } else {
                        Looper.prepare();
                        Toast.makeText(CollegeListActivity.this, "服务器异常", Toast.LENGTH_SHORT).show(); //请求失败
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
        View v = inflater.inflate(R.layout.dialog_loading, null);// 得到加载view
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
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
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

    Runnable pageRunn = new Runnable() {
        @Override
        public void run() {

        }
    };

    @Override
    public void onStop() {
        super.onStop();
        itemId = 1;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        itemId = 1;
    }
}
