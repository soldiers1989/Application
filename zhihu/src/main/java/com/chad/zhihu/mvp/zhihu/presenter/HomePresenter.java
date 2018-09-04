package com.chad.zhihu.mvp.zhihu.presenter;

import com.chad.zhihu.entity.zhihu.LatestInfo;
import com.chad.zhihu.hepler.RxSchedulersHelper;
import com.chad.zhihu.hepler.retrofit.ZhiHuRetrofitHelper;
import com.chad.zhihu.mvp.base.BasePresenter;
import com.chad.zhihu.mvp.zhihu.view.IHomeView;

import java.util.concurrent.TimeUnit;

import io.reactivex.ObservableTransformer;

public class HomePresenter extends BasePresenter<IHomeView> {

    public void getLatestInfo(ObservableTransformer observableTransformer) {
        ZhiHuRetrofitHelper.getLatestInfo()
                .compose(observableTransformer) // 与RxLifecycle绑定
                .delay(1, TimeUnit.SECONDS) // 延迟一秒执行
                .map(o -> setStoriesDate((LatestInfo) o)) // map转换符，此处主要是给stories设置个日期
                .compose(RxSchedulersHelper.bindToMainThread()) // 线程切换
                .subscribe(o -> getView().onLatestInfo((LatestInfo) o),
                        throwable -> getView().onFail());
    }

    private LatestInfo setStoriesDate(LatestInfo latestInfo) {
        if (latestInfo == null) {
            return null;
        }
        for (LatestInfo.Stories stories : latestInfo.getStories()) {
            stories.setDate(latestInfo.getDate());
        }
        return latestInfo;
    }
}
