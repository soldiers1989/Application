package com.chad.read.ui.fragment;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.chad.read.R;
import com.chad.read.ui.base.BaseFragment;

import butterknife.BindView;

/**
 * 精选
 */
public class SelectionFragment extends BaseFragment {

    private static final String TAG = SelectionFragment.class.getSimpleName();

    @BindView(R.id.layout_swipe_refresh)
    SwipeRefreshLayout mSwipeRefreshLayout;
    @BindView(R.id.recycler_selection)
    RecyclerView mSelectionRecycler;

    private LinearLayoutManager mLinearLayoutManager = null;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_selection;
    }

    @Override
    public void initViews() {

    }

    @Override
    public void initData() {

    }
}
