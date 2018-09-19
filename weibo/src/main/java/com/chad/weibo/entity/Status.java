package com.chad.weibo.entity;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * 微博信息
 */
public class Status implements Parcelable {

    private String created_at;
    private long id;
    private long mid;
    private String idstr;
    private String text;
    private String source;
    private boolean favorited;
    private boolean truncated;
    private String in_reply_to_status_id;
    private String in_reply_to_user_id;
    private String in_reply_to_screen_name;
    private String thumbnail_pic;
    private String bmiddle_pic;
    private String original_pic;
    private Geo geo;
    private User user;
    private Status retweeted_status;
    private int reposts_count;
    private int comments_count;
    private int attitudes_count;
    private int mlevel;

    public String getCreated_at() {
        return created_at;
    }

    public long getId() {
        return id;
    }

    public long getMid() {
        return mid;
    }

    public String getIdstr() {
        return idstr;
    }

    public String getText() {
        return text;
    }

    public String getSource() {
        return source;
    }

    public boolean isFavorited() {
        return favorited;
    }

    public boolean isTruncated() {
        return truncated;
    }

    public String getIn_reply_to_status_id() {
        return in_reply_to_status_id;
    }

    public String getIn_reply_to_user_id() {
        return in_reply_to_user_id;
    }

    public String getIn_reply_to_screen_name() {
        return in_reply_to_screen_name;
    }

    public String getThumbnail_pic() {
        return thumbnail_pic;
    }

    public String getBmiddle_pic() {
        return bmiddle_pic;
    }

    public String getOriginal_pic() {
        return original_pic;
    }

    public Geo getGeo() {
        return geo;
    }

    public User getUser() {
        return user;
    }

    public Status getRetweeted_status() {
        return retweeted_status;
    }

    public int getReposts_count() {
        return reposts_count;
    }

    public int getComments_count() {
        return comments_count;
    }

    public int getAttitudes_count() {
        return attitudes_count;
    }

    public int getMlevel() {
        return mlevel;
    }

    protected Status(Parcel out) {
        created_at = out.readString();
        id = out.readLong();
        mid = out.readLong();
        idstr = out.readString();
        text = out.readString();
        source = out.readString();
        favorited = out.readByte() == 1? true: false;
        truncated = out.readByte() == 1? true: false;
        in_reply_to_status_id = out.readString();
        in_reply_to_user_id = out.readString();
        in_reply_to_screen_name = out.readString();
        thumbnail_pic = out.readString();
        bmiddle_pic = out.readString();
        original_pic = out.readString();
        geo = (Geo) out.readValue(Geo.class.getClassLoader());
        user = (User) out.readValue(User.class.getClassLoader());
        retweeted_status = (Status) out.readValue(Status.class.getClassLoader());
        reposts_count = out.readInt();
        comments_count = out.readInt();
        attitudes_count = out.readInt();
        mlevel = out.readInt();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel in, int flags) {
        in.writeString(created_at);
        in.writeLong(id);
        in.writeLong(mid);
        in.writeString(idstr);
        in.writeString(text);
        in.writeString(source);
        in.writeByte((byte) (favorited? 1: 0));
        in.writeByte((byte) (truncated? 1: 0));
        in.writeString(in_reply_to_status_id);
        in.writeString(in_reply_to_user_id);
        in.writeString(in_reply_to_screen_name);
        in.writeString(thumbnail_pic);
        in.writeString(bmiddle_pic);
        in.writeString(original_pic);
        in.writeValue(geo);
        in.writeValue(user);
        in.writeValue(retweeted_status);
        in.writeInt(reposts_count);
        in.writeInt(comments_count);
        in.writeInt(attitudes_count);
        in.writeInt(mlevel);
    }

    public static final Creator<Status> CREATOR = new Creator<Status>() {
        @Override
        public Status createFromParcel(Parcel source) {
            return new Status(source);
        }

        @Override
        public Status[] newArray(int size) {
            return new Status[size];
        }
    };
}
