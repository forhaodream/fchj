package com.fchj.czglgz.chengzhangguanli_high.overseasstudy;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.fchj.czglgz.chengzhangguanli_high.R;
import com.fchj.czglgz.chengzhangguanli_high.dropdownmenu.DropActivity;
import com.fchj.czglgz.chengzhangguanli_high.dropdownmenu.DropDownMenu;
import com.fchj.czglgz.chengzhangguanli_high.dropdownmenu.GridDropDownAdapter;
import com.fchj.czglgz.chengzhangguanli_high.dropdownmenu.ListIconDropDownAdapter;
import com.fchj.czglgz.chengzhangguanli_high.util.FindLastItem;
import com.fchj.czglgz.chengzhangguanli_high.util.Url;
import com.fchj.czglgz.chengzhangguanli_high.volunteer.AllCollegeModel;
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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class OsStudyActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener {

    DropDownMenu mDropDownMenu;
    private String headers[] = {"国家地区", "院校类型", "学校类别"};
    private List<View> popupViews = new ArrayList<>();
    private ListIconDropDownAdapter cityAdapter, ageAdapter, sexAdapter;
    private GridDropDownAdapter constellationAdapter;
    private List<String> shengList;
    private String citys[] = {"不限", "武汉", "北京", "上海", "成都", "广州", "深圳", "重庆", "天津", "西安", "南京", "杭州"};
    private String subject[] = {"不限", "综合类大学", "学院", "混校", "国际预科学院", "职业技术学院", "语言学校", "女校", "男校", "高中", "初中"};
    private String kinds[] = {"不限", "公立", "私立"};
    private int constellationPosition = 0;
    private ImageView returnImg, searchImg;
    private ListView mListView;
    private int itemId = 1;
    private Handler mHandler;
    public WindowManager.LayoutParams lp;
    private String years = "";
    private EditText searchEd;
    private String searchStr = "";
    private List<String> mData;
    private ListView mSearchList;
    private OsStudyModel mOsStudyModel;
    private List<OsStudyModel.DataBean> mOsStudyData;
    private List<OsStudyModel.DataBean> mOsStudyPageData;
    private PopupOsCollegeAdapter mPopupOsCollegeAdapter;
    private String address = "";
    private String type = "";
    private String schoolType = "";
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private RecyclerView mRecyclerView;
    private OsStudyAdapter mOsStudyAdapter;
    private SearchByNameModel mSearchByNameModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_os_study);
        // createLoadingDialog(OsStudyActivity.this, "加载中....");
        mRecyclerView = (RecyclerView) findViewById(R.id.activity_os_study_rlc);
        FindLastItem findLastItem = new FindLastItem(mRecyclerView, OsStudyActivity.this);
        findLastItem.refresh();
        mHandler = new Handler();
        getData();
        initView();
        getLastItem();
        mSwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.activity_os_study_swiperefresh);
        mSwipeRefreshLayout.setOnRefreshListener(this);
        mSwipeRefreshLayout.setColorSchemeResources(R.color.dark_blue);

        getAllOSCollegeByKind();

    }

    private void getLastItem() {
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                int lastPosition = -1;
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
                    if (layoutManager instanceof GridLayoutManager) {
                        lastPosition = ((GridLayoutManager) layoutManager).findLastVisibleItemPosition();
                    } else if (layoutManager instanceof LinearLayoutManager) {
                        lastPosition = ((LinearLayoutManager) layoutManager).findLastVisibleItemPosition();
                    } else if (layoutManager instanceof StaggeredGridLayoutManager) {
                        int[] lastPositions = new int[((StaggeredGridLayoutManager) layoutManager).getSpanCount()];
                        ((StaggeredGridLayoutManager) layoutManager).findLastVisibleItemPositions(lastPositions);
                        lastPosition = findMax(lastPositions);
                    }
                    if (lastPosition == recyclerView.getLayoutManager().getItemCount() - 1) {
                        itemId += 1;
                        getAllCollegeByKindPage();

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

    private void initView() {
        // 基础组件
        returnImg = (ImageView) findViewById(R.id.title_fanhui);
        returnImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        // 搜索
        searchImg = (ImageView) findViewById(R.id.title_search);
        searchImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchPopup();
            }
        });
        mDropDownMenu = (DropDownMenu) findViewById(R.id.dropDownMenu);
        //init city menu
        final ListView cityView = new ListView(this);
        cityAdapter = new ListIconDropDownAdapter(this, shengList);
        cityView.setDividerHeight(0);
        cityView.setAdapter(cityAdapter);
        //init age menu
        final ListView ageView = new ListView(this);
        ageView.setDividerHeight(0);
        ageAdapter = new ListIconDropDownAdapter(this, Arrays.asList(subject));
        ageView.setAdapter(ageAdapter);
        //init sex menu
        final ListView sexView = new ListView(this);
        sexView.setDividerHeight(0);
        sexAdapter = new ListIconDropDownAdapter(this, Arrays.asList(kinds));
        sexView.setAdapter(sexAdapter);
        //init popupViews
        popupViews.add(cityView);
        popupViews.add(ageView);
        popupViews.add(sexView);

        //add item click event
        cityView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                cityAdapter.setCheckItem(position);
                mDropDownMenu.setTabText(position == 0 ? headers[0] : shengList.get(position));
                if (position == 0) {
                    address = "";
                } else {
                    address = shengList.get(position).toString();

                }
                getAllOSCollegeByKind();
                //   Toast.makeText(OsStudyActivity.this, "" + shengList.get(position), Toast.LENGTH_SHORT).show();
                mDropDownMenu.closeMenu();
            }
        });

        ageView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ageAdapter.setCheckItem(position);
