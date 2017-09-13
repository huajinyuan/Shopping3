package cn.zy.base.shopping.http;


/**
 * Created by  on 2017/2/16.
 */

public interface Config {

    String BASE = "https://www.forudropshipping.com";
    int client_id = 3;
    String client_secret = "5UrNvuI10ok6o4IrG21guD6qrV5urPTd05v3VQSI";
    /**
     * 登录接口
     */
    String POST_LOGIN = BASE + "/api/mobile/oauth/token";
    /**
     * 注册接口
     */
    String POST_REG = BASE + "/api/mobile/oauth/token";
    /**
     * Dashboard
     */
    String DASHBOARD = BASE + "/api/mobile/user/dashboard";
    /**
     * 商品列表
     */
    String PRODUCTS = BASE + "/api/mobile/user/products/";
    /**
     * 商品分类
     */
    String PRODUCTS_CATEGORIES = BASE + "/api/mobile/user/products/categories";
    /**
     * GET 获取详情  PUT 更新商品  DELETE 删除商品
     */
    String PRODUCTS_UPDATE = BASE + "/api/mobile/user/products/{{product_id}}";
    /**
     * Orders
     */
    String ORDER_GROUPS = BASE + "/api/mobile/user/order_groups";
    /**
     * GET 某个订单列表
     */
    String ORDER_GROUPS_BYID = BASE + "/api/mobile/user/order_groups/{order_id}";
    /**
     *  DELETE 删除某个订单列表下的某个订单
     *  GET 显示某个订单列表下的某个订单
     */
    String ORDER_GROUPS_INFO = BASE + "/api/mobile/user/order_groups/{order_id}/order/{id}";

}
