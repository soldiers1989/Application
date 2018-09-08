package com.chad.zhihu.ui.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.chad.zhihu.R;
import com.chad.zhihu.entity.zhihu.SectionsInfo;
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
        mSectionsAdapter.setOnItemClickListener(position -> {
            // TODO: 2018/9/8
        });

        mSectionsRecycler.setLayoutManager(mLinearLayoutManager);
        mSectionsRecycler.setAdapter(mSectionsAdapter);
    }

    @Override
    public void OnSectionsInfo(SectionsInfo sectionsInfo) {
        LogUtil.d(TAG, "OnSectionsInfo : sectionsInfo = " + sectionsInfo);
        mSectionsAdapter.setDataList(sectionsInfo.getData());
    }

    @Override
    public void onError(String msg) {
        LogUtil.d(TAG, "onError : msg = " + msg);
    }
}
