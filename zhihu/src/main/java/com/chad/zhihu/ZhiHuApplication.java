package com.chad.zhihu;

import android.app.Application;

import com.chad.zhihu.util.LogUtil;
import com.facebook.drawee.backends.pipeline.Fresco;

import cn.bingoogolapple.swipebacklayout.BGASwipeBackManager;

public class ZhiHuApplication extends Application {

    private static final String TAG = ZhiHuApplication.class.getSimpleName();

    private static ZhiHuApplication mZhiHuApplication;

    @Override
    public void onCreate() {
        LogUtil.d(TAG, "onCreate");
        mZhiHuApplication = this;
        initSwipeBackManager();
        initFresco();
        super.onCreate();
    }

    /**
     * 初始化SwipeBackManager
     */
    private void initSwipeBackManager() {
        LogUtil.d(TAG, "initSwipeBackManager");
        BGASwipeBackManager.getInstance().init(this);
    }

    private void initFresco() {
        LogUtil.d(TAG, "initFresco");
        Fresco.initialize(this);
    }

    public static ZhiHuApplication getZhiHuApplication() {
        return mZhiHuApplication;
    }
}
