package com.chad.hlife.ui.activity;

import android.graphics.Color;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.Toolbar;

import com.chad.hlife.BuildConfig;
import com.chad.hlife.R;
import com.chad.hlife.app.AppConstant;
import com.chad.hlife.ui.base.BaseRxAppCompatActivity;
import com.chad.hlife.util.LogUtil;
import com.chad.hlife.util.StatusBarUtil;

import butterknife.BindView;

public class AboutSoftActivity extends BaseRxAppCompatActivity {

    private static final String TAG = AboutSoftActivity.class.getSimpleName();

    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.text_soft_version)
    AppCompatTextView mTextSoftVersion;

    @Override
    protected int onGetLayoutId() {
        return R.layout.activity_about_soft;
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
        mToolbar.setTitle(R.string.about_soft);
        mToolbar.setTitleTextColor(Color.WHITE);
        mToolbar.setNavigationIcon(R.drawable.ic_back_light);
        mToolbar.setNavigationOnClickListener(view -> finish());
    }

    @Override
    protected void onInitData() {
        LogUtil.d(TAG, "onInitData");
        mTextSoftVersion.setText(getString(R.string.soft_version) + "：" + BuildConfig.VERSION_NAME);
    }
}
