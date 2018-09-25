package com.chad.hlife.entity.weibo;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * 新浪微博用户信息
 */
public class WeiBoUserInfo implements Parcelable {

    private long id;
    private String name;
    private String location;
    private String description;
    private String cover_image_phone;
    private String gender;
    private String avatar_large;

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getLocation() {
        return location;
    }

    public String getDescription() {
        return description;
    }

    public String getCover_image_phone() {
        return cover_image_phone;
    }

    public String getGender() {
        return gender;
    }

    public String getAvatar_large() {
        return avatar_large;
    }

    protected WeiBoUserInfo(Parcel out) {
        id = out.readLong();
        name = out.readString();
        location = out.readString();
        description = out.readString();
        cover_image_phone = out.readString();
        gender = out.readString();
        avatar_large = out.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel in, int flags) {
        in.writeLong(id);
        in.writeString(name);
        in.writeString(location);
        in.writeString(description);
        in.writeString(cover_image_phone);
        in.writeString(gender);
        in.writeString(avatar_large);
    }

    public static final Creator<WeiBoUserInfo> CREATOR = new Creator<WeiBoUserInfo>() {
        @Override
        public WeiBoUserInfo createFromParcel(Parcel source) {
            return new WeiBoUserInfo(source);
        }

        @Override
        public WeiBoUserInfo[] newArray(int size) {
            return new WeiBoUserInfo[size];
        }
    };
}
