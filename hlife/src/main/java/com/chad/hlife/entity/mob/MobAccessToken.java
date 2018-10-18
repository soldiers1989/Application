package com.chad.hlife.entity.mob;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * 用户Token
 */
public class MobAccessToken implements Parcelable {

    private String token;
    private String uid;

    public MobAccessToken() {}

    public MobAccessToken(String token, String uid) {
        this.token = token;
        this.uid = uid;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getUid() {
        return uid;
    }

    protected MobAccessToken(Parcel in) {
        token = in.readString();
        uid = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(token);
        parcel.writeString(uid);
    }

    public static final Creator<MobAccessToken> CREATOR = new Creator<MobAccessToken>() {
        @Override
        public MobAccessToken createFromParcel(Parcel parcel) {
            return new MobAccessToken(parcel);
        }

        @Override
        public MobAccessToken[] newArray(int size) {
            return new MobAccessToken[size];
        }
    };
}
