package com.chad.hlife.entity.mob;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;
import com.google.gson.annotations.Until;

import java.util.ArrayList;
import java.util.List;

/**
 * 今日油价
 */
public class OilPricesInfo implements Parcelable {

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

    protected OilPricesInfo(Parcel in) {
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
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(msg);
        dest.writeString(retCode);
        dest.writeList(result);
    }

    public static final Creator<OilPricesInfo> CREATOR = new Creator<OilPricesInfo>() {
        @Override
        public OilPricesInfo createFromParcel(Parcel source) {
            return new OilPricesInfo(source);
        }

        @Override
        public OilPricesInfo[] newArray(int size) {
            return new OilPricesInfo[size];
        }
    };


    public static class Result implements Parcelable {
        
        Prices prices;

        public Prices getPrices() {
            return prices;
        }

        protected Result(Parcel in) {
            prices = (Prices) in.readValue(Prices.class.getClassLoader());
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeValue(prices);
        }

        @Override
        public int describeContents() {
            return 0;
        }

        public static final Creator<Result> CREATOR = new Creator<Result>() {
            @Override
            public Result createFromParcel(Parcel in) {
                return new Result(in);
            }

            @Override
            public Result[] newArray(int size) {
                return new Result[size];
            }
        };
    }

    public static class Prices implements Parcelable {

        private String dieselOil0;
        private String gasoline90;
        private String gasoline93;
        private String gasoline97;
        private String province;

        public String getDieselOil0() {
            return dieselOil0;
        }

        public String getGasoline90() {
            return gasoline90;
        }

        public String getGasoline93() {
            return gasoline93;
        }

        public String getGasoline97() {
            return gasoline97;
        }

        public String getProvince() {
            return province;
        }

        protected Prices(Parcel in) {
            dieselOil0 = in.readString();
            gasoline90 = in.readString();
            gasoline93 = in.readString();
            gasoline97 = in.readString();
            province = in.readString();
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(dieselOil0);
            dest.writeString(gasoline90);
            dest.writeString(gasoline93);
            dest.writeString(gasoline97);
            dest.writeString(province);
        }

        public static final Creator<Prices> CREATOR = new Creator<Prices>() {
            @Override
            public Prices createFromParcel(Parcel source) {
                return new Prices(source);
            }

            @Override
            public Prices[] newArray(int size) {
                return new Prices[size];
            }
        };
    }
}
