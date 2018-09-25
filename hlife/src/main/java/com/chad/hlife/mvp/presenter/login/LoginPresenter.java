package com.chad.hlife.mvp.presenter.login;

import android.app.Activity;

import com.chad.hlife.app.AppConstant;
import com.chad.hlife.mvp.base.BasePresenter;
import com.chad.hlife.mvp.model.LoginModel;
import com.chad.hlife.mvp.view.ILoginView;
import com.sina.weibo.sdk.auth.WbConnectErrorMessage;
import com.sina.weibo.sdk.auth.sso.SsoHandler;

public class LoginPresenter extends BasePresenter<ILoginView> implements ILoginPresenter {

    public void login(int model, Activity activity) {
        switch (model) {
            case AppConstant.MODEL_LOGIN_WEIBO:
                LoginModel.getInstance().weiBoAuth(activity, this);
                break;
            case AppConstant.MODEL_LOGIN_WECHAT:
                LoginModel.getInstance().weChatAuth(this);
                break;
            default:
                break;
        }
    }

    public void logout(int model) {
        switch (model) {
            case AppConstant.MODEL_LOGIN_WEIBO:
                LoginModel.getInstance().cancelWeiBoAuth(this);
                break;
            case AppConstant.MODEL_LOGIN_WECHAT:
                break;
            default:
                break;
        }
    }

    public SsoHandler getWeiBoSsoHandler() {
        return LoginModel.getInstance().getWeiBoSsoHandler();
    }

    public String getWeiBoAccessToken() {
        return LoginModel.getInstance().getWeiBoAccessToken();
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

    @Override
    public void onWeiBoLogout() {
        getView().onWeiBoLogout();
    }
}
