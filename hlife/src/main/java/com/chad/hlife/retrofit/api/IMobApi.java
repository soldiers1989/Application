package com.chad.hlife.retrofit.api;

import com.chad.hlife.entity.mob.HistoryInfo;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface IMobApi {

    /**
     * 历史上的今天
     *
     * @return
     */
    @GET("appstore/history/query")
    Observable<HistoryInfo> getHistoryInfo(@Query("key") String key, @Query("day") String day);
}
