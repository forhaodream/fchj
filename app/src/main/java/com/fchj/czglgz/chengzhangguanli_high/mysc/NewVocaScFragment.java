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

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.fchj.czglgz.chengzhangguanli_high.R;
import com.fchj.czglgz.chengzhangguanli_high.util.Url;
import com.fchj.czglgz.chengzhangguanli_high.vocation.VocaScModel;
import com.fchj.czglgz.chengzhangguanli_high.vocation.VocationDetailActivity;
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

public class NewVocaScFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {
    List<MyScModel.DataBean> mDataBeanList;
    private ListView mListView;
    private Handler mHandler;
    private MyScModel mMyScModel;
    private SharedPreferences usernameSp;
    private int userId;
    private View view;
    private VocaScAdapter mVocaScRlcAdapter;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private RecyclerView mRecyclerView;

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
//        initVoid();
        return view;
    }


    private void getVocaData() {
        FormEncodingBuilder builder = new FormEncodingBuilder();
        builder.add("userId", String.valueOf(userId));
        Request request = new Request.Builder().url(Url.scListUrl).post(builder.build()).build();
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
                        Log.d("aaaaaaaaaaa", body);
                        Gson gson = new Gson();
                        JsonReader reader = new JsonReader(new StringReader(body));
                        mMyScModel = gson.fromJson(reader, MyScModel.class);
                        if (mMyScModel.getCode() == 1) {
                            mHandler.post(new Runnable() {
                                @Override
                                public void run() {
                                    mSwipeRefreshLayout.setRefreshing(false);
                                    mDataBeanList = mMyScModel.getData();
                                    Log.d("aaaaaaaaaaa", mDataBeanList.size() + "");
                                    mVocaScRlcAdapter = new VocaScAdapter(mDataBeanList);
                                    mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
                                    mRecyclerView.setAdapter(mVocaScRlcAdapter);
                                    mVocaScRlcAdapter.setOnItemClickListener(new VocaScAdapter.OnItemClickListener() {
                                        @Override
                                        public void onItemClick(VocaScAdapter.ViewHolder holder, int position, String vcid) {
                                            addSc(mDataBeanList.get(position).getVctid(), position);
                                            Toast.makeText(getActivity(), "123", Toast.LENGTH_SHORT).show();
                                        }

                                        @Override
                                        public void onToDetail(View view, int position) {
                                            Intent toDetail = new Intent(getActivity(), VocationDetailActivity.class);
                                            toDetail.putExtra("vocaid", mDataBeanList.get(position).getVctid());
                                            Log.d("vocaid", mDataBeanList.get(position).getVctid());
                                            startActivity(toDetail);
                                        }
                                    });
                                }
                            });
                        } else if (mMyScModel.getCode() == 2) {
                            Looper.prepare();
                            Toast.makeText(getActivity(), mMyScModel.getMsg() + "", Toast.LENGTH_SHORT).show();
                            Looper.loop();
                        } else {
                            Looper.prepare();
                            Toast.makeText(getActivity(), mMyScModel.getMsg() + "", Toast.LENGTH_SHORT).show();
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


//    public class VocaScRlcAdapter extends RecyclerView.Adapter<VocaScRlcAdapter.ViewHolder> {
//
//        public List<MyScModel.DataBean> mData;
//
//        public interface AAAA {
//        }
//
//        public VocaScRlcAdapter(List<MyScModel.DataBean> data) {
//            this.mData = data;
//            Log.d("aaaaa", data.size() + "");
//        }
//
//        @Override
//        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_voca_sc_detail, parent, false);
//            ViewHolder viewHolder = new ViewHolder(view);
//            return viewHolder;
//        }
//
//        @Override
//        public void onBindViewHolder(ViewHolder holder, final int position) {
//            holder.nameTv.setText(mData.get(position).getVoc().getVocaname().toString() + "");//
//            holder.includeTv.setText(mData.get(position).getVoc().getVocatype().toString() + "");//
//            final ViewHolder finalHolder = holder;
//            holder.sctitle.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    addSc(mData.get(position).getVctid());
//                }
//            });
//        }
//
//        @Override
//        public int getItemCount() {
//            return mData == null ? 0 : mData.size();
//        }
//
//        public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
//            TextView nameTv, includeTv;
//            ImageView headImg;
//            Button sctitle;
//
//            public ViewHolder(final View itemView) { //, MyItemClickListener myItemClickListener
//                super(itemView);
//                //将全局的监听赋值给接口
////                this.mListener = myItemClickListener;
//                itemView.setOnClickListener(this);
//                nameTv = (TextView) itemView.findViewById(R.id.item_vocation_name);
//                includeTv = (TextView) itemView.findViewById(R.id.item_vocation_type);
//                sctitle = (Button) itemView.findViewById(R.id.sc_title);
//
//            }
//
//            @Override
//            public void onClick(View v) {
//            }
//
//
//        }
//
//
//        // 删除数据
//        public void removeData(int position) {
//            mData.remove(position);
//            notifyItemRemoved(position);
//            notifyItemRangeChanged(0, mData.size());
//        }
//
//        public void removeItem(int adapterPosition) {
//            if (adapterPosition == RecyclerView.NO_POSITION)
//                return;
//            if (adapterPosition >= 0 && adapterPosition < mData.size()) {  //mDatas为数据集合
//                mData.remove(adapterPosition);
//                notifyItemRemoved(adapterPosition);
//            }
//        }
//    }

//    class VocaScAdapter extends BaseAdapter {
//
//        private List<MyScModel.DataBean> mData;
//
//        public VocaScAdapter(List<MyScModel.DataBean> data) {
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
//            holder.sctitle.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    addSc(mData.get(position).getVctid());
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
//
//        public void deteleItem(int pos){
//            mData.remove(pos);
//            notifyDataSetChanged();
//        }
//
//    }

    private VocaScModel mVocaScModel;

    public void addSc(final String pos, final int itemPos) {
        FormEncodingBuilder builder = new FormEncodingBuilder();
        builder.add("vctid", pos);
        builder.add("userId", String.valueOf(userId));
        Request request = new Request.Builder().url(Url.addScUrl).post(builder.build()).build();
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
                                    mVocaScRlcAdapter.removeData(itemPos);


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

    //    private void initVoid() {
//        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
//            @Override
//            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
//                //当前RecyclerView显示出来的最后一个的item的position
//                int lastPosition = -1;
//                //当前状态为停止滑动状态SCROLL_STATE_IDLE时
//                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
//                    RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
//                    if (layoutManager instanceof GridLayoutManager) {
//                        //通过LayoutManager找到当前显示的最后的item的position
//                        lastPosition = ((GridLayoutManager) layoutManager).findLastVisibleItemPosition();
//                    } else if (layoutManager instanceof LinearLayoutManager) {
//                        lastPosition = ((LinearLayoutManager) layoutManager).findLastVisibleItemPosition();
//                    } else if (layoutManager instanceof StaggeredGridLayoutManager) {
//                        //因为StaggeredGridLayoutManager的特殊性可能导致最后显示的item存在多个，所以这里取到的是一个数组
//                        //得到这个数组后再取到数组中position值最大的那个就是最后显示的position值了
//                        int[] lastPositions = new int[((StaggeredGridLayoutManager) layoutManager).getSpanCount()];
//                        ((StaggeredGridLayoutManager) layoutManager).findLastVisibleItemPositions(lastPositions);
//                        lastPosition = findMax(lastPositions);
//                    }
//                    // 时判断界面显示的最后item的position是否等于itemCount总数-1也就是最后一个item的position
//                    //如果相等则说明已经滑动到最后了
//                    //发送一条广播通知更新数据
//                    if (lastPosition == recyclerView.getLayoutManager().getItemCount() - 1) {
//                        Toast.makeText(getActivity(), "To", Toast.LENGTH_SHORT).show();
//
//                    }
//
//                }
//            }
//
//            @Override
//            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
//
//            }
//        });
//    }
//
//    private int findMax(int[] lastPositions) {
//        int max = lastPositions[0];
//        for (int value : lastPositions) {
//            if (value > max) {
//                max = value;
//            }
//        }
//        return max;
//    }
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            Log.d("---------a", "1");
            //相当于onResume
            getVocaData();
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