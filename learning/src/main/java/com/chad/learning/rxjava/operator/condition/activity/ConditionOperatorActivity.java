package com.chad.learning.rxjava.operator.condition.activity;

import android.support.v7.widget.AppCompatTextView;

import com.chad.learning.R;
import com.chad.learning.parent.base.BaseAppCompatActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Predicate;

public class ConditionOperatorActivity extends BaseAppCompatActivity {

    @BindView(R.id.text_content)
    AppCompatTextView mTextContent;

    private String mContent;

    @Override
    public int getLayoutId() {
        return R.layout.activity_rx_java_operator_condition;
    }

    @Override
    public void initViews() {

    }

    @Override
    public void initData() {

    }

    // all()条件/布尔操作符，判断发送的每项数据是否都满足设置的函数条件，若满足返回true，否则返回false
    @OnClick(R.id.btn_all)
    public void onAllClick() {
        mContent = "all \n";
        Observable.just(1, 2, 3, 4, 5, 6)
                .all(new Predicate<Integer>() {
                    @Override
                    public boolean test(Integer integer) throws Exception {
                        return integer <= 10; // 该函数用于判断Observable发送的所有数据是否全部都小于等于10
                    }
                })
                .subscribe(new Consumer<Boolean>() {
                    @Override
                    public void accept(Boolean aBoolean) throws Exception {
                        mContent += "accept = " + aBoolean + "\n";
                        mTextContent.setText(mContent);
                    }
                });
    }

    // takeWhile()条件/布尔操作符，判断发送的每项数据是否满足设置的函数条件
    // 若发送的数据满足该条件，则发送该项数据，否则不发送
    @OnClick(R.id.btn_take_while)
    public void onTakeWhile() {
        mContent = "takeWhile \n";
        // 每秒发送一个数据，从0开始
        Observable.interval(1, TimeUnit.SECONDS)
                .takeWhile(new Predicate<Long>() {
                    @Override
                    public boolean test(Long aLong) throws Exception {
                        return aLong < 3;   // 当发送的数据小于3时，才发送Observable的数据
                    }
                })
                .subscribe(new Consumer<Long>() {
                    @Override
                    public void accept(Long aLong) throws Exception {
                        mContent += "accept = " + aLong + "\n";
                        mTextContent.setText(mContent);
                    }
                });
    }

    // skipWhile()条件/布尔操作符，判断发送的每项数据是否满足设置的函数条件，
    // 直到该判断条件等于false时，才开始发送Observable的数据
    @OnClick(R.id.btn_skip_while)
    public void onSkipWhile() {
        mContent = "skipWhile \n";
        // 每秒发送一个数据，从0开始
        Observable.interval(1, TimeUnit.SECONDS)
                .skipWhile(new Predicate<Long>() {
                    @Override
                    public boolean test(Long aLong) throws Exception {
                        return aLong < 5; // 判断条件不成立，才会发送数据
                    }
                })
                .subscribe(new Consumer<Long>() {
                    @Override
                    public void accept(Long aLong) throws Exception {
                        mContent += "accept = " + aLong + "\n";
                        mTextContent.setText(mContent);
                    }
                });
    }

    // takeUntil()条件/布尔操作符，执行到某个条件时，停止发送事件
    @OnClick(R.id.btn_take_until)
    public void onTakeUntil() {
        mContent = "takeUntil \n";
        Observable.interval(1, TimeUnit.SECONDS)
                .takeUntil(new Predicate<Long>() {
                    @Override
                    public boolean test(Long aLong) throws Exception {
                        return aLong > 3; // 当发送的数据大于3时，停止发送事件
                    }
                })
                .subscribe(new Consumer<Long>() {
                    @Override
                    public void accept(Long aLong) throws Exception {
                        mContent += "accept = " + aLong + "\n";
                        mTextContent.setText(mContent);
                    }
                });

        // 该判断条件也可以是Observable，
        // 即等到takeUntil()传入的Observable开始发送数据,
        // （原始）第1个Observable的数据停止发送数据
        Observable.interval(1, TimeUnit.SECONDS)
                .takeUntil(Observable.timer(5, TimeUnit.SECONDS)) // 这个Observable开始发送数据
                .subscribe(new Observer<Long>() {                        // 原始的Observable就停止发送数据

                    @Override
                    public void onSubscribe(Disposable d) {
                        mContent += "onSubscribe \n";
                        mTextContent.setText(mContent);
                    }

                    @Override
                    public void onNext(Long aLong) {
                        mContent += "onNext = " + aLong + "\n";
                        mTextContent.setText(mContent);
                    }

                    @Override
                    public void onError(Throwable e) {
                        mContent += "onError \n";
                        mTextContent.setText(mContent);
                    }

                    @Override
                    public void onComplete() {
                        mContent += "onComplete \n";
                        mTextContent.setText(mContent);
                    }
                });
    }

