package com.fchj.czglgz.chengzhangguanli_high.major;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.fchj.czglgz.chengzhangguanli_high.R;
import com.fchj.czglgz.chengzhangguanli_high.mysc.MyScModel;
import com.fchj.czglgz.chengzhangguanli_high.mysc.VocaScAdapter;
import com.fchj.czglgz.chengzhangguanli_high.util.FindLastItem;
import com.fchj.czglgz.chengzhangguanli_high.util.Url;
import com.fchj.czglgz.chengzhangguanli_high.volunteer.CollegeListDetailActivity;
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

@SuppressLint("ValidFragment")
public class AboutCollegeFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {
    private View mView;
    private RecyclerView mRecyclerView;
    private Handler mHandler;
    private MyScModel mMyScModel;
    private SharedPreferences usernameSp;
    private int userId;
    private VocaScAdapter mVocaScRlcAdapter;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private int itemId = 1;
    private AboutCollegeModel mAboueCollegeModel;
    private List<AboutCollegeModel.DataBean> mData;
    private List<AboutCollegeModel.DataBean> pageData;
    private AboutCollegeAdapter mAboutCollegeAdapter;
    private String title, typeStr;

    public AboutCollegeFragment(String title, String type) {
        this.title = title;
        this.typeStr = type;
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_about_college, null);
        mRecyclerView = (RecyclerView) mView.findViewById(R.id.frag_about_college_rlc);
        FindLastItem findLastItem = new FindLastItem(mRecyclerView, getContext());
        findLastItem.refresh();
        mHandler = new Handler();
        usernameSp = getActivity().getSharedPreferences("user_npt", MODE_PRIVATE);
        userId = usernameSp.getInt("userId", 0);
        mSwipeRefreshLayout = (SwipeRefreshLayout) mView.findViewById(R.id.ac_swiperefresh);
        mSwipeRefreshLayout.setOnRefreshListener(this);
        mSwipeRefreshLayout.setColorSchemeResources(R.color.dark_blue);
        addList();
        initVoid();
        return mView;
    }


    // 获取recycle的lastitem
    private void initVoid() {
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                //当前RecyclerView显示出来的最后一个的item的position
                int lastPosition = -1;
                //当前状态为停止滑动状态SCROLL_STATE_IDLE时
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
                    if (layoutManager instanceof GridLayoutManager) {
                        //通过LayoutManager找到当前显示的最后的item的position
                        lastPosition = ((GridLayoutManager) layoutManager).findLastVisibleItemPosition();
                    } else if (layoutManager instanceof LinearLayoutManager) {
                        lastPosition = ((LinearLayoutManager) layoutManager).findLastVisibleItemPosition();
                    } else if (layoutManager instanceof StaggeredGridLayoutManager) {
                        //因为StaggeredGridLayoutManager的特殊性可能导致最后显示的item存在多个，所以这里取到的是一个数组
                        //得到这个数组后再取到数组中position值最大的那个就是最后显示的position值了
                        int[] lastPositions = new int[((StaggeredGridLayoutManager) layoutManager).getSpanCount()];
                        ((StaggeredGridLayoutManager) layoutManager).findLastVisibleItemPositions(lastPositions);
                        lastPosition = findMax(lastPositions);
                    }
                    // 时判断界面显示的最后item的position是否等于itemCount总数-1也就是最后一个item的position
                    //如果相等则说明已经滑动到最后了
                    //发送一条广播通知更新数据
                    if (lastPosition == recyclerView.getLayoutManager().getItemCount() - 1 && recyclerView.getLayoutManager().getItemCount() != 1) {
                        itemId += 1;
                        addPageCheck();
                        //Toast.makeText(getActivity(), "加载", Toast.LENGTH_SHORT).show();

                    }

                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {

            }
        });
    }

    private int findMax(int[] lastPositions) {
        int max = lastPositions[0];
        for (int value : lastPositions) {
            if (value > max) {
                max = value;
            }
        }
        return max;
    }

    @Override
    public void onRefresh() {
        addList();
    }

    private void addList() {
        FormEncodingBuilder builder = new FormEncodingBuilder();
        Log.d("title", title);
        builder.add("majorname", title + "");
        builder.add("pageNum", "1");
        builder.add("pageSize", "10");
        builder.add("coursetype", typeStr);
        Request request = new Request.Builder().url(Url.getAboutCollegeUrl).post(builder.build()).build();
        OkHttpClient okHttpClient = new OkHttpClient();
        final Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {
                Looper.prepare();
                Toast.makeText(getActivity(), "请求失败", Toast.LENGTH_SHORT).show();
                Looper.loop();


            }

            @Override
            public void onResponse(Response response) {
                try {
                    if (response.isSuccessful()) {
                        String body = response.body().string();
                        Log.d("sdadsadas", body);
                        Gson gson = new Gson();
                        JsonReader reader = new JsonReader(new StringReader(body));
                        mAboueCollegeModel = gson.fromJson(reader, AboutCollegeModel.class);
                        if (mAboueCollegeModel.getCode() == 1) {

                            mHandler.post(new Runnable() {
                                @Override
                                public void run() {

                                    mSwipeRefreshLayout.setRefreshing(false);
                                    mData = mAboueCollegeModel.getData();
                                    mAboutCollegeAdapter = new AboutCollegeAdapter(mData);
                                    mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
                                    mRecyclerView.setAdapter(mAboutCollegeAdapter);
                                    mAboutCollegeAdapter.setOnItemClickListener(new AboutCollegeAdapter.OnItemClickListener() {
                                        @Override
                                        public void onItemClick(AboutCollegeAdapter.ViewHolder holder, int position, String vcid) {


                                        }

                                        @Override
                                        public void onToDetail(View view, int position) {
                                            Intent toCollege = new Intent(getActivity(), CollegeListDetailActivity.class);
                                            toCollege.putExtra("guid", mData.get(position).getUniversityname() + "");
                                            startActivity(toCollege);
                                        }
                                    });

                                }
                            });
                        } else if (mAboueCollegeModel.getCode() == 2) {
                            Looper.prepare();
                            Toast.makeText(getActivity(), mAboueCollegeModel.getMsg(), Toast.LENGTH_SHORT).show();
                            Looper.loop();
                        } else {
                            Looper.prepare();
                            Toast.makeText(getActivity(), mAboueCollegeModel.getMsg(), Toast.LENGTH_SHORT).show();
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
        builder.add("pageSize", "10");
        builder.add("majorname", title + "");
        builder.add("coursetype", "本科专业");
        Request request = new Request.Builder().url(Url.getAboutCollegeUrl).post(builder.build()).build();
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
                        mAboueCollegeModel = gson.fromJson(reader, AboutCollegeModel.class);
                        pageData = mAboueCollegeModel.getData();
                        if (mAboueCollegeModel.getCode() == 1) {
                            mHandler.post(pageRunn);
                        } else if (mAboueCollegeModel.getCode() == 2) {
                            Looper.prepare();
                            Toast.makeText(getActivity(), mAboueCollegeModel.getMsg(), Toast.LENGTH_SHORT).show();
                            Looper.loop();
                        } else {
                            Looper.prepare();
                            Toast.makeText(getActivity(), mAboueCollegeModel.getMsg(), Toast.LENGTH_SHORT).show();
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
                mAboutCollegeAdapter.notifyDataSetChanged();
            } else {
                mData.clear();
                mAboutCollegeAdapter.notifyDataSetChanged();
                Log.d("没有", "数据");
            }
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
