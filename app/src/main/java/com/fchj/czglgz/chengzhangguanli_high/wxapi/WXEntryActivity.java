package com.fchj.czglgz.chengzhangguanli_high.wxapi;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import com.fchj.czglgz.chengzhangguanli_high.activity.HomeActivity;
import com.fchj.czglgz.chengzhangguanli_high.activity.LoginActivity;
import com.fchj.czglgz.chengzhangguanli_high.model.LoginModel;
import com.fchj.czglgz.chengzhangguanli_high.model.WxInfoModel;
import com.fchj.czglgz.chengzhangguanli_high.model.WxLoginModel;
import com.fchj.czglgz.chengzhangguanli_high.model.WxModel;
import com.fchj.czglgz.chengzhangguanli_high.util.IntentUtil;
import com.fchj.czglgz.chengzhangguanli_high.util.Url;
import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import com.squareup.okhttp.Call;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.modelmsg.SendAuth;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import java.io.IOException;
import java.io.StringReader;

public class WXEntryActivity extends Activity implements IWXAPIEventHandler {
    private static final String TAG = "WXEntryActivity";
    private IWXAPI mIWXAPI;
    private static final String APP_ID = "wx9fefde76187f8505";
    private static final String SECRET = "a6830b221e19cb2192a0c4fe596234cf";
    private Handler mHandler;
    private String htmlStr;
    private String accessToken;
    private String openId;
    private String openidStr;
    private String unionid;
    private String username;
    private String headurl;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("-------", TAG);
        mHandler = new Handler();
        mIWXAPI = WXAPIFactory.createWXAPI(this, "wx9fefde76187f8505", true);
        mIWXAPI.handleIntent(getIntent(), this);

    }

    @Override
    public void onReq(BaseReq baseReq) {

    }

    @Override
    public void onResp(BaseResp baseResp) {
//登录回调
        switch (baseResp.errCode) {
            case BaseResp.ErrCode.ERR_OK:
                String code = ((SendAuth.Resp) baseResp).code;
                //获取用户信息
                Log.d("code", code + "");
                getAccessToken(code);
                break;
            case BaseResp.ErrCode.ERR_AUTH_DENIED://用户拒绝授权
                finish();
                break;
            case BaseResp.ErrCode.ERR_USER_CANCEL://用户取消
                finish();
                break;
            default:
                finish();
                break;
        }
    }

    private WxModel mWxModel;

    private void getAccessToken(String code) {
        //创建okHttpClient对象
        OkHttpClient mOkHttpClient = new OkHttpClient();
        final Request request = new Request.Builder()
                .url("https://api.weixin.qq.com/sns/oauth2/access_token?appid=" + APP_ID
                        + "&secret=" + SECRET
                        + "&code=" + code
                        + "&grant_type=authorization_code")
                .build();
        Call call = mOkHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {
            }

            @Override
            public void onResponse(final Response response) throws IOException {
                String body = response.body().string();
                if (!TextUtils.isEmpty(body)) {
                    Gson gson = new Gson();
                    JsonReader reader = new JsonReader(new StringReader(body));
                    mWxModel = gson.fromJson(reader, WxModel.class);
                    mHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            accessToken = mWxModel.getAccess_token();
                            openId = mWxModel.getOpenid();
                            getWxInfo(accessToken, openId);
                        }
                    });
                } else {
                    Toast.makeText(WXEntryActivity.this, "获取失败", Toast.LENGTH_SHORT).show();
                }


            }
        });
    }

    private WxInfoModel mWxInfoModel;

    private void getWxInfo(String access, String openid) {
        OkHttpClient mOkHttpClient = new OkHttpClient();
        final Request request = new Request.Builder()
                .url("https://api.weixin.qq.com/sns/userinfo?access_token=" + access
                        + "&openid=" + openid)
                .build();
        Call call = mOkHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {
            }

            @Override
            public void onResponse(final Response response) throws IOException {
                final String body = response.body().string();
                if (!TextUtils.isEmpty(body)) {
                    Gson gson = new Gson();
                    JsonReader reader = new JsonReader(new StringReader(body));
                    mWxInfoModel = gson.fromJson(reader, WxInfoModel.class);
                    mHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            Log.d("bbbb", body);
                         //   Log.d("bbbb", mWxInfoModel.getHeadimgurl());
                            openidStr = mWxInfoModel.getOpenid();
                            unionid = mWxInfoModel.getUnionid();
                            username = mWxInfoModel.getNickname();
                            headurl = mWxInfoModel.getHeadimgurl();
                            wxLogin();
                        }
                    });
                } else {
                    Toast.makeText(WXEntryActivity.this, "获取失败", Toast.LENGTH_SHORT).show();
                }


            }
        });
    }

    private WxLoginModel mWxLoginModel;

    private void wxLogin() {
        FormEncodingBuilder builder = new FormEncodingBuilder();
        builder.add("openid", openidStr);
        builder.add("unionid", unionid);
        builder.add("username", username);
        builder.add("headurl", headurl);
        Request request = new Request.Builder().url(Url.wxLoginUrl).post(builder.build()).build();
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
                        Log.d("wx_login_body", body);
                        Gson gson = new Gson();
                        JsonReader reader = new JsonReader(new StringReader(body));
                        mWxLoginModel = gson.fromJson(reader, WxLoginModel.class);
                        if (mWxLoginModel.getCode() == 1) {
                            SharedPreferences.Editor userInfo = getSharedPreferences("user_npt", MODE_PRIVATE).edit();
                            userInfo.putString("userName", mWxLoginModel.getData().getSignname() + "");
                            userInfo.putInt("userId", mWxLoginModel.getData().getId());
                            userInfo.putInt("userType", mWxLoginModel.getData().getUsertype());
                            userInfo.commit();
                            Looper.prepare();
                            Toast.makeText(WXEntryActivity.this, "微信登录成功", Toast.LENGTH_SHORT).show();
                            IntentUtil.showIntent(WXEntryActivity.this, HomeActivity.class);
                            Looper.loop();
                        } else if (mWxLoginModel.getCode() == 2) {
                            Toast.makeText(WXEntryActivity.this, "账号不存在", Toast.LENGTH_SHORT).show();
                        } else if (mWxLoginModel.getCode() == 3) {
                            Toast.makeText(WXEntryActivity.this, "密码错误", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(WXEntryActivity.this, "登录失败", Toast.LENGTH_SHORT).show();
                        }


                    } else {
                        Looper.prepare();
                        Toast.makeText(WXEntryActivity.this, "服务器异常", Toast.LENGTH_SHORT).show(); //请求失败
                        Looper.loop();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }


            }
        });


    }

}
