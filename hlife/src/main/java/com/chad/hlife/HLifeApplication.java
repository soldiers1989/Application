package com.chad.hlife;

import android.app.Application;

import com.chad.hlife.util.LogUtil;
import com.facebook.drawee.backends.pipeline.Fresco;

public class HLifeApplication extends Application {

    private static final String TAG = HLifeApplication.class.getSimpleName();

    private static HLifeApplication mHLifeApplication = null;

    @Override
    public void onCreate() {
        LogUtil.d(TAG, "onCreate");
        mHLifeApplication = this;
        initFresco();
        super.onCreate();
    }

    private void initFresco() {
        LogUtil.d(TAG, "initFresco");
        Fresco.initialize(this);
    }

    public static HLifeApplication getHLifeApplication() {
        LogUtil.d(TAG, "getHLifeApplication");
        return mHLifeApplication == null? null: mHLifeApplication;
    }
}
