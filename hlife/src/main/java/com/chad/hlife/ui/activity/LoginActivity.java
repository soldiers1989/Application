package com.chad.hlife.ui.activity;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;

import com.chad.hlife.R;
import com.chad.hlife.app.AppConstant;
import com.chad.hlife.mvp.presenter.login.LoginPresenter;
import com.chad.hlife.mvp.view.ILoginView;
import com.chad.hlife.ui.base.BaseMvpAppCompatActivity;
import com.chad.hlife.util.LogUtil;
import com.sina.weibo.sdk.auth.WbConnectErrorMessage;
import com.sina.weibo.sdk.auth.sso.SsoHandler;

import butterknife.BindView;
import butterknife.OnClick;

public class LoginActivity extends BaseMvpAppCompatActivity<ILoginView, LoginPresenter>
        implements ILoginView {

    private static final String TAG = LoginActivity.class.getSimpleName();

    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    @Override
    protected LoginPresenter onGetPresenter() {
        return new LoginPresenter();
    }

    @Override
    protected int onGetLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    protected void onInitView() {
        LogUtil.d(TAG, "onInitView");
        initToolbar();
    }

    private void initToolbar() {
        LogUtil.d(TAG, "initToolbar");
        mToolbar.setNavigationIcon(R.drawable.ic_back_light);
        mToolbar.setNavigationOnClickListener(view -> onBackPressed());
    }

    @Override
    protected void onInitData() {

    }

    @OnClick(R.id.btn_login_weibo)
    public void weiBoLogin() {
        LogUtil.d(TAG, "weiBoLogin");
        presenter.login(AppConstant.MODEL_LOGIN_WEIBO, this);
    }

    @OnClick(R.id.btn_login_wechat)
    public void weChatLogin() {
        LogUtil.d(TAG, "weChatLogin");
        presenter.login(AppConstant.MODEL_LOGIN_WECHAT, this);
    }

    @Override
    public void onWeiBoLoginSuccess() {

    }

    @Override
    public void onWeiBoLoginCancel() {

    }

    @Override
    public void onWeiBoLoginFailure(WbConnectErrorMessage wbConnectErrorMessage) {

    }

    @Override
    public void onWeiBoLogout() {

    }

    @Override
    public void onError(Object object) {

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        SsoHandler ssoHandler = presenter.getWeiBoSsoHandler();
        LogUtil.d(TAG, "onActivityResult : ssoHandler = "
                + (ssoHandler == null ? "Null" : "Not Null"));
        if (ssoHandler != null) {
            ssoHandler.authorizeCallBack(requestCode, resultCode, data);
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
