package com.chad.zhihu.ui.activity;

import android.content.Intent;
import android.graphics.Color;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.Toolbar;
import android.webkit.WebView;

import com.chad.zhihu.R;
import com.chad.zhihu.app.Constant;
import com.chad.zhihu.entity.zhihu.DetailsInfo;
import com.chad.zhihu.hepler.glide.CustomGlideModule;
import com.chad.zhihu.mvp.zhihu.presenter.details.DetailsPresenter;
import com.chad.zhihu.mvp.zhihu.view.IDetailsView;
import com.chad.zhihu.ui.base.BaseSwipeBackActivity;
import com.chad.zhihu.util.HtmlUtil;
import com.chad.zhihu.util.LogUtil;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;

public class DetailsActivity extends BaseSwipeBackActivity<IDetailsView, DetailsPresenter>
        implements IDetailsView {

    private static final String TAG = DetailsActivity.class.getSimpleName();

    @BindView(R.id.layout_collapsing)
    CollapsingToolbarLayout mLayoutCollapsing;
    @BindView(R.id.layout_appbar)
    AppBarLayout mLayoutAppBar;
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
    private int mCurrentIndex;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_details;
    }

    @Override
    protected DetailsPresenter getPresenter() {
        return new DetailsPresenter();
    }

    @Override
    protected void initViews() {
        LogUtil.d(TAG, "initViews");
        initAppBar();
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
            presenter.getDetailsInfo(bindToLifecycle(), mCurrentId);
        } else {
            onError("");
        }
        if (mStoriesIds != null) {
            for (int i = 0; i < mStoriesIds.size(); i++) {
                if (mStoriesIds.get(i) == mCurrentId) {
                    mCurrentIndex = i;
                }
            }
        }
    }

    private void initAppBar() {
        LogUtil.d(TAG, "initToolbar");
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
        if (mCurrentIndex == 0) {
            return;
        }
        mCurrentIndex --;
        presenter.getDetailsInfo(bindToLifecycle(), mStoriesIds.get(mCurrentIndex));
    }

    @OnClick(R.id.btn_down)
    public void onClickDown() {
        LogUtil.d(TAG, "onClickDown");
        if (mCurrentIndex == mStoriesIds.size() - 1) {
            return;
        }
        mCurrentIndex ++;
        presenter.getDetailsInfo(bindToLifecycle(), mStoriesIds.get(mCurrentIndex));
    }

    @Override
    public void onDetailsInfo(DetailsInfo detailsInfo) {
        LogUtil.d(TAG, "onDetailsInfo : detailsInfo = " + detailsInfo);
        if (detailsInfo == null) {
            return;
        }
        CustomGlideModule.loadImage(getApplicationContext(), detailsInfo.getImage(), mImagePreview);

        mTextTitle.setText(detailsInfo.getTitle());
        mTextSource.setText(detailsInfo.getImage_source());

        String html = HtmlUtil.getHtml(detailsInfo);
        mWebDetail.loadData(html, HtmlUtil.MIME_TYPE, HtmlUtil.ENCODED);
    }

    @Override
    public void onError(String msg) {
        LogUtil.d(TAG, "onError : msg = " + msg);
    }
}
