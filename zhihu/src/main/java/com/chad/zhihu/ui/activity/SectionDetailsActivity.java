package com.chad.zhihu.ui.activity;

import android.content.Intent;
import android.graphics.Color;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.chad.zhihu.R;
import com.chad.zhihu.app.Constant;
import com.chad.zhihu.entity.zhihu.SectionDetailsInfo;
import com.chad.zhihu.entity.zhihu.SectionsInfo;
import com.chad.zhihu.mvp.zhihu.presenter.sections.SectionsPresenter;
import com.chad.zhihu.mvp.zhihu.view.ISectionsView;
import com.chad.zhihu.ui.base.BaseMvpRxAppCompatActivity;
import com.chad.zhihu.util.LogUtil;

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

    private SectionDetailsInfo mSectionDetailsInfo = null;

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
        initToolbar();
        initSwipeRefresh();
    }

    @Override
    protected void initData() {
        LogUtil.d(TAG, "initData");
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
        mSwipeRefresh.setOnRefreshListener(() -> {
            presenter.getSectionDetailsInfo(bindToLifecycle(), mId);
        });
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
        mSwipeRefresh.setRefreshing(false);
    }

    @Override
    public void onError(String msg) {
        LogUtil.d(TAG, "onError : msg = " + msg);
    }
}
