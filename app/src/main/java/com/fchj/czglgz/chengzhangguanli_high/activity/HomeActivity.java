package com.fchj.czglgz.chengzhangguanli_high.activity;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.hardware.Sensor;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTabHost;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.fchj.czglgz.chengzhangguanli_high.R;
import com.fchj.czglgz.chengzhangguanli_high.fragment.HomeFragment;
import com.fchj.czglgz.chengzhangguanli_high.fragment.MineFragment;
import com.fchj.czglgz.chengzhangguanli_high.fragment.NewGaoKaoFragment;
import com.fchj.czglgz.chengzhangguanli_high.fragment.NewHomeFragment;
import com.fchj.czglgz.chengzhangguanli_high.fragment.VolunteerFragment;
import com.fchj.czglgz.chengzhangguanli_high.fragment.XueBaFragment;
import com.fchj.czglgz.chengzhangguanli_high.util.IntentUtil;
import com.fchj.czglgz.chengzhangguanli_high.wodetiwen.QuestionAskActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;

import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayerStandard;

/**
 * Created by Administrator on 2018/4/9 0009.
 */

public class HomeActivity extends AppCompatActivity implements View.OnClickListener {
    public static final String TAG = "HomeActivity";
    final private int REQUEST_CODE_ASK_PERMISSIONS = 123;//权限请求码
    private FragmentTabHost mTabHost;
    private LinearLayout button0, button3, newButton;
    private RelativeLayout button1;
    private static Boolean isQuit = false;
    private Timer timer = new Timer();
    // 视频相关
    private SensorManager sensorManager;
    private SensorEventListener sensorEventListener;
    //初始化标签数组
    String tabs[] = {"Tab1", "Tab3", "Tab2", "Tab4"};

    //初始化界面数组
    Class cls[] = {NewHomeFragment.class, VolunteerFragment.class, NewGaoKaoFragment.class, MineFragment.class};

