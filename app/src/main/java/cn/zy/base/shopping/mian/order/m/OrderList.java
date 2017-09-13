package cn.zy.base.shopping.mian.order.m;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by 51090 on 2017/8/31.
 */

public class OrderList implements Serializable{
    private String next_page_url;
    private ArrayList<OrderInfo> orders;
    private ArrayList<OrderStatuses> statuses;

    public String getNext_page_url() {
        return next_page_url;
    }

    public void setNext_page_url(String next_page_url) {
        this.next_page_url = next_page_url;
    }

    public ArrayList<OrderInfo> getOrders() {
        return orders;
    }

    public void setOrders(ArrayList<OrderInfo> orders) {
        this.orders = orders;
    }

    public ArrayList<OrderStatuses> getStatuses() {
        return statuses;
    }

    public void setStatuses(ArrayList<OrderStatuses> statuses) {
        this.statuses = statuses;
    }

    @Override
    public String toString() {
        return "OrderList{" +
                "next_page_url='" + next_page_url + '\'' +
                ", orders=" + orders +
                ", statuses=" + statuses +
                '}';
    }
}
