package com.chad.learning.rxjava.operator.array.activity;

import com.chad.learning.parent.base.BaseAppCompatActivity;

import java.util.ArrayList;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.BiConsumer;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class ArrayOperatorActivity extends BaseAppCompatActivity {

    @Override
    public int getLayoutId() {
        return 0;
    }

    @Override
    public void initViews() {

    }

    @Override
    public void initData() {

    }

    // concat()组合操作符，组合多个被观察者一起发送数据，合并后按发送顺序串行执行，不能超过四个事件
    public void onConcatClick() {
        Observable.concat(
                Observable.just(1),
                Observable.just(2),
                Observable.just(3),
                Observable.just(4))
                .subscribe(new Consumer<Integer>() {

                    @Override
                    public void accept(Integer integer) throws Exception {

                    }
                });
    }

    // concatArray()组合操作符，组合多个被观察者一起发送数据，合并后按发送顺序串行执行，可以超过四个事件
    public void onConcatArray() {
        Observable.concatArray(
                Observable.just(1),
                Observable.just(2),
                Observable.just(3),
                Observable.just(4),
                Observable.just(5)
        ).subscribe(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Exception {

            }
        });
    }

    // merge()组合操作符，组合多个被观察者一起发送数据，合并后按时间线并行执行，
    // 不能超过四个事件
    public void onMergeClick() {
        Observable.merge(
                Observable.intervalRange(0, 3, 1, 1, TimeUnit.SECONDS),
                Observable.intervalRange(2, 3, 1, 1, TimeUnit.SECONDS)
        ).subscribe(new Consumer<Long>() {
            @Override
            public void accept(Long aLong) throws Exception {
                // 执行结果0,2 -> 1,3 -> 2,4
            }
        });
    }

    // merge()组合操作符，组合多个被观察者一起发送数据，合并后按时间线并行执行，
    // 可以超过四个事件
    public void onMergeArrayClick() {
        Observable.mergeArray(
                Observable.intervalRange(0, 3, 1, 1, TimeUnit.SECONDS),
                Observable.intervalRange(1, 3, 1, 1, TimeUnit.SECONDS),
                Observable.intervalRange(2, 3, 1, 1, TimeUnit.SECONDS),
                Observable.intervalRange(3, 3, 1, 1, TimeUnit.SECONDS),
                Observable.intervalRange(4, 3, 1, 1, TimeUnit.SECONDS)
        ).subscribe(new Consumer<Long>() {
            @Override
            public void accept(Long aLong) throws Exception {
                // 执行结果0,1,2,3,4 -> 1,2,3,4,5 -> 2,3,4,5,6
            }
        });
    }

    // concatDelayError()组合操作符，使用concat()操作符时，若其中一个被观察者发出onError事件，
    // 则马上会终止其他被观察者发送事件，该操作符可以将onError事件推迟到其他
    // 被观察者发送事件结束后才触发
    public void onConcatDelayError() {
        Observable.concatArrayDelayError(
                Observable.create(new ObservableOnSubscribe<Integer>() {
                    @Override
                    public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {

                        emitter.onNext(1);
                        emitter.onNext(2);
                        emitter.onNext(3);
                        emitter.onError(new NullPointerException()); // 发送Error事件，因为使用了concatDelayError，所以第2个Observable将会发送事件，等发送完毕后，再发送错误事件
                        emitter.onComplete();
                    }
                }),
                Observable.just(4, 5, 6))
                .subscribe(new Observer<Integer>() {

                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Integer value) {
                    }

                    @Override
                    public void onError(Throwable e) {
                    }

                    @Override
                    public void onComplete() {
                    }
                });
    }

    // mergeDelayError()组合操作符，使用merge()操作符时，若其中一个被观察者发出onError事件，
    // 则马上会终止其他被观察者发送事件，该操作符可以将onError事件推迟到其他
    // 被观察者发送事件结束后才触发
    public void onMergeDelayError() {
        Observable.mergeArrayDelayError(
                Observable.create(new ObservableOnSubscribe<Integer>() {
                    @Override
                    public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {

                        emitter.onNext(1);
                        emitter.onNext(2);
                        emitter.onNext(3);
                        emitter.onError(new NullPointerException()); // 发送Error事件，因为使用了concatDelayError，所以第2个Observable将会发送事件，等发送完毕后，再发送错误事件
                        emitter.onComplete();
                    }
                }),
                Observable.just(4, 5, 6))
                .subscribe(new Observer<Integer>() {

                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Integer value) {
                    }

                    @Override
                    public void onError(Throwable e) {
                    }

                    @Override
                    public void onComplete() {
                    }
                });
    }

    // zip()合并操作符，合并多个被观察者发送的事件，生成一个新的事件序列，并最终发送
    public void onZipClick() {
        // 创建第1个被观察者
        Observable<Integer> observable1 = Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                // 被观察者1发送了事件1
                emitter.onNext(1);
                // 为了方便展示效果，所以在发送事件后加入2s的延迟
                Thread.sleep(1000);

                // 被观察者1发送了事件2
                emitter.onNext(2);
                Thread.sleep(1000);

                // 被观察者1发送了事件3
                emitter.onNext(3);
                Thread.sleep(1000);

                emitter.onComplete();
            }
        }).subscribeOn(Schedulers.io()); // 设置被观察者1在工作线程1中工作

        // 创建第2个被观察者
        Observable<String> observable2 = Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> emitter) throws Exception {
                // 被观察者2发送了事件A
                emitter.onNext("A");
                Thread.sleep(1000);

                // 被观察者2发送了事件B
                emitter.onNext("B");
                Thread.sleep(1000);

                // 被观察者2发送了事件C
                emitter.onNext("C");
                Thread.sleep(1000);

                // 被观察者2发送了事件D
                emitter.onNext("D");
                Thread.sleep(1000);

                emitter.onComplete();
            }
        }).subscribeOn(Schedulers.newThread());
        // 设置被观察者2在工作线程2中工作
        // 假设不作线程控制，则该两个被观察者会在同一个线程中工作，
        // 即发送事件存在先后顺序，而不是同时发送

        // 使用zip变换操作符进行事件合并
        // 注：创建BiFunction对象传入的第3个参数 = 合并后数据的数据类型
        Observable.zip(observable1, observable2, new BiFunction<Integer, String, String>() {
            @Override
            public String apply(Integer integer, String string) throws Exception {
                return integer + string;
            }
        }).subscribe(new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {
            }

            @Override
            public void onNext(String value) {
            }

            @Override
            public void onError(Throwable e) {
            }

            @Override
            public void onComplete() {
            }
        });
    }

    // combineLatest()合并操作符
    public void onCombineLatest() {
        Observable.combineLatest(
                Observable.just(1L, 2L, 3L), // 第1个发送数据事件的Observable
                Observable.intervalRange(0, 3, 1, 1, TimeUnit.SECONDS), // 第2个发送数据事件的Observable：从0开始发送、共发送3个数据、第1次事件延迟发送时间 = 1s、间隔时间 = 1s
                new BiFunction<Long, Long, Long>() {
                    @Override
                    public Long apply(Long o1, Long o2) throws Exception {
                        // o1 = 第1个Observable发送的最新（最后）1个数据
                        // o2 = 第2个Observable发送的每1个数据
                        return o1 + o2;
                        // 合并的逻辑 = 相加
                        // 即第1个Observable发送的最后1个数据 与 第2个Observable发送的每1个数据进行相加
                    }
                }).subscribe(new Consumer<Long>() {
            @Override
            public void accept(Long s) throws Exception {
            }
        });
    }

    // reduce()合并操作符
    public void onReduceClick() {
        Observable.just(1, 2, 3, 4)
                .reduce(new BiFunction<Integer, Integer, Integer>() {
                    // 在该复写方法中复写聚合的逻辑
                    @Override
                    public Integer apply(@NonNull Integer s1, @NonNull Integer s2) throws Exception {
                        return s1 * s2;
                        // 本次聚合的逻辑是：全部数据相乘起来
                        // 原理：第1次取前2个数据相乘，之后每次获取到的数据 = 返回的数据x原始下1个数据每
                    }
                }).subscribe(new Consumer<Integer>() {
            @Override
            public void accept(@NonNull Integer s) throws Exception {
                // 执行顺序：1 * 2  = 2 -> 2 * 3 = 6 -> 6 * 4 = 24
            }
        });
    }

    // collect()合并操作符
    public void onCollectClick() {
        Observable.just(1, 2, 3, 4, 5, 6)
                .collect(
                        // 1. 创建数据结构（容器），用于收集被观察者发送的数据
                        new Callable<ArrayList<Integer>>() {
                            @Override
                            public ArrayList<Integer> call() throws Exception {
                                return new ArrayList<>();
                            }
                            // 2. 对发送的数据进行收集
                        }, new BiConsumer<ArrayList<Integer>, Integer>() {
                            @Override
                            public void accept(ArrayList<Integer> list, Integer integer)
                                    throws Exception {
                                // 参数说明：list = 容器，integer = 后者数据
                                list.add(integer);
                                // 对发送的数据进行收集
                            }
                        }).subscribe(new Consumer<ArrayList<Integer>>() {
            @Override
            public void accept(@NonNull ArrayList<Integer> s) throws Exception {
                // 执行结果：{1， 2，3，4，5，6}

            }
        });
    }

    // startWith()&startWithArray()合并操作符
    public void onStartWith() {
        // 在一个被观察者发送事件前，追加发送一些数据
        // 注：追加数据顺序 = 后调用先追加
        Observable.just(4, 5, 6)
                .startWith(0)  // 追加单个数据 = startWith()
                .startWithArray(1, 2, 3) // 追加多个数据 = startWithArray()
                .subscribe(new Observer<Integer>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onNext(Integer value) {
                    }

                    @Override
                    public void onError(Throwable e) {
                    }

                    @Override
                    public void onComplete() {
                    }
                });

        // 在一个被观察者发送事件前，追加发送被观察者
        // 注：追加数据顺序 = 后调用先追加
        Observable.just(4, 5, 6)
                .startWith(Observable.just(1, 2, 3))
                .subscribe(new Observer<Integer>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Integer value) {
                    }

                    @Override
                    public void onError(Throwable e) {
                    }

                    @Override
                    public void onComplete() {
                    }
                });
    }

    // count()统计被观察者发送事件的数量
    public void onCountClick() {
        Observable.just(1, 2, 3, 4)
                .count()
                .subscribe(new Consumer<Long>() {
                    @Override
                    public void accept(Long aLong) throws Exception {
                        // 返回结果是long类型
                    }
                });
    }
}
