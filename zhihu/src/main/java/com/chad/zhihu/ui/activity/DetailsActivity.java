package com.chad.zhihu.ui.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.support.design.widget.AppBarLayout;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.AppCompatImageButton;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.chad.zhihu.R;
import com.chad.zhihu.app.AppSettings;
import com.chad.zhihu.app.Constant;
import com.chad.zhihu.entity.DetailsExtraInfo;
import com.chad.zhihu.entity.DetailsInfo;
import com.chad.zhihu.hepler.ActivityHelper;
import com.chad.zhihu.glide.CustomGlideModule;
import com.chad.zhihu.mvp.zhihu.presenter.details.DetailsPresenter;
import com.chad.zhihu.mvp.zhihu.view.IDetailsView;
import com.chad.zhihu.ui.base.BaseMvpRxAppCompatActivity;
import com.chad.zhihu.util.HtmlUtil;
import com.chad.zhihu.util.LogUtil;
import com.chad.zhihu.util.StringUtil;
import com.chad.zhihu.util.SystemStatusBarUtil;
import com.jakewharton.rxbinding2.view.RxView;
import com.jakewharton.rxbinding2.widget.RxTextView;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.OnClick;

public class DetailsActivity extends BaseMvpRxAppCompatActivity<IDetailsView, DetailsPresenter>
        implements IDetailsView {

    private static final String TAG = DetailsActivity.class.getSimpleName();

    @BindView(R.id.layout_appbar)
    AppBarLayout mLayoutAppBar;
    @BindView(R.id.image_preview)
    AppCompatImageView mImagePreview;
    @BindView(R.id.text_title)
    AppCompatTextView mTextTitle;
    @BindView(R.id.text_source)
    AppCompatTextView mTextSource;
    @BindView(R.id.view_scroll)
    NestedScrollView mScrollView;
    @BindView(R.id.web_detail)
    WebView mWebDetail;
    @BindView(R.id.text_like)
    AppCompatTextView mTextLike;
    @BindView(R.id.text_comment)
    AppCompatTextView mTextComment;
    @BindView(R.id.btn_up)
    AppCompatImageButton mBtnUp;
    @BindView(R.id.btn_down)
    AppCompatImageButton mBtnDown;

    private DetailsInfo mDetailsInfo = null;
    private DetailsExtraInfo mDetailsExtraInfo = null;

    private ArrayList<Integer> mStoryIds = null;

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
        SystemStatusBarUtil.setTranslucentStatusBar(this);
        initAppBar();
        initWeb();
        initUpDownButton();
    }

    @Override
    protected void initData() {
        LogUtil.d(TAG, "initData");
        Intent intent = getIntent();
        if (intent != null) {
            mStoryIds = intent.getIntegerArrayListExtra(Constant.EXTRA_ID_LIST);
            mCurrentId = intent.getIntExtra(Constant.EXTRA_ID, -1);
        }
        if (mCurrentId != -1) {
            presenter.getDetailsInfo(bindToLifecycle(), mCurrentId);
        } else {
            onError("");
        }
        if (mStoryIds != null) {
            mCurrentIndex = mStoryIds.indexOf(mCurrentId);
        }

        mWebDetail.getSettings().setBlockNetworkImage(!AppSettings.getInstance().isGraphBrowsing());
    }

    private void initAppBar() {
        LogUtil.d(TAG, "initAppBar");
        mLayoutAppBar.addOnOffsetChangedListener((appBarLayout, i) -> {
            // 0全部展开
            if (i == 0) {
                SystemStatusBarUtil.unlockStatusBar(DetailsActivity.this);
            } else {
                SystemStatusBarUtil.lockStatusBar(DetailsActivity.this);
            }
        });
    }

    private void initWeb() {
        LogUtil.d(TAG, "initWeb");
        mWebDetail.setWebViewClient(new WebViewClient() {

            /**
             * 5.0之前有效
             */
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                if (AppSettings.getInstance().isBuiltInBrowser()) {
                    ActivityHelper.startBrowserActivity(DetailsActivity.this, url);
                } else {
                    Intent intent = new Intent();
                    intent.setAction("android.intent.action.VIEW");
                    Uri uri = Uri.parse(url);
                    intent.setData(uri);
                    DetailsActivity.this.startActivity(intent);
                }
                return true;
            }

            /**
             * 5.0之后使用该方法
             * @param view
             * @param request
             * @return
             */
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
                    return false;
                }
                if (AppSettings.getInstance().isBuiltInBrowser()) {
                    ActivityHelper.startBrowserActivity(DetailsActivity.this, request.getUrl().toString());
                } else {
                    Intent intent = new Intent();
                    intent.setAction("android.intent.action.VIEW");
                    intent.setData(request.getUrl());
                    DetailsActivity.this.startActivity(intent);
                }
                return true;
            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
            }
        });
    }

    private void initUpDownButton() {
        LogUtil.d(TAG, "initUpDownButton");
        // throttleFirst() 在一秒之内，只发送第一次事件
        RxView.clicks(mBtnUp)
                .throttleFirst(1, TimeUnit.SECONDS)
                .subscribe(o -> {
                    if (mCurrentIndex == 0) {
                        return;
                    }
                    mCurrentIndex--;
                    presenter.getDetailsInfo(bindToLifecycle(), mStoryIds.get(mCurrentIndex));
                });

        RxView.clicks(mBtnDown)
                .throttleFirst(1, TimeUnit.SECONDS)
                .subscribe(o -> {
                    if (mCurrentIndex == mStoryIds.size() - 1) {
                        return;
                    }
                    mCurrentIndex++;
                    presenter.getDetailsInfo(bindToLifecycle(), mStoryIds.get(mCurrentIndex));
                });
    }

    @OnClick(R.id.btn_share)
    public void onClickShare() {
        LogUtil.d(TAG, "onClickShare");
        ActivityHelper.share(this, mDetailsInfo.getTitle(), mDetailsInfo.getId());
    }

    @OnClick(R.id.btn_comment)
    public void onClickComment() {
        LogUtil.d(TAG, "onClickComment");
        ActivityHelper.startCommentActivity(this, mDetailsExtraInfo.getComments(),
                mDetailsExtraInfo.getLongComments(), mDetailsExtraInfo.getShortComments(),
                mDetailsInfo.getId());
    }

    @Override
    public void onDetailsInfo(DetailsInfo detailsInfo) {
        LogUtil.d(TAG, "onDetailsInfo : detailsInfo = " + detailsInfo);
        if (detailsInfo == null) {
            return;
        }
        mDetailsInfo = detailsInfo;
        if (AppSettings.getInstance().isGraphBrowsing()) {
            CustomGlideModule.loadImage(getApplicationContext(), detailsInfo.getImage(), mImagePreview);
        } else {
            mImagePreview.setImageResource(R.drawable.pic_default_placeholder);
        }

        try {
            RxTextView.text(mTextTitle).accept(detailsInfo.getTitle());
            RxTextView.text(mTextSource).accept(detailsInfo.getImage_source());
        } catch (Exception e) {
            e.printStackTrace();
        }
        String html = HtmlUtil.getHtml(detailsInfo);
        mWebDetail.loadData(html, HtmlUtil.MIME_TYPE, HtmlUtil.ENCODED);
    }

    @Override
    public void onDetailsExtraInfo(DetailsExtraInfo detailsExtraInfo) {
        LogUtil.d(TAG, "onDetailsExtraInfo : detailsExtraInfo = " + detailsExtraInfo);
        if (detailsExtraInfo == null) {
            return;
        }
        mDetailsExtraInfo = detailsExtraInfo;
        mTextLike.setText(detailsExtraInfo.getPopularity() >= 99 ?
                StringUtil.findStringById(this, R.string.details_extra_info) :
                String.valueOf(detailsExtraInfo.getPopularity()));
        mTextComment.setText(detailsExtraInfo.getComments() >= 99 ?
                StringUtil.findStringById(this, R.string.details_extra_info) :
                String.valueOf(detailsExtraInfo.getComments()));
    }

    @Override
    public void onError(String msg) {
        LogUtil.d(TAG, "onError : msg = " + msg);
    }
}
