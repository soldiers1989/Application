package com.chad.hlife.mvp.model.mob;

import com.chad.hlife.entity.mob.OilPricesInfo;
import com.chad.hlife.mvp.presenter.mob.oilprices.IOilPricesPresenter;
import com.chad.hlife.retrofit.HLifeRetrofit;
import com.chad.hlife.util.RxSchedulersUtil;

import io.reactivex.ObservableTransformer;

public class OilPricesModel {

    private static volatile OilPricesModel mOilPricesModel = null;

    public static OilPricesModel getInstance() {
        synchronized (OilPricesModel.class) {
            if (mOilPricesModel == null) {
                mOilPricesModel = new OilPricesModel();
            }
        }
        return mOilPricesModel;
    }

    private OilPricesModel() {
    }

    public void getOilPricesInfo(ObservableTransformer transformer, String key, IOilPricesPresenter oilPricesPresenter) {
        HLifeRetrofit.getOilPricesInfo(key)
                .compose(transformer)
                .compose(RxSchedulersUtil.workThread())
                .subscribe(o -> oilPricesPresenter.onOilPricesInfo((OilPricesInfo) o),
                        throwable -> oilPricesPresenter.onError(throwable));
    }
}
