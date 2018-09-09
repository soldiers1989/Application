package com.chad.zhihu.mvp.zhihu.model.details;

import com.chad.zhihu.mvp.zhihu.presenter.details.IDetailsPresenter;

import io.reactivex.ObservableTransformer;

public interface IDetailsModel {

    void getDetailsInfo(ObservableTransformer transformer, int id, IDetailsPresenter presenter);

    void getDetailsExtraInfo(ObservableTransformer transformer, int id, IDetailsPresenter presenter);
}
