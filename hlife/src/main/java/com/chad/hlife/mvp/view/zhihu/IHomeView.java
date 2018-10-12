package com.chad.hlife.mvp.view.zhihu;

import com.chad.hlife.entity.zhihu.HomeInfo;
import com.chad.hlife.mvp.base.IBaseView;

public interface IHomeView extends IBaseView {

    void onLatestHomeInfo(HomeInfo homeInfo);

    void onMoreHomeInfo(HomeInfo homeInfo);
}
