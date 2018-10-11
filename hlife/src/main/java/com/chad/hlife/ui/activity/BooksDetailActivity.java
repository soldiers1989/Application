package com.chad.hlife.ui.activity;

import android.content.Intent;
import android.text.TextUtils;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.chad.hlife.R;
import com.chad.hlife.app.AppConstant;
import com.chad.hlife.ui.base.BaseRxAppCompatActivity;
import com.chad.hlife.util.LogUtil;

import butterknife.BindView;

public class BooksDetailActivity extends BaseRxAppCompatActivity {

    private static final String TAG = BooksDetailActivity.class.getSimpleName();

    @BindView(R.id.view_web)
    WebView mWebView;

    @Override
    protected int onGetLayoutId() {
        return R.layout.activity_books_detail;
    }

    @Override
    protected void onInitView() {
        LogUtil.d(TAG, "onInitView");
        initWebView();
    }

    private void initWebView() {
        LogUtil.d(TAG, "initWebView");
        WebSettings webSettings = mWebView.getSettings();
        // 支持JS
        webSettings.setJavaScriptEnabled(true);
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true);
        webSettings.setBuiltInZoomControls(true);
        webSettings.setDisplayZoomControls(true);
        webSettings.setLoadWithOverviewMode(true);
        // 多窗口
        webSettings.supportMultipleWindows();
        // 自适应屏幕
        webSettings.setUseWideViewPort(true);
        webSettings.setLoadWithOverviewMode(true);
        // 支持缩放
        webSettings.setSupportZoom(false);
        // 隐藏原生缩放控件
        webSettings.setDisplayZoomControls(false);
        // 支持内容重新布局
        webSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        webSettings.supportMultipleWindows();
        webSettings.setSupportMultipleWindows(true);
        // 设置缓存模式
        webSettings.setDomStorageEnabled(true);
        webSettings.setDatabaseEnabled(true);
        webSettings.setCacheMode(WebSettings.LOAD_DEFAULT);
        webSettings.setAppCacheEnabled(true);
        webSettings.setAppCachePath(mWebView.getContext().getCacheDir().getAbsolutePath());
        // 设置可访问文件
        webSettings.setAllowFileAccess(true);
        webSettings.setNeedInitialFocus(true);
        webSettings.setNeedInitialFocus(true);
        // 自动加载图片
        webSettings.setLoadsImagesAutomatically(true);
        // 设定编码格式
        webSettings.setDefaultTextEncodingName("UTF-8");

        // 支持获取手势焦点
        mWebView.requestFocusFromTouch();
        mWebView.setHorizontalFadingEdgeEnabled(true);
        mWebView.setVerticalFadingEdgeEnabled(false);
        mWebView.setVerticalScrollBarEnabled(false);

        mWebView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                return true;
            }
        });
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
        String url = intent.getStringExtra(AppConstant.EXTRA_URL);
        if (!TextUtils.isEmpty(url)) {
            mWebView.loadUrl(url);
        }
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
