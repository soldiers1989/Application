package com.chad.hlife.ui.fragment;

import android.support.v7.widget.AppCompatTextView;

import com.chad.hlife.HLifeApplication;
import com.chad.hlife.R;
import com.chad.hlife.app.AppConstant;
import com.chad.hlife.app.AppSettings;
import com.chad.hlife.helper.ActivityHelper;
import com.chad.hlife.helper.MobAuthHelper;
import com.chad.hlife.helper.WeiBoAuthHelper;
import com.chad.hlife.ui.base.BaseRxFragment;
import com.chad.hlife.util.CacheFileUtil;
import com.chad.hlife.util.LogUtil;

import butterknife.BindView;
import butterknife.OnClick;

public class SettingsFragment extends BaseRxFragment {

    private static final String TAG = SettingsFragment.class.getSimpleName();

    @BindView(R.id.text_cache)
    AppCompatTextView mTextCache;

    @Override
    protected int onGetLayoutId() {
        return R.layout.fragment_settings;
    }

    @Override
    protected void onInitView() {
        LogUtil.d(TAG, "onInitView");
    }

    @Override
    protected void onInitData() {
        LogUtil.d(TAG, "onInitData");
    }

    @OnClick(R.id.layout_user_data)
    public void onUserDataClick() {
        LogUtil.d(TAG, "onUserDataClick");
        ActivityHelper.startUserDataActivity(getActivity());
    }

    @OnClick(R.id.layout_update_password)
    public void onUpdatePassword() {
        LogUtil.d(TAG, "onUpdatePassword");
        ActivityHelper.startUpdatePasswordActivity(getActivity());
    }

    @OnClick(R.id.layout_about_soft)
    public void onAboutSoftClick() {
        LogUtil.d(TAG, "onAboutSoftClick");
        ActivityHelper.startAboutSoftActivity(getActivity());
    }

    @OnClick(R.id.layout_clear_cache)
    public void onClearCacheClick() {
        LogUtil.d(TAG, "onClearCacheClick");
        CacheFileUtil.clearCacheFile(HLifeApplication.getHLifeApplication());
        refreshData();
    }

    @OnClick(R.id.btn_logout)
    public void onLogoutClick() {
        LogUtil.d(TAG, "onLogoutClick");
        switch (AppSettings.getInstance().getLoginModel()) {
            case AppConstant.LOGIN_MODEL_MOB:
                MobAuthHelper.getInstance().clearAccessToken();
                MobAuthHelper.getInstance().clearUserConfig();
                break;
            case AppConstant.LOGIN_MODEL_WEIBO:
                WeiBoAuthHelper.getInstance(HLifeApplication.getHLifeApplication()).clearAccessToken();
                break;
            default:
                break;
        }
        AppSettings.getInstance().putLoginModel(AppConstant.LOGIN_MODEL_NULL);
        getActivity().finish();
    }

    @Override
    public void onResume() {
        LogUtil.d(TAG, "onResume");
        refreshData();
        super.onResume();
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        LogUtil.d(TAG, "onHiddenChanged : hidden = " + hidden);
        if (!hidden) {
            refreshData();
        }
        super.onHiddenChanged(hidden);
    }

    private void refreshData() {
        LogUtil.d(TAG, "refreshData");
        mTextCache.setText(CacheFileUtil.getCacheFileSize(HLifeApplication.getHLifeApplication()));
    }
}
