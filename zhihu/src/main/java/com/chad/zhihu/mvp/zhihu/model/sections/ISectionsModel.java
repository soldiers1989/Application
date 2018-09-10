package com.chad.zhihu.mvp.zhihu.model.sections;

import com.chad.zhihu.mvp.zhihu.presenter.sections.ISectionsPresenter;

import io.reactivex.ObservableTransformer;

public interface ISectionsModel {

    void getSectionsInfo(ObservableTransformer transformer, ISectionsPresenter presenter);

    void getSectionDetailsInfo(ObservableTransformer transformer, int id, ISectionsPresenter presenter);
}
