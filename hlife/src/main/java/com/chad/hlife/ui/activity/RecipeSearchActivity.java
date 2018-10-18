package com.chad.hlife.ui.activity;

import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

import com.chad.hlife.R;
import com.chad.hlife.app.AppConstant;
import com.chad.hlife.app.config.MobConfig;
import com.chad.hlife.entity.mob.RecipeCategoryInfo;
import com.chad.hlife.entity.mob.RecipeDetailInfo;
import com.chad.hlife.eventbus.EventMessage;
import com.chad.hlife.eventbus.EventType;
import com.chad.hlife.helper.ActivityHelper;
import com.chad.hlife.mvp.presenter.recipe.RecipePresenter;
import com.chad.hlife.mvp.view.IRecipeView;
import com.chad.hlife.ui.adapter.RecipeAdapter;
import com.chad.hlife.ui.base.BaseMvpAppCompatActivity;
import com.chad.hlife.ui.view.dialog.ProgressDialog;
import com.chad.hlife.ui.view.refresh.FooterView;
import com.chad.hlife.util.LogUtil;
import com.chad.hlife.util.StatusBarUtil;
import com.github.nuptboyzhb.lib.SuperSwipeRefreshLayout;

import org.greenrobot.eventbus.EventBus;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;

public class RecipeSearchActivity extends BaseMvpAppCompatActivity<IRecipeView, RecipePresenter>
        implements IRecipeView, SearchView.OnQueryTextListener,
        SuperSwipeRefreshLayout.OnPullRefreshListener, SuperSwipeRefreshLayout.OnPushLoadMoreListener {

    private static final String TAG = RecipeSearchActivity.class.getSimpleName();

    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.layout_super_swipe_refresh)
    SuperSwipeRefreshLayout mSuperSwipeRefreshLayout;
    @BindView(R.id.view_recycler)
    RecyclerView mRecyclerView;
    @BindView(R.id.dialog_progress)
    ProgressDialog mProgressDialog;

    private SearchView mSearchView;
    private FooterView mFooterView;

    private RecipeAdapter mRecipeAdapter;
    private RecipeDetailInfo.Recipe mRecipe;

    private String mRecipeName;

    private int mCurrentPage = 1;

    @Override
    protected RecipePresenter onGetPresenter() {
        return new RecipePresenter();
    }

    @Override
    protected int onGetLayoutId() {
        return R.layout.activity_recipe_search;
    }

    @Override
    protected void onInitView() {
        LogUtil.d(TAG, "onInitView");
        initColor();
        initToolbar();
        initSearchView(mToolbar.findViewById(R.id.item_search));
        initSuperSwipeRefreshLayout();
        initRecyclerView();
    }

    private void initColor() {
        LogUtil.d(TAG, "initColor");
        StatusBarUtil.setStatusBarColor(this, getResources().getColor(AppConstant.COLOR_STATUS_BAR_BLUE));
    }

    private void initToolbar() {
        LogUtil.d(TAG, "initToolbar");
        mToolbar.inflateMenu(R.menu.menu_recipe_search);
        mToolbar.setNavigationIcon(R.drawable.ic_back_light);
        mToolbar.setNavigationOnClickListener(v -> onBackPressed());
    }

    private void initSearchView(SearchView searchView) {
        LogUtil.d(TAG, "initSearchView : searchView = " + searchView);
        if (searchView == null) {
            return;
        }
        mSearchView = searchView;
        mSearchView.setQueryHint(getString(R.string.input_dish_name));
        mSearchView.setBackgroundResource(R.drawable.bg_search_shape);
        mSearchView.onActionViewExpanded();
        mSearchView.setSubmitButtonEnabled(true);
        ((SearchView.SearchAutoComplete) mSearchView
                .findViewById(android.support.v7.appcompat.R.id.search_src_text)).setTextSize(16);
        mSearchView.setOnQueryTextListener(this);
    }

    private void initSuperSwipeRefreshLayout() {
        LogUtil.d(TAG, "initSuperSwipeRefreshLayout");
        mSuperSwipeRefreshLayout.setHeaderView(new ConstraintLayout(getApplicationContext()));
        mSuperSwipeRefreshLayout.setOnPullRefreshListener(this);
        mFooterView = new FooterView(getApplicationContext());
        mSuperSwipeRefreshLayout.setFooterView(mFooterView);
        mSuperSwipeRefreshLayout.setOnPushLoadMoreListener(this);
    }

    private void initRecyclerView() {
        LogUtil.d(TAG, "initRecyclerView");
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mRecyclerView.addItemDecoration(new DividerItemDecoration(getApplicationContext(), DividerItemDecoration.VERTICAL));
        mRecipeAdapter = new RecipeAdapter(getApplicationContext());
        mRecipeAdapter.setOnItemClickListener(position -> {
            mRecipe = mRecipeAdapter.getData().get(position).getRecipe();
            ActivityHelper.startRecipeDetailActivity(this, mRecipeAdapter.getData().get(position).getName());
        });
        mRecyclerView.setAdapter(mRecipeAdapter);
    }

    @Override
    protected void onInitData() {
        LogUtil.d(TAG, "onInitData");
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
        showProgressDialog(false);
        mSuperSwipeRefreshLayout.setLoadMore(false);
        mFooterView.setLoading(false);
        if (recipeDetailInfo.getMsg().equals("success")) {
            mRecipeAdapter.addData(recipeDetailInfo.getResult().getList());
            mRecipeName = mSearchView.getQuery().toString();
            mCurrentPage++;
        } else {
            resetData();
            Toast.makeText(getApplicationContext(), recipeDetailInfo.getMsg(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onError(Object object) {
        LogUtil.d(TAG, "onError");
    }

    @Override
    public boolean onQueryTextSubmit(String text) {
        LogUtil.d(TAG, "onQueryTextSubmit : text = " + text);
        mSearchView.clearFocus();
        showProgressDialog(true);
        Observable.timer(1, TimeUnit.SECONDS)
                .compose(bindToLifecycle())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(aLong -> {
                    resetData();
                    presenter.getRecipeDetailInfoByName(bindToLifecycle(), MobConfig.APP_KEY, text, mCurrentPage, 20);
                });
        return true;
    }

    @Override
    public boolean onQueryTextChange(String s) {
        return false;
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
        presenter.getRecipeDetailInfoByName(bindToLifecycle(), MobConfig.APP_KEY, mRecipeName, mCurrentPage, 20);
    }

    @Override
    public void onPushDistance(int i) {

    }

    @Override
    public void onPushEnable(boolean enable) {
        LogUtil.d(TAG, "onPushEnable : enable = " + enable);
        if (!enable) {
            mFooterView.setLoading(true);
        }
    }

    private void resetData() {
        LogUtil.d(TAG, "resetData");
        mRecipeAdapter.setData(null);
        mRecipeName = null;
        mCurrentPage = 1;
    }

    private void showProgressDialog(boolean isShow) {
        LogUtil.d(TAG, "showProgressDialog : isShow = " + isShow);
        if (isShow) {
            mProgressDialog.setTitle(getString(R.string.querying));
            mProgressDialog.show();
        } else {
            mProgressDialog.dismiss();
        }
    }

    @Override
    protected void onStop() {
        LogUtil.d(TAG, "onStop");
        EventBus.getDefault().post(new EventMessage(EventType.TYPE_RECIPE, mRecipe));
        super.onStop();
    }
}
