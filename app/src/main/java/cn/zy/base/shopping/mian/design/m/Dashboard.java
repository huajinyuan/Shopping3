package cn.zy.base.shopping.mian.design.m;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by 51090 on 2017/8/31.
 */

public class Dashboard implements Serializable {
    private String total_pending_payments;
    private String account_balance;
    private String total_products;
    private String total_public_designs;
    private ArrayList<ChatInfo> chart;

    public String getTotal_pending_payments() {
        return total_pending_payments;
    }

    public void setTotal_pending_payments(String total_pending_payments) {
        this.total_pending_payments = total_pending_payments;
    }

    public String getAccount_balance() {
        return account_balance;
    }

    public void setAccount_balance(String account_balance) {
        this.account_balance = account_balance;
    }

    public String getTotal_products() {
        return total_products;
    }

    public void setTotal_products(String total_products) {
        this.total_products = total_products;
    }

    public String getTotal_public_designs() {
        return total_public_designs;
    }

    public void setTotal_public_designs(String total_public_designs) {
        this.total_public_designs = total_public_designs;
    }

    public ArrayList<ChatInfo> getChart() {
        return chart;
    }

    public void setChart(ArrayList<ChatInfo> chart) {
        this.chart = chart;
    }

    @Override
    public String toString() {
        return "Dashboard{" +
                "total_pending_payments=" + total_pending_payments +
                ", account_balance='" + account_balance + '\'' +
                ", total_products='" + total_products + '\'' +
                ", total_public_designs='" + total_public_designs + '\'' +
                ", chart=" + chart +
                '}';
    }
}
