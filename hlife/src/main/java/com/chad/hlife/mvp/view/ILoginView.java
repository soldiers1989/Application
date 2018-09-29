package com.chad.hlife.mvp.view;

import com.chad.hlife.mvp.base.IBaseView;
import com.sina.weibo.sdk.auth.WbConnectErrorMessage;

public interface ILoginView extends IBaseView {

    void onWeiBoLoginSuccess();

    void onWeiBoLoginCancel();

    void onWeiBoLoginFailure(WbConnectErrorMessage wbConnectErrorMessage);
}
