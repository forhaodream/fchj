package com.fchj.czglgz.chengzhangguanli_high.travel;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.fchj.czglgz.chengzhangguanli_high.R;
import com.fchj.czglgz.chengzhangguanli_high.util.Url;
import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import com.squareup.okhttp.Call;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;
import java.io.StringReader;

public class TravelStudyDetailActivity extends Activity {
    private int tsId;
    private TravelDetailModel mTravelDetailModel;
    private Handler mHandler;
    private WebView mWebView;
    private WebSettings webSettings;
    private String url;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.avtivity_travel_study_detail);
        mHandler = new Handler();
        mWebView = new WebView(this);
        mWebView = (WebView) findViewById(R.id.about_webview);
        Intent intent = getIntent();
        url = intent.getStringExtra("urls");
        addWebView();

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
        mWebView.loadUrl(url);
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
        //自适应屏幕
        mWebView.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        mWebView.getSettings().setLoadWithOverviewMode(true);
        mWebView.getSettings().setUseWideViewPort(true);
        mWebView.getSettings().setDisplayZoomControls(true);
        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.clearCache(true);

    }


}
