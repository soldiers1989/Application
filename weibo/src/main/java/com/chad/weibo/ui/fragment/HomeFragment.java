package com.chad.weibo.ui.fragment;

import com.chad.weibo.R;
import com.chad.weibo.ui.base.BaseRxFragment;
import com.chad.weibo.util.LogUtil;
import com.github.nuptboyzhb.lib.SuperSwipeRefreshLayout;

import butterknife.BindView;

// https://github.com/nuptboyzhb/SuperSwipeRefreshLayout
public class HomeFragment extends BaseRxFragment {

    private static final String TAG = HomeFragment.class.getSimpleName();

    @BindView(R.id.layout_swipe_refresh)
    SuperSwipeRefreshLayout mSuperSwipeRefreshLayout;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initViews() {
        LogUtil.d(TAG, "initViews");
        initSuperSwipeRefresh();
    }

    private void initSuperSwipeRefresh() {
        LogUtil.d(TAG, "initSuperSwipeRefresh");
        mSuperSwipeRefreshLayout.setHeaderView(null);
        mSuperSwipeRefreshLayout.setOnPullRefreshListener(
                new SuperSwipeRefreshLayout.OnPullRefreshListener() {
                    @Override
                    public void onRefresh() {
                        LogUtil.d(TAG, "onRefresh");
                    }

                    @Override
                    public void onPullDistance(int distance) {
                        LogUtil.d(TAG, "onPullDistance : distance = " + distance);
                    }

                    @Override
                    public void onPullEnable(boolean enable) {
                        LogUtil.d(TAG, "onPullEnable : enable = " + enable);
                    }
                });
    }

    @Override
    protected void initData() {
        LogUtil.d(TAG, "initData");
    }
}
