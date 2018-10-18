package com.chad.hlife.ui.activity;

import android.graphics.Color;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.Toolbar;
import android.text.InputFilter;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import com.chad.hlife.R;
import com.chad.hlife.app.AppConstant;
import com.chad.hlife.app.config.MobConfig;
import com.chad.hlife.entity.mob.UserRegisterInfo;
import com.chad.hlife.mvp.presenter.register.RegisterPresenter;
import com.chad.hlife.mvp.view.IRegisterView;
import com.chad.hlife.ui.base.BaseMvpAppCompatActivity;
import com.chad.hlife.util.CheckUtil;
import com.chad.hlife.util.InputFilterUtil;
import com.chad.hlife.util.LogUtil;
import com.chad.hlife.util.StatusBarUtil;

import butterknife.BindView;
import butterknife.OnClick;

public class RegisterActivity extends BaseMvpAppCompatActivity<IRegisterView, RegisterPresenter>
        implements IRegisterView {

    private static final String TAG = RegisterActivity.class.getSimpleName();

    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.image_tips_user_name)
    AppCompatImageView mImageUserNameTips;
    @BindView(R.id.image_tips_password)
    AppCompatImageView mImagePasswordTips;
    @BindView(R.id.text_tips_user_name)
    AppCompatTextView mTextUserNameTips;
    @BindView(R.id.text_tips_password)
    AppCompatTextView mTextPasswordTips;
    @BindView(R.id.edit_user_name)
    AppCompatEditText mEditUserName;
    @BindView(R.id.edit_password)
    AppCompatEditText mEditPassword;
    @BindView(R.id.edit_password_again)
    AppCompatEditText mEditAgainPassword;

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
        initTipsView();
        initEditText();
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

    private void initTipsView() {
        LogUtil.d(TAG, "initTipsView");
        mTextUserNameTips.setText(R.string.user_name_tips);
        mTextPasswordTips.setText(R.string.password_tips);
    }

    private void initEditText() {
        LogUtil.d(TAG, "initEditText");
        mEditUserName.setFilters(new InputFilter[]{new InputFilterUtil.SpaceFilter()});
        mEditUserName.setOnFocusChangeListener((view, b) ->
                checkUserName(mEditUserName.getText().toString())
        );
        mEditPassword.setOnFocusChangeListener((view, b) ->
                checkPassword(mEditPassword.getText().toString())
        );
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
        } else if (!password.equals(mEditAgainPassword.getText().toString())) {
            Toast.makeText(getApplicationContext(), R.string.the_password_entered_twice_is_inconsistent,
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
        if (userRegisterInfo.getMsg().equals("success")) {
            finish();
        } else {
            Toast.makeText(getApplicationContext(), userRegisterInfo.getMsg(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onError(Object object) {
        LogUtil.d(TAG, "onError");
    }

    private void checkUserName(String userName) {
        LogUtil.d(TAG, "checkUserName : userName = " + userName);
        if (TextUtils.isEmpty(userName)) {
            return;
        }
        if (mImageUserNameTips.getVisibility() != View.VISIBLE) {
            mImageUserNameTips.setVisibility(View.VISIBLE);
        }
        if (CheckUtil.isUserName(userName)) {
            mImageUserNameTips.setImageResource(R.drawable.ic_pass);
        } else {
            mImageUserNameTips.setImageResource(R.drawable.ic_blocking);
        }
    }

    private void checkPassword(String password) {
        LogUtil.d(TAG, "checkPassword : password = " + password);
        if (TextUtils.isEmpty(password)) {
            return;
        }
        if (mImagePasswordTips.getVisibility() != View.VISIBLE) {
            mImagePasswordTips.setVisibility(View.VISIBLE);
        }
        if (CheckUtil.isPassword(password)) {
            mImagePasswordTips.setImageResource(R.drawable.ic_pass);
        } else {
            mImagePasswordTips.setImageResource(R.drawable.ic_blocking);
        }
    }
}
