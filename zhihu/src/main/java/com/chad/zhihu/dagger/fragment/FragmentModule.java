package com.chad.zhihu.dagger.fragment;

import com.chad.zhihu.mvp.Contract;

import dagger.Module;
import dagger.Provides;

@Module
public class FragmentModule {

    private final Contract.IView iView;

    public FragmentModule(Contract.IView iView) {
        this.iView = iView;
    }

    @FragmentScope
    @Provides
    Contract.IView provideContractIView() {
        return iView;
    }
}
