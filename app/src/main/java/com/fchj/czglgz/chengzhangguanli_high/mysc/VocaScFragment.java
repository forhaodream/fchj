package com.fchj.czglgz.chengzhangguanli_high.mysc;


import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.Toast;

import com.fchj.czglgz.chengzhangguanli_high.R;
import com.fchj.czglgz.chengzhangguanli_high.jiazhangketang.JZAdapter;
import com.fchj.czglgz.chengzhangguanli_high.major.ZhuanKeAdapter;
import com.fchj.czglgz.chengzhangguanli_high.util.Url;
import com.fchj.czglgz.chengzhangguanli_high.volunteer.CollegeListDetailModel;
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
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static android.content.Context.MODE_PRIVATE;

public class VocaScFragment extends Fragment {
    private Handler mHandler;
    private ExpandableListView mExpandableListView;
    private ArrayList<GroupBean> mGroupBeans;
    private ArrayList<List> mChildrenList;
    private ScExAdapter mScExAdapter;
    private CollegeListDetailModel mDetailModel;
    private List<CollegeListDetailModel.DataBean.LuadtiBean> mLuadtiBeans;
    private String mGroupDatas;
    private CollegeListDetailModel.DataBean mTestData;
    private MyScModel mMyScModel;
    private List<MyScModel.DataBean> mNameData;
    private List<MyScModel.DataBean> mMajorData;

    private SharedPreferences usernameSp;
    private int userId;
    View view;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_voca_sc, null);
        mHandler = new Handler();
      //  mExpandableListView = (ExpandableListView) view.findViewById(R.id.frag_voca_expand);
        usernameSp = getActivity().getSharedPreferences("user_npt", MODE_PRIVATE);
        userId = usernameSp.getInt("userId", 0);
        //  mScExAdapter = new ScExAdapter(mGroupBeans, mChildrenList, getActivity());
        //  mExpandableListView.setAdapter(mScExAdapter);
        mExpandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                Toast.makeText(getActivity(), "点击", Toast.LENGTH_SHORT).show();
                return true;
            }
        });
