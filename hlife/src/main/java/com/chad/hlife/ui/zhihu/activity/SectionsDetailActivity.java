package com.chad.hlife.ui.zhihu.activity;

import android.content.Intent;
import android.graphics.Color;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;

import com.chad.hlife.R;
import com.chad.hlife.app.AppConstant;
import com.chad.hlife.entity.zhihu.SectionsDetailInfo;
import com.chad.hlife.entity.zhihu.SectionsInfo;
import com.chad.hlife.helper.ActivityHelper;
import com.chad.hlife.mvp.presenter.zhihu.sections.SectionsPresenter;
import com.chad.hlife.mvp.view.zhihu.ISectionsView;
import com.chad.hlife.ui.base.BaseMvpAppCompatActivity;
import com.chad.hlife.ui.view.loading.DoubleCircleLoadingView;
import com.chad.hlife.ui.view.recycler.OnLoadMoreScrollListener;
import com.chad.hlife.ui.view.refresh.HeaderView;
import com.chad.hlife.ui.zhihu.adapter.SectionsDetailAdapter;
import com.chad.hlife.util.LogUtil;
import com.chad.hlife.util.StatusBarUtil;
import com.github.nuptboyzhb.lib.SuperSwipeRefreshLayout;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;

public class SectionsDetailActivity extends BaseMvpAppCompatActivity<ISectionsView, SectionsPresenter>
        implements ISectionsView, SuperSwipeRefreshLayout.OnPullRefreshListener {

    private static final String TAG = SectionsDetailActivity.class.getSimpleName();

    @BindView(R.id.toolbar)
    Toolbar mToolbar;
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
    private SectionsDetailAdapter mSectionsDetailAdapter;

    private ArrayList<Integer> mStoryIds;

    private int mId;
    private int mTimestamp;

    @Override
    protected SectionsPresenter onGetPresenter() {
        return new SectionsPresenter();
    }

    @Override
    protected int onGetLayoutId() {
        return R.layout.activity_sections_detail;
    }

    @Override
    protected void onInitView() {
        LogUtil.d(TAG, "onInitView");
        initColor();
        initToolbar();
        initSuperSwipeRefreshLayout();
        initRecyclerView();
    }

    private void initColor() {
        LogUtil.d(TAG, "initColor");
        StatusBarUtil.setStatusBarColor(this, getResources().getColor(AppConstant.COLOR_STATUS_BAR_RED));
        mLoadingView.setColor(getResources().getColor(AppConstant.COLOR_STATUS_BAR_RED));
    }

    private void initToolbar() {
        LogUtil.d(TAG, "initToolbar");
        mToolbar.setTitleTextColor(Color.WHITE);
        mToolbar.setNavigationIcon(R.drawable.ic_back_light);
        mToolbar.setNavigationOnClickListener(v -> onBackPressed());
    }

    private void initSuperSwipeRefreshLayout() {
        LogUtil.d(TAG, "initSuperSwipeRefreshLayout");
        mHeaderView = new HeaderView(getApplicationContext());
        mSuperSwipeRefreshLayout.setHeaderView(mHeaderView);
        mSuperSwipeRefreshLayout.setOnPullRefreshListener(this);
    }

    private void initRecyclerView() {
        LogUtil.d(TAG, "initRecyclerView");
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mLoadMoreScrollListener = new LoadMoreScrollListener();
        mLoadMoreScrollListener.setLinearLayoutManager(linearLayoutManager);
        mRecyclerView.addItemDecoration(new DividerItemDecoration(getApplicationContext(), DividerItemDecoration.VERTICAL));
        mRecyclerView.addOnScrollListener(mLoadMoreScrollListener);
        mSectionsDetailAdapter = new SectionsDetailAdapter(getApplicationContext());
        mSectionsDetailAdapter.setOnItemClickListener(position -> ActivityHelper.startDetailActivity(this, mStoryIds,
                mSectionsDetailAdapter.getData().get(position).getId()));
        mRecyclerView.setAdapter(mSectionsDetailAdapter);
    }

    @Override
    protected void onInitData() {
        LogUtil.d(TAG, "onInitData");
        mStoryIds = new ArrayList<>();
        handleIntent(getIntent());
    }

    private void handleIntent(Intent intent) {
        LogUtil.d(TAG, "handleIntent : intent = " + (intent == null ? "Null" : "Not Null"));
        if (intent == null) {
            return;
        }
        String title = intent.getStringExtra(AppConstant.EXTRA_TITLE);
        if (!TextUtils.isEmpty(title)) {
            mToolbar.setTitle(title);
        }
        mId = intent.getIntExtra(AppConstant.EXTRA_ID, -1);
        if (mId != -1) {
            presenter.getSectionsDetailInfo(bindToLifecycle(), mId);
        }
    }

    @Override
    public void OnSectionsInfo(SectionsInfo sectionsInfo) {

    }

    @Override
    public void onSectionsDetailInfo(SectionsDetailInfo sectionsDetailInfo) {
        LogUtil.d(TAG, "onSectionsDetailInfo : sectionsDetailInfo = " + sectionsDetailInfo);
        if (sectionsDetailInfo == null) {
            return;
        }
        if (mLoading != null && mLoading.getVisibility() == View.VISIBLE) {
            mLoading.setVisibility(View.GONE);
        }
        if (mSuperSwipeRefreshLayout.isRefreshing()) {
            mSuperSwipeRefreshLayout.setRefreshing(false);
        }
        mStoryIds.clear();
        mStoryIds.addAll(sectionsDetailInfo.getStoryIds());
        mSectionsDetailAdapter.setData(sectionsDetailInfo.getStories());
        mTimestamp = sectionsDetailInfo.getTimestamp();
    }

    @Override
    public void onBeforeSectionsDetailInfo(SectionsDetailInfo sectionsDetailInfo) {
        LogUtil.d(TAG, "onBeforeSectionsDetailInfo : sectionsDetailInfo = " + sectionsDetailInfo);
        if (sectionsDetailInfo == null) {
            return;
        }
        if (mLoadMoreScrollListener.isLoading()) {
            mLoadMoreScrollListener.setLoading(false);
        }
        mStoryIds.addAll(sectionsDetailInfo.getStoryIds());
        mSectionsDetailAdapter.addData(sectionsDetailInfo.getStories());
        mTimestamp = sectionsDetailInfo.getTimestamp();
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
                .subscribe(aLong -> presenter.getSectionsDetailInfo(bindToLifecycle(), mId));
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

    private class LoadMoreScrollListener extends OnLoadMoreScrollListener {

        @Override
        protected void onLoadMore() {
            LogUtil.d(TAG, "onLoadMore");
            presenter.getBeforeSectionsDetailInfo(bindToLifecycle(), mId, mTimestamp);
        }
    }
}
