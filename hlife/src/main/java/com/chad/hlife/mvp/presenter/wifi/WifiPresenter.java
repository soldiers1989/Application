package com.chad.hlife.mvp.presenter.wifi;

import com.chad.hlife.entity.juhe.WifiInfo;
import com.chad.hlife.mvp.base.BasePresenter;
import com.chad.hlife.mvp.model.WifiModel;
import com.chad.hlife.mvp.view.IWifiView;

import io.reactivex.ObservableTransformer;

public class WifiPresenter extends BasePresenter<IWifiView> implements IWifiPresenter {

    public void getWifiInfo(ObservableTransformer transformer, double lon, double lat, int gtype,
                            String key) {
        WifiModel.getInstance().getWifiInfo(transformer, lon, lat, gtype, key, this);
    }

    @Override
    public void onWifiInfo(WifiInfo wifiInfo) {
        getView().onWifiInfo(wifiInfo);
    }

    @Override
    public void onError(Object object) {
        getView().onError(object);
    }
}
