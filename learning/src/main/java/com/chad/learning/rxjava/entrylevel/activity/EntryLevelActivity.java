package com.chad.learning.rxjava.entrylevel.activity;

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
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class EntryLevelActivity extends BaseAppCompatActivity implements View.OnClickListener {

    @BindView(R.id.btn_step)
    AppCompatButton mBtnStep;
    @BindView(R.id.btn_chain)
    AppCompatButton mBtnChain;
    @BindView(R.id.btn_dispose)
    AppCompatButton mBtnDispose;
    @BindView(R.id.text_content)
    AppCompatTextView mTextContent;

    private String mContent;

    @Override
    public int getLayoutId() {
        return R.layout.activity_rx_java_entry;
    }

    @Override
    public void initViews() {

    }

    @Override
    public void initData() {

    }

    @OnClick({R.id.btn_step, R.id.btn_chain, R.id.btn_dispose})
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_step:
                onStepClick();
                break;
            case R.id.btn_chain:
                onChainClick();
                break;
            case R.id.btn_dispose:
                onDisposeClick();
                break;
            default:
                break;
        }
    }

    // 分步实现
    private void onStepClick() {
        mContent = "Step" + "\n";
        Observable<Integer> observable = Observable.create(new ObservableOnSubscribe() {

            @Override
            public void subscribe(ObservableEmitter emitter) throws Exception {
                emitter.onNext(1);
                emitter.onNext(2);
                emitter.onNext(3);
                emitter.onComplete();
            }
        });

        Observer<Integer> observer = new Observer<Integer>() {

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
        };

        observable.subscribe(observer);
    }

    // 基于事件流的链式调用
    private void onChainClick() {
        mContent = "Chain" + "\n";
        Observable.create(new ObservableOnSubscribe<Integer>() {

            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                emitter.onNext(1);
                emitter.onNext(2);
                emitter.onNext(3);
                emitter.onError(new Throwable());
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

    // 切断观察者与被观察者之间的联系
    private void onDisposeClick() {
        mContent = "Dispose" + "\n";
        Observable.create(new ObservableOnSubscribe<Integer>() {

            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                emitter.onNext(1);
                emitter.onNext(2);
                emitter.onNext(3);
                emitter.onComplete();
            }
        }).subscribe(new Observer<Integer>() {

            Disposable disposable = null;

            @Override
            public void onSubscribe(Disposable d) {
                disposable = d;
                mContent += "onSubscribe" + "\n";
                mTextContent.setText(mContent);
            }

            @Override
            public void onNext(Integer integer) {
                mContent += "onNext " + integer + "\n";
                mTextContent.setText(mContent);
                // 若没有切断被观察这和观察者之间的联系，就切断
                if (integer == 2 && disposable != null && !disposable.isDisposed()) {
                    disposable.dispose();
                }
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
