package com.chad.hlife.ui.view.banner;

public class Banner {

    private int id;

    private String title;
    private String image;

    public Banner(int id, String title, String image) {
        this.id = id;
        this.title = title;
        this.image = image;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getImage() {
        return image;
    }
}
