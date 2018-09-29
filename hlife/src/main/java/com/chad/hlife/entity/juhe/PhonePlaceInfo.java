package com.chad.hlife.entity.juhe;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * 手机号码归属地信息
 */
public class PhonePlaceInfo implements Parcelable {

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

    protected PhonePlaceInfo(Parcel in) {
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

    public static final Creator<PhonePlaceInfo> CREATOR = new Creator<PhonePlaceInfo>() {
        @Override
        public PhonePlaceInfo createFromParcel(Parcel parcel) {
            return new PhonePlaceInfo(parcel);
        }

        @Override
        public PhonePlaceInfo[] newArray(int size) {
            return new PhonePlaceInfo[size];
        }
    };

    public static class Result implements Parcelable {

        private String province;
        private String city;
        private String areacode;
        private String zip;
        private String company;
        private String card;

        public String getProvince() {
            return province;
        }

        public String getCity() {
            return city;
        }

        public String getAreacode() {
            return areacode;
        }

        public String getZip() {
            return zip;
        }

        public String getCompany() {
            return company;
        }

        public String getCard() {
            return card;
        }

        protected Result(Parcel in) {
            province = in.readString();
            city = in.readString();
            areacode = in.readString();
            zip = in.readString();
            company = in.readString();
            card = in.readString();
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeString(province);
            parcel.writeString(city);
            parcel.writeString(areacode);
            parcel.writeString(zip);
            parcel.writeString(company);
            parcel.writeString(card);
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
