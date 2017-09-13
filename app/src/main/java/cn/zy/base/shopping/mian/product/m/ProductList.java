package cn.zy.base.shopping.mian.product.m;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by 51090 on 2017/8/31.
 */

public class ProductList implements Serializable {
   private String next_page_url;
    private ArrayList<ProductInfo>products;

    public String getNext_page_url() {
        return next_page_url;
    }

    public void setNext_page_url(String next_page_url) {
        this.next_page_url = next_page_url;
    }

    public ArrayList<ProductInfo> getProducts() {
        return products;
    }

    public void setProducts(ArrayList<ProductInfo> products) {
        this.products = products;
    }

    @Override
    public String toString() {
        return "ProductList{" +
                "next_page_url='" + next_page_url + '\'' +
                ", products=" + products +
                '}';
    }
}
