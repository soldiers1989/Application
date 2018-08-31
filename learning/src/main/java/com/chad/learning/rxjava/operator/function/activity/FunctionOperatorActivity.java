package com.chad.learning.rxjava.operator.function.activity;

import android.content.Intent;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;

import com.chad.learning.R;
import com.chad.learning.parent.base.BaseAppCompatActivity;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.Notification;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;

/**
 * 功能性操作符
 */
public class FunctionOperatorActivity extends BaseAppCompatActivity implements View.OnClickListener {

    @BindView(R.id.btn_subscribe)
    AppCompatButton mBtnSubscribe;
    @BindView(R.id.btn_delay)
    AppCompatButton mBtnDelay;
    @BindView(R.id.btn_do)
    AppCompatButton mBtnDo;
    @BindView(R.id.btn_error_return)
    AppCompatButton mBtnErrorReturn;
    @BindView(R.id.btn_error_resume_next)
    AppCompatButton mBtnErrorResumeNext;
    @BindView(R.id.btn_exception_resume_next)
    AppCompatButton mBtnExceptionResumeNext;
    @BindView(R.id.btn_retry)
    AppCompatButton mBtnRetry;
    @BindView(R.id.btn_repeat)
    AppCompatButton mBtnRepeat;
    @BindView(R.id.text_content)
    AppCompatTextView mTextContent;

    String mContent;

    @Override
    public int getLayoutId() {
        return R.layout.activity_rx_java_operator_function;
    }

    @Override
    public void initViews() {

    }

    @Override
    public void initData() {

    }

