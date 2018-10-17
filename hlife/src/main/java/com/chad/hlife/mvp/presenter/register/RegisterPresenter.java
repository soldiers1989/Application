package com.chad.hlife.mvp.presenter.register;

import com.chad.hlife.entity.mob.UserRegisterInfo;
import com.chad.hlife.mvp.base.BasePresenter;
import com.chad.hlife.mvp.model.RegisterModel;
import com.chad.hlife.mvp.view.IRegisterView;

import io.reactivex.ObservableTransformer;

public class RegisterPresenter extends BasePresenter<IRegisterView> implements IRegisterPresenter {

    public void register(ObservableTransformer transformer, String key, String userName, String password) {
        RegisterModel.getInstance().register(transformer, key, userName, password, this);
    }

    @Override
    public void onUserRegisterInfo(UserRegisterInfo userRegisterInfo) {
        getView().onUserRegisterInfo(userRegisterInfo);
    }

    @Override
    public void onError(Object object) {
        getView().onError(object);
    }
}
