package com.chad.zhihu.mvp.zhihu.model.home;

import com.chad.zhihu.mvp.zhihu.presenter.home.IHomePresenter;

import io.reactivex.ObservableTransformer;

public interface IHomeModel {

    void getLatestHomeInfo(ObservableTransformer transformer, IHomePresenter presenter);

    void getMoreHomeInfo(ObservableTransformer transformer, String date, IHomePresenter presenter);
}
