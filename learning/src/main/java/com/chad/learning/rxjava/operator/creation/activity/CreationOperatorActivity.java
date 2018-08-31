package com.chad.learning.rxjava.operator.creation.activity;

import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;

import com.chad.learning.R;
import com.chad.learning.parent.base.BaseAppCompatActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;

/**
 * 创建操作符
 */
public class CreationOperatorActivity extends BaseAppCompatActivity implements View.OnClickListener {

    @BindView(R.id.btn_create)
    AppCompatButton mBtnCreate;
    @BindView(R.id.btn_just)
    AppCompatButton mBtnJust;
    @BindView(R.id.btn_from_array)
    AppCompatButton mBtnFromArray;
    @BindView(R.id.btn_from_iterable)
    AppCompatButton mBtnFromIterable;
    @BindView(R.id.btn_defer)
    AppCompatButton mBtnDefer;
    @BindView(R.id.btn_timer)
    AppCompatButton mBtnTimer;
    @BindView(R.id.btn_interval)
    AppCompatButton mBtnInterval;
    @BindView(R.id.btn_interval_range)
    AppCompatButton mBtnIntervalRange;
    @BindView(R.id.btn_range)
    AppCompatButton mBtnRange;
    @BindView(R.id.btn_range_long)
    AppCompatButton mBtnRangeLong;
    @BindView(R.id.text_content)
    AppCompatTextView mTextContent;

    private Integer mDefer = 10;

    private String mContent;

    @Override
    public int getLayoutId() {
        return R.layout.activity_rx_java_operator_creation;
    }

    @Override
    public void initViews() {

    }

    @Override
    public void initData() {

    }

