package com.chad.hlife.entity.juhe;

public class QueryInfo {

    private int imageId;
    private int textId;

    public QueryInfo(int imageId, int textId) {
        this.imageId = imageId;
        this.textId = textId;
    }

    public int getImageId() {
        return imageId;
    }

    public int getTextId() {
        return textId;
    }
}
