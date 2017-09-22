package cn.zy.base.shopping.mian.order.m;

import java.io.Serializable;

/**
 * Created by 51090 on 2017/8/31.
 */

public class OrderInfo implements Serializable {
    private String id;
    private String name;
    private String status;
    private String platform;
    private String quantity;
    private String subtotal;
    /**
     * normal 0
     * DHL 1
     * EMS 2
     */
    private int Delivery = 0;
    private String total;
    private String cost;
    private String shipping;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
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

    public int getDelivery() {
        return Delivery;
    }

    public void setDelivery(int delivery) {
        Delivery = delivery;
    }

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
        return "OrderInfo{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", status='" + status + '\'' +
                ", platform='" + platform + '\'' +
                ", quantity='" + quantity + '\'' +
                ", subtotal='" + subtotal + '\'' +
                ", Delivery=" + Delivery +
                ", total='" + total + '\'' +
                ", cost='" + cost + '\'' +
                ", shipping='" + shipping + '\'' +
                '}';
    }
}
