package com.fchj.czglgz.chengzhangguanli_high.dropdownmenu;


import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Handler;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;


import com.fchj.czglgz.chengzhangguanli_high.R;
import com.fchj.czglgz.chengzhangguanli_high.adapter.PopupShengAdapter;
import com.fchj.czglgz.chengzhangguanli_high.base.BaseActivity;
import com.fchj.czglgz.chengzhangguanli_high.util.Url;
import com.fchj.czglgz.chengzhangguanli_high.volunteer.AllCollegeModel;
import com.fchj.czglgz.chengzhangguanli_high.volunteer.CollegeListDetailActivity;
import com.fchj.czglgz.chengzhangguanli_high.volunteer.MajorListActivity;
import com.fchj.czglgz.chengzhangguanli_high.volunteer.PopupCollegeAdapter;
import com.fchj.czglgz.chengzhangguanli_high.volunteer.SearchCollegeActivity;
import com.fchj.czglgz.chengzhangguanli_high.volunteer.SearchCollegeModel;
import com.fchj.czglgz.chengzhangguanli_high.volunteer.SearchMajorActivity;
import com.fchj.czglgz.chengzhangguanli_high.volunteer.SearchMajorModel;
import com.fchj.czglgz.chengzhangguanli_high.volunteeradapter.GetAllCollegeAdapter;
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
import java.util.EventListener;
import java.util.List;

public class DropActivity extends AppCompatActivity {

    DropDownMenu mDropDownMenu;
    private String headers[] = {"地区", "院校类型", "学校类别"};
    private List<View> popupViews = new ArrayList<>();
    private ListIconDropDownAdapter cityAdapter, ageAdapter, sexAdapter;
    private GridDropDownAdapter constellationAdapter;
    private List<String> shengList;
    private String citys[] = {"不限", "武汉", "北京", "上海", "成都", "广州", "深圳", "重庆", "天津", "西安", "南京", "杭州"};
    private String subject[] = {"不限", "体育", "军事", "农林", "医药", "工科", "师范", "政法", "林业", "民族", "理工", "综合", "艺术"};
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
    private SearchCollegeModel mSearchCollegeModel;
    private List<SearchCollegeModel.DataBean> mSearchCollegeData;
    private List<SearchCollegeModel.DataBean> mSearchPageData;
    private PopupCollegeAdapter mPopupCollegeAdapter;
    private GetAllCollegeAdapter mGetAllCollegeAdapter;
    private String address = "";
    private String type = "";
    private String subjecttype = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drop);
        createLoadingDialog(DropActivity.this, "加载中....");
        mHandler = new Handler();
        getData();
        initView();
        mListView = (ListView) findViewById(R.id.all_college_lv);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent toDetail = new Intent(DropActivity.this, CollegeListDetailActivity.class);
                toDetail.putExtra("guid", mSearchCollegeData.get(position).getUniversityname());
                Log.d("guid", mSearchCollegeData.get(position).getUniversityname());
                startActivity(toDetail);
            }
        });
//        getAllCollege("");
        getAllCollegeByKind();
        PullToRefreshLayout ptr = (PullToRefreshLayout) findViewById(R.id.all_college_pullrefresh);
        mListView = (ListView) ptr.getPullableView();
        ptr.setOnPullListener(new PullToRefreshLayout.OnPullListener() {
            @Override
            public void onRefresh(PullToRefreshLayout pullToRefreshLayout) {
                getAllCollegeByKind();
                pullToRefreshLayout.refreshFinish(0);
            }

            @Override
            public void onLoadMore(PullToRefreshLayout pullToRefreshLayout) {
                itemId += 1;
//                getPageCollege("");
                getAllCollegeByKindPage();
                pullToRefreshLayout.loadmoreFinish(0);
            }
        });
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
                getAllCollegeByKind();
                //   Toast.makeText(DropActivity.this, "" + shengList.get(position), Toast.LENGTH_SHORT).show();
                mDropDownMenu.closeMenu();
            }
        });

        ageView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ageAdapter.setCheckItem(position);
