package com.chad.weibo.retrofit;

import com.chad.weibo.entity.RateLimitStatus;
import com.chad.weibo.entity.TimeLine;
import com.chad.weibo.entity.User;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * 提供给Retrofit的新浪微博API
 */
public interface IWeiBoApi {

    @GET("account/rate_limit_status.json")
    Observable<RateLimitStatus> getRateLimitStatus(@Query("access_token") String access_token);

    @GET("users/show.json")
    Observable<User> getUser(@Query("access_token") String access_token,
                             @Query("uid") long uid);

    @GET("statuses/home_timeline.json")
    Observable<TimeLine> getHomeTimeLine(@Query("access_token") String access_token,
                                         @Query("count") int count,
                                         @Query("page") int page,
                                         @Query("feature") int feature);

    @GET("statuses/user_timeline.json")
    Observable<TimeLine> getUserTimeLine(@Query("access_token") String access_token,
                                         @Query("uid") long uid,
                                         @Query("count") int count,
                                         @Query("page") int page,
                                         @Query("feature") int feature);
}
