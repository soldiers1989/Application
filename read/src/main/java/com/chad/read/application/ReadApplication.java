package com.chad.read.application;

import android.app.Application;

import com.chad.read.R;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

public class ReadApplication extends Application {

    private static final String TAG = ReadApplication.class.getSimpleName();

    @Override
    public void onCreate() {
        super.onCreate();
        initFonts();
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
}
