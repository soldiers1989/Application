package com.chad.zhihu;

import android.app.Application;

import com.chad.zhihu.dagger.app.AppComponent;
import com.chad.zhihu.dagger.app.AppModule;
import com.chad.zhihu.dagger.app.DaggerAppComponent;
import com.chad.zhihu.util.LogUtil;

public class ZhiHuApplication extends Application {

    private static final String TAG = ZhiHuApplication.class.getSimpleName();

    private static AppComponent appComponent = null;

    @Override
    public void onCreate() {
        initAppComponent();
        LogUtil.d(TAG, "onCreate");
        super.onCreate();
    }

    private void initAppComponent() {
        appComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .build();
        LogUtil.d(TAG, "initAppComponent");
    }

    public static AppComponent getAppComponent() {
        LogUtil.d(TAG, "getAppComponent : appComponent" + appComponent);
        return appComponent;
    }
}
