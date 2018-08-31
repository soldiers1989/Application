package com.chad.learning.rxjava.operator.function.activity;

import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;

import com.chad.learning.R;
import com.chad.learning.parent.base.BaseAppCompatActivity;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;

public class RepeatActivity extends BaseAppCompatActivity implements View.OnClickListener {

    @BindView(R.id.btn_repeat)
    AppCompatButton mBtnRepeat;
    @BindView(R.id.btn_repeat_when)
    AppCompatButton nBtnRepeatWhen;
    @BindView(R.id.text_content)
    AppCompatTextView mTextContent;

    private String mContent;

    @Override
    public int getLayoutId() {
        return R.layout.activity_rx_java_operator_function_repeat;
    }

    @Override
    public void initViews() {

    }

    @Override
    public void initData() {

    }

    @OnClick({R.id.btn_repeat, R.id.btn_repeat_when})
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_repeat:
                onRepeatClick();
                break;
            case R.id.btn_repeat_when:
                onRepeatWhenClick();
                break;
            default:
                break;
        }
    }

    // repeat()重复发送操作符，无条件的、重复的发送被观察者事件
    private void onRepeatClick() {
        mContent = "repeat()" + "\n";
        Observable.just(1, 2).repeat(3) // 参数是重复发送被观察者事件的次数
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

    // repeatWhen()重复发送操作符，有条件的、重复发送被观察者事件
    private void onRepeatWhenClick() {
        Observable.just(1, 2, 4).repeatWhen(new Function<Observable<Object>, ObservableSource<?>>() {
            @Override
            // 在Function函数中，必须对输入的 Observable<Object>进行处理，
            // 这里我们使用的是flatMap操作符接收上游的数据
            public ObservableSource<?> apply(@NonNull Observable<Object> objectObservable) throws Exception {
                // 将原始 Observable 停止发送事件的标识（Complete（） /  Error（））转换成1个 Object
                // 类型数据传递给1个新被观察者（Observable）,以此决定是否重新订阅 & 发送原来的 Observable
                // 此处有2种情况：
                // 1. 若新被观察者（Observable）返回1个Complete（） /  Error（）事件，则不重新订阅 &
                //    发送原来的 Observable
                // 2. 若新被观察者（Observable）返回其余事件，则重新订阅 & 发送原来的 Observable
                return objectObservable.flatMap(new Function<Object, ObservableSource<?>>() {
                    @Override
                    public ObservableSource<?> apply(@NonNull Object throwable) throws Exception {
                        mContent += "apply" + "\n";
                        mTextContent.setText(mContent);
                        // 情况1：若新被观察者（Observable）返回1个Complete（） /  Error（）事件，
                        // 则不重新订阅 & 发送原来的 Observable
                        return Observable.empty();
                        // Observable.empty() = 发送Complete事件，但不会回调观察者的onComplete（）

                        // return Observable.error(new Throwable("不再重新订阅事件"));
                        // 返回Error事件 = 回调onError（）事件，并接收传过去的错误信息。

                        // 情况2：若新被观察者（Observable）返回其余事件，则重新订阅 & 发送原来的 Observable
                        // return Observable.just(1);
                        // 仅仅是作为1个触发重新订阅被观察者的通知，发送的是什么数据并不重要，
                        // 只要不是Complete（） /  Error（）事件
                    }
                });

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
}
