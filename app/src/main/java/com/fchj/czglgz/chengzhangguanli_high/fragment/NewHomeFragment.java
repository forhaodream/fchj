package com.fchj.czglgz.chengzhangguanli_high.fragment;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.fchj.czglgz.chengzhangguanli_high.R;
import com.fchj.czglgz.chengzhangguanli_high.activity.JiaZhangActivity;
import com.fchj.czglgz.chengzhangguanli_high.activity.KaActivity;
import com.fchj.czglgz.chengzhangguanli_high.activity.ZhuanJiaActivity;
import com.fchj.czglgz.chengzhangguanli_high.activity.ZiXunActivity;
import com.fchj.czglgz.chengzhangguanli_high.activity.ZiXunDetailActivity;
import com.fchj.czglgz.chengzhangguanli_high.adapter.AllCollegeAdapter;
import com.fchj.czglgz.chengzhangguanli_high.adapter.AutoSwitchAdapter;
import com.fchj.czglgz.chengzhangguanli_high.adapter.HomeRecyclerAdapter;
import com.fchj.czglgz.chengzhangguanli_high.adapter.ZiXunAdapter;
import com.fchj.czglgz.chengzhangguanli_high.dropdownmenu.DropActivity;
import com.fchj.czglgz.chengzhangguanli_high.major.MajorKindActivity;
import com.fchj.czglgz.chengzhangguanli_high.model.BannerModel;
import com.fchj.czglgz.chengzhangguanli_high.model.HomeZXModel;
import com.fchj.czglgz.chengzhangguanli_high.model.TestModel;
import com.fchj.czglgz.chengzhangguanli_high.model.VersionModel;
import com.fchj.czglgz.chengzhangguanli_high.model.ZiXunModel;
import com.fchj.czglgz.chengzhangguanli_high.overseasstudy.OsStudyActivity;
import com.fchj.czglgz.chengzhangguanli_high.test.TestTreeActivity;
import com.fchj.czglgz.chengzhangguanli_high.travel.TravelStudyActivity;
import com.fchj.czglgz.chengzhangguanli_high.util.IntentUtil;
import com.fchj.czglgz.chengzhangguanli_high.util.Url;
import com.fchj.czglgz.chengzhangguanli_high.util.VersionTools;
import com.fchj.czglgz.chengzhangguanli_high.view.AutoSwitchView;
import com.fchj.czglgz.chengzhangguanli_high.vocation.VocationActivity;
import com.fchj.czglgz.chengzhangguanli_high.volunteer.AllCollegeModel;
import com.fchj.czglgz.chengzhangguanli_high.volunteer.CollegeListDetailActivity;
import com.fchj.czglgz.chengzhangguanli_high.volunteer.SearchCollegeModel;
import com.fchj.czglgz.chengzhangguanli_high.volunteeradapter.GetAllCollegeAdapter;
import com.fchj.czglgz.chengzhangguanli_high.zhuanjiajieda.ZhuanJiaDetailActivity;
import com.fchj.czglgz.chengzhangguanli_high.zixun.ZXFragAdapter;
import com.fchj.czglgz.chengzhangguanli_high.zixun.ZXFragModel;
import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import com.lh.ch.pulltorefresh.PullToRefreshLayout;
import com.lh.ch.updateversion.UpdateHelper;
import com.lh.ch.updateversion.bean.UpdateEntity;
import com.lh.ch.updateversion.listener.UpdateListener;
import com.squareup.okhttp.Call;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by Administrator on 2018/5/21 0021.
 */

