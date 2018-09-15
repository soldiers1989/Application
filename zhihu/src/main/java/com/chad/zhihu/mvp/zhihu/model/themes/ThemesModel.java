package com.chad.zhihu.mvp.zhihu.model.themes;

import com.chad.zhihu.entity.ThemeDetailsInfo;
import com.chad.zhihu.entity.ThemesInfo;
import com.chad.zhihu.hepler.RxSchedulersHelper;
import com.chad.zhihu.retrofit.ZhiHuRetrofit;
import com.chad.zhihu.mvp.zhihu.presenter.themes.IThemesPresenter;
import com.chad.zhihu.util.LogUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
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
    public void getThemesInfo(ObservableTransformer transformer, IThemesPresenter presenter) {
        LogUtil.d(TAG, "getThemesInfo");
        ZhiHuRetrofit.getThemesInfo()
                .compose(transformer)
                .compose(RxSchedulersHelper.bindToMainThread())
                .subscribe(o -> presenter.onThemesInfo((ThemesInfo) o),
                        throwable -> presenter.onError(throwable.toString()));
    }

    @Override
    public void getThemeDetailsInfo(ObservableTransformer transformer, int id, IThemesPresenter presenter) {
        LogUtil.d(TAG, "getThemeDetailsInfo : id = " + id);
        ZhiHuRetrofit.getThemeDetaildInfo(id)
                .compose(transformer)
                .delay(1, TimeUnit.SECONDS)
                .map(o -> initStories((ThemeDetailsInfo) o))
                .compose(RxSchedulersHelper.bindToMainThread())
                .subscribe(o -> presenter.onThemeDetailsInfo((ThemeDetailsInfo) o),
                        throwable -> presenter.onError(throwable.toString()));
    }

    private ThemeDetailsInfo initStories(ThemeDetailsInfo detailsInfo) {
        LogUtil.d(TAG, "initStories : detailsInfo = " + detailsInfo);
        if (detailsInfo == null) {
            return null;
        }
        Observable.fromIterable(detailsInfo.getStories())
                .collect((Callable<List<Integer>>) () -> new ArrayList<>(),
                        ((storyIds, story) -> storyIds.add(story.getId())))
                .subscribe(storyIds -> detailsInfo.setStoryIds(storyIds), throwable -> {});
        return detailsInfo;
    }
}
