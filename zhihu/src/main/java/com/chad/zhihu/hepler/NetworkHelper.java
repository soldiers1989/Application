package com.chad.zhihu.hepler;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.chad.zhihu.ZhiHuApplication;
import com.chad.zhihu.dagger.app.AppComponent;
import com.chad.zhihu.util.LogUtil;

public class NetworkHelper {

    private static final String TAG = NetworkHelper.class.getSimpleName();

    private static Context context = null;

    static {
        initContext();
    }

    private static void initContext() {
        LogUtil.d(TAG, "initContext : context = " + context);
        if (context == null) {
            AppComponent appComponent = ZhiHuApplication.getAppComponent();
            LogUtil.d(TAG, "initContext : appComponent = " + appComponent);
            if (appComponent != null) {
                context = appComponent.context();
            }
        }
    }

    public static boolean isNetworkConnected() {
        LogUtil.d(TAG, "isNetworkConnected : context = " + context);
        if (context == null) {
            return false;
        }
        ConnectivityManager connectivityManager = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        LogUtil.d(TAG, "isNetworkConnected : connectivityManager = " + connectivityManager);
        if (connectivityManager != null) {
            NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
            LogUtil.d(TAG, "isNetworkConnected : networkInfo = " + networkInfo);
            if (networkInfo != null) {
                return  networkInfo.isConnected();
            }
        }
        return false;
    }

}
