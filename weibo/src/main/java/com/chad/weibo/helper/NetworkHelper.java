package com.chad.weibo.helper;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.chad.weibo.WeiBoApplication;
import com.chad.weibo.util.LogUtil;

public class NetworkHelper {

    private static final String TAG = NetworkHelper.class.getSimpleName();

    public static boolean isNetworkConnected() {
        ConnectivityManager connectivityManager = (ConnectivityManager) WeiBoApplication
                .getWeiBoApplication().getSystemService(Context.CONNECTIVITY_SERVICE);
        LogUtil.d(TAG, "isNetworkConnected : connectivityManager = "
                + (connectivityManager == null ? null : "Not Null"));
        if (connectivityManager != null) {
            NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
            LogUtil.d(TAG, "isNetworkConnected : networkInfo = "
                    + (networkInfo == null ? null : "Not Null"));
            if (networkInfo != null) {
                return networkInfo.isConnected();
            }
        }
        return false;
    }

}
