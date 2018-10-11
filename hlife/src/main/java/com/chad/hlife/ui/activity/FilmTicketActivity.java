package com.chad.hlife.ui.activity;

import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.chad.hlife.R;
import com.chad.hlife.app.config.JuHeConfig;
import com.chad.hlife.entity.juhe.FilmTicketInfo;
import com.chad.hlife.mvp.presenter.film.FilmTicketPresenter;
import com.chad.hlife.mvp.view.IFilmTicketView;
import com.chad.hlife.ui.base.BaseMvpAppCompatActivity;
import com.chad.hlife.util.LogUtil;
import com.chad.hlife.util.StringUtil;

import butterknife.BindView;

public class FilmTicketActivity extends BaseMvpAppCompatActivity<IFilmTicketView, FilmTicketPresenter>
        implements IFilmTicketView {

    private static final String TAG = FilmTicketActivity.class.getSimpleName();

    @BindView(R.id.view_web)
    WebView mWebView;

    @Override
    protected FilmTicketPresenter onGetPresenter() {
        return new FilmTicketPresenter();
    }

    @Override
    protected int onGetLayoutId() {
        return R.layout.activity_film_ticket;
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
        presenter.getFilmTicketInfo(bindToLifecycle(), JuHeConfig.KEY_FILM_TICKET);
    }

    @Override
    public void onBackPressed() {
        if (mWebView.canGoBack()) {
            mWebView.goBack();
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public void onFilmTicketInfo(FilmTicketInfo filmTicketInfo) {
        LogUtil.d(TAG, "onFilmTicketInfo : filmTicketInfo = "
                + (filmTicketInfo == null ? "Null" : "Not Null"));
        if (filmTicketInfo == null) {
            return;
        }
        String url = filmTicketInfo.getResult().getH5url();
        mWebView.loadUrl(StringUtil.formatUrl(url));
    }

    @Override
    public void onError(Object object) {
        LogUtil.d(TAG, "onError");
    }
}
