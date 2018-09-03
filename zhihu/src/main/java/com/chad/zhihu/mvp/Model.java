package com.chad.zhihu.mvp;

import com.chad.zhihu.entity.LatestDailyInfo;
import com.chad.zhihu.hepler.RxSchedulersHelper;
import com.chad.zhihu.hepler.retrofit.RetrofitHelper;

import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.ObservableTransformer;

public class Model {

    private static final String TAG = Model.class.getSimpleName();

    private Contract.IModel iModel = null;

    public Model(Contract.IModel iModel) {
        this.iModel = iModel;
    }

    public void getLatestDailyInfo(ObservableTransformer transformer) {
        RetrofitHelper.getLatestDailyInfo()
                .compose(transformer) // Fragment的bindToLifecycle()
                .delay(1, TimeUnit.SECONDS)
                .compose(RxSchedulersHelper.cutThread())
                .subscribe(latestDailyInfo -> {
                    iModel.onLatestDailyInfo(setStoriesDate((LatestDailyInfo) latestDailyInfo));
                }, throwable -> {
                    iModel.onFail();
                });

    }

    private LatestDailyInfo setStoriesDate(LatestDailyInfo latestDailyInfo) {
        if (latestDailyInfo == null) {
            return null;
        }
        List<LatestDailyInfo.Stories> storiesList = latestDailyInfo.getStories();
        for (LatestDailyInfo.Stories stories : storiesList) {
            stories.setDate(latestDailyInfo.getDate());
        }
        return latestDailyInfo;
    }
}
