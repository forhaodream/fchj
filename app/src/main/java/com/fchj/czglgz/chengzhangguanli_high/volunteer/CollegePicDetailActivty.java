package com.fchj.czglgz.chengzhangguanli_high.volunteer;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.fchj.czglgz.chengzhangguanli_high.R;
import com.fchj.czglgz.chengzhangguanli_high.activity.NewLoginActivity;
import com.fchj.czglgz.chengzhangguanli_high.dropdownmenu.DropActivity;
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
import java.util.List;

public class CollegePicDetailActivty extends Activity {
    private GridView mPicGrid;
    private CollegePicDetailAdapter mAdapter;
    private CollegePicDetailModel mCollegePicDetailModel;
    private List<CollegePicDetailModel.DataBean> mPicData;
    private ImageView returnImg;
    private Handler mHandler;
    private String guid = "";
    public WindowManager.LayoutParams lp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_college_pic_detail);
        mHandler = new Handler();
        mPicGrid = (GridView) findViewById(R.id.college_pic_detail);
        mPicGrid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Toast.makeText(CollegePicDetailActivty.this, "aaa", Toast.LENGTH_SHORT).show();
                picPopup(mPicData.get(position).getSpecificimg());//specificimg
                getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
            }
        });
        returnImg = (ImageView) findViewById(R.id.title_fanhui);
        returnImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        Intent intent = getIntent();
        guid = intent.getStringExtra("guid");
        addPic();

    }

    private void addPic() {
        FormEncodingBuilder builder = new FormEncodingBuilder();
        builder.add("guid", guid + "");
//        builder.add("pageNum", "1");
//        builder.add("pageSize", "20");
        Log.d("guid", guid + "");
        Request request = new Request.Builder().url(Url.getSceneryUrl).post(builder.build()).build();
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
                        mCollegePicDetailModel = gson.fromJson(reader, CollegePicDetailModel.class);
                        if (mCollegePicDetailModel.getCode() == 1) {

                            mHandler.post(new Runnable() {
                                @Override
                                public void run() {
                                    mPicData = mCollegePicDetailModel.getData();
                                    mAdapter = new CollegePicDetailAdapter(mPicData);
                                    mPicGrid.setAdapter(mAdapter);

                                }
                            });
                        } else {
                            Log.d("没有", "数据");
                        }

                    } else {
                        Looper.prepare();
                        Toast.makeText(CollegePicDetailActivty.this, "服务器异常", Toast.LENGTH_SHORT).show(); //请求失败
                        Looper.loop();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        });


    }

    private void picPopup(String url) {//
        final View popView = View.inflate(this, R.layout.popwindow_full_pic, null);
        AutoRelativeLayout layout = (AutoRelativeLayout) popView.findViewById(R.id.popup_pic_rl);
        ImageView imageView = (ImageView) popView.findViewById(R.id.full_pic_img);
        //获取屏幕宽高
        int weight = getResources().getDisplayMetrics().widthPixels;
        int height = getResources().getDisplayMetrics().heightPixels;
        final PopupWindow popupWindow = new PopupWindow(popView, weight, height);
        popupWindow.setFocusable(true);
        //点击外部popueWindow消失
        popupWindow.setOutsideTouchable(true);
        layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                popupWindow.dismiss();
            }
        });
        Glide.with(getApplication()).load(url).into(imageView);


        //popupWindow出现屏幕变为半透明
//        final WindowManager.LayoutParams
        lp = getWindow().getAttributes();
        lp.alpha = 0.5f;
        getWindow().setAttributes(lp);
        popupWindow.showAtLocation(popView, Gravity.BOTTOM, 0, 0);
//
        popupWindow.setOnDismissListener(new poponDismissListener());

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


}
