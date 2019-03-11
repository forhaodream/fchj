package com.fchj.czglgz.chengzhangguanli_high.vocation;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.app.usage.UsageEvents;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.Nullable;
import android.support.v7.widget.CardView;
import android.text.TextUtils;
import android.util.Log;
import android.view.GestureDetector;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.fchj.czglgz.chengzhangguanli_high.R;
import com.fchj.czglgz.chengzhangguanli_high.activity.AdsActivity;
import com.fchj.czglgz.chengzhangguanli_high.activity.HomeActivity;
import com.fchj.czglgz.chengzhangguanli_high.base.BaseActivity;
import com.fchj.czglgz.chengzhangguanli_high.util.IntentUtil;
import com.fchj.czglgz.chengzhangguanli_high.util.Url;
import com.fchj.czglgz.chengzhangguanli_high.volunteeradapter.CollegePicDetailAdapter;
import com.fchj.czglgz.chengzhangguanli_high.volunteermodel.CollegePicDetailModel;
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

public class VocationActivity extends Activity {
    private GridView mPicGrid;
    private CollegePicDetailAdapter mAdapter;
    private CollegePicDetailModel mCollegePicDetailModel;
    private List<CollegePicDetailModel.DataBean> mPicData;
    private ImageView returnImg;
    private Handler mHandler;
    private String guid = "";
    public WindowManager.LayoutParams lp;
    private PicModel mPicModel;
    private List<PicModel> mPhotoData;
    private String[] vocationName = {"农、林、牧、渔业", "采矿业", "制造业", "电力、热力、燃气及水生产和供应业", "建筑业"
            , "批发和零售业", "交通运输、仓储和邮政业", "住宿和餐饮业", "信息传输、软件和信息技术服务业", "金融业"
            , "房地产业", "租赁和商户服务业", "科学研究和技术服务业", "水利、环境和公共设施管理业", "居民服务、修理和其他服务业"
            , "教育", "卫生和社会工作", "文化、体育和娱乐业", "公共管理、社会保障和社会组织", "通用或其他"};
    private int[] vocationPic = {
            R.mipmap.nongye,
            R.mipmap.caikuang,
            R.mipmap.zhizao,
            R.mipmap.dianli,
            R.mipmap.jianzhuye,
            R.mipmap.pifa,
            R.mipmap.jiaotong,
            R.mipmap.zhusu,
            R.mipmap.xinxi,
            R.mipmap.jinrongye,
            R.mipmap.fangdichan,
            R.mipmap.zhulin,
            R.mipmap.keyan,
            R.mipmap.shuilihuanjing,
            R.mipmap.juminfuwu,
            R.mipmap.jiaoyu,
            R.mipmap.weisheng,
            R.mipmap.wenhuayule,
            R.mipmap.gonggongguanl,
            R.mipmap.tongyong

    };
    private VocationAdapter mVocationAdapter;
    private VocationModel mVocationModel;
    private Dialog loadingDialog;
    private List<VocationModel.DataBean> mData;
    private VocationListAdapter mVocationListAdapter;
    private ListView popupLv;
    // 获取username
    private SharedPreferences usernameSp;
    private int userId = -1;
    private int followId = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vocation);
        mHandler = new Handler();
        mPicGrid = (GridView) findViewById(R.id.college_pic_detail);
        mPicGrid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                createLoadingDialog(VocationActivity.this, "正在加载...");
                listPopup(vocationName[position]);//
                Log.d("1111", vocationName[position]);
            }
        });
        usernameSp = getSharedPreferences("user_npt", MODE_PRIVATE);
        userId = usernameSp.getInt("userId", 0);

        returnImg = (ImageView) findViewById(R.id.title_fanhui);
        returnImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        mPhotoData = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            mPicModel = new PicModel();
            mPicModel.setVocationName(vocationName[i]);
            mPicModel.setPhotoId(vocationPic[i]);
            mPhotoData.add(mPicModel);
        }
        int a = 0;
        hasNotchInOppo(VocationActivity.this);
        if (VocationActivity.this.getPackageManager().hasSystemFeature("com.oppo.feature.screen.heteromorphism")) {
            a = 1;

        } else {
            a = 2;
        }
        mVocationAdapter = new VocationAdapter(mPhotoData, VocationActivity.this, a);
        mPicGrid.setAdapter(mVocationAdapter);


    }

    public static boolean hasNotchInOppo(Context context) {
        return context.getPackageManager().hasSystemFeature("com.oppo.feature.screen.heteromorphism");

    }

    private PopupWindow popupWindow;

    private void listPopup(final String vocType) { //final String vocType,int gridX, int gridY
        getListData(vocType);
        final View popView = View.inflate(this, R.layout.popup_vocation_list, null);
        popupLv = (ListView) popView.findViewById(R.id.popup_vocation_lv);
        TextView textView = (TextView) popView.findViewById(R.id.title_text);
        textView.setText(vocType);
        //获取屏幕宽高
        int weight = getResources().getDisplayMetrics().widthPixels * 5 / 6;
        int height = getResources().getDisplayMetrics().heightPixels * 2 / 3;
        popupWindow = new PopupWindow(popView, weight, height);
        popupWindow.setFocusable(true);
        popupWindow.setAnimationStyle(R.style.mypopwindow_anim_style);
        //点击外部popueWindow消失
        popupWindow.setOutsideTouchable(true);
        popupLv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent toDetail = new Intent(VocationActivity.this, VocationDetailActivity.class);
                toDetail.putExtra("vocaname", mData.get(position).getVocaname());
                toDetail.putExtra("vocaid", mData.get(position).getVctid());
                startActivity(toDetail);
                popupWindow.dismiss();
            }
        });
        //popupWindow出现屏幕变为半透明
