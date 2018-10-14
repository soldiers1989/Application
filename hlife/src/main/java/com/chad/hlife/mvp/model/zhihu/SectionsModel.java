package com.chad.hlife.mvp.model.zhihu;

import com.chad.hlife.entity.zhihu.SectionsDetailInfo;
import com.chad.hlife.entity.zhihu.SectionsInfo;
import com.chad.hlife.mvp.presenter.zhihu.sections.ISectionsPresenter;
import com.chad.hlife.retrofit.HLifeRetrofit;
import com.chad.hlife.util.RxSchedulersUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

import io.reactivex.Observable;
import io.reactivex.ObservableTransformer;

public class SectionsModel {

    private static volatile SectionsModel sectionsModel = null;

    public static SectionsModel getInstance() {
        synchronized (SectionsModel.class) {
            if (sectionsModel == null) {
                sectionsModel = new SectionsModel();
            }
        }
        return sectionsModel;
    }

    private SectionsModel() {
    }

    public void getSectionsInfo(ObservableTransformer transformer, ISectionsPresenter sectionsPresenter) {
        HLifeRetrofit.getSectionsInfo()
                .compose(transformer)
                .compose(RxSchedulersUtil.workThread())
                .subscribe(o -> sectionsPresenter.OnSectionsInfo((SectionsInfo) o),
                        throwable -> sectionsPresenter.onError(throwable));
    }

    public void getSectionsDetailInfo(ObservableTransformer transformer, int id, ISectionsPresenter sectionsPresenter) {
        HLifeRetrofit.getSectionsDetailInfo(id)
                .compose(transformer)
                .map(o -> initStories((SectionsDetailInfo) o))
                .compose(RxSchedulersUtil.workThread())
                .subscribe(o -> sectionsPresenter.onSectionsDetailInfo((SectionsDetailInfo) o),
                        throwable -> sectionsPresenter.onError(throwable));
    }

    public void getBeforeSectionsDetailInfo(ObservableTransformer transformer, int id, long timestamp,
                                            ISectionsPresenter sectionsPresenter) {
        HLifeRetrofit.getBeforeSectionsDetailInfo(id, timestamp)
                .compose(transformer)
                .map(o -> initStories((SectionsDetailInfo) o))
                .compose(RxSchedulersUtil.workThread())
                .subscribe(o -> sectionsPresenter.onBeforeSectionsDetailInfo((SectionsDetailInfo) o),
                        throwable -> sectionsPresenter.onError(throwable.toString()));
    }

    private SectionsDetailInfo initStories(SectionsDetailInfo detailsInfo) {
        if (detailsInfo == null) {
            return null;
        }
        Observable.fromIterable(detailsInfo.getStories())
                .collect((Callable<List<Integer>>) () -> new ArrayList<>(),
                        (storyIds, story) -> storyIds.add(story.getId()))
                .subscribe(storyIds -> detailsInfo.setStoryIds(storyIds), throwable -> {
                });
        return detailsInfo;
    }
}
