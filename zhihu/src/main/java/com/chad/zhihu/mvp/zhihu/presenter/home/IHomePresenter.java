package com.chad.zhihu.mvp.zhihu.presenter.home;

import com.chad.zhihu.entity.HomeInfo;
import com.chad.zhihu.mvp.base.IBasePresenter;

public interface IHomePresenter extends IBasePresenter {

    void onLatestHomeInfo(HomeInfo homeInfo);

    void onMoreHomeInfo(HomeInfo homeInfo);
}
