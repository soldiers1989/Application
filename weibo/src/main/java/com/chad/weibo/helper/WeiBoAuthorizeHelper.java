package com.chad.weibo.helper;

import android.app.Activity;
import android.content.Context;

import com.chad.weibo.util.LogUtil;
import com.sina.weibo.sdk.auth.WbAuthListener;
import com.sina.weibo.sdk.auth.sso.SsoHandler;

public class WeiBoAuthorizeHelper {

    private static final String TAG = WeiBoAuthorizeHelper.class.getSimpleName();

    private Context mContext;
    private SsoHandler mSsoHandler = null;

    private static volatile WeiBoAuthorizeHelper mWeiBoAuthorizeHelper = null;

    public static WeiBoAuthorizeHelper getInstance(Context context) {
        synchronized (WeiBoAuthorizeHelper.class) {
            if (mWeiBoAuthorizeHelper == null) {
                mWeiBoAuthorizeHelper = new WeiBoAuthorizeHelper(context);
            }
        }
        return mWeiBoAuthorizeHelper;
    }

    private WeiBoAuthorizeHelper(Context context) {
        mContext = context;
    }

    public void createSsoHandler(Activity activity) {
        LogUtil.d(TAG, "createSsoHandler : activity = " + activity
                + " , mSsoHandler = " + mSsoHandler);
        if (activity == null || mSsoHandler != null) {
            return;
        }
        mSsoHandler = new SsoHandler(activity);
    }

    public void authorize(WbAuthListener listener) {
        LogUtil.d(TAG, "createSsoHandler : listener = " + listener
                + " , mSsoHandler = " + mSsoHandler);
        if (listener == null || mSsoHandler == null) {
            return;
        }
        mSsoHandler.authorize(listener);
    }
}