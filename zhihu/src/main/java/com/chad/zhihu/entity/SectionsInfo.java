package com.chad.zhihu.entity;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * 专栏实体类
 */
public class SectionsInfo implements Parcelable {

    private List<Section> data;

    protected SectionsInfo(Parcel parcel) {
        data = new ArrayList<>();
        parcel.readList(data, Section.class.getClassLoader());
    }

    public void setData(List<Section> data) {
        this.data = data;
    }

    public List<Section> getData() {
        return data;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeList(data);
    }

    public static final Creator<SectionsInfo> CREATOR = new Creator<SectionsInfo>() {

        @Override
        public SectionsInfo createFromParcel(Parcel source) {
            return new SectionsInfo(source);
        }

        @Override
        public SectionsInfo[] newArray(int size) {
            return new SectionsInfo[size];
        }
    };

    public static class Section implements Parcelable {
        /**
         * description : 看别人的经历，理解自己的生活
         * id : 1
         * name : 深夜惊奇
         * thumbnail : http://pic3.zhimg.com/91125c9aebcab1c84f58ce4f8779551e.jpg
         */
        private int id;
        private String name;
        private String description;
        private String thumbnail;

        protected Section(Parcel parcel) {
            id = parcel.readInt();
            name = parcel.readString();
            description = parcel.readString();
            thumbnail = parcel.readString();
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getId() {
            return id;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getDescription() {
            return description;
        }

        public void setThumbnail(String thumbnail) {
            this.thumbnail = thumbnail;
        }

        public String getThumbnail() {
            return thumbnail;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeInt(id);
            dest.writeString(name);
            dest.writeString(description);
            dest.writeString(thumbnail);
        }

        public static final Creator<Section> CREATOR = new Creator<Section>() {

            @Override
            public Section createFromParcel(Parcel source) {
                return new Section(source);
            }

            @Override
            public Section[] newArray(int size) {
                return new Section[size];
            }
        };
    }
}
