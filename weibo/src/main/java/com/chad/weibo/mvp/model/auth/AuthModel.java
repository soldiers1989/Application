package com.chad.weibo.mvp.model.auth;

import android.app.Activity;

import com.chad.weibo.helper.WeiBoAuthHelper;
import com.chad.weibo.mvp.presenter.auth.AuthPresenter;
import com.chad.weibo.util.LogUtil;
import com.sina.weibo.sdk.auth.Oauth2AccessToken;
import com.sina.weibo.sdk.auth.WbAuthListener;
import com.sina.weibo.sdk.auth.WbConnectErrorMessage;
import com.sina.weibo.sdk.auth.sso.SsoHandler;

public class AuthModel implements IAuthModel, WbAuthListener {

    private static final String TAG = AuthModel.class.getSimpleName();

    private AuthPresenter mOauthPresenter;
    private WeiBoAuthHelper mWeiBoAuthHelper;

    private static volatile AuthModel mOauthModel = null;

    public static AuthModel getInstance(AuthPresenter oauthPresenter) {
        synchronized (AuthModel.class) {
            if (mOauthModel == null) {
                mOauthModel = new AuthModel(oauthPresenter);
            }
        }
        return mOauthModel;
    }

    private AuthModel(AuthPresenter oauthPresenter) {
        mOauthPresenter = oauthPresenter;
        mWeiBoAuthHelper = WeiBoAuthHelper.getInstance();
    }

    @Override
    public boolean isSessionValid() {
        LogUtil.d(TAG, "isSessionValid");
        return mWeiBoAuthHelper.isSessionValid();
    }

    @Override
    public SsoHandler getSsoHandler() {
        LogUtil.d(TAG, "getSsoHandler");
        return mWeiBoAuthHelper.getSsoHandler();
    }

    @Override
    public void authorizeClientSso(Activity activity) {
        LogUtil.d(TAG, "authorizeClientSso");
        mWeiBoAuthHelper.authorizeClientSso(activity, this);
    }

    @Override
    public void authorizeWeb(Activity activity) {
        LogUtil.d(TAG, "authorizeWeb");
        mWeiBoAuthHelper.authorizeWeb(activity, this);
    }

    @Override
    public void authorize(Activity activity) {
        LogUtil.d(TAG, "authorize");
        mWeiBoAuthHelper.authorize(activity, this);
    }

    @Override
    public void clearAccessToken() {
        LogUtil.d(TAG, "clearAccessToken");
        mWeiBoAuthHelper.clearAccessToken();
    }

    @Override
    public void onSuccess(Oauth2AccessToken oauth2AccessToken) {
        LogUtil.d(TAG, "onSuccess : oauth2AccessToken = "
                + (oauth2AccessToken == null ? null : "Not Null"));
        mWeiBoAuthHelper.setOauth2AccessToken(oauth2AccessToken);
        LogUtil.d(TAG, "onSuccess : isSessionValid = " + mWeiBoAuthHelper.isSessionValid());
        if (mWeiBoAuthHelper.isSessionValid()) {
            mWeiBoAuthHelper.writeAccessToken(oauth2AccessToken);
            if (mOauthPresenter != null) {
                mOauthPresenter.onAuthSuccess();
            }
        }
    }

    @Override
    public void cancel() {
        LogUtil.d(TAG, "cancel");
        if (mOauthPresenter != null) {
            mOauthPresenter.onAuthCancel();
        }
    }

    @Override
    public void onFailure(WbConnectErrorMessage wbConnectErrorMessage) {
        LogUtil.d(TAG, "onFailure : code = " + wbConnectErrorMessage.getErrorCode()
                + " , message = " + wbConnectErrorMessage.getErrorMessage());
        if (mOauthPresenter != null) {
            mOauthPresenter.onAuthFailure(wbConnectErrorMessage);
        }
    }
}
