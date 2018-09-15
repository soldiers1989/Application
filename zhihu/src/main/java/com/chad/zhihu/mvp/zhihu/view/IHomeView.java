package com.chad.zhihu.mvp.zhihu.view;

import com.chad.zhihu.entity.HomeInfo;
import com.chad.zhihu.mvp.base.IBaseView;

public interface IHomeView extends IBaseView {

    void onLatestHomeInfo(HomeInfo homeInfo);

    void onMoreHomeInfo(HomeInfo homeInfo);
}
