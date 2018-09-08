package com.chad.zhihu.mvp.zhihu.model.sections;

import com.chad.zhihu.mvp.zhihu.presenter.sections.SectionsPresenter;

import io.reactivex.ObservableTransformer;

public interface ISectionsModel {

    void getSectionsInfo(ObservableTransformer transformer, SectionsPresenter presenter);
}
