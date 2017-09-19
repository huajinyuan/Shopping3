package cn.zy.base.shopping.mian.design.m;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by gtgs on 17/9/13.
 */

public class PublishDesignInfo implements Serializable {
    private String id;
    private String title;
    private String user;
    private String price_range;
    private boolean is_in_wishlist;
    private ArrayList<String> images;
    private ArrayList<String> tags;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPrice_range() {
        return price_range;
    }

    public void setPrice_range(String price_range) {
        this.price_range = price_range;
    }

    public boolean getIs_in_wishlist() {
        return is_in_wishlist;
    }

    public void setIs_in_wishlist(boolean is_in_wishlist) {
        this.is_in_wishlist = is_in_wishlist;
    }

    public ArrayList<String> getTags() {
        return tags;
    }

    public void setTags(ArrayList<String> tags) {
        this.tags = tags;
    }

    public ArrayList<String> getImages() {
        return images;
    }

    public void setImages(ArrayList<String> images) {
        this.images = images;
    }

    @Override
    public String toString() {
        return "PublishDesignInfo{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", user='" + user + '\'' +
                ", price_range='" + price_range + '\'' +
                ", is_in_wishlist=" + is_in_wishlist +
                ", images=" + images +
                ", tags=" + tags +
                '}';
    }
}
