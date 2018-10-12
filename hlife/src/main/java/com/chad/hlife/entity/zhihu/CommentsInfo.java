package com.chad.hlife.entity.zhihu;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * 评论
 */
public class CommentsInfo implements Parcelable {

    private List<Comment> comments;

    protected CommentsInfo(Parcel parcel) {
        comments = new ArrayList<>();
        parcel.readList(comments, Comment.class.getClassLoader());
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public List<Comment> getComments() {
        return comments;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeList(comments);
    }

    public static final Creator<CommentsInfo> CREATOR = new Creator<CommentsInfo>() {

        @Override
        public CommentsInfo createFromParcel(Parcel source) {
            return new CommentsInfo(source);
        }

        @Override
        public CommentsInfo[] newArray(int size) {
            return new CommentsInfo[size];
        }
    };

    public static class Comment implements Parcelable {
        /**
         * author : vivian-小扣
         * content : 只看过三部他的电影，《梦旅人》，《岸边之旅》，和最近的《罗曼蒂克消亡史》，每一部都很出彩。
         * avatar : http://pic4.zhimg.com/6c4ab083a109ff176ff563cc24972cfb_im.jpg
         * time : 1482911777
         * id : 27655915
         * likes : 0
         */
        private int id;
        private int time;
        private int likes;
        private String author;
        private String avatar;
        private String content;

        protected Comment(Parcel parcel) {
            id = parcel.readInt();
            time = parcel.readInt();
            likes = parcel.readInt();
            author = parcel.readString();
            avatar = parcel.readString();
            content = parcel.readString();
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getId() {
            return id;
        }

        public void setTime(int time) {
            this.time = time;
        }

        public int getTime() {
            return time;
        }

        public void setLikes(int likes) {
            this.likes = likes;
        }

        public int getLikes() {
            return likes;
        }

        public void setAuthor(String author) {
            this.author = author;
        }

        public String getAuthor() {
            return author;
        }

        public void setAvatar(String avatar) {
            this.avatar = avatar;
        }

        public String getAvatar() {
            return avatar;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getContent() {
            return content;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeInt(id);
            dest.writeInt(time);
            dest.writeInt(likes);
            dest.writeString(author);
            dest.writeString(avatar);
            dest.writeString(content);
        }

        public static final Creator<Comment> CREATOR = new Creator<Comment>() {

            @Override
            public Comment createFromParcel(Parcel source) {
                return new Comment(source);
            }

            @Override
            public Comment[] newArray(int size) {
                return new Comment[size];
            }
        };
    }
}
