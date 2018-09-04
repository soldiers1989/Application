package com.chad.zhihu.hepler;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.chad.zhihu.ZhiHuApplication;
import com.chad.zhihu.util.LogUtil;

public class NetworkHelper {

    private static final String TAG = NetworkHelper.class.getSimpleName();

    public static boolean isNetworkConnected() {
        ConnectivityManager connectivityManager = (ConnectivityManager) ZhiHuApplication
                .getZhiHuApplication().getSystemService(Context.CONNECTIVITY_SERVICE);
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
