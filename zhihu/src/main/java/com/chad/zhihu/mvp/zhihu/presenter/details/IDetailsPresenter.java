package com.chad.zhihu.mvp.zhihu.presenter.details;

import com.chad.zhihu.entity.zhihu.DetailsExtraInfo;
import com.chad.zhihu.entity.zhihu.DetailsInfo;
import com.chad.zhihu.mvp.base.IBasePresenter;

public interface IDetailsPresenter extends IBasePresenter {

    void onDetailsInfo(DetailsInfo detailsInfo);

    void onDetailsExtraInfo(DetailsExtraInfo detailsExtraInfo);
}
