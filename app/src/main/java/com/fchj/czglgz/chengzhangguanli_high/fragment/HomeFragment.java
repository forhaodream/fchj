package com.fchj.czglgz.chengzhangguanli_high.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Looper;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
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
import com.fchj.czglgz.chengzhangguanli_high.activity.SchoolNewsActivity;
import com.fchj.czglgz.chengzhangguanli_high.activity.ZhuanJiaActivity;
import com.fchj.czglgz.chengzhangguanli_high.activity.ZiXunActivity;
import com.fchj.czglgz.chengzhangguanli_high.activity.ZiXunDetailActivity;
import com.fchj.czglgz.chengzhangguanli_high.adapter.AutoSwitchAdapter;
import com.fchj.czglgz.chengzhangguanli_high.adapter.ZiXunAdapter;
import com.fchj.czglgz.chengzhangguanli_high.model.BannerModel;
import com.fchj.czglgz.chengzhangguanli_high.model.HomeZXModel;
import com.fchj.czglgz.chengzhangguanli_high.model.VersionModel;
import com.fchj.czglgz.chengzhangguanli_high.model.ZiXunModel;
import com.fchj.czglgz.chengzhangguanli_high.util.IntentUtil;
import com.fchj.czglgz.chengzhangguanli_high.util.Url;
import com.fchj.czglgz.chengzhangguanli_high.view.AutoSwitchView;
import com.fchj.czglgz.chengzhangguanli_high.zixun.ZXFragAdapter;
import com.fchj.czglgz.chengzhangguanli_high.zixun.ZXFragModel;
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
import java.util.List;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by Administrator on 2018/4/9 0009.
 */

public class HomeFragment extends Fragment implements View.OnClickListener {
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

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_home, null);
        mHandler = new Handler();
        mContext = getActivity();
        mScrollView = (ScrollView) view.findViewById(R.id.home_scroll);
        username = getActivity().getSharedPreferences("user_npt", MODE_PRIVATE);
        userId = username.getInt("userId", 0);
        // Banner
        mAutoSwitchView = (AutoSwitchView) view.findViewById(R.id.home_banner);
        toKaImg = (ImageView) view.findViewById(R.id.home_to_ka);
        toKaImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                IntentUtil.showIntent(getActivity(), KaActivity.class);
            }
        });
        mScrollView = (ScrollView) view.findViewById(R.id.home_scroll);
        initView();
        xiaZX();
        addBanner();
//        addList();
        mAutoSwitchView.setFocusable(true);
      //  viewFlipper = (ViewFlipper) view.findViewById(R.id.viewFlipper);
