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
     *  基本创建操作符：Observable.create() 完整创建一个被观察者对象
     *
     *  快速创建操作符：
     *                  Observable.just()   快速创建一个被观察者对象，直接发送传入的事件， 最多只能发送10个参数
     *                  Observable.fromArray()  快速创建一个被观察者对象，直接发送传入的数组数据，
     *                                          会将数组中的数据转换为Observable对象，可以发送十个以上事件，
     *                                          可用于数组元素遍历
     *                  Observable.fromIterable()   快速创建一个被观察者对象，直接发送传入的集合数据，
     *                                              会将集合中的数据转换为Observable对象，可以发送十个以上事件，
     *                                              可用于集合元素遍历
     *
     *                  Observable.empty()  只发送onComplete()事件
     *                  Observable.error()  只发送onError()事件
     *                  Observable.never()  不发送任何事件
     *
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
     *                subscribe()   订阅，即连接观察者和被观察者
     *                subscribeOn()、observeOn() 线程调度，快速、方便指定控制被观察者和观察者的工作线程
     *                delay()   使得被观察者延迟一段时间发送事件，有四个重载方法
     *                do()  在某个事件的生命周期中执行，do操作符有很多个，具体看Demo
     *                onErrorReturn()   遇到错误时，发送一个特殊事件且正常终止，可捕获在他之前发生的异常
     *                onErrorResumeNext()   遇到错误时，发送一个新的Observable，拦截的错误是Throwable
     *                onExceptionResumeNext() 遇到错误时，发送一个新的Observable，拦截的错误是Exception
     *                retry() 重试，即当出现错误时，让被观察者（Observable）重新发送数据，有五个重载方法
     *                retryUntil()  出现错误后，判断是否需要重新发送数据，类似于retry(Predicate)，唯一区别，
     *                              retryUntil()返回true则不重新发送数据
     *                retryWhen()   遇到错误时，将发送的错误传递个一个新的被观察者（Observable）,
     *                              并决定是否需要重新订阅原始被观察者（Observable）
     *                repeat()  无条件的、重复的发送被观察者事件
     *                repeatWhen()  有条件的、重复的发送被观察者事件，将原始Observable停止发送事件的标识
     *                              （Complete()/Error()）转换成一个Object类型数据传递给一个新的被观察者，
     *                              以此决定是否重新订阅原来的Observable
     *
     *
     *  过滤操作符：过滤/筛选被观察者（Observable）发送的事件
     *             根据指定条件过滤事件：filter()  过滤特定条件的事件
     *                                   ofType()   过滤特定数据类型的数据
     *                                   skip() 跳过某个事件，跳过正序的第一个事件
     *                                   skipLast() 跳过某个事件，跳过反序的第一个事件
     *                                   distinct() 过滤事件序列中重复的事件
     *                                   distinctUntilChanged() 过滤事件序列中连续重复的事件
     *             根据指定事件数量过滤事件：take()  指定观察者最多能接收到事件的数量
     *                                       takeLast() 指定观察者只能接收到被观察者发送的最后几个事件
     *             根据指定时间过滤事件：throttleFirst()   在某段时间内，只发送该时间段内的第一次事件
     *                                   throttleLast() 在某段时间内，只发送改时间段内的最后一次事件
     *                                   sample()   在某段时间内，只发送该时间段内最新一次事件，
     *                                              与throttleLast()类似
     *                                   throttleWithTimeout()  发送数据事件时，若2次发送事件的间隔＜指定时间，
     *                                                          就会丢弃前一次的数据，直到指定时间内
     *                                                          都没有新数据发射时才会发送后一次的数据
     *                                   debounce() 发送数据事件时，若2次发送事件的间隔＜指定时间，
     *                                              就会丢弃前一次的数据，直到指定时间内
     *                                              都没有新数据发射时才会发送后一次的数据
     *             根据指定事件位置过滤事件：firstElement()  仅选取第一个元素
     *                                       lastElement()  仅选取最后一个元素
     *                                       elementAt()    指定接收某个元素（通过索引值确定），允许越界，
     *                                                      可以设置一个默认参数，越界时候会执行
     *                                       elementAtOrError() 在elementAt()基础上，当出现越界情况，抛出异常
     *
     *  条件/布尔操作符：通过设置函数，判断被观察者发送的事件是否符合条件
     *                   all()  判断发送的每项数据是否都满足设置的函数条件，若满足返回true，否则返回false
     *                   contains() 判断发送的数据中是否包含指定数据，若包含返回true，否则返回false
     *                   isEmpty()  判断发送的数据是否为空，若为空返回true，否则返回false
     *                   amb()  当需要发送多个Observable时，只发送先发送数据的Observable的数据，
     *                          而其余Observable则被丢弃，
     *                   takeWhile()    判断发送的每项数据是否满足设置的函数条件，若发送的数据满足该条件，
     *                                  则发送该项数据，否则不发送
     *                   takeUntil()    执行到某个条件时，停止发送事件，该判断条件也可以是Observable，即等到
     *                                  takeUtil()传入的Observable开始发送数据，原始Observable的数据停止发送数据
     *                   skipWhile()    判断发送的每项数据是否满足设置的函数条件，直接判断该条件 = false时，
     *                                  才开始发送Observable的数据
     *                   skipUntil()    等到skipUntil()传入的Observable开始发送数据，原始Observable的数据才
     *                                  开始发送
     *                   defaultIfEmpty()   在不发送任何有效事件，仅发送了Complete事件的前提下，发送一个默认值
     *                   SequenceEqual()    判断两个Observable需要发送的数据是否相同，若相同返回true，
     *                                      否则返回false
     *  变换操作符：
     *              map() 对观察者发送的每一个事件都通过指定的函数处理，从而变换成另外一种事件
     *                    即被观察者发送的事件转换为任意类型的事件
     *              flatMap()   将被观察者发送的事件序列进行拆分&单独转换，在合并成一个新的事件序列，
     *                          最后在进行发送
     *              concatMap() 类似flatMap()，与flatMap()的区别是拆分&重新合并生成的事件序列的顺序等于
     *                          被观察者旧序列生产的顺序
     *              buffer()    定期从被观察者需要发送的事件中获取一定数量的事件放到缓存中，最终发送
     *
     *  组合/合并操作符：
     *                  组合多个被观察者：
     *                                  按发送顺序：
     *                                              concat()    组合多个被观察者一起发送数据，
     *                                                          合并后按发送顺序串行执行，
     *                                                          不能超过四个事件
     *                                              concatArray()   组合多个被观察者一起发送数据，
     *                                                              合并后按发送顺序串行执行，
     *                                                              可以超过四个事件
     *                                  按时间：
     *                                          merge() 组合多个被观察者一起发送数据，
     *                                                  合并后按时间线并行执行，
     *                                                  不能超过四个事件
     *                                          mergeArray()   组合多个被观察者一起发送数据，
     *                                                          合并后按时间线并行执行，
     *                                                          可以超过四个事件
     *                                  错误处理：
     *                                          concatDelayError() 使用concat()操作符时，
     *                                                             若其中一个被观察者发出onError事件，
     *                                                             则马上会终止其他被观察者发送事件，
     *                                                             该操作符可以将onError事件推迟到其他
     *                                                             被观察者发送事件结束后才触发
     *                                          mergeDelayError()  使用merge()操作符时，
     *                                                             若其中一个被观察者发出onError事件，
     *                                                             则马上会终止其他被观察者发送事件，
     *                                                             该操作符可以将onError事件推迟到其他
     *                                                             被观察者发送事件结束后才触发
     *                  合并多个事件：
     *                              按数量：
     *                                      zip()   合并多个被观察者发送的事件，生成一个新的事件序列，
     *                                              并最终发送
     *                              按时间：
     *                                      combineLatest() 当两个Observables中的任何一个发送了数据后，
     *                                                      将先发送了数据的Observables 的最新（最后）
     *                                                      一个数据 与 另外一个Observable发送的每个数据结合，
     *                                                      最终基于该函数的结果发送数据，与Zip（）的区别：
     *                                                      Zip（） = 按个数合并，即1对1合并；
     *                                                      CombineLatest（） = 按时间合并，即在同一个时间点上合并
     *                                      combineLatestDelayError()   作用类似于concatDelayError（）
     *                                                                  / mergeDelayError（） ，即错误处理
     *                              合并成一个事件发送：
     *                                                  reduce() 把被观察者需要发送的事件聚合成1个事件 & 发送，
     *                                                           聚合的逻辑根据需求撰写，但本质都是前2个数据聚合，
     *                                                           然后与后1个数据继续进行聚合，依次类推
     *                                                  collect()   将被观察者Observable发送的数据事件收集到一个数据结构里
     *                  发送事件前追加发送事件：
     *                                          startWith() 在一个被观察者发送事件前，追加发送一些数据 / 一个新的被观察者
     *                                          startWithArray()    在一个被观察者发送事件前，追加发送一些数据 / 一个新的被观察者
     *                  统计发送事件数量：
     *                                  count() 统计被观察者发送事件的数量
     *
     *  https://blog.csdn.net/carson_ho/article/details/78179340
     */
}
