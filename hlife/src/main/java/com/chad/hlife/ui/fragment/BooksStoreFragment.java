package com.chad.hlife.ui.fragment;

import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.chad.hlife.R;
import com.chad.hlife.entity.juhe.BookCatalogInfo;
import com.chad.hlife.mvp.presenter.books.BooksStorePresenter;
import com.chad.hlife.mvp.view.IBooksStoreView;
import com.chad.hlife.ui.adapter.BookListAdapter;
import com.chad.hlife.ui.base.BaseMvpFragment;
import com.chad.hlife.util.LogUtil;
import com.github.nuptboyzhb.lib.SuperSwipeRefreshLayout;

import butterknife.BindView;

public class BooksStoreFragment extends BaseMvpFragment<IBooksStoreView, BooksStorePresenter>
        implements IBooksStoreView, SuperSwipeRefreshLayout.OnPullRefreshListener {

    private static final String TAG = BooksStoreFragment.class.getSimpleName();

    @BindView(R.id.layout_super_swipe_refresh)
    SuperSwipeRefreshLayout mSuperSwipeRefreshLayout;
    @BindView(R.id.view_recycler)
    RecyclerView mRecyclerView;

    private BookListAdapter mBookListAdapter;

    @Override
    protected BooksStorePresenter onGetPresenter() {
        return new BooksStorePresenter();
    }

    @Override
    protected int onGetLayoutId() {
        return R.layout.fragment_books_store;
    }

    @Override
    protected void onInitView() {
        LogUtil.d(TAG, "onInitView");
        initSuperSwipeRefreshLayout();
        initRecyclerView();
    }

    private void initSuperSwipeRefreshLayout() {
        LogUtil.d(TAG, "initSuperSwipeRefreshLayout");
        mSuperSwipeRefreshLayout.setHeaderView(new ConstraintLayout(getContext()));
        mSuperSwipeRefreshLayout.setOnPullRefreshListener(this);
    }

    private void initRecyclerView() {
        LogUtil.d(TAG, "initRecyclerView");
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 2);
        mRecyclerView.setLayoutManager(gridLayoutManager);
        mBookListAdapter = new BookListAdapter(getContext());
        mRecyclerView.setAdapter(mBookListAdapter);
    }

    @Override
    protected void onInitData() {
        LogUtil.d(TAG, "onInitData");
//        presenter.getBookCatalogInfo(bindToLifecycle(), JuHeConfig.KEY_BOOKS);
    }

    @Override
    public void onBookCatalogInfo(BookCatalogInfo bookCatalogInfo) {
        LogUtil.d(TAG, "onBookCatalogInfo : bookCatalogInfo = "
                + (bookCatalogInfo == null ? "Null" : "Not Null"));
        if (bookCatalogInfo == null) {
            return;
        }
        mBookListAdapter.setData(bookCatalogInfo.getResult());
    }

    @Override
    public void onError(Object object) {
        LogUtil.d(TAG, "onError");
    }

    @Override
    public void onRefresh() {
        LogUtil.d(TAG, "onRefresh");
        mSuperSwipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void onPullDistance(int i) {

    }

    @Override
    public void onPullEnable(boolean b) {

    }
}
