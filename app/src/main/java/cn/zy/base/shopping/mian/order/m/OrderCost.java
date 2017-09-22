package cn.zy.base.shopping.mian.order.m;

import java.io.Serializable;

/**
 * Created by gtgs on 17/9/21.
 */

public class OrderCost implements Serializable {

    private String total;
    private String cost;
    private String shipping;

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getCost() {
        return cost;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }

    public String getShipping() {
        return shipping;
    }

    public void setShipping(String shipping) {
        this.shipping = shipping;
    }

    @Override
    public String toString() {
        return "OrderCost{" +
                "total='" + total + '\'' +
                ", cost='" + cost + '\'' +
                ", shipping='" + shipping + '\'' +
                '}';
    }
}