public class NewHomeFragment extends Fragment implements View.OnClickListener {
    private ListView mListView;
    private ZXFragAdapter mZXFragAdapter;
    private View view;
    private Handler mHandler;
    private Context mContext;
    // 检查更新
    private String virCode;
    private VersionModel mVersionModel;
    // banner
    private BannerModel mBannerModel;
    private List<BannerModel.DataBean> mBannerData;
    private AutoSwitchView mAutoSwitchView;
    private AutoSwitchAdapter mAdapter;
    private TextView zxTv, zhuanjiaTv, jiazhangTv, xiaoyuanTv;
    private SharedPreferences username;
    private int userId;
    private HomeZXModel mHomeZXModel;
    private ZiXunModel mZiXunModel;
    private ListView mZXListview;
    private ZiXunAdapter mZiXunAdapter;
    private List<ZiXunModel.DataBean> zxData;
    private ScrollView mScrollView;
    private ImageView toKaImg;
    private Context context;
    private ViewFlipper viewFlipper;
    private List<String> titles;
    private ZXFragModel mZXFragModel;
    private List<ZXFragModel.DataBean> mData;
    private RecyclerView mHomeRecycler;
    private HomeRecyclerAdapter mHomeRecyclerAdapter;
    private TestModel mTestModel;
    private List<TestModel.DataBean> mRecyclerData;
    private int itemId = 1;
    private SearchCollegeModel mSearchCollegeModel;
    private List<SearchCollegeModel.DataBean> mSearchCollegeData;
    private List<SearchCollegeModel.DataBean> mSearchPageData;
    private TextView mFragHomeXb;
    private TextView mFragHomeMajor;
    private TextView mFragHomeProfession;
    private TextView mFragHomeLx;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_new_home, null);
        createLoadingDialog(getActivity(), "正在加载....");
        mHandler = new Handler();
        mContext = getActivity();
        username = getActivity().getSharedPreferences("user_npt", MODE_PRIVATE);
        userId = username.getInt("userId", 0);
        mListView = (ListView) view.findViewById(R.id.fragment_new_home_list);
        View headView = View.inflate(getActivity(), R.layout.home_list_header_one, null);
        View headView2 = View.inflate(getActivity(), R.layout.home_list_header_two, null);
        View headView3 = View.inflate(getActivity(), R.layout.home_list_header_three, null);
        mListView.addHeaderView(headView, null, true);
        mListView.addHeaderView(headView2, null, true);
        mListView.addHeaderView(headView3, null, true);
        mListView.setHeaderDividersEnabled(false);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent toDetail = new Intent(getActivity(), CollegeListDetailActivity.class);
                toDetail.putExtra("guid", mSearchCollegeData.get(position - 3).getUniversityname());
                Log.d("guid", mSearchCollegeData.get(position - 3).getUniversityname());
                startActivity(toDetail);
            }
        });
        PullToRefreshLayout ptr = (PullToRefreshLayout) view.findViewById(R.id.fragment_new_home_pullrefresh);
        mListView = (ListView) ptr.getPullableView();
        ptr.setOnPullListener(new PullToRefreshLayout.OnPullListener() {
            @Override
            public void onRefresh(PullToRefreshLayout pullToRefreshLayout) {

                pullToRefreshLayout.refreshFinish(0);
            }

            @Override
            public void onLoadMore(PullToRefreshLayout pullToRefreshLayout) {
                itemId += 1;
//                getPageCollege();
                getAllCollegeByKindPage();
                pullToRefreshLayout.loadmoreFinish(0);
            }
        });
        mAutoSwitchView = (AutoSwitchView) view.findViewById(R.id.home_banner);
        viewFlipper = (ViewFlipper) view.findViewById(R.id.viewFlipper);
        // 布局
        initView();
        // 更新
        update();
        // addVerticalBanner();
        // 测试数据
        //  addList();
        // 6条资讯
        xiaZX();
        addBanner();
        // 滚动新闻
        initData();
        setViews();
        initRecycler();
        addRecyclerData();
