package com.chad.learning.rxjava.demo.function.error.activity;

import android.support.annotation.NonNull;
import android.support.v7.widget.AppCompatTextView;

import com.chad.learning.R;
import com.chad.learning.parent.base.BaseAppCompatActivity;
import com.chad.learning.rxjava.demo.function.error.entity.JSTranslation;
import com.chad.learning.rxjava.demo.function.error.interfaces.IRequest;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * 网络请求出错重连
 */
public class ErrorRetryActivity extends BaseAppCompatActivity {

    @BindView(R.id.text_content)
    AppCompatTextView mTextContent;

    // 可重试次数
    private int maxConnectCount = 10;
    // 当前已重试次数
    private int currentRetryCount = 0;
    // 重试等待时间
    private int waitRetryTime = 0;

    @Override
    public int getLayoutId() {
        return R.layout.activity_rx_java_demo_error;
    }

    @Override
    public void initViews() {

    }

    @Override
    public void initData() {

    }

    @OnClick(R.id.btn_error)
    public void onRetryErrorClick() {
        // 创建Retrofit对象
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://fy.iciba.com/") // // 设置网络请求BaseUrl
                .addConverterFactory(GsonConverterFactory.create()) // 添加Gson数据解析器
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create()) // 设置RxJava网络请求适配器
                .build();
        // 创建网络请求接口的实例
        IRequest iRequest = retrofit.create(IRequest.class);
        // 采用Observable<...>形式对网络请求进行封装
        Observable<JSTranslation> observable = iRequest.get();
        // 通过RetryWhen实现网络出错重连
        observable.retryWhen(new Function<Observable<Throwable>, ObservableSource<?>>() {
            @Override
            public ObservableSource<?> apply(@NonNull Observable<Throwable> throwableObservable) throws Exception {
                return throwableObservable.flatMap(new Function<Throwable, ObservableSource<?>>() {
                    @Override
                    public ObservableSource<?> apply(Throwable throwable) throws Exception {
                        if (throwable instanceof IOException) {
                            if (currentRetryCount < maxConnectCount) {
                                currentRetryCount++;
                                waitRetryTime = 1 + currentRetryCount * 1;
                                return Observable.just(1).delay(waitRetryTime, TimeUnit.SECONDS);
                            } else {
                                return Observable.error(new Throwable("已经重复的次数" + currentRetryCount));
                            }
                        } else {
                            return Observable.error(new Throwable("发生了非IO异常"));
                        }
                    }
                });
            }
        }).observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<JSTranslation>() {

                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(JSTranslation jsTranslation) {
                        int status = jsTranslation.getStatus();
                        JSTranslation.Content content = jsTranslation.getContent();

                        mTextContent.setText("onNext = " + status + " , " + content.getFrom()
                                + " , " + content.getTo() + " , " + content.getVendor() + " , "
                                + content.getOut() + " , " + content.getErr_no() + "\n");
                    }

                    @Override
                    public void onError(Throwable e) {
                        mTextContent.setText("onError : " + e.toString());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
