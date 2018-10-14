package com.chad.hlife.ui.juhe.activity;

import android.os.Build;
import android.support.constraint.ConstraintLayout;
import android.view.View;
import android.webkit.GeolocationPermissions;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.chad.hlife.R;
import com.chad.hlife.app.AppConstant;
import com.chad.hlife.app.config.JuHeConfig;
import com.chad.hlife.entity.juhe.FilmTicketInfo;
import com.chad.hlife.helper.WebViewHelper;
import com.chad.hlife.mvp.presenter.juhe.film.FilmTicketPresenter;
import com.chad.hlife.mvp.view.juhe.IFilmTicketView;
import com.chad.hlife.ui.base.BaseMvpAppCompatActivity;
import com.chad.hlife.util.LogUtil;
import com.chad.hlife.util.StringUtil;
import com.chad.hlife.util.StatusBarUtil;

import butterknife.BindView;

public class FilmTicketActivity extends BaseMvpAppCompatActivity<IFilmTicketView, FilmTicketPresenter>
        implements IFilmTicketView {

    private static final String TAG = FilmTicketActivity.class.getSimpleName();

    @BindView(R.id.view_web)
    WebView mWebView;
    @BindView(R.id.layout_loading)
    ConstraintLayout mLoading;

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

        mWebView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onGeolocationPermissionsShowPrompt(String origin, GeolocationPermissions.Callback callback) {
                callback.invoke(origin, true, true);
                super.onGeolocationPermissionsShowPrompt(origin, callback);
            }
        });
    }

    @Override
    protected void onInitData() {
        LogUtil.d(TAG, "onInitData");
        presenter.getFilmTicketInfo(bindToLifecycle(), JuHeConfig.KEY_FILM_TICKET);
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

    @Override
    public void onBackPressed() {
        if (mWebView.canGoBack()) {
            mWebView.goBack();
        } else {
            super.onBackPressed();
        }
    }
}
