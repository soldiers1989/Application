package com.chad.zhihu.mvp.zhihu.model.detail;

import com.chad.zhihu.mvp.zhihu.presenter.detail.IDetailPresenter;

import io.reactivex.ObservableTransformer;

public interface IDetailModel {

    void getDetailInfo(ObservableTransformer transformer, int id, IDetailPresenter presenter);
}
