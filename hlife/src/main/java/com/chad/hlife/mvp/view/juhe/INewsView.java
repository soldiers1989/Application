package com.chad.hlife.mvp.view.juhe;

import com.chad.hlife.entity.juhe.NewsInfo;
import com.chad.hlife.mvp.base.IBaseView;

public interface INewsView extends IBaseView {

    void onNewsInfo(NewsInfo newsInfo);
}
