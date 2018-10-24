package com.chad.hlife.entity.mob;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * 用户密码修改
 */
public class UserPasswordInfo implements Parcelable {

    private String msg;
    private String retCode;

    public String getMsg() {
        return msg;
    }

    public String getRetCode() {
        return retCode;
    }

    protected UserPasswordInfo(Parcel in) {
        msg = in.readString();
        retCode = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(msg);
        parcel.writeString(retCode);
    }

    public static final Creator<UserPasswordInfo> CREATOR = new Creator<UserPasswordInfo>() {
        @Override
        public UserPasswordInfo createFromParcel(Parcel parcel) {
            return new UserPasswordInfo(parcel);
        }

        @Override
        public UserPasswordInfo[] newArray(int size) {
            return new UserPasswordInfo[size];
        }
    };
}
