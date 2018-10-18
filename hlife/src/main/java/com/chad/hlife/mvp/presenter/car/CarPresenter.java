package com.chad.hlife.mvp.presenter.car;

import com.chad.hlife.entity.mob.CarBrandInfo;
import com.chad.hlife.mvp.base.BasePresenter;
import com.chad.hlife.mvp.model.CarModel;
import com.chad.hlife.mvp.view.ICarView;

import io.reactivex.ObservableTransformer;

public class CarPresenter extends BasePresenter<ICarView> implements ICarPresenter {

    public void getCarBrandInfo(ObservableTransformer transformer, String key) {
        CarModel.getInstance().getCarBrandInfo(transformer, key, this);
    }

    @Override
    public void onCarBrandInfo(CarBrandInfo carBrandInfo) {
        getView().onCarBrandInfo(carBrandInfo);
    }

    @Override
    public void onError(Object object) {
        getView().onError(object);
    }
}
