package com.chad.learning.dagger;

public class Notes {
    /**
     * Dagger2是一个依赖注入框架，依赖注入是面向对象编程的一种设计模式，
     * 其目的是为了降低程序耦合，这个耦合就是类之间的依赖引起的。
     *
     * Dagger2的基本概念：通过@Inject使用具体的某个对象，这个对象是由@Providers注解提供，
     * 但是@Providers只能在固定的模块中，也就是@Module注解，我们查找的时候，不是直接去找
     * 模块，而是去找@Component
     *
     * Module之间有依赖关系：@Module (includes = {BModule.class}) 关键字：includes 引入B模块
     *
     * 一个Component应用多个Module: @Component (modules = {AModule.class, BModule.class})
     *
     * 一个Component依赖其他Component：@Component (modules = {MModule.class}, dependencies = BComponent.class)
     *
     * 注解@Named：相当于有个表示，虽然大家都是同一个对象，但是实例化对象不同就不是一样的
     *
     * 注解@Singleton：单例模式，在想要单例化的对象@Providers上添加即可
     *
     * 注解@Scope：
     *
     * 注解@Documented：标记在文档
     *
     * 注解@Retention(RUNTIME)：运行时级别
     *
     * 注解@Subcomponent：系统提供的一个Component，使用Subcomponent默认会依赖Component
     *
     * 注意：1.Provide如果是单例模式，对应的Component也要是单例模式
     *       2.inject(Activity activity)不能放父类
     *       3.即使使用了单例模式，在不同的Activity中对象是不一样的
     *       4.依赖Component，Component之间的Scoped不能相同
     *       5.子类Component依赖父类的Component，子类Component的Scoped要小于父类的Scoped，
     *       Singleton的级别是Application
     *       6.多个Module之间不能提供相同的对象实例
     *       7.Module中使用了自定义的Scoped，那么对应的Component使用同样的Scoped
     *
     * https://blog.csdn.net/u012131888/article/details/78579787
     */
}
