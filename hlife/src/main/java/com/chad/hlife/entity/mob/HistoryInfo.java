package com.chad.hlife.entity.mob;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * 历史上的今天
 */
public class HistoryInfo implements Parcelable {

    private String msg;
    private String retCode;
    private List<Result> result;

    public String getMsg() {
        return msg;
    }

    public String getRetCode() {
        return retCode;
    }

    public List<Result> getResult() {
        return result;
    }

    protected HistoryInfo(Parcel in) {
        msg = in.readString();
        retCode = in.readString();
        result = new ArrayList<>();
        in.readList(result, Result.class.getClassLoader());
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(msg);
        dest.writeString(retCode);
        dest.writeList(result);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<HistoryInfo> CREATOR = new Creator<HistoryInfo>() {
        @Override
        public HistoryInfo createFromParcel(Parcel in) {
            return new HistoryInfo(in);
        }

        @Override
        public HistoryInfo[] newArray(int size) {
            return new HistoryInfo[size];
        }
    };

    public static class Result implements Parcelable {

        private int month;
        private int day;
        private String id;
        private String date;
        private String title;
        private String event;

        public int getMonth() {
            return month;
        }

        public int getDay() {
            return day;
        }

        public String getId() {
            return id;
        }

        public String getDate() {
            return date;
        }

        public String getTitle() {
            return title;
        }

        public String getEvent() {
            return event;
        }

        protected Result(Parcel in) {
            month = in.readInt();
            day = in.readInt();
            id = in.readString();
            date = in.readString();
            title = in.readString();
            event = in.readString();
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeInt(month);
            dest.writeInt(day);
            dest.writeString(id);
            dest.writeString(date);
            dest.writeString(title);
            dest.writeString(event);
        }

        public static final Creator<Result> CREATOR = new Creator<Result>() {
            @Override
            public Result createFromParcel(Parcel source) {
                return new Result(source);
            }

            @Override
            public Result[] newArray(int size) {
                return new Result[size];
            }
        };
    }
}
