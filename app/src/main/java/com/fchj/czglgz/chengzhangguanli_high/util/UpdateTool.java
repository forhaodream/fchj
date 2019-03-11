package com.fchj.czglgz.chengzhangguanli_high.util;

import android.app.ActivityManager;
import android.app.Application;
import android.content.pm.PackageManager;

import com.lh.ch.updateversion.UpdateHelper;
import com.lh.ch.updateversion.bean.UpdateEntity;
import com.lh.ch.updateversion.listener.ParseData;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Administrator on 2018/5/2 0002.
 */

public class UpdateTool extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
//        EMOptions options = new EMOptions();
//        options.setAcceptInvitationAlways(false);
//
//        // 初始化
//        EMClient.getInstance().init(getApplicationContext(), options);
//
//        EMClient.getInstance().setDebugMode(true);
        HashMap<String, String> mmm = new HashMap<>();
        mmm.put("", "");

        UpdateHelper.init(this);

        UpdateHelper
                .getInstance()
                .post(Url.updateUrl, mmm)

//                .get("http://www.pp11.cn/wap/json/shengji.aspx")

                .setDownType(UpdateHelper.DownType.down_auto_Install)
                .showProgressDialog(true)
                .setJsonParser(new ParseData() {
                    @Override
                    public UpdateEntity parse(String httpResponse) {
                        UpdateEntity updateEntity = new UpdateEntity();
                        updateEntity.setAppName("成长管家-高中版");
                        updateEntity.setContent("本次升级新增、优化了部分功能。");
                        //https://qd.myapp.com/myapp/qqteam/AndroidQQ/mobileqq_android.apk
                        //http://www.kuxuel.com/shengji/kuxuele.apkhttp://www.pp11.cn/shengji/a.apk
                        updateEntity.setUpdateUrl("http://growup-highschool.oss-cn-hangzhou.aliyuncs.com/APK/gumhs.apk");
                        updateEntity.setVersionCode(2);
                        updateEntity.setVersionName("1.1.3");
                        return updateEntity;
                    }
                });
    }

    private String getAppName(int pID) {
        String processName = null;
        ActivityManager am = (ActivityManager) this.getSystemService(ACTIVITY_SERVICE);
        List l = am.getRunningAppProcesses();
        Iterator i = l.iterator();
        PackageManager pm = this.getPackageManager();
        while (i.hasNext()) {
            ActivityManager.RunningAppProcessInfo info = (ActivityManager.RunningAppProcessInfo) i.next();
            try {
                if (info.pid == pID) {
                    processName = info.processName;
                    return processName;
                }
            } catch (Exception e) {


            }

        }

        return processName;
    }


}
