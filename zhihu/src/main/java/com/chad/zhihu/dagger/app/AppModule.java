package com.chad.zhihu.dagger.app;

import android.content.Context;

import com.chad.zhihu.ZhiHuApplication;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {

    private final ZhiHuApplication zhiHuApplication;

    public AppModule(ZhiHuApplication zhiHuApplication) {
        this.zhiHuApplication = zhiHuApplication;
    }

    @Singleton
    @Provides
    Context provideZhiHuApplication() {
        return zhiHuApplication;
    }
}
