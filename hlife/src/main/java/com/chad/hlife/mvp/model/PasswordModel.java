package com.chad.hlife.mvp.model;

import com.chad.hlife.entity.mob.UserPasswordInfo;
import com.chad.hlife.mvp.presenter.password.IPasswordPresenter;
import com.chad.hlife.retrofit.HLifeRetrofit;
import com.chad.hlife.util.RxSchedulersUtil;

import io.reactivex.ObservableTransformer;

public class PasswordModel {

    private static volatile PasswordModel mPasswordModel = null;

    public static PasswordModel getInstance() {
        synchronized (PasswordModel.class) {
            if (mPasswordModel == null) {
                mPasswordModel = new PasswordModel();
            }
        }
        return mPasswordModel;
    }

    private PasswordModel() {
    }

    public void updatePassword(ObservableTransformer transformer, String key, String userName,
                               String oldPassword, String newPassword, IPasswordPresenter passwordPresenter) {
        HLifeRetrofit.updatePassword(key, userName, oldPassword, newPassword)
                .compose(transformer)
                .compose(RxSchedulersUtil.workThread())
                .subscribe(o -> passwordPresenter.onUserPasswordInfo((UserPasswordInfo) o),
                        throwable -> passwordPresenter.onError(throwable));
    }
}
