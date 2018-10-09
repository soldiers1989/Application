package com.chad.hlife.ui.activity;

import android.content.Intent;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;

import com.chad.hlife.R;
import com.chad.hlife.app.AppConstant;
import com.chad.hlife.app.config.JuHeConfig;
import com.chad.hlife.entity.juhe.HistoryDetailInfo;
import com.chad.hlife.entity.juhe.HistoryInfo;
import com.chad.hlife.mvp.presenter.history.HistoryPresenter;
import com.chad.hlife.mvp.view.IHistoryView;
import com.chad.hlife.ui.adapter.HistoryDetailAdapter;
import com.chad.hlife.ui.base.BaseMvpAppCompatActivity;
import com.chad.hlife.util.LogUtil;

import butterknife.BindView;

public class HistoryDetailActivity extends BaseMvpAppCompatActivity<IHistoryView, HistoryPresenter>
        implements IHistoryView {

    private static final String TAG = HistoryDetailActivity.class.getSimpleName();

    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.text_content)
    AppCompatTextView mTextContent;
    @BindView(R.id.view_recycler)
    RecyclerView mRecyclerView;

    private HistoryDetailAdapter mHistoryDetailAdapter;

    @Override
    protected HistoryPresenter onGetPresenter() {
        return new HistoryPresenter();
    }

    @Override
    protected int onGetLayoutId() {
        return R.layout.activity_history_detail;
    }

    @Override
    protected void onInitView() {
        LogUtil.d(TAG, "onInitView");
        initToolbar();
        initRecyclerView();
    }

    private void initToolbar() {
        LogUtil.d(TAG, "initToolbar");
        mToolbar.setTitleTextColor(getResources().getColor(R.color.colorText));
        mToolbar.setNavigationIcon(R.drawable.ic_close_dark);
        mToolbar.setNavigationOnClickListener(v -> onBackPressed());
    }

    private void initRecyclerView() {
        LogUtil.d(TAG, "initRecyclerView");
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mHistoryDetailAdapter = new HistoryDetailAdapter(getApplicationContext());
        mRecyclerView.setAdapter(mHistoryDetailAdapter);
    }

    @Override
    protected void onInitData() {
        LogUtil.d(TAG, "onInitData");
        handleIntent(getIntent());
    }

    private void handleIntent(Intent intent) {
        LogUtil.d(TAG, "handleIntent : intent = " + (intent == null ? "Null" : "Not Null"));
        if (intent == null) {
            return;
        }
        String title = intent.getStringExtra(AppConstant.EXTRA_TITLE);
        if (!TextUtils.isEmpty(title)) {
            mToolbar.setTitle(title);
        }
        String id = intent.getStringExtra(AppConstant.EXTRA_ID);
        if (!TextUtils.isEmpty(id)) {
            presenter.getHistoryDetailInfo(bindToLifecycle(), JuHeConfig.KEY_HISTORY, id);
        }
    }

    @Override
    public void onHistoryInfo(HistoryInfo historyInfo) {

    }

    @Override
    public void onHistoryDetailInfo(HistoryDetailInfo historyDetailInfo) {
        LogUtil.d(TAG, "onHistoryDetailInfo : historyDetailInfo = "
                + (historyDetailInfo == null ? "Null" : "Not Null"));
        if (historyDetailInfo == null) {
            return;
        }
        mTextContent.setText(historyDetailInfo.getResult().get(0).getContent());
        mHistoryDetailAdapter.addData(historyDetailInfo.getResult().get(0).getPicUrl());
    }

    @Override
    public void onError(Object object) {
        LogUtil.d(TAG, "onError");
    }
}
