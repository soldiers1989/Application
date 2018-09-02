package com.chad.zhihu.dagger.activity;

import com.chad.zhihu.mvp.Contract;

import dagger.Module;
import dagger.Provides;

@Module
public class ActivityModule {

    private final Contract.IView iView;

    public ActivityModule(Contract.IView iView) {
        this.iView = iView;
    }

    @ActivityScope
    @Provides
    Contract.IView provideContractIView() {
        return iView;
    }
}
