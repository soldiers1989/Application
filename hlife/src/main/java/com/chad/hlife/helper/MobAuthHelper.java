package com.chad.hlife.helper;

import android.content.Context;

import com.chad.hlife.app.AppSettings;
import com.chad.hlife.entity.mob.MobAccessToken;

public class MobAuthHelper {

    private static volatile MobAuthHelper mMobAuthHelper = null;

    public static MobAuthHelper getInstance(Context context) {
        synchronized (MobAuthHelper.class) {
            if (mMobAuthHelper == null) {
                mMobAuthHelper = new MobAuthHelper(context);
            }
        }
        return mMobAuthHelper;
    }

    private MobAuthHelper(Context context) {
    }

    public void writeAccessToken(MobAccessToken mobAccessToken) {
        if (mobAccessToken == null) {
            return;
        }
        AppSettings.getInstance().putToken(mobAccessToken.getToken());
        AppSettings.getInstance().putUid(mobAccessToken.getUid());
    }

    public MobAccessToken readAccessToken() {
        String token = AppSettings.getInstance().getToken();
        String uid = AppSettings.getInstance().getUid();
        return new MobAccessToken(token, uid);
    }

    public void clearAccessToken() {
        AppSettings.getInstance().putToken(null);
        AppSettings.getInstance().putUid(null);
    }
}
