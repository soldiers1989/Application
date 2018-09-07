package com.chad.zhihu.ui.fragment;

import android.support.annotation.NonNull;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.chad.zhihu.R;
import com.chad.zhihu.entity.zhihu.HomeInfo;
import com.chad.zhihu.hepler.ActivityHelper;
import com.chad.zhihu.mvp.zhihu.presenter.home.HomePresenter;
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

public class HomeFragment extends BaseRxFragment<IHomeView, HomePresenter> implements IHomeView {

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
    private ArrayList<Integer> mStoriesIds = null;

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
        mStoriesIds = new ArrayList<>();
        mSwipeRefresh.post(() -> {
            mSwipeRefresh.setRefreshing(true);
            presenter.getLatestHomeInfo(bindToLifecycle());
        });

    }

    private void initSwipeRefresh() {
        LogUtil.d(TAG, "initSwipeRefresh");
        mSwipeRefresh.setColorSchemeResources(R.color.colorProgress);
        mSwipeRefresh.setOnRefreshListener(() -> {
            if (mBannerList != null) {
                mBannerList.clear();
            }
            presenter.getLatestHomeInfo(bindToLifecycle());
        });
    }

    private void initHomeRecycler() {
        LogUtil.d(TAG, "initHomeRecycler");
        mLinearLayoutManager = new LinearLayoutManager(getActivity());

        mLoadMoreScrollListener = new LoadMoreScrollListener();
        mLoadMoreScrollListener.setLinearLayoutManager(mLinearLayoutManager);

        mHomeAdapter = new HomeAdapter(getActivity());
        mHomeAdapter.setOnItemClickListener(id ->
                ActivityHelper.startDetailActivity(getActivity(), mStoriesIds, id));

        mBannerView = new BannerView(getActivity());
        mBannerView.setOnBannerItemClickListener(id ->
                ActivityHelper.startDetailActivity(getActivity(), mStoriesIds, id));

        mHeaderViewAdapter = new HeaderViewAdapter(mHomeAdapter);
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
        mStoriesIds.addAll(mHomeInfo.getStoriesIds());
        mSwipeRefresh.setRefreshing(false);
        mLoadMoreScrollListener.setLoading(false);
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
        mStoriesIds.addAll(mHomeInfo.getStoriesIds());
        mSwipeRefresh.setRefreshing(false);
        mLoadMoreScrollListener.setLoading(false);
        mHomeAdapter.addStoriesList(homeInfo.getStories());
    }

    @Override
    public void onError() {
        LogUtil.d(TAG, "onError");
        mSwipeRefresh.setRefreshing(false);
        mLoadMoreScrollListener.setLoading(false);
        // TODO: 2018/9/6
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
