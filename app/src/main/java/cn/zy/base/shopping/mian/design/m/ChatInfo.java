package cn.zy.base.shopping.mian.design.m;

import java.io.Serializable;

/**
 * Created by 51090 on 2017/8/31.
 */

public class ChatInfo implements Serializable {
    private String month;
    private String total_orders;

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getTotal_orders() {
        return total_orders;
    }

    public void setTotal_orders(String total_orders) {
        this.total_orders = total_orders;
    }

    @Override
    public String toString() {
        return "ChatInfo{" +
                "month='" + month + '\'' +
                ", total_orders='" + total_orders + '\'' +
                '}';
    }
}
