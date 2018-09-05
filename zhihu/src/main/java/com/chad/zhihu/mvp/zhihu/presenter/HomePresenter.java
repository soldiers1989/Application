package com.chad.zhihu.mvp.zhihu.presenter;

import com.chad.zhihu.entity.zhihu.LatestInfo;
import com.chad.zhihu.hepler.RxSchedulersHelper;
import com.chad.zhihu.hepler.retrofit.ZhiHuRetrofitHelper;
import com.chad.zhihu.mvp.base.BasePresenter;
import com.chad.zhihu.mvp.zhihu.view.IHomeView;
import com.chad.zhihu.util.LogUtil;

import java.util.concurrent.TimeUnit;

import io.reactivex.ObservableTransformer;

public class HomePresenter extends BasePresenter<IHomeView> {

    private static final String TAG = HomePresenter.class.getSimpleName();

    public void getLatestInfo(ObservableTransformer observableTransformer) {
        LogUtil.d(TAG, "getLatestInfo");
        ZhiHuRetrofitHelper.getLatestInfo()
                .compose(observableTransformer) // 与RxLifecycle绑定
                .delay(1, TimeUnit.SECONDS) // 延迟一秒执行
                .map(o -> setStoriesDate((LatestInfo) o)) // map转换符，此处主要是给stories设置个日期
                .compose(RxSchedulersHelper.bindToMainThread()) // 线程切换
                .subscribe(o -> getView().onLatestInfo((LatestInfo) o),
                        throwable -> getView().onFail());
    }

    private LatestInfo setStoriesDate(LatestInfo latestInfo) {
        LogUtil.d(TAG, "setStoriesDate : latestInfo = " + latestInfo);
        if (latestInfo == null) {
            return null;
        }
        LogUtil.d(TAG, "setStoriesDate : latestInfo.getDate() = " + latestInfo.getDate());
        for (LatestInfo.Stories stories : latestInfo.getStories()) {
            stories.setDate(latestInfo.getDate());
        }
        return latestInfo;
    }
}
