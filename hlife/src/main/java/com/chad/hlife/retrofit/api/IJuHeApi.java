package com.chad.hlife.retrofit.api;

import com.chad.hlife.entity.juhe.NewsInfo;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface IJuHeApi {

    /**
     * 新闻头条
     * @param type
     * @param key
     * @return
     */
    @GET("toutiao/index")
    Observable<NewsInfo> getNewsInfo(@Query("type") String type, @Query("key") String key);
}
