package com.chad.learning.rxjava.network.error.interfaces;

import com.chad.learning.rxjava.network.polling.entity.JSTranslation;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface IRequest {
    // 采用注解加Observable<...>接口描述网络请求参数
    @GET("ajax.php?a=fy&f=auto&t=auto&w=Hello")
    Observable<JSTranslation> get();
}
