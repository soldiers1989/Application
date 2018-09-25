package com.chad.hlife.mvp.model;

import android.app.Activity;

import com.chad.hlife.HLifeApplication;
import com.chad.hlife.helper.WeiBoAuthHelper;
import com.chad.hlife.mvp.presenter.login.ILoginPresenter;
import com.sina.weibo.sdk.auth.Oauth2AccessToken;
import com.sina.weibo.sdk.auth.WbAuthListener;
import com.sina.weibo.sdk.auth.WbConnectErrorMessage;
import com.sina.weibo.sdk.auth.sso.SsoHandler;

public class LoginModel {

    private WeiBoAuthHelper mWeiBoAuthHelper;

    private static volatile LoginModel mLoginModel = null;

    public static LoginModel getInstance() {
        synchronized (LoginModel.class) {
            if (mLoginModel == null) {
                mLoginModel = new LoginModel();
            }
        }
        return mLoginModel;
    }

    private LoginModel() {
        mWeiBoAuthHelper = WeiBoAuthHelper.getInstance(HLifeApplication.getHLifeApplication());
    }

    public void weiBoAuth(Activity activity, ILoginPresenter loginPresenter) {
        if (mWeiBoAuthHelper == null) {
            return;
        }
        if (mWeiBoAuthHelper.isSessionValid()) {
            loginPresenter.onWeiBoLoginSuccess();
        } else {
            mWeiBoAuthHelper.authorize(activity, new WbAuthListener() {
                @Override
                public void onSuccess(Oauth2AccessToken oauth2AccessToken) {
                    mWeiBoAuthHelper.writeAccessToken(oauth2AccessToken);
                    loginPresenter.onWeiBoLoginSuccess();
                }

                @Override
                public void cancel() {
                    loginPresenter.onWeiBoLoginCancel();
                }

                @Override
                public void onFailure(WbConnectErrorMessage wbConnectErrorMessage) {
                    loginPresenter.onWeiBoLoginFailure(wbConnectErrorMessage);
                }
            });
        }
    }

    public void cancelWeiBoAuth(ILoginPresenter loginPresenter) {
        if (mWeiBoAuthHelper == null) {
            return;
        }
        mWeiBoAuthHelper.clearAccessToken();
        loginPresenter.onWeiBoLogout();
    }

    public SsoHandler getWeiBoSsoHandler() {
        if (mWeiBoAuthHelper == null) {
            return null;
        }
        return mWeiBoAuthHelper.getSsoHandler();
    }

    public String getWeiBoAccessToken() {
        if (mWeiBoAuthHelper == null) {
            return null;
        }
        return mWeiBoAuthHelper.getOauth2AccessToken().getToken();
    }

    public void weChatAuth(ILoginPresenter loginPresenter) {

    }
}
