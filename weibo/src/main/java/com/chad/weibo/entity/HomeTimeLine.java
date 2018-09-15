package com.chad.weibo.entity;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * 获取当前登录用户及其所关注（授权）用户的最新微博
 */
public class HomeTimeLine implements Parcelable {

    private List<Status> statuses;
    private List<Ad> ad;
    private int previous_cursor;
    private int next_cursor;
    private int total_number;

    protected HomeTimeLine(Parcel in) {
        statuses = new ArrayList<>();
        in.readList(statuses, Status.class.getClassLoader());
        ad = new ArrayList<>();
        in.readList(ad, Ad.class.getClassLoader());
        previous_cursor = in.readInt();
        next_cursor = in.readInt();
        total_number = in.readInt();
    }

    public List<Status> getStatuses() {
        return statuses;
    }

    public List<Ad> getAd() {
        return ad;
    }

    public int getPrevious_cursor() {
        return previous_cursor;
    }

    public int getNext_cursor() {
        return next_cursor;
    }

    public int getTotal_number() {
        return total_number;
    }

    @Override
    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeList(statuses);
        parcel.writeList(ad);
        parcel.writeInt(previous_cursor);
        parcel.writeInt(next_cursor);
        parcel.writeInt(total_number);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<HomeTimeLine> CREATOR = new Creator<HomeTimeLine>() {
        @Override
        public HomeTimeLine createFromParcel(Parcel in) {
            return new HomeTimeLine(in);
        }

        @Override
        public HomeTimeLine[] newArray(int size) {
            return new HomeTimeLine[size];
        }
    };

    public static class Status implements Parcelable {

        private String created_at;
        private int id;
        private String text;
        private String source;
        private boolean favorited;
        private boolean truncated;
        private String in_reply_to_status_id;
        private String in_reply_to_user_id;
        private String in_reply_to_screen_name;
        private String geo;
        private String mid;
        private int reposts_count;
        private int comments_count;
        private String[] annotations;
        private User user;

        protected Status(Parcel in) {
            created_at = in.readString();
            id = in.readInt();
            text = in.readString();
            source = in.readString();
            favorited = in.readByte() == 1? true: false;
            truncated = in.readByte() == 1? true: false;
            in_reply_to_status_id = in.readString();
            in_reply_to_user_id = in.readString();
            in_reply_to_screen_name = in.readString();
            geo = in.readString();
            mid = in.readString();
            reposts_count = in.readInt();
            comments_count = in.readInt();
            annotations = new String[]{};
            in.readStringArray(annotations);
            user = in.readParcelable(User.class.getClassLoader());
        }

        public String getCreated_at() {
            return created_at;
        }

