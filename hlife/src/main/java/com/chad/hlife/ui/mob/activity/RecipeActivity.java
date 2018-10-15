package com.chad.hlife.ui.mob.activity;

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
import com.chad.hlife.app.config.MobConfig;
import com.chad.hlife.entity.mob.RecipeCategoryInfo;
import com.chad.hlife.entity.mob.RecipeDetailInfo;
import com.chad.hlife.mvp.presenter.mob.recipe.RecipePresenter;
import com.chad.hlife.mvp.view.mob.IRecipeView;
import com.chad.hlife.ui.base.BaseMvpAppCompatActivity;
import com.chad.hlife.ui.mob.adapter.RecipeAdapter;
import com.chad.hlife.ui.view.refresh.FooterView;
import com.chad.hlife.ui.view.refresh.HeaderView;
import com.chad.hlife.util.LogUtil;
import com.chad.hlife.util.StatusBarUtil;
import com.github.nuptboyzhb.lib.SuperSwipeRefreshLayout;

import butterknife.BindView;

public class RecipeActivity extends BaseMvpAppCompatActivity<IRecipeView, RecipePresenter>
        implements IRecipeView, SuperSwipeRefreshLayout.OnPullRefreshListener, SuperSwipeRefreshLayout.OnPushLoadMoreListener {

    private static final String TAG = RecipeActivity.class.getSimpleName();

    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.layout_super_swipe_refresh)
    SuperSwipeRefreshLayout mSuperSwipeRefreshLayout;
    @BindView(R.id.view_recycler)
    RecyclerView mRecyclerView;
    @BindView(R.id.layout_loading)
    ConstraintLayout mLoading;

    private RecipeAdapter mRecipeAdapter;

    private String mType;
    private String mParam;
    private int mCurrentPage = 1;

    @Override
    protected RecipePresenter onGetPresenter() {
        return new RecipePresenter();
    }

    @Override
    protected int onGetLayoutId() {
        return R.layout.activity_recipe;
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
        mSuperSwipeRefreshLayout.setHeaderView(new ConstraintLayout(getApplicationContext()));
        mSuperSwipeRefreshLayout.setOnPullRefreshListener(this);
        mSuperSwipeRefreshLayout.setFooterView(new FooterView(getApplicationContext()));
        mSuperSwipeRefreshLayout.setOnPushLoadMoreListener(this);
    }

    private void initRecyclerView() {
        LogUtil.d(TAG, "initRecyclerView");
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mRecyclerView.addItemDecoration(new DividerItemDecoration(getApplicationContext(), DividerItemDecoration.VERTICAL));
        mRecipeAdapter = new RecipeAdapter(getApplicationContext());
        mRecyclerView.setAdapter(mRecipeAdapter);
    }

    @Override
    protected void onInitData() {
        LogUtil.d(TAG, "onInitData");
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
        mType = intent.getStringExtra(AppConstant.EXTRA_TYPE);
        if (!TextUtils.isEmpty(mType)) {
            mParam = intent.getStringExtra(AppConstant.EXTRA_PARAM);
            getRecipeDetailInfo(mType);
        }
    }

    private void getRecipeDetailInfo(String type) {
        LogUtil.d(TAG, "getRecipeDetailInfo : type = " + type);
        if (TextUtils.isEmpty(type)) {
            return;
        }
        if (AppConstant.TYPE_RECIPE_ID.equals(mType)) {
            presenter.getRecipeDetailInfoByCId(bindToLifecycle(), MobConfig.APP_KEY, mParam, mCurrentPage, 20);
        } else if (AppConstant.TYPE_RECIPE_NAME.equals(mType)) {
            presenter.getRecipeDetailInfoByName(bindToLifecycle(), MobConfig.APP_KEY, mParam, mCurrentPage, 20);
        }
    }

    @Override
    public void onRecipeCategoryInfo(RecipeCategoryInfo recipeCategoryInfo) {

    }

    @Override
    public void onRecipeDetailInfo(RecipeDetailInfo recipeDetailInfo) {
        LogUtil.d(TAG, "onRecipeDetailInfo : recipeDetailInfo = " + recipeDetailInfo);
        if (recipeDetailInfo == null) {
            return;
        }
        if (mLoading != null && mLoading.getVisibility() == View.VISIBLE) {
            mLoading.setVisibility(View.GONE);
        }
        mSuperSwipeRefreshLayout.setLoadMore(false);
        mRecipeAdapter.addData(recipeDetailInfo.getResult().getList());
        mCurrentPage ++;
    }

    @Override
    public void onError(Object object) {
        LogUtil.d(TAG, "onError");
    }

    @Override
    public void onRefresh() {
        LogUtil.d(TAG, "onRefresh");
        mSuperSwipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void onPullDistance(int i) {

    }

    @Override
    public void onPullEnable(boolean b) {

    }

    @Override
    public void onLoadMore() {
        LogUtil.d(TAG, "onLoadMore");
        getRecipeDetailInfo(mType);
    }

    @Override
    public void onPushDistance(int i) {

    }

    @Override
    public void onPushEnable(boolean b) {

    }
}
