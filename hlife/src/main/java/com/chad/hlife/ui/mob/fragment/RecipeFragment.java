package com.chad.hlife.ui.mob.fragment;

import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.View;

import com.chad.hlife.R;
import com.chad.hlife.app.AppConstant;
import com.chad.hlife.app.config.MobConfig;
import com.chad.hlife.entity.mob.RecipeCategoryInfo;
import com.chad.hlife.entity.mob.RecipeDetailInfo;
import com.chad.hlife.helper.ActivityHelper;
import com.chad.hlife.mvp.presenter.mob.recipe.RecipePresenter;
import com.chad.hlife.mvp.view.mob.IRecipeView;
import com.chad.hlife.ui.base.BaseMvpFragment;
import com.chad.hlife.ui.mob.adapter.RecipeMainCategoryAdapter;
import com.chad.hlife.ui.mob.adapter.RecipeSubCategoryAdapter;
import com.chad.hlife.util.LogUtil;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;

public class RecipeFragment extends BaseMvpFragment<IRecipeView, RecipePresenter> implements IRecipeView {

    private static final String TAG = RecipeFragment.class.getSimpleName();

    @BindView(R.id.view_search)
    SearchView mSearchView;
    @BindView(R.id.view_recycler_main)
    RecyclerView mMainRecyclerView;
    @BindView(R.id.view_recycler_sub)
    RecyclerView mSubRecyclerView;
    @BindView(R.id.layout_loading)
    ConstraintLayout mLoading;

    private RecipeMainCategoryAdapter mRecipeMainCategoryAdapter;
    private RecipeSubCategoryAdapter mRecipeSubCategoryAdapter;

    @Override
    protected RecipePresenter onGetPresenter() {
        return new RecipePresenter();
    }

    @Override
    protected int onGetLayoutId() {
        return R.layout.fragment_recipe;
    }

    @Override
    protected void onInitView() {
        LogUtil.d(TAG, "onInitView");
        initSearchView();
        initRecyclerView();
    }

    private void initSearchView() {
        LogUtil.d(TAG, "initSearchView");
        mSearchView.setSubmitButtonEnabled(true);
        mSearchView.setIconified(false);
        mSearchView.onActionViewExpanded();
    }

    private void initRecyclerView() {
        LogUtil.d(TAG, "initRecyclerView");
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mMainRecyclerView.setLayoutManager(linearLayoutManager);
        mMainRecyclerView.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
        mRecipeMainCategoryAdapter = new RecipeMainCategoryAdapter(getContext());
        mRecipeMainCategoryAdapter.setOnItemClickListener(position -> {
            for (int i = 0; i < mMainRecyclerView.getChildCount(); i++) {
                mMainRecyclerView.getChildAt(i).setSelected(false);
            }
            mMainRecyclerView.getChildAt(position).setSelected(true);
            mRecipeSubCategoryAdapter.setData(mRecipeMainCategoryAdapter.getData().get(position).getChilds());
        });
        mMainRecyclerView.setAdapter(mRecipeMainCategoryAdapter);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 2);
        mSubRecyclerView.setLayoutManager(gridLayoutManager);
        mRecipeSubCategoryAdapter = new RecipeSubCategoryAdapter(getContext());
        mRecipeSubCategoryAdapter.setOnItemClickListener(position ->
                ActivityHelper.startRecipeActivity(getActivity(),
                        mRecipeSubCategoryAdapter.getData().get(position).getCategoryInfo().getName(),
                        AppConstant.TYPE_RECIPE_ID,
                        mRecipeSubCategoryAdapter.getData().get(position).getCategoryInfo().getCtgId())
        );
        mSubRecyclerView.setAdapter(mRecipeSubCategoryAdapter);
    }

    @Override
    protected void onInitData() {
        LogUtil.d(TAG, "onInitView");
        presenter.getRecipeCategoryInfo(bindToLifecycle(), MobConfig.APP_KEY);
    }

    @Override
    public void onRecipeCategoryInfo(RecipeCategoryInfo recipeCategoryInfo) {
        LogUtil.d(TAG, "onRecipeCategoryInfo : recipeCategoryInfo = " + recipeCategoryInfo);
        if (recipeCategoryInfo == null) {
            return;
        }
        mRecipeMainCategoryAdapter.setData(recipeCategoryInfo.getResult().getChilds());
        Observable.timer(100, TimeUnit.MILLISECONDS)
                .compose(bindToLifecycle())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(aLong -> {
                    if (mLoading != null && mLoading.getVisibility() == View.VISIBLE) {
                        mLoading.setVisibility(View.GONE);
                    }
                    mMainRecyclerView.getChildAt(0).setSelected(true);
                    mRecipeSubCategoryAdapter.setData(recipeCategoryInfo.getResult().getChilds().get(0).getChilds());
                });
    }

    @Override
    public void onRecipeDetailInfo(RecipeDetailInfo recipeDetailInfo) {

    }

    @Override
    public void onError(Object object) {
        LogUtil.d(TAG, "onError");
    }
}
