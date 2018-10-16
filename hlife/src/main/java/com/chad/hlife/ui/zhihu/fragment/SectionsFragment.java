package com.chad.hlife.ui.zhihu.fragment;

import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.hlife.R;
import com.chad.hlife.app.AppConstant;
import com.chad.hlife.entity.zhihu.SectionsDetailInfo;
import com.chad.hlife.entity.zhihu.SectionsInfo;
import com.chad.hlife.helper.ActivityHelper;
import com.chad.hlife.mvp.presenter.zhihu.sections.SectionsPresenter;
import com.chad.hlife.mvp.view.zhihu.ISectionsView;
import com.chad.hlife.ui.base.BaseMvpFragment;
import com.chad.hlife.ui.view.loading.DoubleCircleLoadingView;
import com.chad.hlife.ui.zhihu.adapter.SectionsAdapter;
import com.chad.hlife.util.LogUtil;

import butterknife.BindView;

public class SectionsFragment extends BaseMvpFragment<ISectionsView, SectionsPresenter>
        implements ISectionsView {

    private static final String TAG = SectionsFragment.class.getSimpleName();

    @BindView(R.id.view_recycler)
    RecyclerView mRecyclerView;
    @BindView(R.id.layout_loading)
    ConstraintLayout mLoading;
    @BindView(R.id.view_loading)
    DoubleCircleLoadingView mLoadingView;

    private SectionsAdapter mSectionsAdapter;

    @Override
    protected SectionsPresenter onGetPresenter() {
        return new SectionsPresenter();
    }

    @Override
    protected int onGetLayoutId() {
        return R.layout.fragment_sections;
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
        mSectionsAdapter = new SectionsAdapter(getContext());
        mSectionsAdapter.setOnItemClickListener(position ->
                ActivityHelper.startSectionsDetailActivity(getActivity(),
                        mSectionsAdapter.getData().get(position).getName(),
                        mSectionsAdapter.getData().get(position).getId()));
        mRecyclerView.setAdapter(mSectionsAdapter);
    }


    @Override
    protected void onInitData() {
        LogUtil.d(TAG, "initData");
        presenter.getSectionsInfo(bindToLifecycle());
    }

    @Override
    public void OnSectionsInfo(SectionsInfo sectionsInfo) {
        LogUtil.d(TAG, "OnSectionsInfo : sectionsInfo = " + sectionsInfo);
        if (sectionsInfo == null) {
            return;
        }
        if (mLoading != null && mLoading.getVisibility() == View.VISIBLE) {
            mLoading.setVisibility(View.GONE);
        }
        mSectionsAdapter.setData(sectionsInfo.getData());
    }

    @Override
    public void onSectionsDetailInfo(SectionsDetailInfo sectionsDetailInfo) {

    }

    @Override
    public void onBeforeSectionsDetailInfo(SectionsDetailInfo sectionsDetailInfo) {

    }

    @Override
    public void onError(Object object) {
        LogUtil.d(TAG, "onError");
    }
}
