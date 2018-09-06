package com.chad.zhihu.mvp.zhihu.presenter;

import com.chad.zhihu.entity.zhihu.HomeInfo;
import com.chad.zhihu.hepler.RxSchedulersHelper;
import com.chad.zhihu.hepler.retrofit.ZhiHuRetrofitHelper;
import com.chad.zhihu.mvp.base.BasePresenter;
import com.chad.zhihu.mvp.zhihu.view.IHomeView;
import com.chad.zhihu.util.LogUtil;

import java.util.concurrent.TimeUnit;

import io.reactivex.ObservableTransformer;

public class HomePresenter extends BasePresenter<IHomeView> {

    private static final String TAG = HomePresenter.class.getSimpleName();

    public void getLatestHomeInfo(ObservableTransformer observableTransformer) {
        LogUtil.d(TAG, "getLatestHomeInfo");
        ZhiHuRetrofitHelper.getLatestHomeInfo()
                .compose(observableTransformer) // 与RxLifecycle绑定
                .delay(1, TimeUnit.SECONDS) // 延迟一秒执行
                .map(o -> setStoriesDate((HomeInfo) o)) // map转换符，此处主要是给stories设置个日期
                .compose(RxSchedulersHelper.bindToMainThread()) // 线程切换
                .subscribe(o -> getView().onLatestHomeInfo((HomeInfo) o),
                        throwable -> getView().onFail());
    }

    public void getMoreHomeInfo(ObservableTransformer observableTransformer, String date) {
        LogUtil.d(TAG, "getLatestHomeInfo : date = "+ date);
        ZhiHuRetrofitHelper.getMoreHomeInfo(date)
                .compose(observableTransformer)
                .map(o -> setStoriesDate((HomeInfo) o))
                .compose(RxSchedulersHelper.bindToMainThread())
                .subscribe(o -> getView().onMoreHomeInfo((HomeInfo) o),
                        throwable -> getView().onFail());
    }

    private HomeInfo setStoriesDate(HomeInfo homeInfo) {
        LogUtil.d(TAG, "setStoriesDate : homeInfo = " + homeInfo);
        if (homeInfo == null) {
            return null;
        }
        LogUtil.d(TAG, "setStoriesDate : homeInfo.getDate() = " + homeInfo.getDate());
        for (HomeInfo.Stories stories : homeInfo.getStories()) {
            stories.setDate(homeInfo.getDate());
            if (stories.getImages() != null && stories.getImages().size() > 1) {
                stories.setMultiPic(true);
            }
        }
        return homeInfo;
    }
}
