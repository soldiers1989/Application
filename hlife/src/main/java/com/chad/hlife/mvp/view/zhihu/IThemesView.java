package com.chad.hlife.mvp.view.zhihu;


import com.chad.hlife.entity.zhihu.ThemesDetailInfo;
import com.chad.hlife.entity.zhihu.ThemesInfo;
import com.chad.hlife.mvp.base.IBaseView;

public interface IThemesView extends IBaseView {

    void onThemesInfo(ThemesInfo themesInfo);

    void onThemesDetailInfo(ThemesDetailInfo themesDetailInfo);
}
