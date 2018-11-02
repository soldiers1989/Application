package com.chad.hlife.ui.activity;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatEditText;
import android.text.InputFilter;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.widget.Toast;

import com.chad.hlife.R;
import com.chad.hlife.app.AppConstant;
import com.chad.hlife.app.AppSettings;
import com.chad.hlife.app.config.MobConfig;
import com.chad.hlife.entity.mob.UserLoginInfo;
import com.chad.hlife.helper.ActivityHelper;
import com.chad.hlife.helper.MobAuthHelper;
import com.chad.hlife.mvp.presenter.login.LoginPresenter;
import com.chad.hlife.mvp.view.ILoginView;
import com.chad.hlife.ui.base.BaseMvpAppCompatActivity;
import com.chad.hlife.ui.view.ProgressDialog;
import com.chad.hlife.util.InputFilterUtil;
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

    private ProgressDialog mProgressDialog;

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
        initEditText();
    }

    private void initColor() {
        LogUtil.d(TAG, "initColor");
        StatusBarUtil.setFullScreenStatusBar(this);
    }

    private void initEditText() {
        LogUtil.d(TAG, "initEditText");
        mEditUserName.setFilters(new InputFilter[]{new InputFilterUtil.SpaceFilter()});
    }

    @Override
    protected void onInitData() {
        LogUtil.d(TAG, "onInitData");
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
            showProgressDialog(true);
            Observable.timer(2, TimeUnit.SECONDS)
                    .compose(bindToLifecycle())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(aLong ->
                            presenter.login(bindToLifecycle(), MobConfig.APP_KEY, userName, password)
                    );
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
    public void onMobLoginSuccess() {
        LogUtil.d(TAG, "onMobLoginSuccess");
        showProgressDialog(false);
        if (AppSettings.getInstance().getLoginModel() != AppConstant.LOGIN_MODEL_MOB) {
            AppSettings.getInstance().putLoginModel(AppConstant.LOGIN_MODEL_MOB);
            MobAuthHelper.getInstance().writeUserConfig(mEditUserName.getText().toString(),
                    mEditPassword.getText().toString());
        }
        startMainActivity();
    }

    @Override
    public void onMobLoginFail(UserLoginInfo userLoginInfo) {
        LogUtil.d(TAG, "onMobLoginFail : userLoginInfo = " + userLoginInfo);
        if (userLoginInfo == null) {
            return;
        }
        showProgressDialog(false);
        AppSettings.getInstance().putLoginModel(AppConstant.LOGIN_MODEL_NULL);
        MobAuthHelper.getInstance().clearAccessToken();
        Toast.makeText(getApplicationContext(), userLoginInfo.getMsg(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onWeiBoLoginSuccess() {
        LogUtil.d(TAG, "onWeiBoLoginSuccess");
        AppSettings.getInstance().putLoginModel(AppConstant.LOGIN_MODEL_WEIBO);
        startMainActivity();
    }

    @Override
    public void onWeiBoLoginCancel() {
        LogUtil.d(TAG, "onWeiBoLoginCancel");
        AppSettings.getInstance().putLoginModel(AppConstant.LOGIN_MODEL_NULL);
    }

    @Override
    public void onWeiBoLoginFailure(WbConnectErrorMessage wbConnectErrorMessage) {
        LogUtil.d(TAG, "onWeiBoLoginFailure : errorMessage = " + wbConnectErrorMessage.getErrorMessage());
        AppSettings.getInstance().putLoginModel(AppConstant.LOGIN_MODEL_NULL);
    }

    @Override
    public void onError(Object object) {
        LogUtil.d(TAG, "onError");
        showProgressDialog(false);
        AppSettings.getInstance().putLoginModel(AppConstant.LOGIN_MODEL_NULL);
        Toast.makeText(getApplicationContext(), R.string.login_fail, Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            ActivityHelper.startLauncherActivity(this);
            finish();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    private void showProgressDialog(boolean isShow) {
        LogUtil.d(TAG, "showProgressDialog : isShow = " + isShow);
        if (mProgressDialog == null) {
            mProgressDialog = new ProgressDialog(this);
            mProgressDialog.setTitle(R.string.logining);
        }
        if (isShow && !mProgressDialog.isShowing()) {
            mProgressDialog.show();
        } else if (mProgressDialog.isShowing()) {
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
