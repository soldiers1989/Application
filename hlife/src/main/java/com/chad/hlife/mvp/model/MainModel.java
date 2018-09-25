package com.chad.hlife.mvp.model;

import com.chad.hlife.mvp.presenter.main.IMainPresenter;

import io.reactivex.ObservableTransformer;

public class MainModel {

    private static volatile MainModel mMainModel = null;

    public static MainModel getInstance() {
        synchronized (MainModel.class) {
            if (mMainModel == null) {
                mMainModel = new MainModel();
            }
        }
        return mMainModel;
    }

    private MainModel() {
    }

    public void getWeiBoUserInfo(ObservableTransformer transformer, String accessToken,
                                 IMainPresenter mainPresenter) {

    }

    public void getWeiXinUserInfo(ObservableTransformer transformer, String accessToken,
                                  IMainPresenter mainPresenter) {

    }
}
