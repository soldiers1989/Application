package com.chad.weibo.entity;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * 地理信息
 */
public class Geo implements Parcelable {

    private String longitude;
    private String latitude;
    private String city;
    private String province;
    private String city_name;
    private String province_name;
    private String address;
    private String pinyin;
    private String more;

    public String getLongitude() {
        return longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public String getCity() {
        return city;
    }

    public String getProvince() {
        return province;
    }

    public String getCity_name() {
        return city_name;
    }

    public String getProvince_name() {
        return province_name;
    }

    public String getAddress() {
        return address;
    }

    public String getPinyin() {
        return pinyin;
    }

    public String getMore() {
        return more;
    }

    protected Geo(Parcel out) {
        longitude = out.readString();
        latitude = out.readString();
        city = out.readString();
        province = out.readString();
        city_name = out.readString();
        province_name = out.readString();
        address = out.readString();
        pinyin = out.readString();
        more = out.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel in, int flags) {
        in.writeString(longitude);
        in.writeString(latitude);
        in.writeString(city);
        in.writeString(province);
        in.writeString(city_name);
        in.writeString(province_name);
        in.writeString(address);
        in.writeString(pinyin);
        in.writeString(more);
    }

    public static final Creator<Geo> CREATOR = new Creator<Geo>() {
        @Override
        public Geo createFromParcel(Parcel source) {
            return new Geo(source);
        }

        @Override
        public Geo[] newArray(int size) {
            return new Geo[size];
        }
    };
}
