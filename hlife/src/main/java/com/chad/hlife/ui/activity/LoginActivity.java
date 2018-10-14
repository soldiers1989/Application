package com.chad.hlife.ui.activity;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.view.View;

import com.chad.hlife.R;
import com.chad.hlife.app.AppConstant;
import com.chad.hlife.app.AppSettings;
import com.chad.hlife.helper.ActivityHelper;
import com.chad.hlife.mvp.presenter.login.LoginPresenter;
import com.chad.hlife.mvp.view.ILoginView;
import com.chad.hlife.ui.base.BaseMvpAppCompatActivity;
import com.chad.hlife.util.LogUtil;
import com.chad.hlife.util.StatusBarUtil;
import com.sina.weibo.sdk.auth.WbConnectErrorMessage;
import com.sina.weibo.sdk.auth.sso.SsoHandler;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;

public class LoginActivity extends BaseMvpAppCompatActivity<ILoginView, LoginPresenter>
        implements ILoginView {

    private static final String TAG = LoginActivity.class.getSimpleName();

    @BindView(R.id.layout_loading)
    ConstraintLayout mLoading;

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
        StatusBarUtil.setFullScreenStatusBar(this);
        mLoading.setVisibility(View.GONE);
    }

    @Override
    protected void onInitData() {
        LogUtil.d(TAG, "onInitData");
        switch (AppSettings.getInstance().getLoginModel()) {
            case AppConstant.MODEL_LOGIN_WEIBO:
                if (presenter.isWeiBoSessionValid()) {
                    startMainActivity(false);
                } else {
                    presenter.login(AppConstant.MODEL_LOGIN_WEIBO, this);
                }
                break;
            case AppConstant.MODEL_LOGIN_WECHAT:
                // TODO: 2018/10/14
                break;
            default:
                break;
        }
    }

    private void startMainActivity(boolean isDelay) {
        LogUtil.d(TAG, "startMainActivity");
        if (isDelay) {
            mLoading.setVisibility(View.VISIBLE);
            Observable.timer(2, TimeUnit.SECONDS)
                    .compose(bindToLifecycle())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(aLong -> {
                        ActivityHelper.startMainActivity(this);
                        finish();
                    });
        } else {
            ActivityHelper.startMainActivity(this);
            finish();
        }
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
        LogUtil.d(TAG, "onWeiBoLoginSuccess");
        AppSettings.getInstance().setLoginModel(AppConstant.MODEL_LOGIN_WEIBO);
        startMainActivity(true);
    }

    @Override
    public void onWeiBoLoginCancel() {
        LogUtil.d(TAG, "onWeiBoLoginCancel");
        AppSettings.getInstance().setLoginModel(-1);
        finish();
    }

    @Override
    public void onWeiBoLoginFailure(WbConnectErrorMessage wbConnectErrorMessage) {
        LogUtil.d(TAG, "onWeiBoLoginFailure : errorMessage = " + wbConnectErrorMessage.getErrorMessage());
        AppSettings.getInstance().setLoginModel(-1);
    }

    @Override
    public void onError(Object object) {
        LogUtil.d(TAG, "onError");
        AppSettings.getInstance().setLoginModel(-1);
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
