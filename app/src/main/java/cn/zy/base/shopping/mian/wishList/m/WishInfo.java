package cn.zy.base.shopping.mian.wishList.m;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by gtgs on 17/9/13.
 */

public class WishInfo implements Serializable {
    private String id;
    private String public_design_id;
    private String title;
    private String user;
    private String price_range;
    private ArrayList<String> tags;
    private ArrayList<String> images;

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

    public String getPublic_design_id() {
        return public_design_id;
    }

    public void setPublic_design_id(String public_design_id) {
        this.public_design_id = public_design_id;
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
        return "WishInfo{" +
                "id='" + id + '\'' +
                ", public_design_id='" + public_design_id + '\'' +
                ", title='" + title + '\'' +
                ", user='" + user + '\'' +
                ", price_range='" + price_range + '\'' +
                ", tags=" + tags +
                ", images=" + images +
                '}';
    }
}
