package com.chad.hlife.mvp.model;

import com.chad.hlife.entity.mob.UserRegisterInfo;
import com.chad.hlife.mvp.presenter.register.IRegisterPresenter;
import com.chad.hlife.retrofit.HLifeRetrofit;
import com.chad.hlife.util.RxSchedulersUtil;

import io.reactivex.ObservableTransformer;

public class RegisterModel {

    private static volatile RegisterModel mRegisterModel = null;

    public static RegisterModel getInstance() {
        synchronized (RegisterModel.class) {
            if (mRegisterModel == null) {
                mRegisterModel = new RegisterModel();
            }
        }
        return mRegisterModel;
    }

    private RegisterModel() {
    }

    public void register(ObservableTransformer transformer, String key, String userName, String password,
                         IRegisterPresenter registerPresenter) {
        HLifeRetrofit.register(key, userName, password)
                .compose(transformer)
                .compose(RxSchedulersUtil.workThread())
                .subscribe(o -> registerPresenter.onUserRegisterInfo((UserRegisterInfo) o),
                        throwable -> registerPresenter.onError(password));
    }
}
