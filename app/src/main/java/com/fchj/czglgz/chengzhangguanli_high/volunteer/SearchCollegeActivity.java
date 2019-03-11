package com.fchj.czglgz.chengzhangguanli_high.volunteer;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.Nullable;
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
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.fchj.czglgz.chengzhangguanli_high.R;
import com.fchj.czglgz.chengzhangguanli_high.activity.LoginActivity;
import com.fchj.czglgz.chengzhangguanli_high.adapter.PopupShengAdapter;
import com.fchj.czglgz.chengzhangguanli_high.dropdownmenu.DropActivity;
import com.fchj.czglgz.chengzhangguanli_high.util.IntentUtil;
import com.fchj.czglgz.chengzhangguanli_high.util.Url;
import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import com.squareup.okhttp.Call;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import com.zhy.autolayout.AutoRelativeLayout;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

public class SearchCollegeActivity extends Activity implements View.OnClickListener, SearchView.OnQueryTextListener {
    private ImageView titleFanhui;
    private TextView titleText;
    private RelativeLayout title;
    private TextView oneText;
    private AutoRelativeLayout searchCollegeYear;
    private TextView twoText;
    private AutoRelativeLayout searchCollegeAddress;
    private TextView fourText;
    private TextView collegeZyTv;
    private TextView fiveText;
    private AutoRelativeLayout searchCollegeSubject;
    private AutoRelativeLayout searchFinish, searchNameRl;
    private TextView yearsTv, wenkeTv, shengTv;
    private Handler mHandler;
    private String searchStr = "";
    private SearchMajorModel mSearchMajorModel;
    private List<String> mData;
    private SearchCollegeModel mSearchCollegeModel;
    private List<SearchCollegeModel.DataBean> mSearchCollegeData;
    private PopupCollegeAdapter mPopupCollegeAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_college);
        mHandler = new Handler();
        initView();
        getData();
        getSubjectData();
        getyearData();
    }

    private void initView() {
        titleFanhui = (ImageView) findViewById(R.id.title_fanhui);
        titleFanhui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        titleText = (TextView) findViewById(R.id.title_text);
        title = (RelativeLayout) findViewById(R.id.title);
        oneText = (TextView) findViewById(R.id.one_text);
        searchCollegeYear = (AutoRelativeLayout) findViewById(R.id.search_college_year);
        searchCollegeYear.setOnClickListener(this);
        twoText = (TextView) findViewById(R.id.two_text);
        searchCollegeAddress = (AutoRelativeLayout) findViewById(R.id.search_college_address);
        searchCollegeAddress.setOnClickListener(this);
        fourText = (TextView) findViewById(R.id.four_text);
        collegeZyTv = (TextView) findViewById(R.id.college_zy_ed);
        fiveText = (TextView) findViewById(R.id.five_text);
        searchCollegeSubject = (AutoRelativeLayout) findViewById(R.id.search_college_subject);
        searchCollegeSubject.setOnClickListener(this);
        searchFinish = (AutoRelativeLayout) findViewById(R.id.search_finish);
        searchFinish.setOnClickListener(this);
        searchNameRl = (AutoRelativeLayout) findViewById(R.id.college_search_name);
        searchNameRl.setOnClickListener(this);
        yearsTv = (TextView) findViewById(R.id.college_years_tv);
        wenkeTv = (TextView) findViewById(R.id.college_wenke);
        shengTv = (TextView) findViewById(R.id.college_sheng_tv);
//        yearsTv.setText("2017年");
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.search_finish:
                if (!TextUtils.isEmpty(years) && !TextUtils.isEmpty(sheng) && !TextUtils.isEmpty(subject)) {
                    Intent toDetail = new Intent(SearchCollegeActivity.this, CollegeListActivity.class);
                    toDetail.putExtra("years", years);
                    toDetail.putExtra("sheng", sheng);
                    toDetail.putExtra("subject", subject);
                    toDetail.putExtra("college", searchStr);
                    startActivity(toDetail);
                } else {
                    Toast.makeText(this, "请选择年份/生源地/科类", Toast.LENGTH_SHORT).show();
                }

                break;
            case R.id.search_college_year:
                yearPopup();
                break;
            case R.id.search_college_subject:
                subjectPopup();
                break;
            case R.id.search_college_address:
                shengPopup();
                break;
            case R.id.college_search_name:
                searchPopup();
                break;
        }
    }

    public WindowManager.LayoutParams lp;
    private String years = "";
    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            years = "2016";
            yearsTv.setText("2016年");
        }
    };
    Runnable runnable1 = new Runnable() {
        @Override
        public void run() {
            years = "2017";
            yearsTv.setText("2017年");
        }
    };
    private SearchView searchView;
    private EditText searchEd;
    Runnable searchRunn = new Runnable() {
        @Override
        public void run() {
            searchStr = searchEd.getText().toString();
            if (!TextUtils.isEmpty(searchEd.getText().toString())) {

                collegeZyTv.setText(searchStr);
            } else {
                collegeZyTv.setText("搜索学校");
            }

        }
    };
    private ListView mSearchList;

    private void searchPopup() {
        final View popView = View.inflate(this, R.layout.popwindow_editview, null);
        TextView cancelTv = (TextView) popView.findViewById(R.id.popup_ed_cancel);
        searchEd = (EditText) popView.findViewById(R.id.popup_ed_edit);
        searchEd.setImeOptions(EditorInfo.IME_ACTION_SEARCH);
        mSearchList = (ListView) popView.findViewById(R.id.popup_edit_list);
        //获取屏幕宽高
        int weight = getResources().getDisplayMetrics().widthPixels;
        int height = getResources().getDisplayMetrics().heightPixels * 3 / 4;
        final PopupWindow popupWindow = new PopupWindow(popView, weight, height);
        popupWindow.setFocusable(true);
        //点击外部popueWindow消失
        popupWindow.setOutsideTouchable(true);
        popupWindow.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        searchEd.setOnEditorActionListener(new TextView.OnEditorActionListener() {

            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEND
                        || actionId == EditorInfo.IME_ACTION_DONE
                        || (event != null && KeyEvent.KEYCODE_ENTER ==
                        event.getKeyCode() && KeyEvent.ACTION_DOWN == event.getAction())) {
                    createLoadingDialog(SearchCollegeActivity.this, "正在加载...");
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
                        searchStr = mSearchCollegeData.get(position).getUniversityname();
                        if (!TextUtils.isEmpty(searchEd.getText().toString())) {

                            collegeZyTv.setText(searchStr);
                        } else {
                            collegeZyTv.setText("搜索学校");
                        }
                    }
                });
            }
        });
        cancelTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!TextUtils.isEmpty(searchEd.getText().toString())) {
                    createLoadingDialog(SearchCollegeActivity.this, "正在加载...");
                    getSearchData(searchEd.getText().toString());
                } else {
                    Toast.makeText(SearchCollegeActivity.this, "请输入搜索内容", Toast.LENGTH_SHORT).show();
                }
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
                            Toast.makeText(SearchCollegeActivity.this, mSearchCollegeModel.getMsg() + "", Toast.LENGTH_SHORT).show();
                            Looper.loop();
                        } else {
                            closeDialog(loadingDialog);
                            Looper.prepare();
                            Toast.makeText(SearchCollegeActivity.this, mSearchCollegeModel.getMsg() + "", Toast.LENGTH_SHORT).show();
                            Looper.loop();
                        }
                    } else {
                        Looper.prepare();
                        Toast.makeText(SearchCollegeActivity.this, "服务器异常", Toast.LENGTH_SHORT).show(); //请求失败
                        Looper.loop();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        });
    }

    private List<String> yearList;

    private void getyearData() {
        yearList = new ArrayList<>();
        yearList.add("2017年");
        yearList.add("2016年");
    }

    private void yearPopup() {

        final View popView = View.inflate(this, R.layout.popwindow_years, null);
        ListView yearLv = (ListView) popView.findViewById(R.id.popup_year_lv);
        mPopupShengAdapter = new PopupShengAdapter(yearList);
        yearLv.setAdapter(mPopupShengAdapter);

        //获取屏幕宽高
        int weight = getResources().getDisplayMetrics().widthPixels;
        int height = getResources().getDisplayMetrics().heightPixels * 1 / 4;

        final PopupWindow popupWindow = new PopupWindow(popView, weight, height);
        //   popupWindow.setAnimationStyle(R.style.anim_popup_dir);
        popupWindow.setFocusable(true);
        //点击外部popueWindow消失
        popupWindow.setOutsideTouchable(true);
        yearLv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String yearsStr = yearList.get(position).toString();
                years = yearsStr.substring(0, yearsStr.length() - 1);
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        yearsTv.setText(years);
                    }
                });
                Log.d("popo", years);
                popupWindow.dismiss();
            }
        });
        //popupWindow出现屏幕变为半透明
