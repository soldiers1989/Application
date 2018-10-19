package com.chad.hlife.ui.activity;

import android.content.Intent;
import android.graphics.Color;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;

import com.chad.hlife.R;
import com.chad.hlife.app.AppConstant;
import com.chad.hlife.app.config.MobConfig;
import com.chad.hlife.entity.mob.CarBrandInfo;
import com.chad.hlife.entity.mob.CarTypeInfo;
import com.chad.hlife.mvp.presenter.car.CarPresenter;
import com.chad.hlife.mvp.view.ICarView;
import com.chad.hlife.ui.adapter.CarTypeAdapter;
import com.chad.hlife.ui.base.BaseMvpAppCompatActivity;
import com.chad.hlife.ui.view.loading.DoubleCircleLoadingView;
import com.chad.hlife.util.LogUtil;
import com.chad.hlife.util.StatusBarUtil;

import butterknife.BindView;

public class CarActivity extends BaseMvpAppCompatActivity<ICarView, CarPresenter> implements ICarView {

    private static final String TAG = CarActivity.class.getSimpleName();

    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.view_recycler)
    RecyclerView mRecyclerView;
    @BindView(R.id.text_tips)
    AppCompatTextView mTextTips;
    @BindView(R.id.layout_loading)
    ConstraintLayout mLoading;
    @BindView(R.id.view_loading)
    DoubleCircleLoadingView mLoadingView;

    private CarTypeAdapter mCarTypeAdapter;

    @Override
    protected CarPresenter onGetPresenter() {
        return new CarPresenter();
    }

    @Override
    protected int onGetLayoutId() {
        return R.layout.activity_car;
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
        StatusBarUtil.setStatusBarColor(this, getResources().getColor(AppConstant.COLOR_STATUS_BAR_BLUE));
        mLoadingView.setColor(getResources().getColor(AppConstant.COLOR_STATUS_BAR_BLUE));
    }

    private void initToolbar() {
        LogUtil.d(TAG, "initToolbar");
        mToolbar.setTitleTextColor(Color.WHITE);
        mToolbar.setNavigationIcon(R.drawable.ic_back_light);
        mToolbar.setNavigationOnClickListener(v -> onBackPressed());
    }

    private void initRecyclerView() {
        LogUtil.d(TAG, "initRecyclerView");
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mRecyclerView.addItemDecoration(new DividerItemDecoration(getApplicationContext(), DividerItemDecoration.VERTICAL));
        mCarTypeAdapter = new CarTypeAdapter(getApplicationContext());
        mRecyclerView.setAdapter(mCarTypeAdapter);
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
        String name = intent.getStringExtra(AppConstant.EXTRA_NAME);
        if (!TextUtils.isEmpty(name)) {
            presenter.getCarTypeInfo(bindToLifecycle(), MobConfig.APP_KEY, name);
        }
    }
    @Override
    public void onCarBrandInfo(CarBrandInfo carBrandInfo) {

    }

    @Override
    public void onCarTypeInfo(CarTypeInfo carTypeInfo) {
        LogUtil.d(TAG, "onCarTypeInfo : carTypeInfo = " + carTypeInfo);
        if (carTypeInfo == null) {
            return;
        }
        if (mLoading != null && mLoading.getVisibility() == View.VISIBLE) {
            mLoading.setVisibility(View.GONE);
        }
        if (carTypeInfo.getMsg().equals("success")) {
            mCarTypeAdapter.setData(carTypeInfo.getResult());
        } else {
            mTextTips.setVisibility(View.VISIBLE);
            mTextTips.setText(carTypeInfo.getMsg());
        }
    }

    @Override
    public void onError(Object object) {

    }
}
