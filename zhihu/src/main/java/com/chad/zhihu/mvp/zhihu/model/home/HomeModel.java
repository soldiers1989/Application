package com.chad.zhihu.mvp.zhihu.model.home;

import com.chad.zhihu.entity.zhihu.HomeInfo;
import com.chad.zhihu.hepler.RxSchedulersHelper;
import com.chad.zhihu.retrofit.ZhiHuRetrofit;
import com.chad.zhihu.mvp.zhihu.presenter.home.IHomePresenter;
import com.chad.zhihu.util.LogUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.ObservableTransformer;

public class HomeModel implements IHomeModel {

    private static final String TAG = HomeModel.class.getSimpleName();

    private static volatile  HomeModel homeModel = null;

    public static HomeModel getInstance() {
        synchronized (HomeModel.class) {
            if (homeModel == null) {
                homeModel = new HomeModel();
            }
        }
        return homeModel;
    }

    private HomeModel() {}

    @Override
    public void getLatestHomeInfo(ObservableTransformer transformer, IHomePresenter presenter) {
        LogUtil.d(TAG, "getLatestHomeInfo");
        ZhiHuRetrofit.getLatestHomeInfo()
                .compose(transformer) // 与RxLifecycle绑定
                .delay(1, TimeUnit.SECONDS) // 延迟一秒执行
                .map(o -> initStories((HomeInfo) o)) // map转换符，此处主要初始化化story
                .compose(RxSchedulersHelper.bindToMainThread()) // 线程切换，在IO线程和主线程间切换
                .subscribe(o -> presenter.onLatestHomeInfo((HomeInfo) o),
                        throwable -> presenter.onError(throwable.toString()));
    }

    @Override
    public void getMoreHomeInfo(ObservableTransformer transformer, String date, IHomePresenter presenter) {
        LogUtil.d(TAG, "getLatestHomeInfo : date = " + date);
        ZhiHuRetrofit.getMoreHomeInfo(date)
                .compose(transformer)
                .map(o -> initStories((HomeInfo) o))
                .compose(RxSchedulersHelper.bindToMainThread())
                .subscribe(o -> presenter.onMoreHomeInfo((HomeInfo) o),
                        throwable -> presenter.onError(throwable.toString()));
    }

    private HomeInfo initStories(HomeInfo homeInfo) {
        LogUtil.d(TAG, "initStories : homeInfo = " + homeInfo);
        if (homeInfo == null) {
            return null;
        }
        LogUtil.d(TAG, "initStories : homeInfo.getDate() = " + homeInfo.getDate());
        // fromIterable() 从集合中发送每一个数据事件
        // collect()    将被观察者发送的数据事件收集到一个数据机构里
        Observable.fromIterable(homeInfo.getStories())
                .collect((Callable<List<Integer>>) () -> new ArrayList<>(), (storyIds, story) -> {
                    story.setDate(homeInfo.getDate());
                    if (story.getImages() != null && story.getImages().size() > 1) {
                        story.setMultiPic(true);
                    }
                    storyIds.add(story.getId());
                }).subscribe(storyIds -> homeInfo.setStoryIds(storyIds), throwable -> {});
        return homeInfo;
    }
}
