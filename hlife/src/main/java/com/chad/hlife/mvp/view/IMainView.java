package com.chad.hlife.mvp.view;

import com.chad.hlife.entity.weibo.WeiBoUserInfo;
import com.chad.hlife.mvp.base.IBaseView;

public interface IMainView extends IBaseView{

    void onWeiBoUserInfo(WeiBoUserInfo weiBoUserInfo);
}
