package com.chad.weibo;

import android.app.Application;

import com.chad.weibo.util.LogUtil;
import com.facebook.drawee.backends.pipeline.Fresco;

public class WeiBoApplication extends Application {

    private static final String TAG = WeiBoApplication.class.getSimpleName();

    @Override
    public void onCreate() {
        LogUtil.d(TAG, "onCreate");
        initFresco();
        super.onCreate();
    }

    private void initFresco() {
        LogUtil.d(TAG, "initFresco");
        Fresco.initialize(this);
    }
}
