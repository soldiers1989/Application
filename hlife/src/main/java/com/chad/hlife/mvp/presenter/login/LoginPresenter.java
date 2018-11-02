package com.chad.hlife.mvp.presenter.login;

import android.app.Activity;

import com.chad.hlife.entity.mob.UserLoginInfo;
import com.chad.hlife.mvp.base.BasePresenter;
import com.chad.hlife.mvp.model.LoginModel;
import com.chad.hlife.mvp.view.ILoginView;
import com.sina.weibo.sdk.auth.WbConnectErrorMessage;
import com.sina.weibo.sdk.auth.sso.SsoHandler;

import io.reactivex.ObservableTransformer;

public class LoginPresenter extends BasePresenter<ILoginView> implements ILoginPresenter {

    public void login(ObservableTransformer transformer, String key, String userName, String password) {
        LoginModel.getInstance().login(transformer, key, userName, password, this);
    }

    public void weiBoLogin(Activity activity) {
        LoginModel.getInstance().weiBoAuth(activity, this);
    }

    public SsoHandler getWeiBoSsoHandler() {
        return LoginModel.getInstance().getWeiBoSsoHandler();
    }

    @Override
    public void onError(Object object) {
        getView().onError(object);
    }

    @Override
    public void onMobLoginSuccess() {
        getView().onMobLoginSuccess();
    }

    @Override
    public void onMobLoginFail(UserLoginInfo userLoginInfo) {
        getView().onMobLoginFail(userLoginInfo);
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
