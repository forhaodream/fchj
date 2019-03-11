package com.fchj.czglgz.chengzhangguanli_high.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
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
 * Created by Administrator on 2018/5/7 0007.
 */

public class BannerDetailActivity extends Activity {
    private TextView titleTv;
    private ImageView returnImg;
    private String titleName, detailUrl;
    private WebSettings webSettings;
    private JSObject mJSObject;
    private MyWebChromeClient chromeClient;
    private MyWebViewClient WVClient;
    private WebView mWebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_banner_detail);
        mWebView = new WebView(this);
        mWebView = (WebView) findViewById(R.id.about_webview);
        returnImg = (ImageView) findViewById(R.id.title_fanhui);
        returnImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        Intent intent = getIntent();
        titleName = intent.getStringExtra("");
        detailUrl = intent.getStringExtra("");
        titleTv = (TextView) findViewById(R.id.title_text);
        titleTv.setText(titleName);
        mJSObject = new JSObject(BannerDetailActivity.this);
        WVClient = new MyWebViewClient();
        chromeClient = new MyWebChromeClient();
    }
    private void addWebView() {
        webSettings = mWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setBuiltInZoomControls(true);
        webSettings.setSavePassword(false);
        //支持多种分辨率，需要js网页支持
        webSettings.setUserAgentString("mac os");
        webSettings.setDefaultTextEncodingName("utf-8");
        //显示本地js网页
        mWebView.loadUrl(detailUrl);
//        mWebView.setWebViewClient(WVClient);
//        mWebView.setWebChromeClient(chromeClient);
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
        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.clearCache(true);

    }
}
