package cn.zy.base.shopping.mian.product.m;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by gtgs on 17/9/19.
 */

public class ProductTypeList implements Serializable {

    private ArrayList<Category> categories;

    public ArrayList<Category> getCategories() {
        return categories;
    }

    public void setCategories(ArrayList<Category> categories) {
        this.categories = categories;
    }

    @Override
    public String toString() {
        return "ProductTypeList{" +
                "categories=" + categories +
                '}';
    }
}
