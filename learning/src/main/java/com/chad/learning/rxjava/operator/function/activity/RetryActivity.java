package com.chad.learning.rxjava.operator.function.activity;

import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;

import com.chad.learning.R;
import com.chad.learning.parent.base.BaseAppCompatActivity;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.BiPredicate;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;

public class RetryActivity extends BaseAppCompatActivity implements View.OnClickListener {

    @BindView(R.id.btn_retry)
    AppCompatButton mBtnRetry;
    @BindView(R.id.btn_retry_long)
    AppCompatButton mBtnRetryLong;
    @BindView(R.id.btn_retry_predicate)
    AppCompatButton mBtnRetryPredicate;
    @BindView(R.id.btn_retry_bi_predicate)
    AppCompatButton mBtnRetryBiPredicate;
    @BindView(R.id.btn_retry_long_predicate)
    AppCompatButton mBtnRetryLongPredicate;
    @BindView(R.id.btn_retry_when)
    AppCompatButton mBtnRetryWhen;
    @BindView(R.id.text_content)
    AppCompatTextView mTextContent;

    private String mContent;

    @Override
    public int getLayoutId() {
        return R.layout.activity_rx_java_operator_function_retry;
    }

    @Override
    public void initViews() {

    }

    @Override
    public void initData() {

    }

    @OnClick({R.id.btn_retry, R.id.btn_retry_long, R.id.btn_retry_predicate,
            R.id.btn_retry_bi_predicate, R.id.btn_retry_long_predicate, R.id.btn_retry_when})
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_retry:
                onRetryClick();
                break;
            case R.id.btn_retry_long:
                onRetryLongClick();
                break;
            case R.id.btn_retry_predicate:
                onRetryPredicateClick();
                break;
            case R.id.btn_retry_bi_predicate:
                onRetryBiPredicateClick();
                break;
            case R.id.btn_retry_long_predicate:
                onRetryLongPredicateClick();
                break;
            case R.id.btn_retry_when:
                onRetryWhenClick();
                break;
            default:
                break;
        }
    }

    // retry()错误处理操作符，出现错误时，让被观察者重新发送数据，若一直错误，则一直发送
    private void onRetryClick() {
        mContent = "retry()" + "\n";
        Observable.create(new ObservableOnSubscribe<Integer>() {

            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                emitter.onNext(1);
                emitter.onError(new Throwable("Error"));
                emitter.onNext(2);
            }
        }).retry()  // 重试，若一直出现错误，Observable就一直发送数据
                .subscribe(new Observer<Integer>() {
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

    // retry(long)错误处理操作符，出现错误时，让被观察者重新发送数据，具备重试次数限制
    private void onRetryLongClick() {
        mContent = "retry(long)" + "\n";
        Observable.create(new ObservableOnSubscribe<Integer>() {

            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                emitter.onNext(1);
                emitter.onError(new Throwable("Error"));
                emitter.onNext(2);
            }
        }).retry(5) // 参数为重复发送数据的次数
                .subscribe(new Observer<Integer>() {
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

    // retry(Predicate)错误处理操作符，出现错误后判断是否需要重新发送数据
    private void onRetryPredicateClick() {
        mContent = "retry(Predicate)" + "\n";
        Observable.create(new ObservableOnSubscribe<Integer>() {

            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                emitter.onNext(1);
                emitter.onError(new Throwable("Error"));
                emitter.onNext(2);
            }
        }).retry(new Predicate<Throwable>() { // 拦截错误后，判断是否需要重新发送请求
            @Override
            public boolean test(Throwable throwable) throws Exception {
                mContent += "test" + "\n";
                mTextContent.setText(mContent);
                // return true 重新发送数据（若持续遇到错误，就持续重新发送）
                // return false 不重新发送数据，调用观察者的onError()
                return true;
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

    // retry(BiPredicate)错误处理操作符，出现错误后，判断是否需要重新发送数据
    private void onRetryBiPredicateClick() {
        mContent = "retry(BiPredicate)" + "\n";
        Observable.create(new ObservableOnSubscribe<Integer>() {

            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                emitter.onNext(1);
                emitter.onError(new Throwable("Error"));
                emitter.onNext(2);
            }
        }).retry(new BiPredicate<Integer, Throwable>() { // 参数Integer是重复发送的次数
            @Override
            public boolean test(Integer integer, Throwable throwable) throws Exception {
                mContent += "test" + integer + "\n";
                mTextContent.setText(mContent);
                // return true 重新发送数据（若持续遇到错误，就持续重新发送）
                // return false 不重新发送数据，调用观察者的onError()
                return true;
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

    //  retry(long, Predicate)错误处理操作符，出现错误后，判断是否需要重新发送数据，具备重试次数限制
    private void onRetryLongPredicateClick() {
        Observable.create(new ObservableOnSubscribe<Integer>() {

            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                emitter.onNext(1);
                emitter.onError(new Throwable("Error"));
                emitter.onNext(2);
            }
        }).retry(5, new Predicate<Throwable>() { // 参数一是重复发送数据次数，参数二是判断逻辑
            @Override
            public boolean test(Throwable throwable) throws Exception {
                mContent += "test" + "\n";
                mTextContent.setText(mContent);
                return true;
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

    // retryWhen()错误处理操作符，遇到错误时，将发生的错误传递给一个新的被观察者，并决定是否需要重新
    // 订阅原始被观察者
    private void onRetryWhenClick() {
        Observable.create(new ObservableOnSubscribe<Integer>() {

            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                emitter.onNext(1);
                emitter.onError(new Throwable("Error"));
                emitter.onNext(2);
            }
        }).retryWhen(new Function<Observable<Throwable>, ObservableSource<?>>() {
            // 参数Observable<Throwable>中的泛型 = 上游操作符抛出的异常，可通过该条件来判断异常的类型
            @Override
            public ObservableSource<?> apply(Observable<Throwable> throwableObservable) throws Exception {
                // 返回Observable<?> = 新的被观察者 Observable（任意类型）
                return throwableObservable.flatMap(new Function<Throwable, ObservableSource<?>>() {
                    @Override
                    public ObservableSource<?> apply(Throwable throwable) throws Exception {
                        // 若返回的Observable发送的事件 = Error事件，则原始的Observable不重新发送事件
                        // 该异常错误信息可在观察者中的onError（）中获得
                        return Observable.error(new Throwable("retryWhen Over"));
                        // 若返回的Observable发送的事件 = Next事件，
                        // 则原始的Observable重新发送事件（若持续遇到错误，则持续重试）
                        // return Observable.just(3);
                    }
                });
            }
        }).subscribe(new Observer<Integer>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Integer integer) {

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
