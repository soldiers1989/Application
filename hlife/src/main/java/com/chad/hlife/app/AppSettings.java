package com.chad.hlife.app;

import android.content.Context;
import android.content.SharedPreferences;

import com.chad.hlife.HLifeApplication;

public class AppSettings {

    public static final String NAME_PREFERENCES = "Settings";
    public static final String KEY_LOGIN_STATUS = "login_status";

    private SharedPreferences mSharedPreferences;

    private static volatile AppSettings mAppSettings = null;

    public static AppSettings getInstance() {
        synchronized (AppSettings.class) {
            if (mAppSettings == null) {
                mAppSettings = new AppSettings();
            }
        }
        return mAppSettings;
    }

    private AppSettings() {
        mSharedPreferences = HLifeApplication.getHLifeApplication()
                .getSharedPreferences(NAME_PREFERENCES, Context.MODE_PRIVATE);
    }

    public void setLoginStatus(boolean isLogin) {
        if (mSharedPreferences == null) {
            return;
        }
        mSharedPreferences.edit().putBoolean(KEY_LOGIN_STATUS, isLogin).commit();
    }

    public boolean getLoginStatus() {
        if (mSharedPreferences == null) {
            return false;
        }
        return mSharedPreferences.getBoolean(KEY_LOGIN_STATUS, false);
    }
}
