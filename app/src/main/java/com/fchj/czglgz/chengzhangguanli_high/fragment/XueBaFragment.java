package com.fchj.czglgz.chengzhangguanli_high.fragment;

import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.fchj.czglgz.chengzhangguanli_high.R;
import com.fchj.czglgz.chengzhangguanli_high.activity.KaActivity;
import com.fchj.czglgz.chengzhangguanli_high.activity.ZiXunDetailActivity;
import com.fchj.czglgz.chengzhangguanli_high.util.IntentUtil;
import com.fchj.czglgz.chengzhangguanli_high.util.Url;
import com.fchj.czglgz.chengzhangguanli_high.xuebalaile.XueBaAdapter;
import com.fchj.czglgz.chengzhangguanli_high.xuebalaile.XueBaDetailActivity;
import com.fchj.czglgz.chengzhangguanli_high.xuebalaile.XueBaModel;
import com.fchj.czglgz.chengzhangguanli_high.xuebalaile.XuebaDetialModel;
import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import com.lh.ch.pulltorefresh.PullToRefreshLayout;
import com.squareup.okhttp.Call;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;
import java.io.StringReader;
import java.util.List;

import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayerStandard;

import static android.content.Context.SENSOR_SERVICE;

/**
 * Created by Administrator on 2018/4/9 0009.
 */

public class XueBaFragment extends Fragment {
    private View view;
    private XueBaModel mXueBaModel;
    private Handler mHandler;
    private ListView mListView;
    private List<XueBaModel.DataBean> mData;
    private List<XueBaModel.DataBean> pageData;
    private int itemId = 1;
    private XueBaAdapter mXueBaAdapter;
    private ImageView toKaImg;
    private SensorManager sensorManager;
    private SensorEventListener sensorEventListener;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_xueba, null);
        mHandler = new Handler();
        addList();
        mListView = (ListView) view.findViewById(R.id.xb_frag_list_view);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent toDetail = new Intent(getActivity(), XueBaDetailActivity.class);
                toDetail.putExtra("sid", mData.get(position).getId());
                startActivity(toDetail);

            }
        });

