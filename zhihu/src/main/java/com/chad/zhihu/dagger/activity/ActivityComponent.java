package com.chad.zhihu.dagger.activity;

import com.chad.zhihu.dagger.app.AppComponent;
import com.chad.zhihu.ui.activity.MainActivity;

import dagger.Component;

@ActivityScope
@Component(dependencies = AppComponent.class, modules = {ActivityModule.class})
public interface ActivityComponent {

    void inject(MainActivity activity);
}
