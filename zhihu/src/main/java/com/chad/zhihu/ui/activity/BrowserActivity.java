package com.chad.zhihu.ui.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.constraint.ConstraintSet;
import android.support.v7.widget.AppCompatImageButton;
import android.support.v7.widget.Toolbar;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.chad.zhihu.R;
import com.chad.zhihu.app.Constant;
import com.chad.zhihu.ui.base.BaseRxAppCompatActivity;
import com.chad.zhihu.util.LogUtil;
import com.jakewharton.rxbinding2.view.RxView;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;

public class BrowserActivity extends BaseRxAppCompatActivity {

    private static final String TAG = BrowserActivity.class.getSimpleName();

    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.web_browser)
    WebView mWebBrowser;
    @BindView(R.id.btn_back)
    AppCompatImageButton mBtnBack;
    @BindView(R.id.btn_forward)
    AppCompatImageButton mBtnForward;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_browser;
    }

    @Override
    protected void initViews() {
        LogUtil.d(TAG, "initViews");
        initToolbar();
        initWeb();
        initNavigationButton();
    }

    @Override
    protected void initData() {
        LogUtil.d(TAG, "initData");
        Intent intent = new Intent();
        if (intent == null) {
            return;
        }
        String url = intent.getStringExtra(Constant.EXTRA_URL);
//        mWebBrowser.loadUrl(url);
    }

    private void initToolbar() {
        LogUtil.d(TAG, "initToolbar");
        setSupportActionBar(mToolbar);
    }

    private void initWeb() {
        LogUtil.d(TAG, "initWeb");
        mWebBrowser.setWebViewClient(new WebViewClient() {
            @SuppressLint("NewApi")
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                mWebBrowser.loadUrl(request.getUrl().toString());
                return true;
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
            }
        });
        mWebBrowser.loadUrl("http://www.baidu.com");

        mWebBrowser.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                switch (newProgress) {
                    case 100:
                        break;
                    default:
                        break;
                }
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
    }
}