    private ImageView image1, image2, image4, newImage;
    private TextView text1, text2, text3, text4, text5;
    private RelativeLayout button2; //显示数字标签布局
    private ImageView xueBaImg;
    //清除sp
    private SharedPreferences npt;
    private boolean isExit;
    private Handler mHandler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            isExit = false;
        }

    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        List<String> permissionList = new ArrayList<>();
        if (ContextCompat.checkSelfPermission(HomeActivity.this, Manifest.permission.READ_PHONE_STATE) !=
                PackageManager.PERMISSION_GRANTED) {
            permissionList.add(Manifest.permission.READ_PHONE_STATE);
        }
        if (ContextCompat.checkSelfPermission(HomeActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) !=
                PackageManager.PERMISSION_GRANTED) {
            permissionList.add(Manifest.permission.WRITE_EXTERNAL_STORAGE);
        }
        if (ContextCompat.checkSelfPermission(HomeActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) !=
                PackageManager.PERMISSION_GRANTED) {
            permissionList.add(Manifest.permission.ACCESS_FINE_LOCATION);
        }
        if (ContextCompat.checkSelfPermission(HomeActivity.this, Manifest.permission.ACCESS_COARSE_LOCATION) !=
                PackageManager.PERMISSION_GRANTED) {
            permissionList.add(Manifest.permission.ACCESS_COARSE_LOCATION);
        }
        if (!permissionList.isEmpty()) {
            String[] permissions = permissionList.toArray(new String[permissionList.size()]);
            ActivityCompat.requestPermissions(HomeActivity.this, permissions, 1);
        } else {
            Log.d("homeA", "已授权");
        }

        mHandler = new Handler();
        addView();
        Intent intent = getIntent();
        int index = intent.getIntExtra("huan", -1);
        Log.d("home_index", String.valueOf(index));
        if (String.valueOf(index).equals("1")) {
            setLayoutButton1();
            mTabHost.setCurrentTabByTag(tabs[0]);
        }

    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        if (newConfig.fontScale != 1)//非默认值
            getResources();
        super.onConfigurationChanged(newConfig);
    }

    @Override
    public Resources getResources() {
        Resources res = super.getResources();
        if (res.getConfiguration().fontScale != 1) {//非默认值
            Configuration newConfig = new Configuration();
            newConfig.setToDefaults();//设置默认
            res.updateConfiguration(newConfig, res.getDisplayMetrics());
        }
        return res;
    }

    private void addView() {
        //实例化控件
        this.image1 = (ImageView) findViewById(R.id.image1);
        this.image2 = (ImageView) findViewById(R.id.image2);
        this.image4 = (ImageView) findViewById(R.id.image4);
        this.newImage = (ImageView) findViewById(R.id.image_new);
//        xueBaImg = (ImageView) findViewById(R.id.home_xueba);
//        xueBaImg.setOnClickListener(this);
        this.text1 = (TextView) findViewById(R.id.text1);
        this.text2 = (TextView) findViewById(R.id.text_new);
        this.text3 = (TextView) findViewById(R.id.text2);
        this.text4 = (TextView) findViewById(R.id.text4);
        //实例化 FragmentTabHost (注：id 的获取必须为固定) 与 FrameLayout 布局
        mTabHost = (FragmentTabHost) this.findViewById(android.R.id.tabhost);
        mTabHost.setup(this, getSupportFragmentManager(), R.id.realtabcontent);
        mTabHost.getTabWidget().setVisibility(View.GONE);//隐藏顶部切换菜单
        for (int i = 0; i < tabs.length; i++) {
            //向 FragmentTabHost 添加标签以及 Fragment 界面
            mTabHost.addTab(mTabHost.newTabSpec(tabs[i]).setIndicator(tabs[i]),
                    cls[i], null);

        }
        npt = getSharedPreferences("user_npt", MODE_PRIVATE);
        String a = npt.getString("userName", "");
        Log.d("asasas", a);
        //实例化布局按钮控件
        button0 = (LinearLayout) findViewById(R.id.Button0);
        button1 = (RelativeLayout) findViewById(R.id.Button1);
        button3 = (LinearLayout) findViewById(R.id.Button3);
        newButton = (LinearLayout) findViewById(R.id.Button_new);


        //设置监听事件
        this.button0.setOnClickListener(this);
        this.button1.setOnClickListener(this);
        this.newButton.setOnClickListener(this);
        this.button3.setOnClickListener(this);


        //设置默认选中标签
        mTabHost.setCurrentTabByTag(tabs[0]);
// 视频相关
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        sensorEventListener = new JCVideoPlayer.JCAutoFullscreenListener();

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            // 首页
            case R.id.Button0:
                setLayoutButton1();
                mTabHost.setCurrentTabByTag(tabs[0]);
                break;
            // 高考志愿
            case R.id.Button_new:
                setLayoutButton2();
                mTabHost.setCurrentTabByTag(tabs[1]);
//                showPopueWindow();
                break;
            // 新高考
            case R.id.Button1:
                setlayoutbutton3();
                mTabHost.setCurrentTabByTag(tabs[2]);
                break;

            // 我的
            case R.id.Button3:
                setlayoutbutton4();
                mTabHost.setCurrentTabByTag(tabs[3]);
                break;

        }
    }

    //设置点击切换标签字体颜色与背景图片的切换
    private void setLayoutButton1() {
        image1.setBackgroundResource(R.mipmap.home_lan);
        newImage.setBackgroundResource(R.mipmap.zy_hui);
        image2.setBackgroundResource(R.mipmap.xueba_hui);
        image4.setBackgroundResource(R.mipmap.mine_hui);
        text1.setTextColor(this.getResources().getColor(R.color.text_green));
        text2.setTextColor(this.getResources().getColor(R.color.text_grays));
        text3.setTextColor(this.getResources().getColor(R.color.text_grays));
        text4.setTextColor(this.getResources().getColor(R.color.text_grays));

    }

    private void setLayoutButton2() {
        image1.setBackgroundResource(R.mipmap.home_hui);
        newImage.setBackgroundResource(R.mipmap.zy_lan);
        image2.setBackgroundResource(R.mipmap.xueba_hui);
        image4.setBackgroundResource(R.mipmap.mine_hui);
        text1.setTextColor(this.getResources().getColor(R.color.text_grays));
        text2.setTextColor(this.getResources().getColor(R.color.text_green));
        text3.setTextColor(this.getResources().getColor(R.color.text_grays));
        text4.setTextColor(this.getResources().getColor(R.color.text_grays));
    }

    private void setlayoutbutton3() {
        image1.setBackgroundResource(R.mipmap.home_hui);
        newImage.setBackgroundResource(R.mipmap.zy_hui);
        image2.setBackgroundResource(R.mipmap.xueba_lan);
        image4.setBackgroundResource(R.mipmap.mine_hui);
        text1.setTextColor(this.getResources().getColor(R.color.text_grays));
        text2.setTextColor(this.getResources().getColor(R.color.text_grays));
        text3.setTextColor(this.getResources().getColor(R.color.text_green));
        text4.setTextColor(this.getResources().getColor(R.color.text_grays));
    }

    private void setlayoutbutton4() {
        image1.setBackgroundResource(R.mipmap.home_hui);
        newImage.setBackgroundResource(R.mipmap.zy_hui);
        image2.setBackgroundResource(R.mipmap.xueba_hui);
        image4.setBackgroundResource(R.mipmap.mine_lan);
        text1.setTextColor(this.getResources().getColor(R.color.text_grays));
        text2.setTextColor(this.getResources().getColor(R.color.text_grays));
        text3.setTextColor(this.getResources().getColor(R.color.text_grays));
        text4.setTextColor(this.getResources().getColor(R.color.text_green));
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            exit();
            System.gc();

            return false;
        } else {
            return super.onKeyDown(keyCode, event);
        }
    }

    public void exit() {
        if (!isExit) {
            isExit = true;
            Toast.makeText(getApplicationContext(), "再按一次退出程序", Toast.LENGTH_SHORT).show();
            mHandler.sendEmptyMessageDelayed(0, 2000);
        } else {
            Intent intent = new Intent(Intent.ACTION_MAIN);
            intent.addCategory(Intent.CATEGORY_HOME);
            startActivity(intent);
            System.exit(0);
        }
    }

    JCVideoPlayerStandard mJCVideoPlayerStandard;

    @Override
    public void onBackPressed() {
        if (JCVideoPlayer.backPress()) {
            return;
        }
        super.onBackPressed();
    }

    @Override
    protected void onResume() {
        super.onResume();
        Sensor accelerometerSensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        sensorManager.registerListener(sensorEventListener, accelerometerSensor, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(sensorEventListener);
        JCVideoPlayer.releaseAllVideos();
    }


    public WindowManager.LayoutParams lp;

    private void showPopueWindow() {
        final View popView = View.inflate(this, R.layout.popwindow_home, null);

        Button bt_cancle = (Button) popView.findViewById(R.id.btn_pop_cancel);

        //获取屏幕宽高
        int weight = getResources().getDisplayMetrics().widthPixels * 4 / 5;
        int height = getResources().getDisplayMetrics().heightPixels * 1 / 4;

        final PopupWindow popupWindow = new PopupWindow(popView, weight, height);
        //   popupWindow.setAnimationStyle(R.style.anim_popup_dir);
        popupWindow.setFocusable(true);
        //点击外部popueWindow消失
        popupWindow.setOutsideTouchable(true);

        //popupWindow出现屏幕变为半透明
//        final WindowManager.LayoutParams
        lp = getWindow().getAttributes();
        lp.alpha = 0.5f;
        getWindow().setAttributes(lp);
        popupWindow.showAtLocation(popView, Gravity.CENTER, 0, 50);
        bt_cancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                popupWindow.dismiss();
            }
        });
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

