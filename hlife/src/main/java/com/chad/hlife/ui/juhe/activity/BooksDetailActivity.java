package com.chad.hlife.ui.juhe.activity;

import android.content.Intent;
import android.os.Build;
import android.support.constraint.ConstraintLayout;
import android.text.TextUtils;
import android.view.View;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.chad.hlife.R;
import com.chad.hlife.app.AppConstant;
import com.chad.hlife.helper.WebViewHelper;
import com.chad.hlife.ui.base.BaseRxAppCompatActivity;
import com.chad.hlife.util.LogUtil;
import com.chad.hlife.util.StatusBarUtil;

import butterknife.BindView;

public class BooksDetailActivity extends BaseRxAppCompatActivity {

    private static final String TAG = BooksDetailActivity.class.getSimpleName();

    @BindView(R.id.view_web)
    WebView mWebView;
    @BindView(R.id.layout_loading)
    ConstraintLayout mLoading;

    @Override
    protected int onGetLayoutId() {
        return R.layout.activity_books_detail;
    }

    @Override
    protected void onInitView() {
        LogUtil.d(TAG, "onInitView");
        StatusBarUtil.setStatusBarColor(this, getResources().getColor(AppConstant.COLOR_STATUS_BAR_RED));
        initWebView();
    }

    private void initWebView() {
        LogUtil.d(TAG, "initWebView");
        WebViewHelper.webViewSettings(mWebView);

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
            public void onPageFinished(WebView view, String url) {
                if (mLoading != null && mLoading.getVisibility() == View.VISIBLE) {
                    mLoading.setVisibility(View.GONE);
                }
                super.onPageFinished(view, url);
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
