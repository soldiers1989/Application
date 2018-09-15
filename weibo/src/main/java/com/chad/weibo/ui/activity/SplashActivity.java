package com.chad.weibo.ui.activity;

import com.chad.weibo.R;
import com.chad.weibo.ui.base.BaseRxAppCompatActivity;
import com.chad.weibo.util.LogUtil;

public class SplashActivity extends BaseRxAppCompatActivity {

    private static final String TAG = SplashActivity.class.getSimpleName();

    @Override
    protected int getLayoutId() {
        return R.layout.activity_splash;
    }

    @Override
    protected void initViews() {
        LogUtil.d(TAG, "initViews");
    }

    @Override
    protected void initData() {
        LogUtil.d(TAG, "initData");
    }
}
