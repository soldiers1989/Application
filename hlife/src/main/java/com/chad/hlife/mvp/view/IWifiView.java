package com.chad.hlife.mvp.view;

import com.chad.hlife.entity.juhe.WifiInfo;
import com.chad.hlife.mvp.base.IBaseView;

public interface IWifiView extends IBaseView {

    void onWifiInfo(WifiInfo wifiInfo);
}
