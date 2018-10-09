package com.chad.hlife.ui.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.chad.hlife.R;
import com.chad.hlife.app.AppConstant;
import com.chad.hlife.app.config.JuHeConfig;
import com.chad.hlife.entity.juhe.JokeInfo;
import com.chad.hlife.mvp.presenter.joke.JokePresenter;
import com.chad.hlife.mvp.view.IJokeView;
import com.chad.hlife.ui.adapter.JokeAdapter;
import com.chad.hlife.ui.base.BaseMvpFragment;
import com.chad.hlife.ui.view.refresh.FooterView;
import com.chad.hlife.ui.view.refresh.HeaderView;
import com.chad.hlife.util.DateUtil;
import com.chad.hlife.util.LogUtil;
import com.facebook.common.time.SystemClock;
import com.github.nuptboyzhb.lib.SuperSwipeRefreshLayout;

import butterknife.BindView;

public class JokeFragment extends BaseMvpFragment<IJokeView, JokePresenter>
        implements IJokeView, SuperSwipeRefreshLayout.OnPullRefreshListener,
        SuperSwipeRefreshLayout.OnPushLoadMoreListener {

    private static final String TAG = JokeFragment.class.getSimpleName();

    @BindView(R.id.layout_super_swipe_refresh)
    SuperSwipeRefreshLayout mSuperSwipeRefreshLayout;
    @BindView(R.id.view_recycler)
    RecyclerView mRecyclerView;

    private HeaderView mHeaderView;

    private JokeAdapter mJokeAdapter;

    @Override
    protected JokePresenter onGetPresenter() {
        return new JokePresenter();
    }

    @Override
    protected int onGetLayoutId() {
        return R.layout.fragment_joke;
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
        mSuperSwipeRefreshLayout.setFooterView(new FooterView(getContext()));
        mSuperSwipeRefreshLayout.setOnPullRefreshListener(this);
        mSuperSwipeRefreshLayout.setOnPushLoadMoreListener(this);
    }

    private void initRecyclerView() {
        LogUtil.d(TAG, "initRecyclerView");
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mJokeAdapter = new JokeAdapter(getContext());
        mRecyclerView.setAdapter(mJokeAdapter);
    }

    @Override
    protected void onInitData() {
        LogUtil.d(TAG, "onInitData");
        presenter.getJokeInfo(bindToLifecycle(), JuHeConfig.KEY_JOKE);
    }

    @Override
    public void onJokeInfo(JokeInfo jokeInfo) {
        LogUtil.d(TAG, "onJokeInfo : jokeInfo = " + (jokeInfo == null ? "Null" : "Not Null"));
        if (jokeInfo == null) {
            return;
        }
        if (mSuperSwipeRefreshLayout.isRefreshing()) {
            mSuperSwipeRefreshLayout.setRefreshing(false);
        }
        mJokeAdapter.setData(jokeInfo.getResult().getData());
    }

    @Override
    public void onMoreJokeInfo(JokeInfo jokeInfo) {
        LogUtil.d(TAG, "onMoreJokeInfo : jokeInfo = " + (jokeInfo == null ? "Null" : "Not Null"));
        if (jokeInfo == null) {
            return;
        }
        mSuperSwipeRefreshLayout.setLoadMore(false);
        mJokeAdapter.addData(jokeInfo.getResult().getData());
    }

    @Override
    public void onError(Object object) {
        LogUtil.d(TAG, "onError");
    }

    @Override
    public void onRefresh() {
        LogUtil.d(TAG, "onRefresh");
        if (mHeaderView == null) {
            return;
        }
        mHeaderView.refresh();
        presenter.getJokeInfo(bindToLifecycle(), JuHeConfig.KEY_JOKE);
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

    @Override
    public void onLoadMore() {
        LogUtil.d(TAG, "onLoadMore");
        presenter.getMoreJokeInfo(bindToLifecycle(), AppConstant.SORT_JOKE_DESC,
                mJokeAdapter.getData().get(mJokeAdapter.getItemCount() - 1).getUnixtime(),
                JuHeConfig.KEY_JOKE);
    }

    @Override
    public void onPushDistance(int i) {

    }

    @Override
    public void onPushEnable(boolean b) {

    }
}
