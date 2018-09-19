package com.chad.weibo.mvp.model.main;

import com.chad.weibo.mvp.presenter.main.IMainPresenter;

import io.reactivex.ObservableTransformer;

public interface IMainModel {

    void getUser(ObservableTransformer transformer, String access_token, long uid,
                 IMainPresenter mainPresenter);
}
