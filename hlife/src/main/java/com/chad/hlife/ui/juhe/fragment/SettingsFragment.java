package com.chad.hlife.ui.juhe.fragment;

import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.chad.hlife.BuildConfig;
import com.chad.hlife.HLifeApplication;
import com.chad.hlife.R;
import com.chad.hlife.app.AppSettings;
import com.chad.hlife.helper.WeiBoAuthHelper;
import com.chad.hlife.ui.base.BaseRxFragment;
import com.chad.hlife.ui.juhe.adapter.SettingsAdapter;
import com.chad.hlife.util.CacheFileUtil;
import com.chad.hlife.util.LogUtil;
import com.github.nuptboyzhb.lib.SuperSwipeRefreshLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class SettingsFragment extends BaseRxFragment
        implements SuperSwipeRefreshLayout.OnPullRefreshListener {

    private static final String TAG = SettingsFragment.class.getSimpleName();

    @BindView(R.id.layout_super_swipe_refresh)
    SuperSwipeRefreshLayout mSuperSwipeRefreshLayout;
    @BindView(R.id.view_recycler)
    RecyclerView mRecyclerView;

    private SettingsAdapter mSettingsAdapter;

    @Override
    protected int onGetLayoutId() {
        return R.layout.fragment_settings;
    }

    @Override
    protected void onInitView() {
        LogUtil.d(TAG, "onInitView");
        initSuperSwipeRefreshLayout();
        initRecyclerView();
    }

    private void initSuperSwipeRefreshLayout() {
        LogUtil.d(TAG, "initSuperSwipeRefreshLayout");
        mSuperSwipeRefreshLayout.setHeaderView(new ConstraintLayout(getContext()));
        mSuperSwipeRefreshLayout.setOnPullRefreshListener(this);
    }

    private void initRecyclerView() {
        LogUtil.d(TAG, "initRecyclerView");
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mSettingsAdapter = new SettingsAdapter(getContext());
        mSettingsAdapter.setOnItemClickListener(position -> {
            switch (position) {
                case 2:
                    CacheFileUtil.clearCacheFile(HLifeApplication.getHLifeApplication());
                    getSettingsData();
                    break;
                case 3:
                    WeiBoAuthHelper.getInstance(HLifeApplication.getHLifeApplication()).clearAccessToken();
                    AppSettings.getInstance().setLoginModel(-1);
                    getActivity().finish();
                    break;
                default:
                    break;
            }
        });
        mRecyclerView.setAdapter(mSettingsAdapter);
    }

    @Override
    protected void onInitData() {
        LogUtil.d(TAG, "onInitData");
    }

    @Override
    public void onRefresh() {
        mSuperSwipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void onPullDistance(int i) {

    }

    @Override
    public void onPullEnable(boolean b) {

    }

    @Override
    public void onResume() {
        LogUtil.d(TAG, "onResume");
        getSettingsData();
        super.onResume();
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        LogUtil.d(TAG, "onHiddenChanged : hidden = " + hidden);
        if (!hidden) {
            getSettingsData();
        }
        super.onHiddenChanged(hidden);
    }

    private void getSettingsData() {
        LogUtil.d(TAG, "getSettingsData");
        List<String> list = new ArrayList<>();
        list.add("小黑工作室");
        list.add(BuildConfig.VERSION_NAME);
        list.add(CacheFileUtil.getCacheFileSize(HLifeApplication.getHLifeApplication()));
        list.add(getString(R.string.logout));
        mSettingsAdapter.setData(list);
    }
}
