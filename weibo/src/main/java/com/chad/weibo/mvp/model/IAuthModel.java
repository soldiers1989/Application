package com.chad.weibo.mvp.model;

import android.app.Activity;

import com.sina.weibo.sdk.auth.sso.SsoHandler;

public interface IAuthModel {

    boolean isSessionValid();

    SsoHandler getSsoHandler();

    void authorizeClientSso(Activity activity);

    void authorizeWeb(Activity activity);

    void authorize(Activity activity);

    void clearAccessToken();
}
