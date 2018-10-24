package com.chad.hlife.app;

import android.content.Context;
import android.content.SharedPreferences;

import com.chad.hlife.HLifeApplication;

public class AppSettings {

    public static final String NAME_PREFERENCES = "life_settings";
    public static final String KEY_LOGIN_MODEL = "login_model";
    public static final String KEY_USER_NAME = "user_name";
    public static final String KEY_PASSWORD = "password";
    public static final String KEY_TOKEN = "token";
    public static final String KEY_UID = "uid";

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

    public synchronized void putLoginModel(int model) {
        if (mSharedPreferences == null) {
            return;
        }
        mSharedPreferences.edit().putInt(KEY_LOGIN_MODEL, model).commit();
    }

    public int getLoginModel() {
        if (mSharedPreferences == null) {
            return AppConstant.LOGIN_MODEL_NULL;
        }
        return mSharedPreferences.getInt(KEY_LOGIN_MODEL, AppConstant.LOGIN_MODEL_NULL);
    }

    public synchronized void putUserName(String userName) {
        if (mSharedPreferences == null) {
            return;
        }
        mSharedPreferences.edit().putString(KEY_USER_NAME, userName).commit();
    }

    public String getUserName() {
        if (mSharedPreferences == null) {
            return null;
        }
        return mSharedPreferences.getString(KEY_USER_NAME, null);
    }

    public synchronized void putPassword(String password) {
        if (mSharedPreferences == null) {
            return;
        }
        mSharedPreferences.edit().putString(KEY_PASSWORD, password).commit();
    }

    public String getPassword() {
        if (mSharedPreferences == null) {
            return null;
        }
        return mSharedPreferences.getString(KEY_PASSWORD, null);
    }

    public synchronized void putToken(String token) {
        if (mSharedPreferences == null) {
            return;
        }
        mSharedPreferences.edit().putString(KEY_TOKEN, token).commit();
    }

    public String getToken() {
        if (mSharedPreferences == null) {
            return null;
        }
        return mSharedPreferences.getString(KEY_TOKEN, null);
    }

    public synchronized void putUid(String uid) {
        if (mSharedPreferences == null) {
            return;
        }
        mSharedPreferences.edit().putString(KEY_UID, uid).commit();
    }

    public String getUid() {
        if (mSharedPreferences == null) {
            return null;
        }
        return mSharedPreferences.getString(KEY_UID, null);
    }
}
