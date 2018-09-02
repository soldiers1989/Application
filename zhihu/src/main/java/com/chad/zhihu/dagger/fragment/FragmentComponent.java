package com.chad.zhihu.dagger.fragment;

import android.support.v4.app.Fragment;

import com.chad.zhihu.dagger.activity.ActivityComponent;

import dagger.Component;

@FragmentScope
@Component(dependencies = ActivityComponent.class, modules = {FragmentModule.class})
public interface FragmentComponent {

    void inject(Fragment fragment);
}
