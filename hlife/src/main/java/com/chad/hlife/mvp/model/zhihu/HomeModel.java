package com.chad.hlife.mvp.model.zhihu;

import com.chad.hlife.entity.zhihu.HomeInfo;
import com.chad.hlife.mvp.presenter.zhihu.home.IHomePresenter;
import com.chad.hlife.retrofit.HLifeRetrofit;
import com.chad.hlife.util.RxSchedulersUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

import io.reactivex.Observable;
import io.reactivex.ObservableTransformer;

public class HomeModel {

    private static volatile HomeModel homeModel = null;

    public static HomeModel getInstance() {
        synchronized (HomeModel.class) {
            if (homeModel == null) {
                homeModel = new HomeModel();
            }
        }
        return homeModel;
    }

    private HomeModel() {
    }

    public void getLatestHomeInfo(ObservableTransformer transformer, IHomePresenter homePresenter) {
        HLifeRetrofit.getLatestHomeInfo()
                .compose(transformer) // 与RxLifecycle绑定
                .map(o -> initStories((HomeInfo) o)) // map转换符，此处主要初始化化story
                .compose(RxSchedulersUtil.workThread()) // 线程切换，在IO线程和主线程间切换
                .subscribe(o -> homePresenter.onLatestHomeInfo((HomeInfo) o),
                        throwable -> homePresenter.onError(throwable.toString()));
    }

    public void getMoreHomeInfo(ObservableTransformer transformer, String date, IHomePresenter homePresenter) {
        HLifeRetrofit.getMoreHomeInfo(date)
                .compose(transformer)
                .map(o -> initStories((HomeInfo) o))
                .compose(RxSchedulersUtil.workThread())
                .subscribe(o -> homePresenter.onMoreHomeInfo((HomeInfo) o),
                        throwable -> homePresenter.onError(throwable));
    }

    private HomeInfo initStories(HomeInfo homeInfo) {
        if (homeInfo == null) {
            return null;
        }
        // fromIterable() 从集合中发送每一个数据事件
        // collect()    将被观察者发送的数据事件收集到一个数据机构里
        Observable.fromIterable(homeInfo.getStories())
                .collect((Callable<List<Integer>>) () -> new ArrayList<>(),
                        (storyIds, story) -> {
                            story.setDate(homeInfo.getDate());
                            if (story.getImages() != null && story.getImages().size() > 1) {
                                story.setMultiPic(true);
                            }
                            storyIds.add(story.getId());
                        }).subscribe(storyIds -> homeInfo.setStoryIds(storyIds), throwable -> {
        });
        return homeInfo;
    }
}
