package com.chad.hlife.ui.activity;

import android.Manifest;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.WindowManager;
import android.webkit.GeolocationPermissions;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.chad.hlife.R;
import com.chad.hlife.app.AppConstant;
import com.chad.hlife.helper.ActivityHelper;
import com.chad.hlife.helper.WebViewHelper;
import com.chad.hlife.ui.base.BaseRxAppCompatActivity;
import com.chad.hlife.ui.view.loading.DoubleCircleLoadingView;
import com.chad.hlife.util.LogUtil;
import com.chad.hlife.util.StatusBarUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class TaoTicketActivity extends BaseRxAppCompatActivity {

    private static final String TAG = TaoTicketActivity.class.getSimpleName();

    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.view_web)
    WebView mWebView;
    @BindView(R.id.layout_video)
    ConstraintLayout mVideoLayout;
    @BindView(R.id.layout_loading)
    ConstraintLayout mLoading;
    @BindView(R.id.view_loading)
    DoubleCircleLoadingView mLoadingView;

    @Override
    protected int onGetLayoutId() {
        return R.layout.activity_tao_ticket;
    }

    @Override
    protected void onInitView() {
        LogUtil.d(TAG, "onInitView");
        initColor();
        initToolbar();
        initWebView();
    }

    private void initColor() {
        LogUtil.d(TAG, "initColor");
        StatusBarUtil.setStatusBarColor(this, getResources().getColor(AppConstant.COLOR_STATUS_BAR_RED));
        mLoadingView.setColor(getResources().getColor(AppConstant.COLOR_STATUS_BAR_RED));
    }

    private void initToolbar() {
        LogUtil.d(TAG, "initToolbar");
        mToolbar.setTitle(R.string.tao_ticket);
        mToolbar.setTitleTextColor(Color.WHITE);
        mToolbar.setNavigationIcon(R.drawable.ic_close_light);
        mToolbar.setNavigationOnClickListener(v -> finish());
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

        mWebView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onGeolocationPermissionsShowPrompt(String origin, GeolocationPermissions.Callback callback) {
                callback.invoke(origin, true, false);
                super.onGeolocationPermissionsShowPrompt(origin, callback);
            }

            @Override
            public void onShowCustomView(View view, CustomViewCallback callback) {
                if (mVideoLayout.getVisibility() == View.VISIBLE) {
                    return;
                }
                setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
                setStatusBarVisibility(false);
                mVideoLayout.setVisibility(View.VISIBLE);
                mVideoLayout.addView(view);
                super.onShowCustomView(view, callback);
            }

            @Override
            public void onHideCustomView() {
                if (mVideoLayout.getVisibility() == View.GONE) {
                    return;
                }
                setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
                setStatusBarVisibility(true);
                mVideoLayout.removeAllViews();
                mVideoLayout.setVisibility(View.GONE);
                super.onHideCustomView();
            }
        });
    }

    @Override
    protected void onInitData() {
        LogUtil.d(TAG, "onInitData");
        checkPermissions();
        mWebView.loadUrl(AppConstant.URL_TAO_TICKET);
    }

    private void checkPermissions() {
        LogUtil.d(TAG, "checkPermissions");
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            return;
        }
        List<String> permissions = new ArrayList<>();
        if (!(checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED)) {
            permissions.add(Manifest.permission.ACCESS_FINE_LOCATION);
        }
        if (!(checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED)) {
            permissions.add(Manifest.permission.ACCESS_COARSE_LOCATION);
        }
        if (!(checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED)) {
            permissions.add(Manifest.permission.WRITE_EXTERNAL_STORAGE);
        }
        if (permissions.size() > 0) {
            String[] requestPermissions = new String[permissions.size()];
            permissions.toArray(requestPermissions);
            requestPermissions(requestPermissions, AppConstant.PERMISSION_REQUEST_CODE);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == AppConstant.PERMISSION_REQUEST_CODE && !hasAllPermissionsGranted(grantResults)) {
            ActivityHelper.startDetailSettingsActivity(this, getPackageName());
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    private boolean hasAllPermissionsGranted(int[] grantResults) {
        LogUtil.d(TAG, "hasAllPermissionsGranted");
        for (int grantResult : grantResults) {
            if (grantResult == PackageManager.PERMISSION_DENIED) {
                return false;
            }
        }
        return true;
    }

    @Override
    public void onBackPressed() {
        if (mWebView.canGoBack()) {
            mWebView.goBack();
        } else {
            super.onBackPressed();
        }
    }

    private void setStatusBarVisibility(boolean visible) {
        int flag = visible ? 0 : WindowManager.LayoutParams.FLAG_FULLSCREEN;
        getWindow().setFlags(flag, WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }
}
