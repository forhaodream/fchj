package com.fchj.czglgz.chengzhangguanli_high.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.fchj.czglgz.chengzhangguanli_high.R;
import com.fchj.czglgz.chengzhangguanli_high.util.Url;
import com.stx.xhb.xbanner.XBanner;
import com.stx.xhb.xbanner.transformers.Transformer;

import java.util.ArrayList;
import java.util.List;

public class BannerTest extends Activity {
    private XBanner mXBanner;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_free_ask);
//        mXBanner = (XBanner) findViewById(R.id.os_detail_xbanner);
//
//        List<String> imgesUrl = new ArrayList<>();
//        imgesUrl.add(Url.fileUrl + "/HighShoolManager/image/banner/201805081501072915.jpg");
//        imgesUrl.add(Url.fileUrl + "/HighShoolManager/image/banner/201806121006496655.jpg");
//        imgesUrl.add(Url.fileUrl + "/HighShoolManager/image/banner/20180427121539318.jpg");
//        imgesUrl.add(Url.fileUrl + "/HighShoolManager/image/banner/201806121006496655.jpg");
//        mXBanner.setData(imgesUrl, null);//第二个参数为提示文字资源集合
//        mXBanner.loadImage(new XBanner.XBannerAdapter() {
//            @Override
//            public void loadBanner(XBanner banner, Object model, View view, int position) {
//                Glide.with(BannerTest.this).load(Url.fileUrl + "/HighShoolManager/image/banner/201806121006496655.jpg").placeholder(R.drawable.ic_launcher).error(R.drawable.ic_launcher).into((ImageView) view);
//            }
//        });
//        mXBanner.setPageTransformer(Transformer.Accordion);
    }
}
