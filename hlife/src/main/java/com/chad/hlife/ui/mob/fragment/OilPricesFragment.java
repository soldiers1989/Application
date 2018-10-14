package com.chad.hlife.ui.mob.fragment;

import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.hlife.R;
import com.chad.hlife.app.config.MobConfig;
import com.chad.hlife.entity.mob.OilPricesInfo;
import com.chad.hlife.mvp.presenter.mob.oilprices.OilPricesPresenter;
import com.chad.hlife.mvp.view.mob.IOilPricesView;
import com.chad.hlife.ui.base.BaseMvpFragment;
import com.chad.hlife.ui.mob.adapter.OilPricesAdapter;
import com.chad.hlife.util.LogUtil;

import butterknife.BindView;

public class OilPricesFragment extends BaseMvpFragment<IOilPricesView, OilPricesPresenter>
        implements IOilPricesView {

    private static final String TAG = OilPricesFragment.class.getSimpleName();

    @BindView(R.id.view_recycler)
    RecyclerView mRecyclerView;
    @BindView(R.id.layout_loading)
    ConstraintLayout mLoading;

    private OilPricesAdapter mOilPricesAdapter;

    @Override
    protected OilPricesPresenter onGetPresenter() {
        return new OilPricesPresenter();
    }

    @Override
    protected int onGetLayoutId() {
        return R.layout.fragment_oil_prices;
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
        mOilPricesAdapter = new OilPricesAdapter(getContext());
        mRecyclerView.setAdapter(mOilPricesAdapter);
    }

    @Override
    protected void onInitData() {
        LogUtil.d(TAG, "onInitData");
        presenter.getOilPricesInfo(bindToLifecycle(), MobConfig.APP_KEY);
    }

    @Override
    public void onOilPricesInfo(OilPricesInfo oilPricesInfo) {
        LogUtil.d(TAG, "onOilPricesInfo : oilPricesInfo = " + oilPricesInfo);
        if (oilPricesInfo == null) {
            return;
        }
        if (mLoading != null && mLoading.getVisibility() == View.VISIBLE) {
            mLoading.setVisibility(View.GONE);
        }
        mOilPricesAdapter.setData(oilPricesInfo.getResult());
    }

    @Override
    public void onError(Object object) {
        LogUtil.d(TAG, "onError");
    }
}
