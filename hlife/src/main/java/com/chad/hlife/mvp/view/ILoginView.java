package com.chad.hlife.mvp.view;

import com.chad.hlife.entity.mob.UserLoginInfo;
import com.chad.hlife.mvp.base.IBaseView;
import com.sina.weibo.sdk.auth.WbConnectErrorMessage;

public interface ILoginView extends IBaseView {

    void onMobLoginSuccess();

    void onMobLoginFail(UserLoginInfo userLoginInfo);

    void onWeiBoLoginSuccess();

    void onWeiBoLoginCancel();

    void onWeiBoLoginFailure(WbConnectErrorMessage wbConnectErrorMessage);
}
