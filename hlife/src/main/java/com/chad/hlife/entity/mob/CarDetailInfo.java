package com.chad.hlife.entity.mob;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * 汽车详细信息
 */
public class CarDetailInfo implements Parcelable {

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

    protected CarDetailInfo(Parcel in) {
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

    public static final Creator<CarDetailInfo> CREATOR = new Creator<CarDetailInfo>() {
        @Override
        public CarDetailInfo createFromParcel(Parcel parcel) {
            return new CarDetailInfo(parcel);
        }

        @Override
        public CarDetailInfo[] newArray(int size) {
            return new CarDetailInfo[size];
        }
    };

    public static class Result implements Parcelable {

        private String brand; // 品牌名称
        private String brandName; // 车系名称
        private String carImage; // 图片地址
        private String seriesName; // 车型名称
        private String sonBrand; // 子品牌或合资品牌
        private List<Config> baseInfo; // 车型基本配置信息
        private List<Config> carbody; // 车身配置信息
        private List<Config> engine; // 发动机配置信息
        private List<Config> motorList; // 电动机配置信息
        private List<Config> chassis;  // 底盘配置信息
        private List<Config> controlConfig; // 操控配置信息
        private List<Config> transmission; // 变速箱信息
        private List<Config> wheelInfo; // 车轮制动信息
        private List<Config> safetyDevice; // 安全装置信息
        private List<Config> exterConfig; // 外部配置信息
        private List<Config> lightConfig; // 灯光配置信息
        private List<Config> glassConfig; // 玻璃/后视镜配置信息
        private List<Config> interConfig; // 内部配置信息
        private List<Config> seatConfig; // 座椅配置信息
        private List<Config> airConfig; // 空调/冰箱配置信息
        private List<Config> mediaConfig; // 多媒体配置信息
        private List<Config> techConfig; // 高科技配置信息

        public String getBrand() {
            return brand;
        }

        public String getBrandName() {
            return brandName;
        }

        public String getCarImage() {
            return carImage;
        }

        public String getSeriesName() {
            return seriesName;
        }

        public String getSonBrand() {
            return sonBrand;
        }

        public List<Config> getBaseInfo() {
            return baseInfo;
        }

        public List<Config> getCarbody() {
            return carbody;
        }

        public List<Config> getEngine() {
            return engine;
        }

        public List<Config> getMotorList() {
            return motorList;
        }

        public List<Config> getChassis() {
            return chassis;
        }

        public List<Config> getControlConfig() {
            return controlConfig;
        }

        public List<Config> getTransmission() {
            return transmission;
        }

        public List<Config> getWheelInfo() {
            return wheelInfo;
        }

        public List<Config> getSafetyDevice() {
            return safetyDevice;
        }

        public List<Config> getExterConfig() {
            return exterConfig;
        }

        public List<Config> getLightConfig() {
            return lightConfig;
        }

        public List<Config> getGlassConfig() {
            return glassConfig;
        }

        public List<Config> getInterConfig() {
            return interConfig;
        }

        public List<Config> getSeatConfig() {
            return seatConfig;
        }

        public List<Config> getAirConfig() {
            return airConfig;
        }

        public List<Config> getMediaConfig() {
            return mediaConfig;
        }

        public List<Config> getTechConfig() {
            return techConfig;
        }

        protected Result(Parcel in) {
            brand = in.readString();
            brandName = in.readString();
            carImage = in.readString();
            seriesName = in.readString();
            sonBrand = in.readString();
            baseInfo = new ArrayList<>();
            in.readList(baseInfo, Config.class.getClassLoader());
            carbody = new ArrayList<>();
            in.readList(carbody, Config.class.getClassLoader());
            engine = new ArrayList<>();
            in.readList(engine, Config.class.getClassLoader());
            motorList = new ArrayList<>();
            in.readList(motorList, Config.class.getClassLoader());
            chassis = new ArrayList<>();
            in.readList(chassis, Config.class.getClassLoader());
            controlConfig = new ArrayList<>();
            in.readList(controlConfig, Config.class.getClassLoader());
            transmission = new ArrayList<>();
            in.readList(transmission, Config.class.getClassLoader());
            wheelInfo = new ArrayList<>();
            in.readList(wheelInfo, Config.class.getClassLoader());
            safetyDevice = new ArrayList<>();
            in.readList(safetyDevice, Config.class.getClassLoader());
            exterConfig = new ArrayList<>();
            in.readList(exterConfig, Config.class.getClassLoader());
            lightConfig = new ArrayList<>();
            in.readList(lightConfig, Config.class.getClassLoader());
            glassConfig = new ArrayList<>();
            in.readList(glassConfig, Config.class.getClassLoader());
            interConfig = new ArrayList<>();
            in.readList(interConfig, Config.class.getClassLoader());
            seatConfig = new ArrayList<>();
            in.readList(seatConfig, Config.class.getClassLoader());
            airConfig = new ArrayList<>();
            in.readList(airConfig, Config.class.getClassLoader());
            mediaConfig = new ArrayList<>();
            in.readList(mediaConfig, Config.class.getClassLoader());
            techConfig = new ArrayList<>();
            in.readList(techConfig, Config.class.getClassLoader());
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeString(brand);
            parcel.writeString(brandName);
            parcel.writeString(carImage);
            parcel.writeString(seriesName);
            parcel.writeString(sonBrand);
            parcel.writeList(baseInfo);
            parcel.writeList(carbody);
            parcel.writeList(engine);
            parcel.writeList(motorList);
            parcel.writeList(chassis);
            parcel.writeList(controlConfig);
            parcel.writeList(transmission);
            parcel.writeList(wheelInfo);
            parcel.writeList(safetyDevice);
            parcel.writeList(exterConfig);
            parcel.writeList(lightConfig);
            parcel.writeList(glassConfig);
            parcel.writeList(interConfig);
            parcel.writeList(seatConfig);
            parcel.writeList(airConfig);
            parcel.writeList(mediaConfig);
            parcel.writeList(techConfig);
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

    public static class Config implements Parcelable {

        private String name;
        private String value;

        public String getName() {
            return name;
        }

        public String getValue() {
            return value;
        }

        protected Config(Parcel in) {
            name = in.readString();
            value = in.readString();
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeString(name);
            parcel.writeString(value);
        }

        public static final Creator<Config> CREATOR = new Creator<Config>() {
            @Override
            public Config createFromParcel(Parcel parcel) {
                return new Config(parcel);
            }

            @Override
            public Config[] newArray(int size) {
                return new Config[size];
            }
        };
    }
}
