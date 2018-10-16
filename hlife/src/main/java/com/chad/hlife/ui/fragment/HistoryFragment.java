package com.chad.hlife.ui.fragment;

import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.hlife.R;
import com.chad.hlife.app.AppConstant;
import com.chad.hlife.app.config.MobConfig;
import com.chad.hlife.entity.mob.HistoryInfo;
import com.chad.hlife.eventbus.EventMessage;
import com.chad.hlife.eventbus.EventType;
import com.chad.hlife.helper.ActivityHelper;
import com.chad.hlife.mvp.presenter.mob.history.HistoryPresenter;
import com.chad.hlife.mvp.view.mob.IHistoryView;
import com.chad.hlife.ui.base.BaseMvpFragment;
import com.chad.hlife.ui.adapter.HistoryAdapter;
import com.chad.hlife.ui.view.loading.DoubleCircleLoadingView;
import com.chad.hlife.util.DateUtil;
import com.chad.hlife.util.LogUtil;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;

public class HistoryFragment extends BaseMvpFragment<IHistoryView, HistoryPresenter>
        implements IHistoryView {

    private static final String TAG = HistoryFragment.class.getSimpleName();

    @BindView(R.id.text_date)
    AppCompatTextView mTextDate;
    @BindView(R.id.view_recycler)
    RecyclerView mRecyclerView;
    @BindView(R.id.layout_loading)
    ConstraintLayout mLoading;
    @BindView(R.id.view_loading)
    DoubleCircleLoadingView mLoadingView;

    private HistoryAdapter mHistoryAdapter;

    private String mHistoryEvent;

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
        mLoadingView.setColor(getResources().getColor(AppConstant.COLOR_STATUS_BAR_BLUE));
        initDate();
        initRecyclerView();
    }

    private void initDate() {
        LogUtil.d(TAG, "initDate");
        mTextDate.setText(DateUtil.getYearMonthDay(getContext()));
    }

    private void initRecyclerView() {
        LogUtil.d(TAG, "initRecyclerView");
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mHistoryAdapter = new HistoryAdapter(getContext());
        mHistoryAdapter.setOnItemClickListener(position -> {
            mHistoryEvent = mHistoryAdapter.getData().get(position).getEvent();
            ActivityHelper.startHistoryDetailActivity(getActivity(), mHistoryAdapter.getData().get(position).getTitle());
        });
        mRecyclerView.setAdapter(mHistoryAdapter);
    }

    @Override
    protected void onInitData() {
        LogUtil.d(TAG, "onInitData");
        presenter.getHistoryInfo(bindToLifecycle(), MobConfig.APP_KEY, DateUtil.getMonthDay());
    }

    @Override
    public void onHistoryInfo(HistoryInfo historyInfo) {
        LogUtil.d(TAG, "onHistoryInfo : historyInfo = " + historyInfo);
        if (historyInfo == null) {
            return;
        }
        if (mLoading != null && mLoading.getVisibility() == View.VISIBLE) {
            mLoading.setVisibility(View.GONE);
        }
        mHistoryAdapter.setData(historyInfo.getResult());
    }

    @Override
    public void onError(Object object) {
        LogUtil.d(TAG, "onError");
    }

    @Override
    public void onStop() {
        LogUtil.d(TAG, "onStop");
        EventBus.getDefault().post(new EventMessage(EventType.TYPE_HISTORY, mHistoryEvent));
        super.onStop();
    }
}
