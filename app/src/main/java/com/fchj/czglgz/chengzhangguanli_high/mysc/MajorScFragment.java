package com.fchj.czglgz.chengzhangguanli_high.mysc;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.fchj.czglgz.chengzhangguanli_high.R;
import com.fchj.czglgz.chengzhangguanli_high.major.MajorDetailActivity;
import com.fchj.czglgz.chengzhangguanli_high.util.IntentUtil;
import com.fchj.czglgz.chengzhangguanli_high.util.Url;
import com.fchj.czglgz.chengzhangguanli_high.vocation.VocaScModel;
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

import static android.content.Context.MODE_PRIVATE;

public class MajorScFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {
    private Handler mHandler;
    private ListView mListView;
    List<ScMajorModel.DataBean> mDataBeanList;
    private SharedPreferences usernameSp;
    private int userId;
    View view;
    private VocaScRlcAdapter mVocaScRlcAdapter;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private RecyclerView mRecyclerView;
    private ScMajorModel mScMajorModel;
    private MajorScAdapter mMajorScAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_voca_sc, null);
        mHandler = new Handler();
        usernameSp = getActivity().getSharedPreferences("user_npt", MODE_PRIVATE);
        userId = usernameSp.getInt("userId", 0);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.frag_voca_recycle);
        mSwipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swiperefresh);
        mSwipeRefreshLayout.setOnRefreshListener(this);
        mSwipeRefreshLayout.setColorSchemeResources(R.color.dark_blue);
        getVocaData();
        return view;
    }

    private void getVocaData() {
        FormEncodingBuilder builder = new FormEncodingBuilder();
        builder.add("userId", String.valueOf(userId));
        Request request = new Request.Builder().url(Url.zyScListUrl).post(builder.build()).build();
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
                        Log.d("sdadsadas", body);
                        Gson gson = new Gson();
                        JsonReader reader = new JsonReader(new StringReader(body));
                        mScMajorModel = gson.fromJson(reader, ScMajorModel.class);

                        if (mScMajorModel.getCode() == 1) {
                            mHandler.post(new Runnable() {
                                @Override
                                public void run() {
                                    mSwipeRefreshLayout.setRefreshing(false);
                                    mDataBeanList = mScMajorModel.getData();
                                    mMajorScAdapter = new MajorScAdapter(mDataBeanList);
                                    mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
                                    mRecyclerView.setAdapter(mMajorScAdapter);
                                    mMajorScAdapter.setOnItemClickListener(new MajorScAdapter.OnItemClickListener() {
                                        @Override
                                        public void onItemClick(MajorScAdapter.ViewHolder holder, int position, String vcid) {
                                            addSc(mDataBeanList.get(position).getMajid(), position);
                                        }

                                        @Override
                                        public void onToDetail(View view, int position) {
                                            Intent toDetail = new Intent(getActivity(), MajorDetailActivity.class);
                                            toDetail.putExtra("zy_name", mDataBeanList.get(position).getMajors().getMajorname());
                                            toDetail.putExtra("zy_type", mDataBeanList.get(position).getMajors().getCoursetype());
                                            startActivity(toDetail);

                                        }
                                    });

//                                    mVocaScRlcAdapter = new VocaScRlcAdapter(mDataBeanList);
//                                    mRecyclerView.setAdapter(mVocaScRlcAdapter);
                                }
                            });
                        } else if (mScMajorModel.getCode() == 2) {
                            Looper.prepare();
                            Toast.makeText(getActivity(), mScMajorModel.getMsg() + "", Toast.LENGTH_SHORT).show();
                            Looper.loop();
                        } else {
                            Looper.prepare();
                            Toast.makeText(getActivity(), mScMajorModel.getMsg() + "", Toast.LENGTH_SHORT).show();
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
    public void onRefresh() {
        getVocaData();


    }

    class VocaScRlcAdapter extends RecyclerView.Adapter<VocaScRlcAdapter.ViewHolder> {
        private List<ScMajorModel.DataBean> mData;

        public VocaScRlcAdapter(List<ScMajorModel.DataBean> data) {
            this.mData = data;
            Log.d("aaaaa", data.size() + "");
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_voca_sc_detail, parent, false);
            ViewHolder viewHolder = new ViewHolder(view);
            return viewHolder;
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, final int position) {
            holder.nameTv.setText(mData.get(position).getMajors().getMajorname().toString() + "");
            holder.includeTv.setText(mData.get(position).getMajors().getMajorgenre().toString() + "");
            final ViewHolder finalHolder = holder;
            holder.sctitle.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {


                }
            });
        }

        @Override
        public int getItemCount() {
            return mData == null ? 0 : mData.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
            TextView nameTv, includeTv;
            ImageView headImg;
            Button sctitle;
            //  private MyItemClickListener mListener;

            public ViewHolder(final View itemView) { //, MyItemClickListener myItemClickListener
                super(itemView);
                //将全局的监听赋值给接口
//                this.mListener = myItemClickListener;
                itemView.setOnClickListener(this);
                nameTv = (TextView) itemView.findViewById(R.id.item_vocation_name);
                includeTv = (TextView) itemView.findViewById(R.id.item_vocation_type);
                sctitle = (Button) itemView.findViewById(R.id.sc_title);

            }

            @Override
            public void onClick(View v) {

            }
        }

        // 删除数据
        public void removeData(int position) {
            mData.remove(position);
            notifyItemRemoved(position);
            notifyDataSetChanged();
        }

    }

