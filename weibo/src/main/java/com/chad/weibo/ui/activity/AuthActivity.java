package com.chad.weibo.ui.activity;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.Toolbar;

import com.chad.weibo.R;
import com.chad.weibo.helper.ActivityHelper;
import com.chad.weibo.mvp.presenter.auth.AuthPresenter;
import com.chad.weibo.mvp.view.IAuthView;
import com.chad.weibo.ui.base.BaseMvpAppCompatActivity;
import com.chad.weibo.util.LogUtil;
import com.chad.weibo.util.RxSchedulersUtil;
import com.sina.weibo.sdk.auth.WbConnectErrorMessage;
import com.sina.weibo.sdk.auth.sso.SsoHandler;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import io.reactivex.Observable;

public class AuthActivity extends BaseMvpAppCompatActivity<IAuthView, AuthPresenter>
        implements IAuthView {

    private static final String TAG = AuthActivity.class.getSimpleName();

    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.message)
    AppCompatTextView mMessage;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_auth;
    }

    @Override
    protected void initViews() {
        LogUtil.d(TAG, "initViews");
        initToolbar();
    }

    private void initToolbar() {
        LogUtil.d(TAG, "initToolbar");
        mToolbar.setTitle("");
        setSupportActionBar(mToolbar);
    }

    @Override
    protected void initData() {
        LogUtil.d(TAG, "initData");
        Observable.timer(2, TimeUnit.SECONDS)
                .compose(bindToLifecycle())
                .compose(RxSchedulersUtil.thread())
                .subscribe(aLong -> auth());
    }

    private void auth() {
        LogUtil.d(TAG, "auth : isSessionValid = " + presenter.isSessionValid());
        if (presenter.isSessionValid()) {
            ActivityHelper.startMainActivity(this);
            finish();
        } else {
            presenter.authorize(this);
        }
    }

    @Override
    protected AuthPresenter getPresenter() {
        return new AuthPresenter();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        SsoHandler ssoHandler = presenter.getSsoHandler();
        LogUtil.d(TAG, "onActivityResult : ssoHandler = " + (ssoHandler == null ? null : "Not Null"));
        if (ssoHandler != null) {
            ssoHandler.authorizeCallBack(requestCode, resultCode, data);
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onAuthSuccess() {
        LogUtil.d(TAG, "onAuthSuccess");
        ActivityHelper.startMainActivity(this);
        finish();
    }

    @Override
    public void onAuthCancel() {
        LogUtil.d(TAG, "onAuthCancel");
        finish();
    }

    @Override
    public void onAuthFailure(WbConnectErrorMessage wbConnectErrorMessage) {
        LogUtil.d(TAG, "onAuthFailure : code = " + wbConnectErrorMessage.getErrorCode()
                + " , message = " + wbConnectErrorMessage.getErrorMessage());
        mMessage.setText(getString(R.string.auth_failure) + wbConnectErrorMessage.getErrorMessage());
    }
}
