package com.chad.learning.eventbus;

public class Notes {
    /**
     * EventBus能够简化各组件间的通信，让我们的代码书写变得简单，能有效的分离事件发送方和接收方
     * （也就是解耦的意思），能避免复杂和容易出错的依赖性和生命周期的问题
     *
     * EventBus三要素：
     *              Event事件：Event事件，可以是任意类型
     *              Subscriber事件订阅者：在Event3.0之前我们必须定义以onEvent开头的那几个方法，
     *                                    分别是onEvent、onEventMainThread、onEventBackgroundThread
     *                                    和onEventAsync，而在3.0之后事件处理的方法名可以随意取，不过
     *                                    需要加上注解@subscribe()，并且指定线程模型，模式是POSTING
     *              Publisher事件发布者：我们可以在任意线程里发布事件，一般情况下，使用EventBus.getDefault()
     *                                   就可以得到一个EventBus对象，然后再调用post(Object)方法即可
     *  EventBus四种线程模型：
     *                      POSTING 默认线程，表示事件处理函数的线程跟发布事件的线程在同一个线程
     *                      MAIN 表示事件处理函数的线程在主线程，因此在这里不能进行耗时操作
     *                      BACKGROUND 表示事件处理函数的线程在后台线程，因此不能进行UI操作，如果发布的事件
     *                                 是主线程，那么事件处理函数将开启一个后台线程，如果发布事件函数的线程
     *                                 是后台线程，那么事件处理函数就用该线程
     *                      ASYNC   表示无论事件发布的线程是哪一个，事件处理函数始终会新建一个子线程
     *                              运行，同样不能进行UI操作
     *
     *  EventBus基本用法：与Android原生的广播机制很像，具体请看Demo
     *
     * https://www.jianshu.com/p/f9ae5691e1bb
     */
}
