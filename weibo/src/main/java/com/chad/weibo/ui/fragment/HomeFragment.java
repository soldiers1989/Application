package com.chad.weibo.ui.fragment;

import android.support.annotation.NonNull;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ProgressBar;

import com.chad.weibo.R;
import com.chad.weibo.constant.AppConstant;
import com.chad.weibo.entity.RateLimitStatus;
import com.chad.weibo.entity.TimeLine;
import com.chad.weibo.helper.WeiBoAuthHelper;
import com.chad.weibo.mvp.presenter.home.HomePresenter;
import com.chad.weibo.mvp.view.IHomeView;
import com.chad.weibo.retrofit.WeiBoRetrofit;
import com.chad.weibo.ui.adapter.TimeLineAdapter;
import com.chad.weibo.ui.base.BaseMvpFragment;
import com.chad.weibo.util.LogUtil;
import com.chad.weibo.util.RxSchedulersUtil;
import com.github.nuptboyzhb.lib.SuperSwipeRefreshLayout;
import com.sina.weibo.sdk.auth.Oauth2AccessToken;

import butterknife.BindView;
import io.reactivex.functions.Consumer;

public class HomeFragment extends BaseMvpFragment<IHomeView, HomePresenter> implements IHomeView {

    private static final String TAG = HomeFragment.class.getSimpleName();

    @BindView(R.id.layout_swipe_refresh)
    SuperSwipeRefreshLayout mSuperSwipeRefreshLayout;
    @BindView(R.id.view_recycler_home)
    RecyclerView mHomeRecyclerView;

    private AppCompatImageView mRefreshArrow;
    private ProgressBar mRefreshProgress;
    private AppCompatTextView mRefreshTitle;

    private TimeLineAdapter mTimeLineAdapter = null;

    private int mPage = 1;
    private int mFeature = AppConstant.TIME_LINE_FEATURE_ALL;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initViews() {
        LogUtil.d(TAG, "initViews");
        initSuperSwipeRefresh();
        initRecyclerView();
    }

    private void initSuperSwipeRefresh() {
        LogUtil.d(TAG, "initSuperSwipeRefresh");
        mSuperSwipeRefreshLayout.setHeaderView(createHeaderView());
        mSuperSwipeRefreshLayout.setFooterView(createFooterView());
        mSuperSwipeRefreshLayout.setOnPullRefreshListener(
                new SuperSwipeRefreshLayout.OnPullRefreshListener() {
                    @Override
                    public void onRefresh() {
                        LogUtil.d(TAG, "onRefresh");
                        mRefreshArrow.setVisibility(View.GONE);
                        mRefreshProgress.setVisibility(View.VISIBLE);
                        mRefreshTitle.setText(R.string.load_in);
                        getHomeTimeLine(mPage, mFeature);
                    }

                    @Override
                    public void onPullDistance(int distance) {
                        LogUtil.d(TAG, "onPullDistance : distance = " + distance);
                    }

                    @Override
                    public void onPullEnable(boolean enable) {
                        LogUtil.d(TAG, "onPullEnable : enable = " + enable);
                        mRefreshArrow.setVisibility(View.VISIBLE);
                        mRefreshArrow.setRotation(enable ? 180 : 0);
                        mRefreshProgress.setVisibility(View.GONE);
                        mRefreshTitle.setText(enable ? R.string.release_updates : R.string.drop_down_refresh);
                    }
                });

        mSuperSwipeRefreshLayout.setOnPushLoadMoreListener(
                new SuperSwipeRefreshLayout.OnPushLoadMoreListener() {
                    @Override
                    public void onLoadMore() {
                        LogUtil.d(TAG, "onLoadMore");
                        getMoreHomeTimeLine(++mPage, mFeature);
                    }

                    @Override
                    public void onPushDistance(int distance) {

                    }

                    @Override
                    public void onPushEnable(boolean enable) {

                    }
                });
    }

    private View createHeaderView() {
        LogUtil.d(TAG, "createHeaderView");
        View headerView = LayoutInflater.from(getActivity()).inflate(R.layout.item_refresh_header, null);
        mRefreshArrow = headerView.findViewById(R.id.arrow);
        mRefreshProgress = headerView.findViewById(R.id.progress);
        mRefreshTitle = headerView.findViewById(R.id.title);
        return headerView;
    }

    private View createFooterView() {
        LogUtil.d(TAG, "createFooterView");
        return LayoutInflater.from(getActivity()).inflate(R.layout.item_refresh_footer, null);
    }

    private void initRecyclerView() {
        LogUtil.d(TAG, "initRecyclerView");
        mTimeLineAdapter = new TimeLineAdapter(getActivity());
        mHomeRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mHomeRecyclerView.setAdapter(mTimeLineAdapter);
        mHomeRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                LinearLayoutManager linearLayoutManager = (LinearLayoutManager) mHomeRecyclerView
                        .getLayoutManager();
                if (linearLayoutManager.findFirstVisibleItemPosition() > linearLayoutManager.getChildCount() - 5) {
                    getMoreHomeTimeLine(++mPage, mFeature);
                }
            }
        });
    }

    @Override
    protected void initData() {
        LogUtil.d(TAG, "initData");
        getHomeTimeLine(mPage, mFeature);
    }

    private void getHomeTimeLine(int page, int feature) {
        LogUtil.d(TAG, "getFeatureHomeTimeLine");
        Oauth2AccessToken accessToken = WeiBoAuthHelper.getInstance().getOauth2AccessToken();
        if (accessToken != null) {
            String access_token = accessToken.getToken();
            presenter.getHomeTimeLine(bindToLifecycle(), access_token, AppConstant.TIME_LINE_COUNT, page, feature);
        }

    }

    private void getMoreHomeTimeLine(int page, int feature) {
        LogUtil.d(TAG, "getMoreHomeTimeLine");
        Oauth2AccessToken accessToken = WeiBoAuthHelper.getInstance().getOauth2AccessToken();
        if (accessToken != null) {
            String access_token = accessToken.getToken();
            presenter.getMoreHomeTimeLine(bindToLifecycle(), access_token, AppConstant.TIME_LINE_COUNT, page, feature);
        }
    }

    @Override
    protected HomePresenter getPresenter() {
        return new HomePresenter();
    }

    @Override
    public void onHomeTimeLine(TimeLine timeLine) {
        LogUtil.d(TAG, "onHomeTimeLine : timeLine = " + (timeLine == null ? null : "Not Null"));
        if (timeLine == null) {
            return;
        }
//        mTimeLineAdapter.setData(timeLine.getStatuses());
        mSuperSwipeRefreshLayout.setRefreshing(false);
        mPage = 1;
    }

    @Override
    public void onMoreHomeTimeLine(TimeLine timeLine) {
        LogUtil.d(TAG, "onMoreHomeTimeLine : timeLine = " + (timeLine == null ? null : "Not Null"));
        if (timeLine == null) {
            return;
        }
//        mTimeLineAdapter.addData(timeLine.getStatuses());
        mSuperSwipeRefreshLayout.setLoadMore(false);
    }

    @Override
    public void onError(Object object) {
        LogUtil.d(TAG, "onError : message = " + ((Throwable) object).getMessage());
    }
}
