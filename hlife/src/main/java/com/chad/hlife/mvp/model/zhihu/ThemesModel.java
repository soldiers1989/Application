package com.chad.hlife.mvp.model.zhihu;

import com.chad.hlife.entity.zhihu.ThemesDetailInfo;
import com.chad.hlife.entity.zhihu.ThemesInfo;
import com.chad.hlife.mvp.presenter.zhihu.themes.IThemesPresenter;
import com.chad.hlife.retrofit.HLifeRetrofit;
import com.chad.hlife.util.RxSchedulersUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

import io.reactivex.Observable;
import io.reactivex.ObservableTransformer;

public class ThemesModel {

    private static volatile ThemesModel themesModel = null;

    public static ThemesModel getInstance() {
        synchronized (ThemesModel.class) {
            if (themesModel == null) {
                themesModel = new ThemesModel();
            }
        }
        return themesModel;
    }

    private ThemesModel() {}

    public void getThemesInfo(ObservableTransformer transformer, IThemesPresenter themesPresenter) {
        HLifeRetrofit.getThemesInfo()
                .compose(transformer)
                .compose(RxSchedulersUtil.workThread())
                .subscribe(o -> themesPresenter.onThemesInfo((ThemesInfo) o),
                        throwable -> themesPresenter.onError(throwable.toString()));
    }

    public void getThemesDetailInfo(ObservableTransformer transformer, int id, IThemesPresenter themesPresenter) {
        HLifeRetrofit.getThemesDetailInfo(id)
                .compose(transformer)
                .map(o -> initStories((ThemesDetailInfo) o))
                .compose(RxSchedulersUtil.workThread())
                .subscribe(o -> themesPresenter.onThemesDetailInfo((ThemesDetailInfo) o),
                        throwable -> themesPresenter.onError(throwable));
    }

    private ThemesDetailInfo initStories(ThemesDetailInfo detailsInfo) {
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
