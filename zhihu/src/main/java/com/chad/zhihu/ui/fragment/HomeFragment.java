package com.chad.zhihu.ui.fragment;

import android.support.annotation.NonNull;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.chad.zhihu.R;
import com.chad.zhihu.entity.zhihu.HomeInfo;
import com.chad.zhihu.hepler.ActivityHelper;
import com.chad.zhihu.mvp.zhihu.presenter.HomePresenter;
import com.chad.zhihu.mvp.zhihu.view.IHomeView;
import com.chad.zhihu.ui.adapter.HomeAdapter;
import com.chad.zhihu.ui.base.BaseRxFragment;
import com.chad.zhihu.ui.view.banner.Banner;
import com.chad.zhihu.ui.view.banner.BannerView;
import com.chad.zhihu.ui.view.recycler.HeaderViewAdapter;
import com.chad.zhihu.ui.view.recycler.OnLoadMoreScrollListener;
import com.chad.zhihu.util.LogUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import io.reactivex.Observable;

public class HomeFragment extends BaseRxFragment<IHomeView, HomePresenter> implements
        IHomeView, SwipeRefreshLayout.OnRefreshListener, BannerView.OnBannerItemClickListener, HomeAdapter.OnItemClickListener {

    private static final String TAG = HomeFragment.class.getSimpleName();

    @BindView(R.id.swipe_refresh)
    SwipeRefreshLayout mSwipeRefresh;
    @BindView(R.id.home_recycler)
    RecyclerView mHomeRecycler;

    private LinearLayoutManager mLinearLayoutManager = null;
    private LoadMoreScrollListener mLoadMoreScrollListener = null;
    private BannerView mBannerView;
    private HomeAdapter mHomeAdapter = null;
    private HeaderViewAdapter mHeaderViewAdapter = null;
    private HomeInfo mHomeInfo = null;

    private List<Banner> mBannerList = null;

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
        LogUtil.d(TAG, "initViews");
        initSwipeRefresh();
        initHomeRecycler();
    }

    @Override
    protected void initData() {
        LogUtil.d(TAG, "initData");
        mBannerList = new ArrayList<>();
        mSwipeRefresh.post(() -> {
            mSwipeRefresh.setRefreshing(true);
            onRefresh();
        });

    }

    private void initSwipeRefresh() {
        LogUtil.d(TAG, "initSwipeRefresh");
        mSwipeRefresh.setColorSchemeResources(R.color.colorSwipeRefreshScheme);
        mSwipeRefresh.setOnRefreshListener(this);
    }

    private void initHomeRecycler() {
        LogUtil.d(TAG, "initHomeRecycler");
        mLinearLayoutManager = new LinearLayoutManager(getActivity());

        mLoadMoreScrollListener = new LoadMoreScrollListener();
        mLoadMoreScrollListener.setLinearLayoutManager(mLinearLayoutManager);

        mBannerView = new BannerView(getActivity());
        mBannerView.setOnBannerItemClickListener(this);

        mHomeAdapter = new HomeAdapter(getActivity());
        mHomeAdapter.setOnItemClickListener(this);

        mHeaderViewAdapter = new HeaderViewAdapter(mHomeAdapter);
        mBannerView = new BannerView(getActivity());

        mHeaderViewAdapter.addHeaderView(mBannerView);

        mHomeRecycler.setHasFixedSize(true);
        mHomeRecycler.setLayoutManager(mLinearLayoutManager);
        mHomeRecycler.setAdapter(mHeaderViewAdapter);
        mHomeRecycler.addOnScrollListener(mLoadMoreScrollListener);
    }

    @Override
    public void onLatestHomeInfo(HomeInfo homeInfo) {
        LogUtil.d(TAG, "onLatestHomeInfo : homeInfo = " + homeInfo);
        if (homeInfo == null) {
            return;
        }
        mHomeInfo = homeInfo;
        mSwipeRefresh.setRefreshing(false);
        Observable.fromIterable(homeInfo.getTop_stories())
                .forEach(topStories ->
                        mBannerList.add(new Banner(topStories.getId(), topStories.getTitle(),
                                topStories.getImage())));
        mBannerView.setBannerList(mBannerList);
        mBannerView.start();
        mHomeAdapter.setStoriesList(homeInfo.getStories());
    }

    @Override
    public void onMoreHomeInfo(HomeInfo homeInfo) {
        LogUtil.d(TAG, "onMoreHomeInfo : homeInfo = " + homeInfo);
        if (homeInfo == null) {
            return;
        }
        mHomeInfo = homeInfo;
        mLoadMoreScrollListener.setLoading(false);
        mHomeAdapter.addStoriesList(homeInfo.getStories());
    }

    @Override
    public void onFail() {
        LogUtil.d(TAG, "onFail");
        mLoadMoreScrollListener.setLoading(false);
    }

    @Override
    public void onRefresh() {
        LogUtil.d(TAG, "onRefresh : mBannerList = " + mBannerList
                + " , presenter = " + presenter);
        if (mBannerList != null) {
            mBannerList.clear();
        }
        if (presenter == null) {
            return;
        }
        presenter.getLatestHomeInfo(bindToLifecycle());
    }

    @Override
    public void onBannerItemClick(int id) {
        LogUtil.d(TAG, "onBannerItemClick : id = " + id);
        ActivityHelper.startHomeDetailActivity(getActivity(), id);
    }

    @Override
    public void onItemClick(int id) {
        LogUtil.d(TAG, "onItemClick : id = " + id);
        ActivityHelper.startHomeDetailActivity(getActivity(), id);
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
            if (mSwipeRefresh != null && mLinearLayoutManager != null) {
                mSwipeRefresh.setEnabled(mLinearLayoutManager.findFirstCompletelyVisibleItemPosition() == 0);
            }
            super.onScrolled(recyclerView, dx, dy);
        }
    }
}
