package com.chad.zhihu.mvp.zhihu.view;

import com.chad.zhihu.entity.zhihu.LatestInfo;

public interface IHomeView {

    void onLatestInfo(LatestInfo latestInfo);

    void onFail();
}
