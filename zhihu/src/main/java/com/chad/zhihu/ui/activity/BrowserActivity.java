package com.chad.zhihu.ui.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Build;
import android.support.design.widget.AppBarLayout;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.AppCompatImageButton;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.ScrollView;

import com.chad.zhihu.R;
import com.chad.zhihu.app.AppSettings;
import com.chad.zhihu.app.Constant;
import com.chad.zhihu.ui.base.BaseRxAppCompatActivity;
import com.chad.zhihu.util.ColorUtil;
import com.chad.zhihu.util.LogUtil;
import com.chad.zhihu.util.SystemStatusBarUtil;
import com.jakewharton.rxbinding2.view.RxView;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;

public class BrowserActivity extends BaseRxAppCompatActivity {

    private static final String TAG = BrowserActivity.class.getSimpleName();

    @BindView(R.id.layout_appbar)
    AppBarLayout mLayoutAppBar;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.view_scroll)
    NestedScrollView mScrollView;
    @BindView(R.id.web_browser)
    WebView mWebBrowser;
    @BindView(R.id.bar_progress)
    ProgressBar mBarProgress;
    @BindView(R.id.btn_back)
    AppCompatImageButton mBtnBack;
    @BindView(R.id.btn_forward)
    AppCompatImageButton mBtnForward;
    @BindView(R.id.btn_refresh)
    AppCompatImageButton mBtnRefresh;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_browser;
    }

    @Override
    protected void initViews() {
        LogUtil.d(TAG, "initViews");
        SystemStatusBarUtil.setStatusBarColor(this,
                ColorUtil.findRgbById(this, R.color.colorStatusBar));
        SystemStatusBarUtil.lockStatusBar(this);
        initToolbar();
        initWeb();
        initNavigationButton();
    }

    @Override
    protected void initData() {
        LogUtil.d(TAG, "initData");
        Intent intent = getIntent();
        if (intent == null) {
            return;
        }
        String url = intent.getStringExtra(Constant.EXTRA_URL);
        mWebBrowser.loadUrl(url);
    }

    private void initToolbar() {
        LogUtil.d(TAG, "initToolbar");
        mToolbar.setTitleTextColor(Color.WHITE);
        mToolbar.setNavigationIcon(R.drawable.ic_toolbar_back);
        mToolbar.setNavigationOnClickListener(view -> onBackPressed());
    }

    private void initWeb() {
        LogUtil.d(TAG, "initWeb");
        WebSettings webSettings = mWebBrowser.getSettings();
        webSettings.setBlockNetworkImage(!AppSettings.getInstance().isGraphBrowsing());
        webSettings.setDomStorageEnabled(true);
        webSettings.setAppCacheEnabled(true);
        webSettings.setCacheMode(WebSettings.LOAD_DEFAULT);
        webSettings.setJavaScriptEnabled(true);

        mWebBrowser.setWebViewClient(new WebViewClient() {

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                mWebBrowser.loadUrl(url);
                return true;
            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    mWebBrowser.loadUrl(request.getUrl().toString());
                }
                return true;
            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                mToolbar.setTitle(url);
                mBarProgress.setVisibility(View.VISIBLE);
                mBarProgress.setMax(100);
                super.onPageStarted(view, url, favicon);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                mBarProgress.setVisibility(View.GONE);
                mScrollView.fullScroll(ScrollView.SCROLL_INDICATOR_TOP);
                super.onPageFinished(view, url);
            }
        });

        mWebBrowser.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                mBarProgress.setProgress(newProgress);
                super.onProgressChanged(view, newProgress);
            }
        });
    }

    private void initNavigationButton() {
        LogUtil.d(TAG, "initNavigationButton");
        // throttleFirst() 在一秒之内，只发送第一次事件
        RxView.clicks(mBtnBack)
                .throttleFirst(1, TimeUnit.SECONDS)
                .subscribe(o -> {
                    if (mWebBrowser.canGoBack()) {
                        mWebBrowser.goBack();
                    }
                });

        RxView.clicks(mBtnForward)
                .throttleFirst(1, TimeUnit.SECONDS)
                .subscribe(o -> {
                    if (mWebBrowser.canGoForward()) {
                        mWebBrowser.goForward();
                    }
                });

        RxView.clicks(mBtnRefresh)
                .throttleFirst(1, TimeUnit.SECONDS)
                .subscribe(o -> mWebBrowser.reload());
    }
}