//        final WindowManager.LayoutParams
        lp = getWindow().getAttributes();
        lp.alpha = 0.6f;
        getWindow().setAttributes(lp);
        popupWindow.showAtLocation(popView, Gravity.CENTER, 0, 0);// gridX, gridY
//        popupWindow.showAsDropDown(popView, gridX, gridY);
//        popupWindow.showAsDropDown(popView, gridX, gridY, Gravity.CENTER);
        popupWindow.setOnDismissListener(new VocationActivity.poponDismissListener());

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

    private void getListData(String string) {
        FormEncodingBuilder builder = new FormEncodingBuilder();
        builder.add("vocType", string);
        builder.add("userId", String.valueOf(userId));
        Log.d("vocTypestring", string);
        Request request = new Request.Builder().url(Url.getVocationUrl).post(builder.build()).build();
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
                        mVocationModel = gson.fromJson(reader, VocationModel.class);
                        if (mVocationModel.getCode() == 1) {
                            closeDialog(loadingDialog);
                            mHandler.post(new Runnable() {
                                @Override
                                public void run() {
                                    mData = mVocationModel.getData();
                                    mVocationListAdapter = new VocationListAdapter(mData);
                                    popupLv.setAdapter(mVocationListAdapter);

                                }
                            });
                        } else if (mVocationModel.getCode() == 2) {
                            closeDialog(loadingDialog);
                            Looper.prepare();
                            Toast.makeText(VocationActivity.this, mVocationModel.getMsg() + "", Toast.LENGTH_SHORT).show();
                            Looper.loop();
                        } else {
                            closeDialog(loadingDialog);
                            Looper.prepare();
                            Toast.makeText(VocationActivity.this, mVocationModel.getMsg() + "", Toast.LENGTH_SHORT).show();
                            Looper.loop();
                        }
                    } else {
                        Looper.prepare();
                        Toast.makeText(VocationActivity.this, "服务器异常", Toast.LENGTH_SHORT).show(); //请求失败
                        Looper.loop();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        });
    }

    class VocationListAdapter extends BaseAdapter {
        private List<VocationModel.DataBean> mData;

        public VocationListAdapter(List<VocationModel.DataBean> data) {
            this.mData = data;
        }

        @Override
        public int getCount() {
            return mData.size();
        }

        @Override
        public Object getItem(int position) {
            return mData.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @SuppressLint("ResourceAsColor")
        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            ViewHolder holder = null;
            if (convertView == null) {
                convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_popup_voca, null);
                holder = new ViewHolder();
                holder.titleTv = (TextView) convertView.findViewById(R.id.item_vocation_name);
                //   holder.scImg = (AutoRelativeLayout) convertView.findViewById(R.id.item_vocation_sc);
                holder.mCardView = (CardView) convertView.findViewById(R.id.cardView);
                holder.sctitle = (Button) convertView.findViewById(R.id.sc_title);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            holder.titleTv.setText(mData.get(position).getVocaname() + "");
            if (mData.get(position).getIsfollow() == 0) {
                holder.sctitle.setBackgroundResource(R.drawable.sc_circle);
                holder.sctitle.setText("关注");
            } else {
                holder.sctitle.setBackgroundResource(R.drawable.sc_gray_circle);
                holder.sctitle.setText("已关注");
            }
            final ViewHolder finalHolder = holder;
            holder.sctitle.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    addSc(mData.get(position).getVctid(), finalHolder.sctitle);
                }
            });
            return convertView;
        }

        class ViewHolder {
            Button sctitle;
            TextView titleTv;
            AutoRelativeLayout scImg;
            CardView mCardView;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    private VocaScModel mVocaScModel;

    private void addSc(final String pos, final Button button) {
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
                            mHandler.post(new Runnable() {
                                @Override
                                public void run() {
                                    button.setBackgroundResource(R.drawable.sc_gray_circle);
                                    button.setText("已关注");
                                }
                            });
                            Log.d("关注", "成功");
                        } else if (mVocaScModel.getCode() == 2) {
                            mHandler.post(new Runnable() {
                                @Override
                                public void run() {
                                    button.setBackgroundResource(R.drawable.sc_circle);
                                    button.setText("关注");

                                }
                            });

                        } else {
                            Looper.prepare();
                            Toast.makeText(VocationActivity.this, mVocaScModel.getMsg() + "", Toast.LENGTH_SHORT).show();
                            Looper.loop();
                        }
                    } else {
                        Looper.prepare();
                        Toast.makeText(VocationActivity.this, "服务器异常", Toast.LENGTH_SHORT).show(); //请求失败
                        Looper.loop();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        });
    }
}
