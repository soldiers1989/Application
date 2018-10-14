package com.chad.hlife.ui.juhe.fragment;

import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.hlife.R;
import com.chad.hlife.app.config.JuHeConfig;
import com.chad.hlife.entity.juhe.BookCatalogInfo;
import com.chad.hlife.entity.juhe.BookContentInfo;
import com.chad.hlife.helper.ActivityHelper;
import com.chad.hlife.mvp.presenter.juhe.books.BooksStorePresenter;
import com.chad.hlife.mvp.view.juhe.IBooksStoreView;
import com.chad.hlife.ui.base.BaseMvpFragment;
import com.chad.hlife.ui.juhe.adapter.BookCatalogAdapter;
import com.chad.hlife.util.LogUtil;

import butterknife.BindView;

public class BooksStoreFragment extends BaseMvpFragment<IBooksStoreView, BooksStorePresenter>
        implements IBooksStoreView {

    private static final String TAG = BooksStoreFragment.class.getSimpleName();

    @BindView(R.id.view_recycler)
    RecyclerView mRecyclerView;
    @BindView(R.id.layout_loading)
    ConstraintLayout mLoading;

    private BookCatalogAdapter mBookCatalogAdapter;

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
        initRecyclerView();
    }

    private void initRecyclerView() {
        LogUtil.d(TAG, "initRecyclerView");
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 2);
        mRecyclerView.setLayoutManager(gridLayoutManager);
        mBookCatalogAdapter = new BookCatalogAdapter(getContext());
        mBookCatalogAdapter.setOnItemClickListener(position ->
                ActivityHelper.startBooksStoreActivity(getActivity(),
                        mBookCatalogAdapter.getData().get(position).getCatalog(),
                        mBookCatalogAdapter.getData().get(position).getId()));
        mRecyclerView.setAdapter(mBookCatalogAdapter);
    }

    @Override
    protected void onInitData() {
        LogUtil.d(TAG, "onInitData");
        presenter.getBookCatalogInfo(bindToLifecycle(), JuHeConfig.KEY_BOOKS);
    }

    @Override
    public void onBookCatalogInfo(BookCatalogInfo bookCatalogInfo) {
        LogUtil.d(TAG, "onBookCatalogInfo : bookCatalogInfo = "
                + (bookCatalogInfo == null ? "Null" : "Not Null"));
        if (bookCatalogInfo == null) {
            return;
        }
        if (mLoading != null && mLoading.getVisibility() == View.VISIBLE) {
            mLoading.setVisibility(View.GONE);
        }
        mBookCatalogAdapter.setData(bookCatalogInfo.getResult());
    }

    @Override
    public void onBookContentInfo(BookContentInfo bookContentInfo) {

    }

    @Override
    public void onError(Object object) {
        LogUtil.d(TAG, "onError");
    }
}
