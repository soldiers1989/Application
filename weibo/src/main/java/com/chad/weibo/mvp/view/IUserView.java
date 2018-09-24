package com.chad.weibo.mvp.view;

import com.chad.weibo.entity.TimeLine;
import com.chad.weibo.mvp.base.IBaseView;

public interface IUserView extends IBaseView {

    void onUserTimeLine(TimeLine timeLine);

    void onMoreUserTimeLine(TimeLine timeLine);
}
