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
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Predicate;
import io.reactivex.schedulers.Schedulers;

/**
 * 根据指定条件过滤事件
 */
public class ConditionsActivity extends BaseAppCompatActivity {

    @BindView(R.id.text_content)
    AppCompatTextView mTextContent;

    private String mContent;

    @Override
    public int getLayoutId() {
        return R.layout.activity_rx_java_operator_filter_conditions;
    }

    @Override
    public void initViews() {

    }

    @Override
    public void initData() {

    }

    // filter()过滤操作符，过滤特定条件的事件
    @OnClick(R.id.btn_filter)
    public void onFilterClick() {
        mContent = "filter \n";
        Observable.create(new ObservableOnSubscribe<Integer>() {

            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                emitter.onNext(1);
                emitter.onNext(2);
                emitter.onNext(3);
                emitter.onNext(4);
                emitter.onNext(5);
            }
        }).filter(new Predicate<Integer>() {

            @Override
            public boolean test(Integer integer) throws Exception {
                return integer >= 3;
            }
        }).subscribe(new Observer<Integer>() {

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

    // ofType()过滤操作符，过滤特定数据类型的数据
    @OnClick(R.id.btn_of_type)
    public void onOfTypeClick() {
        mContent = "ofType \n";
        Observable.just("4444", 6, "hello", 8)
                .ofType(Integer.class)
                .subscribe(new Consumer<Integer>() {

                    @Override
                    public void accept(Integer integer) throws Exception {
                        mContent += "accept = " + integer + "\n";
                        mTextContent.setText(mContent);
                    }
                });
    }

    // skip()过滤操作符，跳过某个事件，从左至右
    @OnClick(R.id.btn_skip)
    public void onSkipClick() {
        mContent = "skip \n";
        Observable.just(1, 2, 3, 4, 5)
                .skip(1) // 跳过正序的第一个事件
                .skipLast(1) // 跳过反序的第一个事件
                .subscribe(new Consumer<Integer>() {

                    @Override
                    public void accept(Integer integer) throws Exception {
                        mContent += "accept = " + integer + "\n";
                        mTextContent.setText(mContent);
                    }
                });
    }

    // skipLast()过滤操作符，跳过某个事件，从右至左
    @OnClick(R.id.btn_skip_last)
    public void onSkipLastClick() {
        mContent = "skipLast \n";
        // 发送0 - 5事件，延迟0秒，一秒钟发送一次
        Observable.intervalRange(0, 5, 0, 1, TimeUnit.SECONDS)
                .skip(1, TimeUnit.SECONDS) // 跳过第1秒发送的事件
                .skipLast(1, TimeUnit.SECONDS) // 跳过最后一秒发送的数据
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Consumer<Long>() {

                    @Override
                    public void accept(Long aLong) throws Exception {
                        mContent += "accept = " + aLong + "\n";
                        mTextContent.setText(mContent);
                    }
                });
    }

    // distinct()过滤操作符，过滤事件中重复的事件
    @OnClick(R.id.btn_distinct)
    public void onDistinctClick() {
        mContent = "distinct \n";
        Observable.just(1, 2, 3, 4, 5, 1)
                .distinct()
                .subscribe(new Consumer<Integer>() {

                    @Override
                    public void accept(Integer integer) throws Exception {
                        mContent += "accept = " + integer + "\n";
                        mTextContent.setText(mContent);
                    }
                });
    }

    // distinctUntilChanged()过滤操作符，过滤事件中连续重复的事件
    @OnClick(R.id.btn_distinct_until_changed)
    public void onDistinctUntilChanged() {
        mContent = "distinctUntilChanged \n";
        Observable.just(1, 1, 2, 4, 2, 3, 3)
                .distinctUntilChanged()
                .subscribe(new Consumer<Integer>() {

                    @Override
                    public void accept(Integer integer) throws Exception {
                        mContent += "accept = " + integer + "\n";
                        mTextContent.setText(mContent);
                    }
                });
    }
}
