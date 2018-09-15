package com.chad.zhihu.ui.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.chad.zhihu.R;
import com.chad.zhihu.entity.SectionDetailsInfo;
import com.chad.zhihu.entity.SectionsInfo;
import com.chad.zhihu.hepler.ActivityHelper;
import com.chad.zhihu.mvp.zhihu.presenter.sections.SectionsPresenter;
import com.chad.zhihu.mvp.zhihu.view.ISectionsView;
import com.chad.zhihu.ui.adapter.SectionsAdapter;
import com.chad.zhihu.ui.base.BaseRxFragment;
import com.chad.zhihu.util.LogUtil;

import butterknife.BindView;

public class SectionsFragment extends BaseRxFragment<ISectionsView, SectionsPresenter>
        implements ISectionsView {

    private static final String TAG = SectionsFragment.class.getSimpleName();

    @BindView(R.id.sections_recycler)
    RecyclerView mSectionsRecycler;

    private LinearLayoutManager mLinearLayoutManager = null;
    private SectionsAdapter mSectionsAdapter = null;
    private SectionsInfo mSectionsInfo = null;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_sections;
    }

    @Override
    protected SectionsPresenter getPresenter() {
        return new SectionsPresenter();
    }

    @Override
    protected void initViews() {
        LogUtil.d(TAG, "initViews");
        initSectionsRecycler();
    }

    @Override
    protected void initData() {
        LogUtil.d(TAG, "initData");
        presenter.getSectionsInfo(bindToLifecycle());
    }

    private void initSectionsRecycler() {
        LogUtil.d(TAG, "initSectionsRecycler");
        mLinearLayoutManager = new LinearLayoutManager(getActivity());
        mSectionsAdapter = new SectionsAdapter(getActivity());
        mSectionsAdapter.setOnItemClickListener(position ->
            ActivityHelper.startSectionDetailsActivity(getActivity(),
                    mSectionsInfo.getData().get(position).getName(),
                    mSectionsInfo.getData().get(position).getId()));

        mSectionsRecycler.setLayoutManager(mLinearLayoutManager);
        mSectionsRecycler.setAdapter(mSectionsAdapter);
    }

    @Override
    public void OnSectionsInfo(SectionsInfo sectionsInfo) {
        LogUtil.d(TAG, "OnSectionsInfo : sectionsInfo = " + sectionsInfo);
        if (sectionsInfo == null) {
            return;
        }
        mSectionsInfo = sectionsInfo;
        mSectionsAdapter.setData(sectionsInfo.getData());
    }

    @Override
    public void onSectionDetailsInfo(SectionDetailsInfo sectionDetailsInfo) {

    }

    @Override
    public void onBeforeSectionDetailsInfo(SectionDetailsInfo sectionDetailsInfo) {

    }

    @Override
    public void onError(String msg) {
        LogUtil.d(TAG, "onError : msg = " + msg);
    }
}
