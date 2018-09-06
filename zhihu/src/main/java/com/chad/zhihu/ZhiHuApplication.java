package com.chad.zhihu;

import android.app.Application;

import com.chad.zhihu.util.LogUtil;

import cn.bingoogolapple.swipebacklayout.BGASwipeBackManager;

public class ZhiHuApplication extends Application {

    private static final String TAG = ZhiHuApplication.class.getSimpleName();

    private static ZhiHuApplication zhiHuApplication;

    @Override
    public void onCreate() {
        LogUtil.d(TAG, "onCreate");
        zhiHuApplication = this;
        initSwipeBackManager();
        super.onCreate();
    }

    /**
     * 初始化SwipeBackManager
     */
    private void initSwipeBackManager() {
        LogUtil.d(TAG, "initSwipeBackManager");
        BGASwipeBackManager.getInstance().init(this);
    }

    public static ZhiHuApplication getZhiHuApplication() {
        return zhiHuApplication;
    }
}
