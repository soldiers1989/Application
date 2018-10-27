package com.chad.hlife.ui.activity;

import android.graphics.Color;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.Toolbar;

import com.chad.hlife.R;
import com.chad.hlife.app.AppConstant;
import com.chad.hlife.app.AppSettings;
import com.chad.hlife.app.config.MobConfig;
import com.chad.hlife.entity.mob.MobAccessToken;
import com.chad.hlife.entity.mob.UserProfileInfo;
import com.chad.hlife.helper.MobAuthHelper;
import com.chad.hlife.mvp.presenter.user.UserProfilePresenter;
import com.chad.hlife.mvp.view.IUserProfileView;
import com.chad.hlife.ui.base.BaseMvpAppCompatActivity;
import com.chad.hlife.util.LogUtil;
import com.chad.hlife.util.StatusBarUtil;

import butterknife.BindView;

public class UserProfileActivity extends BaseMvpAppCompatActivity<IUserProfileView, UserProfilePresenter>
        implements IUserProfileView {

    private static final String TAG = UserProfileActivity.class.getSimpleName();

    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.text_user_name)
    AppCompatTextView mTextUserName;
    @BindView(R.id.text_gender)
    AppCompatTextView mTextGender;
    @BindView(R.id.text_birthday)
    AppCompatTextView mTextBirthday;
    @BindView(R.id.text_mobile_phone)
    AppCompatTextView mTextMobilePhone;

    @Override
    protected UserProfilePresenter onGetPresenter() {
        return new UserProfilePresenter();
    }

    @Override
    protected int onGetLayoutId() {
        return R.layout.activity_user_data;
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
        mToolbar.setTitle(R.string.user_profile);
        mToolbar.setTitleTextColor(Color.WHITE);
        mToolbar.setNavigationIcon(R.drawable.ic_back_light);
        mToolbar.setNavigationOnClickListener(view -> finish());
    }

    @Override
    protected void onInitData() {
        LogUtil.d(TAG, "onInitData");
        mTextUserName.setText(AppSettings.getInstance().getUserName());
        initUserProfile();
    }

    private void initUserProfile() {
        LogUtil.d(TAG, "initUserProfile");
        String appKey = MobConfig.APP_KEY;
        MobAccessToken mobAccessToken = MobAuthHelper.getInstance().readAccessToken();
        presenter.queryGender(bindToLifecycle(), appKey, mobAccessToken.getToken(), mobAccessToken.getUid(),
                AppConstant.USER_PROFILE_GENDER);
        presenter.queryBirthday(bindToLifecycle(), appKey, mobAccessToken.getToken(), mobAccessToken.getUid(),
                AppConstant.USER_PROFILE_BIRTHDAY);
        presenter.queryMobilePhone(bindToLifecycle(), appKey, mobAccessToken.getToken(), mobAccessToken.getUid(),
                AppConstant.USER_PROFILE_PHONE);
    }

    @Override
    public void onPutGender(UserProfileInfo userProfileInfo) {
        LogUtil.d(TAG, "onPutGender : userProfileInfo = " + userProfileInfo);
        if (userProfileInfo == null) {
            return;
        }
        if (userProfileInfo.getMsg().equals("success")) {
            MobAccessToken mobAccessToken = MobAuthHelper.getInstance().readAccessToken();
            presenter.queryGender(bindToLifecycle(), MobConfig.APP_KEY, mobAccessToken.getToken(),
                    mobAccessToken.getUid(), AppConstant.USER_PROFILE_GENDER);
        }
    }

    @Override
    public void onQueryGender(UserProfileInfo userProfileInfo) {
        LogUtil.d(TAG, "onQueryGender : userProfileInfo = " + userProfileInfo);
        if (userProfileInfo == null) {
            return;
        }
        if (userProfileInfo.getMsg().equals("success")) {
            mTextGender.setText(userProfileInfo.getResult());
        }
    }

    @Override
    public void onPutBirthday(UserProfileInfo userProfileInfo) {
        LogUtil.d(TAG, "onPutBirthday : userProfileInfo = " + userProfileInfo);
        if (userProfileInfo == null) {
            return;
        }
        if (userProfileInfo.getMsg().equals("success")) {
            MobAccessToken mobAccessToken = MobAuthHelper.getInstance().readAccessToken();
            presenter.queryBirthday(bindToLifecycle(), MobConfig.APP_KEY, mobAccessToken.getToken(),
                    mobAccessToken.getUid(), AppConstant.USER_PROFILE_BIRTHDAY);
        }
    }

    @Override
    public void onQueryBirthday(UserProfileInfo userProfileInfo) {
        LogUtil.d(TAG, "onQueryBirthday : userProfileInfo = " + userProfileInfo);
        if (userProfileInfo == null) {
            return;
        }
        if (userProfileInfo.getMsg().equals("success")) {
            mTextBirthday.setText(userProfileInfo.getResult());
        }
    }

    @Override
    public void onPutMobilePhone(UserProfileInfo userProfileInfo) {
        LogUtil.d(TAG, "onPutMobilePhone : userProfileInfo = " + userProfileInfo);
        if (userProfileInfo == null) {
            return;
        }
        if (userProfileInfo.getMsg().equals("success")) {
            MobAccessToken mobAccessToken = MobAuthHelper.getInstance().readAccessToken();
            presenter.queryMobilePhone(bindToLifecycle(), MobConfig.APP_KEY, mobAccessToken.getToken(),
                    mobAccessToken.getUid(), AppConstant.USER_PROFILE_PHONE);
        }
    }

    @Override
    public void onQueryMobilePhone(UserProfileInfo userProfileInfo) {
        LogUtil.d(TAG, "onQueryMobilePhone : userProfileInfo = " + userProfileInfo);
        if (userProfileInfo == null) {
            return;
        }
        if (userProfileInfo.getMsg().equals("success")) {
            mTextMobilePhone.setText(userProfileInfo.getResult());
        }
    }

    @Override
    public void onError(Object object) {
        LogUtil.d(TAG, "onError");
    }
}