    @OnClick({R.id.btn_subscribe, R.id.btn_delay, R.id.btn_do, R.id.btn_error_return
            , R.id.btn_error_resume_next, R.id.btn_exception_resume_next, R.id.btn_retry
            , R.id.btn_repeat})
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_subscribe:
                onSubscribeClick();
                break;
            case R.id.btn_delay:
                onDelayClick();
                break;
            case R.id.btn_do:
                onDoClick();
                break;
            case R.id.btn_error_return:
                onErrorReturnClick();
                break;
            case R.id.btn_error_resume_next:
                onErrorResumeNextClick();
                break;
            case R.id.btn_exception_resume_next:
                onExceptionResumeNextClick();
                break;
            case R.id.btn_retry:
                onRetryClick();
                break;
            case R.id.btn_repeat:
                onRepeatClick();
                break;
            default:
                break;
        }
    }

    // subscribe()订阅操作符，连接被观察者和观察者
    private void onSubscribeClick() {
        mContent = "subscribe" + "\n";
        Observable.create(new ObservableOnSubscribe<Integer>() {

            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                emitter.onNext(1);
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

    // delay()延迟操作符，使得被观察者延迟一段时间再发送事件，具有多个重载方法
    private void onDelayClick() {
        mContent = "delay" + "\n";
        // 第一个参数是延迟执行时间
        // 第二个参数是时间单位
        // 第三个参数是任务调度器
        // 第四个参数是错误通知是否延迟
        Observable.just(1).delay(2, TimeUnit.SECONDS, AndroidSchedulers.mainThread(), false)
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

    // do()操作符，在事件的生命周期中操作
    private void onDoClick() {
        mContent = "do" + "\n";
        Observable.just(1)
                // 观察者订阅时调用
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        mContent += "doOnSubscribe" + "\n";
                        mTextContent.setText(mContent);
                    }
                })
                // Observable每次发送事件都会调用一次
                .doOnEach(new Consumer<Notification<Integer>>() {
                    @Override
                    public void accept(Notification<Integer> integerNotification) throws Exception {
                        mContent += "doOnEach" + "\n";
                        mTextContent.setText(mContent);
                    }
                })
                // 执行onNext()事件之前调用
                .doOnNext(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        mContent += "doOnNext" + "\n";
                        mTextContent.setText(mContent);
                    }
                })
                // 执行onNext()事件之后调用
                .doAfterNext(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        mContent += "doAfterNext" + "\n";
                        mTextContent.setText(mContent);
                    }
                })
                // 执行onError()事件之前调用
                .doOnError(new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        mContent += "doOnError" + "\n";
                        mTextContent.setText(mContent);
                    }
                })
                // 执行onComplete()事件之前调用
                .doOnComplete(new Action() {
                    @Override
                    public void run() throws Exception {
                        mContent += "doOnComplete" + "\n";
                        mTextContent.setText(mContent);
                    }
                })
                // Observable无论正常发送完毕/异常终止之前调用
                // （doOnError()、doOnComplete()、doFinally()之前）
                .doOnTerminate(new Action() {
                    @Override
                    public void run() throws Exception {
                        mContent += "doOnTerminate" + "\n";
                        mTextContent.setText(mContent);
                    }
                })
                // Observable无论正常发送完毕/异常终止之后调用
                // （doOnError()、doOnComplete()、doFinally()之后）
                .doAfterTerminate(new Action() {
                    @Override
                    public void run() throws Exception {
                        mContent += "doAfterTerminate" + "\n";
                        mTextContent.setText(mContent);
                    }
                })
                // 完成时调用（doOnError()、doOnComplete()之后）
                .doFinally(new Action() {
                    @Override
                    public void run() throws Exception {
                        mContent += "doFinally" + "\n";
                        mTextContent.setText(mContent);
                    }
                })
                .subscribe(new Observer<Integer>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        mContent += "onSubscribe" + "\n";
                        mTextContent.setText(mContent);
                    }

                    @Override
                    public void onNext(Integer integer) {
                        mContent += "onNext" + "\n";
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

    // onErrorReturn()错误处理操作符，遇到错误时，发送一个特殊事件并正常终止
    private void onErrorReturnClick() {
        mContent = "onErrorReturn" + "\n";
        Observable.create(new ObservableOnSubscribe<Integer>() {

            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                emitter.onNext(1);
                emitter.onError(new Throwable("Error"));
            }
        }).onErrorReturn(new Function<Throwable, Integer>() {
            @Override
            public Integer apply(Throwable throwable) throws Exception {
                // 可再此处捕获异常
                mContent += "apply" + throwable.getMessage() + "\n";
                mTextContent.setText(mContent);
                // 发送一个特殊事件
                return 666;
            }
        }).subscribe(new Observer<Integer>() {
            @Override
            public void onSubscribe(Disposable d) {
                mContent += "onSubscribe" + "\n";
                mTextContent.setText(mContent);
            }

            @Override
            public void onNext(Integer integer) {
                mContent += "onNext" + integer + "\n";
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

    // onErrorResumeNext()错误处理操作符，遇到错误时，发送一个新的Observable
    // onErrorResumeNext()拦截的错误 = Throwable；若需拦截Exception请用onExceptionResumeNext()
    // 若onErrorResumeNext()拦截的错误 = Exception，则会将错误传递给观察者的onError方法
    private void onErrorResumeNextClick() {
        mContent = "onErrorResumeNext" + "\n";
        Observable.create(new ObservableOnSubscribe<Integer>() {

            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                emitter.onNext(1);
                emitter.onError(new Throwable("Error"));
            }
        }).onErrorResumeNext(new Function<Throwable, ObservableSource<? extends Integer>>() {
            @Override
            public ObservableSource<? extends Integer> apply(Throwable throwable) throws Exception {
                mContent += "apply" + throwable.getMessage() + "\n";
                return Observable.just(666);
            }
        }).subscribe(new Observer<Integer>() {
            @Override
            public void onSubscribe(Disposable d) {
                mContent += "onSubscribe" + "\n";
                mTextContent.setText(mContent);
            }

            @Override
            public void onNext(Integer integer) {
                mContent += "onNext" + integer + "\n";
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

    // onExceptionResumeNext()错误处理操作符，遇到错误时，发送一个新的Observable
    // onExceptionResumeNext()拦截的错误 = Exception；若需拦截Throwable请用onErrorResumeNext()
    // onExceptionResumeNext()拦截的错误 = Throwable，则会将错误传递给观察者的onError方法
    private void onExceptionResumeNextClick() {
        mContent = "onExceptionResumeNext" + "\n";
        Observable.create(new ObservableOnSubscribe<Integer>() {

            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                emitter.onNext(1);
                emitter.onError(new Exception("Error"));
            }
        }).onExceptionResumeNext(new ObservableSource<Integer>() {
            @Override
            public void subscribe(Observer<? super Integer> observer) {
                mContent += "subscribe" + "\n";
                mTextContent.setText(mContent);
                observer.onNext(666);
                observer.onComplete();
            }
        }).subscribe(new Observer<Integer>() {
            @Override
            public void onSubscribe(Disposable d) {
                mContent += "onSubscribe" + "\n";
                mTextContent.setText(mContent);
            }

            @Override
            public void onNext(Integer integer) {
                mContent += "onNext" + integer + "\n";
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

    // retry()处理错误操作符，重试作用，当出现错误时，让Observable重新发送数据
    private void onRetryClick() {
        Intent intent = new Intent(FunctionOperatorActivity.this, RetryActivity.class);
        startActivity(intent);
    }

    // repeat()重复发送操作符，重复不断地发送被观察者事件
    private void onRepeatClick() {
        Intent intent = new Intent(FunctionOperatorActivity.this, RepeatActivity.class);
        startActivity(intent);
    }
}
