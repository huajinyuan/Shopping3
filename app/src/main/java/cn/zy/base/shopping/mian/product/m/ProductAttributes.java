package cn.zy.base.shopping.mian.product.m;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by gtgs on 17/9/19.
 */

public class ProductAttributes implements Serializable {
    private String type;
    private ArrayList<String> options;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public ArrayList<String> getOptions() {
        return options;
    }

    public void setOptions(ArrayList<String> options) {
        this.options = options;
    }

    @Override
    public String toString() {
        return "ProductAttributes{" +
                "type='" + type + '\'' +
                ", options=" + options +
                '}';
    }
}
