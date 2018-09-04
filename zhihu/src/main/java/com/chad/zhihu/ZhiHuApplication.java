package com.chad.zhihu;

import android.app.Application;

import com.chad.zhihu.util.LogUtil;

public class ZhiHuApplication extends Application {

    private static final String TAG = ZhiHuApplication.class.getSimpleName();

    private static ZhiHuApplication zhiHuApplication;

    @Override
    public void onCreate() {
        LogUtil.d(TAG, "onCreate");
        zhiHuApplication = this;
        super.onCreate();
    }

    public static ZhiHuApplication getZhiHuApplication() {
        return zhiHuApplication;
    }
}
