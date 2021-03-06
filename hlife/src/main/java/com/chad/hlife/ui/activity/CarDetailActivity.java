package com.chad.hlife.ui.activity;

import android.content.Intent;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;

import com.chad.hlife.R;
import com.chad.hlife.app.AppConstant;
import com.chad.hlife.app.config.MobConfig;
import com.chad.hlife.entity.mob.CarBrandInfo;
import com.chad.hlife.entity.mob.CarDetailInfo;
import com.chad.hlife.entity.mob.CarTypeInfo;
import com.chad.hlife.glide.CustomGlideModule;
import com.chad.hlife.mvp.presenter.car.CarPresenter;
import com.chad.hlife.mvp.view.ICarView;
import com.chad.hlife.ui.adapter.CarDetailAdapter;
import com.chad.hlife.ui.base.BaseMvpAppCompatActivity;
import com.chad.hlife.ui.view.NestedExpandableListView;
import com.chad.hlife.ui.view.loading.DoubleCircleLoadingView;
import com.chad.hlife.util.LogUtil;
import com.chad.hlife.util.StatusBarUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class CarDetailActivity extends BaseMvpAppCompatActivity<ICarView, CarPresenter>
        implements ICarView {

    private static final String TAG = CarDetailActivity.class.getSimpleName();

    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.image_preview)
    AppCompatImageView mImagePreview;
    @BindView(R.id.view_expandable_list)
    NestedExpandableListView mExpandableListView;
    @BindView(R.id.layout_loading)
    ConstraintLayout mLoading;
    @BindView(R.id.view_loading)
    DoubleCircleLoadingView mLoadingView;

    private CarDetailAdapter mCarDetailAdapter;

    @Override
    protected CarPresenter onGetPresenter() {
        return new CarPresenter();
    }

    @Override
    protected int onGetLayoutId() {
        return R.layout.activity_car_detail;
    }

    @Override
    protected void onInitView() {
        LogUtil.d(TAG, "onInitView");
        initColor();
        initToolbar();
        initExpandableListView();
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

    private void initExpandableListView() {
        LogUtil.d(TAG, "initExpandableListView");
        mExpandableListView.setOnGroupClickListener((parent, v, groupPosition, id) -> false);
        mCarDetailAdapter = new CarDetailAdapter(getApplicationContext());
        mCarDetailAdapter.setGroupData(getResources().getStringArray(R.array.car));
        mExpandableListView.setAdapter(mCarDetailAdapter);
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
        String carId = intent.getStringExtra(AppConstant.EXTRA_ID);
        if (!TextUtils.isEmpty(carId)) {
            presenter.getCarDetailInfo(bindToLifecycle(), MobConfig.APP_KEY, carId);
        }
    }

    @Override
    public void onCarBrandInfo(CarBrandInfo carBrandInfo) {

    }

    @Override
    public void onCarTypeInfo(CarTypeInfo carTypeInfo) {

    }

    @Override
    public void onCarDetailInfo(CarDetailInfo carDetailInfo) {
        LogUtil.d(TAG, "onCarDetailInfo : carDetailInfo = " + carDetailInfo);
        if (carDetailInfo == null) {
            return;
        }
        if (mLoading != null && mLoading.getVisibility() == View.VISIBLE) {
            mLoading.setVisibility(View.GONE);
        }
        CustomGlideModule.loadCenterCrop(getApplicationContext(),
                carDetailInfo.getResult().get(0).getCarImage(), mImagePreview);
        CarDetailInfo.Result result = carDetailInfo.getResult().get(0);
        List<List<CarDetailInfo.Config>> lists = new ArrayList<>();
        lists.add(result.getBaseInfo());
        lists.add(result.getCarbody());
        lists.add(result.getEngine());
        lists.add(result.getChassis());
        lists.add(result.getWheelInfo());
        lists.add(result.getTransmission());
        lists.add(result.getSafetyDevice());
        lists.add(result.getControlConfig());
        lists.add(result.getTechConfig());
        lists.add(result.getExterConfig());
        lists.add(result.getInterConfig());
        lists.add(result.getSeatConfig());
        lists.add(result.getMediaConfig());
        lists.add(result.getLightConfig());
        lists.add(result.getGlassConfig());
        lists.add(result.getAirConfig());
        mCarDetailAdapter.setChildData(lists);
    }

    @Override
    public void onError(Object object) {
        LogUtil.d(TAG, "onError");
    }
}
