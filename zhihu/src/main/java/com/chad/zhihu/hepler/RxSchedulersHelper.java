package com.chad.zhihu.hepler;

import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * RxJava的IO线程和AndroidMainThread切换
 */
public class RxSchedulersHelper {

    public static <T> ObservableTransformer<T, T> bindToMainThread() {
        return upstream -> upstream.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }
}
