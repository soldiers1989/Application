package com.chad.hlife.ui.activity;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatEditText;
import android.text.TextUtils;
import android.widget.Toast;

import com.chad.hlife.R;
import com.chad.hlife.app.AppConstant;
import com.chad.hlife.app.AppSettings;
import com.chad.hlife.helper.ActivityHelper;
import com.chad.hlife.mvp.presenter.login.LoginPresenter;
import com.chad.hlife.mvp.view.ILoginView;
import com.chad.hlife.ui.base.BaseMvpAppCompatActivity;
import com.chad.hlife.ui.view.dialog.ProgressDialog;
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

    @BindView(R.id.edit_user_name)
    AppCompatEditText mEditUserName;
    @BindView(R.id.edit_password)
    AppCompatEditText mEditPassword;
    @BindView(R.id.dialog_progress)
    ProgressDialog mProgressDialog;

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
        initColor();
    }

    private void initColor() {
        LogUtil.d(TAG, "initColor");
        StatusBarUtil.setFullScreenStatusBar(this);
    }

    @Override
    protected void onInitData() {
        LogUtil.d(TAG, "onInitData");
        initLoginStatus();
    }

    private void initLoginStatus() {
        LogUtil.d(TAG, "initLoginStatus");
        switch (AppSettings.getInstance().getLoginModel()) {
            case AppConstant.LOGIN_MODEL_SELF:
                startMainActivity();
                break;
            case AppConstant.LOGIN_MODEL_WEIBO:
                if (presenter.isWeiBoSessionValid()) {
                    startMainActivity();
                } else {
                    presenter.weiBoLogin(this);
                }
                break;
            default:
                break;
        }
    }

    @OnClick(R.id.btn_login)
    public void onLoginClick() {
        LogUtil.d(TAG, "onLoginClick");
        String userName = mEditUserName.getText().toString();
        String password = mEditPassword.getText().toString();
        if (TextUtils.isEmpty(userName) || TextUtils.isEmpty(password)) {
            Toast.makeText(getApplicationContext(), R.string.user_or_password_cannot_be_empty,
                    Toast.LENGTH_SHORT).show();
        } else {
            // TODO: 2018/10/17  
        }
    }

    @OnClick(R.id.btn_register)
    public void onRegisterClick() {
        LogUtil.d(TAG, "onRegisterClick");
        ActivityHelper.startRegisterActivity(this);
    }

    @OnClick(R.id.btn_forget_password)
    public void onForgetPasswordClick() {
        LogUtil.d(TAG, "onForgetPasswordClick");
    }

    @OnClick(R.id.btn_login_weibo)
    public void onWeiBoLogonClick() {
        LogUtil.d(TAG, "onWeiBoLogonClick");
        presenter.weiBoLogin(this);
    }

    @Override
    public void onWeiBoLoginSuccess() {
        LogUtil.d(TAG, "onWeiBoLoginSuccess");
        AppSettings.getInstance().putLoginModel(AppConstant.LOGIN_MODEL_WEIBO);
        showProgressDialog(true);
        Observable.timer(2, TimeUnit.SECONDS)
                .compose(bindToLifecycle())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(aLong -> {
                    showProgressDialog(false);
                    startMainActivity();
                });
    }

    @Override
    public void onWeiBoLoginCancel() {
        LogUtil.d(TAG, "onWeiBoLoginCancel");
        AppSettings.getInstance().putLoginModel(AppConstant.LOGIN_MODEL_NULL);
        finish();
    }

    @Override
    public void onWeiBoLoginFailure(WbConnectErrorMessage wbConnectErrorMessage) {
        LogUtil.d(TAG, "onWeiBoLoginFailure : errorMessage = " + wbConnectErrorMessage.getErrorMessage());
        AppSettings.getInstance().putLoginModel(AppConstant.LOGIN_MODEL_NULL);
    }

    @Override
    public void onError(Object object) {
        LogUtil.d(TAG, "onError");
        AppSettings.getInstance().putLoginModel(AppConstant.LOGIN_MODEL_NULL);
    }

    private void showProgressDialog(boolean isShow) {
        LogUtil.d(TAG, "showProgressDialog : isShow = " + isShow);
        if (isShow) {
            mProgressDialog.setTitle(getString(R.string.logining));
            mProgressDialog.show();
        } else {
            mProgressDialog.dismiss();
        }
    }

    private void startMainActivity() {
        LogUtil.d(TAG, "startMainActivity");
        ActivityHelper.startMainActivity(this);
        finish();
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
