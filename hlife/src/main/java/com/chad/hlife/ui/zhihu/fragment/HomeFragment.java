package com.chad.hlife.ui.zhihu.fragment;

import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;

import com.chad.hlife.R;
import com.chad.hlife.app.AppConstant;
import com.chad.hlife.entity.zhihu.HomeInfo;
import com.chad.hlife.helper.ActivityHelper;
import com.chad.hlife.mvp.presenter.zhihu.home.HomePresenter;
import com.chad.hlife.mvp.view.zhihu.IHomeView;
import com.chad.hlife.ui.base.BaseMvpFragment;
import com.chad.hlife.ui.view.banner.Banner;
import com.chad.hlife.ui.view.banner.BannerView;
import com.chad.hlife.ui.view.loading.DoubleCircleLoadingView;
import com.chad.hlife.ui.view.recycler.HeaderViewAdapter;
import com.chad.hlife.ui.view.recycler.OnLoadMoreScrollListener;
import com.chad.hlife.ui.view.refresh.HeaderView;
import com.chad.hlife.ui.zhihu.adapter.HomeAdapter;
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
    @BindView(R.id.layout_loading)
    ConstraintLayout mLoading;
    @BindView(R.id.view_loading)
    DoubleCircleLoadingView mLoadingView;

    private HeaderView mHeaderView;
    private LoadMoreScrollListener mLoadMoreScrollListener;
    private BannerView mBannerView;
    private HomeAdapter mHomeAdapter;

    private List<Banner> mBanners;
    private ArrayList<Integer> mStoryIds;

    private String mDate;

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
        mLoadingView.setColor(getResources().getColor(AppConstant.COLOR_STATUS_BAR_RED));
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
        mRecyclerView.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
        mLoadMoreScrollListener = new LoadMoreScrollListener();
        mLoadMoreScrollListener.setLinearLayoutManager(linearLayoutManager);
        mRecyclerView.addOnScrollListener(mLoadMoreScrollListener);
        mHomeAdapter = new HomeAdapter(getContext());
        mHomeAdapter.setOnItemClickListener(position ->
                ActivityHelper.startDetailActivity(getActivity(), mStoryIds,
                        mHomeAdapter.getData().get(position).getId())
        );

        mBannerView = new BannerView(getContext());
        mBannerView.setOnBannerItemClickListener(id ->
                ActivityHelper.startDetailActivity(getActivity(), mStoryIds, id)
        );
        HeaderViewAdapter headerViewAdapter = new HeaderViewAdapter(mHomeAdapter);
        headerViewAdapter.addHeaderView(mBannerView);
        mRecyclerView.setAdapter(headerViewAdapter);
    }

    @Override
    public void onLatestHomeInfo(HomeInfo homeInfo) {
        LogUtil.d(TAG, "onLatestHomeInfo : homeInfo = " + homeInfo);
        if (homeInfo == null) {
            return;
        }
        if (mLoading != null && mLoading.getVisibility() == View.VISIBLE) {
            mLoading.setVisibility(View.GONE);
        }
        if (mSuperSwipeRefreshLayout.isRefreshing()) {
            mSuperSwipeRefreshLayout.setRefreshing(false);
        }
        mBanners.clear();
        Observable.fromIterable(homeInfo.getTop_stories())
                .forEach(topStories ->
                        mBanners.add(new Banner(topStories.getId(), topStories.getTitle(),
                                topStories.getImage())));
        mBannerView.setBannerList(mBanners);
        mBannerView.start();
        mHomeAdapter.setData(homeInfo.getStories());
        mStoryIds.clear();
        mStoryIds.addAll(homeInfo.getStoryIds());
        mDate = homeInfo.getDate();
    }

    @Override
    public void onMoreHomeInfo(HomeInfo homeInfo) {
        LogUtil.d(TAG, "onMoreHomeInfo : homeInfo = " + homeInfo);
        if (homeInfo == null) {
            return;
        }
        if (mLoadMoreScrollListener.isLoading()) {
            mLoadMoreScrollListener.setLoading(false);
        }
        mHomeAdapter.addData(homeInfo.getStories());
        mStoryIds.addAll(homeInfo.getStoryIds());
        mDate = homeInfo.getDate();
    }

    @Override
    public void onError(Object object) {
        LogUtil.d(TAG, "onError");
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
            LogUtil.d(TAG, "onLoadMore : mDate = " + mDate);
            if (TextUtils.isEmpty(mDate)) {
                return;
            }
            presenter.getMoreHomeInfo(bindToLifecycle(), mDate);
        }
    }
}
