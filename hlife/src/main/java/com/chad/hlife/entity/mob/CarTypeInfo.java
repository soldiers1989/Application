package com.chad.hlife.entity.mob;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * 某品牌全部车型
 */
public class CarTypeInfo implements Parcelable {

    private String msg;
    private String retCode;
    private List<Car> result;

    public String getMsg() {
        return msg;
    }

    public String getRetCode() {
        return retCode;
    }

    public List<Car> getResult() {
        return result;
    }

    protected CarTypeInfo(Parcel in) {
        msg = in.readString();
        retCode = in.readString();
        result = new ArrayList<>();
        in.readList(result, Car.class.getClassLoader());
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

    public static final Creator<CarTypeInfo> CREATOR = new Creator<CarTypeInfo>() {
        @Override
        public CarTypeInfo createFromParcel(Parcel parcel) {
            return new CarTypeInfo(parcel);
        }

        @Override
        public CarTypeInfo[] newArray(int size) {
            return new CarTypeInfo[size];
        }
    };

    public static class Car implements Parcelable {

        private String brandName;
        private String carId;
        private String guidePrice;
        private String seriesName;

        public String getBrandName() {
            return brandName;
        }

        public String getCarId() {
            return carId;
        }

        public String getGuidePrice() {
            return guidePrice;
        }

        public String getSeriesName() {
            return seriesName;
        }

        protected Car(Parcel in) {
            brandName = in.readString();
            carId = in.readString();
            guidePrice = in.readString();
            seriesName = in.readString();
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeString(brandName);
            parcel.writeString(carId);
            parcel.writeString(guidePrice);
            parcel.writeString(seriesName);
        }

        public static final Creator<Car> CREATOR = new Creator<Car>() {
            @Override
            public Car createFromParcel(Parcel parcel) {
                return new Car(parcel);
            }

            @Override
            public Car[] newArray(int size) {
                return new Car[size];
            }
        };
    }
}