//                schoolType = subject[position];
                if (position == 0) {
                    schoolType = "";
                } else {
                    schoolType = subject[position];

                }
                getAllOSCollegeByKind();
                mDropDownMenu.setTabText(position == 0 ? headers[1] : subject[position]);
                mDropDownMenu.closeMenu();
            }
        });

        sexView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                sexAdapter.setCheckItem(position);
                mDropDownMenu.setTabText(position == 0 ? headers[2] : kinds[position]);
                if (position == 0) {
                    type = "";
                } else {
                    type = kinds[position];
                }
                getAllOSCollegeByKind();
                //  Toast.makeText(OsStudyActivity.this, "" + kinds[position], Toast.LENGTH_SHORT).show();
                mDropDownMenu.closeMenu();
            }
        });

        mDropDownMenu.setDropDownMenu(Arrays.asList(headers), popupViews);
    }

    private AllCollegeModel mAllCollegeModel;
    private List<AllCollegeModel.DataBean> mDataBeans;
    private List<AllCollegeModel.DataBean> pageBeans;

    private void getAllCollege(String collegeStr) {

        FormEncodingBuilder builder = new FormEncodingBuilder();
        builder.add("pageNum", "1");
        builder.add("academyname", collegeStr);
        builder.add("pageSize", "10");
        Request request = new Request.Builder().url(Url.getOsUniByNameUrl).post(builder.build()).build();
        OkHttpClient okHttpClient = new OkHttpClient();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {
                Looper.prepare();
                Toast.makeText(OsStudyActivity.this, "服务器异常", Toast.LENGTH_LONG).show();
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
                        mOsStudyModel = gson.fromJson(reader, OsStudyModel.class);
                        if (mOsStudyModel.getCode() == 1) {

                            mHandler.post(new Runnable() {
                                @Override
                                public void run() {
                                    mSwipeRefreshLayout.setRefreshing(false);
                                    mOsStudyData = mOsStudyModel.getData();
                                    mOsStudyAdapter = new OsStudyAdapter(mOsStudyData);
                                    mRecyclerView.setLayoutManager(new LinearLayoutManager(OsStudyActivity.this));
                                    mRecyclerView.setAdapter(mOsStudyAdapter);
                                    mOsStudyAdapter.setOnItemClickListener(new OsStudyAdapter.OnItemClickListener() {
                                        @Override
                                        public void onItemClick(OsStudyAdapter.ViewHolder holder, int position) {
                                            Intent toAsk = new Intent(OsStudyActivity.this, FreeAskActivity.class);
                                            toAsk.putExtra("countryStr", mOsStudyData.get(position).getOwnedCountry());
                                            startActivity(toAsk);
//                                            Toast.makeText(OsStudyActivity.this, "跳转到咨询", Toast.LENGTH_SHORT).show();

                                        }

                                        @Override
                                        public void onToDetail(View view, int position) {
                                            Intent toDetail = new Intent(OsStudyActivity.this, OsStudydDetailActivity.class);
                                            toDetail.putExtra("os_id", mOsStudyData.get(position).getIntalshId() + "");
                                            startActivity(toDetail);
                                            // Toast.makeText(OsStudyActivity.this, "跳转到" + mOsStudyData.get(position).getSchoolName() + "详细", Toast.LENGTH_SHORT).show();

                                        }
                                    });
                                }
                            });
                        } else if (mOsStudyModel.getCode() == 2) {
                            Looper.prepare();
                            Toast.makeText(OsStudyActivity.this, mOsStudyModel.getMsg() + "", Toast.LENGTH_SHORT).show();
                            Looper.loop();
                        } else {
                            Looper.prepare();
                            Toast.makeText(OsStudyActivity.this, mOsStudyModel.getMsg() + "", Toast.LENGTH_SHORT).show();
                            Looper.loop();
                        }
                    } else {
                        Looper.prepare();
                        Toast.makeText(OsStudyActivity.this, "服务器异常", Toast.LENGTH_SHORT).show(); //请求失败
                        Looper.loop();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        });
    }


    private void getAllOSCollegeByKind() {
        FormEncodingBuilder builder = new FormEncodingBuilder();
        builder.add("ownedCountry", address);
        builder.add("schoolNature", type);
        builder.add("schoolType", schoolType);
        builder.add("pageNum", "1");
        builder.add("pageSize", "10");
        Request request = new Request.Builder().url(Url.getOsCollegeUrl).post(builder.build()).build();
        OkHttpClient okHttpClient = new OkHttpClient();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {
                Looper.prepare();
                Toast.makeText(OsStudyActivity.this, "服务器异常", Toast.LENGTH_LONG).show();
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
                        mOsStudyModel = gson.fromJson(reader, OsStudyModel.class);
                        if (mOsStudyModel.getCode() == 1) {
                            closeDialog(loadingDialog);
                            mHandler.post(new Runnable() {
                                @Override
                                public void run() {
                                    mSwipeRefreshLayout.setRefreshing(false);
                                    mOsStudyData = mOsStudyModel.getData();
                                    mOsStudyAdapter = new OsStudyAdapter(mOsStudyData);
                                    mRecyclerView.setLayoutManager(new LinearLayoutManager(OsStudyActivity.this));
                                    mRecyclerView.setAdapter(mOsStudyAdapter);
                                    mOsStudyAdapter.setOnItemClickListener(new OsStudyAdapter.OnItemClickListener() {
                                        @Override
                                        public void onItemClick(OsStudyAdapter.ViewHolder holder, int position) {
                                            Intent toAsk = new Intent(OsStudyActivity.this, FreeAskActivity.class);
                                            toAsk.putExtra("countryStr", mOsStudyData.get(position).getOwnedCountry());
                                            startActivity(toAsk);
//                                            Toast.makeText(OsStudyActivity.this, "跳转到咨询", Toast.LENGTH_SHORT).show();

                                        }

                                        @Override
                                        public void onToDetail(View view, int position) {
                                            Intent toDetail = new Intent(OsStudyActivity.this, OsStudydDetailActivity.class);
                                            toDetail.putExtra("os_id", mOsStudyData.get(position).getIntalshId() + "");
                                            startActivity(toDetail);
                                            // Toast.makeText(OsStudyActivity.this, "跳转到" + mOsStudyData.get(position).getSchoolName() + "详细", Toast.LENGTH_SHORT).show();

                                        }
                                    });
                                }
                            });
                        } else if (mOsStudyModel.getCode() == 2) {
                            closeDialog(loadingDialog);
                            mHandler.post(new Runnable() {
                                @Override
                                public void run() {
                                    mOsStudyData.clear();
                                    mOsStudyAdapter.notifyDataSetChanged();
                                }
                            });
                            Looper.prepare();
                            Toast.makeText(OsStudyActivity.this, mOsStudyModel.getMsg() + "", Toast.LENGTH_SHORT).show();
                            Looper.loop();
                        } else {
                            closeDialog(loadingDialog);
                            Looper.prepare();
                            Toast.makeText(OsStudyActivity.this, mOsStudyModel.getMsg() + "", Toast.LENGTH_SHORT).show();
                            Looper.loop();
                        }
                    } else {
                        Looper.prepare();
                        Toast.makeText(OsStudyActivity.this, "服务器异常", Toast.LENGTH_SHORT).show(); //请求失败
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
        builder.add("ownedCountry", address);
        builder.add("schoolNature", type);
        builder.add("schoolType", schoolType);
        builder.add("pageNum", String.valueOf(itemId));
        builder.add("pageSize", "10");
        Request request = new Request.Builder().url(Url.getOsCollegeUrl).post(builder.build()).build();
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
                        mOsStudyModel = gson.fromJson(reader, OsStudyModel.class);
                        mOsStudyPageData = mOsStudyModel.getData();
                        if (mOsStudyModel.getCode() == 1) {
                            mHandler.post(new Runnable() {
                                @Override
                                public void run() {
                                    if (mOsStudyPageData.size() > 0) {
                                        mOsStudyData.addAll(mOsStudyPageData);
                                        mOsStudyAdapter.notifyDataSetChanged();
                                    } else {
                                        mData.clear();
                                        mOsStudyAdapter.notifyDataSetChanged();
                                        Log.d("没有", "数据");
                                    }
                                }
                            });
                        } else if (mOsStudyModel.getCode() == 2) {
                            Looper.prepare();
                            Toast.makeText(OsStudyActivity.this, mOsStudyModel.getMsg() + "", Toast.LENGTH_SHORT).show();
                            Looper.loop();
                        } else {
                            Looper.prepare();
                            Toast.makeText(OsStudyActivity.this, mOsStudyModel.getMsg() + "", Toast.LENGTH_SHORT).show();
                            Looper.loop();
                        }
                    } else {
                        Looper.prepare();
                        Toast.makeText(OsStudyActivity.this, "服务器异常", Toast.LENGTH_SHORT).show(); //请求失败
                        Looper.loop();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }


    private void searchPopup() {
        final View popView = View.inflate(this, R.layout.popwindow_editview, null);
        TextView cancelTv = (TextView) popView.findViewById(R.id.popup_ed_cancel);
        searchEd = (EditText) popView.findViewById(R.id.popup_ed_edit);
        searchEd.setImeOptions(EditorInfo.IME_ACTION_SEARCH);
        mSearchList = (ListView) popView.findViewById(R.id.popup_edit_list);
        //获取屏幕宽高
        int weight = getResources().getDisplayMetrics().widthPixels;
        int height = getResources().getDisplayMetrics().heightPixels;
        final PopupWindow popupWindow = new PopupWindow(popView, weight, height);
        popupWindow.setFocusable(true);
        //点击外部popueWindow消失
        popupWindow.setOutsideTouchable(true);
        searchEd.setOnEditorActionListener(new TextView.OnEditorActionListener() {

            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEND
                        || actionId == EditorInfo.IME_ACTION_DONE
                        || (event != null && KeyEvent.KEYCODE_ENTER ==
                        event.getKeyCode() && KeyEvent.ACTION_DOWN == event.getAction())) {
                    createLoadingDialog(OsStudyActivity.this, "正在加载...");
                    getSearchData(searchEd.getText().toString());
                }
                return false;
            }
        });
        mSearchList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        popupWindow.dismiss();
                        searchStr = mOsSearchData.get(position).getSchoolName() + "";
                        getAllCollege(searchStr);

                    }
                });
            }
        });
        cancelTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!TextUtils.isEmpty(searchEd.getText().toString())) {
                    createLoadingDialog(OsStudyActivity.this, "正在加载...");
                    getSearchData(searchEd.getText().toString());
                } else {
                    Toast.makeText(OsStudyActivity.this, "请输入搜索内容", Toast.LENGTH_SHORT).show();
                }


