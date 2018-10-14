package com.chad.hlife.mvp.presenter.zhihu.themes;

import com.chad.hlife.entity.zhihu.ThemesDetailInfo;
import com.chad.hlife.entity.zhihu.ThemesInfo;
import com.chad.hlife.mvp.base.BasePresenter;
import com.chad.hlife.mvp.model.zhihu.ThemesModel;
import com.chad.hlife.mvp.view.zhihu.IThemesView;

import io.reactivex.ObservableTransformer;

public class ThemesPresenter extends BasePresenter<IThemesView> implements IThemesPresenter {

    public void getThemesInfo(ObservableTransformer transformer) {
        ThemesModel.getInstance().getThemesInfo(transformer, this);
    }

    public void getThemesDetailInfo(ObservableTransformer transformer, int id) {
        ThemesModel.getInstance().getThemesDetailInfo(transformer, id, this);
    }

    @Override
    public void onThemesInfo(ThemesInfo themesInfo) {
        getView().onThemesInfo(themesInfo);
    }

    @Override
    public void onThemesDetailInfo(ThemesDetailInfo themesDetailInfo) {
        getView().onThemesDetailInfo(themesDetailInfo);
    }

    @Override
    public void onError(Object object) {
        getView().onError(object);
    }
}
