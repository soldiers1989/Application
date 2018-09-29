package com.chad.hlife.entity.juhe;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * 图书分类目录
 */
public class BookCatalogInfo implements Parcelable {

    private String reason;
    private String resultcode;
    private List<Result> result;

    public String getReason() {
        return reason;
    }

    public String getResultcode() {
        return resultcode;
    }

    public List<Result> getResult() {
        return result;
    }

    protected BookCatalogInfo(Parcel in) {
        reason = in.readString();
        resultcode = in.readString();
        result = new ArrayList<>();
        in.readList(result, Result.class.getClassLoader());
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(reason);
        parcel.writeString(resultcode);
        parcel.writeList(result);
    }

    public static final Creator<BookCatalogInfo> CREATOR = new Creator<BookCatalogInfo>() {
        @Override
        public BookCatalogInfo createFromParcel(Parcel parcel) {
            return new BookCatalogInfo(parcel);
        }

        @Override
        public BookCatalogInfo[] newArray(int size) {
            return new BookCatalogInfo[size];
        }
    };

    public static class Result implements Parcelable {

        private int id;
        private String catalog;

        public int getId() {
            return id;
        }

        public String getCatalog() {
            return catalog;
        }

        protected Result(Parcel in) {
            id = in.readInt();
            catalog = in.readString();
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeInt(id);
            parcel.writeString(catalog);
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
