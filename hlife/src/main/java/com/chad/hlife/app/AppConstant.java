package com.chad.hlife.app;

import com.chad.hlife.HLifeApplication;

import java.io.File;

public class AppConstant {

    public static final File FILE_DIR_CACHE = HLifeApplication.getHLifeApplication().getCacheDir();

    public static final int MODEL_LOGIN_WEIBO = 0;
    public static final int MODEL_LOGIN_WECHAT = 1;

    public static final int REQUEST_CODE_GPS_SETTINGS = 0;
    public static final int REQUEST_CODE_LOCATION_PERMISSION = 1;

    public static final int TYPE_WIFI_BAIDU = 1;
    public static final int TYPE_WIFI_GOOGLE = 2;
    public static final int TYPE_WIFI_GPS = 3;

    public static final String EXTRA_ID = "id";
    public static final String EXTRA_TITLE = "title";
    public static final String EXTRA_URL = "url";

    public static final String URL_BASE_WEIBO = "https://api.weibo.com/2/";
    public static final String URL_BASE_JUHE_V = "http://v.juhe.cn/";
    public static final String URL_BASE_JUHE_API = "http://apis.juhe.cn/";
    public static final String URL_BASE_ZHIHU = "http://news-at.zhihu.com/api/4/";

    public static final String TYPE_NEWS_TOP = "top";
    public static final String TYPE_NEWS_SHEHUI = "shehui";
    public static final String TYPE_NEWS_GUONEI = "guonei";
    public static final String TYPE_NEWS_GUOJI = "guoji";
    public static final String TYPE_NEWS_YULE = "yule";
    public static final String TYPE_NEWS_TIYU = "tiyu";
    public static final String TYPE_NEWS_JUNSHI = "junshi";
    public static final String TYPE_NEWS_KEJI = "keji";
    public static final String TYPE_NEWS_CAIJING = "caijing";
    public static final String TYPE_NEWS_SHISHANG = "shishang";

    public static final String SORT_JOKE_DESC = "desc";
    public static final String SORT_JOKE_ASC = "asc";
}
