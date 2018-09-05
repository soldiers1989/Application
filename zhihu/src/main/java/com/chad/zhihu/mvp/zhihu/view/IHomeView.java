package com.chad.zhihu.mvp.zhihu.view;

import com.chad.zhihu.entity.zhihu.HomeInfo;

public interface IHomeView {

    void onLatestHomeInfo(HomeInfo homeInfo);

    void onMoreHomeInfo(HomeInfo homeInfo);

    void onFail();
}
