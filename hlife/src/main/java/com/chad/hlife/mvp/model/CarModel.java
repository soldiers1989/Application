package com.chad.hlife.mvp.model;

import com.chad.hlife.entity.mob.CarBrandInfo;
import com.chad.hlife.mvp.presenter.car.ICarPresenter;
import com.chad.hlife.retrofit.HLifeRetrofit;
import com.chad.hlife.util.RxSchedulersUtil;

import io.reactivex.ObservableTransformer;

public class CarModel {

    private static volatile CarModel mCarModel = null;

    public static CarModel getInstance() {
        synchronized (CarModel.class) {
            if (mCarModel == null) {
                mCarModel = new CarModel();
            }
        }
        return mCarModel;
    }

    private CarModel() {
    }

    public void getCarBrandInfo(ObservableTransformer transformer, String key,
                            ICarPresenter carPresenter) {
        HLifeRetrofit.getCarBrandInfo(key)
                .compose(transformer)
                .compose(RxSchedulersUtil.workThread())
                .subscribe(o -> carPresenter.onCarBrandInfo((CarBrandInfo) o),
                        throwable -> carPresenter.onError(throwable));
    }
}
