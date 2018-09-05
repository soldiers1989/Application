package com.chad.zhihu.hepler.retrofit;

import com.chad.zhihu.ZhiHuApplication;
import com.chad.zhihu.entity.zhihu.LatestInfo;
import com.chad.zhihu.hepler.NetworkHelper;
import com.chad.zhihu.util.LogUtil;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Retrofit的具体操作
 */
public class ZhiHuRetrofitHelper {

    private static final String TAG = ZhiHuRetrofitHelper.class.getSimpleName();

    private static final String BASE_URL_ZHIHU_DAILY = "http://news-at.zhihu.com/api/4/";

    private static OkHttpClient okHttpClient = null;
    private static IZhiHuApi iZhiHuApi = null;

    static {
        initOkHttpClient();
        initIZhiHuApi();
    }

    private static void initOkHttpClient() {
        LogUtil.d(TAG, "initOkHttpClient : okHttpClient = " + okHttpClient);
        if (okHttpClient == null) {
            synchronized (ZhiHuRetrofitHelper.class) {
                File cacheDir = ZhiHuApplication.getZhiHuApplication().getCacheDir(); // 缓存文件目录
                File cacheFile = new File(cacheDir, "ZhiHuCache"); // 创建缓存文件
                int cacheMaxSize = 1024 * 1024 *100; // 缓存大小为100M
                Cache cache = new Cache(cacheFile, cacheMaxSize); // 创建缓存文件
                // 将请求体打印出来，控制台可查看
                HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
                httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
                okHttpClient = new OkHttpClient.Builder()
                        .cache(cache) // 配置缓存文件
                        .addInterceptor(httpLoggingInterceptor) // 添加消息拦截器
                        .addNetworkInterceptor(new HttpCacheInterceptor())  // 添加网络拦截器
                        .retryOnConnectionFailure(true) // 连接失败后重试
                        .connectTimeout(10, TimeUnit.SECONDS)   // 连接超时为10秒
                        .build();
            }
        }
    }

    private static void initIZhiHuApi() {
        LogUtil.d(TAG, "initIZhiHuApi");
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL_ZHIHU_DAILY)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        iZhiHuApi = retrofit.create(IZhiHuApi.class);
    }

    /**
     * 获取最新的首页信息
     * @return
     */
    public static Observable<LatestInfo> getLatestInfo() {
        return iZhiHuApi.getLatestInfo();
    }

    /**
     *  自定义OkHttp缓存拦截器
     */
    private static class HttpCacheInterceptor implements Interceptor {

        private static final int MAX_AGE = 60 * 60; // 有网络时缓存最大失效时间为一小时
        private static final int MAX_STALE = 60 * 60 * 24; // 无网络时缓存最大失效时间为一天

        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request();
            if (NetworkHelper.isNetworkConnected()) {
                // 有网络时强制使用网络
                request = request.newBuilder().cacheControl(CacheControl.FORCE_NETWORK).build();
            } else {
                // 无网络时强制使用缓存
                request = request.newBuilder().cacheControl(CacheControl.FORCE_CACHE).build();
            }
            // 将响应头添加至缓存
            Response response = chain.proceed(request);
            if (NetworkHelper.isNetworkConnected()) {
                response = response.newBuilder()
                        .removeHeader("Pragma")
                        .header("Cache-Control", "public, max-age=" + MAX_AGE)
                        .build();
            } else {
                response = response.newBuilder()
                        .removeHeader("Pragma")
                        .header("Cache-Control", "public, only-if-cached, max-stale="
                                + MAX_STALE)
                        .build();
            }
            return response;
        }
    }
}