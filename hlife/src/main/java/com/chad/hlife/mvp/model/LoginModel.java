package com.chad.hlife.mvp.model;

import android.app.Activity;

import com.chad.hlife.HLifeApplication;
import com.chad.hlife.entity.mob.UserLoginInfo;
import com.chad.hlife.helper.MobAuthHelper;
import com.chad.hlife.helper.WeiBoAuthHelper;
import com.chad.hlife.mvp.presenter.login.ILoginPresenter;
import com.chad.hlife.retrofit.HLifeRetrofit;
import com.chad.hlife.util.RxSchedulersUtil;
import com.sina.weibo.sdk.auth.Oauth2AccessToken;
import com.sina.weibo.sdk.auth.WbAuthListener;
import com.sina.weibo.sdk.auth.WbConnectErrorMessage;
import com.sina.weibo.sdk.auth.sso.SsoHandler;

import io.reactivex.ObservableTransformer;

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
    }

    public void login(ObservableTransformer transformer, String key, String userName, String password,
                      ILoginPresenter loginPresenter) {
        HLifeRetrofit.login(key, userName, password)
                .compose(transformer)
                .compose(RxSchedulersUtil.workThread())
                .subscribe(o -> {
                            UserLoginInfo userLoginInfo = (UserLoginInfo) o;
                            loginPresenter.onMobLogin(userLoginInfo);
                            MobAuthHelper.getInstance()
                                    .writeAccessToken(userLoginInfo.getMobAccessToken());
                        },
                        throwable -> loginPresenter.onError(throwable));
    }

    public void weiBoAuth(Activity activity, ILoginPresenter loginPresenter) {
        initWeiBoAuthHelper();
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

    public SsoHandler getWeiBoSsoHandler() {
        initWeiBoAuthHelper();
        return mWeiBoAuthHelper.getSsoHandler();
    }

    public boolean isWeiBoSessionValid() {
        initWeiBoAuthHelper();
        return mWeiBoAuthHelper.isSessionValid();
    }

    private void initWeiBoAuthHelper() {
        if (mWeiBoAuthHelper == null) {
            mWeiBoAuthHelper = WeiBoAuthHelper.getInstance(HLifeApplication.getHLifeApplication());
        }
    }
}
