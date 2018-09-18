package com.chad.weibo;

import android.app.Application;

import com.chad.weibo.constant.WeiBoConstant;
import com.chad.weibo.util.LogUtil;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.sina.weibo.sdk.WbSdk;
import com.sina.weibo.sdk.auth.AuthInfo;

public class WeiBoApplication extends Application {

    private static final String TAG = WeiBoApplication.class.getSimpleName();

    private static WeiBoApplication mWeiBoApplication = null;

    @Override
    public void onCreate() {
        LogUtil.d(TAG, "onCreate");
        mWeiBoApplication = this;
        initFresco();
        initWeiBoSdk();
        super.onCreate();
    }

    private void initFresco() {
        LogUtil.d(TAG, "initFresco");
        Fresco.initialize(this);
    }

    private void initWeiBoSdk() {
        LogUtil.d(TAG, "initWeiBoSdk");
        AuthInfo authInfo = new AuthInfo(this, WeiBoConstant.APP_KEY,
                WeiBoConstant.REDIRECT_URL, WeiBoConstant.SCOPE);
        WbSdk.install(this, authInfo);
    }

    public static WeiBoApplication getWeiBoApplication() {
        LogUtil.d(TAG, "getWeiBoApplication");
        return mWeiBoApplication == null? null: mWeiBoApplication;
    }
}
