package com.chad.learning.realm.application;

import android.app.Application;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class RealmApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        initRealm();
    }

    /**
     * 初始化Realm
     */
    private void initRealm() {
        Realm.init(this);
        RealmConfiguration realmConfiguration = new RealmConfiguration.Builder().build();
        Realm.setDefaultConfiguration(realmConfiguration); // 设置默认配置
    }
}
