package com.chad.zhihu.entity.zhihu;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * 额外信息
 */
public class DetailsExtraInfo implements Parcelable {

    private int popularity; // 点赞数
    private int comments; // 总评论数
    @SerializedName("long_comments")
    private int longComments; // 长评论数
    @SerializedName("short_comments")
    private int shortComments; // 短评论数

    protected DetailsExtraInfo(Parcel parcel) {
        popularity = parcel.readInt();
        comments = parcel.readInt();
        longComments = parcel.readInt();
        shortComments = parcel.readInt();
    }

    public void setPopularity(int popularity) {
        this.popularity = popularity;
    }

    public int getPopularity() {
        return popularity;
    }

    public void setComments(int comments) {
        this.comments = comments;
    }

    public int getComments() {
        return comments;
    }

    public void setLongComments(int longComments) {
        this.longComments = longComments;
    }

    public int getLongComments() {
        return longComments;
    }

    public void setShortComments(int shortComments) {
        this.shortComments = shortComments;
    }

    public int getShortComments() {
        return shortComments;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(popularity);
        dest.writeInt(comments);
        dest.writeInt(longComments);
        dest.writeInt(shortComments);
    }

    public static final Creator<DetailsExtraInfo> CREATOR = new Creator<DetailsExtraInfo>() {

        @Override
        public DetailsExtraInfo createFromParcel(Parcel source) {
            return new DetailsExtraInfo(source);
        }

        @Override
        public DetailsExtraInfo[] newArray(int size) {
            return new DetailsExtraInfo[size];
        }
    };
}
