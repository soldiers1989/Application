package com.chad.zhihu.mvp.zhihu.view;

import com.chad.zhihu.entity.DetailsExtraInfo;
import com.chad.zhihu.entity.DetailsInfo;
import com.chad.zhihu.mvp.base.IBaseView;

public interface IDetailsView extends IBaseView {

    void onDetailsInfo(DetailsInfo detailsInfo);

    void onDetailsExtraInfo(DetailsExtraInfo detailsExtraInfo);
}
