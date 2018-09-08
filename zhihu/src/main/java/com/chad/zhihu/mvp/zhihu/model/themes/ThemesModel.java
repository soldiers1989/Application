package com.chad.zhihu.mvp.zhihu.model.themes;

import com.chad.zhihu.entity.zhihu.ThemesInfo;
import com.chad.zhihu.hepler.RxSchedulersHelper;
import com.chad.zhihu.hepler.retrofit.ZhiHuRetrofitHelper;
import com.chad.zhihu.mvp.zhihu.presenter.themes.ThemesPresenter;
import com.chad.zhihu.util.LogUtil;

import io.reactivex.ObservableTransformer;

public class ThemesModel implements IThemesModel {

    private static final String TAG = ThemesModel.class.getSimpleName();

    private static volatile ThemesModel themesModel = null;

    public static ThemesModel getInstance() {
        synchronized (ThemesModel.class) {
            if (themesModel == null) {
                themesModel = new ThemesModel();
            }
        }
        return themesModel;
    }

    @Override
    public void getThemesInfo(ObservableTransformer transformer, ThemesPresenter presenter) {
        LogUtil.d(TAG, "getThemesInfo");
        ZhiHuRetrofitHelper.getThemesInfo()
                .compose(transformer)
                .compose(RxSchedulersHelper.bindToMainThread())
                .subscribe(o -> presenter.onThemesInfo((ThemesInfo) o),
                        throwable -> presenter.onError(throwable.toString()));
    }
}
