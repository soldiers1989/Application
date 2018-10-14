package com.chad.hlife.app;

import android.content.Context;
import android.content.SharedPreferences;

import com.chad.hlife.HLifeApplication;

public class AppSettings {

    public static final String NAME_PREFERENCES = "Settings";
    public static final String KEY_LOGIN_MODEL = "login_model";

    private SharedPreferences mSharedPreferences;

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
        mSharedPreferences = HLifeApplication.getHLifeApplication()
                .getSharedPreferences(NAME_PREFERENCES, Context.MODE_PRIVATE);
    }

    public void setLoginModel(int model) {
        if (mSharedPreferences == null) {
            return;
        }
        mSharedPreferences.edit().putInt(KEY_LOGIN_MODEL, model).commit();
    }

    public int getLoginModel() {
        if (mSharedPreferences == null) {
            return -1;
        }
        return mSharedPreferences.getInt(KEY_LOGIN_MODEL, -1);
    }
}