//        for (int i = 0; i < mScExAdapter.getGroupCount(); i++) {
//            mScExAdapter.onGroupExpanded(i);
//        }
        getVocaData();
        // initData();
        return view;
    }

    private void initData() {
        mGroupBeans = new ArrayList<>();
        mChildrenList = new ArrayList<>();
        mGroupBeans.add(new GroupBean("农、林、牧、渔业"));
        mGroupBeans.add(new GroupBean("采矿业"));
        mGroupBeans.add(new GroupBean("制造业"));
        mGroupBeans.add(new GroupBean("电力、热力、燃气及水生产和供应业"));
        mGroupBeans.add(new GroupBean("建筑业"));
        mGroupBeans.add(new GroupBean("批发和零售业"));
        mGroupBeans.add(new GroupBean("交通运输、仓储和邮政业"));
        mGroupBeans.add(new GroupBean("住宿和餐饮业"));
        mGroupBeans.add(new GroupBean("信息传输、软件和信息技术服务业"));
        mGroupBeans.add(new GroupBean("金融业"));
        mGroupBeans.add(new GroupBean("房地产业"));
        mGroupBeans.add(new GroupBean("租赁和商户服务业"));
        mGroupBeans.add(new GroupBean("科学研究和技术服务业"));
        mGroupBeans.add(new GroupBean("水利、环境和公共设施管理业"));
        mGroupBeans.add(new GroupBean("居民服务、修理和其他服务业"));
        mGroupBeans.add(new GroupBean("教育"));
        mGroupBeans.add(new GroupBean("卫生和社会工作"));
        mGroupBeans.add(new GroupBean("文化、体育和娱乐业"));
        mGroupBeans.add(new GroupBean("公共管理、社会保障和社会组织"));
        mGroupBeans.add(new GroupBean("通用或其他"));
        List<Classes> classesList = new ArrayList<>();
        for (int i = 1; i < mGroupBeans.size(); i++) {
            Classes classes = new Classes();

            classes.name = mGroupBeans.get(i).getTitle();


            classes.student = aaa;

            classesList.add(classes);
        }
        //mExpandableListView = (ExpandableListView) view.findViewById(R.id.frag_benke_expand);
        ZhuanKeAdapter zhuanKeAdapter = new ZhuanKeAdapter(classesList, getActivity());
        mExpandableListView.setAdapter(zhuanKeAdapter);
        //    addNumList();
//        for (int i = 0; i < 20; i++) {
//            ArrayList aa = new ArrayList();
//            aa.add(new ItemBean("测试数据1"));
//            aa.add(new ItemBean("测试数据2"));
//            aa.add(new ItemBean("测试数据3"));
//            aa.add(new ItemBean("测试数据4"));
//            mChildrenList.add(aa);
//        }

//        ArrayList bb = new ArrayList();
//        bb.add(new ItemBean("bbb1"));
//        bb.add(new ItemBean("bbb2"));
//        bb.add(new ItemBean("bbb3"));
//        bb.add(new ItemBean("bbb4"));
//        mChildrenList.add(bb);
//        ArrayList cc = new ArrayList();
//        cc.add(new ItemBean("ccc1"));
//        cc.add(new ItemBean("ccc2"));
//        cc.add(new ItemBean("ccc3"));
//        cc.add(new ItemBean("ccc4"));
//        mChildrenList.add(cc);

        /**
         * String name = mLuadtiBeans.get(0).getMajor();
         String name1 = mLuadtiBeans.get(1).getMajor();
         String name2 = mLuadtiBeans.get(2).getMajor();
         for (int i = 0; i < 20; i++) {
         ArrayList aa = new ArrayList();
         aa.add(name);
         aa.add(name1);
         aa.add(name2);
         mChildrenList.add(mLuadtiBeans);
         }
         */
    }

    List<String> namelist;
    List<String> majorList;
    List<List> majorLists;
    List<String> aaa;
    List arrList;
    List<MyScModel.DataBean> mDataBeanList;

    private void getVocaData() {
        mGroupBeans = new ArrayList<>();
        mChildrenList = new ArrayList<>();
        mGroupBeans.add(new GroupBean("农、林、牧、渔业"));
        mGroupBeans.add(new GroupBean("采矿业"));
        mGroupBeans.add(new GroupBean("制造业"));
        mGroupBeans.add(new GroupBean("电力、热力、燃气及水生产和供应业"));
        mGroupBeans.add(new GroupBean("建筑业"));
        mGroupBeans.add(new GroupBean("批发和零售业"));
        mGroupBeans.add(new GroupBean("交通运输、仓储和邮政业"));
        mGroupBeans.add(new GroupBean("住宿和餐饮业"));
        mGroupBeans.add(new GroupBean("信息传输、软件和信息技术服务业"));
        mGroupBeans.add(new GroupBean("金融业"));
        mGroupBeans.add(new GroupBean("房地产业"));
        mGroupBeans.add(new GroupBean("租赁和商户服务业"));
        mGroupBeans.add(new GroupBean("科学研究和技术服务业"));
        mGroupBeans.add(new GroupBean("水利、环境和公共设施管理业"));
        mGroupBeans.add(new GroupBean("居民服务、修理和其他服务业"));
        mGroupBeans.add(new GroupBean("教育"));
        mGroupBeans.add(new GroupBean("卫生和社会工作"));
        mGroupBeans.add(new GroupBean("文化、体育和娱乐业"));
        mGroupBeans.add(new GroupBean("公共管理、社会保障和社会组织"));
        mGroupBeans.add(new GroupBean("通用或其他"));

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
                        Log.d("sdadsadas", body);
                        Gson gson = new Gson();
                        JsonReader reader = new JsonReader(new StringReader(body));
                        mMyScModel = gson.fromJson(reader, MyScModel.class);
                        if (mMyScModel.getCode() == 1) {

                            mHandler.post(new Runnable() {
                                @Override
                                public void run() {
                                    mDataBeanList = mMyScModel.getData();
                                    for (int i = 0; i < mDataBeanList.size(); i++) {
                                        majorLists = new ArrayList<>();
                                        ArrayList aa = new ArrayList();
                                        aa.add(mDataBeanList.get(i).getVoc().getVocaname());
                                        majorLists.add(aa);
                                    }

//                                    aa.add(new ItemBean("测试数据2"));
//                                    aa.add(new ItemBean("测试数据3"));
//                                    aa.add(new ItemBean("测试数据4"));
//                                    majorLists.add(aa);
//                                    mNameData = mMyScModel.getData();
//
//                                    Log.d("datasize", mNameData.size() + "");
//                                    mMajorData = mMyScModel.getData();
//                                    for (int i = 0; i < mMajorData.size(); i++) {
//                                        aaa = new ArrayList<>();
//                                        //    aaa = Collections.singletonList(mMajorData.get(i).getVoc().getVocaname().toString());
//                                        //  aaa.add(mMajorData.get(i).getVoc().getVocaname().toString());
//                                        arrList = new ArrayList(aaa);
//                                        arrList.add(mMajorData.get(i).getVoc().getVocaname().toString());
//
//                                    }
//
//                                    List<Classes> classesList = new ArrayList<>();
//                                    for (int i = 1; i < mGroupBeans.size(); i++) {
//                                        Classes classes = new Classes();
//
//                                        classes.name = mGroupBeans.get(i).getTitle();
//
//
//                                        classes.student = arrList;
//
//                                        classesList.add(classes);
//                                    }
                                    //  mExpandableListView = (ExpandableListView) view.findViewById(R.id.frag_benke_expand);
                                    ScExAdapter scExAdapter = new ScExAdapter(mDataBeanList, majorLists, getActivity());
                                    //    ZhuanKeAdapter zhuanKeAdapter = new ZhuanKeAdapter(classesList, getActivity());
                                    mExpandableListView.setAdapter(scExAdapter);
//                                    for (int i = 0; i < mNameData.size(); i++) {
//                                        namelist = new ArrayList<>();
//                                        namelist.add(mNameData.get(i).getVoc().getVocatype());
//                                        majorList = new ArrayList<>();
//                                        majorList.add(mNameData.get(i).getVoc().getVocaname());
//                                        ArrayList aa = new ArrayList();
//                                        aa.add(new ItemBean(mNameData.get(i).getVoc().getVocaname()));
//                                        mChildrenList.add(aa);
//                                    }


//                                    mScExAdapter = new ScExAdapter(namelist, majorLists, getActivity());
//                                    mExpandableListView.setAdapter(mScExAdapter);

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


}
