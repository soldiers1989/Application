package com.chad.hlife.entity.mob;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * 汽车品牌
 */
public class CarBrandInfo implements Parcelable {

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

    protected CarBrandInfo(Parcel in) {
        msg = in.readString();
        retCode = in.readString();
        result = new ArrayList<>();
        in.readList(result, Result.class.getClassLoader());
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(msg);
        parcel.writeString(retCode);
        parcel.writeList(result);
    }

    public static final Creator<CarBrandInfo> CREATOR = new Creator<CarBrandInfo>() {
        @Override
        public CarBrandInfo createFromParcel(Parcel parcel) {
            return new CarBrandInfo(parcel);
        }

        @Override
        public CarBrandInfo[] newArray(int size) {
            return new CarBrandInfo[size];
        }
    };

    public static class Result implements Parcelable {

        private String name;
        private List<CarType> son;
        private String firstLetter;

        public String getName() {
            return name;
        }

        public List<CarType> getSon() {
            return son;
        }

        public void setFirstLetter(String firstLetter) {
            this.firstLetter = firstLetter;
        }

        public String getFirstLetter() {
            return firstLetter;
        }

        protected Result(Parcel in) {
            name = in.readString();
            son = new ArrayList<>();
            in.readList(son, CarType.class.getClassLoader());
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeString(name);
            parcel.writeList(son);
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

    public static class CarType implements Parcelable {

        private String car;
        private String type;

        public String getCar() {
            return car;
        }

        public String getType() {
            return type;
        }

        protected CarType(Parcel in) {
            car = in.readString();
            type = in.readString();
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeString(car);
            parcel.writeString(type);
        }

        public static final Creator<CarType> CREATOR = new Creator<CarType>() {
            @Override
            public CarType createFromParcel(Parcel parcel) {
                return null;
            }

            @Override
            public CarType[] newArray(int size) {
                return new CarType[size];
            }
        };
    }
}
