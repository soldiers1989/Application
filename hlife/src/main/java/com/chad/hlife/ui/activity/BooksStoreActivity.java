package com.chad.hlife.ui.activity;

import com.chad.hlife.entity.juhe.BookCatalogInfo;
import com.chad.hlife.mvp.presenter.books.BooksStorePresenter;
import com.chad.hlife.mvp.view.IBooksStoreView;
import com.chad.hlife.ui.base.BaseMvpAppCompatActivity;
import com.chad.hlife.util.LogUtil;

public class BooksStoreActivity extends BaseMvpAppCompatActivity<IBooksStoreView, BooksStorePresenter>
        implements IBooksStoreView {

    private static final String TAG = BooksStoreActivity.class.getSimpleName();

    @Override
    protected BooksStorePresenter onGetPresenter() {
        return new BooksStorePresenter();
    }

    @Override
    protected int onGetLayoutId() {
        return 0;
    }

    @Override
    protected void onInitView() {

    }

    @Override
    protected void onInitData() {

    }

    @Override
    public void onBookCatalogInfo(BookCatalogInfo bookCatalogInfo) {

    }

    @Override
    public void onError(Object object) {
        LogUtil.d(TAG, "onError");
    }
}
