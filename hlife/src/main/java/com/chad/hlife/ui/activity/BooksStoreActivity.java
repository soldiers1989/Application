package com.chad.hlife.ui.activity;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;

import com.chad.hlife.R;
import com.chad.hlife.app.AppConstant;
import com.chad.hlife.app.config.JuHeConfig;
import com.chad.hlife.entity.juhe.BookCatalogInfo;
import com.chad.hlife.entity.juhe.BookContentInfo;
import com.chad.hlife.mvp.presenter.books.BooksStorePresenter;
import com.chad.hlife.mvp.view.IBooksStoreView;
import com.chad.hlife.ui.base.BaseMvpAppCompatActivity;
import com.chad.hlife.util.LogUtil;

import butterknife.BindView;

public class BooksStoreActivity extends BaseMvpAppCompatActivity<IBooksStoreView, BooksStorePresenter>
        implements IBooksStoreView {

    private static final String TAG = BooksStoreActivity.class.getSimpleName();

    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    @Override
    protected BooksStorePresenter onGetPresenter() {
        return new BooksStorePresenter();
    }

    @Override
    protected int onGetLayoutId() {
        return R.layout.activity_books_store;
    }

    @Override
    protected void onInitView() {
        LogUtil.d(TAG, "onInitView");
        initToolbar();
    }

    private void initToolbar() {
        LogUtil.d(TAG, "initToolbar");
        mToolbar.setTitleTextColor(Color.WHITE);
        mToolbar.setNavigationIcon(R.drawable.ic_back_light);
        mToolbar.setNavigationOnClickListener(v -> onBackPressed());
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
        int id = intent.getIntExtra(AppConstant.EXTRA_ID, -1);
        if (-1 != id) {
            presenter.getBookContentInfo(bindToLifecycle(), JuHeConfig.KEY_BOOKS, id, 0, 30);
        }
    }

    @Override
    public void onBookCatalogInfo(BookCatalogInfo bookCatalogInfo) {

    }

    @Override
    public void onBookContentInfo(BookContentInfo bookContentInfo) {
        LogUtil.d(TAG, "onBookContentInfo : bookContentInfo = "
                + (bookContentInfo == null ? "Null" : "Not Null"));
        if (bookContentInfo == null) {
            return;
        }
    }

    @Override
    public void onError(Object object) {
        LogUtil.d(TAG, "onError");
    }
}
