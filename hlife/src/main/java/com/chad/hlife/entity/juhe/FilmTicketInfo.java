package com.chad.hlife.entity.juhe;

import android.os.Parcel;
import android.os.Parcelable;

import com.chad.hlife.util.StringUtil;

/**
 * 电影票
 */
public class FilmTicketInfo implements Parcelable {

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

    protected FilmTicketInfo(Parcel in) {
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

    public static final Creator<FilmTicketInfo> CREATOR = new Creator<FilmTicketInfo>() {
        @Override
        public FilmTicketInfo createFromParcel(Parcel parcel) {
            return new FilmTicketInfo(parcel);
        }

        @Override
        public FilmTicketInfo[] newArray(int size) {
            return new FilmTicketInfo[size];
        }
    };

    public static class Result implements Parcelable {

        private String h5url;
        private String h5weixin;

        public String getH5url() {
            return h5url;
        }

        public String getH5weixin() {
            return h5weixin;
        }

        protected Result(Parcel in) {
            h5url = in.readString();
            h5weixin = in.readString();
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeString(h5url);
            parcel.writeString(h5weixin);
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
