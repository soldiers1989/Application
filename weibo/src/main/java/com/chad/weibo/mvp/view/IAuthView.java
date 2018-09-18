package com.chad.weibo.mvp.view;

import com.sina.weibo.sdk.auth.WbConnectErrorMessage;

public interface IAuthView {

    void onAuthSuccess();

    void onAuthCancel();

    void onAuthFailure(WbConnectErrorMessage wbConnectErrorMessage);
}