//                subjecttype = subject[position];
                if (position == 0) {
                    subjecttype = "";
                } else {
                    subjecttype = subject[position];

                }
                getAllCollegeByKind();
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
                getAllCollegeByKind();
                //  Toast.makeText(DropActivity.this, "" + kinds[position], Toast.LENGTH_SHORT).show();
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
        builder.add("universityname", collegeStr);
        builder.add("pageSize", "10");
        Request request = new Request.Builder().url(Url.getCollegeUrl).post(builder.build()).build();
        OkHttpClient okHttpClient = new OkHttpClient();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {
                Looper.prepare();
                Toast.makeText(DropActivity.this, "服务器异常", Toast.LENGTH_LONG).show();
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
                                    mSearchCollegeData = mSearchCollegeModel.getData();
                                    mGetAllCollegeAdapter = new GetAllCollegeAdapter(mSearchCollegeData);
                                    mListView.setAdapter(mGetAllCollegeAdapter);
                                }
                            });
                        } else if (mSearchCollegeModel.getCode() == 2) {
                            Looper.prepare();
                            Toast.makeText(DropActivity.this, mSearchCollegeModel.getMsg() + "", Toast.LENGTH_SHORT).show();
                            Looper.loop();
                        } else {
                            Looper.prepare();
                            Toast.makeText(DropActivity.this, mSearchCollegeModel.getMsg() + "", Toast.LENGTH_SHORT).show();
                            Looper.loop();
                        }
                    } else {
                        Looper.prepare();
                        Toast.makeText(DropActivity.this, "服务器异常", Toast.LENGTH_SHORT).show(); //请求失败
                        Looper.loop();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        });
    }

    private void getPageCollege(String collegeStr) {

        FormEncodingBuilder builder = new FormEncodingBuilder();
        builder.add("pageNum", String.valueOf(itemId));
        builder.add("universityname", collegeStr);
        builder.add("pageSize", "10");
        Request request = new Request.Builder().url(Url.getCollegeUrl).post(builder.build()).build();
        OkHttpClient okHttpClient = new OkHttpClient();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {
                Looper.prepare();
                Toast.makeText(DropActivity.this, "服务器异常", Toast.LENGTH_LONG).show();
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
                            Toast.makeText(DropActivity.this, mSearchCollegeModel.getMsg() + "", Toast.LENGTH_SHORT).show();
                            Looper.loop();
                        } else {
                            Looper.prepare();
                            Toast.makeText(DropActivity.this, mSearchCollegeModel.getMsg() + "", Toast.LENGTH_SHORT).show();
                            Looper.loop();
                        }

                    } else {
                        Looper.prepare();
                        Toast.makeText(DropActivity.this, "服务器异常", Toast.LENGTH_SHORT).show(); //请求失败
                        Looper.loop();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    Runnable searchRunn = new Runnable() {
        @Override
        public void run() {
            searchStr = searchEd.getText().toString();

        }
    };

    private void getAllCollegeByKind() {

        FormEncodingBuilder builder = new FormEncodingBuilder();
        builder.add("universityaddress", address);
        builder.add("universitytype", type);
        builder.add("universitysubjecttype", subjecttype);
        builder.add("pageNum", "1");
        builder.add("pageSize", "10");
        Request request = new Request.Builder().url(Url.getCollegeByKindsUrl).post(builder.build()).build();
        OkHttpClient okHttpClient = new OkHttpClient();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {
                Looper.prepare();
                Toast.makeText(DropActivity.this, "服务器异常", Toast.LENGTH_LONG).show();
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
                            Toast.makeText(DropActivity.this, mSearchCollegeModel.getMsg() + "", Toast.LENGTH_SHORT).show();
                            Looper.loop();
                        } else {
                            closeDialog(loadingDialog);
                            Looper.prepare();
                            Toast.makeText(DropActivity.this, mSearchCollegeModel.getMsg() + "", Toast.LENGTH_SHORT).show();
                            Looper.loop();
                        }
                    } else {
                        Looper.prepare();
                        Toast.makeText(DropActivity.this, "服务器异常", Toast.LENGTH_SHORT).show(); //请求失败
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
        builder.add("universityaddress", address);
        builder.add("universitytype", type);
        builder.add("universitysubjecttype", subjecttype);
        Log.d("coll", address);
        Log.d("coll", type);
        Log.d("coll", subjecttype);
        builder.add("pageNum", String.valueOf(itemId));
        builder.add("pageSize", "10");
        Request request = new Request.Builder().url(Url.getCollegeByKindsUrl).post(builder.build()).build();
        OkHttpClient okHttpClient = new OkHttpClient();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {
                Looper.prepare();
                Toast.makeText(DropActivity.this, "服务器异常", Toast.LENGTH_LONG).show();
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
                            Toast.makeText(DropActivity.this, mSearchCollegeModel.getMsg() + "", Toast.LENGTH_SHORT).show();
                            Looper.loop();
                        } else {
                            Looper.prepare();
                            Toast.makeText(DropActivity.this, mSearchCollegeModel.getMsg() + "", Toast.LENGTH_SHORT).show();
                            Looper.loop();
                        }
                    } else {
                        Looper.prepare();
                        Toast.makeText(DropActivity.this, "服务器异常", Toast.LENGTH_SHORT).show(); //请求失败
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
                    createLoadingDialog(DropActivity.this, "正在加载...");
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
                        searchStr = mSearchCollegeData.get(position).getUniversityname() + "";
                        getAllCollege(searchStr);

                    }
                });
            }
        });
        cancelTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!TextUtils.isEmpty(searchEd.getText().toString())) {
                    createLoadingDialog(DropActivity.this, "正在加载...");
                    getSearchData(searchEd.getText().toString());
                } else {
                    Toast.makeText(DropActivity.this, "请输入搜索内容", Toast.LENGTH_SHORT).show();
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
    private void getSearchData(String string) {
        FormEncodingBuilder builder = new FormEncodingBuilder();
        builder.add("universityname", string);
        builder.add("pageNum", "-1");
        builder.add("pageSize", "-1");
        Log.d("string", string);
        Request request = new Request.Builder().url(Url.getCollegeUrl).post(builder.build()).build();
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
                        mSearchCollegeModel = gson.fromJson(reader, SearchCollegeModel.class);
                        if (mSearchCollegeModel.getCode() == 1) {
                            closeDialog(loadingDialog);
                            mHandler.post(new Runnable() {
                                @Override
                                public void run() {
                                    mSearchCollegeData = mSearchCollegeModel.getData();
                                    mPopupCollegeAdapter = new PopupCollegeAdapter(mSearchCollegeData);
                                    mSearchList.setAdapter(mPopupCollegeAdapter);
                                }
                            });
                        } else if (mSearchCollegeModel.getCode() == 2) {
                            closeDialog(loadingDialog);
                            Looper.prepare();
                            Toast.makeText(DropActivity.this, mSearchCollegeModel.getMsg() + "", Toast.LENGTH_SHORT).show();
                            Looper.loop();
                        } else {
                            closeDialog(loadingDialog);
                            Looper.prepare();
                            Toast.makeText(DropActivity.this, mSearchCollegeModel.getMsg() + "", Toast.LENGTH_SHORT).show();
                            Looper.loop();
                        }
                    } else {
                        Looper.prepare();
                        Toast.makeText(DropActivity.this, "服务器异常", Toast.LENGTH_SHORT).show(); //请求失败
                        Looper.loop();
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        });
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
        shengList.add("北京");
        shengList.add("天津");
        shengList.add("河北");
        shengList.add("山西");
        shengList.add("内蒙古");
        shengList.add("辽宁");
        shengList.add("吉林");
        shengList.add("黑龙江");
        shengList.add("上海");
        shengList.add("江苏");
        shengList.add("浙江");
        shengList.add("安徽");
        shengList.add("福建");
        shengList.add("江西");
        shengList.add("山东");
        shengList.add("河南");
        shengList.add("湖北");
        shengList.add("湖南");
        shengList.add("广东");
        shengList.add("广西");
        shengList.add("海南");
        shengList.add("重庆");
        shengList.add("四川");
        shengList.add("贵州");
        shengList.add("云南");
        shengList.add("西藏");
        shengList.add("陕西");
        shengList.add("甘肃");
        shengList.add("青海");
        shengList.add("宁夏");
        shengList.add("新疆");
        shengList.add("台湾");
        shengList.add("香港");
        shengList.add("澳门");

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
