package com.chad.zhihu.app;

import android.content.Context;
import android.content.SharedPreferences;

import com.chad.zhihu.ZhiHuApplication;

public class AppSettings {

    public static final String NAME_PREFERENCES = "Settings";
    public static final String KEY_GRAPH_BROWSING = "graphBrowsing";
    public static final String KEY_BUILT_IN_BROWSER = "builtInBrowser";

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
        mSharedPreferences = ZhiHuApplication.getZhiHuApplication()
                .getSharedPreferences(NAME_PREFERENCES, Context.MODE_PRIVATE);
    }

    public void setGraphBrowsing(boolean isShow) {
        if (mSharedPreferences == null) {
            return;
        }
        mSharedPreferences.edit().putBoolean(KEY_GRAPH_BROWSING, isShow).commit();
    }

    public boolean isGraphBrowsing() {
        if (mSharedPreferences == null) {
            return true;
        }
        return mSharedPreferences.getBoolean(KEY_GRAPH_BROWSING, true);
    }

    public void setBuiltInBrowser(boolean isInBrowser) {
        if (mSharedPreferences == null) {
            return;
        }
        mSharedPreferences.edit().putBoolean(KEY_BUILT_IN_BROWSER, isInBrowser).commit();
    }

    public boolean isBuiltInBrowser() {
        if (mSharedPreferences == null) {
            return true;
        }
        return mSharedPreferences.getBoolean(KEY_BUILT_IN_BROWSER, true);
    }
}
