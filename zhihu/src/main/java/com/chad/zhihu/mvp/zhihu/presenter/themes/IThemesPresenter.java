package com.chad.zhihu.mvp.zhihu.presenter.themes;

import com.chad.zhihu.entity.ThemeDetailsInfo;
import com.chad.zhihu.entity.ThemesInfo;
import com.chad.zhihu.mvp.base.IBasePresenter;

public interface IThemesPresenter extends IBasePresenter {

    void onThemesInfo(ThemesInfo themesInfo);

    void onThemeDetailsInfo(ThemeDetailsInfo themeDetailsInfo);
}