//                popupWindow.dismiss();
            }
        });
        //popupWindow出现屏幕变为半透明
//        final WindowManager.LayoutParams
        lp = getWindow().getAttributes();
        lp.alpha = 0.5f;
        getWindow().setAttributes(lp);
        popupWindow.showAtLocation(popView, Gravity.BOTTOM, 0, 0);

        popupWindow.setOnDismissListener(new poponDismissListener());

    }

    private List<SearchByNameModel.DataBean> mOsSearchData;
    private SearchByNameAdapter mSearchByNameAdapter;

    private void getSearchData(String string) {
        FormEncodingBuilder builder = new FormEncodingBuilder();
        builder.add("academyname", string);
        builder.add("pageNum", "1");
        builder.add("pageSize", "50");
        Log.d("string", string);
        Request request = new Request.Builder().url(Url.getOsUniByNameUrl).post(builder.build()).build();
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
                        Log.d("message", response.message());
                        String body = response.body().string();
                        Log.d("sdadsadas", body);
                        Gson gson = new Gson();
                        JsonReader reader = new JsonReader(new StringReader(body));
                        mSearchByNameModel = gson.fromJson(reader, SearchByNameModel.class);
                        if (mSearchByNameModel.getCode() == 1) {
                            closeDialog(loadingDialog);
                            mHandler.post(new Runnable() {
                                @Override
                                public void run() {
                                    mOsSearchData = mSearchByNameModel.getData();
                                    mSearchByNameAdapter = new SearchByNameAdapter(mOsSearchData);
                                    mSearchList.setAdapter(mSearchByNameAdapter);
                                }
                            });
                        } else if (mSearchByNameModel.getCode() == 2) {
                            closeDialog(loadingDialog);
                            Looper.prepare();
                            Toast.makeText(OsStudyActivity.this, mSearchByNameModel.getMsg() + "", Toast.LENGTH_SHORT).show();
                            Looper.loop();
                        } else {
                            closeDialog(loadingDialog);
                            Looper.prepare();
                            Toast.makeText(OsStudyActivity.this, mSearchByNameModel.getMsg() + "", Toast.LENGTH_SHORT).show();
                            Looper.loop();
                        }
                    } else {
                        Looper.prepare();
                        Toast.makeText(OsStudyActivity.this, "服务器异常", Toast.LENGTH_SHORT).show(); //请求失败
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
        getAllOSCollegeByKind();

    }

    class poponDismissListener implements PopupWindow.OnDismissListener {

        @Override
        public void onDismiss() {
            backgroundAlpha(1f);
        }

    }

    public void backgroundAlpha(float bgAlpha) {
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.alpha = bgAlpha; //0.0-1.0
        getWindow().setAttributes(lp);
    }

    @Override
    public void onBackPressed() {
        //退出activity前关闭菜单
        if (mDropDownMenu.isShowing()) {
            mDropDownMenu.closeMenu();
        } else {
            super.onBackPressed();
        }
    }

    private void getData() {

        shengList = new ArrayList<String>();
        shengList.add("不限");
        shengList.add("荷兰");
        shengList.add("加拿大");
        shengList.add("德国");
        shengList.add("瑞典");
        shengList.add("法国");
        shengList.add("意大利");
        shengList.add("日本");
        shengList.add("瑞士");
        shengList.add("西班牙");
        shengList.add("新加坡");
        shengList.add("匈牙利");
        shengList.add("美国");
        shengList.add("爱尔兰");
        shengList.add("爱莎尼亚");
        shengList.add("丹麦");
        shengList.add("俄罗斯");
        shengList.add("马来西亚");
        shengList.add("英国");
        shengList.add("比利时");
        shengList.add("芬兰");
        shengList.add("澳大利亚");
        shengList.add("中国香港");

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
    public Resources getResources() {
        Resources res = super.getResources();
        Configuration config = new Configuration();
        config.setToDefaults();
        res.updateConfiguration(config, res.getDisplayMetrics());
        return res;
    }

    @Override
    protected void onResume() {
        super.onResume();
        itemId = 1;
    }

    @Override
    protected void onStop() {
        super.onStop();
        itemId = 1;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        itemId = 1;
    }
}

