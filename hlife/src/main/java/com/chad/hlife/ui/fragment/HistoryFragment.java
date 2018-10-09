package com.chad.hlife.ui.fragment;

import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.chad.hlife.R;
import com.chad.hlife.app.config.JuHeConfig;
import com.chad.hlife.entity.juhe.HistoryDetailInfo;
import com.chad.hlife.entity.juhe.HistoryInfo;
import com.chad.hlife.helper.ActivityHelper;
import com.chad.hlife.mvp.presenter.history.HistoryPresenter;
import com.chad.hlife.mvp.view.IHistoryView;
import com.chad.hlife.ui.adapter.HistoryAdapter;
import com.chad.hlife.ui.base.BaseMvpFragment;
import com.chad.hlife.util.DateUtil;
import com.chad.hlife.util.LogUtil;
import com.github.nuptboyzhb.lib.SuperSwipeRefreshLayout;

import butterknife.BindView;

public class HistoryFragment extends BaseMvpFragment<IHistoryView, HistoryPresenter>
        implements IHistoryView, SuperSwipeRefreshLayout.OnPullRefreshListener {

    private static final String TAG = HistoryFragment.class.getSimpleName();

    @BindView(R.id.text_date)
    AppCompatTextView mTextDate;
    @BindView(R.id.layout_super_swipe_refresh)
    SuperSwipeRefreshLayout mSuperSwipeRefreshLayout;
    @BindView(R.id.view_recycler)
    RecyclerView mRecyclerView;

    private HistoryAdapter mHistoryAdapter;

    @Override
    protected HistoryPresenter onGetPresenter() {
        return new HistoryPresenter();
    }

    @Override
    protected int onGetLayoutId() {
        return R.layout.fragment_history;
    }

    @Override
    protected void onInitView() {
        LogUtil.d(TAG, "onInitView");
        initDate();
        initSuperSwipeRefreshLayout();
        initRecyclerView();
    }

    private void initDate() {
        LogUtil.d(TAG, "initDate");
        mTextDate.setText(DateUtil.getYearMonthDay(getContext()));
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
        mHistoryAdapter = new HistoryAdapter(getContext());
        mHistoryAdapter.setOnItemClickListener(position ->
                ActivityHelper.startHistoryDetailActivity(getActivity(),
                        mHistoryAdapter.getData().get(position).getTitle(),
                        mHistoryAdapter.getData().get(position).getEId()));
        mRecyclerView.setAdapter(mHistoryAdapter);
    }

    @Override
    protected void onInitData() {
        LogUtil.d(TAG, "onInitData");
        presenter.getHistoryInfo(bindToLifecycle(), JuHeConfig.KEY_HISTORY, DateUtil.getMonthDay());
    }

    @Override
    public void onHistoryInfo(HistoryInfo historyInfo) {
        LogUtil.d(TAG, "onHistoryInfo : historyInfo = " + (historyInfo == null ? "Null" : "Not Null"));
        if (historyInfo == null) {
            return;
        }
        mHistoryAdapter.setData(historyInfo.getResult());
    }

    @Override
    public void onHistoryDetailInfo(HistoryDetailInfo historyDetailInfo) {

    }

    @Override
    public void onError(Object object) {
        LogUtil.d(TAG, "onError");
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
