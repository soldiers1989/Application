package com.chad.hlife.ui.juhe.activity;

import android.content.Intent;
import android.graphics.Color;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import com.chad.hlife.R;
import com.chad.hlife.app.AppConstant;
import com.chad.hlife.app.config.JuHeConfig;
import com.chad.hlife.entity.juhe.BookCatalogInfo;
import com.chad.hlife.entity.juhe.BookContentInfo;
import com.chad.hlife.helper.ActivityHelper;
import com.chad.hlife.mvp.presenter.juhe.books.BooksStorePresenter;
import com.chad.hlife.mvp.view.juhe.IBooksStoreView;
import com.chad.hlife.ui.base.BaseMvpAppCompatActivity;
import com.chad.hlife.ui.juhe.adapter.BookContentAdapter;
import com.chad.hlife.util.LogUtil;

import butterknife.BindView;

public class BooksStoreActivity extends BaseMvpAppCompatActivity<IBooksStoreView, BooksStorePresenter>
        implements IBooksStoreView {

    private static final String TAG = BooksStoreActivity.class.getSimpleName();

    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.view_recycler)
    RecyclerView mRecyclerView;
    @BindView(R.id.layout_loading)
    ConstraintLayout mLoading;

    private BookContentAdapter mBookContentAdapter;

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
        initRecyclerView();
    }

    private void initToolbar() {
        LogUtil.d(TAG, "initToolbar");
        mToolbar.setTitleTextColor(Color.WHITE);
        mToolbar.setNavigationIcon(R.drawable.ic_back_light);
        mToolbar.setNavigationOnClickListener(v -> onBackPressed());
    }

    private void initRecyclerView() {
        LogUtil.d(TAG, "initRecyclerView");
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mRecyclerView.addItemDecoration(new DividerItemDecoration(getApplicationContext(), DividerItemDecoration.VERTICAL));
        mBookContentAdapter = new BookContentAdapter(getApplicationContext());
        mBookContentAdapter.setOnItemClickListener(position -> {
            BookContentInfo.Data bookData = mBookContentAdapter.getData().get(position);
            String online = bookData.getOnline();
            if (TextUtils.isEmpty(online)) {
                Toast.makeText(getApplicationContext(), R.string.no_books_available_for_sale, Toast.LENGTH_SHORT).show();
            } else {
                String url = online.substring(online.indexOf(":") + 1, online.indexOf(" "));
                ActivityHelper.startBooksDetailActivity(getApplicationContext(), url);
            }
        });
        mRecyclerView.setAdapter(mBookContentAdapter);
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
        mLoading.setVisibility(View.GONE);
        mBookContentAdapter.setData(bookContentInfo.getResult().getData());
    }

    @Override
    public void onError(Object object) {
        LogUtil.d(TAG, "onError");
    }
}
