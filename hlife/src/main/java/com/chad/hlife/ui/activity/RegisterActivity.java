package com.chad.hlife.ui.activity;

import android.graphics.Color;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.widget.Toast;

import com.chad.hlife.R;
import com.chad.hlife.app.AppConstant;
import com.chad.hlife.app.config.MobConfig;
import com.chad.hlife.entity.mob.UserRegisterInfo;
import com.chad.hlife.mvp.presenter.register.RegisterPresenter;
import com.chad.hlife.mvp.view.IRegisterView;
import com.chad.hlife.ui.base.BaseMvpAppCompatActivity;
import com.chad.hlife.util.LogUtil;
import com.chad.hlife.util.StatusBarUtil;

import butterknife.BindView;
import butterknife.OnClick;

public class RegisterActivity extends BaseMvpAppCompatActivity<IRegisterView, RegisterPresenter>
        implements IRegisterView {

    private static final String TAG = RegisterActivity.class.getSimpleName();

    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.edit_user_name)
    AppCompatEditText mEditUserName;
    @BindView(R.id.edit_password)
    AppCompatEditText mEditPassword;

    @Override
    protected RegisterPresenter onGetPresenter() {
        return new RegisterPresenter();
    }

    @Override
    protected int onGetLayoutId() {
        return R.layout.activity_register;
    }

    @Override
    protected void onInitView() {
        LogUtil.d(TAG, "initViews");
        initColor();
        initToolbar();
    }

    private void initColor() {
        LogUtil.d(TAG, "initColor");
        StatusBarUtil.setStatusBarColor(this, getResources().getColor(AppConstant.COLOR_STATUS_BAR_BLUE));
    }

    private void initToolbar() {
        LogUtil.d(TAG, "initToolbar");
        mToolbar.setTitle(R.string.register);
        mToolbar.setTitleTextColor(Color.WHITE);
        mToolbar.setNavigationIcon(R.drawable.ic_back_light);
        mToolbar.setNavigationOnClickListener(view -> finish());
    }

    @Override
    protected void onInitData() {

    }

    @OnClick(R.id.btn_register)
    public void onRegisterClick() {
        LogUtil.d(TAG, "onRegisterClick");
        String userName = mEditUserName.getText().toString();
        String password = mEditPassword.getText().toString();
        if (TextUtils.isEmpty(userName) || TextUtils.isEmpty(password)) {
            Toast.makeText(getApplicationContext(), R.string.user_or_password_cannot_be_empty,
                    Toast.LENGTH_SHORT).show();
        } else {
            presenter.register(bindToLifecycle(), MobConfig.APP_KEY, userName, password);
        }
    }

    @Override
    public void onUserRegisterInfo(UserRegisterInfo userRegisterInfo) {
        LogUtil.d(TAG, "onUserRegisterInfo : userRegisterInfo = " + userRegisterInfo);
        if (userRegisterInfo == null) {
            return;
        }
    }

    @Override
    public void onError(Object object) {
        LogUtil.d(TAG, "onError");
    }
}
