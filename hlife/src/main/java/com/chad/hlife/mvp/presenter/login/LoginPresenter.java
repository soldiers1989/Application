package com.chad.hlife.mvp.presenter.login;

import android.app.Activity;

import com.chad.hlife.mvp.base.BasePresenter;
import com.chad.hlife.mvp.model.LoginModel;
import com.chad.hlife.mvp.view.ILoginView;
import com.sina.weibo.sdk.auth.WbConnectErrorMessage;
import com.sina.weibo.sdk.auth.sso.SsoHandler;

public class LoginPresenter extends BasePresenter<ILoginView> implements ILoginPresenter {

    public void weiBoLogin(Activity activity) {
        LoginModel.getInstance().weiBoAuth(activity, this);
    }

    public SsoHandler getWeiBoSsoHandler() {
        return LoginModel.getInstance().getWeiBoSsoHandler();
    }

    public boolean isWeiBoSessionValid() {
        return LoginModel.getInstance().isWeiBoSessionValid();
    }

    @Override
    public void onError(Object object) {
        getView().onError(object);
    }

    @Override
    public void onWeiBoLoginSuccess() {
        getView().onWeiBoLoginSuccess();
    }

    @Override
    public void onWeiBoLoginCancel() {
        getView().onWeiBoLoginCancel();
    }

    @Override
    public void onWeiBoLoginFailure(WbConnectErrorMessage wbConnectErrorMessage) {
        getView().onWeiBoLoginFailure(wbConnectErrorMessage);
    }
}
