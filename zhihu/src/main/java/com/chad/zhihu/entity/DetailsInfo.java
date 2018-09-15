package com.chad.zhihu.entity;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * 详细信息实体类
 */
public class DetailsInfo implements Parcelable {

    private int id;
    private int type;
    private String ga_prefix;
    private String title;
    private String image_source;
    private String image;
    private String share_url;
    private String body;
    private List<String> js;
    private List<String> css;

    protected DetailsInfo(Parcel parcel) {
        id = parcel.readInt();
        type = parcel.readInt();
        ga_prefix = parcel.readString();
        title = parcel.readString();
        image_source = parcel.readString();
        image = parcel.readString();
        share_url = parcel.readString();
        body = parcel.readString();
        js = parcel.createStringArrayList();
        css = parcel.createStringArrayList();
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getType() {
        return type;
    }

    public void setGa_prefix(String ga_prefix) {
        this.ga_prefix = ga_prefix;
    }

    public String getGa_prefix() {
        return ga_prefix;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setImage_source(String image_source) {
        this.image_source = image_source;
    }

    public String getImage_source() {
        return image_source;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getImage() {
        return image;
    }

    public void setShare_url(String share_url) {
        this.share_url = share_url;
    }

    public String getShare_url() {
        return share_url;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getBody() {
        return body;
    }

    public void setJs(List<String> js) {
        this.js = js;
    }

    public List<String> getJs() {
        return js;
    }

    public void setCss(List<String> css) {
        this.css = css;
    }

    public List<String> getCss() {
        return css;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeInt(type);
        parcel.writeString(ga_prefix);
        parcel.writeString(title);
        parcel.writeString(image_source);
        parcel.writeString(image);
        parcel.writeString(share_url);
        parcel.writeString(body);
        parcel.writeList(js);
        parcel.writeList(css);
    }

    public static final Creator<DetailsInfo> CREATOR = new Creator<DetailsInfo>() {

        @Override
        public DetailsInfo createFromParcel(Parcel parcel) {
            return new DetailsInfo(parcel);
        }

        @Override
        public DetailsInfo[] newArray(int size) {
            return new DetailsInfo[size];
        }
    };
}
