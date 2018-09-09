package com.chad.zhihu.mvp.zhihu.model.themes;

import com.chad.zhihu.mvp.zhihu.presenter.themes.IThemesPresenter;

import io.reactivex.ObservableTransformer;

public interface IThemesModel {

    void getThemesInfo(ObservableTransformer transformer, IThemesPresenter presenter);

    void getThemeDetailsInfo(ObservableTransformer transformer, int id, IThemesPresenter presenter);
}
