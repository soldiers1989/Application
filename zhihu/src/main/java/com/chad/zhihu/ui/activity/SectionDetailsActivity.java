package com.chad.zhihu.ui.activity;

import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.chad.zhihu.R;
import com.chad.zhihu.app.Constant;
import com.chad.zhihu.entity.SectionDetailsInfo;
import com.chad.zhihu.entity.SectionsInfo;
import com.chad.zhihu.hepler.ActivityHelper;
import com.chad.zhihu.mvp.zhihu.presenter.sections.SectionsPresenter;
import com.chad.zhihu.mvp.zhihu.view.ISectionsView;
import com.chad.zhihu.ui.adapter.SectionDetailsAdapter;
import com.chad.zhihu.ui.base.BaseMvpRxAppCompatActivity;
import com.chad.zhihu.ui.view.recycler.OnLoadMoreScrollListener;
import com.chad.zhihu.util.ColorUtil;
import com.chad.zhihu.util.LogUtil;
import com.chad.zhihu.util.SystemStatusBarUtil;

import java.util.ArrayList;

import butterknife.BindView;

public class SectionDetailsActivity extends BaseMvpRxAppCompatActivity<ISectionsView, SectionsPresenter>
        implements ISectionsView {

    private static final String TAG = SectionDetailsActivity.class.getSimpleName();

    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.swipe_refresh)
    SwipeRefreshLayout mSwipeRefresh;
    @BindView(R.id.section_recycler)
    RecyclerView mSectionDetailsRecycler;

    private LinearLayoutManager mLinearLayoutManager = null;
    private LoadMoreScrollListener mLoadMoreScrollListener = null;
    private SectionDetailsAdapter mSectionDetailsAdapter = null;
    private SectionDetailsInfo mSectionDetailsInfo = null;

    private ArrayList<Integer> mStoryIds;

    private int mId;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_section_details;
    }

    @Override
    protected SectionsPresenter getPresenter() {
        return new SectionsPresenter();
    }

    @Override
    protected void initViews() {
        LogUtil.d(TAG, "initViews");
        SystemStatusBarUtil.setStatusBarColor(this,
                ColorUtil.findRgbById(this, R.color.colorStatusBar));
        initToolbar();
        initSwipeRefresh();
        initSectionDetailsRecycler();
    }

    @Override
    protected void initData() {
        LogUtil.d(TAG, "initData");
        mStoryIds = new ArrayList<>();
        Intent intent = getIntent();
        if (intent == null) {
            return;
        }
        String title = intent.getStringExtra(Constant.EXTRA_TITLE);
        mId = intent.getIntExtra(Constant.EXTRA_ID, -1);
        mToolbar.setTitle(title);
        mSwipeRefresh.post(() -> {
            mSwipeRefresh.setRefreshing(true);
            presenter.getSectionDetailsInfo(bindToLifecycle(), mId);
        });
    }

    private void initToolbar() {
        LogUtil.d(TAG, "initToolbar");
        mToolbar.setTitleTextColor(Color.WHITE);
        mToolbar.setNavigationIcon(R.drawable.ic_toolbar_back);
        mToolbar.setNavigationOnClickListener(view -> onBackPressed());
    }

    private void initSwipeRefresh() {
        LogUtil.d(TAG, "initSwipeRefresh");
        mSwipeRefresh.setColorSchemeResources(R.color.colorProgress);
        mSwipeRefresh.setOnRefreshListener(() ->
            presenter.getSectionDetailsInfo(bindToLifecycle(), mId));
    }

    private void initSectionDetailsRecycler() {
        LogUtil.d(TAG, "initSectionDetailsRecycler");
        mLinearLayoutManager = new LinearLayoutManager(this);

        mSectionDetailsAdapter = new SectionDetailsAdapter(this);
        mSectionDetailsAdapter.setOnItemClickListener(position -> {
            ActivityHelper.startDetailsActivity(this, mStoryIds, mStoryIds.get(position));
        });

        mLoadMoreScrollListener = new LoadMoreScrollListener();
        mLoadMoreScrollListener.setLinearLayoutManager(mLinearLayoutManager);

        mSectionDetailsRecycler.setLayoutManager(mLinearLayoutManager);
        mSectionDetailsRecycler.setAdapter(mSectionDetailsAdapter);
        mSectionDetailsRecycler.addOnScrollListener(mLoadMoreScrollListener);
    }

    @Override
    public void OnSectionsInfo(SectionsInfo sectionsInfo) {

    }

    @Override
    public void onSectionDetailsInfo(SectionDetailsInfo sectionDetailsInfo) {
        LogUtil.d(TAG, "onSectionDetailsInfo : sectionDetailsInfo = " + sectionDetailsInfo);
        if (sectionDetailsInfo == null) {
            return;
        }
        mSectionDetailsInfo = sectionDetailsInfo;
        mStoryIds.clear();
        mStoryIds.addAll(sectionDetailsInfo.getStoryIds());
        mSwipeRefresh.setRefreshing(false);
        mLoadMoreScrollListener.setLoading(false);
        mSectionDetailsAdapter.setData(sectionDetailsInfo.getStories());
    }

    @Override
    public void onBeforeSectionDetailsInfo(SectionDetailsInfo sectionDetailsInfo) {
        LogUtil.d(TAG, "onBeforeSectionDetailsInfo : sectionDetailsInfo = " + sectionDetailsInfo);
        if (sectionDetailsInfo == null) {
            return;
        }
        mSectionDetailsInfo = sectionDetailsInfo;
        mStoryIds.addAll(sectionDetailsInfo.getStoryIds());
        mSwipeRefresh.setRefreshing(false);
        mLoadMoreScrollListener.setLoading(false);
        mSectionDetailsAdapter.addData(sectionDetailsInfo.getStories());
    }

    @Override
    public void onError(String msg) {
        LogUtil.d(TAG, "onError : msg = " + msg);
        mSwipeRefresh.setRefreshing(false);
        mLoadMoreScrollListener.setLoading(false);
    }

    private class LoadMoreScrollListener extends OnLoadMoreScrollListener {

        @Override
        protected void onLoadMore() {
            LogUtil.d(TAG, "onLoadMore : mSectionDetailsInfo = " + mSectionDetailsInfo);
            if (mSectionDetailsInfo == null || presenter == null) {
                return;
            }
            presenter.getBeforeSectionDetailsInfo(bindToLifecycle(), mId, mSectionDetailsInfo.getTimestamp());
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