//        final WindowManager.LayoutParams
        lp = getWindow().getAttributes();
        lp.alpha = 0.9f;
        getWindow().setAttributes(lp);
        popupWindow.showAtLocation(popView, Gravity.BOTTOM, 0, 0);

        popupWindow.setOnDismissListener(new poponDismissListener());

    }

    private String subject = "";
    Runnable wenke = new Runnable() {
        @Override
        public void run() {
            subject = "文科";
            wenkeTv.setText("文科");
        }
    };
    Runnable like = new Runnable() {
        @Override
        public void run() {
            subject = "理科";
            wenkeTv.setText("理科");
        }
    };
    Runnable yishu = new Runnable() {
        @Override
        public void run() {
            subject = "艺术";
            wenkeTv.setText("艺术类");
        }
    };
    private List<String> subjectList;

    private void getSubjectData() {
        subjectList = new ArrayList<String>();
        subjectList.add("文科");
        subjectList.add("理科");
        subjectList.add("艺术类");
        subjectList.add("体育类");
        subjectList.add("综合");
        subjectList.add("文理");
    }

    private void subjectPopup() {
        final View popView = View.inflate(this, R.layout.popwindow_subject, null);
        ListView subjectLv = (ListView) popView.findViewById(R.id.popup_subject_lv);
        mPopupShengAdapter = new PopupShengAdapter(subjectList);
        subjectLv.setAdapter(mPopupShengAdapter);
        //获取屏幕宽高
        int weight = getResources().getDisplayMetrics().widthPixels;
        int height = getResources().getDisplayMetrics().heightPixels * 1 / 2;

        final PopupWindow popupWindow = new PopupWindow(popView, weight, height);
        //   popupWindow.setAnimationStyle(R.style.anim_popup_dir);
        popupWindow.setFocusable(true);
        //点击外部popueWindow消失
        popupWindow.setOutsideTouchable(true);
        subjectLv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                subject = subjectList.get(position).toString();
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        wenkeTv.setText(subject);
                    }
                });
                Log.d("popo", subject);
                popupWindow.dismiss();
            }
        });
        //popupWindow出现屏幕变为半透明
