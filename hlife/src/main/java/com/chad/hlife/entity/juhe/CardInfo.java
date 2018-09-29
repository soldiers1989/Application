package com.chad.hlife.entity.juhe;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * 身份证信息
 */
public class CardInfo implements Parcelable {

    private String reason;
    private String resultcode;
    private Result result;

    public String getReason() {
        return reason;
    }

    public String getResultCode() {
        return resultcode;
    }

    public Result getResult() {
        return result;
    }

    protected CardInfo(Parcel in) {
        reason = in.readString();
        resultcode = in.readString();
        result = (Result) in.readValue(Result.class.getClassLoader());
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(reason);
        parcel.writeString(resultcode);
        parcel.writeValue(result);
    }

    public static final Creator<CardInfo> CREATOR = new Creator<CardInfo>() {
        @Override
        public CardInfo createFromParcel(Parcel parcel) {
            return new CardInfo(parcel);
        }

        @Override
        public CardInfo[] newArray(int size) {
            return new CardInfo[size];
        }
    };

    public static class Result implements Parcelable {

        private String area;
        private String sex;
        private String birthday;

        public String getArea() {
            return area;
        }

        public String getSex() {
            return sex;
        }

        public String getBirthday() {
            return birthday;
        }

        protected Result(Parcel in) {
            area = in.readString();
            sex = in.readString();
            birthday = in.readString();
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeString(area);
            parcel.writeString(sex);
            parcel.writeString(birthday);
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
