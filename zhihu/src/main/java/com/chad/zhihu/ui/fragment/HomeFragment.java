package com.chad.zhihu.ui.fragment;

import android.app.Activity;

import com.chad.zhihu.R;
import com.chad.zhihu.entity.LatestDailyInfo;
import com.chad.zhihu.mvp.Contract;
import com.chad.zhihu.mvp.Presenter;
import com.chad.zhihu.ui.base.BaseRxFragment;
import com.chad.zhihu.util.LogUtil;

import javax.inject.Inject;

public class HomeFragment extends BaseRxFragment implements Contract.IView {

    private static final String TAG = HomeFragment.class.getSimpleName();

    @Inject
    Presenter presenter;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initViews() {

    }

    @Override
    protected void initData() {

    }

    @Override
    public void onLatestDailyInfo(LatestDailyInfo latestDailyInfo) {
        LogUtil.d(TAG, "onLatestDailyInfo = " + latestDailyInfo);
    }

    @Override
    public void onFail() {
        LogUtil.d(TAG, "onFail");
    }
}
