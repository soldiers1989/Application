package com.chad.hlife.ui.zhihu.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Build;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.AppCompatImageButton;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.chad.hlife.R;
import com.chad.hlife.app.AppConstant;
import com.chad.hlife.entity.zhihu.DetailExtraInfo;
import com.chad.hlife.entity.zhihu.DetailInfo;
import com.chad.hlife.glide.CustomGlideModule;
import com.chad.hlife.helper.ActivityHelper;
import com.chad.hlife.mvp.presenter.zhihu.detail.DetailPresenter;
import com.chad.hlife.mvp.view.zhihu.IDetailView;
import com.chad.hlife.ui.base.BaseMvpAppCompatActivity;
import com.chad.hlife.util.HtmlUtil;
import com.chad.hlife.util.LogUtil;
import com.chad.hlife.util.StatusBarUtil;
import com.jakewharton.rxbinding2.view.RxView;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.OnClick;

public class DetailActivity extends BaseMvpAppCompatActivity<IDetailView, DetailPresenter>
        implements IDetailView {

    private static final String TAG = DetailActivity.class.getSimpleName();

    @BindView(R.id.image_preview)
    AppCompatImageView mImagePreview;
    @BindView(R.id.text_title)
    AppCompatTextView mTextTitle;
    @BindView(R.id.text_source)
    AppCompatTextView mTextSource;
    @BindView(R.id.view_web)
    WebView mWebView;
    @BindView(R.id.text_like)
    AppCompatTextView mTextLike;
    @BindView(R.id.text_comment)
    AppCompatTextView mTextComment;
    @BindView(R.id.btn_previous)
    AppCompatImageButton mBtnPrevious;
    @BindView(R.id.btn_next)
    AppCompatImageButton mBtnNext;
    @BindView(R.id.layout_loading)
    ConstraintLayout mLoading;

    private ArrayList<Integer> mStoryIds;

    private DetailInfo mDetailInfo;
    private DetailExtraInfo mDetailExtraInfo;

    private int mCurrentId;
    private int mCurrentIndex;

    @Override
    protected DetailPresenter onGetPresenter() {
        return new DetailPresenter();
    }

    @Override
    protected int onGetLayoutId() {
        return R.layout.activity_detail;
    }

    @Override
    protected void onInitView() {
        LogUtil.d(TAG, "onInitView");
        StatusBarUtil.setStatusBarColor(this, getResources().getColor(AppConstant.COLOR_STATUS_BAR_BLACK));
        initWebView();
        initPageButton();
    }

    private void initWebView() {
        LogUtil.d(TAG, "initWebView");
        mWebView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    view.loadUrl(request.getUrl().toString());
                } else {
                    view.loadUrl(request.toString());
                }
                return true;
            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                if (mLoading != null && mLoading.getVisibility() == View.GONE) {
                    mLoading.setVisibility(View.VISIBLE);
                }
                super.onPageStarted(view, url, favicon);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                if (mLoading != null && mLoading.getVisibility() == View.VISIBLE) {
                    mLoading.setVisibility(View.GONE);
                }
                super.onPageFinished(view, url);
            }
        });
    }

    private void initPageButton() {
        LogUtil.d(TAG, "initPageButton");
        // throttleFirst() 在一秒之内，只发送第一次事件
        RxView.clicks(mBtnPrevious)
                .throttleFirst(1, TimeUnit.SECONDS)
                .subscribe(o -> {
                    if (mCurrentIndex == 0) {
                        Toast.makeText(getApplicationContext(), R.string.first_one, Toast.LENGTH_SHORT).show();
                        return;
                    }
                    mCurrentIndex--;
                    presenter.getDetailInfo(bindToLifecycle(), mStoryIds.get(mCurrentIndex));
                });

        RxView.clicks(mBtnNext)
                .throttleFirst(1, TimeUnit.SECONDS)
                .subscribe(o -> {
                    if (mCurrentIndex == mStoryIds.size() - 1) {
                        Toast.makeText(getApplicationContext(), R.string.last_one, Toast.LENGTH_SHORT).show();
                        return;
                    }
                    mCurrentIndex++;
                    presenter.getDetailInfo(bindToLifecycle(), mStoryIds.get(mCurrentIndex));
                });
    }

    @Override
    protected void onInitData() {
        LogUtil.d(TAG, "onInitData");
        mStoryIds = new ArrayList<>();
        handleIntent(getIntent());
    }

    private void handleIntent(Intent intent) {
        LogUtil.d(TAG, "handleIntent : intent = " + (intent == null ? "Null" : "Not Null"));
        if (intent == null) {
            return;
        }
        mStoryIds.addAll(intent.getIntegerArrayListExtra(AppConstant.EXTRA_LIST_ID));
        mCurrentId = intent.getIntExtra(AppConstant.EXTRA_ID, -1);
        if (mCurrentId != -1) {
            presenter.getDetailInfo(bindToLifecycle(), mCurrentId);
        }
        if (mStoryIds != null && mStoryIds.size() > 0) {
            mCurrentIndex = mStoryIds.indexOf(mCurrentId);
        }
    }

    @OnClick(R.id.btn_comment)
    public void onCommentClick() {
        LogUtil.d(TAG, "onCommentClick");
        ActivityHelper.startCommentsActivity(this, mDetailExtraInfo.getComments(),
                mDetailExtraInfo.getLongComments(), mDetailExtraInfo.getShortComments(), mDetailInfo.getId());
    }

    @Override
    public void onDetailInfo(DetailInfo detailInfo) {
        LogUtil.d(TAG, "onDetailInfo : detailInfo = " + detailInfo);
        if (detailInfo == null) {
            return;
        }
        mDetailInfo = detailInfo;
        CustomGlideModule.loadCenterCrop(getApplicationContext(), detailInfo.getImage(), mImagePreview);
        mTextTitle.setText(detailInfo.getTitle());
        mTextSource.setText(getString(R.string.source) + "：" + detailInfo.getImage_source());
        String html = HtmlUtil.getHtml(detailInfo);
        mWebView.loadData(html, HtmlUtil.MIME_TYPE, HtmlUtil.ENCODED);
    }

    @Override
    public void onDetailExtraInfo(DetailExtraInfo detailExtraInfo) {
        LogUtil.d(TAG, "onDetailExtraInfo : detailExtraInfo = " + detailExtraInfo);
        if (detailExtraInfo == null) {
            return;
        }
        mDetailExtraInfo = detailExtraInfo;
        mTextLike.setText(detailExtraInfo.getPopularity() >= 99 ?
                getText(R.string.greater_than_99) : String.valueOf(detailExtraInfo.getPopularity()));
        mTextComment.setText(detailExtraInfo.getComments() >= 99 ?
                getText(R.string.greater_than_99) : String.valueOf(detailExtraInfo.getComments()));
    }

    @Override
    public void onError(Object object) {
        LogUtil.d(TAG, "onError");
    }
}
