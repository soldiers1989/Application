package com.chad.hlife.mvp.presenter.car;

import com.chad.hlife.entity.mob.CarBrandInfo;
import com.chad.hlife.entity.mob.CarDetailInfo;
import com.chad.hlife.entity.mob.CarTypeInfo;
import com.chad.hlife.mvp.base.BasePresenter;
import com.chad.hlife.mvp.model.CarModel;
import com.chad.hlife.mvp.view.ICarView;

import io.reactivex.ObservableTransformer;

public class CarPresenter extends BasePresenter<ICarView> implements ICarPresenter {

    public void getCarBrandInfo(ObservableTransformer transformer, String key) {
        CarModel.getInstance().getCarBrandInfo(transformer, key, this);
    }

    public void getCarTypeInfo(ObservableTransformer transformer, String key, String name) {
        CarModel.getInstance().getCarTypeInfo(transformer, key, name, this);
    }

    public void getCarDetailInfo(ObservableTransformer transformer, String key, String carId) {
        CarModel.getInstance().getCarDetailInfo(transformer, key, carId, this);
    }

    @Override
    public void onCarBrandInfo(CarBrandInfo carBrandInfo) {
        getView().onCarBrandInfo(carBrandInfo);
    }

    @Override
    public void onCarTypeInfo(CarTypeInfo carTypeInfo) {
        getView().onCarTypeInfo(carTypeInfo);
    }

    @Override
    public void onCarDetailInfo(CarDetailInfo carDetailInfo) {
        getView().onCarDetailInfo(carDetailInfo);
    }

    @Override
    public void onError(Object object) {
        getView().onError(object);
    }
}
