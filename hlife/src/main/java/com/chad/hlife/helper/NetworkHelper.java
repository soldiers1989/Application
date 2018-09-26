package com.chad.hlife.helper;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.chad.hlife.HLifeApplication;
import com.chad.hlife.util.LogUtil;

public class NetworkHelper {

    private static final String TAG = NetworkHelper.class.getSimpleName();

    public static boolean isNetworkConnected() {
        ConnectivityManager connectivityManager = (ConnectivityManager) HLifeApplication
                .getHLifeApplication().getSystemService(Context.CONNECTIVITY_SERVICE);
        LogUtil.d(TAG, "isNetworkConnected : connectivityManager = "
                + (connectivityManager == null ? "Null" : "Not Null"));
        if (connectivityManager != null) {
            NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
            LogUtil.d(TAG, "isNetworkConnected : networkInfo = "
                    + (networkInfo == null ? "Null" : "Not Null"));
            if (networkInfo != null) {
                return networkInfo.isConnected();
            }
        }
        return false;
    }

}
