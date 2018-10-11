package com.chad.hlife.entity.juhe;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * 周边Wifi
 */
public class WifiInfo implements Parcelable {

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

    protected WifiInfo(Parcel in) {
        reason = in.readString();
        resultcode = in.readString();
        result = (Result) in.readValue(Result.class.getClassLoader());
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(reason);
        dest.writeString(resultcode);
        dest.writeValue(result);
    }

    public static final Creator<WifiInfo> CREATOR = new Creator<WifiInfo>() {
        @Override
        public WifiInfo createFromParcel(Parcel source) {
            return new WifiInfo(source);
        }

        @Override
        public WifiInfo[] newArray(int size) {
            return new WifiInfo[size];
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
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeList(data);
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

    public static class Data implements Parcelable {

        private String name;
        private String intro;
        private String address;
        private String province;
        private String city;
        private String distance;
        private Double google_lat;
        private Double google_lon;
        private Double baidu_lat;
        private Double baidu_lon;

        public String getName() {
            return name;
        }

        public String getIntro() {
            return intro;
        }

        public String getAddress() {
            return address;
        }

        public String getProvince() {
            return province;
        }

        public String getCity() {
            return city;
        }

        public String getDistance() {
            return distance;
        }

        public Double getGoogleLat() {
            return google_lat;
        }

        public Double getGoogleLon() {
            return google_lon;
        }

        public Double getBaiduLat() {
            return baidu_lat;
        }

        public Double getBaiduLon() {
            return baidu_lon;
        }

        protected Data(Parcel in) {
            name = in.readString();
            intro = in.readString();
            address = in.readString();
            province = in.readString();
            city = in.readString();
            distance = in.readString();
            google_lat = in.readDouble();
            google_lon = in.readDouble();
            baidu_lat = in.readDouble();
            baidu_lon = in.readDouble();
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(name);
            dest.writeString(intro);
            dest.writeString(address);
            dest.writeString(province);
            dest.writeString(city);
            dest.writeString(distance);
            dest.writeDouble(google_lat);
            dest.writeDouble(google_lon);
            dest.writeDouble(baidu_lat);
            dest.writeDouble(baidu_lon);
        }

        public static final Creator<Data> CREATOR = new Creator<Data>() {
            @Override
            public Data createFromParcel(Parcel source) {
                return new Data(source);
            }

            @Override
            public Data[] newArray(int size) {
                return new Data[size];
            }
        };
    }
}
