package com.chad.hlife.ui.app.activity;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.view.View;

import com.chad.hlife.R;
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
        if (AppSettings.getInstance().getLoginStatus()) {
            if (presenter.isWeiBoSessionValid()) {
                startMainActivity(false);
            } else {
                presenter.weiBoLogin(this);
            }
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

    @OnClick(R.id.btn_login)
    public void onLoginClick() {
        LogUtil.d(TAG, "onLoginClick");
        presenter.weiBoLogin(this);
    }

    @OnClick(R.id.btn_tourists)
    public void onTouristsClick() {
        LogUtil.d(TAG, "onTouristsClick");
        startMainActivity(false);
    }

    @OnClick(R.id.btn_close)
    public void onCloseClick() {
        LogUtil.d(TAG, "onCloseClick");
        finish();
    }

    @Override
    public void onWeiBoLoginSuccess() {
        LogUtil.d(TAG, "onWeiBoLoginSuccess");
        AppSettings.getInstance().setLoginStatus(true);
        startMainActivity(true);
    }

    @Override
    public void onWeiBoLoginCancel() {
        LogUtil.d(TAG, "onWeiBoLoginCancel");
        AppSettings.getInstance().setLoginStatus(false);
        finish();
    }

    @Override
    public void onWeiBoLoginFailure(WbConnectErrorMessage wbConnectErrorMessage) {
        LogUtil.d(TAG, "onWeiBoLoginFailure : errorMessage = " + wbConnectErrorMessage.getErrorMessage());
        AppSettings.getInstance().setLoginStatus(false);
    }

    @Override
    public void onError(Object object) {
        LogUtil.d(TAG, "onError");
        AppSettings.getInstance().setLoginStatus(false);
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
