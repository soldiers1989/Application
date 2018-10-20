package com.chad.hlife.mvp.model;

import com.chad.hlife.entity.mob.CarBrandInfo;
import com.chad.hlife.entity.mob.CarDetailInfo;
import com.chad.hlife.entity.mob.CarTypeInfo;
import com.chad.hlife.mvp.presenter.car.ICarPresenter;
import com.chad.hlife.retrofit.HLifeRetrofit;
import com.chad.hlife.util.PinYinUtil;
import com.chad.hlife.util.RxSchedulersUtil;

import io.reactivex.Observable;
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
                .map(o -> initNameLetter((CarBrandInfo) o))
                .compose(RxSchedulersUtil.workThread())
                .subscribe(o -> carPresenter.onCarBrandInfo((CarBrandInfo) o),
                        throwable -> carPresenter.onError(throwable));
    }

    public void getCarTypeInfo(ObservableTransformer transformer, String key, String name,
                               ICarPresenter carPresenter) {
        HLifeRetrofit.getCarTypeInfo(key, name)
                .compose(transformer)
                .compose(RxSchedulersUtil.workThread())
                .subscribe(o -> carPresenter.onCarTypeInfo((CarTypeInfo) o),
                        throwable -> carPresenter.onError(throwable));
    }

    public void getCarDetailInfo(ObservableTransformer transformer, String key, String carId,
                                 ICarPresenter carPresenter) {
        HLifeRetrofit.getCarDetailInfo(key, carId)
                .compose(transformer)
                .compose(RxSchedulersUtil.workThread())
                .subscribe(o -> carPresenter.onCarDetailInfo((CarDetailInfo) o),
                        throwable -> carPresenter.onError(throwable));
    }

    private CarBrandInfo initNameLetter(CarBrandInfo carBrandInfo) {
        if (carBrandInfo == null) {
            return null;
        }
        Observable.fromIterable(carBrandInfo.getResult())
                .forEach(result -> result.setFirstLetter(PinYinUtil.getPinYinFirstLetter(result.getName())));
        return carBrandInfo;
    }
}
