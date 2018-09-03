package com.chad.zhihu.mvp;

import com.chad.zhihu.entity.LatestDailyInfo;

public class Contract {

    public interface IModel {
        void onLatestDailyInfo(LatestDailyInfo latestDailyInfo);

        void onFail();
    }

    public interface IView {
        void onLatestDailyInfo(LatestDailyInfo latestDailyInfo);

        void onFail();
    }
}
