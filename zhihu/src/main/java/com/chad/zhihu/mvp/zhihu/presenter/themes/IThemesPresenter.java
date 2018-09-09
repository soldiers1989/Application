package com.chad.zhihu.mvp.zhihu.presenter.themes;

import com.chad.zhihu.entity.zhihu.ThemeDetailsInfo;
import com.chad.zhihu.entity.zhihu.ThemesInfo;
import com.chad.zhihu.mvp.base.IBasePresenter;

public interface IThemesPresenter extends IBasePresenter {

    void onThemesInfo(ThemesInfo themesInfo);

    void onThemeDetailsInfo(ThemeDetailsInfo themeDetailsInfo);
}
