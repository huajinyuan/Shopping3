package cn.zy.base.shopping.mian.product.m;

import java.io.Serializable;

/**
 * Created by gtgs on 17/9/22.
 */

public class ProductAttribute implements Serializable {
    private String key;
    private String value;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "ProductAttribute{" +
                "key='" + key + '\'' +
                ", value='" + value + '\'' +
                '}';
    }
}
