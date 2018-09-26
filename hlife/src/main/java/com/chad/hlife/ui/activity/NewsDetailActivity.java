package com.chad.hlife.ui.activity;

import android.support.v7.widget.Toolbar;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import com.chad.hlife.R;
import com.chad.hlife.ui.base.BaseRxAppCompatActivity;
import com.chad.hlife.util.LogUtil;

import butterknife.BindView;

public class NewsDetailActivity extends BaseRxAppCompatActivity {

    private static final String TAG = NewsDetailActivity.class.getSimpleName();

    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.bar_progress)
    ProgressBar mProgressBar;
    @BindView(R.id.view_web)
    WebView mWebView;

    @Override
    protected int onGetLayoutId() {
        return R.layout.activity_news_detail;
    }

    @Override
    protected void onInitView() {
        LogUtil.d(TAG, "onInitView");
        initToolbar();
        initWebView();
    }

    private void initToolbar() {
        LogUtil.d(TAG, "initToolbar");
        mToolbar.setNavigationIcon(R.drawable.ic_close_dark);
        mToolbar.setNavigationOnClickListener(v -> onBackPressed());
    }

    private void initWebView() {
        LogUtil.d(TAG, "initWebView");
        WebSettings webSettings = mWebView.getSettings();
        webSettings.setSupportZoom(true);
        webSettings.setUseWideViewPort(true);
        mWebView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                return true;
            }
        });
        mWebView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                if (newProgress == 100) {
                    mProgressBar.setProgress(newProgress);
                    mProgressBar.setVisibility(View.INVISIBLE);
                } else {
                    mProgressBar.setVisibility(View.VISIBLE);
                    mProgressBar.setProgress(newProgress);
                }
                super.onProgressChanged(view, newProgress);
            }
        });
    }

    @Override
    protected void onInitData() {
        LogUtil.d(TAG, "onInitData");
        mWebView.loadUrl("http://mini.eastday.com/mobile/170105110355287.html?qid=juheshuju");
    }

    @Override
    public void onBackPressed() {
        if (mWebView.canGoBack()) {
            mWebView.goBack();
        } else {
            super.onBackPressed();
        }
    }
}
