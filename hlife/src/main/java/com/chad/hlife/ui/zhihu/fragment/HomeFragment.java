package com.chad.hlife.ui.zhihu.fragment;

import android.support.annotation.NonNull;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.chad.hlife.R;
import com.chad.hlife.app.config.JuHeConfig;
import com.chad.hlife.entity.zhihu.HomeInfo;
import com.chad.hlife.helper.ActivityHelper;
import com.chad.hlife.mvp.presenter.zhihu.home.HomePresenter;
import com.chad.hlife.mvp.view.zhihu.IHomeView;
import com.chad.hlife.ui.base.BaseMvpFragment;
import com.chad.hlife.ui.juhe.adapter.NewsAdapter;
import com.chad.hlife.ui.view.banner.Banner;
import com.chad.hlife.ui.view.banner.BannerView;
import com.chad.hlife.ui.view.recycler.OnLoadMoreScrollListener;
import com.chad.hlife.ui.view.refresh.HeaderView;
import com.chad.hlife.util.LogUtil;
import com.github.nuptboyzhb.lib.SuperSwipeRefreshLayout;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;

public class HomeFragment extends BaseMvpFragment<IHomeView, HomePresenter> implements IHomeView, SuperSwipeRefreshLayout.OnPullRefreshListener {

    private static final String TAG = HomeFragment.class.getSimpleName();

    @BindView(R.id.layout_super_swipe_refresh)
    SuperSwipeRefreshLayout mSuperSwipeRefreshLayout;
    @BindView(R.id.view_recycler)
    RecyclerView mRecyclerView;

    private HeaderView mHeaderView;
    private BannerView mBannerView;

    private HomeInfo mHomeInfo = null;

    private List<Banner> mBanners = null;
    private List<Integer> mStoryIds = null;

    @Override
    protected HomePresenter onGetPresenter() {
        return new HomePresenter();
    }

    @Override
    protected int onGetLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    protected void onInitView() {
        LogUtil.d(TAG, "onInitView");
        initSuperSwipeRefreshLayout();
        initRecyclerView();
    }

    @Override
    protected void onInitData() {
        LogUtil.d(TAG, "onInitData");
        mBanners = new ArrayList<>();
        mStoryIds = new ArrayList<>();
        presenter.getLatestHomeInfo(bindToLifecycle());
    }

    private void initSuperSwipeRefreshLayout() {
        LogUtil.d(TAG, "initSuperSwipeRefreshLayout");
        mHeaderView = new HeaderView(getContext());
        mSuperSwipeRefreshLayout.setHeaderView(mHeaderView);
        mSuperSwipeRefreshLayout.setOnPullRefreshListener(this);
    }

    private void initRecyclerView() {
        LogUtil.d(TAG, "initRecyclerView");
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(linearLayoutManager);
    }

    @Override
    public void onLatestHomeInfo(HomeInfo homeInfo) {
        LogUtil.d(TAG, "onLatestHomeInfo : homeInfo = " + homeInfo);
        if (homeInfo == null) {
            return;
        }
        if (mSuperSwipeRefreshLayout.isRefreshing()) {
            mSuperSwipeRefreshLayout.setRefreshing(false);
        }
        mHomeInfo = homeInfo;
        mStoryIds.clear();
        mStoryIds.addAll(homeInfo.getStoryIds());
        Observable.fromIterable(homeInfo.getTop_stories())
                .forEach(topStories ->
                        mBanners.add(new Banner(topStories.getId(), topStories.getTitle(),
                                topStories.getImage())));
        mBannerView.setBannerList(mBanners);
        mBannerView.start();
    }

    @Override
    public void onMoreHomeInfo(HomeInfo homeInfo) {
        LogUtil.d(TAG, "onMoreHomeInfo : homeInfo = " + homeInfo);
        if (homeInfo == null) {
            return;
        }
        mHomeInfo = homeInfo;
        mStoryIds.addAll(homeInfo.getStoryIds());
    }

    @Override
    public void onError(Object object) {

    }

    @Override
    public void onRefresh() {
        LogUtil.d(TAG, "onRefresh");
        if (mHeaderView == null) {
            return;
        }
        mHeaderView.refresh();
        Observable.timer(1, TimeUnit.SECONDS)
                .compose(bindToLifecycle())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(aLong -> presenter.getLatestHomeInfo(bindToLifecycle()));
    }

    @Override
    public void onPullDistance(int i) {

    }

    @Override
    public void onPullEnable(boolean enable) {
        LogUtil.d(TAG, "onPullEnable : enable = " + enable);
        if (mHeaderView == null) {
            return;
        }
        mHeaderView.pullEnable(enable);
    }

    @Override
    public void onDestroy() {
        LogUtil.d(TAG, "onDestroy");
        if (mBannerView != null) {
            mBannerView.stop();
        }
        super.onDestroy();
    }


    private class LoadMoreScrollListener extends OnLoadMoreScrollListener {

        @Override
        protected void onLoadMore() {
            LogUtil.d(TAG, "onLoadMore : mHomeInfo = " + mHomeInfo);
            if (mHomeInfo == null || presenter == null) {
                return;
            }
            presenter.getMoreHomeInfo(bindToLifecycle(), mHomeInfo.getDate());
        }

        @Override
        public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
//            if (mSwipeRefresh != null && mLinearLayoutManager != null) {
//                mSwipeRefresh.setEnabled(mLinearLayoutManager.findFirstCompletelyVisibleItemPosition() == 0);
//            }
            super.onScrolled(recyclerView, dx, dy);
        }
    }
}
