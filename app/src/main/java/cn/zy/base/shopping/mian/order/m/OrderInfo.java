package cn.zy.base.shopping.mian.order.m;

import java.io.Serializable;

/**
 * Created by 51090 on 2017/8/31.
 */

public class OrderInfo implements Serializable{
    private String id;
    private String name;
    private String status;
    private String platform;
    private String quantity;
    private String subtotal;

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

    @Override
    public String toString() {
        return "OrderInfo{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", status='" + status + '\'' +
                ", platform='" + platform + '\'' +
                ", quantity='" + quantity + '\'' +
                ", subtotal='" + subtotal + '\'' +
                '}';
    }
}
