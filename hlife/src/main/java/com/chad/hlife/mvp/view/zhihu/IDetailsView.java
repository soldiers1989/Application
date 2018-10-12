package com.chad.hlife.mvp.view.zhihu;

import com.chad.hlife.entity.zhihu.DetailsExtraInfo;
import com.chad.hlife.entity.zhihu.DetailsInfo;
import com.chad.hlife.mvp.base.IBaseView;

public interface IDetailsView extends IBaseView {

    void onDetailsInfo(DetailsInfo detailsInfo);

    void onDetailsExtraInfo(DetailsExtraInfo detailsExtraInfo);
}
