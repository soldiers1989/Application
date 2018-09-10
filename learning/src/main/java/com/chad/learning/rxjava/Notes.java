package com.chad.learning.rxjava;

public class Notes {
    /**
     *  定义：RxJava是一个基于事件流、实现异步操作的库
     *
     *  作用：实现异步操作，类似于Android中的Handler、AsyncTask作用
     *
     *  特点：由于RxJava的使用方式是基于事件流的链式调用，所以使得RxJava逻辑简洁、实现优雅、使用简单，
     *  更重要的是随着程序逻辑的复杂度提高，它依然能够保持简洁&优雅
     *
     *  原理：RxJava基于一种扩展的观察者模式
     *       RxJava的扩展观察者模式中有四个角色：
     *       Observable 被观察者 产生事件
     *       Observer    观察者 接受事件，并给出响应动作
     *       Subscribe   订阅  连接被观察者&观察者
     *       Event   事件  被观察者和观察者沟通的载体
     *  RxJava的原理可总结为：被观察者（Observable）通过订阅（Subscribe）按顺序发送事件给观察者
     *  （Observer），观察者（Observer）按顺序接收事件并作出对应的响应动作
     *
     *  RxJava2.x提供了多个函数式接口，用于实现简便式的观察者模式
     *
     *  快速创建操作符：
     *                  Observable.empty()  只发送onComplete()事件
     *                  Observable.error()  只发送onError()事件
     *                  Observable.never()  不发送任何事件
     *  延迟创建操作符：
     *                  Observable.defer()  有观察者订阅时，才动态创建被观察者&发送事件
     *                  Observable.timer()  延迟指定时间后，发送一个Long数值0，默认运行在第一个线程，
     *                                      可以自定义任务调度器（Scheduler）
     *                  Observable.interval()   每隔指定的时间就发送事件，
     *                                          发送的事件序列从0开始无限的递增1的整数序列，
     *                                          默认在Computation调度器上运行，可以自定义任务调度器（Scheduler）
     *                  Observable.intervalRange()  每隔指定的时间就发送事件，可以指定发送数据的数量，
     *                                              发送的时间序列从0开始无限的递增1的整数序列，
     *                                              作用类似与interval()
     *                  Observable.range()  连续发送一个事件，可指定范围，
     *                                      发送的事件序列从0开始无限的递增1的整数序列，
     *                                      作用类似于intervalRange()，区别在于无延迟发送
     *                  Observable.rangeLong()  连续发送一个事件，可指定范围，
     *                                          发送的事件序列从0开始无限的递增1的整数序列，
     *                                          作用类似于range()，区别在于支持Long数据类型
     *
     *  功能性操作符：辅助被观察者（Observable）在发送事件时实现一些功能性的需求，如错误处理，线程调度等
     *
     *  过滤操作符：过滤/筛选被观察者（Observable）发送的事件
     *             根据指定条件过滤事件：filter()、ofType()、skip()、skipLast()、distinct()、distinctUntilChanged();
     *             根据指定事件数量过滤事件：take()、takeLast()
     *             根据指定时间过滤事件：throttleFirst()、throttleLast()、sample()、throttleWithTimeout(),
     *                                  debounce()
     *             根据指定事件位置过滤事件：firstElement()、lastElement()、elementAt()、elementAtOrError()
     *
     *  https://blog.csdn.net/carson_ho/article/details/78179340
     */
}
