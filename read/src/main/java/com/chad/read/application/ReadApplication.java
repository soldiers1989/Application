package com.chad.read.application;

import android.app.Application;

import com.chad.read.R;
import com.chad.read.util.LogUtil;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

/**
 * 全局Application
 */
public class ReadApplication extends Application {

    private static final String TAG = ReadApplication.class.getSimpleName();

    private static ReadApplication mReadApplication;

    @Override
    public void onCreate() {
        super.onCreate();
        mReadApplication = this;
        initFonts();
        LogUtil.d(TAG, "onCreate");
    }

    /**
     * 初始化字体加载库
     */
    private void initFonts() {
        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/Lobster-1.4.otf")
                .setFontAttrId(R.attr.fontPath)
                .build());
    }

    public static ReadApplication getReadApplication() {
        return mReadApplication;
    }
}
