package cn.zy.base.shopping.mian.product.m;

import org.json.JSONArray;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by 51090 on 2017/8/31.
 */

public class ProductInfo implements Serializable {
    private String id;
    private String title;
    private Category category;
    private String price_range;
    private String content;
    private JSONArray prices;
    private ArrayList<ProductAttributes> attributes;
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

    public ArrayList<String> getImages() {
        return images;
    }

    public void setImages(ArrayList<String> images) {
        this.images = images;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public JSONArray getPrices() {
        return prices;
    }

    public void setPrices(JSONArray prices) {
        this.prices = prices;
    }

    public ArrayList<ProductAttributes> getAttributes() {
        return attributes;
    }

    public void setAttributes(ArrayList<ProductAttributes> attributes) {
        this.attributes = attributes;
    }

    @Override
    public String toString() {
        return "ProductInfo{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", category=" + category +
                ", price_range='" + price_range + '\'' +
                ", content='" + content + '\'' +
                ", prices=" + prices +
                ", attributes=" + attributes +
                ", images=" + images +
                '}';
    }
}
