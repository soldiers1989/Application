package com.chad.hlife.ui.activity;

import android.graphics.Color;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import com.chad.hlife.R;
import com.chad.hlife.app.AppConstant;
import com.chad.hlife.app.AppSettings;
import com.chad.hlife.app.config.MobConfig;
import com.chad.hlife.entity.mob.UserPasswordInfo;
import com.chad.hlife.helper.ActivityHelper;
import com.chad.hlife.mvp.presenter.password.PasswordPresenter;
import com.chad.hlife.mvp.view.IPasswordView;
import com.chad.hlife.ui.base.BaseMvpAppCompatActivity;
import com.chad.hlife.util.CheckUtil;
import com.chad.hlife.util.LogUtil;
import com.chad.hlife.util.StatusBarUtil;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;

public class UpdatePasswordActivity extends BaseMvpAppCompatActivity<IPasswordView, PasswordPresenter>
        implements IPasswordView {

    private static final String TAG = UpdatePasswordActivity.class.getSimpleName();

    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.image_tips_new_password)
    AppCompatImageView mImagePasswordTips;
    @BindView(R.id.text_tips_new_password)
    AppCompatTextView mTextPasswordTips;
    @BindView(R.id.edit_password)
    AppCompatEditText mEditPassword;
    @BindView(R.id.edit_new_password)
    AppCompatEditText mEditNewPassword;
    @BindView(R.id.edit_new_password_again)
    AppCompatEditText mEditAgainNewPassword;

    @Override
    protected PasswordPresenter onGetPresenter() {
        return new PasswordPresenter();
    }

    @Override
    protected int onGetLayoutId() {
        return R.layout.activity_update_password;
    }

    @Override
    protected void onInitView() {
        LogUtil.d(TAG, "initViews");
        initColor();
        initToolbar();
        initTipsView();
        initEditText();
    }

    private void initColor() {
        LogUtil.d(TAG, "initColor");
        StatusBarUtil.setStatusBarColor(this, getResources().getColor(AppConstant.COLOR_STATUS_BAR_BLUE));
    }

    private void initToolbar() {
        LogUtil.d(TAG, "initToolbar");
        mToolbar.setTitle(R.string.update_password);
        mToolbar.setTitleTextColor(Color.WHITE);
        mToolbar.setNavigationIcon(R.drawable.ic_back_light);
        mToolbar.setNavigationOnClickListener(view -> finish());
    }

    private void initTipsView() {
        LogUtil.d(TAG, "initTipsView");
        mTextPasswordTips.setText(R.string.password_tips);
    }

    private void initEditText() {
        LogUtil.d(TAG, "initEditText");
        mEditPassword.setOnFocusChangeListener((view, b) ->
                checkPassword(mEditPassword.getText().toString())
        );
        mEditNewPassword.setOnFocusChangeListener((view, b) ->
                checkNewPassword(mEditNewPassword.getText().toString())
        );
    }

    @Override
    protected void onInitData() {

    }

    @OnClick({R.id.btn_update})
    public void onUpdateClick() {
        LogUtil.d(TAG, "onUpdateClick");
        String password = mEditPassword.getText().toString();
        String newPassword = mEditNewPassword.getText().toString();
        if (TextUtils.isEmpty(password) || TextUtils.isEmpty(newPassword)) {
            Toast.makeText(getApplicationContext(), R.string.new_or_old_password_cannot_be_empty,
                    Toast.LENGTH_SHORT).show();
        } else if (!newPassword.equals(mEditAgainNewPassword.getText().toString())) {
            Toast.makeText(getApplicationContext(), R.string.the_new_password_entered_twice_is_inconsistent,
                    Toast.LENGTH_SHORT).show();
        } else {
            presenter.updatePassword(bindToLifecycle(), MobConfig.APP_KEY,
                    AppSettings.getInstance().getUserName(), AppSettings.getInstance().getPassword(),
                    mEditNewPassword.getText().toString());
        }
    }

    @Override
    public void onUserPasswordInfo(UserPasswordInfo userPasswordInfo) {
        LogUtil.d(TAG, "onUserPasswordInfo : userPasswordInfo = " + userPasswordInfo);
        if (userPasswordInfo == null) {
            return;
        }
        if (userPasswordInfo.getMsg().equals("success")) {
            Toast.makeText(getApplicationContext(), R.string.password_modified_successfully, Toast.LENGTH_SHORT).show();
            clearUserData();
            Observable.timer(200, TimeUnit.MILLISECONDS)
                    .compose(bindToLifecycle())
                    .subscribeOn(AndroidSchedulers.mainThread())
                    .subscribe(aLong -> {
                        ActivityHelper.startLoginActivity(this);
                        finish();
                    });
        } else {
            Toast.makeText(getApplicationContext(), userPasswordInfo.getMsg(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onError(Object object) {
        LogUtil.d(TAG, "onError");
    }

    private void checkPassword(String password) {
        LogUtil.d(TAG, "checkPassword : password = " + password);
        if (TextUtils.isEmpty(password)) {
            return;
        }
        if (!password.equals(AppSettings.getInstance().getPassword())) {
            Toast.makeText(getApplicationContext(), R.string.please_enter_the_old_password, Toast.LENGTH_LONG).show();
            mEditPassword.getText().clear();
        }
    }

    private void checkNewPassword(String password) {
        LogUtil.d(TAG, "checkNewPassword : password = " + password);
        if (TextUtils.isEmpty(password)) {
            return;
        }
        if (mImagePasswordTips.getVisibility() != View.VISIBLE) {
            mImagePasswordTips.setVisibility(View.VISIBLE);
        }
        if (CheckUtil.isUserName(password)) {
            mImagePasswordTips.setImageResource(R.drawable.ic_pass);
        } else {
            mImagePasswordTips.setImageResource(R.drawable.ic_blocking);
        }
    }

    private void clearUserData() {
        LogUtil.d(TAG, "clearUserData");
        AppSettings.getInstance().putLoginModel(AppConstant.LOGIN_MODEL_NULL);
        AppSettings.getInstance().putUserName(null);
        AppSettings.getInstance().putPassword(null);
        AppSettings.getInstance().putToken(null);
        AppSettings.getInstance().putUid(null);
    }
}
