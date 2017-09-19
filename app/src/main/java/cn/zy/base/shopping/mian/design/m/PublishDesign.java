package cn.zy.base.shopping.mian.design.m;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by gtgs on 17/9/13.
 */

public class PublishDesign implements Serializable {
    private String next_page_url;
    private ArrayList<PublishDesignInfo> public_designs;

    public String getNext_page_url() {
        return next_page_url;
    }

    public void setNext_page_url(String next_page_url) {
        this.next_page_url = next_page_url;
    }

    public ArrayList<PublishDesignInfo> getPublic_designs() {
        return public_designs;
    }

    public void setPublic_designs(ArrayList<PublishDesignInfo> public_designs) {
        this.public_designs = public_designs;
    }

    @Override
    public String toString() {
        return "PublishDesign{" +
                "next_page_url='" + next_page_url + '\'' +
                ", public_designs=" + public_designs +
                '}';
    }
}
