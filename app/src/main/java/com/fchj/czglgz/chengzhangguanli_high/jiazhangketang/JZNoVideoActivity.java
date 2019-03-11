package com.fchj.czglgz.chengzhangguanli_high.jiazhangketang;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.fchj.czglgz.chengzhangguanli_high.R;
import com.fchj.czglgz.chengzhangguanli_high.model.WebViewModel;
import com.fchj.czglgz.chengzhangguanli_high.util.Url;
import com.fchj.czglgz.chengzhangguanli_high.web.JSObject;
import com.fchj.czglgz.chengzhangguanli_high.web.MyWebChromeClient;
import com.fchj.czglgz.chengzhangguanli_high.web.MyWebViewClient;
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

/**
 * Created by Administrator on 2018/5/2 0002.
 */

public class JZNoVideoActivity extends Activity {
    private WebView mWebView;
    private ImageView returnImg;
    private WebViewModel mOpenModel;
    private Handler mHandler;
    private String url;
    private String title;
    private TextView titleTv;
    private WebSettings webSettings;
    private JSObject mJSObject;
    private MyWebChromeClient chromeClient;
    private MyWebViewClient WVClient;
    private int newsId;
    private JZDetailModel mJZDetailModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jz_no_video);
        mHandler = new Handler();
        mWebView = new WebView(this);
        mWebView = (WebView) findViewById(R.id.about_webview);
        Intent intent = getIntent();
        newsId = intent.getIntExtra("newsid", 0);
        setZiXun();
        returnImg = (ImageView) findViewById(R.id.title_fanhui);
        returnImg.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                finish();
            }
        });
        titleTv = (TextView) findViewById(R.id.title_text);
        mJSObject = new JSObject(JZNoVideoActivity.this);
        WVClient = new MyWebViewClient();
        chromeClient = new MyWebChromeClient();
    }

    private void setZiXun() {
        Log.d("adasdas", String.valueOf(newsId));
        FormEncodingBuilder builder = new FormEncodingBuilder();
        builder.add("pId", String.valueOf(newsId));
        Request request = new Request.Builder().url(Url.jzNoVideoUrl).post(builder.build()).build();
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
                        Log.d("asdasdasdasd", body);
                        Gson gson = new Gson();
                        JsonReader reader = new JsonReader(new StringReader(body));
                        mJZDetailModel = gson.fromJson(reader, JZDetailModel.class);

                        if (mJZDetailModel.getCode() == 1) {

                            mHandler.post(new Runnable() {
                                @Override
                                public void run() {


                                    addWebView(mJZDetailModel.getData().getClasscontenttexturl());
                                }
                            });
                        } else if (mJZDetailModel.getCode() == 2) {
                            Looper.prepare();
                            Toast.makeText(JZNoVideoActivity.this, mJZDetailModel.getMsg() + "", Toast.LENGTH_SHORT).show();
                            Looper.loop();
                        } else {
                            Looper.prepare();
                            Toast.makeText(JZNoVideoActivity.this, mJZDetailModel.getMsg() + "", Toast.LENGTH_SHORT).show();
                            Looper.loop();
                        }


                    } else {
                        Looper.prepare();
                        Toast.makeText(JZNoVideoActivity.this, "服务器异常", Toast.LENGTH_SHORT).show(); //请求失败
                        Looper.loop();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        });


    }

    private void addWebView(String url) {
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