//        final WindowManager.LayoutParams
        lp = getWindow().getAttributes();
        lp.alpha = 0.9f;
        getWindow().setAttributes(lp);
        popupWindow.showAtLocation(popView, Gravity.BOTTOM, 0, 0);

        popupWindow.setOnDismissListener(new poponDismissListener());

    }


    private PopupShengAdapter mPopupShengAdapter;
    private List<String> shengList;

    private void getData() {

        shengList = new ArrayList<String>();
        shengList.add("全国");
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

    private String sheng = "";

    private void shengPopup() {

        final View popView = View.inflate(this, R.layout.popwindow_sheng, null);
        ListView shengLv = (ListView) popView.findViewById(R.id.popup_sheng_lv);
        mPopupShengAdapter = new PopupShengAdapter(shengList);
        shengLv.setAdapter(mPopupShengAdapter);

        //获取屏幕宽高
        int weight = getResources().getDisplayMetrics().widthPixels;
        int height = getResources().getDisplayMetrics().heightPixels * 3 / 4;

        final PopupWindow popupWindow = new PopupWindow(popView, weight, height);
        //   popupWindow.setAnimationStyle(R.style.anim_popup_dir);
        popupWindow.setFocusable(true);
        //点击外部popueWindow消失
        popupWindow.setOutsideTouchable(true);
        shengLv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                final String shengStr = shengList.get(position).toString();
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        sheng = shengStr;
                        shengTv.setText(sheng);
                    }
                });
                Log.d("popo", sheng);
                popupWindow.dismiss();
            }
        });

        //popupWindow出现屏幕变为半透明
//        final WindowManager.LayoutParams
        lp = getWindow().getAttributes();
        lp.alpha = 0.9f;
        getWindow().setAttributes(lp);
        popupWindow.showAtLocation(popView, Gravity.BOTTOM, 0, 0);

        popupWindow.setOnDismissListener(new poponDismissListener());

    }

    @Override
    public boolean onQueryTextSubmit(String s) {
        Toast.makeText(this, "搜索的字符" + s, Toast.LENGTH_LONG).show();
        return false;
    }

    @Override
    public boolean onQueryTextChange(String s) {

        return true;
    }

//    @Override
//    public boolean onQueryTextSubmit(String query) {
//        Toast.makeText(this, "搜索的字符" + searchView, Toast.LENGTH_LONG).show();
//        return false;
//    }
//
//    @Override
//    public boolean onQueryTextChange(String newText) {
//        return false;
//    }


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


}

