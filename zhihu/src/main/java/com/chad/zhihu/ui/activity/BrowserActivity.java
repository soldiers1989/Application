package com.chad.zhihu.ui.activity;

import com.chad.zhihu.ui.base.BaseRxAppCompatActivity;
import com.chad.zhihu.util.LogUtil;

public class BrowserActivity extends BaseRxAppCompatActivity {

    private static final String TAG = BrowserActivity.class.getSimpleName();

    @Override
    protected int getLayoutId() {
        return 0;
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
