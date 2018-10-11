package com.chad.hlife.ui.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.chad.hlife.R;
import com.chad.hlife.entity.juhe.QueryInfo;
import com.chad.hlife.ui.adapter.QueryAdapter;
import com.chad.hlife.ui.base.BaseRxFragment;
import com.chad.hlife.util.LogUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class QueryFragment extends BaseRxFragment {

    private static final String TAG = QueryFragment.class.getSimpleName();

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
        initRecyclerView();
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
}
