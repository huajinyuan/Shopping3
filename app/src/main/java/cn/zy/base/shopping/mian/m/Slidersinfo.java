package cn.zy.base.shopping.mian.m;

import java.io.Serializable;

/**
 * Created by gtgs on 17/9/15.
 */

public class Slidersinfo implements Serializable {
    private String img;
    private String url;

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "Slidersinfo{" +
                "img='" + img + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}