    // skipUntil()条件/布尔操作符，等到skipUntil()开始发送数据，
    // （原始）第一个Observable的数据才开始发送数据
    @OnClick(R.id.btn_skip_until)
    public void onSkipUntil() {
        mContent = "skipUntil \n";
        Observable.interval(1, TimeUnit.SECONDS)
                .skipUntil(Observable.timer(5, TimeUnit.SECONDS)) // 这个Observable开始发送数据，
                .subscribe(new Observer<Long>() {                       // 原始的Observable才开始发送数据

                    @Override
                    public void onSubscribe(Disposable d) {
                        mContent += "onSubscribe \n";
                        mTextContent.setText(mContent);
                    }

                    @Override
                    public void onNext(Long aLong) {
                        mContent += "onNext = " + aLong + "\n";
                        mTextContent.setText(mContent);
                    }

                    @Override
                    public void onError(Throwable e) {
                        mContent += "onError \n";
                        mTextContent.setText(mContent);
                    }

                    @Override
                    public void onComplete() {
                        mContent += "onComplete \n";
                        mTextContent.setText(mContent);
                    }
                });
    }

    // sequenceEqual()条件/布尔操作符，判定两个Observable需要发送的数据是否相同
    @OnClick(R.id.btn_sequence_equal)
    public void onSequenceEqualClick() {
        mContent = "sequenceEqual \n";
        Observable.sequenceEqual(Observable.just(1, 2, 3), Observable.just(1, 2, 3))
                .subscribe(new Consumer<Boolean>() {
                    @Override
                    public void accept(Boolean aBoolean) throws Exception {
                        mContent += "accept = " + aBoolean + "\n";
                        mTextContent.setText(mContent);
                    }
                });
    }

    // contains()条件/布尔操作符，判断发送的数据中是否包含指定数据
    @OnClick(R.id.btn_contains)
    public void onContainsClick() {
        mContent = "contains \n";
        Observable.just(1, 2, 3, 4)
                .contains(4)
                .subscribe(new Consumer<Boolean>() {
                    @Override
                    public void accept(Boolean aBoolean) throws Exception {
                        mContent += "accept = " + aBoolean + "\n";
                        mTextContent.setText(mContent);
                    }
                });
    }

    // isEmpty()条件/布尔操作符，判断发送的数据是否为空
    @OnClick(R.id.btn_is_empty)
    public void onIsEmptyClick() {
        mContent = "isEmpty \n";
        Observable.just(1, 2, 3, 4)
                .isEmpty()
                .subscribe(new Consumer<Boolean>() {
                    @Override
                    public void accept(Boolean aBoolean) throws Exception {
                        mContent += "accept = " + aBoolean + "\n";
                        mTextContent.setText(mContent);
                    }
                });
    }

    // amb()条件/布尔操作符，当需要发送多个Observable时，只发送先发送数据的Observable数据，
    // 其余的Observable则被丢弃
    @OnClick(R.id.btn_amb)
    public void onAmbClick() {
        mContent = "amb \n";
        // 创建一个List，存放多个Observable
        List<ObservableSource<Integer>> observableSources = new ArrayList<>();
        observableSources.add(Observable.just(1, 2, 3).delay(1, TimeUnit.SECONDS));
        observableSources.add(Observable.just(4, 5, 6));
        Observable.amb(observableSources)
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        mContent += "accept = " + integer + "\n";
                        mTextContent.setText(mContent);
                    }
                });
    }

    // defaultIfEmpty()条件/布尔操作符，再不发送任何有效事件，仅发送了Complete事件的前提下，发送一个默认值
    @OnClick(R.id.btn_default_if_empty)
    public void onDefaultIfEmptyClick() {
        mContent = "defaultIfEmpty\n";
        Observable.create(new ObservableOnSubscribe<Integer>() {

            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                emitter.onComplete(); // 不发送任何有效事件，发送Complete事件
            }
        }).defaultIfEmpty(10) // 发送默认值
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

                    }
                });
    }
}
