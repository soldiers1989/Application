package com.chad.hlife.mvp.presenter.mob.oilprice;

import com.chad.hlife.entity.mob.OilPriceInfo;
import com.chad.hlife.mvp.base.BasePresenter;
import com.chad.hlife.mvp.model.mob.OilPriceModel;
import com.chad.hlife.mvp.view.mob.IOilPriceView;

import io.reactivex.ObservableTransformer;

public class OilPricePresenter extends BasePresenter<IOilPriceView> implements IOilPricePresenter {

    public void getOilPriceInfo(ObservableTransformer transformer, String key) {
        OilPriceModel.getInstance().getOilPriceInfo(transformer, key, this);
    }

    @Override
    public void onOilPriceInfo(OilPriceInfo oilPriceInfo) {
        getView().onOilPriceInfo(oilPriceInfo);
    }

    @Override
    public void onError(Object object) {
        getView().onError(object);
    }
}
