package com.chad.learning.rxjava.operator.filter.activity;

import android.support.v7.widget.AppCompatTextView;

import com.chad.learning.R;
import com.chad.learning.parent.base.BaseAppCompatActivity;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * 根据指定时间过滤事件
 */
public class TimeActivity extends BaseAppCompatActivity {

    @BindView(R.id.text_content)
    AppCompatTextView mTextContent;

    private String mContent;

    @Override
    public int getLayoutId() {
        return R.layout.activity_rx_java_operator_filter_time;
    }

    @Override
    public void initViews() {

    }

    @Override
    public void initData() {

    }

    // throttleFirst()过滤操作符，在某段时间内，只发送该时间段内的第一次事件
    @OnClick(R.id.btn_throttle_first)
    public void onThrottleFirstClick() {
        mContent = "throttleFirst \n";
        Observable.create(new ObservableOnSubscribe<Integer>() {

            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                emitter.onNext(1);
                Thread.sleep(500);
                emitter.onNext(2);
                Thread.sleep(400);
                emitter.onNext(3);
                Thread.sleep(300);
                emitter.onNext(4);
                Thread.sleep(300);
                emitter.onNext(5);
                Thread.sleep(300);
                emitter.onNext(6);
                Thread.sleep(400);
                emitter.onNext(7);
                Thread.sleep(300);
                emitter.onNext(8);
                Thread.sleep(300);
                emitter.onNext(9);
                Thread.sleep(300);
                emitter.onComplete();
            }
        }).throttleFirst(1, TimeUnit.SECONDS) // 只发送一秒之内的第一次事件
                .subscribe(new Observer<Integer>() {

                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Integer integer) {
                        mContent += "onNext = " + integer + "\n";
                        mTextContent.setText(mContent);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {
                        // 收到的事件是1、4、7
                        mContent += "onComplete \n";
                        mTextContent.setText(mContent);
                    }
                });
    }

    // throttleLast()过滤操作符，在某段时间内，只发送该时间段内的最后一次事件
    @OnClick(R.id.btn_throttle_last)
    public void onThrottleLastClick() {
        mContent = "throttleLast \n";
        Observable.create(new ObservableOnSubscribe<Integer>() {

            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                emitter.onNext(1);
                Thread.sleep(500);
                emitter.onNext(2);
                Thread.sleep(400);
                emitter.onNext(3);
                Thread.sleep(300);
                emitter.onNext(4);
                Thread.sleep(300);
                emitter.onNext(5);
                Thread.sleep(300);
                emitter.onNext(6);
                Thread.sleep(400);
                emitter.onNext(7);
                Thread.sleep(300);
                emitter.onNext(8);
                Thread.sleep(300);
                emitter.onNext(9);
                Thread.sleep(300);
                emitter.onComplete();
            }
        }).throttleLast(1, TimeUnit.SECONDS) // 只发送一秒之内的最后一次事件
                .subscribe(new Observer<Integer>() {

                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Integer integer) {
                        // 收到的事件是3、6、9
                        mContent += "onNext = " + integer + "\n";
                        mTextContent.setText(mContent);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {
                        mContent += "onComplete \n";
                        mTextContent.setText(mContent);
                    }
                });
    }

    // sample()过滤操作符，在某段事件内，只发送该时间段内最新（最后）一次事件，与throttleLast()类似
    @OnClick(R.id.btn_sample)
    public void onSample() {
        mContent = "sample \n";
        Observable.create(new ObservableOnSubscribe<Integer>() {

            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                emitter.onNext(1);
                Thread.sleep(500);
                emitter.onNext(2);
                Thread.sleep(400);
                emitter.onNext(3);
                Thread.sleep(300);
                emitter.onNext(4);
                Thread.sleep(300);
                emitter.onNext(5);
                Thread.sleep(300);
                emitter.onNext(6);
                Thread.sleep(400);
                emitter.onNext(7);
                Thread.sleep(300);
                emitter.onNext(8);
                Thread.sleep(300);
                emitter.onNext(9);
                Thread.sleep(300);
                emitter.onComplete();
            }
        }).sample(1, TimeUnit.SECONDS) // 只发送一秒之内的最后一次事件
                .subscribe(new Observer<Integer>() {

                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Integer integer) {
                        // 收到的事件是3、6、9
                        mContent += "onNext = " + integer + "\n";
                        mTextContent.setText(mContent);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {
                        mContent += "onComplete \n";
                        mTextContent.setText(mContent);
                    }
                });
    }

    // throttleWithTimeout()过滤操作符，发送数据事件时，若两次发送的事件间隔小于指定时间，
    // 就会丢弃前一次的数据，直到指定时间内都没有新数据发射时才会发送最后一次数据
    @OnClick(R.id.btn_throttle_with_timeout)
    public void onThrottleWithTimeOut() {
        mContent = "throttleWithTimeout \n";
        Observable.create(new ObservableOnSubscribe<Integer>() {

            @Override
            public void subscribe(ObservableEmitter<Integer> e) throws Exception {
                // 隔段事件发送时间
                e.onNext(1);
                Thread.sleep(500);
                e.onNext(2); // 1和2之间的间隔小于指定时间1s，所以前1次数据（1）会被抛弃，2会被保留
                Thread.sleep(1500);  // 因为2和3之间的间隔大于指定时间1s，所以之前被保留的2事件将发出
                e.onNext(3);
                Thread.sleep(1500);  // 因为3和4之间的间隔大于指定时间1s，所以3事件将发出
                e.onNext(4);
                Thread.sleep(500); // 因为4和5之间的间隔小于指定时间1s，所以前1次数据（4）会被抛弃，5会被保留
                e.onNext(5);
                Thread.sleep(500); // 因为5和6之间的间隔小于指定时间1s，所以前1次数据（5）会被抛弃，6会被保留
                e.onNext(6);
                Thread.sleep(1500); // 因为6和Complete实践之间的间隔大于指定时间1s，所以之前被保留的6事件将发出

                e.onComplete();
            }
        }).throttleWithTimeout(1, TimeUnit.SECONDS)//每1秒中采用数据
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

    // debounce()过滤操作符，发送数据事件时，若两次发送的事件间隔小于指定时间，
    // 就会丢弃前一次的数据，直到指定时间内都没有新数据发射时才会发送最后一次数据
    @OnClick(R.id.btn_debounce)
    public void onDebounce() {
        mContent = "debounce \n";
        Observable.create(new ObservableOnSubscribe<Integer>() {

            @Override
            public void subscribe(ObservableEmitter<Integer> e) throws Exception {
                // 隔段事件发送时间
                e.onNext(1);
                Thread.sleep(500);
                e.onNext(2); // 1和2之间的间隔小于指定时间1s，所以前1次数据（1）会被抛弃，2会被保留
                Thread.sleep(1500);  // 因为2和3之间的间隔大于指定时间1s，所以之前被保留的2事件将发出
                e.onNext(3);
                Thread.sleep(1500);  // 因为3和4之间的间隔大于指定时间1s，所以3事件将发出
                e.onNext(4);
                Thread.sleep(500); // 因为4和5之间的间隔小于指定时间1s，所以前1次数据（4）会被抛弃，5会被保留
                e.onNext(5);
                Thread.sleep(500); // 因为5和6之间的间隔小于指定时间1s，所以前1次数据（5）会被抛弃，6会被保留
                e.onNext(6);
                Thread.sleep(1500); // 因为6和Complete实践之间的间隔大于指定时间1s，所以之前被保留的6事件将发出

                e.onComplete();
            }
        }).debounce(1, TimeUnit.SECONDS)// 每1秒中采用数据
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
}
