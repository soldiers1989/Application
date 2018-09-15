package com.chad.zhihu.mvp.zhihu.presenter.themes;

import com.chad.zhihu.entity.ThemeDetailsInfo;
import com.chad.zhihu.entity.ThemesInfo;
import com.chad.zhihu.mvp.base.BasePresenter;
import com.chad.zhihu.mvp.zhihu.model.themes.ThemesModel;
import com.chad.zhihu.mvp.zhihu.view.IThemesView;
import com.chad.zhihu.util.LogUtil;

import io.reactivex.ObservableTransformer;

public class ThemesPresenter extends BasePresenter<IThemesView> implements IThemesPresenter {

    private static final String TAG = ThemesInfo.class.getSimpleName();

    public void getThemesInfo(ObservableTransformer transformer) {
        LogUtil.d(TAG, "getThemesInfo");
        ThemesModel.getInstance().getThemesInfo(transformer, this);
    }

    public void getThemeDetailsInfo(ObservableTransformer transformer, int id) {
        LogUtil.d(TAG, "getThemeDetailsInfo : id = " + id);
        ThemesModel.getInstance().getThemeDetailsInfo(transformer, id, this);
    }

    @Override
    public void onThemesInfo(ThemesInfo themesInfo) {
        getView().onThemesInfo(themesInfo);
    }

    @Override
    public void onThemeDetailsInfo(ThemeDetailsInfo themeDetailsInfo) {
        getView().onThemeDetailsInfo(themeDetailsInfo);
    }

    @Override
    public void onError(String msg) {
        getView().onError(msg);
    }
}
