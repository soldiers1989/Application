package com.chad.weibo.ui.fragment;

import com.chad.weibo.R;
import com.chad.weibo.ui.base.BaseRxFragment;
import com.chad.weibo.util.LogUtil;

public class MessageFragment extends BaseRxFragment {

    private static final String TAG = MessageFragment.class.getSimpleName();

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_message;
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
