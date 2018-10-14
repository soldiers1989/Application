package com.chad.hlife.mvp.presenter.mob.oilprices;

import com.chad.hlife.entity.mob.OilPricesInfo;
import com.chad.hlife.mvp.base.BasePresenter;
import com.chad.hlife.mvp.model.mob.OilPricesModel;
import com.chad.hlife.mvp.view.mob.IOilPricesView;

import io.reactivex.ObservableTransformer;

public class OilPricesPresenter extends BasePresenter<IOilPricesView> implements IOilPricesPresenter {

    public void getOilPricesInfo(ObservableTransformer transformer, String key) {
        OilPricesModel.getInstance().getOilPricesInfo(transformer, key, this);
    }

    @Override
    public void onOilPricesInfo(OilPricesInfo oilPricesInfo) {
        getView().onOilPricesInfo(oilPricesInfo);
    }

    @Override
    public void onError(Object object) {
        getView().onError(object);
    }
}
