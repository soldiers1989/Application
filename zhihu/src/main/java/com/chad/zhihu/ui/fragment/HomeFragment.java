package com.chad.zhihu.ui.fragment;

import android.support.annotation.NonNull;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.chad.zhihu.R;
import com.chad.zhihu.entity.zhihu.LatestInfo;
import com.chad.zhihu.mvp.zhihu.presenter.HomePresenter;
import com.chad.zhihu.mvp.zhihu.view.IHomeView;
import com.chad.zhihu.ui.adapter.LatestAdapter;
import com.chad.zhihu.ui.base.BaseRxFragment;
import com.chad.zhihu.ui.view.banner.Banner;
import com.chad.zhihu.ui.view.banner.BannerView;
import com.chad.zhihu.ui.view.recycler.HeaderViewAdapter;
import com.chad.zhihu.ui.view.recycler.OnLoadScrollListener;
import com.chad.zhihu.util.LogUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import io.reactivex.Observable;

public class HomeFragment extends BaseRxFragment<IHomeView, HomePresenter> implements
        IHomeView, SwipeRefreshLayout.OnRefreshListener {

    private static final String TAG = HomeFragment.class.getSimpleName();

    @BindView(R.id.swipe_refresh)
    SwipeRefreshLayout mSwipeRefresh;
    @BindView(R.id.home_recycler)
    RecyclerView mHomeRecycler;
    private BannerView mBannerView;

    private LinearLayoutManager linearLayoutManager = null;
    private OnRecyclerLoadScrollListener onRecyclerLoadScrollListener = null;
    private LatestAdapter latestAdapter = null;
    private HeaderViewAdapter headerViewAdapter = null;
    private LatestInfo latestInfo = null;

    private List<Banner> bannerList = null;

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
        bannerList = new ArrayList<>();
        mSwipeRefresh.post(() -> mSwipeRefresh.setRefreshing(true));
        LogUtil.d(TAG, "initData");
    }

    private void initSwipeRefresh() {
        mSwipeRefresh.setColorSchemeResources(R.color.colorSwipeRefreshScheme);
        mSwipeRefresh.setOnRefreshListener(this);
        LogUtil.d(TAG, "initSwipeRefresh");
    }

    private void initHomeRecycler() {
        linearLayoutManager = new LinearLayoutManager(getActivity());
        onRecyclerLoadScrollListener = new OnRecyclerLoadScrollListener();
        latestAdapter = new LatestAdapter(getActivity());
        headerViewAdapter = new HeaderViewAdapter(latestAdapter);
        mBannerView = new BannerView(getActivity());
        headerViewAdapter.addHeaderView(mBannerView);

        mHomeRecycler.setHasFixedSize(true);
        mHomeRecycler.setLayoutManager(linearLayoutManager);
        mHomeRecycler.setAdapter(headerViewAdapter);
        mHomeRecycler.addOnScrollListener(onRecyclerLoadScrollListener);
        LogUtil.d(TAG, "initHomeRecycler");
    }

    @Override
    public void onLatestInfo(LatestInfo latestInfo) {
        LogUtil.d(TAG, "onLatestInfo : latestInfo = " + latestInfo);
        if (latestInfo == null) {
            return;
        }
        this.latestInfo = latestInfo;
        mSwipeRefresh.setRefreshing(false);
        Observable.fromIterable(latestInfo.getTop_stories())
                .forEach(topStories -> {
                    bannerList.add(new Banner(topStories.getId(), topStories.getTitle(), topStories.getImage()));
                });
        mBannerView.setBannerList(bannerList);
        mBannerView.start();
        latestAdapter.setStoriesList(latestInfo.getStories());
    }

    @Override
    public void onFail() {
        LogUtil.d(TAG, "onFail");
    }

    @Override
    public void onRefresh() {
        if (bannerList != null) {
            bannerList.clear();
        }
        if (presenter == null) {
            return;
        }
        presenter.getLatestInfo(bindToLifecycle());
        LogUtil.d(TAG, "onRefresh");
    }

    private class OnRecyclerLoadScrollListener extends OnLoadScrollListener {

        @Override
        protected void onLoad(int currentPage) {

        }

        @Override
        public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
            if (mSwipeRefresh != null && linearLayoutManager != null) {
                mSwipeRefresh.setEnabled(linearLayoutManager.findFirstVisibleItemPosition() == 0);
            }
            super.onScrolled(recyclerView, dx, dy);
        }
    }
}
