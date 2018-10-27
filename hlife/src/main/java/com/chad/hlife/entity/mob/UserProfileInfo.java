package com.chad.hlife.entity.mob;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * 用户资料
 */
public class UserProfileInfo implements Parcelable {

    private String msg;
    private String retCode;
    private String result;

    public String getMsg() {
        return msg;
    }

    public String getRetCode() {
        return retCode;
    }

    public String getResult() {
        return result;
    }

    protected UserProfileInfo(Parcel in) {
        msg = in.readString();
        retCode = in.readString();
        result = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(msg);
        dest.writeString(retCode);
        dest.writeString(result);
    }

    public static final Creator<UserProfileInfo> CREATOR = new Creator<UserProfileInfo>() {
        @Override
        public UserProfileInfo createFromParcel(Parcel source) {
            return new UserProfileInfo(source);
        }

        @Override
        public UserProfileInfo[] newArray(int size) {
            return new UserProfileInfo[size];
        }
    };
}
