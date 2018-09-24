package com.chad.weibo.entity;

import android.os.Parcel;
import android.os.Parcelable;

public class RateLimitStatus implements Parcelable {

    private String ip_limit;
    private String limit_time_unit;
    private String remaining_ip_hits;
    private String remaining_user_hits;
    private String reset_time;
    private String reset_time_in_seconds;
    private String user_limit;

    protected RateLimitStatus(Parcel out) {
        ip_limit = out.readString();
        limit_time_unit = out.readString();
        remaining_ip_hits = out.readString();
        remaining_user_hits = out.readString();
        reset_time = out.readString();
        reset_time_in_seconds = out.readString();
        user_limit = out.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(ip_limit);
        dest.writeString(limit_time_unit);
        dest.writeString(remaining_ip_hits);
        dest.writeString(remaining_user_hits);
        dest.writeString(reset_time);
        dest.writeString(reset_time_in_seconds);
        dest.writeString(user_limit);
    }

    public static final Creator<RateLimitStatus> CREATOR = new Creator<RateLimitStatus>() {
        @Override
        public RateLimitStatus createFromParcel(Parcel source) {
            return new RateLimitStatus(source);
        }

        @Override
        public RateLimitStatus[] newArray(int size) {
            return new RateLimitStatus[size];
        }
    };
}
