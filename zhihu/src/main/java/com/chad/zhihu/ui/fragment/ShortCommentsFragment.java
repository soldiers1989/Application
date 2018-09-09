package com.chad.zhihu.ui.fragment;

import android.content.Intent;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.zhihu.R;
import com.chad.zhihu.app.Constant;
import com.chad.zhihu.entity.zhihu.CommentsInfo;
import com.chad.zhihu.mvp.zhihu.presenter.comments.CommentsPresenter;
import com.chad.zhihu.mvp.zhihu.view.ICommentsView;
import com.chad.zhihu.ui.adapter.CommentsAdapter;
import com.chad.zhihu.ui.base.BaseRxFragment;
import com.chad.zhihu.util.LogUtil;

import butterknife.BindView;

public class ShortCommentsFragment extends BaseRxFragment<ICommentsView, CommentsPresenter>
        implements ICommentsView {

    private static final String TAG = ShortCommentsFragment.class.getSimpleName();

    @BindView(R.id.comments_recycler)
    RecyclerView mCommentsRecycler;
    @BindView(R.id.view_empty)
    View mEmptyView;
    @BindView(R.id.text_message)
    AppCompatTextView mTextMessage;

    private LinearLayoutManager mLinearLayoutManager = null;
    private DividerItemDecoration mDividerItemDecoration = null;
    private CommentsAdapter mCommentsAdapter = null;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_comments_short;
    }

    @Override
    protected CommentsPresenter getPresenter() {
        return new CommentsPresenter();
    }

    @Override
    protected void initViews() {
        LogUtil.d(TAG, "initViews");
        initCommentsRecycler();
        mTextMessage.setText(R.string.comment_sofa_message_short);
    }

    @Override
    protected void initData() {
        LogUtil.d(TAG, "initData");
        Intent intent = getActivity().getIntent();
        if (intent == null) {
            return;
        }
        int id = intent.getIntExtra(Constant.EXTRA_ID, -1);
        presenter.getShortCommentsInfo(bindToLifecycle(), id);
    }

    private void initCommentsRecycler() {
        LogUtil.d(TAG, "initCommentsRecycler");
        mLinearLayoutManager = new LinearLayoutManager(getActivity());
        mDividerItemDecoration = new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL);
        mCommentsAdapter = new CommentsAdapter(getActivity());

        mCommentsRecycler.setLayoutManager(mLinearLayoutManager);
        mCommentsRecycler.addItemDecoration(mDividerItemDecoration);
        mCommentsRecycler.setAdapter(mCommentsAdapter);
    }

    @Override
    public void onCommentsInfo(CommentsInfo commentsInfo) {
        LogUtil.d(TAG, "onCommentsInfo : commentsInfo = " + commentsInfo);
        if (commentsInfo == null || commentsInfo.getComments().size() == 0) {
            mEmptyView.setVisibility(View.VISIBLE);
            return;
        }
        mCommentsAdapter.setData(commentsInfo.getComments());
    }

    @Override
    public void onError(String msg) {
        LogUtil.d(TAG, "onError : msg = " + msg);
    }
}