//        initData();
//        setViews();
        return view;
    }

    private void initView() {
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
                IntentUtil.showIntent(getActivity(), SchoolNewsActivity.class);
                break;


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
            public void onResponse(Response response) {
                try {
                    if (response.isSuccessful()) {
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

    Runnable mBannerRunn = new Runnable() {
        @Override
        public void run() {
            mAdapter = new AutoSwitchAdapter(getActivity(), mBannerModel.getData());
            mAutoSwitchView.setAdapter(mAdapter);

        }
    };


    private void update() {
        OkHttpClient okHttpClient = new OkHttpClient();
        Request request = new Request.Builder().url("http://www.pp11.cn/wap/json/shengji.aspx").build();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {

            }

            @Override
            public void onResponse(Response response) throws IOException {
                String body = response.body().string();
                Log.d("没有", body);
                if (!TextUtils.isEmpty(body)) {
                    Gson gson = new Gson();
                    JsonReader reader = new JsonReader(new StringReader(body));
                    mVersionModel = gson.fromJson(reader, VersionModel.class);
                    mHandler.post(virRunn);
                } else {
                    Log.d("没有", "数据");
                }
            }
        });

    }

    Runnable virRunn = new Runnable() {
        @Override
        public void run() {
//            virCode = mVersionModel.getBanben();
//            Log.d("111jk", virCode);
//            Log.d("111xt", VersionTools.getVersion(getActivity()));
//            // !VersionTools.getVersion(getActivity()).equals(virCode)
//            if (Float.parseFloat(virCode) > Float.parseFloat(VersionTools.getVersion(getActivity()))) {
//                Log.d("sdadasdadas", String.valueOf(Float.parseFloat(VersionTools.getVersion(getActivity()))));
//                Log.d("sdadasdadas", String.valueOf(Float.parseFloat(virCode)));
//
//                UpdateHelper
//                        .getInstance()
//                        .setCheckType(UpdateHelper.CheckType.check_with_Dialog)
//                        .setUpdateListener(false, new UpdateListener() {
//                            @Override
//                            public void Update(boolean update, UpdateEntity updateEntity) {
//                                if (update) {
//                                    Toast.makeText(getActivity(), "发现新版本", Toast.LENGTH_SHORT).show();
//                                } else {
//                                    Toast.makeText(getActivity(), "未发现新版本", Toast.LENGTH_SHORT).show();
//                                }
//                            }
//                        })
//                        .check(getActivity());
//            }
        }
    };


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
            public void onResponse(Response response) {
                try {
                    if (response.isSuccessful()) {
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

    private void addList() {

        FormEncodingBuilder builder = new FormEncodingBuilder();
        builder.add("infotype", "3");
        builder.add("title", "");
        builder.add("pageNum", "1");
        builder.add("pageSize", "30");
        Request request = new Request.Builder().url(Url.pageZXUrl).post(builder.build()).build();
        OkHttpClient okHttpClient = new OkHttpClient();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {

            }

            @Override
            public void onResponse(Response response) throws IOException {
                String body = response.body().string();
                Gson gson = new Gson();
                JsonReader reader = new JsonReader(new StringReader(body));
                mZXFragModel = gson.fromJson(reader, ZXFragModel.class);
                Log.d("sdadsadas", body);
                if (mZXFragModel.getCode() == 1) {
                    mHandler.post(new Runnable() {
                        @Override
                        public void run() {

                            mData = mZXFragModel.getData();
                            Log.d("sdadsadas", mData.size() + "");
                            setViews();


                        }
                    });
                } else {
                    Log.d("没有", "数据");
                }


            }
        });
    }

    /**
     * 初始化新闻标题数据
     */
    private void initData() {
        titles = new ArrayList();
        titles.add("日本老年犯罪严重，监狱成老人无奈归宿");
        titles.add("美机闯香港空域");
        titles.add("大学教授亮工资条");
        titles.add("英国空军轰炸IS");
        titles.add("三连败收官！柯洁再负AlphaGo，人机大战遭零封");
        titles.add("女子爬山遭雷劈晕");
        titles.add("电信诈骗现新骗局");
    }

    /**
     * 为每一页设置视图
     */
    private void setViews() {
        int viewNum;
        if (titles.size() > 0) {
            //计算ViewFlipper视图的数目
            viewNum = titles.size() / 2 + 1;
            Log.d("viewNum", viewNum + "");
            for (int i = 0; i < viewNum; i++) {
                //每一个视图的第一个新闻标题中集合中的下标值
                final int position = i * 2;
                View itemView = View.inflate(mContext, R.layout.title_view, null);
                TextView tvTitle1 = (TextView) itemView.findViewById(R.id.tv_title1);
//                TextView tvTitle2 = (TextView) itemView.findViewById(R.id.tv_title2);
//                LinearLayout ll = (LinearLayout) itemView.findViewById(R.id.ll_second);
                tvTitle1.setText(titles.get(position));
                Log.d("tvtitle1", titles.get(position));

                //判断第二行是否有数据
//                if (position + 1 < titles.size()) {
//                    tvTitle2.setText(titles.get(position + 1));
//                } else {
//                    //表示该视图的第二个标题没有数据，隐藏第二行布局
//                    ll.setVisibility(View.GONE);
//                }

                //标题1的点击事件
                tvTitle1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(mContext, mData.get(position).getTitle() + "", Toast.LENGTH_SHORT).show();
                    }
                });

//                //标题2的点击事件
//                tvTitle2.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        Toast.makeText(mContext, mData.get(position + 1).getTitle() + "", Toast.LENGTH_SHORT).show();
//                    }
//                });

                viewFlipper.addView(itemView);
            }
            //视图进入动画
            viewFlipper.setInAnimation(mContext, R.anim.news_in);
            //视图退出动画
            viewFlipper.setOutAnimation(mContext, R.anim.news_out);
            //自动开始滚动
            viewFlipper.setAutoStart(true);
            //视图的切换间隔
            viewFlipper.setFlipInterval(3000);
//            viewFlipper.startFlipping();
        }
    }
//    private void setViews() {
//        int viewNum;
//        if (mData.size() > 0) {
//            //计算ViewFlipper视图的数目
//            viewNum = mData.size() / 2;
//            Log.d("viewNum", viewNum + "");
//            for (int i = 0; i < viewNum; i++) {
//                //每一个视图的第一个新闻标题中集合中的下标值
//                final int position = i * 2;
//                View itemView = View.inflate(mContext, R.layout.title_view, null);
//                TextView tvTitle1 = (TextView) itemView.findViewById(R.id.tv_title1);
//                TextView tvTitle2 = (TextView) itemView.findViewById(R.id.tv_title2);
//                LinearLayout ll = (LinearLayout) itemView.findViewById(R.id.ll_second);
//                tvTitle1.setText(mData.get(position).getTitle() + "");
//                Log.d("tvtitle1", mData.get(position).getTitle() + "");
//
//                //判断第二行是否有数据
//                if (position + 1 < mData.size()) {
//                    tvTitle2.setText(mData.get(position + 1).getTitle() + "");
//                } else {
//                    //表示该视图的第二个标题没有数据，隐藏第二行布局
//                    ll.setVisibility(View.GONE);
//                }
//
//                //标题1的点击事件
//                tvTitle1.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        Toast.makeText(mContext, mData.get(position).getTitle() + "", Toast.LENGTH_SHORT).show();
//                    }
//                });
//
//                //标题2的点击事件
//                tvTitle2.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        Toast.makeText(mContext, mData.get(position + 1).getTitle() + "", Toast.LENGTH_SHORT).show();
//                    }
//                });
//
//                viewFlipper.addView(itemView);
//            }
//            //视图进入动画
//            viewFlipper.setInAnimation(mContext, R.anim.news_in);
//            //视图退出动画
//            viewFlipper.setOutAnimation(mContext, R.anim.news_out);
//            //自动开始滚动
//            viewFlipper.setAutoStart(true);
//            //视图的切换间隔
//            viewFlipper.setFlipInterval(3000);
////            viewFlipper.startFlipping();
//        }
//    }

}
