package com.chad.hlife.mvp.model.mob;

import com.chad.hlife.entity.mob.OilPriceInfo;
import com.chad.hlife.mvp.presenter.mob.oilprice.IOilPricePresenter;
import com.chad.hlife.retrofit.HLifeRetrofit;
import com.chad.hlife.util.RxSchedulersUtil;

import io.reactivex.ObservableTransformer;

public class OilPriceModel {

    private static volatile OilPriceModel mOilPriceModel = null;

    public static OilPriceModel getInstance() {
        synchronized (OilPriceModel.class) {
            if (mOilPriceModel == null) {
                mOilPriceModel = new OilPriceModel();
            }
        }
        return mOilPriceModel;
    }

    private OilPriceModel() {
    }

    public void getOilPriceInfo(ObservableTransformer transformer, String key, IOilPricePresenter oilPricePresenter) {
        HLifeRetrofit.getOilPriceInfo(key)
                .compose(transformer)
                .compose(RxSchedulersUtil.workThread())
                .subscribe(o -> oilPricePresenter.onOilPriceInfo((OilPriceInfo) o),
                        throwable -> oilPricePresenter.onError(throwable));
    }
}
