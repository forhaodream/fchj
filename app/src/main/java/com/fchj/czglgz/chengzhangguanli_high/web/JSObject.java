package com.fchj.czglgz.chengzhangguanli_high.web;

import android.content.Context;
import android.webkit.JavascriptInterface;
import android.widget.Toast;

/**
 * Created by Administrator on 2018/4/12 0012.
 */
public class JSObject {
    /*
      * 绑定的object对象
      * */
    private Context context;

    public JSObject(Context context) {
        this.context = context;
    }

    /*
     * JS调用android的方法
     * @JavascriptInterface仍然必不可少
     *
     * */
    @JavascriptInterface
    public void JsCallAndroid() {
        Toast.makeText(context, "上传成功", Toast.LENGTH_SHORT).show();
//        return "JS call Andorid";
    }
}