//        getAllCollege();
        getAllCollegeByKind();
        return view;
    }


    private void initView() {
        toKaImg = (ImageView) view.findViewById(R.id.home_to_ka);
        toKaImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                IntentUtil.showIntent(getActivity(), KaActivity.class);
            }

        });
        zxTv = (TextView) view.findViewById(R.id.frag_home_zx);
        zxTv.setOnClickListener(this);
        zhuanjiaTv = (TextView) view.findViewById(R.id.frag_home_zhuanjia);
        zhuanjiaTv.setOnClickListener(this);
        jiazhangTv = (TextView) view.findViewById(R.id.frag_home_jiazhang);
        jiazhangTv.setOnClickListener(this);
        xiaoyuanTv = (TextView) view.findViewById(R.id.frag_home_xy);
        xiaoyuanTv.setOnClickListener(this);
        mZXListview = (ListView) view.findViewById(R.id.home_hd_list);
        mZXListview.setFocusable(false);
        mZXListview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent toZXDetail = new Intent(getActivity(), ZiXunDetailActivity.class);
                toZXDetail.putExtra("newsid", zxData.get(i).getId());
                startActivity(toZXDetail);
            }
        });
        mHomeRecycler = (RecyclerView) view.findViewById(R.id.home_new_reyview);
        mFragHomeXb = (TextView) view.findViewById(R.id.frag_home_xb);
        mFragHomeXb.setOnClickListener(this);
        mFragHomeMajor = (TextView) view.findViewById(R.id.frag_home_major);
        mFragHomeMajor.setOnClickListener(this);
        mFragHomeProfession = (TextView) view.findViewById(R.id.frag_home_vocation);
        mFragHomeProfession.setOnClickListener(this);
        mFragHomeLx = (TextView) view.findViewById(R.id.frag_home_lx);
        mFragHomeLx.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.frag_home_zx:
                IntentUtil.showIntent(getActivity(), ZiXunActivity.class);
                break;
            case R.id.frag_home_zhuanjia:
                IntentUtil.showIntent(getActivity(), ZhuanJiaActivity.class);
                break;
            case R.id.frag_home_jiazhang:
                IntentUtil.showIntent(getActivity(), JiaZhangActivity.class);
                break;
            case R.id.frag_home_xy:
                IntentUtil.showIntent(getActivity(), DropActivity.class);
                break;
            case R.id.frag_home_xb:
                IntentUtil.showIntent(getActivity(), TravelStudyActivity.class);
                break;
            case R.id.frag_home_major:
                IntentUtil.showIntent(getActivity(), MajorKindActivity.class);
                break;
            case R.id.frag_home_vocation:
                IntentUtil.showIntent(getActivity(), VocationActivity.class);
                break;
            case R.id.frag_home_lx:
                IntentUtil.showIntent(getActivity(), OsStudyActivity.class);
                break;


        }
    }

    /**
     * 初始化新闻标题数据
     */
    private void initData() {
        titles = new ArrayList();
        titles.add("中国考试志愿网 上线了!");
        titles.add("中国考试志愿网 上线了!");
        titles.add("中国考试志愿网 上线了!");
        titles.add("中国考试志愿网 上线了!");
        titles.add("中国考试志愿网 上线了!");
        titles.add("中国考试志愿网 上线了!");
        titles.add("中国考试志愿网 上线了!");

    }

    //
//    /**
//     * 为每一页设置视图
//     */
    private void setViews() {
        if (titles.size() > 0) {
            for (int i = 0; i < titles.size(); i++) {
                View itemView = View.inflate(mContext, R.layout.title_view, null);
                TextView tvTitle1 = (TextView) itemView.findViewById(R.id.tv_title1);
                tvTitle1.setText(titles.get(i));
                Log.d("tvtitle1", titles.get(i));
                //标题1的点击事件
                tvTitle1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        IntentUtil.showIntent(getActivity(), KaActivity.class);
                    }
                });
                viewFlipper.addView(itemView);
            }
            //视图进入动画
            viewFlipper.setInAnimation(mContext, R.anim.news_in);
            //视图退出动画
            viewFlipper.setOutAnimation(mContext, R.anim.news_out);
            //自动开始滚动
            viewFlipper.setAutoStart(true);
            //视图的切换间隔
            viewFlipper.setFlipInterval(5000);
