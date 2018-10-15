package com.chad.hlife.entity.mob;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * 今日油价
 */
public class OilPriceInfo implements Parcelable {

    private String msg;
    private String retCode;
    private Result result;

    public String getMsg() {
        return msg;
    }

    public String getRetCode() {
        return retCode;
    }

    public Result getResult() {
        return result;
    }

    protected OilPriceInfo(Parcel in) {
        msg = in.readString();
        retCode = in.readString();
        result = (Result) in.readValue(Result.class.getClassLoader());
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(msg);
        dest.writeString(retCode);
        dest.writeValue(result);
    }

    public static final Creator<OilPriceInfo> CREATOR = new Creator<OilPriceInfo>() {
        @Override
        public OilPriceInfo createFromParcel(Parcel source) {
            return new OilPriceInfo(source);
        }

        @Override
        public OilPriceInfo[] newArray(int size) {
            return new OilPriceInfo[size];
        }
    };

    public static class Result implements Parcelable {
        
        List<Price> prices;

        public List<Price> getPrices() {
            return prices;
        }

        protected Result(Parcel in) {
            prices = new ArrayList<>();
            in.readList(prices, Price.class.getClassLoader());
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeList(prices);
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

    public static class Price implements Parcelable {

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

        protected Price(Parcel in) {
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

        public static final Creator<Price> CREATOR = new Creator<Price>() {
            @Override
            public Price createFromParcel(Parcel source) {
                return new Price(source);
            }

            @Override
            public Price[] newArray(int size) {
                return new Price[size];
            }
        };
    }
}
