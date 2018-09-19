package com.chad.weibo.entity;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * 当前登录用户及其所关注用户的最新微博
 */
public class HomeTimeLine implements Parcelable {

    private List<Status> statuses;
    private int previous_cursor;
    private int next_cursor;
    private int total_number;

    protected HomeTimeLine(Parcel out) {
        statuses = new ArrayList<>();
        out.readList(statuses, Status.class.getClassLoader());
        previous_cursor = out.readInt();
        next_cursor = out.readInt();
        total_number = out.readInt();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel in, int i) {
        in.writeList(statuses);
        in.writeInt(previous_cursor);
        in.writeInt(next_cursor);
        in.writeInt(total_number);
    }

    public static final Creator<HomeTimeLine> CREATOR = new Creator<HomeTimeLine>() {
        @Override
        public HomeTimeLine createFromParcel(Parcel parcel) {
            return new HomeTimeLine(parcel);
        }

        @Override
        public HomeTimeLine[] newArray(int size) {
            return new HomeTimeLine[size];
        }
    };
}
