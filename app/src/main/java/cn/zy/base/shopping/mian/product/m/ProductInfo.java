package cn.zy.base.shopping.mian.product.m;

import java.io.Serializable;

/**
 * Created by 51090 on 2017/8/31.
 */

public class ProductInfo implements Serializable{
    private String id;
    private String title;
    private Category category;
    private String price_range;

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

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getPrice_range() {
        return price_range;
    }

    public void setPrice_range(String price_range) {
        this.price_range = price_range;
    }

    @Override
    public String toString() {
        return "ProductInfo{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", category=" + category +
                ", price_range='" + price_range + '\'' +
                '}';
    }
}
