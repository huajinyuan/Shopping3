package cn.zy.base.shopping.mian.order;

import cn.zy.base.shopping.mian.order.m.OrderInfo;

/**
 * Created by gtgs on 17/9/18.
 */

public interface IOrderitemBack {
    void back(OrderInfo o);

    void CheckDelivery(int postion, int isCheck);


}
