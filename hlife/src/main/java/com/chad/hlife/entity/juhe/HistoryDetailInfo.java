package com.chad.hlife.entity.juhe;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * 历史上的今天详情
 */
public class HistoryDetailInfo implements Parcelable {

    private String reason;
    private String error_code;
    private Result result;

    public String getReason() {
        return reason;
    }

    public String getErrorCode() {
        return error_code;
    }

    public Result getResult() {
        return result;
    }

    protected HistoryDetailInfo(Parcel out) {
        reason = out.readString();
        error_code = out.readString();
        result = (Result) out.readValue(Result.class.getClassLoader());
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(reason);
        parcel.writeString(error_code);
        parcel.writeValue(result);
    }

    public static final Creator<HistoryDetailInfo> CREATOR = new Creator<HistoryDetailInfo>() {
        @Override
        public HistoryDetailInfo createFromParcel(Parcel parcel) {
            return new HistoryDetailInfo(parcel);
        }

        @Override
        public HistoryDetailInfo[] newArray(int size) {
            return new HistoryDetailInfo[size];
        }
    };

    public static class Result implements Parcelable {

        private String e_id;
        private String title;
        private String content;
        private int picNo;
        private List<PicUrl> picUrl;

        public String getEId() {
            return e_id;
        }

        public String getTitle() {
            return title;
        }

        public String getContent() {
            return content;
        }

        public int getPicNo() {
            return picNo;
        }

        public List<PicUrl> getPicUrl() {
            return picUrl;
        }

        protected Result(Parcel out) {
            e_id = out.readString();
            title = out.readString();
            content = out.readString();
            picNo = out.readInt();
            picUrl = new ArrayList<>();
            out.readList(picUrl, PicUrl.class.getClassLoader());
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeString(e_id);
            parcel.writeString(title);
            parcel.writeString(content);
            parcel.writeInt(picNo);
            parcel.writeList(picUrl);
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

    public static class PicUrl implements Parcelable {

        private int id;
        private String pic_title;
        private String url;

        public int getId() {
            return id;
        }

        public String getPicTitle() {
            return pic_title;
        }

        public String getUrl() {
            return url;
        }

        protected PicUrl(Parcel out) {
            id = out.readInt();
            pic_title = out.readString();
            url = out.readString();
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeInt(id);
            parcel.writeString(pic_title);
            parcel.writeString(url);
        }

        public static final Creator<PicUrl> CREATOR = new Creator<PicUrl>() {
            @Override
            public PicUrl createFromParcel(Parcel parcel) {
                return new PicUrl(parcel);
            }

            @Override
            public PicUrl[] newArray(int size) {
                return new PicUrl[size];
            }
        };
    }
}