        public int getId() {
            return id;
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

        public String getGeo() {
            return geo;
        }

        public String getMid() {
            return mid;
        }

        public int getReposts_count() {
            return reposts_count;
        }

        public int getComments_count() {
            return comments_count;
        }

        public String[] getAnnotations() {
            return annotations;
        }

        public User getUser() {
            return user;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeString(created_at);
            parcel.writeInt(id);
            parcel.writeString(text);
            parcel.writeString(source);
            parcel.writeByte((byte) (favorited == true? 1: 0));
            parcel.writeByte((byte) (truncated == true? 1: 0));
            parcel.writeString(in_reply_to_status_id);
            parcel.writeString(in_reply_to_user_id);
            parcel.writeString(in_reply_to_screen_name);
            parcel.writeString(geo);
            parcel.writeString(mid);
            parcel.writeInt(reposts_count);
            parcel.writeInt(comments_count);
            parcel.writeStringArray(annotations);
            parcel.writeParcelable(user, i);
        }

        public static final Creator<Status> CREATOR = new Creator<Status>() {
            @Override
            public Status createFromParcel(Parcel in) {
                return new Status(in);
            }

            @Override
            public Status[] newArray(int size) {
                return new Status[size];
            }
        };

        public static class User implements Parcelable {

            private int id;
            private String screen_name;
            private String name;
            private String province;
            private String city;
            private String location;
            private String description;
            private String url;
            private String profile_image_url;
            private String domain;
            private String gender;
            private int followers_count;
            private int friends_count;
            private int statuses_count;
            private int favourites_count;
            private String created_at;
            private boolean following;
            private boolean allow_all_act_msg;
            private String remark;
            private boolean geo_enabled;
            private boolean verified;
            private boolean allow_all_comment;
            private String avatar_large;
            private String verified_reason;
            private boolean follow_me;
            private int online_status;
            private int bi_followers_count;

            protected User(Parcel in) {
                id = in.readInt();
                screen_name = in.readString();
                name = in.readString();
                province = in.readString();
                city = in.readString();
                location = in.readString();
                description = in.readString();
                url = in.readString();
                profile_image_url = in.readString();
                domain = in.readString();
                gender = in.readString();
                followers_count = in.readInt();
                friends_count = in.readInt();
                statuses_count = in.readInt();
                favourites_count = in.readInt();
                created_at = in.readString();
                following = in.readByte() == 1? true: false;
                allow_all_act_msg = in.readByte() == 1? true: false;
                remark = in.readString();
                geo_enabled = in.readByte() == 1? true: false;
                verified = in.readByte() == 1? true: false;
                allow_all_comment = in.readByte() == 1? true: false;
                avatar_large = in.readString();
                verified_reason = in.readString();
                follow_me = in.readByte() == 1? true: false;
                online_status = in.readInt();
                bi_followers_count = in.readInt();
            }

            public int getId() {
                return id;
            }

            public String getScreen_name() {
                return screen_name;
            }

            public String getName() {
                return name;
            }

            public String getProvince() {
                return province;
            }

            public String getCity() {
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

            public String getDomain() {
                return domain;
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

            public String getRemark() {
                return remark;
            }

            public boolean isGeo_enabled() {
                return geo_enabled;
            }

            public boolean isVerified() {
                return verified;
            }

            public boolean isAllow_all_comment() {
                return allow_all_comment;
            }

            public String getAvatar_large() {
                return avatar_large;
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

            @Override
            public int describeContents() {
                return 0;
            }

            @Override
            public void writeToParcel(Parcel parcel, int i) {
                parcel.writeInt(id);
                parcel.writeString(screen_name);
                parcel.writeString(name);
                parcel.writeString(province);
                parcel.writeString(city);
                parcel.writeString(location);
                parcel.writeString(description);
                parcel.writeString(url);
                parcel.writeString(profile_image_url);
                parcel.writeString(domain);
                parcel.writeString(gender);
                parcel.writeInt(followers_count);
                parcel.writeInt(friends_count);
                parcel.writeInt(statuses_count);
                parcel.writeInt(favourites_count);
                parcel.writeString(created_at);
                parcel.writeByte((byte) (following == true? 1: 0));
                parcel.writeByte((byte) (allow_all_act_msg == true? 1: 0));
                parcel.writeString(remark);
                parcel.writeByte((byte) (geo_enabled == true? 1: 0));
                parcel.writeByte((byte) (verified == true? 1: 0));
                parcel.writeByte((byte) (allow_all_comment == true? 1: 0));
                parcel.writeString(avatar_large);
                parcel.writeString(verified_reason);
                parcel.writeByte((byte) (follow_me == true? 1: 0));
                parcel.writeInt(online_status);
                parcel.writeInt(bi_followers_count);
            }

            public static final Creator<User> CREATOR = new Creator<User>() {
                @Override
                public User createFromParcel(Parcel in) {
                    return new User(in);
                }

                @Override
                public User[] newArray(int size) {
                    return new User[size];
                }
            };
        }
    }

    public static class Ad implements Parcelable {

        private int id;
        private String mark;

        protected Ad(Parcel in) {
            id = in.readInt();
            mark = in.readString();
        }

        public int getId() {
            return id;
        }

        public String getMark() {
            return mark;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeInt(id);
            parcel.writeString(mark);
        }

        public static final Creator<Ad> CREATOR = new Creator<Ad>() {
            @Override
            public Ad createFromParcel(Parcel parcel) {
                return new Ad(parcel);
            }

            @Override
            public Ad[] newArray(int size) {
                return new Ad[size];
            }
        };
    }
}
