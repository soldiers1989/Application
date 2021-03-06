package com.chad.hlife.retrofit.api;

import com.chad.hlife.entity.weibo.WeiBoUserInfo;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface IWeiBoApi {

    /**
     * 获取微博用户信息
     * @param access_token
     * @param uid
     * @return
     */
    @GET("users/show.json")
    Observable<WeiBoUserInfo> getUserInfo(@Query("access_token") String access_token, @Query("uid") long uid);
}
