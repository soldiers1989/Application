package com.chad.learning.dagger.app;

import com.chad.learning.dagger.mvp.Contract;

import dagger.Module;
import dagger.Provides;

/**
 * @Module 带有此注解的类，用来提供依赖，里面定义一些用@Provides注解的以provide开头的方法，
 * 这些方法就是所提供的依赖，Dagger2会在该类中寻找实例化某个类所需要的依赖。
 */
@Module
public class AppModule {

    private Contract.View mView;

    public AppModule(Contract.View view) {
        mView = view;
    }

    @Provides
    Contract.View provideAppView() {
        return mView;
    }
}
