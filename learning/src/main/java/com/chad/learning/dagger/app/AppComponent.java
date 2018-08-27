package com.chad.learning.dagger.app;

import com.chad.learning.dagger.activity.DaggerActivity;

import dagger.Component;

/**
 * Component是一个接口或者抽象类，用@Component注解标注
 * @Component 用来将@Inject和@Module联系起来的桥梁，从@Module中获取依赖并将依赖注入给@Inject
 */
@Component (modules = AppModule.class)
public interface AppComponent {

    void inject(DaggerActivity activity);
}
