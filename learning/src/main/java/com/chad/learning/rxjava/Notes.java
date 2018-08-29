package com.chad.learning.rxjava;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class Notes {
    /**
     * 定义：RxJava是一个基于事件流、实现异步操作的库
     *
     * 作用：实现异步操作，类似于Android中的Handler、AsyncTask作用
     *
     * 特点：由于RxJava的使用方式是基于事件流的链式调用，所以使得RxJava逻辑简洁、实现优雅、使用简单，
     *      更重要的是随着程序逻辑的复杂度提高，它依然能够保持简洁&优雅
     *
     *  原理：RxJava基于一种扩展的观察者模式
     *       RxJava的扩展观察者模式中有四个角色：
     *                                          Observable 被观察者 产生事件
     *                                          Observer    观察者 接受事件，并给出响应动作
     *                                          Subscribe   订阅  连接被观察者&观察者
     *                                          Event   事件  被观察者和观察者沟通的载体
     *      RxJava的原理可总结为：被观察者（Observable）通过订阅（Subscribe）按顺序发送事件给观察者
     *                          （Observer），观察者（Observer）按顺序接收事件并作出对应的响应动作
     *
     *  https://blog.csdn.net/carson_ho/article/details/78179340
     */

    // 创建被观察者Observable对象，create()是最基本的创建事件序列的方法
            // 此处传入一个ObservableOnSubscribe对象参数，当Observable被订阅时，ObservableOnSubscribe
            // 的call()方法会自动被调用，即事件序列就会按照设定依次被触发，即观察者会一次调用对应事件的
            // 复写方法从而响应事件，从而实现了被观察者调用了观察者的回调方法&由被观察者向观察者的事件
            // 传递，即观察者模式
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
    Observer<Integer> observer  = new Observer<Integer>() {

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
}
