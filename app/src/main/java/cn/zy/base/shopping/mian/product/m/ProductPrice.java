package cn.zy.base.shopping.mian.product.m;

import java.io.Serializable;

/**
 * Created by gtgs on 17/9/22.
 */

public class ProductPrice implements Serializable {
    private String price;
    private String suggested_price;

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getSuggested_price() {
        return suggested_price;
    }

    public void setSuggested_price(String suggested_price) {
        this.suggested_price = suggested_price;
    }

    @Override
    public String toString() {
        return "ProductPrice{" +
                "price='" + price + '\'' +
                ", suggested_price='" + suggested_price + '\'' +
                '}';
    }
}
