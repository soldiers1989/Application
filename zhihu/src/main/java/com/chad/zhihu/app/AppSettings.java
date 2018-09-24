package com.chad.zhihu.app;

import android.content.Context;
import android.content.SharedPreferences;

import com.chad.zhihu.ZhiHuApplication;

public class AppSettings {

    public static final String NAME_PREFERENCES = "Settings";
    public static final String MODE_PICTURE = "pictureMode";

    private SharedPreferences mSharedPreferences = null;

    private static volatile AppSettings  mAppSettings = null;

    public static AppSettings getInstance() {
        synchronized (AppSettings.class) {
            if (mAppSettings == null) {
                mAppSettings = new AppSettings();
            }
        }
        return mAppSettings;
    }

    private AppSettings() {
        mSharedPreferences = ZhiHuApplication.getZhiHuApplication()
                .getSharedPreferences(NAME_PREFERENCES, Context.MODE_PRIVATE);
    }

    public void setPictureMode(boolean isShow) {
        if (mSharedPreferences == null) {
            return;
        }
        mSharedPreferences.edit().putBoolean(MODE_PICTURE, isShow).commit();
    }

    public boolean isShowPicture() {
        if (mSharedPreferences == null) {
            return true;
        }
        return mSharedPreferences.getBoolean(MODE_PICTURE, true);
    }
}
