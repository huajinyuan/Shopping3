package cn.zy.base.shopping.mian.order.m;

import java.io.Serializable;

/**
 * Created by gtgs on 17/9/13.
 */

public class OrderInfoList implements Serializable {
    private String id;
    private String order_number;
    private String quantity;
    private String subtotal;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOrder_number() {
        return order_number;
    }

    public void setOrder_number(String order_number) {
        this.order_number = order_number;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(String subtotal) {
        this.subtotal = subtotal;
    }

    @Override
    public String toString() {
        return "OrderInfoList{" +
                "id='" + id + '\'' +
                ", order_number='" + order_number + '\'' +
                ", quantity='" + quantity + '\'' +
                ", subtotal='" + subtotal + '\'' +
                '}';
    }
}