//            viewFlipper.startFlipping();
        }
    }

    // Banner
    private void addBanner() {
        FormEncodingBuilder builder = new FormEncodingBuilder();
        builder.add("", "");
        Request request = new Request.Builder().url(Url.bannerUrl).post(builder.build()).build();
        OkHttpClient okHttpClient = new OkHttpClient();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {
                Looper.prepare();
                Toast.makeText(getActivity(), "服务器异常", Toast.LENGTH_LONG).show();
                Looper.loop();
            }

            @Override
            public void onResponse(Response response) throws IOException {
                String body = response.body().string();
                Log.d("aaaaa", body);
                Gson gson = new Gson();
                JsonReader reader = new JsonReader(new StringReader(body));
                mBannerModel = gson.fromJson(reader, BannerModel.class);
                if (mBannerModel.getCode() == 1) {
                    mBannerData = mBannerModel.getData();
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
            mAdapter = new AutoSwitchAdapter(getActivity(), mBannerModel.getData());
            mAutoSwitchView.setAdapter(mAdapter);

        }
    };

    private void initRecycler() {
        LinearLayoutManager ms = new LinearLayoutManager(getActivity());
        ms.setOrientation(LinearLayoutManager.HORIZONTAL); // 设置recyclerview布局方式为横向布局
        mHomeRecycler.setLayoutManager(ms);
    }

    private void addRecyclerData() {
        FormEncodingBuilder builder = new FormEncodingBuilder();
        builder.add("usertype", "2");
        builder.add("asktype", "1");
        Request request = new Request.Builder().url(Url.zjListUrl).post(builder.build()).build();
        OkHttpClient okHttpClient = new OkHttpClient();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {

            }

            @Override
            public void onResponse(Response response) throws IOException {
                String body = response.body().string();
                Log.d("sdadsadas", body);
                Gson gson = new Gson();
                JsonReader reader = new JsonReader(new StringReader(body));
                mTestModel = gson.fromJson(reader, TestModel.class);
                if (mTestModel.getCode() == 1) {

                    mHandler.post(new Runnable() {
                        @Override
                        public void run() {

                            mRecyclerData = mTestModel.getData();
                            mHomeRecyclerAdapter = new HomeRecyclerAdapter(mRecyclerData);
                            mHomeRecycler.setAdapter(mHomeRecyclerAdapter);
                            mHomeRecyclerAdapter.setItemClickListener(new HomeRecyclerAdapter.MyItemClickListener() {
                                @Override
                                public void onItemClick(View view, int position) {
                                    Intent toDetail = new Intent(getActivity(), ZhuanJiaDetailActivity.class);
                                    toDetail.putExtra("zjId", mRecyclerData.get(position).getId());
                                    startActivity(toDetail);
                                }
                            });


                        }
                    });
                } else if (mTestModel.getCode() == 2) {
                    Looper.prepare();
                    Toast.makeText(getActivity(), mTestModel.getMsg() + "", Toast.LENGTH_SHORT).show();
                    Looper.loop();
                } else {
                    Looper.prepare();
                    Toast.makeText(getActivity(), mTestModel.getMsg() + "", Toast.LENGTH_SHORT).show();
                    Looper.loop();
                }


            }
        });
    }

    private AllCollegeAdapter mAllCollegeAdapter;
    private AllCollegeModel mAllCollegeModel;
    private List<AllCollegeModel.DataBean> mDataBeans;
    private List<AllCollegeModel.DataBean> pageBeans;

    private void getAllCollege() {

        FormEncodingBuilder builder = new FormEncodingBuilder();
        builder.add("pageNum", "1");
        builder.add("pageSize", "5");
        Request request = new Request.Builder().url(Url.allCollegeUrl).post(builder.build()).build();
        OkHttpClient okHttpClient = new OkHttpClient();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {
                Looper.prepare();
                Toast.makeText(getActivity(), "服务器异常", Toast.LENGTH_LONG).show();
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
                        mAllCollegeModel = gson.fromJson(reader, AllCollegeModel.class);
                        if (mAllCollegeModel.getCode() == 1) {
                            closeDialog(loadingDialog);
                            mHandler.post(new Runnable() {
                                @Override
                                public void run() {
                                    mDataBeans = mAllCollegeModel.getData();
                                    mAllCollegeAdapter = new AllCollegeAdapter(mDataBeans);
                                    mListView.setAdapter(mAllCollegeAdapter);


                                }
                            });
                        } else if (mAllCollegeModel.getCode() == 2) {
                            closeDialog(loadingDialog);
                            Looper.prepare();
                            Toast.makeText(getActivity(), mAllCollegeModel.getMsg() + "", Toast.LENGTH_SHORT).show();
                            Looper.loop();
                        } else {
                            closeDialog(loadingDialog);
                            Looper.prepare();
                            Toast.makeText(getActivity(), mAllCollegeModel.getMsg() + "", Toast.LENGTH_SHORT).show();
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

    private void getPageCollege() {

        FormEncodingBuilder builder = new FormEncodingBuilder();
        builder.add("pageNum", String.valueOf(itemId));
        builder.add("pageSize", "10");
        Request request = new Request.Builder().url(Url.allCollegeUrl).post(builder.build()).build();
        OkHttpClient okHttpClient = new OkHttpClient();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {
                Looper.prepare();
                Toast.makeText(getActivity(), "服务器异常", Toast.LENGTH_LONG).show();
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
                        mAllCollegeModel = gson.fromJson(reader, AllCollegeModel.class);
                        if (mAllCollegeModel.getCode() == 1) {
                            mHandler.post(new Runnable() {
                                @Override
                                public void run() {
                                    pageBeans = mAllCollegeModel.getData();
                                    if (pageBeans.size() > 0) {
                                        mDataBeans.addAll(pageBeans);
                                        mAllCollegeAdapter.notifyDataSetChanged();
                                    } else {
                                        mDataBeans.clear();
                                        mAllCollegeAdapter.notifyDataSetChanged();
                                        Log.d("没有", "数据");
                                    }
                                    Log.d("mdatanea", mDataBeans.get(0).getUnty().getUniversityname() + "");


                                }
                            });
                        } else if (mAllCollegeModel.getCode() == 2) {
                            Looper.prepare();
                            Toast.makeText(getActivity(), mAllCollegeModel.getMsg() + "", Toast.LENGTH_SHORT).show();
                            Looper.loop();
                        } else {
                            Looper.prepare();
                            Toast.makeText(getActivity(), mAllCollegeModel.getMsg() + "", Toast.LENGTH_SHORT).show();
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

    private GetAllCollegeAdapter mGetAllCollegeAdapter;

    private void getAllCollegeByKind() {

        FormEncodingBuilder builder = new FormEncodingBuilder();
        builder.add("universityaddress", "");
        builder.add("universitytype", "");
        builder.add("universitysubjecttype", "");
        builder.add("pageNum", "1");
        builder.add("pageSize", "5");
        Request request = new Request.Builder().url(Url.getCollegeByKindsUrl).post(builder.build()).build();
        OkHttpClient okHttpClient = new OkHttpClient();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {
                Looper.prepare();
                Toast.makeText(getActivity(), "服务器异常", Toast.LENGTH_LONG).show();
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
                        mSearchCollegeModel = gson.fromJson(reader, SearchCollegeModel.class);
                        if (mSearchCollegeModel.getCode() == 1) {
                            closeDialog(loadingDialog);
                            mHandler.post(new Runnable() {
                                @Override
                                public void run() {
                                    mSearchCollegeData = mSearchCollegeModel.getData();
                                    mGetAllCollegeAdapter = new GetAllCollegeAdapter(mSearchCollegeData);
                                    mListView.setAdapter(mGetAllCollegeAdapter);
                                }
                            });
                        } else if (mSearchCollegeModel.getCode() == 2) {
                            closeDialog(loadingDialog);
                            mHandler.post(new Runnable() {
                                @Override
                                public void run() {
                                    mSearchCollegeData.clear();
                                    mGetAllCollegeAdapter.notifyDataSetChanged();
                                }
                            });
                            Looper.prepare();
                            Toast.makeText(getActivity(), mSearchCollegeModel.getMsg() + "", Toast.LENGTH_SHORT).show();
                            Looper.loop();
                        } else {
                            closeDialog(loadingDialog);
                            Looper.prepare();
                            Toast.makeText(getActivity(), mSearchCollegeModel.getMsg() + "", Toast.LENGTH_SHORT).show();
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

    private void getAllCollegeByKindPage() {
        FormEncodingBuilder builder = new FormEncodingBuilder();
        builder.add("universityaddress", "");
        builder.add("universitytype", "");
        builder.add("universitysubjecttype", "");
        builder.add("pageNum", String.valueOf(itemId));
        builder.add("pageSize", "10");
        Request request = new Request.Builder().url(Url.getCollegeByKindsUrl).post(builder.build()).build();
        OkHttpClient okHttpClient = new OkHttpClient();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {
                Looper.prepare();
                Toast.makeText(getActivity(), "服务器异常", Toast.LENGTH_LONG).show();
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
                        mSearchCollegeModel = gson.fromJson(reader, SearchCollegeModel.class);
                        if (mSearchCollegeModel.getCode() == 1) {

                            mHandler.post(new Runnable() {
                                @Override
                                public void run() {
                                    mSearchPageData = mSearchCollegeModel.getData();
                                    if (mSearchPageData.size() > 0) {
                                        mSearchCollegeData.addAll(mSearchPageData);
                                        mGetAllCollegeAdapter.notifyDataSetChanged();
                                    } else {
                                        mSearchCollegeData.clear();
                                        mGetAllCollegeAdapter.notifyDataSetChanged();
                                        Log.d("没有", "数据");
                                    }
                                }
                            });
                        } else if (mSearchCollegeModel.getCode() == 2) {
                            Looper.prepare();
                            Toast.makeText(getActivity(), mSearchCollegeModel.getMsg() + "", Toast.LENGTH_SHORT).show();
                            Looper.loop();
                        } else {
                            Looper.prepare();
                            Toast.makeText(getActivity(), mSearchCollegeModel.getMsg() + "", Toast.LENGTH_SHORT).show();
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

    private void xiaZX() {
        FormEncodingBuilder builder = new FormEncodingBuilder();
        builder.add("pageNum", "1");
        builder.add("pageSize", "5");
        Request request = new Request.Builder().url(Url.zixunUrl).post(builder.build()).build();
        OkHttpClient okHttpClient = new OkHttpClient();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {
                Looper.prepare();
                Toast.makeText(getActivity(), "服务器异常", Toast.LENGTH_LONG).show();
                Looper.loop();

            }

            @Override
            public void onResponse(Response response) throws IOException {
                /**
                 *  if (response.isSuccessful())

                 }
                 */
                String body = response.body().string();
                Log.d("home_zx", body);
                Gson gson = new Gson();
                JsonReader reader = new JsonReader(new StringReader(body));
                mZiXunModel = gson.fromJson(reader, ZiXunModel.class);
                if (mZiXunModel.getCode() == 1) {
                    mHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            zxData = mZiXunModel.getData();
                            mZiXunAdapter = new ZiXunAdapter(zxData);
                            mZXListview.setAdapter(mZiXunAdapter);

                        }
                    });
                } else {
                    Log.d("没有", "数据");
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

    private void update() {
        OkHttpClient okHttpClient = new OkHttpClient();
        Request request = new Request.Builder().url(Url.updateUrl).build();
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
                        Log.d("update_body", body);
                        Gson gson = new Gson();
                        JsonReader reader = new JsonReader(new StringReader(body));
                        mVersionModel = gson.fromJson(reader, VersionModel.class);
                        if (mVersionModel.getCode() == 1) {
                            mHandler.post(virRunn);

                        } else if (mVersionModel.getCode() == 2) {
                            Looper.prepare();
                            Toast.makeText(getActivity(), mVersionModel.getMsg() + "", Toast.LENGTH_SHORT).show();
                            Looper.loop();
                        } else {
                            Looper.prepare();
                            Toast.makeText(getActivity(), mVersionModel.getMsg() + "", Toast.LENGTH_SHORT).show();
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

    Runnable virRunn = new Runnable() {
        @Override
        public void run() {
            virCode = mVersionModel.getData().getAndroidversion();
            Log.d("111jk", virCode);
            Log.d("111xt", VersionTools.getVersion(getActivity()));
            if (!VersionTools.getVersion(getActivity()).equals(virCode)) {
                UpdateHelper
                        .getInstance()
                        .setCheckType(UpdateHelper.CheckType.check_with_Dialog)
                        .setUpdateListener(false, new UpdateListener() {
                            @Override
                            public void Update(boolean update, UpdateEntity updateEntity) {
                                if (update) {
                                    Toast.makeText(getActivity(), "发现新版本", Toast.LENGTH_SHORT).show();
                                } else {
                                    Toast.makeText(getActivity(), "未发现新版本", Toast.LENGTH_SHORT).show();
                                }
                            }
                        })
                        .check(getActivity());
            } else {
                Log.d("update", "当前最新版本");
            }
        }
    };

}

