package com.chad.hlife.ui.zhihu.activity;

import android.content.Intent;
import android.graphics.Color;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;

import com.chad.hlife.R;
import com.chad.hlife.app.AppConstant;
import com.chad.hlife.entity.zhihu.ThemesDetailInfo;
import com.chad.hlife.entity.zhihu.ThemesInfo;
import com.chad.hlife.helper.ActivityHelper;
import com.chad.hlife.mvp.presenter.zhihu.themes.ThemesPresenter;
import com.chad.hlife.mvp.view.zhihu.IThemesView;
import com.chad.hlife.ui.base.BaseMvpAppCompatActivity;
import com.chad.hlife.ui.view.recycler.HeaderViewAdapter;
import com.chad.hlife.ui.view.refresh.HeaderView;
import com.chad.hlife.ui.view.theme.ThemesDetailHeaderView;
import com.chad.hlife.ui.zhihu.adapter.ThemesDetailAdapter;
import com.chad.hlife.util.LogUtil;
import com.chad.hlife.util.StatusBarUtil;
import com.github.nuptboyzhb.lib.SuperSwipeRefreshLayout;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;

public class ThemesDetailActivity extends BaseMvpAppCompatActivity<IThemesView, ThemesPresenter>
        implements IThemesView, SuperSwipeRefreshLayout.OnPullRefreshListener {

    private static final String TAG = ThemesDetailActivity.class.getSimpleName();

    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.layout_super_swipe_refresh)
    SuperSwipeRefreshLayout mSuperSwipeRefreshLayout;
    @BindView(R.id.view_recycler)
    RecyclerView mRecyclerView;
    @BindView(R.id.layout_loading)
    ConstraintLayout mLoading;

    private HeaderView mHeaderView;
    private ThemesDetailHeaderView mThemesDetailHeaderView;

    private ThemesDetailAdapter mThemesDetailAdapter;

    private ArrayList<Integer> mStoryIds;

    private int mId;

    @Override
    protected ThemesPresenter onGetPresenter() {
        return new ThemesPresenter();
    }

    @Override
    protected int onGetLayoutId() {
        return R.layout.activity_themes_detail;
    }

    @Override
    protected void onInitView() {
        LogUtil.d(TAG, "onInitView");
        StatusBarUtil.setStatusBarColor(this, getResources().getColor(AppConstant.COLOR_STATUS_BAR_BLUE));
        initToolbar();
        initSuperSwipeRefreshLayout();
        initRecyclerView();
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
        mThemesDetailAdapter = new ThemesDetailAdapter(getApplicationContext());
        mThemesDetailAdapter.setOnItemClickListener(position ->
                ActivityHelper.startDetailActivity(this, mStoryIds,
                        mThemesDetailAdapter.getData().get(position).getId())
        );
        mThemesDetailHeaderView = new ThemesDetailHeaderView(getApplicationContext());
        HeaderViewAdapter headerViewAdapter = new HeaderViewAdapter(mThemesDetailAdapter);
        headerViewAdapter.addHeaderView(mThemesDetailHeaderView);
        mRecyclerView.setAdapter(headerViewAdapter);
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
            presenter.getThemesDetailInfo(bindToLifecycle(), mId);
        }
    }

    @Override
    public void onThemesInfo(ThemesInfo themesInfo) {

    }

    @Override
    public void onThemesDetailInfo(ThemesDetailInfo themesDetailInfo) {
        LogUtil.d(TAG, "ThemesDetailInfo : themesDetailInfo = " + themesDetailInfo);
        if (themesDetailInfo == null) {
            return;
        }
        if (mLoading != null && mLoading.getVisibility() == View.VISIBLE) {
            mLoading.setVisibility(View.GONE);
        }
        if (mSuperSwipeRefreshLayout.isRefreshing()) {
            mSuperSwipeRefreshLayout.setRefreshing(false);
        }
        mThemesDetailAdapter.setData(themesDetailInfo.getStories());
        mThemesDetailHeaderView.setImagePreview(themesDetailInfo.getImage());
        mThemesDetailHeaderView.setDescription(themesDetailInfo.getDescription());
        mThemesDetailHeaderView.setData(themesDetailInfo.getEditors());
        mStoryIds.clear();
        mStoryIds.addAll(themesDetailInfo.getStoryIds());
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
                .subscribe(aLong -> presenter.getThemesDetailInfo(bindToLifecycle(), mId));
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
}
