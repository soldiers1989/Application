package com.chad.weibo.entity;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * 用户信息
 */
public class User implements Parcelable {

    private long id;
    private String idstr;
    private String screen_name;
    private String name;
    private int province;
    private int city;
    private String location;
    private String description;
    private String url;
    private String profile_image_url;
    private String profile_url;
    private String cover_image_phone;
    private String domain;
    private String weihao;
    private String gender;
    private int followers_count;
    private int friends_count;
    private int statuses_count;
    private int favourites_count;
    private String created_at;
    private boolean following;
    private boolean allow_all_act_msg;
    private boolean geo_enabled;
    private boolean verified;
    private int verified_type;
    private String remark;
    private Status status;
    private boolean allow_all_comment;
    private String avatar_large;
    private String avatar_hd;
    private String verified_reason;
    private boolean follow_me;
    private int online_status;
    private int bi_followers_count;
    private String lang;
    private int urank;

    public long getId() {
        return id;
    }

    public String getIdstr() {
        return idstr;
    }

    public String getScreen_name() {
        return screen_name;
    }

    public String getName() {
        return name;
    }

    public int getProvince() {
        return province;
    }

    public int getCity() {
        return city;
    }

    public String getLocation() {
        return location;
    }

    public String getDescription() {
        return description;
    }

    public String getUrl() {
        return url;
    }

    public String getProfile_image_url() {
        return profile_image_url;
    }

    public String getProfile_url() {
        return profile_url;
    }

    public String getCover_image_phone() {
        return cover_image_phone;
    }

    public String getDomain() {
        return domain;
    }

    public String getWeihao() {
        return weihao;
    }

    public String getGender() {
        return gender;
    }

    public int getFollowers_count() {
        return followers_count;
    }

    public int getFriends_count() {
        return friends_count;
    }

    public int getStatuses_count() {
        return statuses_count;
    }

    public int getFavourites_count() {
        return favourites_count;
    }

    public String getCreated_at() {
        return created_at;
    }

    public boolean isFollowing() {
        return following;
    }

    public boolean isAllow_all_act_msg() {
        return allow_all_act_msg;
    }

    public boolean isGeo_enabled() {
        return geo_enabled;
    }

    public boolean isVerified() {
        return verified;
    }

    public int getVerified_type() {
        return verified_type;
    }

    public String getRemark() {
        return remark;
    }

    public Status getStatus() {
        return status;
    }

    public boolean isAllow_all_comment() {
        return allow_all_comment;
    }

    public String getAvatar_large() {
        return avatar_large;
    }

    public String getAvatar_hd() {
        return avatar_hd;
    }

    public String getVerified_reason() {
        return verified_reason;
    }

    public boolean isFollow_me() {
        return follow_me;
    }

    public int getOnline_status() {
        return online_status;
    }

    public int getBi_followers_count() {
        return bi_followers_count;
    }

    public String getLang() {
        return lang;
    }

    public int getUrank() {
        return urank;
    }

    protected User(Parcel out) {
        id = out.readLong();
        idstr = out.readString();
        screen_name = out.readString();
        name = out.readString();
        province = out.readInt();
        city = out.readInt();
        location = out.readString();
        description = out.readString();
        url = out.readString();
        profile_image_url = out.readString();
        profile_url = out.readString();
        cover_image_phone = out.readString();
        domain = out.readString();
        weihao = out.readString();
        gender = out.readString();
        followers_count = out.readInt();
        friends_count = out.readInt();
        statuses_count = out.readInt();
        favourites_count = out.readInt();
        created_at = out.readString();
        following = out.readByte() == 1 ? true : false;
        allow_all_act_msg = out.readByte() == 1 ? true : false;
        geo_enabled = out.readByte() == 1 ? true : false;
        verified = out.readByte() == 1 ? true : false;
        verified_type = out.readInt();
        remark = out.readString();
        status = (Status) out.readValue(Status.class.getClassLoader());
        allow_all_comment = out.readByte() == 1 ? true : false;
        avatar_large = out.readString();
        avatar_hd = out.readString();
        verified_reason = out.readString();
        follow_me = out.readByte() == 1 ? true : false;
        online_status = out.readInt();
        bi_followers_count = out.readInt();
        lang = out.readString();
        urank = out.readInt();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel in, int flags) {
        in.writeLong(id);
        in.writeString(idstr);
        in.writeString(screen_name);
        in.writeString(name);
        in.writeInt(province);
        in.writeInt(city);
        in.writeString(location);
        in.writeString(description);
        in.writeString(url);
        in.writeString(profile_image_url);
        in.writeString(profile_url);
        in.writeString(cover_image_phone);
        in.writeString(domain);
        in.writeString(weihao);
        in.writeString(gender);
        in.writeInt(followers_count);
        in.writeInt(friends_count);
        in.writeInt(statuses_count);
        in.writeInt(favourites_count);
        in.writeString(created_at);
        in.writeByte((byte) (following ? 1 : 0));
        in.writeByte((byte) (allow_all_act_msg ? 1 : 0));
        in.writeByte((byte) (geo_enabled ? 1 : 0));
        in.writeByte((byte) (verified ? 1 : 0));
        in.writeInt(verified_type);
        in.writeString(remark);
        in.writeValue(status);
        in.writeByte((byte) (allow_all_comment ? 1 : 0));
        in.writeString(avatar_large);
        in.writeString(avatar_hd);
        in.writeString(verified_reason);
        in.writeByte((byte) (follow_me ? 1 : 0));
        in.writeInt(online_status);
        in.writeInt(bi_followers_count);
        in.writeString(lang);
        in.writeInt(urank);
    }

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel source) {
            return new User(source);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };
}
