package com.chad.hlife.entity.juhe;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * 图书分类目录
 */
public class BooksStoreInfo implements Parcelable {

    private String reason;
    private String resultcode;
    private List<Result> result;

    protected BooksStoreInfo(Parcel out) {
        reason = out.readString();
        resultcode = out.readString();
        result = new ArrayList<>();
        out.readList(result, Result.class.getClassLoader());
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

    public static final Creator<BooksStoreInfo> CREATOR = new Creator<BooksStoreInfo>() {
        @Override
        public BooksStoreInfo createFromParcel(Parcel parcel) {
            return new BooksStoreInfo(parcel);
        }

        @Override
        public BooksStoreInfo[] newArray(int size) {
            return new BooksStoreInfo[size];
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

        protected Result(Parcel out) {
            id = out.readInt();
            catalog = out.readString();
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
