package com.chad.zhihu.mvp.zhihu.presenter;

import com.chad.zhihu.entity.zhihu.HomeInfo;
import com.chad.zhihu.hepler.RxSchedulersHelper;
import com.chad.zhihu.hepler.retrofit.ZhiHuRetrofitHelper;
import com.chad.zhihu.mvp.base.BasePresenter;
import com.chad.zhihu.mvp.zhihu.view.IHomeView;
import com.chad.zhihu.util.LogUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.ObservableTransformer;

public class HomePresenter extends BasePresenter<IHomeView> {

    private static final String TAG = HomePresenter.class.getSimpleName();

    public void getLatestHomeInfo(ObservableTransformer transformer) {
        LogUtil.d(TAG, "getLatestHomeInfo");
        ZhiHuRetrofitHelper.getLatestHomeInfo()
                .compose(transformer) // 与RxLifecycle绑定
                .delay(1, TimeUnit.SECONDS) // 延迟一秒执行
                .map(o -> initStories((HomeInfo) o)) // map转换符，此处主要是给stories设置个日期
                .compose(RxSchedulersHelper.bindToMainThread()) // 线程切换
                .subscribe(o -> getView().onLatestHomeInfo((HomeInfo) o),
                        throwable -> getView().onFail());
    }

    public void getMoreHomeInfo(ObservableTransformer transformer, String date) {
        LogUtil.d(TAG, "getLatestHomeInfo : date = "+ date);
        ZhiHuRetrofitHelper.getMoreHomeInfo(date)
                .compose(transformer)
                .map(o -> initStories((HomeInfo) o))
                .compose(RxSchedulersHelper.bindToMainThread())
                .subscribe(o -> getView().onMoreHomeInfo((HomeInfo) o),
                        throwable -> getView().onFail());
    }

    private HomeInfo initStories(HomeInfo homeInfo) {
        LogUtil.d(TAG, "initStories : homeInfo = " + homeInfo);
        if (homeInfo == null) {
            return null;
        }
        LogUtil.d(TAG, "initStories : homeInfo.getDate() = " + homeInfo.getDate());
        List<Integer> storiesIds = new ArrayList<>();
        for (HomeInfo.Stories stories : homeInfo.getStories()) {
            stories.setDate(homeInfo.getDate());
            if (stories.getImages() != null && stories.getImages().size() > 1) {
                stories.setMultiPic(true);
            }
            storiesIds.add(stories.getId());
        }
        homeInfo.setStoriesIds(storiesIds);
        return homeInfo;
    }
}
