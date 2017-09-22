package cn.zy.base.shopping.mian.order.m;

import java.io.Serializable;

/**
 * Created by gtgs on 17/9/21.
 */

public class OrderLogistics implements Serializable {
    private String id;
    private String quantity;
    private String subtotal;
    private String express_agency;
    private String tracking_number;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getExpress_agency() {
        return express_agency;
    }

    public void setExpress_agency(String express_agency) {
        this.express_agency = express_agency;
    }

    public String getTracking_number() {
        return tracking_number;
    }

    public void setTracking_number(String tracking_number) {
        this.tracking_number = tracking_number;
    }

    @Override
    public String toString() {
        return "OrderLogistics{" +
                "id='" + id + '\'' +
                ", quantity='" + quantity + '\'' +
                ", subtotal='" + subtotal + '\'' +
                ", express_agency='" + express_agency + '\'' +
                ", tracking_number='" + tracking_number + '\'' +
                '}';
    }
}
