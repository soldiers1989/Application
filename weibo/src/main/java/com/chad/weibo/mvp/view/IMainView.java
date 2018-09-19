package com.chad.weibo.mvp.view;

import com.chad.weibo.entity.User;
import com.chad.weibo.mvp.base.IBaseView;

public interface IMainView extends IBaseView {

    void onUser(User user);
}
