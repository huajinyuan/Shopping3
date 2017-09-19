package cn.zy.base.shopping.mian.wishList.m;

import java.io.Serializable;
import java.util.ArrayList;

import cn.zy.base.shopping.mian.design.m.PublishDesign;

/**
 * Created by gtgs on 17/9/14.
 */

public class WishList implements Serializable {
    private String next_page_url;
    private ArrayList<WishInfo> wishlists;

    public String getNext_page_url() {
        return next_page_url;
    }

    public void setNext_page_url(String next_page_url) {
        this.next_page_url = next_page_url;
    }

    public ArrayList<WishInfo> getWishlists() {
        return wishlists;
    }

    public void setWishlists(ArrayList<WishInfo> wishlists) {
        this.wishlists = wishlists;
    }

    @Override
    public String toString() {
        return "WishList{" +
                "next_page_url='" + next_page_url + '\'' +
                ", wishlists=" + wishlists +
                '}';
    }
}
