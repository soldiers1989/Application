package com.chad.weibo.helper;

import android.app.Activity;
import android.content.Context;

import com.chad.weibo.util.LogUtil;
import com.sina.weibo.sdk.auth.AccessTokenKeeper;
import com.sina.weibo.sdk.auth.Oauth2AccessToken;
import com.sina.weibo.sdk.auth.WbAuthListener;
import com.sina.weibo.sdk.auth.sso.SsoHandler;

public class WeiBoAuthHelper {

    private static final String TAG = WeiBoAuthHelper.class.getSimpleName();

    private Context mContext;
    private SsoHandler mSsoHandler;
    private Oauth2AccessToken mOauth2AccessToken;

    private static volatile WeiBoAuthHelper mWeiBoAuthHelper = null;

    public static WeiBoAuthHelper getInstance(Context context) {
        synchronized (WeiBoAuthHelper.class) {
            if (mWeiBoAuthHelper == null) {
                mWeiBoAuthHelper = new WeiBoAuthHelper(context);
            }
        }
        return mWeiBoAuthHelper;
    }

    private WeiBoAuthHelper(Context context) {
        mContext = context;
        mOauth2AccessToken = readAccessToken();
    }

    public boolean isSessionValid() {
        return mOauth2AccessToken == null ? false : mOauth2AccessToken.isSessionValid();
    }

    public void setOauth2AccessToken(Oauth2AccessToken accessToken) {
        mOauth2AccessToken = accessToken;
    }

    public Oauth2AccessToken getOauth2AccessToken() {
        return mOauth2AccessToken == null? null: mOauth2AccessToken;
    }

    public void writeAccessToken(Oauth2AccessToken oauth2AccessToken) {
        AccessTokenKeeper.writeAccessToken(mContext, oauth2AccessToken);
    }

    public Oauth2AccessToken readAccessToken() {
        return AccessTokenKeeper.readAccessToken(mContext);
    }

    public void clearAccessToken() {
        AccessTokenKeeper.clear(mContext);
    }

    public SsoHandler getSsoHandler() {
        return mSsoHandler == null ? null : mSsoHandler;
    }

    /**
     * SSO授权，使用新浪微博客户端进行授权
     *
     * @param activity
     * @param listener
     */
    public void authorizeClientSso(Activity activity, WbAuthListener listener) {
        LogUtil.d(TAG, "authorizeClientSso : activity = " + (activity == null ? null : "Not Null")
                + " , listener = " + (listener == null ? null : "Not Null"));
        if (activity == null || listener == null) {
            return;
        }
        createSsoHandler(activity);
        mSsoHandler.authorizeClientSso(listener);
    }

    /**
     * SSO授权，使用网页进行授权
     *
     * @param activity
     * @param listener
     */
    public void authorizeWeb(Activity activity, WbAuthListener listener) {
        LogUtil.d(TAG, "authorizeWeb : activity = " + (activity == null ? null : "Not Null")
                + " , listener = " + (listener == null ? null : "Not Null"));
        if (activity == null || listener == null) {
            return;
        }
        createSsoHandler(activity);
        mSsoHandler.authorizeWeb(listener);
    }

    /**
     * SSO授权，如果手机安装了新浪微博客户端就使用客户端进行授权，否则使用网页进行授权
     *
     * @param activity
     * @param listener
     */
    public void authorize(Activity activity, WbAuthListener listener) {
        LogUtil.d(TAG, "authorize : activity = " + (activity == null ? null : "Not Null")
                + " , listener = " + (listener == null ? null : "Not Null"));
        if (activity == null || listener == null) {
            return;
        }
        createSsoHandler(activity);
        mSsoHandler.authorize(listener);
    }

    private void createSsoHandler(Activity activity) {
        LogUtil.d(TAG, "createSsoHandler : activity = " + (activity == null ? null : "Not Null")
                + " , mSsoHandler = " + (mSsoHandler == null ? null : "Not Null"));
        if (activity == null || mSsoHandler != null) {
            return;
        }
        mSsoHandler = new SsoHandler(activity);
    }
}
