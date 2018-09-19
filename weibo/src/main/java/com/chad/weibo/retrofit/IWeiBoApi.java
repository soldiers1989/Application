package com.chad.weibo.retrofit;

import com.chad.weibo.entity.User;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * 提供给Retrofit的新浪微博API
 */
public interface IWeiBoApi {

    @GET("users/show.json")
    Observable<User> getUser(@Query("access_token") String access_token, @Query("uid") long uid);
}
