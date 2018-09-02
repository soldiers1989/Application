package com.chad.zhihu.dagger.activity;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;
import javax.inject.Singleton;

@Scope
@Retention(RetentionPolicy.RUNTIME)
public @interface ActivityScope {
}
