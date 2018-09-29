package com.chad.hlife.entity.juhe;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * 笑话
 */
public class JokeInfo implements Parcelable {

    private String reason;
    private String error_code;
    private Result result;

    public String getReason() {
        return reason;
    }

    public String getErrorCode() {
        return error_code;
    }

    public Result getResult() {
        return result;
    }

    protected JokeInfo(Parcel in) {
        reason = in.readString();
        error_code = in.readString();
        result = (Result) in.readValue(Result.class.getClassLoader());
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(reason);
        parcel.writeString(error_code);
        parcel.writeValue(result);
    }

    public static final Creator<JokeInfo> CREATOR = new Creator<JokeInfo>() {
        @Override
        public JokeInfo createFromParcel(Parcel parcel) {
            return new JokeInfo(parcel);
        }

        @Override
        public JokeInfo[] newArray(int size) {
            return new JokeInfo[size];
        }
    };

    public static class Result implements Parcelable {

        private List<Data> data;

        public List<Data> getData() {
            return data;
        }

        protected Result(Parcel in) {
            data = new ArrayList<>();
            in.readList(data, Data.class.getClassLoader());
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeList(data);
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

    public static class Data implements Parcelable {

        private String content;
        private String hashId;
        private String unixtime;
        private String updatetime;

        public String getContent() {
            return content;
        }

        public String getHashId() {
            return hashId;
        }

        public String getUnixtime() {
            return unixtime;
        }

        public String getUpdatetime() {
            return updatetime;
        }

        protected Data(Parcel in) {
            content = in.readString();
            hashId = in.readString();
            unixtime = in.readString();
            updatetime = in.readString();
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeString(content);
            parcel.writeString(hashId);
            parcel.writeString(unixtime);
            parcel.writeString(updatetime);
        }

        public static final Creator<Data> CREATOR = new Creator<Data>() {
            @Override
            public Data createFromParcel(Parcel parcel) {
                return new Data(parcel);
            }

            @Override
            public Data[] newArray(int size) {
                return new Data[size];
            }
        };
    }
}
