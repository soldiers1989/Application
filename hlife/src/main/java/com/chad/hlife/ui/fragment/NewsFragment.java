package com.chad.hlife.ui.fragment;

import android.support.design.widget.TabLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.chad.hlife.R;
import com.chad.hlife.app.AppConstant;
import com.chad.hlife.app.config.JuHeConfig;
import com.chad.hlife.entity.juhe.NewsInfo;
import com.chad.hlife.helper.ActivityHelper;
import com.chad.hlife.mvp.presenter.news.NewsPresenter;
import com.chad.hlife.mvp.view.INewsView;
import com.chad.hlife.ui.adapter.NewsAdapter;
import com.chad.hlife.ui.base.BaseMvpFragment;
import com.chad.hlife.ui.view.refresh.HeaderView;
import com.chad.hlife.util.LogUtil;
import com.github.nuptboyzhb.lib.SuperSwipeRefreshLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class NewsFragment extends BaseMvpFragment<INewsView, NewsPresenter>
        implements INewsView, TabLayout.BaseOnTabSelectedListener,
        SuperSwipeRefreshLayout.OnPullRefreshListener {

    private static final String TAG = NewsFragment.class.getSimpleName();

    @BindView(R.id.layout_tab)
    TabLayout mTabLayout;
    @BindView(R.id.layout_super_swipe_refresh)
    SuperSwipeRefreshLayout mSuperSwipeRefreshLayout;
    @BindView(R.id.view_recycler)
    RecyclerView mRecyclerView;

    private HeaderView mHeaderView;

    private NewsAdapter mNewsAdapter;

    private List<String> mNewsTypes;

    @Override
    protected NewsPresenter onGetPresenter() {
        return new NewsPresenter();
    }

    @Override
    protected int onGetLayoutId() {
        return R.layout.fragment_news;
    }

    @Override
    protected void onInitView() {
        LogUtil.d(TAG, "initViews");
        initTabLayout();
        initSuperSwipeRefreshLayout();
        initRecyclerView();
    }

    private void initTabLayout() {
        LogUtil.d(TAG, "initTabLayout");
        mTabLayout.addOnTabSelectedListener(this);
        mTabLayout.addTab(mTabLayout.newTab().setText(R.string.top));
        mTabLayout.addTab(mTabLayout.newTab().setText(R.string.society));
        mTabLayout.addTab(mTabLayout.newTab().setText(R.string.internal));
        mTabLayout.addTab(mTabLayout.newTab().setText(R.string.international));
        mTabLayout.addTab(mTabLayout.newTab().setText(R.string.disport));
        mTabLayout.addTab(mTabLayout.newTab().setText(R.string.physical));
        mTabLayout.addTab(mTabLayout.newTab().setText(R.string.military));
        mTabLayout.addTab(mTabLayout.newTab().setText(R.string.science));
        mTabLayout.addTab(mTabLayout.newTab().setText(R.string.finance));
        mTabLayout.addTab(mTabLayout.newTab().setText(R.string.fashion));
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
        mNewsAdapter = new NewsAdapter(getContext());
        mNewsAdapter.setOnItemClickListener(position ->
                ActivityHelper.startNewsDetailActivity(getActivity(),
                        mTabLayout.getTabAt(mTabLayout.getSelectedTabPosition()).getText().toString(),
                        mNewsAdapter.getData().get(position).getUrl()));
        mRecyclerView.setAdapter(mNewsAdapter);
    }

    @Override
    protected void onInitData() {
        LogUtil.d(TAG, "initData");
        initNewsTypes();
        presenter.getNewsInfo(bindToLifecycle(), mNewsTypes.get(mTabLayout.getSelectedTabPosition()), JuHeConfig.KEY_NEWS);
    }

    private void initNewsTypes() {
        LogUtil.d(TAG, "initNewsTypes");
        mNewsTypes = new ArrayList<>();
        mNewsTypes.add(AppConstant.TYPE_NEWS_TOP);
        mNewsTypes.add(AppConstant.TYPE_NEWS_SHEHUI);
        mNewsTypes.add(AppConstant.TYPE_NEWS_GUONEI);
        mNewsTypes.add(AppConstant.TYPE_NEWS_GUOJI);
        mNewsTypes.add(AppConstant.TYPE_NEWS_YULE);
        mNewsTypes.add(AppConstant.TYPE_NEWS_TIYU);
        mNewsTypes.add(AppConstant.TYPE_NEWS_JUNSHI);
        mNewsTypes.add(AppConstant.TYPE_NEWS_KEJI);
        mNewsTypes.add(AppConstant.TYPE_NEWS_CAIJING);
        mNewsTypes.add(AppConstant.TYPE_NEWS_SHISHANG);
    }

    @Override
    public void onNewsInfo(NewsInfo newsInfo) {
        LogUtil.d(TAG, "onNewsInfo : newsInfo = " + (newsInfo == null ? "Null" : "Not Null"));
        if (newsInfo == null) {
            return;
        }
        if (mSuperSwipeRefreshLayout.isRefreshing()) {
            mSuperSwipeRefreshLayout.setRefreshing(false);
        }
        mNewsAdapter.setData(newsInfo.getResult().getData());
        mRecyclerView.scrollToPosition(0);
    }

    @Override
    public void onError(Object object) {
        LogUtil.d(TAG, "onError");
    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        LogUtil.d(TAG, "onTabSelected : position = " + tab.getPosition());
        if (presenter == null || mNewsTypes == null) {
            return;
        }
        presenter.getNewsInfo(bindToLifecycle(), mNewsTypes.get(tab.getPosition()), JuHeConfig.KEY_NEWS);
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }

    @Override
    public void onRefresh() {
        LogUtil.d(TAG, "onRefresh");
        if (mHeaderView == null) {
            return;
        }
        mHeaderView.refresh();
        presenter.getNewsInfo(bindToLifecycle(),mNewsTypes.get(mTabLayout.getSelectedTabPosition()), JuHeConfig.KEY_NEWS);
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
