package com.chad.hlife.helper;

import android.webkit.WebSettings;
import android.webkit.WebView;

import com.chad.hlife.app.AppConstant;

public class WebViewHelper {

    public static void webViewSettings(WebView webView) {
        if (webView == null) {
            return;
        }
        // 支持获取手势焦点
        webView.requestFocusFromTouch();
        webView.setHorizontalFadingEdgeEnabled(true);
        webView.setVerticalFadingEdgeEnabled(false);
        webView.setVerticalScrollBarEnabled(false);

        WebSettings webSettings = webView.getSettings();
        // 支持JS
        webSettings.setJavaScriptEnabled(true);
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true);
        webSettings.setBuiltInZoomControls(true);
        webSettings.setDisplayZoomControls(true);
        webSettings.setLoadWithOverviewMode(true);
        // 多窗口
        webSettings.setSupportMultipleWindows(true);
        // 自适应屏幕
        webSettings.setUseWideViewPort(true);
        webSettings.setLoadWithOverviewMode(true);
        // 支持缩放
        webSettings.setSupportZoom(false);
        // 隐藏原生缩放控件
        webSettings.setDisplayZoomControls(false);
        // 支持内容重新布局
        webSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        // 设置缓存模式
        webSettings.setCacheMode(WebSettings.LOAD_DEFAULT);
        webSettings.setDomStorageEnabled(true);
        webSettings.setDatabaseEnabled(true);
        webSettings.setAppCacheEnabled(true);
        webSettings.setAppCachePath(AppConstant.FILE_DIR_CACHE.getAbsolutePath());
        // 设置可访问文件
        webSettings.setAllowFileAccess(true);
        webSettings.setNeedInitialFocus(true);
        webSettings.setNeedInitialFocus(true);
        // 启动地理信息
        webSettings.setGeolocationEnabled(true);
        webSettings.setGeolocationDatabasePath(AppConstant.FILE_DIR_CACHE.getAbsolutePath());
        // 自动加载图片
        webSettings.setLoadsImagesAutomatically(true);
        // 设定编码格式
        webSettings.setDefaultTextEncodingName("UTF-8");
    }
}
