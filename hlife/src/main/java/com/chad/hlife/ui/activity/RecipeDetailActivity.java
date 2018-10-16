package com.chad.hlife.ui.activity;

import android.content.Intent;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;

import com.chad.hlife.R;
import com.chad.hlife.app.AppConstant;
import com.chad.hlife.entity.mob.RecipeDetailInfo;
import com.chad.hlife.eventbus.EventMessage;
import com.chad.hlife.eventbus.EventType;
import com.chad.hlife.glide.CustomGlideModule;
import com.chad.hlife.ui.adapter.RecipeIngredientDetailAdapter;
import com.chad.hlife.ui.adapter.RecipeMethodDetailAdapter;
import com.chad.hlife.ui.base.BaseRxAppCompatActivity;
import com.chad.hlife.ui.view.loading.DoubleCircleLoadingView;
import com.chad.hlife.util.JsonUtil;
import com.chad.hlife.util.LogUtil;
import com.chad.hlife.util.StatusBarUtil;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;

public class RecipeDetailActivity extends BaseRxAppCompatActivity {

    private static final String TAG = RecipeDetailActivity.class.getSimpleName();

    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.image_preview)
    AppCompatImageView mImagePreview;
    @BindView(R.id.text_title)
    AppCompatTextView mTextTitle;
    @BindView(R.id.text_sumary)
    AppCompatTextView mTextSummary;
    @BindView(R.id.view_recycler_ingredient)
    RecyclerView mIngredientRecyclerView;
    @BindView(R.id.view_recycler_method)
    RecyclerView mMethodRecyclerView;
    @BindView(R.id.layout_loading)
    ConstraintLayout mLoading;
    @BindView(R.id.view_loading)
    DoubleCircleLoadingView mLoadingView;

    private RecipeIngredientDetailAdapter mRecipeIngredientDetailAdapter;
    private RecipeMethodDetailAdapter mRecipeMethodDetailAdapter;

    @Override
    protected int onGetLayoutId() {
        return R.layout.activity_recipe_detail;
    }

    @Override
    protected void onInitView() {
        LogUtil.d(TAG, "onInitView");
        initColor();
        initToolbar();
        initRecyclerView();
    }

    private void initColor() {
        LogUtil.d(TAG, "initColor");
        StatusBarUtil.setStatusBarColor(this, getResources().getColor(AppConstant.COLOR_STATUS_BAR_BLACK));
        mLoadingView.setColor(getResources().getColor(AppConstant.COLOR_STATUS_BAR_BLUE));
    }

    private void initToolbar() {
        LogUtil.d(TAG, "initToolbar");
        mToolbar.setNavigationIcon(R.drawable.ic_close_dark);
        mToolbar.setNavigationOnClickListener(v -> onBackPressed());
    }

    private void initRecyclerView() {
        LogUtil.d(TAG, "initRecyclerView");
        LinearLayoutManager ingredientLinearLayoutManager = new LinearLayoutManager(getApplicationContext());
        ingredientLinearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mIngredientRecyclerView.setLayoutManager(ingredientLinearLayoutManager);
        mRecipeIngredientDetailAdapter = new RecipeIngredientDetailAdapter(getApplicationContext());
        mIngredientRecyclerView.setAdapter(mRecipeIngredientDetailAdapter);
        LinearLayoutManager methodLinearLayoutManager = new LinearLayoutManager(getApplicationContext());
        methodLinearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mMethodRecyclerView.setLayoutManager(methodLinearLayoutManager);
        mRecipeMethodDetailAdapter = new RecipeMethodDetailAdapter(getApplicationContext());
        mMethodRecyclerView.setAdapter(mRecipeMethodDetailAdapter);
    }

    @Override
    protected void onInitData() {
        LogUtil.d(TAG, "onInitData");
        EventBus.getDefault().register(this);
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
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onRecipe(EventMessage eventMessage) {
        LogUtil.d(TAG, "onRecipe : eventMessage = " + (eventMessage == null ? null : "Not Null"));
        if (eventMessage == null) {
            return;
        }
        switch (eventMessage.getType()) {
            case EventType.TYPE_RECIPE:
                RecipeDetailInfo.Recipe recipe = (RecipeDetailInfo.Recipe) eventMessage.getObject();
                if (mLoading != null && mLoading.getVisibility() == View.VISIBLE) {
                    mLoading.setVisibility(View.GONE);
                }
                CustomGlideModule.loadCenterCrop(getApplicationContext(), recipe.getImg(), mImagePreview);
                mTextTitle.setText(recipe.getTitle());
                mTextSummary.setText(recipe.getSumary().isEmpty() ? getString(R.string.no_summary) : recipe.getSumary());
                mRecipeIngredientDetailAdapter.setData(JsonUtil.formatList(recipe.getIngredients()));
                mRecipeMethodDetailAdapter.setData(JsonUtil.formatRecipeMethod(recipe.getMethod()));
                break;
            default:
                break;
        }
    }

    @Override
    protected void onDestroy() {
        LogUtil.d(TAG, "onDestroy");
        EventBus.getDefault().unregister(this);
        super.onDestroy();
    }
}
