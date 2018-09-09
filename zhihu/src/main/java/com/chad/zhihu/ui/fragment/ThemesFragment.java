package com.chad.zhihu.ui.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.chad.zhihu.R;
import com.chad.zhihu.entity.zhihu.ThemeDetailsInfo;
import com.chad.zhihu.entity.zhihu.ThemesInfo;
import com.chad.zhihu.hepler.ActivityHelper;
import com.chad.zhihu.mvp.zhihu.presenter.themes.ThemesPresenter;
import com.chad.zhihu.mvp.zhihu.view.IThemesView;
import com.chad.zhihu.ui.adapter.ThemesAdapter;
import com.chad.zhihu.ui.base.BaseRxFragment;
import com.chad.zhihu.util.LogUtil;

import butterknife.BindView;

public class ThemesFragment extends BaseRxFragment<IThemesView, ThemesPresenter>
        implements IThemesView {

    private static final String TAG = ThemesFragment.class.getSimpleName();

    @BindView(R.id.themes_recycler)
    RecyclerView mThemesRecycler;

    private LinearLayoutManager mLinearLayoutManager = null;
    private ThemesAdapter mThemesAdapter = null;
    private ThemesInfo mThemesInfo = null;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_themes;
    }

    @Override
    protected ThemesPresenter getPresenter() {
        return new ThemesPresenter();
    }

    @Override
    protected void initViews() {
        LogUtil.d(TAG, "initViews");
        initThemesRecycler();
    }

    @Override
    protected void initData() {
        LogUtil.d(TAG, "initData");
        presenter.getThemesInfo(bindToLifecycle());
    }

    private void initThemesRecycler() {
        LogUtil.d(TAG, "initThemesRecycler");
        mLinearLayoutManager = new LinearLayoutManager(getActivity());
        mThemesAdapter = new ThemesAdapter(getActivity());
        mThemesAdapter.setOnItemClickListener(position -> {
            ActivityHelper.startThemeDetailsActivity(getActivity(),
                    mThemesInfo.getOthers().get(position).getId(),
                    mThemesInfo.getOthers().get(position).getName());
        });

        mThemesRecycler.setLayoutManager(mLinearLayoutManager);
        mThemesRecycler.setAdapter(mThemesAdapter);
    }

    @Override
    public void onThemesInfo(ThemesInfo themesInfo) {
        LogUtil.d(TAG, "onThemesInfo : themesInfo = " + themesInfo);
        if (themesInfo == null) {
            return;
        }
        mThemesInfo = themesInfo;
        mThemesAdapter.setData(themesInfo.getOthers());
    }

    @Override
    public void onThemeDetailsInfo(ThemeDetailsInfo themeDetailsInfo) {

    }

    @Override
    public void onError(String msg) {
        LogUtil.d(TAG, "onError : msg = " + msg);
    }
}
