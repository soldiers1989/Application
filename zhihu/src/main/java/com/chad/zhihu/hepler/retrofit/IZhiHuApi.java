package com.chad.zhihu.hepler.retrofit;

import com.chad.zhihu.entity.LatestDailyInfo;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface IZhiHuApi {

    @GET("stories/latest")
    Observable<LatestDailyInfo> getLatestDailyInfo();
}
