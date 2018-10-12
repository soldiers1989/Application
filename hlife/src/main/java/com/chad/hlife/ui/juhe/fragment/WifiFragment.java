package com.chad.hlife.ui.juhe.fragment;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.chad.hlife.R;
import com.chad.hlife.app.AppConstant;
import com.chad.hlife.app.config.JuHeConfig;
import com.chad.hlife.entity.juhe.WifiInfo;
import com.chad.hlife.helper.ActivityHelper;
import com.chad.hlife.mvp.presenter.juhe.wifi.WifiPresenter;
import com.chad.hlife.mvp.view.juhe.IWifiView;
import com.chad.hlife.ui.base.BaseMvpFragment;
import com.chad.hlife.ui.juhe.adapter.WifiAdapter;
import com.chad.hlife.util.LogUtil;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;

public class WifiFragment extends BaseMvpFragment<IWifiView, WifiPresenter>
        implements IWifiView {

    private static final String TAG = WifiFragment.class.getSimpleName();

    @BindView(R.id.view_recycler)
    RecyclerView mRecyclerView;
    @BindView(R.id.text_location)
    AppCompatTextView mTextLocation;

    private WifiAdapter mWifiAdapter;
    private LocationManager mLocationManager;

    @Override
    protected WifiPresenter onGetPresenter() {
        return new WifiPresenter();
    }

    @Override
    protected int onGetLayoutId() {
        return R.layout.fragment_wifi;
    }

    @Override
    protected void onInitView() {
        LogUtil.d(TAG, "onInitView");
        initRecyclerView();
    }

    private void initRecyclerView() {
        LogUtil.d(TAG, "initRecyclerView");
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mRecyclerView.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
        mWifiAdapter = new WifiAdapter(getContext());
        mRecyclerView.setAdapter(mWifiAdapter);
    }

    @Override
    protected void onInitData() {
        LogUtil.d(TAG, "onInitData");
        mLocationManager = (LocationManager) getContext().getSystemService(Context.LOCATION_SERVICE);
        checkPermissionAndGetWifiInfo();
    }

    private void checkPermissionAndGetWifiInfo() {
        LogUtil.d(TAG, "checkPermissionAndGetWifiInfo : mLocationManager = " + mLocationManager);
        if (mLocationManager == null) {
            return;
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION)
                    != PackageManager.PERMISSION_GRANTED
                    && ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_COARSE_LOCATION)
                    != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION,
                                Manifest.permission.ACCESS_COARSE_LOCATION},
                        AppConstant.REQUEST_CODE_LOCATION_PERMISSION);
            } else {
                Location location = mLocationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
                getWifiInfo(location);
            }
        } else {
            Location location = mLocationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            getWifiInfo(location);
        }
    }

    private void getWifiInfo(Location location) {
        LogUtil.d(TAG, "getWifiInfo : mLocationManager = " + mLocationManager);
        if (mLocationManager == null) {
            return;
        }
        if (mLocationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            if (location != null) {
                presenter.getWifiInfo(bindToLifecycle(), location.getLongitude(), location.getLatitude(),
                        AppConstant.TYPE_WIFI_GOOGLE, JuHeConfig.KEY_WIFI);
            } else {
                mTextLocation.setText(R.string.relocation);
                mTextLocation.setEnabled(true);
                Toast.makeText(getContext(), R.string.position_acquisition_failure, Toast.LENGTH_SHORT).show();
            }
        } else {
            showGpsDialog();
        }
    }

    private void showGpsDialog() {
        LogUtil.d(TAG, "showGpsDialog");
        final AlertDialog.Builder dialog = new AlertDialog.Builder(getContext());
        dialog.setTitle(R.string.dialog_title);
        dialog.setMessage(R.string.dialog_message);
        dialog.setNegativeButton(R.string.dialog_negative, (dialogInterface, i) ->
                ActivityHelper.startGpsSettingsActivity(getActivity(), AppConstant.REQUEST_CODE_GPS_SETTINGS));
        dialog.setPositiveButton(R.string.dialog_positive, (dialogInterface, i) ->
                dialogInterface.dismiss());
        dialog.show();
    }

    @OnClick(R.id.text_location)
    public void onLocationClick() {
        LogUtil.d(TAG, "onLocationClick");
        mTextLocation.setText(R.string.in_position);
        mTextLocation.setEnabled(false);
        Observable.timer(1, TimeUnit.SECONDS)
                .compose(bindToLifecycle())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(aLong -> checkPermissionAndGetWifiInfo());
    }

    @Override
    public void onWifiInfo(WifiInfo wifiInfo) {
        LogUtil.d(TAG, "onWifiInfo : wifiInfo = " + (wifiInfo == null ? "Null" : "Not Null"));
        if (wifiInfo == null) {
            return;
        }
        mTextLocation.setText(R.string.relocation);
        mTextLocation.setEnabled(true);
        mWifiAdapter.setData(wifiInfo.getResult().getData());
    }

    @Override
    public void onError(Object object) {
        LogUtil.d(TAG, "onError");
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        LogUtil.d(TAG, "onRequestPermissionsResult : requestCode = " + requestCode);
        switch (requestCode) {
            case AppConstant.REQUEST_CODE_LOCATION_PERMISSION:
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED
                        && grantResults[1] == PackageManager.PERMISSION_GRANTED) {
                    checkPermissionAndGetWifiInfo();
                } else {
                    if (!ActivityCompat.shouldShowRequestPermissionRationale(getActivity(),
                            Manifest.permission.ACCESS_COARSE_LOCATION)
                            || !ActivityCompat.shouldShowRequestPermissionRationale(getActivity(),
                            Manifest.permission.ACCESS_COARSE_LOCATION)) {
                        showGpsDialog();
                    }
                }
                break;
            default:
                break;
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        LogUtil.d(TAG, "onActivityResult : requestCode = " + requestCode + " , resultCode = " + resultCode);
        switch (requestCode) {
            case AppConstant.REQUEST_CODE_GPS_SETTINGS:
                break;
            default:
                break;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
