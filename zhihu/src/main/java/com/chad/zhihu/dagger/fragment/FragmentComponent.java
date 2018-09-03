package com.chad.zhihu.dagger.fragment;

import com.chad.zhihu.dagger.activity.ActivityComponent;
import com.chad.zhihu.ui.fragment.HomeFragment;

import dagger.Component;

@FragmentScope
@Component(dependencies = ActivityComponent.class, modules = {FragmentModule.class})
public interface FragmentComponent {

    void inject(HomeFragment fragment);
}
