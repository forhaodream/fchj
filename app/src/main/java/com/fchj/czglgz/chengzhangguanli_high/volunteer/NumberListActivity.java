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

public class NumberListActivity extends Activity {
    private ImageView returnImg;
    private ListView mListView;
    private ImageView mTitleFanhui;
    private TextView mTitleText;
    private RelativeLayout mVideoDetailTitle;
    private TextView mZhuanye;
    private TextView mYuanxiao;
    private TextView mPici;
    private TextView mPingjunfen;
    private TextView mZuigaofen;
    private TextView mZuidifen;
    private PullableListView mCollegeList;
    private PullToRefreshLayout mCollegePullrefresh;
    private String years = "";
    private String sheng = "";
    private String subject = "";
    private String major = "";
    private String lownum;
    private String highnum;
    private MajorListModel mMajorListModel;
    private List<MajorListModel.DataBean> mData;
    private List<MajorListModel.DataBean> pageData;
    private Handler mHandler;
    private MajorListAdapter mMajorListAdapter;
    private int itemId = 1;
    private HorizontalScrollView mScrollView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_major_detail);
        createLoadingDialog(NumberListActivity.this, "加载中....");
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
                Intent toDetail = new Intent(NumberListActivity.this, CollegeListDetailActivity.class);
                toDetail.putExtra("guid", mData.get(position).getUniversityname());
                startActivity(toDetail);
            }
        });
//        mScrollView = (HorizontalScrollView) findViewById(R.id.major_detail_hs);
        Intent intent = getIntent();
        years = intent.getStringExtra("years");
        sheng = intent.getStringExtra("sheng");
        subject = intent.getStringExtra("subject");
        lownum = intent.getStringExtra("lownum");
        highnum = intent.getStringExtra("highnum");
        Log.d("college", years);
        Log.d("college", sheng);
        Log.d("college", subject);
        Log.d("college_lownum", lownum);
        Log.d("college_highnum", highnum);
        addList();
        PullToRefreshLayout ptr = (PullToRefreshLayout) findViewById(R.id.college_pullrefresh);
        mListView = (ListView) ptr.getPullableView();
        ptr.setOnPullListener(new PullToRefreshLayout.OnPullListener() {
            @Override
            public void onRefresh(PullToRefreshLayout pullToRefreshLayout) {
//                mScrollView.requestDisallowInterceptTouchEvent(false);
                addList();
                pullToRefreshLayout.refreshFinish(0);
            }

            @Override
            public void onLoadMore(PullToRefreshLayout pullToRefreshLayout) {
//                mScrollView.requestDisallowInterceptTouchEvent(false);
//                mScrollView.setFocusable(false);
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
        mZhuanye = (TextView) findViewById(R.id.zhuanye);
        mYuanxiao = (TextView) findViewById(R.id.yuanxiao);
        mPici = (TextView) findViewById(R.id.pici);
        mPingjunfen = (TextView) findViewById(R.id.pingjunfen);
        mZuigaofen = (TextView) findViewById(R.id.zuigaofen);
        mZuidifen = (TextView) findViewById(R.id.zuidifen);
        mCollegeList = (PullableListView) findViewById(R.id.college_list);
        mCollegePullrefresh = (PullToRefreshLayout) findViewById(R.id.college_pullrefresh);
    }

    private void addList() {

        FormEncodingBuilder builder = new FormEncodingBuilder();
        /**
         String paryear 年份(2016、2017)
         String schoolstatus	学籍(山东、安徽)
         String subject	学科(文科、理科、艺术)
         String major	专业名称
         Integer minscore	最低分数
         Integer maxscore	最高分数
         Integer	pageNum	页号(第几页)
         Integer pageSize	页码(每页显示条数)
         */
        Log.d("college", years);
        Log.d("college", sheng);
        Log.d("college", subject);
        Log.d("college", String.valueOf(lownum));
        Log.d("college", String.valueOf(highnum));
        builder.add("paryear", years);
        builder.add("schoolstatus", sheng);
        builder.add("subject", subject);
        builder.add("major", "");
        builder.add("minscore", String.valueOf(lownum));
        builder.add("maxscore", String.valueOf(highnum));
        builder.add("pageNum", "1");
        builder.add("pageSize", "25");
        Request request = new Request.Builder().url(Url.majorListUrl).post(builder.build()).build();
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
                        mMajorListModel = gson.fromJson(reader, MajorListModel.class);
                        Log.d("sdadsadas", body);
                        if (mMajorListModel.getCode() == 1) {
                            closeDialog(loadingDialog);
                            mHandler.post(new Runnable() {
                                @Override
                                public void run() {

                                    mData = mMajorListModel.getData();
                                    mMajorListAdapter = new MajorListAdapter(mData);
                                    mListView.setAdapter(mMajorListAdapter);

                                }
                            });
                        } else if (mMajorListModel.getCode() == 2) {
                            Looper.prepare();
                            Toast.makeText(NumberListActivity.this, mMajorListModel.getMsg() + "", Toast.LENGTH_SHORT).show();
                            Looper.loop();
                        } else {
                            Looper.prepare();
                            Toast.makeText(NumberListActivity.this, mMajorListModel.getMsg() + "", Toast.LENGTH_SHORT).show();
                            Looper.loop();
                        }
                    } else {
                        Looper.prepare();
                        Toast.makeText(NumberListActivity.this, "服务器异常", Toast.LENGTH_SHORT).show(); //请求失败
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
        Log.d("college", years);
        Log.d("college", sheng);
        Log.d("college", subject);
        Log.d("college", major);
        Log.d("college", String.valueOf(lownum));
        Log.d("college", String.valueOf(highnum));
        builder.add("paryear", years);
        builder.add("schoolstatus", sheng);
        builder.add("subject", subject);
        builder.add("major", major);
        builder.add("minscore", String.valueOf(lownum));
        builder.add("maxscore", String.valueOf(highnum));
        builder.add("pageSize", "15");
        builder.add("pageNum", String.valueOf(itemId));
        Request request = new Request.Builder().url(Url.majorListUrl).post(builder.build()).build();
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
                        mMajorListModel = gson.fromJson(reader, MajorListModel.class);
                        pageData = mMajorListModel.getData();
                        if (mMajorListModel.getCode() == 1) {
                            mHandler.post(new Runnable() {
                                @Override
                                public void run() {
                                    if (pageData.size() > 0) {
                                        mData.addAll(pageData);
                                        mMajorListAdapter.notifyDataSetChanged();
                                    } else {
                                        mData.clear();
                                        mMajorListAdapter.notifyDataSetChanged();
                                        Log.d("没有", "数据");
                                    }
                                }
                            });
                        } else if (mMajorListModel.getCode() == 2) {
                            Looper.prepare();
                            Toast.makeText(NumberListActivity.this, mMajorListModel.getMsg() + "", Toast.LENGTH_SHORT).show();
                            Looper.loop();
                        } else {
                            Looper.prepare();
                            Toast.makeText(NumberListActivity.this, mMajorListModel.getMsg() + "", Toast.LENGTH_SHORT).show();
                            Looper.loop();
                        }
                    } else {
                        Looper.prepare();
                        Toast.makeText(NumberListActivity.this, "服务器异常", Toast.LENGTH_SHORT).show(); //请求失败
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
