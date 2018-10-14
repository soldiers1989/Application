package com.chad.hlife.ui.zhihu.fragment;

import android.content.Intent;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.hlife.R;
import com.chad.hlife.app.AppConstant;
import com.chad.hlife.entity.zhihu.CommentsInfo;
import com.chad.hlife.mvp.presenter.zhihu.comments.CommentsPresenter;
import com.chad.hlife.mvp.view.zhihu.ICommentsView;
import com.chad.hlife.ui.base.BaseMvpFragment;
import com.chad.hlife.ui.zhihu.adapter.CommentsAdapter;
import com.chad.hlife.util.LogUtil;

import butterknife.BindView;

public class ShortCommentsFragment extends BaseMvpFragment<ICommentsView, CommentsPresenter>
        implements ICommentsView {

    private static final String TAG = ShortCommentsFragment.class.getSimpleName();

    @BindView(R.id.view_recycler)
    RecyclerView mRecyclerView;
    @BindView(R.id.image_sofa)
    AppCompatImageView mImageSofa;

    private CommentsAdapter mCommentsAdapter;

    @Override
    protected CommentsPresenter onGetPresenter() {
        return new CommentsPresenter();
    }

    @Override
    protected int onGetLayoutId() {
        return R.layout.fragment_comments;
    }

    @Override
    protected void onInitView() {
        LogUtil.d(TAG, "onInitView");
        initRecyclerView();
    }

    private void initRecyclerView() {
        LogUtil.d(TAG, "initRecyclerView");
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mRecyclerView.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));

        mCommentsAdapter = new CommentsAdapter(getContext());
        mRecyclerView.setAdapter(mCommentsAdapter);
    }

    @Override
    protected void onInitData() {
        LogUtil.d(TAG, "onInitData");
        handleIntent(getActivity().getIntent());
    }

    private void handleIntent(Intent intent) {
        LogUtil.d(TAG, "handleIntent : intent = " + (intent == null ? "Null" : "Not Null"));
        if (intent == null) {
            return;
        }
        int id = intent.getIntExtra(AppConstant.EXTRA_ID, -1);
        if (id != -1) {
            presenter.getShortCommentsInfo(bindToLifecycle(), id);
        }
    }

    @Override
    public void onCommentsInfo(CommentsInfo commentsInfo) {
        LogUtil.d(TAG, "onCommentsInfo : commentsInfo = " + commentsInfo);
        if (commentsInfo == null || commentsInfo.getComments().size() == 0) {
            mImageSofa.setVisibility(View.VISIBLE);
            return;
        }
        mCommentsAdapter.setData(commentsInfo.getComments());
    }

    @Override
    public void onError(Object object) {
        LogUtil.d(TAG, "onError");
    }
}
