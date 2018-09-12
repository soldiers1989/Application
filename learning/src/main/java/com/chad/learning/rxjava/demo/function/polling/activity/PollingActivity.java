package com.chad.learning.rxjava.demo.function.polling.activity;

import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;

import com.chad.learning.R;
import com.chad.learning.parent.base.BaseAppCompatActivity;

import com.chad.learning.rxjava.demo.function.polling.entity.JSTranslation;
import com.chad.learning.rxjava.demo.function.polling.interfaces.IRequest;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * 网络轮询
 */
public class PollingActivity extends BaseAppCompatActivity implements View.OnClickListener {

    @BindView(R.id.btn_polling)
    AppCompatButton mBtnPolling;
    @BindView(R.id.text_content)
    AppCompatTextView mTextContent;

    @Override
    public int getLayoutId() {
        return R.layout.activity_rx_java_demo_polling;
    }

    @Override
    public void initViews() {

    }

    @Override
    public void initData() {

    }

    @OnClick({R.id.btn_polling})
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_polling:
                onPollingClick();
                break;
            default:
                break;
        }
    }

    private void onPollingClick() {
        // 每次发送数字前，就发送1次网络请求，doOnNext()在执行onNext()之前调用，从而实现轮询需求
        Observable.intervalRange(1, 10, 2, 2, TimeUnit.SECONDS)
                .doOnNext(new Consumer<Long>() {
                    @Override
                    public void accept(Long aLong) throws Exception {
                        request(aLong);
                    }
                }).subscribe(new Observer<Long>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Long aLong) {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });
    }

    private void request(Long count) {
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
        // 通过线程切换发送网络请求
        observable.observeOn(AndroidSchedulers.mainThread()) // 切换到IO线程进行网络请求
                .subscribeOn(Schedulers.io()) // 切换回到主线程处理请求结果
                .subscribe(new Observer<JSTranslation>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(JSTranslation jsTranslation) {
                        int status = jsTranslation.getStatus();
                        JSTranslation.Content content = jsTranslation.getContent();

                        mTextContent.setText(count + ", Result = " + status + " , " + content.getFrom()
                                + " , " + content.getTo() + " , " + content.getVendor() + " , "
                                + content.getOut() + " , " + content.getErr_no() + "\n");
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
