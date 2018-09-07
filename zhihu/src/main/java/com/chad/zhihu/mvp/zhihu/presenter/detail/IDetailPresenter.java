package com.chad.zhihu.mvp.zhihu.presenter.detail;

import com.chad.zhihu.entity.zhihu.DetailInfo;
import com.chad.zhihu.mvp.base.IBasePresenter;

public interface IDetailPresenter extends IBasePresenter {

    void onDetailInfo(DetailInfo detailInfo);
}