//        toKaImg = (ImageView) view.findViewById(R.id.home_to_ka);
//        toKaImg.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                IntentUtil.showIntent(getActivity(), KaActivity.class);
//            }
//        });
        sensorManager = (SensorManager) getActivity().getSystemService(SENSOR_SERVICE);
        sensorEventListener = new JCVideoPlayer.JCAutoFullscreenListener();

        PullToRefreshLayout ptr = (PullToRefreshLayout) view.findViewById(R.id.xb_frag_pullrefresh);
        mListView = (ListView) ptr.getPullableView();
        ptr.setOnPullListener(new PullToRefreshLayout.OnPullListener() {
            @Override
            public void onRefresh(PullToRefreshLayout pullToRefreshLayout) {
                addList();
                pullToRefreshLayout.refreshFinish(0);
            }

            @Override
            public void onLoadMore(PullToRefreshLayout pullToRefreshLayout) {
                itemId += 1;
                addPageCheck();
                pullToRefreshLayout.loadmoreFinish(0);
            }
        });
        return view;
    }

    private void addList() {
        FormEncodingBuilder builder = new FormEncodingBuilder();
        builder.add("type", "1");
        builder.add("pageNum", "1");
        builder.add("pageSize", "10");
        Request request = new Request.Builder().url(Url.xuebaListUrl).post(builder.build()).build();
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
                        JsonReader reader = new JsonReader(new StringReader(body));
                        Gson gson = new Gson();
                        mXueBaModel = gson.fromJson(reader, XueBaModel.class);
                        Log.d("sdadsadas", body);
//                if (!TextUtils.isEmpty(mXueBaModel.getData().toString())) {
                        if (mXueBaModel.getCode() == 1) {

                            mHandler.post(new Runnable() {
                                @Override
                                public void run() {
                                    mData = mXueBaModel.getData();
                                    mXueBaAdapter = new XueBaAdapter(mData);
                                    mListView.setAdapter(mXueBaAdapter);

                                }
                            });
                        } else if (mXueBaModel.getCode() == 2) {
                            Looper.prepare();
                            Toast.makeText(getActivity(), mXueBaModel.getMsg() + "", Toast.LENGTH_SHORT).show();
                            Looper.loop();
                        } else {
                            Looper.prepare();
                            Toast.makeText(getActivity(), mXueBaModel.getMsg() + "", Toast.LENGTH_SHORT).show();
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

    private void addPageCheck() {
        FormEncodingBuilder builder = new FormEncodingBuilder();
        builder.add("pageNum", String.valueOf(itemId));
        builder.add("type", "1");
        builder.add("pageSize", "10");
        Request request = new Request.Builder().url(Url.xuebaListUrl).post(builder.build()).build();
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
                        mXueBaModel = gson.fromJson(reader, XueBaModel.class);
                        pageData = mXueBaModel.getData();
                        if (mXueBaModel.getCode() == 1) {

                            mHandler.post(pageRunn);
                        } else if (mXueBaModel.getCode() == 2) {
                            Looper.prepare();
                            Toast.makeText(getActivity(), mXueBaModel.getMsg() + "", Toast.LENGTH_SHORT).show();
                            Looper.loop();
                        } else {
                            Looper.prepare();
                            Toast.makeText(getActivity(), mXueBaModel.getMsg() + "", Toast.LENGTH_SHORT).show();
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

    Runnable pageRunn = new Runnable() {
        @Override
        public void run() {
            if (pageData.size() > 0) {
                mData.addAll(pageData);
                mXueBaAdapter.notifyDataSetChanged();
            } else {
                mData.clear();
                mXueBaAdapter.notifyDataSetChanged();
                Log.d("没有", "数据");
            }
        }
    };

    @Override
    public void onResume() {
        super.onResume();
        getView().setFocusableInTouchMode(true);
        getView().requestFocus();
        getView().setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (event.getAction() == KeyEvent.ACTION_UP && keyCode == KeyEvent.KEYCODE_BACK) {
                    return JCVideoPlayer.backPress();
                }
                return false;
            }
        });
        Sensor accelerometerSensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        sensorManager.registerListener(sensorEventListener, accelerometerSensor, SensorManager.SENSOR_DELAY_NORMAL);
    }


    @Override
    public void onPause() {
        super.onPause();
        sensorManager.unregisterListener(sensorEventListener);
        JCVideoPlayer.releaseAllVideos();
    }

//    class XueBaAdapter extends BaseAdapter {
//        private List<XueBaModel.DataBean> mData;
//
//        public XueBaAdapter(List<XueBaModel.DataBean> data) {
//            this.mDat a = data;
//        }
//
//        @Override
//        public int getCount() {
//            return mData.size();
//        }
//
//        @Override
//        public Object getItem(int position) {
//            return mData.get(position);
//        }
//
//        @Override
//        public long getItemId(int position) {
//            return position;
//        }
//
//        @Override
//        public View getView(int position, View convertView, ViewGroup parent) {
//            ViewHolder holder = null;
//            if (convertView == null) {
//                convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_video_list, null);
//                holder = new ViewHolder();
//                holder.mJCVideoPlayerStandard = (JCVideoPlayerStandard) convertView.findViewById(R.id.item_video_jiecao);
//                holder.titleTv = (TextView) convertView.findViewById(R.id.item_video_title);
//                convertView.setTag(holder);
//            } else {
//                holder = (ViewHolder) convertView.getTag();
//            }
//
//            holder.titleTv.setText(mData.get(position).getVoidname());
//            holder.mJCVideoPlayerStandard.setUp(mData.get(position).getContentvoidurl(), JCVideoPlayer.SCREEN_LAYOUT_NORMAL, "");
//            holder.mJCVideoPlayerStandard.startButton.performClick();
//            /**
//             *   Picasso.with(this)
//             .load("http://img4.jiecaojingxuan.com/2016/11/23/00b026e7-b830-4994-bc87-38f4033806a6.jpg@!640_360")
//             .into(mJcVideoPlayerStandard.thumbImageView);
//             */
//            Glide.with(holder.mJCVideoPlayerStandard.thumbImageView.getContext()).load(Url.fileUrl + mData.get(position).getPreviewimageurl()).into(holder.mJCVideoPlayerStandard.thumbImageView);
//
//            return convertView;
//        }
//
//        class ViewHolder {
//            TextView titleTv;
//            JCVideoPlayerStandard mJCVideoPlayerStandard;
//
//        }
//    }

}
