package com.chad.weibo.mvp.presenter.auth;

import android.app.Activity;

import com.chad.weibo.mvp.base.BasePresenter;
import com.chad.weibo.mvp.model.auth.AuthModel;
import com.chad.weibo.mvp.view.IAuthView;
import com.chad.weibo.util.LogUtil;
import com.sina.weibo.sdk.auth.WbConnectErrorMessage;
import com.sina.weibo.sdk.auth.sso.SsoHandler;

public class AuthPresenter extends BasePresenter<IAuthView> implements IAuthPresenter {

    private static final String TAG = AuthPresenter.class.getSimpleName();

    public boolean isSessionValid() {
        LogUtil.d(TAG, "isSessionValid");
        return AuthModel.getInstance(this).isSessionValid();
    }

    public SsoHandler getSsoHandler() {
        LogUtil.d(TAG, "getSsoHandler");
        return AuthModel.getInstance(this).getSsoHandler();
    }

    public void authorizeClientSso(Activity activity) {
        LogUtil.d(TAG, "authorizeClientSso");
        AuthModel.getInstance(this).authorizeClientSso(activity);
    }

    public void authorizeWeb(Activity activity) {
        LogUtil.d(TAG, "authorizeWeb");
        AuthModel.getInstance(this).authorizeWeb(activity);
    }

    public void authorize(Activity activity) {
        LogUtil.d(TAG, "authorize");
        AuthModel.getInstance(this).authorize(activity);
    }

    public void clearAccessToken() {
        LogUtil.d(TAG, "clearAccessToken");
        AuthModel.getInstance(this).clearAccessToken();
    }

    @Override
    public void onAuthSuccess() {
        getView().onAuthSuccess();
    }

    @Override
    public void onAuthCancel() {
        getView().onAuthCancel();
    }

    @Override
    public void onAuthFailure(WbConnectErrorMessage wbConnectErrorMessage) {
        getView().onAuthFailure(wbConnectErrorMessage);
    }
}
