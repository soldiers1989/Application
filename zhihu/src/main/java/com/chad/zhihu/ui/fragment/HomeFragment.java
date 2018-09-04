package com.chad.zhihu.ui.fragment;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;

import com.chad.zhihu.R;
import com.chad.zhihu.entity.zhihu.LatestInfo;
import com.chad.zhihu.mvp.zhihu.presenter.HomePresenter;
import com.chad.zhihu.mvp.zhihu.view.IHomeView;
import com.chad.zhihu.ui.base.BaseRxFragment;
import com.chad.zhihu.util.LogUtil;

import butterknife.BindView;

public class HomeFragment extends BaseRxFragment<IHomeView, HomePresenter> implements IHomeView, SwipeRefreshLayout.OnRefreshListener {

    private static final String TAG = HomeFragment.class.getSimpleName();

    @BindView(R.id.swipe_refresh)
    SwipeRefreshLayout mSwipeRefresh;
    @BindView(R.id.home_recycler)
    RecyclerView mHomeRecycler;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    protected HomePresenter getPresenter() {
        return new HomePresenter();
    }

    @Override
    protected void initViews() {
        initSwipeRefresh();
        initHomeRecycler();
        LogUtil.d(TAG, "initViews");
    }

    @Override
    protected void initData() {

        LogUtil.d(TAG, "initData");
    }

    private void initSwipeRefresh() {
        mSwipeRefresh.setColorSchemeResources();
        mSwipeRefresh.setOnRefreshListener(this);
        LogUtil.d(TAG, "initSwipeRefresh");
    }

    private void initHomeRecycler() {

        LogUtil.d(TAG, "initHomeRecycler");
    }

    @Override
    public void onLatestInfo(LatestInfo latestInfo) {
        LogUtil.d(TAG, "onLatestInfo : latestInfo = " + latestInfo);
    }

    @Override
    public void onFail() {
        LogUtil.d(TAG, "onFail");
    }

    @Override
    public void onRefresh() {
        if (presenter == null) {
            return;
        }
        presenter.getLatestInfo(bindToLifecycle());
        LogUtil.d(TAG, "onRefresh");
    }
}
