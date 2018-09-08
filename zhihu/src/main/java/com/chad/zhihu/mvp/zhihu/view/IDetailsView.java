package com.chad.zhihu.mvp.zhihu.view;

import com.chad.zhihu.entity.zhihu.DetailsInfo;
import com.chad.zhihu.mvp.base.IBaseView;

public interface IDetailsView extends IBaseView {

    void onDetailsInfo(DetailsInfo detailsInfo);
}