    @OnClick({R.id.btn_create, R.id.btn_just, R.id.btn_from_array, R.id.btn_from_iterable,
            R.id.btn_defer, R.id.btn_timer, R.id.btn_interval, R.id.btn_interval_range,
            R.id.btn_range, R.id.btn_range_long})

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_create:
                onCreateClick();
                break;
            case R.id.btn_just:
                onJustClick();
                break;
            case R.id.btn_from_array:
                onFromArrayClick();
                break;
            case R.id.btn_from_iterable:
                onFromIterableClick();
                break;
            case R.id.btn_defer:
                onDeferClick();
                break;
            case R.id.btn_timer:
                onTimerClick();
                break;
            case R.id.btn_interval:
                onIntervalClick();
                break;
            case R.id.btn_interval_range:
                onIntervalRangeClick();
                break;
            case R.id.btn_range:
                onRangeClick();
                break;
            case R.id.btn_range_long:
                onRangeLongClick();
                break;
            default:
                break;
        }
    }

    // create()基本创建操作符
    private void onCreateClick() {
        mContent = "create" + "\n";
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
                mContent += "onSubscribe" + "\n";
                mTextContent.setText(mContent);
            }

            @Override
            public void onNext(Integer integer) {
                mContent += "onNext " + integer + "\n";
                mTextContent.setText(mContent);
            }

            @Override
            public void onError(Throwable e) {
                mContent += "onError" + "\n";
                mTextContent.setText(mContent);
            }

            @Override
            public void onComplete() {
                mContent += "onComplete" + "\n";
                mTextContent.setText(mContent);
            }
        });
    }

    // just()快速创建操作符，特点是直接发送传入的事件，只能发送十个以下事件
    private void onJustClick() {
        mContent = "just" + "\n";
        Observable.just(1, 2, 3, 4).subscribe(new Observer<Integer>() {
            @Override
            public void onSubscribe(Disposable d) {
                mContent += "onSubscribe" + "\n";
                mTextContent.setText(mContent);
            }

            @Override
            public void onNext(Integer integer) {
                mContent += "onNext " + integer + "\n";
                mTextContent.setText(mContent);
            }

            @Override
            public void onError(Throwable e) {
                mContent += "onError" + "\n";
                mTextContent.setText(mContent);
            }

            @Override
            public void onComplete() {
                mContent += "onComplete" + "\n";
                mTextContent.setText(mContent);
            }
        });
    }

    // fromArray()快速创建操作符，特点是直接发送传入的数组数据，可以发送十个以上事件，可用于数组元素遍历
    private void onFromArrayClick() {
        mContent = "fromArray" + "\n";
        Integer[] integers = {1, 2, 3, 4, 5};
        Observable.fromArray(integers).subscribe(new Observer<Integer>() {
            @Override
            public void onSubscribe(Disposable d) {
                mContent += "onSubscribe" + "\n";
                mTextContent.setText(mContent);
            }

            @Override
            public void onNext(Integer integer) {
                mContent += "onNext " + integer + "\n";
                mTextContent.setText(mContent);
            }

            @Override
            public void onError(Throwable e) {
                mContent += "onError" + "\n";
                mTextContent.setText(mContent);
            }

            @Override
            public void onComplete() {
                mContent += "onComplete" + "\n";
                mTextContent.setText(mContent);
            }
        });
    }

    // fromIterable()快速创建操作符，特点是直接发送传入的List数据，可以发送十个以上事件，可用于List集合遍历
    private void onFromIterableClick() {
        mContent = "fromIterable" + "\n";
        List<Integer> lists = new ArrayList<>();
        lists.add(1);
        lists.add(2);
        lists.add(3);
        lists.add(4);
        lists.add(5);
        lists.add(6);
        Observable.fromIterable(lists).subscribe(new Observer<Integer>() {
            @Override
            public void onSubscribe(Disposable d) {
                mContent += "onSubscribe" + "\n";
                mTextContent.setText(mContent);
            }

            @Override
            public void onNext(Integer integer) {
                mContent += "onNext " + integer + "\n";
                mTextContent.setText(mContent);
            }

            @Override
            public void onError(Throwable e) {
                mContent += "onError" + "\n";
                mTextContent.setText(mContent);
            }

            @Override
            public void onComplete() {
                mContent += "onComplete" + "\n";
                mTextContent.setText(mContent);
            }
        });
    }

    // defer()延迟创建操作符
    private void onDeferClick() {
        mContent = "defer" + "\n";

        // 通过defer()定义被观察者对象，此时被观察者对象还未被创建
        Observable<Integer> observable = Observable.defer(new Callable<ObservableSource<? extends Integer>>() {
            @Override
            public ObservableSource<? extends Integer> call() throws Exception {
                return Observable.just(mDefer);
            }
        });

        mDefer += 20;
        // 观察者开始订阅，此时才会调用defer()创建被观察者
        observable.subscribe(new Observer<Integer>() {
            @Override
            public void onSubscribe(Disposable d) {
                mContent += "onSubscribe" + "\n";
                mTextContent.setText(mContent);
            }

            @Override
            public void onNext(Integer integer) {
                mContent += "onNext " + integer + "\n";
                mTextContent.setText(mContent);
            }

            @Override
            public void onError(Throwable e) {
                mContent += "onError" + "\n";
                mTextContent.setText(mContent);
            }

            @Override
            public void onComplete() {
                mContent += "onComplete" + "\n";
                mTextContent.setText(mContent);
            }
        });
    }

    // timer()延迟创建操作符
    private void onTimerClick() {
        mContent = "timer" + "\n";
        // 第一个参数是延迟执行时间
        // 第二个参数是时间单位
        // 第三个参数是任务调度器
        Observable.timer(3, TimeUnit.SECONDS, AndroidSchedulers.mainThread())
                .subscribe(new Observer<Long>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        mContent += "onSubscribe" + "\n";
                        mTextContent.setText(mContent);
                    }

                    @Override
                    public void onNext(Long aLong) {
                        mContent += "onNext " + aLong + "\n";
                        mTextContent.setText(mContent);
                    }

                    @Override
                    public void onError(Throwable e) {
                        mContent += "onError" + "\n";
                        mTextContent.setText(mContent);
                    }

                    @Override
                    public void onComplete() {
                        mContent += "onComplete" + "\n";
                        mTextContent.setText(mContent);
                    }
                });
    }

    // interval()延迟创建操作符
    private void onIntervalClick() {
        mContent = "interval" + "\n";
        // 第一个参数是延迟执行时间
        // 第二个参数是间隔执行时间
        // 第三个参数是时间单位
        // 第四个参数是任务调度器
        Observable.interval(3, 2, TimeUnit.SECONDS, AndroidSchedulers.mainThread())
                .subscribe(new Observer<Long>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        mContent += "onSubscribe" + "\n";
                        mTextContent.setText(mContent);
                    }

                    @Override
                    public void onNext(Long aLong) {
                        mContent += "onNext " + aLong + "\n";
                        mTextContent.setText(mContent);
                    }

                    @Override
                    public void onError(Throwable e) {
                        mContent += "onError" + "\n";
                        mTextContent.setText(mContent);
                    }

                    @Override
                    public void onComplete() {
                        mContent += "onComplete" + "\n";
                        mTextContent.setText(mContent);
                    }
                });
    }

    // intervalRange()延迟创建操作符
    private void onIntervalRangeClick() {
        mContent = "intervalRange" + "\n";
        // 第一个参数事件序列起始点
        // 第二个参数事件数量
        // 第三个参数延迟执行时间
        // 第四个参数间隔执行时间
        // 第五个参数时间单位
        // 第六个参数任务调度器
        Observable.intervalRange(2, 6, 3, 2, TimeUnit.SECONDS, AndroidSchedulers.mainThread())
                .subscribe(new Observer<Long>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        mContent += "onSubscribe" + "\n";
                        mTextContent.setText(mContent);
                    }

                    @Override
                    public void onNext(Long aLong) {
                        mContent += "onNext " + aLong + "\n";
                        mTextContent.setText(mContent);
                    }

                    @Override
                    public void onError(Throwable e) {
                        mContent += "onError" + "\n";
                        mTextContent.setText(mContent);
                    }

                    @Override
                    public void onComplete() {
                        mContent += "onComplete" + "\n";
                        mTextContent.setText(mContent);
                    }
                });
    }

    //range()延迟创建操作符
    private void onRangeClick() {
        mContent = "range" + "\n";
        // 第一个参数事件序列起始点
        // 第二个参数事件数量
        Observable.range(2, 6)
                .subscribe(new Observer<Integer>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        mContent += "onSubscribe" + "\n";
                        mTextContent.setText(mContent);
                    }

                    @Override
                    public void onNext(Integer integer) {
                        mContent += "onNext " + integer + "\n";
                        mTextContent.setText(mContent);
                    }

                    @Override
                    public void onError(Throwable e) {
                        mContent += "onError" + "\n";
                        mTextContent.setText(mContent);
                    }

                    @Override
                    public void onComplete() {
                        mContent += "onComplete" + "\n";
                        mTextContent.setText(mContent);
                    }
                });
    }

    // rangeLong()延迟创建操作符
    private void onRangeLongClick() {
        mContent = "rangeLong" + "\n";
        // 第一个参数事件序列起始点
        // 第二个参数事件数量
        Observable.rangeLong(2, 6).subscribe(new Observer<Long>() {
            @Override
            public void onSubscribe(Disposable d) {
                mContent += "onSubscribe" + "\n";
                mTextContent.setText(mContent);
            }

            @Override
            public void onNext(Long aLong) {
                mContent += "onNext " + aLong + "\n";
                mTextContent.setText(mContent);
            }

            @Override
            public void onError(Throwable e) {
                mContent += "onError" + "\n";
                mTextContent.setText(mContent);
            }

            @Override
            public void onComplete() {
                mContent += "onComplete" + "\n";
                mTextContent.setText(mContent);
            }
        });
    }
}
