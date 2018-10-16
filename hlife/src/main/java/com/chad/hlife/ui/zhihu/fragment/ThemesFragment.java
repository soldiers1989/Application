package com.chad.hlife.ui.zhihu.fragment;

import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.hlife.R;
import com.chad.hlife.app.AppConstant;
import com.chad.hlife.entity.zhihu.ThemesDetailInfo;
import com.chad.hlife.entity.zhihu.ThemesInfo;
import com.chad.hlife.helper.ActivityHelper;
import com.chad.hlife.mvp.presenter.zhihu.themes.ThemesPresenter;
import com.chad.hlife.mvp.view.zhihu.IThemesView;
import com.chad.hlife.ui.base.BaseMvpFragment;
import com.chad.hlife.ui.view.loading.DoubleCircleLoadingView;
import com.chad.hlife.ui.zhihu.adapter.ThemesAdapter;
import com.chad.hlife.util.LogUtil;

import butterknife.BindView;

public class ThemesFragment extends BaseMvpFragment<IThemesView, ThemesPresenter>
        implements IThemesView {

    private static final String TAG = ThemesFragment.class.getSimpleName();

    @BindView(R.id.view_recycler)
    RecyclerView mRecyclerView;
    @BindView(R.id.layout_loading)
    ConstraintLayout mLoading;
    @BindView(R.id.view_loading)
    DoubleCircleLoadingView mLoadingView;

    private ThemesAdapter mThemesAdapter;

    @Override
    protected ThemesPresenter onGetPresenter() {
        return new ThemesPresenter();
    }

    @Override
    protected int onGetLayoutId() {
        return R.layout.fragment_themes;
    }

    @Override
    protected void onInitView() {
        LogUtil.d(TAG, "onInitView");
        initColor();
        initRecyclerView();
    }

    private void initColor() {
        LogUtil.d(TAG, "initColor");
        mLoadingView.setColor(getResources().getColor(AppConstant.COLOR_STATUS_BAR_RED));
    }

    private void initRecyclerView() {
        LogUtil.d(TAG, "initRecyclerView");
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mRecyclerView.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
        mThemesAdapter = new ThemesAdapter(getContext());
        mThemesAdapter.setOnItemClickListener(position ->
                ActivityHelper.startThemesDetailActivity(getActivity(),
                        mThemesAdapter.getData().get(position).getName(),
                        mThemesAdapter.getData().get(position).getId()));
        mRecyclerView.setAdapter(mThemesAdapter);
    }

    @Override
    protected void onInitData() {
        LogUtil.d(TAG, "initData");
        presenter.getThemesInfo(bindToLifecycle());
    }

    @Override
    public void onThemesInfo(ThemesInfo themesInfo) {
        LogUtil.d(TAG, "onThemesInfo : themesInfo = " + themesInfo);
        if (themesInfo == null) {
            return;
        }
        if (mLoading != null && mLoading.getVisibility() == View.VISIBLE) {
            mLoading.setVisibility(View.GONE);
        }
        mThemesAdapter.setData(themesInfo.getOthers());
    }

    @Override
    public void onThemesDetailInfo(ThemesDetailInfo themesDetailInfo) {

    }

    @Override
    public void onError(Object object) {
        LogUtil.d(TAG, "onError");
    }
}
