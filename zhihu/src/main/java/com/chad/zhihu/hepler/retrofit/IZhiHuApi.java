package com.chad.zhihu.hepler.retrofit;

import com.chad.zhihu.entity.zhihu.HomeInfo;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * 提供给Retrofit的网络请求接口
 */
public interface IZhiHuApi {

    /**
     * 获取最新的日报信息
     * @return
     */
    @GET("stories/latest")
    Observable<HomeInfo> getLatestHomeInfo();

    /**
     * 根据日期获取对应的日报信息
     * @return
     */
    @GET("stories/before/{date}")
    Observable<HomeInfo> getMoreHomeInfo(@Path("date")String date);
}
