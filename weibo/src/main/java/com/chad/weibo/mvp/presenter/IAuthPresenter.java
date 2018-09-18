package com.chad.weibo.mvp.presenter;

import com.sina.weibo.sdk.auth.WbConnectErrorMessage;

public interface IAuthPresenter {

    void onAuthSuccess();

    void onAuthCancel();

    void onAuthFailure(WbConnectErrorMessage wbConnectErrorMessage);
}
