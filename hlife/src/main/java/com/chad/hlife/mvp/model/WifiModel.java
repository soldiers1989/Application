package com.chad.hlife.mvp.model;

import com.chad.hlife.entity.juhe.WifiInfo;
import com.chad.hlife.mvp.presenter.wifi.IWifiPresenter;
import com.chad.hlife.retrofit.HLifeRetrofit;
import com.chad.hlife.util.RxSchedulersUtil;

import io.reactivex.ObservableTransformer;

public class WifiModel {

    private static volatile WifiModel mWifiModel = null;

    public static WifiModel getInstance() {
        synchronized (WifiModel.class) {
            if (mWifiModel == null) {
                mWifiModel = new WifiModel();
            }
        }
        return mWifiModel;
    }

    private WifiModel() {
    }

    public void getWifiInfo(ObservableTransformer transformer, double lon, double lat, int gtype,
                             String key, IWifiPresenter wifiPresenter) {
        HLifeRetrofit.getWifiInfo(lon, lat, gtype, key)
                .compose(transformer)
                .compose(RxSchedulersUtil.workThread())
                .subscribe(o -> wifiPresenter.onWifiInfo((WifiInfo) o),
                        throwable -> wifiPresenter.onError(throwable));
    }
}
