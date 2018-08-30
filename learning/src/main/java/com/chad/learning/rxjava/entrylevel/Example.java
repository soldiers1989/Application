package com.chad.learning.rxjava.entrylevel;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

public class Example {

    // 创建被观察者Observable对象，create()是最基本的创建事件序列的方法
    Observable<Integer> observable = Observable.create(new ObservableOnSubscribe<Integer>() {
        // 在复写subscribe()里定义需要发送的事件
        // 通过ObservableEmitter类对象产生事件并通知观察者
        // ObservableEmitter类是事件发射器，定义需要发送的事件&向观察者发送事件
        @Override
        public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
            emitter.onNext(1);
            emitter.onNext(2);
            emitter.onNext(3);
            emitter.onComplete();
            //  在一个正确运行的事件序列中，onComplete()和onError()二者互斥，只能存在一个
        }
    });
    // 创建被观察者Observable对象扩展方法1：直接将传入的参数依次发送出来
    Observable observableJust = Observable.just("A", "B", "C");
    // 创建被观察者Observable对象扩展方法2：将传入的数组拆分成具体对象后依次发送出来
    String[] words = {"A", "B", "C"};
    Observable observableFrom = Observable.fromArray(words);

    // 创建观察者对象Observer方式1：采用Observer接口
    Observer<Integer> observer = new Observer<Integer>() {

        // 观察者接收事件前调用
        @Override
        public void onSubscribe(Disposable d) {

        }

        // 被观察者生产onNext事件&观察者接收到next事件时调用
        @Override
        public void onNext(Integer integer) {

        }

        // 被观察者生产onError事件&观察者接收到next事件时调用
        @Override
        public void onError(Throwable e) {

        }

        // onComplete&观察者接收到next事件时调用
        @Override
        public void onComplete() {

        }
    };
    // 创建观察者对象Observer方式2：采用Subscriber抽象类
    Subscriber<Integer> subscriber = new Subscriber<Integer>() {
        /**
         * Subscriber是一个实现了Observer的抽象类，对Observer接口进行了扩展，并新增了两个方法：
         * 1、onStart()在还未响应事件前调用，用于做一些初始化工作
         * 2、unsubscribe()用于取消订阅，该方法被调用后，观察者不再接收&响应事件，调用该方法前，
         *   先使用 isUnsubscribed() 判断状态，确定被观察者Observable是否还持有观察者Subscriber的引用，
         *   如果引用不能及时释放，就会出现内存泄露
         */
        // 观察者接收事件前调用
        @Override
        public void onSubscribe(Subscription s) {

        }

        // 被观察者生产onNext事件&观察者接收到next事件时调用
        @Override
        public void onNext(Integer integer) {

        }

        // 被观察者生产onNext事件&观察者接收到next事件时调用
        @Override
        public void onError(Throwable t) {

        }

        // onComplete&观察者接收到next事件时调用
        @Override
        public void onComplete() {

        }
    };

    // 订阅方式1：订阅Observer接口 Observable.subscribe(Observer);
    // 订阅方式2：订阅Subscriber抽象类 Observable.subscribe(Subscriber);

    // Observable.subscribe(Subscriber)的内部实现
    //    public Subscription subscribe(Subscriber subscriber) {
    //        观察者Subscriber抽象类复写方法，用于初始化工作
    //        subscriber.onStart();
    //        通过该调用，从而回调观察者中的对应方法从而响应被观察者生产的事件,
    //        从而实现被观察者调用了观察者的回调方法 & 由被观察者向观察者的事件传递，即观察者模式,
    //        Observable只是生产事件，真正的发送事件是在它被订阅的时候，即当 subscribe() 方法执行时
    //        onSubscribe.call(subscriber);
    //    }

    // RxJava基于事件流的链式调用
    public void lianShi() {
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                emitter.onNext(1);
                emitter.onNext(2);
                emitter.onNext(3);
                emitter.onComplete();
            }
        }).subscribe(new Observer<Integer>() {

            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Integer integer) {

            }

            @Override
            public void onError(Throwable t) {

            }

            @Override
            public void onComplete() {

            }
        });
    }

    //  以Consumer为例，实现简便式观察者模式
    public void  consumer() {
        Observable.just("Hello World").subscribe(new Consumer<String>() {
            // 每次接收到Observable的事件都会调用Consumer.accept（）
            @Override
            public void accept(String s) throws Exception {

            }
        });
    }


}
