package com.chad.hlife.mvp.presenter.password;

import com.chad.hlife.entity.mob.UserPasswordInfo;
import com.chad.hlife.mvp.base.BasePresenter;
import com.chad.hlife.mvp.model.PasswordModel;
import com.chad.hlife.mvp.view.IPasswordView;

import io.reactivex.ObservableTransformer;

public class PasswordPresenter extends BasePresenter<IPasswordView>
        implements IPasswordPresenter {

    public void updatePassword(ObservableTransformer transformer, String key, String userName,
                               String oldPassword, String newPassword) {
        PasswordModel.getInstance().updatePassword(transformer, key, userName, oldPassword, newPassword, this);
    }

    @Override
    public void onUserPasswordInfo(UserPasswordInfo userPasswordInfo) {
        getView().onUserPasswordInfo(userPasswordInfo);
    }

    @Override
    public void onError(Object object) {
        getView().onError(object);
    }
}
