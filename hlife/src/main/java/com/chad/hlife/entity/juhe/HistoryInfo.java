package com.chad.hlife.entity.juhe;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * 历史上的今天
 */
public class HistoryInfo implements Parcelable {

    private String reason;
    private String error_code;
    private List<Result> result;

    public String getReason() {
        return reason;
    }

    public String getErrorCode() {
        return error_code;
    }

    public List<Result> getResult() {
        return result;
    }

    protected HistoryInfo(Parcel in) {
        reason = in.readString();
        error_code = in.readString();
        result = new ArrayList<>();
        in.readList(result, Result.class.getClassLoader());
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(reason);
        parcel.writeString(error_code);
        parcel.writeList(result);
    }

    public static final Creator<HistoryInfo> CREATOR = new Creator<HistoryInfo>() {
        @Override
        public HistoryInfo createFromParcel(Parcel parcel) {
            return new HistoryInfo(parcel);
        }

        @Override
        public HistoryInfo[] newArray(int size) {
            return new HistoryInfo[size];
        }
    };

    public static class Result implements Parcelable {

        private String e_id;
        private String day;
        private String date;
        private String title;

        public String getEId() {
            return e_id;
        }

        public String getDay() {
            return day;
        }

        public String getDate() {
            return date;
        }

        public String getTitle() {
            return title;
        }

        protected Result(Parcel in) {
            e_id = in.readString();
            day = in.readString();
            date = in.readString();
            title = in.readString();
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeString(e_id);
            parcel.writeString(day);
            parcel.writeString(date);
            parcel.writeString(title);
        }

        public static final Creator<Result> CREATOR = new Creator<Result>() {
            @Override
            public Result createFromParcel(Parcel parcel) {
                return new Result(parcel);
            }

            @Override
            public Result[] newArray(int size) {
                return new Result[size];
            }
        };
    }
}
