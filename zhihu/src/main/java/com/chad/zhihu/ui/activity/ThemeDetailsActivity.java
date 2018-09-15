package com.chad.zhihu.ui.activity;

import android.content.Intent;
import android.graphics.Color;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.chad.zhihu.R;
import com.chad.zhihu.app.Constant;
import com.chad.zhihu.entity.ThemeDetailsInfo;
import com.chad.zhihu.entity.ThemesInfo;
import com.chad.zhihu.hepler.ActivityHelper;
import com.chad.zhihu.mvp.zhihu.presenter.themes.ThemesPresenter;
import com.chad.zhihu.mvp.zhihu.view.IThemesView;
import com.chad.zhihu.ui.adapter.ThemeDetailsAdapter;
import com.chad.zhihu.ui.base.BaseMvpRxAppCompatActivity;
import com.chad.zhihu.ui.view.recycler.HeaderViewAdapter;
import com.chad.zhihu.ui.view.theme.ThemeDetailsHeaderView;
import com.chad.zhihu.util.ColorUtil;
import com.chad.zhihu.util.LogUtil;
import com.chad.zhihu.util.SystemStatusBarUtil;

import java.util.ArrayList;

import butterknife.BindView;

public class ThemeDetailsActivity extends BaseMvpRxAppCompatActivity<IThemesView, ThemesPresenter>
        implements IThemesView {

    private static final String TAG = ThemeDetailsActivity.class.getSimpleName();

    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.swipe_refresh)
    SwipeRefreshLayout mSwipeRefresh;
    @BindView(R.id.theme_recycler)
    RecyclerView mThemeDetailsRecycler;

    private LinearLayoutManager mLinearLayoutManager = null;
    private ThemeDetailsAdapter mThemeDetailsAdapter = null;
    private ThemeDetailsHeaderView mThemeDetailsHeaderView = null;
    private HeaderViewAdapter mHeaderViewAdapter = null;
    private ThemeDetailsInfo mThemeDetailsInfo = null;

    private int mId;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_theme_details;
    }

    @Override
    protected ThemesPresenter getPresenter() {
        return new ThemesPresenter();
    }

    @Override
    protected void initViews() {
        LogUtil.d(TAG, "initViews");
        SystemStatusBarUtil.setStatusBarColor(this,
                ColorUtil.findRgbById(this, R.color.colorStatusBar));
        initToolbar();
        initSwipeRefresh();
        initThemeDetailsRecycler();
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
            presenter.getThemeDetailsInfo(bindToLifecycle(), mId);
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
            presenter.getThemeDetailsInfo(bindToLifecycle(), mId)
        );
    }

    private void initThemeDetailsRecycler() {
        LogUtil.d(TAG, "initThemeDetailsRecycler");
        mLinearLayoutManager = new LinearLayoutManager(this);

        mThemeDetailsAdapter = new ThemeDetailsAdapter(this);
        mThemeDetailsAdapter.setOnItemClickListener(position ->
            ActivityHelper.startDetailsActivity(this,
                    (ArrayList<Integer>) mThemeDetailsInfo.getStoryIds(),
                    mThemeDetailsInfo.getStories().get(position).getId())
        );

        mThemeDetailsHeaderView = new ThemeDetailsHeaderView(this);
        mThemeDetailsHeaderView.setOnEditorItemClickListener(position -> {
            // TODO: 2018/9/9
        });


        mHeaderViewAdapter = new HeaderViewAdapter(mThemeDetailsAdapter);
        mHeaderViewAdapter.addHeaderView(mThemeDetailsHeaderView);

        mThemeDetailsRecycler.setHasFixedSize(true);
        mThemeDetailsRecycler.setLayoutManager(mLinearLayoutManager);
        mThemeDetailsRecycler.setAdapter(mHeaderViewAdapter);
    }

    @Override
    public void onThemesInfo(ThemesInfo themesInfo) {

    }

    @Override
    public void onThemeDetailsInfo(ThemeDetailsInfo themeDetailsInfo) {
        LogUtil.d(TAG, "onThemeDetailsInfo : themeDetailsInfo = " + themeDetailsInfo);
        if (themeDetailsInfo == null) {
            return;
        }
        mThemeDetailsInfo = themeDetailsInfo;
        mSwipeRefresh.setRefreshing(false);
        mThemeDetailsAdapter.setData(themeDetailsInfo.getStories());

        mThemeDetailsHeaderView.setImagePreview(themeDetailsInfo.getImage());
        mThemeDetailsHeaderView.setDescription(themeDetailsInfo.getDescription());
        mThemeDetailsHeaderView.setData(themeDetailsInfo.getEditors());
    }

    @Override
    public void onError(String msg) {
        LogUtil.d(TAG, "onError : msg = " + msg);
        mSwipeRefresh.setRefreshing(false);
    }
}
