package com.chad.weibo.mvp.view;

import com.chad.weibo.entity.TimeLine;
import com.chad.weibo.mvp.base.IBaseView;

public interface IHomeView extends IBaseView {

    void onHomeTimeLine(TimeLine timeLine);

    void onMoreHomeTimeLine(TimeLine timeLine);
}
