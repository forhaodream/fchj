package com.fchj.czglgz.chengzhangguanli_high.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;

import com.fchj.czglgz.chengzhangguanli_high.R;
import com.fchj.czglgz.chengzhangguanli_high.web.JSObject;
import com.fchj.czglgz.chengzhangguanli_high.web.MyWebChromeClient;
import com.fchj.czglgz.chengzhangguanli_high.web.MyWebViewClient;

/**
 * Created by Administrator on 2018/4/25 0025.
 */

public class AboutWebActivity extends Activity {
    private WebSettings webSettings;
    private WebView mWebView;
    private JSObject mJSObject;
    private MyWebChromeClient chromeClient;
    private MyWebViewClient WVClient;
    private ImageView returnImg;
    private String url = "";
    private String title = "";
    private TextView titleTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_web);
        returnImg = (ImageView) findViewById(R.id.title_fanhui);
        returnImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        mWebView = new WebView(this);
        mWebView = (WebView) findViewById(R.id.about_webview);
        mJSObject = new JSObject(AboutWebActivity.this);
        WVClient = new MyWebViewClient();
        chromeClient = new MyWebChromeClient();
        titleTv = (TextView) findViewById(R.id.title_text);
        Intent intent = getIntent();
        url = intent.getStringExtra("urls");
        title = intent.getStringExtra("title");
        titleTv.setText(title);
        addWebView(url);
    }

    private void addWebView(String url) {
        webSettings = mWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setBuiltInZoomControls(true);
        webSettings.setSupportZoom(true);
        webSettings.setSavePassword(false);
        //支持多种分辨率，需要js网页支持
        webSettings.setUserAgentString("mac os");
        webSettings.setDefaultTextEncodingName("utf-8");
        //显示本地js网页
        mWebView.loadUrl(url);
        mWebView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return false;

            }
        });
        mWebView.setWebChromeClient(new WebChromeClient());
        //注意第二个参数JsTest，这个是JS网页调用Android方法的一个类似ID的东西
        //自适应屏幕
        mWebView.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        mWebView.getSettings().setLoadWithOverviewMode(true);
        mWebView.getSettings().setUseWideViewPort(true);
        mWebView.getSettings().setDisplayZoomControls(false);
        mWebView.clearCache(true);
//        mWebView.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                return true;
//            }
//        });

    }

}
