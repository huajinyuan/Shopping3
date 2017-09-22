package cn.zy.base.shopping.mian.design.m;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by gtgs on 17/9/22.
 */

public class DesignInfo implements Serializable {
    private String title;
    private String price_range;
    private String category;
    private String content;
    private ArrayList<String> tags;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPrice_range() {
        return price_range;
    }

    public void setPrice_range(String price_range) {
        this.price_range = price_range;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public ArrayList<String> getTags() {
        return tags;
    }

    public void setTags(ArrayList<String> tags) {
        this.tags = tags;
    }

    @Override
    public String toString() {
        return "DesignInfo{" +
                "title='" + title + '\'' +
                ", price_range='" + price_range + '\'' +
                ", category='" + category + '\'' +
                ", content='" + content + '\'' +
                ", tags=" + tags +
                '}';
    }
}
