package cn.zy.base.shopping.mian.order.m;

/**
 * Created by 51090 on 2017/8/31.
 */

public class OrderStatuses {
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
        return "OrderStatuses{" +
                "key='" + key + '\'' +
                ", value='" + value + '\'' +
                '}';
    }
}
