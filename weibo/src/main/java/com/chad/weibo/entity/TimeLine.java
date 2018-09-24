package com.chad.weibo.entity;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * 微博列表
 */
public class TimeLine implements Parcelable {

    private List<Status> statuses;
    private long previous_cursor;
    private long next_cursor;
    private int total_number;

    public List<Status> getStatuses() {
        return statuses;
    }

    public long getPrevious_cursor() {
        return previous_cursor;
    }

    public long getNext_cursor() {
        return next_cursor;
    }

    public int getTotal_number() {
        return total_number;
    }

    protected TimeLine(Parcel out) {
        statuses = new ArrayList<>();
        out.readList(statuses, Status.class.getClassLoader());
        previous_cursor = out.readLong();
        next_cursor = out.readLong();
        total_number = out.readInt();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel in, int i) {
        in.writeList(statuses);
        in.writeLong(previous_cursor);
        in.writeLong(next_cursor);
        in.writeInt(total_number);
    }

    public static final Creator<TimeLine> CREATOR = new Creator<TimeLine>() {
        @Override
        public TimeLine createFromParcel(Parcel parcel) {
            return new TimeLine(parcel);
        }

        @Override
        public TimeLine[] newArray(int size) {
            return new TimeLine[size];
        }
    };
}
