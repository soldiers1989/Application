package com.chad.weibo.entity;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * 微博的可见性及指定可见分组信息
 */
public class Visible implements Parcelable {

    private int type;
    private int list_id;

    public int getType() {
        return type;
    }

    public int getList_id() {
        return list_id;
    }

    protected Visible(Parcel out) {
        type = out.readInt();
        list_id = out.readInt();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel in, int flags) {
        in.writeInt(type);
        in.writeInt(list_id);
    }

    public static final Creator<Visible> CREATOR = new Creator<Visible>() {
        @Override
        public Visible createFromParcel(Parcel source) {
            return new Visible(source);
        }

        @Override
        public Visible[] newArray(int size) {
            return new Visible[size];
        }
    };
}
