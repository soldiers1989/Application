package com.chad.hlife.helper;

import android.app.Activity;
import android.content.Context;

import com.sina.weibo.sdk.auth.AccessTokenKeeper;
import com.sina.weibo.sdk.auth.Oauth2AccessToken;
import com.sina.weibo.sdk.auth.WbAuthListener;
import com.sina.weibo.sdk.auth.sso.SsoHandler;

public class WeiBoAuthHelper {

    private Context mContext;
    private SsoHandler mSsoHandler;

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
    }

    public boolean isSessionValid() {
        return readAccessToken().isSessionValid();
    }

    public Oauth2AccessToken getOauth2AccessToken() {
        return readAccessToken();
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
     * SSO授权，如果手机安装了新浪微博客户端就使用客户端进行授权，否则使用网页进行授权
     *
     * @param activity
     * @param listener
     */
    public void authorize(Activity activity, WbAuthListener listener) {
        if (activity == null || listener == null) {
            return;
        }
        initSsoHandler(activity);
        mSsoHandler.authorize(listener);
    }

    private void initSsoHandler(Activity activity) {
        if (activity == null || mSsoHandler != null) {
            return;
        }
        mSsoHandler = new SsoHandler(activity);
    }
}
