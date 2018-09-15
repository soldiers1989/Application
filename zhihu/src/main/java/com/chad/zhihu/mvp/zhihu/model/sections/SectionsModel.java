package com.chad.zhihu.mvp.zhihu.model.sections;

import com.chad.zhihu.entity.SectionDetailsInfo;
import com.chad.zhihu.entity.SectionsInfo;
import com.chad.zhihu.hepler.RxSchedulersHelper;
import com.chad.zhihu.retrofit.ZhiHuRetrofit;
import com.chad.zhihu.mvp.zhihu.presenter.sections.ISectionsPresenter;
import com.chad.zhihu.util.LogUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.ObservableTransformer;

public class SectionsModel implements ISectionsModel {

    private static final String TAG = SectionsModel.class.getSimpleName();

    private static volatile SectionsModel sectionsModel = null;

    public static SectionsModel getInstance() {
        synchronized (SectionsModel.class) {
            if (sectionsModel == null) {
                sectionsModel = new SectionsModel();
            }
        }
        return sectionsModel;
    }

    @Override
    public void getSectionsInfo(ObservableTransformer transformer, ISectionsPresenter presenter) {
        LogUtil.d(TAG, "getSectionsInfo");
        ZhiHuRetrofit.getSectionsInfo()
                .compose(transformer)
                .compose(RxSchedulersHelper.bindToMainThread())
                .subscribe(o -> presenter.OnSectionsInfo((SectionsInfo) o),
                        throwable -> presenter.onError(throwable.toString()));
    }

    @Override
    public void getSectionDetailsInfo(ObservableTransformer transformer, int id, ISectionsPresenter presenter) {
        LogUtil.d(TAG, "getSectionDetailsInfo : id = " + id);
        ZhiHuRetrofit.getSectionDetailsInfo(id)
                .compose(transformer)
                .delay(1, TimeUnit.SECONDS)
                .map(o -> initStories((SectionDetailsInfo) o))
                .compose(RxSchedulersHelper.bindToMainThread())
                .subscribe(o -> presenter.onSectionDetailsInfo((SectionDetailsInfo) o),
                        throwable -> presenter.onError(throwable.toString()));
    }

    @Override
    public void getBeforeSectionDetailsInfo(ObservableTransformer transformer, int id, long timestamp, ISectionsPresenter presenter) {
        LogUtil.d(TAG, "getBeforeSectionDetailsInfo : id = " + id + " , timestamp = " + timestamp);
        ZhiHuRetrofit.getBeforeSectionDetailsInfo(id, timestamp)
                .compose(transformer)
                .map(o -> initStories((SectionDetailsInfo) o))
                .compose(RxSchedulersHelper.bindToMainThread())
                .subscribe(o -> presenter.onBeforeSectionDetailsInfo((SectionDetailsInfo) o),
                        throwable -> presenter.onError(throwable.toString()));
    }

    private SectionDetailsInfo initStories(SectionDetailsInfo detailsInfo) {
        LogUtil.d(TAG, "initStories : detailsInfo = " + detailsInfo);
        if (detailsInfo == null) {
            return null;
        }
        Observable.fromIterable(detailsInfo.getStories())
                .collect((Callable<List<Integer>>) () -> new ArrayList<>(),
                        (storyIds, story) -> storyIds.add(story.getId()))
                .subscribe(storyIds -> detailsInfo.setStoryIds(storyIds), throwable -> {});
        return detailsInfo;
    }
}
