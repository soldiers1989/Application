package com.chad.zhihu.ui.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.Toolbar;
import android.webkit.WebView;

import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.chad.zhihu.R;
import com.chad.zhihu.app.Constant;
import com.chad.zhihu.entity.zhihu.DetailInfo;
import com.chad.zhihu.hepler.glide.GlideApp;
import com.chad.zhihu.mvp.zhihu.presenter.DetailPresenter;
import com.chad.zhihu.mvp.zhihu.view.IDetailView;
import com.chad.zhihu.ui.base.BaseSwipeBackActivity;
import com.chad.zhihu.util.HtmlUtil;
import com.chad.zhihu.util.LogUtil;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;

public class DetailActivity extends BaseSwipeBackActivity<IDetailView, DetailPresenter>
        implements IDetailView{

    private static final String TAG = DetailActivity.class.getSimpleName();

    @BindView(R.id.layout_collapsing)
    CollapsingToolbarLayout mLayoutCollapsing;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.image_preview)
    AppCompatImageView mImagePreview;
    @BindView(R.id.text_title)
    AppCompatTextView mTextTitle;
    @BindView(R.id.text_source)
    AppCompatTextView mTextSource;
    @BindView(R.id.web_detail)
    WebView mWebDetail;

    private ArrayList<Integer> mStoriesIds = null;

    private int mCurrentId;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_detail;
    }

    @Override
    protected DetailPresenter getPresenter() {
        return new DetailPresenter();
    }

    @Override
    protected void initViews() {
        LogUtil.d(TAG, "initViews");
        initToolbar();
    }

    @Override
    protected void initData() {
        LogUtil.d(TAG, "initData");
        Intent intent = getIntent();
        if (intent != null) {
            mStoriesIds = intent.getIntegerArrayListExtra(Constant.EXTRA_ID_LIST);
            mCurrentId = intent.getIntExtra(Constant.EXTRA_ID, -1);
        }
        if (mCurrentId != -1) {
            presenter.getDetailInfo(bindToLifecycle(), mCurrentId);
        } else {
            onFail();
        }
    }

    private void initToolbar() {
        LogUtil.d(TAG, "initToolbar");
        mToolbar.setTitleTextColor(Color.WHITE);
        mToolbar.setNavigationIcon(R.drawable.ic_toolbar_back);
        mToolbar.setNavigationOnClickListener(view -> onBackPressed());
    }

    @OnClick(R.id.btn_up)
    public void onClickUp() {
        LogUtil.d(TAG, "onClickUp");
    }

    @OnClick(R.id.btn_down)
    public void onClickDown() {
        LogUtil.d(TAG, "onClickDown");
    }

    @Override
    public void onDetailInfo(DetailInfo detailInfo) {
        LogUtil.d(TAG, "onDetailInfo : detailInfo = " + detailInfo);
        if (detailInfo == null) {
            return;
        }
        GlideApp.with(this)
                .load(detailInfo.getImage())
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .placeholder(R.drawable.pic_default_placeholder)
                .into(mImagePreview);

        mTextTitle.setText(detailInfo.getTitle());
        mTextSource.setText(detailInfo.getImage_source());

        String html = HtmlUtil.getHtml(detailInfo);
        mWebDetail.loadData(html, HtmlUtil.MIME_TYPE, HtmlUtil.ENCODED);
    }

    @Override
    public void onFail() {
        LogUtil.d(TAG, "onFail");
    }
}
