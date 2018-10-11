package com.chad.hlife.ui.fragment;

import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.chad.hlife.R;
import com.chad.hlife.app.config.JuHeConfig;
import com.chad.hlife.entity.juhe.WifiInfo;
import com.chad.hlife.mvp.presenter.wifi.WifiPresenter;
import com.chad.hlife.mvp.view.IWifiView;
import com.chad.hlife.ui.adapter.WifiAdapter;
import com.chad.hlife.ui.base.BaseMvpFragment;
import com.chad.hlife.ui.view.refresh.HeaderView;
import com.chad.hlife.util.LogUtil;
import com.github.nuptboyzhb.lib.SuperSwipeRefreshLayout;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;

public class WifiFragment extends BaseMvpFragment<IWifiView, WifiPresenter>
        implements IWifiView, SuperSwipeRefreshLayout.OnPullRefreshListener {

    private static final String TAG = WifiFragment.class.getSimpleName();

    @BindView(R.id.layout_super_swipe_refresh)
    SuperSwipeRefreshLayout mSuperSwipeRefreshLayout;
    @BindView(R.id.view_recycler)
    RecyclerView mRecyclerView;

    private HeaderView mHeaderView;

    private WifiAdapter mWifiAdapter;

    @Override
    protected WifiPresenter onGetPresenter() {
        return new WifiPresenter();
    }

    @Override
    protected int onGetLayoutId() {
        return R.layout.fragment_wifi;
    }

    @Override
    protected void onInitView() {
        LogUtil.d(TAG, "onInitView");
        initSuperSwipeRefreshLayout();
        initRecyclerView();
    }

    private void initSuperSwipeRefreshLayout() {
        LogUtil.d(TAG, "initSuperSwipeRefreshLayout");
        mHeaderView = new HeaderView(getContext());
        mSuperSwipeRefreshLayout.setHeaderView(mHeaderView);
        mSuperSwipeRefreshLayout.setOnPullRefreshListener(this);
    }

    private void initRecyclerView() {
        LogUtil.d(TAG, "initRecyclerView");
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mRecyclerView.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
        mWifiAdapter = new WifiAdapter(getContext());
        mRecyclerView.setAdapter(mWifiAdapter);
    }

    @Override
    protected void onInitData() {
        LogUtil.d(TAG, "onInitData");
        getWifiInfo();
    }

    private void getWifiInfo() {
        presenter.getWifiInfo(bindToLifecycle(), 116.33316801271, 39.888615066575, 1, JuHeConfig.KEY_WIFI);
    }

    @Override
    public void onWifiInfo(WifiInfo wifiInfo) {
        LogUtil.d(TAG, "onWifiInfo : wifiInfo = " + (wifiInfo == null ? "Null" : "Not Null"));
        if (wifiInfo == null) {
            return;
        }
        if (mSuperSwipeRefreshLayout.isRefreshing()) {
            mSuperSwipeRefreshLayout.setRefreshing(false);
        }
        mWifiAdapter.setData(wifiInfo.getResult().getData());
    }

    @Override
    public void onError(Object object) {
        LogUtil.d(TAG, "onError");
    }

    @Override
    public void onRefresh() {
        LogUtil.d(TAG, "onRefresh");
        mHeaderView.refresh();
        Observable.timer(1, TimeUnit.SECONDS)
                .compose(bindToLifecycle())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(aLong -> getWifiInfo());
    }

    @Override
    public void onPullDistance(int i) {

    }

    @Override
    public void onPullEnable(boolean enable) {
        LogUtil.d(TAG, "onPullEnable : enable = " + enable);
        if (mHeaderView == null) {
            return;
        }
        mHeaderView.pullEnable(enable);
    }
}
