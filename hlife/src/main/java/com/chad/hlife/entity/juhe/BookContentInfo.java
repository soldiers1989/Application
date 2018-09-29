package com.chad.hlife.entity.juhe;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * 图书内容
 */
public class BookContentInfo implements Parcelable {

    private String reason;
    private String resultcode;
    private Result result;

    public String getReason() {
        return reason;
    }

    public String getResultcode() {
        return resultcode;
    }

    public Result getResult() {
        return result;
    }

    protected BookContentInfo(Parcel in) {
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

    public static final Creator<BookContentInfo> CREATOR = new Creator<BookContentInfo>() {
        @Override
        public BookContentInfo createFromParcel(Parcel parcel) {
            return new BookContentInfo(parcel);
        }

        @Override
        public BookContentInfo[] newArray(int size) {
            return new BookContentInfo[size];
        }
    };

    public static class Result implements Parcelable {

        private List<Data> data;
        private int totalNum;
        private int pn;
        private int rn;

        public List<Data> getData() {
            return data;
        }

        public int getTotalNum() {
            return totalNum;
        }

        public int getPn() {
            return pn;
        }

        public int getRn() {
            return rn;
        }

        protected Result(Parcel in) {
            data = new ArrayList<>();
            in.readList(data, Data.class.getClassLoader());
            totalNum = in.readInt();
            pn = in.readInt();
            rn = in.readInt();
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeList(data);
            dest.writeInt(totalNum);
            dest.writeInt(pn);
            dest.writeInt(rn);
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

    public static class Data implements Parcelable {

        private String title;
        private String catalog;
        private String tags;
        private String sub1;
        private String sub2;
        private String img;
        private String reading;
        private String online;
        private String bytime;

        public String getTitle() {
            return title;
        }

        public String getCatalog() {
            return catalog;
        }

        public String getTags() {
            return tags;
        }

        public String getSub1() {
            return sub1;
        }

        public String getSub2() {
            return sub2;
        }

        public String getImg() {
            return img;
        }

        public String getReading() {
            return reading;
        }

        public String getOnline() {
            return online;
        }

        public String getBytime() {
            return bytime;
        }

        protected Data(Parcel in) {
            title = in.readString();
            catalog = in.readString();
            tags = in.readString();
            sub1 = in.readString();
            sub2 = in.readString();
            img = in.readString();
            reading = in.readString();
            online = in.readString();
            bytime = in.readString();
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeString(title);
            parcel.writeString(catalog);
            parcel.writeString(tags);
            parcel.writeString(sub1);
            parcel.writeString(sub2);
            parcel.writeString(img);
            parcel.writeString(reading);
            parcel.writeString(online);
            parcel.writeString(bytime);
        }

        public static final Creator<Data> CREATOR = new Creator<Data>() {
            @Override
            public Data createFromParcel(Parcel parcel) {
                return new Data(parcel);
            }

            @Override
            public Data[] newArray(int size) {
                return new Data[size];
            }
        };
    }
}
