package com.chad.hlife.entity.mob;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * 用户登录
 */
public class UserLoginInfo implements Parcelable {

    private String msg;
    private String retCode;
    private MobAccessToken result;

    public String getMsg() {
        return msg;
    }

    public String getRetCode() {
        return retCode;
    }

    public MobAccessToken getMobAccessToken() {
        return result;
    }

    protected UserLoginInfo(Parcel in) {
        msg = in.readString();
        retCode = in.readString();
        result = (MobAccessToken) in.readValue(MobAccessToken.class.getClassLoader());
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(msg);
        parcel.writeString(retCode);
        parcel.writeValue(result);
    }

    public static final Creator<UserLoginInfo> CREATOR = new Creator<UserLoginInfo>() {
        @Override
        public UserLoginInfo createFromParcel(Parcel parcel) {
            return new UserLoginInfo(parcel);
        }

        @Override
        public UserLoginInfo[] newArray(int size) {
            return new UserLoginInfo[size];
        }
    };
}
