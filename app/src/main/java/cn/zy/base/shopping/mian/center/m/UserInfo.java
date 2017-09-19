package cn.zy.base.shopping.mian.center.m;

import java.io.Serializable;

/**
 * Created by gtgs on 17/9/13.
 */

public class UserInfo implements Serializable {
    private String avatar;
    private String name;
    private String email;
    private String balance;

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBalance() {
        return balance;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }

    @Override
    public String toString() {
        return "UserInfo{" +
                "avatar='" + avatar + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", balance='" + balance + '\'' +
                '}';
    }
}