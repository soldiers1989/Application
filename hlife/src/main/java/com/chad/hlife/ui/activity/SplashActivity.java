package com.chad.hlife.ui.activity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;

import com.chad.hlife.HLifeApplication;
import com.chad.hlife.R;
import com.chad.hlife.app.AppConstant;
import com.chad.hlife.app.AppSettings;
import com.chad.hlife.app.config.ADConfig;
import com.chad.hlife.helper.ActivityHelper;
import com.chad.hlife.helper.WeiBoAuthHelper;
import com.chad.hlife.ui.base.BaseRxAppCompatActivity;
import com.chad.hlife.util.LogUtil;
import com.qq.e.ads.splash.SplashAD;
import com.qq.e.ads.splash.SplashADListener;
import com.qq.e.comm.util.AdError;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;

public class SplashActivity extends BaseRxAppCompatActivity implements SplashADListener {

    private static final String TAG = SplashActivity.class.getSimpleName();

    @BindView(R.id.image_logo)
    AppCompatImageView mImageLogo;
    @BindView(R.id.layout_content)
    ConstraintLayout mContentLayout;
    @BindView(R.id.text_skip)
    AppCompatTextView mTextSkip;

    private boolean isCanSkip = false;

    @Override
    protected int onGetLayoutId() {
        return R.layout.activity_splash;
    }

    @Override
    protected void onInitView() {
        LogUtil.d(TAG, "initViews");
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }

    @Override
    protected void onInitData() {
        LogUtil.d(TAG, "initData");
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            checkAndRequestPermission();
        } else {
            fetchSplashAD();
        }
    }

    @Override
    public void onADDismissed() {
        LogUtil.d(TAG, "onADDismissed");
        if (isCanSkip) {
            checkLoginStatus();
        }
    }

    @Override
    public void onNoAD(AdError adError) {
        LogUtil.d(TAG, "adError : code = " + adError.getErrorCode()
                + " , msg = " + adError.getErrorMsg());
        Observable.timer(1, TimeUnit.SECONDS)
                .compose(bindToLifecycle())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(aLong -> checkLoginStatus());
    }

    @Override
    public void onADPresent() {
        LogUtil.d(TAG, "onADPresent");
        mImageLogo.setVisibility(View.GONE);
    }

    @Override
    public void onADClicked() {
        LogUtil.d(TAG, "onADClicked");
    }

    @Override
    public void onADTick(long l) {
        LogUtil.d(TAG, "onADTick");
        mTextSkip.setText(getString(R.string.skip) + " " + (l / 1000f));
    }

    @Override
    public void onADExposure() {
        LogUtil.d(TAG, "onADExposure");
    }

    @OnClick(R.id.text_skip)
    public void onSkipClick() {
        LogUtil.d(TAG, "onSkipClick");
        checkLoginStatus();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        LogUtil.d(TAG, "onKeyDown");
        if (keyCode == KeyEvent.KEYCODE_BACK || keyCode == KeyEvent.KEYCODE_HOME) {
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onResume() {
        LogUtil.d(TAG, "onResume");
        isCanSkip = true;
        super.onResume();
    }

    @Override
    protected void onPause() {
        LogUtil.d(TAG, "onPause");
        isCanSkip = false;
        super.onPause();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        LogUtil.d(TAG, "onRequestPermissionsResult = " + requestCode);
        if (requestCode == AppConstant.PERMISSION_REQUEST_CODE && hasAllPermissionsGranted(grantResults)) {
            fetchSplashAD();
        } else {
            ActivityHelper.startDetailSettingsActivity(this, getPackageName());
            finish();
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void checkAndRequestPermission() {
        LogUtil.d(TAG, "checkAndRequestPermission");
        List<String> permissions = new ArrayList<>();
        if (!(checkSelfPermission(Manifest.permission.READ_PHONE_STATE) == PackageManager.PERMISSION_GRANTED)) {
            permissions.add(Manifest.permission.READ_PHONE_STATE);
        }
        if (!(checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED)) {
            permissions.add(Manifest.permission.WRITE_EXTERNAL_STORAGE);
        }
        if (!(checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED)) {
            permissions.add(Manifest.permission.ACCESS_FINE_LOCATION);
        }
        if (permissions.size() == 0) {
            fetchSplashAD();
        } else {
            String[] requestPermissions = new String[permissions.size()];
            permissions.toArray(requestPermissions);
            requestPermissions(requestPermissions, AppConstant.PERMISSION_REQUEST_CODE);
        }
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

    private void fetchSplashAD() {
        LogUtil.d(TAG, "fetchSplashAD");
        new SplashAD(this, mContentLayout, mTextSkip, ADConfig.APP_ID, ADConfig.AD_ID,
                this, 0);
    }

    private void checkLoginStatus() {
        LogUtil.d(TAG, "checkLoginStatus");
        switch (AppSettings.getInstance().getLoginModel()) {
            case AppConstant.LOGIN_MODEL_NULL:
                ActivityHelper.startLoginActivity(this);
                break;
            case AppConstant.LOGIN_MODEL_MOB:
                ActivityHelper.startMainActivity(this);
                break;
            case AppConstant.LOGIN_MODEL_WEIBO:
                boolean isSessionValid = WeiBoAuthHelper.getInstance(HLifeApplication.getHLifeApplication()).isSessionValid();
                if (isSessionValid) {
                    ActivityHelper.startMainActivity(this);
                } else {
                    ActivityHelper.startLoginActivity(this);
                }
                break;
            default:
                break;
        }
        finish();
    }
}
