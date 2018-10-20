package com.chad.hlife;

import android.app.Application;

import com.chad.hlife.app.config.WeiBoConfig;
import com.chad.hlife.util.LogUtil;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.mob.MobSDK;
import com.sina.weibo.sdk.WbSdk;
import com.sina.weibo.sdk.auth.AuthInfo;

public class HLifeApplication extends Application {

    private static final String TAG = HLifeApplication.class.getSimpleName();

    private static HLifeApplication mHLifeApplication = null;

    @Override
    public void onCreate() {
        LogUtil.d(TAG, "onCreate");
        mHLifeApplication = this;
        initMob();
        initWeiBoSdk();
        initFresco();
        super.onCreate();
    }

    private void initMob() {
        LogUtil.d(TAG, "initMob");
        MobSDK.init(this);
    }

    private void initWeiBoSdk() {
        LogUtil.d(TAG, "initWeiBoSdk");
        AuthInfo authInfo = new AuthInfo(this, WeiBoConfig.APP_KEY,
                WeiBoConfig.REDIRECT_URL, WeiBoConfig.SCOPE);
        WbSdk.install(this, authInfo);
    }

    private void initFresco() {
        LogUtil.d(TAG, "initFresco");
        Fresco.initialize(this);
    }

    public static HLifeApplication getHLifeApplication() {
        LogUtil.d(TAG, "getHLifeApplication");
        return mHLifeApplication == null ? null : mHLifeApplication;
    }
}
