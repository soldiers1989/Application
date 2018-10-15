package com.chad.hlife.ui.mob.fragment;

import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.hlife.R;
import com.chad.hlife.app.config.MobConfig;
import com.chad.hlife.entity.mob.OilPriceInfo;
import com.chad.hlife.mvp.presenter.mob.oilprice.OilPricePresenter;
import com.chad.hlife.mvp.view.mob.IOilPriceView;
import com.chad.hlife.ui.base.BaseMvpFragment;
import com.chad.hlife.ui.mob.adapter.OilPriceAdapter;
import com.chad.hlife.util.LogUtil;

import butterknife.BindView;

public class OilPriceFragment extends BaseMvpFragment<IOilPriceView, OilPricePresenter>
        implements IOilPriceView {

    private static final String TAG = OilPriceFragment.class.getSimpleName();

    @BindView(R.id.view_recycler)
    RecyclerView mRecyclerView;
    @BindView(R.id.layout_loading)
    ConstraintLayout mLoading;

    private OilPriceAdapter mOilPriceAdapter;

    @Override
    protected OilPricePresenter onGetPresenter() {
        return new OilPricePresenter();
    }

    @Override
    protected int onGetLayoutId() {
        return R.layout.fragment_oil_price;
    }

    @Override
    protected void onInitView() {
        LogUtil.d(TAG, "onInitView");
        initRecyclerView();
    }

    private void initRecyclerView() {
        LogUtil.d(TAG, "initRecyclerView");
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mRecyclerView.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
        mOilPriceAdapter = new OilPriceAdapter(getContext());
        mRecyclerView.setAdapter(mOilPriceAdapter);
    }

    @Override
    protected void onInitData() {
        LogUtil.d(TAG, "onInitData");
        presenter.getOilPriceInfo(bindToLifecycle(), MobConfig.APP_KEY);
    }

    @Override
    public void onOilPriceInfo(OilPriceInfo oilPriceInfo) {
        LogUtil.d(TAG, "onOilPriceInfo : oilPriceInfo = " + oilPriceInfo);
        if (oilPriceInfo == null) {
            return;
        }
        if (mLoading != null && mLoading.getVisibility() == View.VISIBLE) {
            mLoading.setVisibility(View.GONE);
        }
        mOilPriceAdapter.setData(oilPriceInfo.getResult().getPrices());
    }

    @Override
    public void onError(Object object) {
        LogUtil.d(TAG, "onError");
    }
}
