package com.chad.hlife.ui.fragment;

import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.chad.hlife.R;
import com.chad.hlife.entity.juhe.QueryInfo;
import com.chad.hlife.ui.adapter.QueryAdapter;
import com.chad.hlife.ui.base.BaseRxFragment;
import com.chad.hlife.util.LogUtil;
import com.github.nuptboyzhb.lib.SuperSwipeRefreshLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class QueryFragment extends BaseRxFragment implements SuperSwipeRefreshLayout.OnPullRefreshListener {

    private static final String TAG = QueryFragment.class.getSimpleName();

    @BindView(R.id.layout_super_swipe_refresh)
    SuperSwipeRefreshLayout mSuperSwipeRefreshLayout;
    @BindView(R.id.view_recycler)
    RecyclerView mRecyclerView;

    private QueryAdapter mQueryAdapter;

    @Override
    protected int onGetLayoutId() {
        return R.layout.fragment_query;
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
        mQueryAdapter = new QueryAdapter(getContext());
        mRecyclerView.setAdapter(mQueryAdapter);

        List<QueryInfo> data = new ArrayList<>();
        data.add(new QueryInfo(R.drawable.ic_id_card, R.string.id_card));
        data.add(new QueryInfo(R.drawable.ic_phone_place, R.string.phone_place));
        mQueryAdapter.setData(data);
    }

    @Override
    protected void onInitData() {

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
}
