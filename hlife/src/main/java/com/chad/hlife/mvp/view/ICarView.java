package com.chad.hlife.mvp.view;

import com.chad.hlife.entity.mob.CarBrandInfo;
import com.chad.hlife.mvp.base.IBaseView;

public interface ICarView extends IBaseView {

    void onCarBrandInfo(CarBrandInfo carBrandInfo);
}
