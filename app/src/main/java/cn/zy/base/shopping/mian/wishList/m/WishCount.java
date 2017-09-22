package cn.zy.base.shopping.mian.wishList.m;

import java.io.Serializable;

/**
 * Created by gtgs on 17/9/22.
 */

public class WishCount implements Serializable {

    private String count;

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "WishCount{" +
                "count='" + count + '\'' +
                '}';
    }
}
