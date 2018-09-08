package com.chad.zhihu.mvp.zhihu.model.themes;

import com.chad.zhihu.mvp.zhihu.presenter.themes.ThemesPresenter;

import io.reactivex.ObservableTransformer;

public interface IThemesModel {

    void getThemesInfo(ObservableTransformer transformer, ThemesPresenter presenter);
}
