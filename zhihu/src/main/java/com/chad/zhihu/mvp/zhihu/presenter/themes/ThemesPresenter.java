package com.chad.zhihu.mvp.zhihu.presenter.themes;

import com.chad.zhihu.entity.zhihu.ThemesInfo;
import com.chad.zhihu.mvp.base.BasePresenter;
import com.chad.zhihu.mvp.zhihu.model.themes.ThemesModel;
import com.chad.zhihu.mvp.zhihu.view.IThemesView;
import com.chad.zhihu.util.LogUtil;

import io.reactivex.ObservableTransformer;

public class ThemesPresenter extends BasePresenter<IThemesView> implements IThemesPresenter {

    private static final String TAG = ThemesInfo.class.getSimpleName();

    public void getDailyInfo(ObservableTransformer transformer) {
        LogUtil.d(TAG, "geThemesInfo");
        ThemesModel.getInstance().getThemesInfo(transformer, this);
    }

    @Override
    public void onThemesInfo(ThemesInfo themesInfo) {
        getView().onThemesInfo(themesInfo);
    }

    @Override
    public void onError(String msg) {
        getView().onError(msg);
    }
}
