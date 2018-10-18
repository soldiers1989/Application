package com.chad.hlife.ui.fragment;

import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.hlife.R;
import com.chad.hlife.app.AppConstant;
import com.chad.hlife.app.config.MobConfig;
import com.chad.hlife.entity.mob.CarBrandInfo;
import com.chad.hlife.mvp.presenter.car.CarPresenter;
import com.chad.hlife.mvp.view.ICarView;
import com.chad.hlife.ui.adapter.CarBrandAdapter;
import com.chad.hlife.ui.base.BaseMvpFragment;
import com.chad.hlife.ui.view.LetterIndexView;
import com.chad.hlife.ui.view.loading.DoubleCircleLoadingView;
import com.chad.hlife.util.LogUtil;

import butterknife.BindView;

public class CarFragment extends BaseMvpFragment<ICarView, CarPresenter> implements ICarView {

    private static final String TAG = CarFragment.class.getSimpleName();

    @BindView(R.id.view_recycler)
    RecyclerView mRecyclerView;
    @Nullable
    @BindView(R.id.view_letter_index)
    LetterIndexView mLetterIndexView;
    @BindView(R.id.layout_loading)
    ConstraintLayout mLoading;
    @BindView(R.id.view_loading)
    DoubleCircleLoadingView mLoadingView;

    private CarBrandAdapter mCarBrandAdapter;

    @Override
    protected CarPresenter onGetPresenter() {
        return new CarPresenter();
    }

    @Override
    protected int onGetLayoutId() {
        return R.layout.fragment_car;
    }

    @Override
    protected void onInitView() {
        LogUtil.d(TAG, "initViews");
        initColor();
        initRecyclerView();
        initLetterIndexView();
    }

    private void initColor() {
        LogUtil.d(TAG, "initColor");
        mLoadingView.setColor(getResources().getColor(AppConstant.COLOR_STATUS_BAR_BLUE));
    }

    private void initRecyclerView() {
        LogUtil.d(TAG, "initRecyclerView");
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mRecyclerView.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
        mCarBrandAdapter = new CarBrandAdapter(getContext());
        mRecyclerView.setAdapter(mCarBrandAdapter);
    }

    private void initLetterIndexView() {
        LogUtil.d(TAG, "initLetterIndexView");

    }

    @Override
    protected void onInitData() {
        LogUtil.d(TAG, "onInitData");
        presenter.getCarBrandInfo(bindToLifecycle(), MobConfig.APP_KEY);
    }

    @Override
    public void onCarBrandInfo(CarBrandInfo carBrandInfo) {
        LogUtil.d(TAG, "onCarBrandInfo : carBrandInfo = " + carBrandInfo);
        if (carBrandInfo == null) {
            return;
        }
        if (mLoading != null && mLoading.getVisibility() == View.VISIBLE) {
            mLoading.setVisibility(View.GONE);
        }
        mCarBrandAdapter.setData(carBrandInfo.getResult());
    }

    @Override
    public void onError(Object object) {
        LogUtil.d(TAG, "onError");
    }
}