//    class VocaScAdapter extends BaseAdapter {
//
//        private List<ScMajorModel.DataBean> mData;
//
//        public VocaScAdapter(List<ScMajorModel.DataBean> data) {
//            this.mData = data;
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
//        @SuppressLint("ResourceAsColor")
//        @Override
//        public View getView(final int position, View convertView, ViewGroup parent) {
//            ViewHolder holder = null;
//            if (convertView == null) {
//                convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_voca_sc_detail, null);
//                holder = new ViewHolder();
//                holder.titleTv = (TextView) convertView.findViewById(R.id.item_vocation_name);
//                holder.typeTv = (TextView) convertView.findViewById(R.id.item_vocation_type);
//                holder.mCardView = (CardView) convertView.findViewById(R.id.cardView);
//                holder.sctitle = (Button) convertView.findViewById(R.id.sc_title);
//                convertView.setTag(holder);
//            } else {
//                holder = (ViewHolder) convertView.getTag();
//            }
//            holder.titleTv.setText(mData.get(position).getVoc().getVocaname() + "");
//            holder.typeTv.setText(mData.get(position).getVoc().getVocatype() + "");
////            if (mData.get(position).getVoc().getIsfollow() == 0) {
////                holder.sctitle.setBackgroundResource(R.drawable.sc_circle);
////                holder.sctitle.setText("关注");
////            } else {
////                holder.sctitle.setBackgroundResource(R.drawable.sc_gray_circle);
////                holder.sctitle.setText("已关注");
////            }
//            final ViewHolder finalHolder = holder;
//            holder.sctitle.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    addSc(mData.get(position).getVctid(), finalHolder.sctitle);
//                }
//            });
//            return convertView;
//        }
//
//        class ViewHolder {
//            Button sctitle;
//            TextView titleTv, typeTv;
//            CardView mCardView;
//        }
//    }

    private VocaScModel mVocaScModel;

    private void addSc(final String pos, final int position) {
        Log.d("pos", pos);
        FormEncodingBuilder builder = new FormEncodingBuilder();
        builder.add("majid", pos);
        builder.add("userId", String.valueOf(userId));
        Request request = new Request.Builder().url(Url.addMajScUrl).post(builder.build()).build();
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
                        Log.d("vocation", body);
                        Gson gson = new Gson();
                        JsonReader reader = new JsonReader(new StringReader(body));
                        mVocaScModel = gson.fromJson(reader, VocaScModel.class);
                        if (mVocaScModel.getCode() == 1) {


                        } else if (mVocaScModel.getCode() == 2) {
                            mHandler.post(new Runnable() {
                                @Override
                                public void run() {
                                    mMajorScAdapter.removeData(position);
                                    Toast.makeText(getActivity(), "取消关注", Toast.LENGTH_SHORT).show();

                                }
                            });

                        } else {
                            Looper.prepare();
                            Toast.makeText(getActivity(), mVocaScModel.getMsg() + "", Toast.LENGTH_SHORT).show();
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
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            Log.d("---------a", "1");
            getVocaData();

            //相当于onResume
//            getVocaData();
//            Toast.makeText(getActivity(), "方法1", Toast.LENGTH_SHORT).show();
        } else {
            getVocaData();
            // 相当于onPause
        }
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        LocalBroadcastManager broadcastManager = LocalBroadcastManager.getInstance(getActivity());
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.intent.action.CART_BROADCAST");
        BroadcastReceiver mItemViewListClickReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                String msg = intent.getStringExtra("data");
                if ("refresh".equals(msg)) {
                    Log.d("---------a", "2");
                }
            }
        };
        broadcastManager.registerReceiver(mItemViewListClickReceiver, intentFilter);
    }
}



