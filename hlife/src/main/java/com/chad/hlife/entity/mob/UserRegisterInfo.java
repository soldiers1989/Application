package com.chad.hlife.entity.mob;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * 用户注册
 */
public class UserRegisterInfo implements Parcelable {

    private String msg;
    private String retCode;
    private String uid;

    public String getMsg() {
        return msg;
    }

    public String getRetCode() {
        return retCode;
    }

    public String getUid() {
        return uid;
    }

    protected UserRegisterInfo(Parcel in) {
        msg = in.readString();
        retCode = in.readString();
        uid = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(msg);
        parcel.writeString(retCode);
        parcel.writeString(uid);
    }

    public static final Creator<UserRegisterInfo> CREATOR = new Creator<UserRegisterInfo>() {
        @Override
        public UserRegisterInfo createFromParcel(Parcel parcel) {
            return new UserRegisterInfo(parcel);
        }

        @Override
        public UserRegisterInfo[] newArray(int size) {
            return new UserRegisterInfo[size];
        }
    };
}
