package com.chad.hlife.entity.juhe;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * 新闻头条
 */
public class NewsInfo implements Parcelable {

    private String reason;
    private Result result;

    public String getReason() {
        return reason;
    }

    public Result getResult() {
        return result;
    }

    protected NewsInfo(Parcel in) {
        reason = in.readString();
        result = (Result) in.readValue(Result.class.getClassLoader());
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(reason);
        parcel.writeValue(result);
    }

    public static final Creator<NewsInfo> CREATOR = new Creator<NewsInfo>() {
        @Override
        public NewsInfo createFromParcel(Parcel parcel) {
            return new NewsInfo(parcel);
        }

        @Override
        public NewsInfo[] newArray(int size) {
            return new NewsInfo[size];
        }
    };

    public static class Result implements Parcelable {

        private String stat;
        private List<Data> data;

        public String getStat() {
            return stat;
        }

        public List<Data> getData() {
            return data;
        }

        protected Result(Parcel in) {
            stat = in.readString();
            data = new ArrayList<>();
            in.readList(data, Data.class.getClassLoader());
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeString(stat);
            parcel.writeList(data);
        }

        public static final Creator<Result> CREATOR = new Creator<Result>() {
            @Override
            public Result createFromParcel(Parcel parcel) {
                return null;
            }

            @Override
            public Result[] newArray(int size) {
                return new Result[size];
            }
        };
    }

    public static class Data implements Parcelable {

        private String uniquekey;
        private String title;
        private String date;
        private String category;
        private String author_name;
        private String url;
        private String thumbnail_pic_s;
        private String thumbnail_pic_s02;
        private String thumbnail_pic_s03;

        public String getUniquekey() {
            return uniquekey;
        }

        public String getTitle() {
            return title;
        }

        public String getDate() {
            return date;
        }

        public String getCategory() {
            return category;
        }

        public String getAuthorName() {
            return author_name;
        }

        public String getUrl() {
            return url;
        }

        public String getThumbnailPicS() {
            return thumbnail_pic_s;
        }

        public String getThumbnailPicS02() {
            return thumbnail_pic_s02;
        }

        public String getThumbnailPicS03() {
            return thumbnail_pic_s03;
        }

        protected Data(Parcel in) {
            uniquekey = in.readString();
            title = in.readString();
            date = in.readString();
            category = in.readString();
            author_name = in.readString();
            url = in.readString();
            thumbnail_pic_s = in.readString();
            thumbnail_pic_s02 = in.readString();
            thumbnail_pic_s03 = in.readString();
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeString(uniquekey);
            parcel.writeString(title);
            parcel.writeString(date);
            parcel.writeString(category);
            parcel.writeString(author_name);
            parcel.writeString(url);
            parcel.writeString(thumbnail_pic_s);
            parcel.writeString(thumbnail_pic_s02);
            parcel.writeString(thumbnail_pic_s03);
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
